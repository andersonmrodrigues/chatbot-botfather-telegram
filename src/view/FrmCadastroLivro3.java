package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.Date;
import javax.swing.*;
import model.EnumCat;
import model.Livro;

/**
 *
 * @author supti
 * criação da GUI utilizando Combinação de BorderLayout, GridLayout e FlowLayout 
 * (livre redimensionamento)
 */
public class FrmCadastroLivro3 extends JDialog implements ActionListener, FocusListener, KeyListener {
    private JLabel lblId, lblNome, lblAutor, lblData, lblCategoria;
    private JTextField txtId, txtTitulo, txtAutor, txtDataPubl, txtCategoria;
    private JButton btnOk, btnCancelar;
    private Livro l;
            
    public FrmCadastroLivro3(Livro l){
        this.l = l;
        this.setModal(true);
        
        //Inicializando todos os controles-----------
        lblId = new JLabel("Id:");
        lblNome = new JLabel("Título:");
        lblAutor = new JLabel("Autor:");
        lblData = new JLabel("Data de Lcto: ");        
        lblCategoria = new JLabel("Categoria:");   
        txtId = new JTextField(10);
        txtTitulo = new JTextField(30);
        txtTitulo.addFocusListener(this);
        txtAutor = new JTextField(30);
        txtCategoria = new JTextField(30);
        txtCategoria.addFocusListener(this);
        txtDataPubl = new JTextField(10);
        btnOk = new JButton("OK");
        btnOk.setMnemonic('O');
        btnOk.addActionListener(this);//adicionando o 'Ouvinte' para o evento
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setMnemonic('C');
        btnCancelar.addActionListener(this);
        //--------------------------------------------
        
        //este panel é só para os rótulos (um abaixo do outro)
        JPanel pnlLabels = new JPanel();        
        pnlLabels.setLayout(new GridLayout(6,1,5,5));        
        pnlLabels.add(lblId);
        pnlLabels.add(lblNome);
        pnlLabels.add(lblAutor);
        pnlLabels.add(lblCategoria);          
        pnlLabels.add(lblData);       
        pnlLabels.add(new JLabel(""));
        
        JPanel pnlID = new JPanel(new GridLayout(1, 2));
        pnlID.add(txtId);
        pnlID.add(new JLabel(""));       
        
        JPanel pnlData = new JPanel(new GridLayout(1, 2));
        pnlData.add(txtDataPubl);  
        pnlData.add(new JLabel(""));
        
        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));         
        pnlBotoes.add(btnOk);
        pnlBotoes.add(btnCancelar);
        
        //conteiner para todos os panels da direita
        JPanel pnlDireita = new JPanel(new GridLayout(6, 1, 5,5));       
        pnlDireita.add(pnlID);
        pnlDireita.add(txtTitulo);
        pnlDireita.add(txtAutor);
        pnlDireita.add(txtCategoria);  
        pnlDireita.add(pnlData);
        pnlDireita.add(pnlBotoes);
        
        //Criando panel geral (rótulos a esquerda e demais controles a direita)
        JPanel pGeral = new JPanel();
        pGeral.setLayout(new BorderLayout());
        pGeral.add(pnlLabels, BorderLayout.LINE_START);
        pGeral.add(pnlDireita, BorderLayout.CENTER);
        
        this.setContentPane(pGeral);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       //método onde será tratado o evento
       
        if(e.getSource() == btnOk){                       
            l.setAutor(txtAutor.getText());
            l.setTitulo(txtTitulo.getText());
            l.setId(Integer.parseInt(txtId.getText()));
            l.setCategoria(model.EnumCat.valueOf(txtCategoria.getText()));
            l.setDataPublicacao(Date.valueOf(txtDataPubl.getText()));           
            
        }else{
            this.dispose();
        }
            
       
      
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == txtTitulo)
            txtTitulo.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == txtCategoria){
            boolean ok=false;
            for(EnumCat c : EnumCat.values()){
                if(c.toString().equals(txtCategoria.getText())){
                    ok = true;
                    break;
                }
            }
            if(!ok){
                JOptionPane.showMessageDialog(this, "Favor informar uma categoria válida", "Aviso", JOptionPane.WARNING_MESSAGE);
                txtCategoria.requestFocus();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*
        if(e.getSource() == txtLinha){
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
                //código de adicionar texto no textarea
                //txtTexto.append(txtLinha.getText() + "\n");
                
                //para adicionar opções na combobox
                //cmb1.addItem("texto adicionar");
                
                //para selecionar um texto no textarea
                txtTexto.setSelectionStart(8);
                txtTexto.setSelecionEnd(13);
                txtTexto.setSelecionColor(Color.yellow);
        }
        
        else if(e.getSource() == txtTexto){
            if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_F)
                //código de abrir a janela de procura
        }
*/
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
