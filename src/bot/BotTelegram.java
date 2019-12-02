/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

import dal.DAO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Categoria;
import model.Cliente;
import model.Pedido;
import model.PedidoItem;
import model.Produto;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import static view.FrmProduto.currencyFormat;

/**
 *
 * @author Anderson
 */
public class BotTelegram extends TelegramLongPollingBot {

    private static String token = "1029226417:AAGGRxjQUin91S4yJ13HJ7EByrXajwQ4O0U";
    private static String link = "t.me/GeraldoPoo2Bot";
    private static String botUser = "GeraldoPoo2Bot";
    private static final String endpoint = "https://api.telegram.org/";

    private List<Categoria> catList;
    private List<Produto> prodList;
    private Cliente cliente;
    private Pedido pedido;
    private List<PedidoItem> itemPedidoList;
    private Produto produtoSelecionado;
    private Integer qtd;
    private String obs;
    private boolean fgFechou;

    @Override
    public String getBotToken() {
        return token;
    }

    /**
     * Recebe mensagens enviadas para o bot via telegram
     */
    @Override
    public void onUpdateReceived(Update update) {
        new Thread() {
            @Override
            public void run() {
                if (update.hasMessage() && update.getMessage().hasText()) {
                    String resposta = "";
                    // Set variables
                    String mensagem = update.getMessage().getText();
                    long id = update.getMessage().getChatId();
                    Integer idMsg = update.getMessage().getMessageId();
                    String nome = update.getMessage().getFrom().getFirstName();
                    if (update.getMessage().getReplyToMessage() != null) {
                        resposta = verificaRespostaAndResponde(update.getMessage());
                    } else {
                        resposta = verificaClienteAndResponde(id, nome);
                    }
                    enviaMensagem(id, resposta, idMsg);
                }
            }
        }.start();
    }

    @Override
    public String getBotUsername() {
        return botUser;
    }

    /**
     * Pergunta ou responde mensagem via teleagram
     */
    private void enviaMensagem(long id, String mensagem, Integer idMsg) {
        ForceReplyKeyboard forceReplyKeyboard = getForceReply();
        SendMessage message = new SendMessage();
        message.setChatId(id);
//        message.enableMarkdown(true);
        if (!fgFechou) {
            message.setReplyToMessageId(idMsg);
            message.setReplyMarkup(forceReplyKeyboard);
        } else {
            fgFechou = false;
        }
        message.setText(mensagem);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica se o cliente existe, caso não existir cria um novo
     */
    private String verificaClienteAndResponde(long id, String nome) {
        try {
            DAO dao = new DAO();
            cliente = null;
            Integer idCliente = (int) (long) id;
            cliente = (Cliente) dao.findById(Cliente.class, idCliente);
            if (cliente == null) {
                Cliente clienteNovo = new Cliente();
                clienteNovo.setIdCliente(idCliente);
                clienteNovo.setDsCliente(nome);
                dao.inserir(clienteNovo);
                cliente = (Cliente) dao.findById(Cliente.class, idCliente);
            }
            if (cliente != null) {
                catList = dao.findAll(Categoria.class, null);
                String msgCategorias = "";
                for (Categoria cat : catList) {
                    msgCategorias += cat.getIdCategoria() + " - " + cat.getDsCategoria() + "\n";
                }
                String msg = "";
                msg += cliente.getDsCliente() + ", qual categoria de lanche? \n\n" + msgCategorias;
                return msg;
            }

        } catch (Exception e) {
        }
        return "";
    }

    /**
     * Faz com que a mensagem enviada pelo bot tenha uma resposta
     */
    private static ForceReplyKeyboard getForceReply() {
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        forceReplyKeyboard.setSelective(true);
        return forceReplyKeyboard;
    }

    /**
     * Principal metodo, identifica a resposta que o usuario enviou no telegram
     * E faz as acoes, caso seja uma resposta reconhecida pelo bot
     */
    private String verificaRespostaAndResponde(Message message) {
        String pergunta = message.getText();
        String respostaLetras = pergunta.replaceAll("[^a-zA-Z]", "");
        String respostaNum = pergunta.replaceAll("[^\\d]", "");
        long id = message.getChatId();
        String nome = message.getFrom().getFirstName();

        if (message.getReplyToMessage().getText().toLowerCase().contains("categoria")) {
            Categoria cat = verificaCategoria(respostaLetras, respostaNum);
            if (cat == null) {
                String resposta = "Não entendi sua resposta. " + verificaClienteAndResponde(id, nome);
                return resposta;
            } else {
                String resposta = produtosByCat(cat);
                return resposta;
            }

        }
        if (message.getReplyToMessage().getText().toLowerCase().contains("produto")) {
            Produto p = verificaProduto(respostaLetras, respostaNum);
            if (p == null) {
                String resposta = "Não entendi sua resposta. " + verificaClienteAndResponde(id, nome);
                return resposta;
            } else {
                produtoSelecionado = p;
                String resposta = "Qual a quantidade?";
                return resposta;
            }

        }
        if (message.getReplyToMessage().getText().toLowerCase().contains("quantidade")) {
            if (!respostaLetras.isEmpty() && respostaNum.isEmpty()) {
                Integer extenso = verificaRespostaPorExtenso(respostaLetras);
                if (extenso != null && extenso != 0) {
                    qtd = extenso;
                    String resposta = "Alguma observacao?";
                    return resposta;
                } else {
                    String resposta = "Não entendi sua resposta. " + verificaClienteAndResponde(id, nome);
                    return resposta;
                }
            } else {
                if (respostaNum.isEmpty()) {
                    String resposta = "Não entendi sua resposta. " + verificaClienteAndResponde(id, nome);
                    return resposta;
                } else {
                    qtd = Integer.valueOf(respostaNum);
                    String resposta = "Alguma observacao?";
                    return resposta;
                }
            }
        }
        if (message.getReplyToMessage().getText().toLowerCase().contains("observacao")) {
            if (respostaLetras.isEmpty()) {
                String resposta = "Não entendi sua resposta. " + verificaClienteAndResponde(id, nome);
                return resposta;
            } else {
                String resposta = "Quer adicionar mais algum?";
                if (respostaLetras.equalsIgnoreCase("não") || respostaLetras.equalsIgnoreCase("nao")) {
                    obs = "";
                } else {
                    obs = pergunta;
                }
                criaBuscaPedidoEItem();
                return resposta;
            }
        }
        if (message.getReplyToMessage().getText().toLowerCase().contains("mais algum")) {
            if (respostaLetras.equalsIgnoreCase("sim")) {
                return verificaClienteAndResponde(id, nome);
            } else {
                fechaPedidoByCliente();
                fgFechou = true;
                String resposta = "Pode chegar ai daqui uns 40 minutos meu querido!";
                return resposta;
            }
        }
        return "Não entendi sua resposta. ";
    }

    /**
     * Verifica se a categoria enviada pelo usuario existe cadastrada
     */
    private Categoria verificaCategoria(String respostaLetras, String respostaNum) {
        Categoria cat = null;
        for (Categoria categoria : catList) {
            if (categoria.getDsCategoria().replaceAll("[^a-zA-Z]", "").toLowerCase().equalsIgnoreCase(respostaLetras.toLowerCase())) {
                cat = categoria;
                break;
            }
            if (!respostaNum.isEmpty() && categoria.getIdCategoria().equals(Integer.valueOf(respostaNum))) {
                if (respostaLetras.isEmpty()) {
                    cat = categoria;
                    break;
                }
            }
        }
        return cat;
    }

    /**
     * Busca e envia os produtos da categoria escolhida pelo usuario
     */
    private String produtosByCat(Categoria cat) {
        try {
            DAO dao = new DAO();
            prodList = dao.findAll(Produto.class, null);
            String msgPrd = "";
            for (Produto prod : prodList) {
                msgPrd += prod.getIdProduto() + " - " + prod.getDsProduto() + " - " + currencyFormat(prod.getVlPreco()) + "\n";
            }
            String msg = "";
            msg += cliente.getDsCliente() + ", em qual produto você tem interesse? \n\n" + msgPrd;
            return msg;
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * Verifica se o produto que o usuario escolheu existe cadastrado
     */
    private Produto verificaProduto(String respostaLetras, String respostaNum) {
        Produto p = null;
        for (Produto produto : prodList) {
            if (produto.getDsProduto().replaceAll("[^a-zA-Z]", "").toLowerCase().equalsIgnoreCase(respostaLetras.toLowerCase())) {
                p = produto;
                break;
            }
            if (!respostaNum.isEmpty() && produto.getIdCategoria().equals(Integer.valueOf(respostaNum))) {
                if (respostaLetras.isEmpty()) {
                    p = produto;
                    break;
                }
            }
        }
        return p;
    }

    /**
     * Cria ou busca pedido e adiciona o item a este pedido
     */
    private void criaBuscaPedidoEItem() {
        try {
            DAO dao = new DAO();
            Pedido pedido = (Pedido) dao.findPedidoByIdCliente(Pedido.class, cliente.getIdCliente());
            if (pedido == null) {
                pedido = new Pedido();
                pedido.setIdCliente(cliente.getIdCliente());
                pedido.setFgEntregue(false);
                pedido.setFgFinalizado(false);
                pedido.setDtPedido(new Timestamp(System.currentTimeMillis()));
                dao.inserir(pedido);
                pedido = (Pedido) dao.findPedidoByIdCliente(Pedido.class, cliente.getIdCliente());
            }
            PedidoItem pi = new PedidoItem();
            pi.setDsObservacao(obs);
            pi.setIdPedido(pedido.getIdPedido());
            pi.setIdProduto(produtoSelecionado.getIdProduto());
            pi.setQtPedido(qtd);
            pi.setVlPreco(produtoSelecionado.getVlPreco());
            dao.inserir(pi);
        } catch (Exception e) {
        }

    }

    /**
     * Encerra o pedido do usuario
     */
    private void fechaPedidoByCliente() {
        try {
            DAO dao = new DAO();
            Pedido pedido = (Pedido) dao.findPedidoByIdCliente(Pedido.class, cliente.getIdCliente());
            pedido.setFgFinalizado(true);
            dao.update(pedido);
        } catch (Exception e) {
        }
    }

    /**
     * Verificador se a resposta que usuario deu ao bot para a pergunta de
     * quantidade, é por extenso
     */
    private Integer verificaRespostaPorExtenso(String respostaLetras) {
        respostaLetras = respostaLetras.toLowerCase().trim();
        if (respostaLetras.equalsIgnoreCase("uma")) {
            respostaLetras = "um";
        }
        if (respostaLetras.equalsIgnoreCase("duas")) {
            respostaLetras = "dois";
        }
        if (respostaLetras.equalsIgnoreCase("catorze")) {
            respostaLetras = "quartorze";
        }
        HashMap<String, Integer> numbersMap = new HashMap<String, Integer>();
        numbersMap.put("um", 1);
        numbersMap.put("dois", 2);
        numbersMap.put("tres", 3);
        numbersMap.put("quatro", 4);
        numbersMap.put("cinco", 5);
        numbersMap.put("seis", 6);
        numbersMap.put("sete", 7);
        numbersMap.put("oito", 8);
        numbersMap.put("nove", 9);
        numbersMap.put("dez", 10);
        numbersMap.put("onze", 11);
        numbersMap.put("doze", 12);
        numbersMap.put("treze", 13);
        numbersMap.put("quartorze", 14);
        numbersMap.put("quinze", 15);
        numbersMap.put("dezesseis", 16);
        numbersMap.put("dezessete", 17);
        numbersMap.put("dezoito", 18);
        numbersMap.put("dezenove", 19);
        numbersMap.put("vinte", 20);
        numbersMap.put("trinta", 30);
        numbersMap.put("quarenta", 40);
        numbersMap.put("cinquenta", 50);
        numbersMap.put("sessenta", 60);
        numbersMap.put("setenta", 70);
        numbersMap.put("oitenta", 80);
        numbersMap.put("noventa", 90);
        numbersMap.put("cem", 100);
        try {
            return numbersMap.get(respostaLetras);
        } catch (Exception e) {
            return 0;
        }
    }
}
