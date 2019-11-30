/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

import dal.DAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 * @author anderson
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

    private void enviaMensagem(long id, String mensagem, Integer idMsg) {
        ForceReplyKeyboard forceReplyKeyboard = getForceReply();
        SendMessage message = new SendMessage(); // Create a message object object
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
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

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

    private static ForceReplyKeyboard getForceReply() {
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        forceReplyKeyboard.setSelective(true);
        return forceReplyKeyboard;
    }

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
            if (respostaNum.isEmpty()) {
                String resposta = "Não entendi sua resposta. " + verificaClienteAndResponde(id, nome);
                return resposta;
            } else {
                qtd = Integer.valueOf(respostaNum);
                String resposta = "Alguma observacao?";
                return resposta;
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

    private void criaBuscaPedidoEItem() {
        try {
            DAO dao = new DAO();
            Pedido pedido = (Pedido) dao.findPedidoByIdCliente(Pedido.class, cliente.getIdCliente());
            if (pedido == null) {
                pedido = new Pedido();
                pedido.setIdCliente(cliente.getIdCliente());
                pedido.setFgEntregue(false);
                pedido.setFgFinalizado(false);
                pedido.setDtPedido(new java.sql.Date(new Date().getTime()));
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

    private void fechaPedidoByCliente() {
        try {
            DAO dao = new DAO();
            Pedido pedido = (Pedido) dao.findPedidoByIdCliente(Pedido.class, cliente.getIdCliente());
            pedido.setFgFinalizado(true);
            dao.update(pedido);
        } catch (Exception e) {
        }
    }
}
