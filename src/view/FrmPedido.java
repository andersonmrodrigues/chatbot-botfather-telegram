/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dal.DAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Pedido;
import model.PedidoItem;
import model.Produto;
import static view.FrmProduto.currencyFormat;

/**
 *
 * @author Anderson e Jean
 */
public class FrmPedido extends javax.swing.JFrame {

    /**
     * Creates new form FrmBiblioteca
     */
    public FrmPedido() {
        initComponents();
        carregaTable();//carregar para a tabela

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        btnFechar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePedido = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePedidoItem = new javax.swing.JTable();
        btnAtualizaTela = new javax.swing.JButton();
        btnFinaliza = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem4.setText("jMenuItem4");

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        jMenuItem7.setText("jMenuItem7");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/Users-Exit-icon.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.setToolTipText("Fechar janela");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        tablePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Pedido", "Cliente", "Data/Hora", "Valor Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablePedido);

        tablePedidoItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod Produto", "Descrição", "Observação", "Qtde", "Vl Unit", "Vl Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablePedidoItem);

        btnAtualizaTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/refresh.png"))); // NOI18N
        btnAtualizaTela.setText("Atualizar Tela");
        btnAtualizaTela.setToolTipText("Atualiza tela de pedidos");
        btnAtualizaTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizaTelaActionPerformed(evt);
            }
        });

        btnFinaliza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/finish.png"))); // NOI18N
        btnFinaliza.setText("Finalizar Entrega");
        btnFinaliza.setToolTipText("Finaliza a entrega de pedido");
        btnFinaliza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnFechar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 181, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAtualizaTela, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnFinaliza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(21, 21, 21))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(202, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btnAtualizaTela)
                .addGap(38, 38, 38)
                .addComponent(btnFinaliza)
                .addGap(66, 66, 66)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFechar)
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(264, Short.MAX_VALUE)))
        );

        jMenu3.setText("Cadastros");
        jMenu3.setToolTipText("Cadastros");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/list.png"))); // NOI18N
        jMenuItem2.setText("Categorias");
        jMenuItem2.setToolTipText("Lista e Cadastro de Categorias");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/list.png"))); // NOI18N
        jMenuItem3.setText("Produtos");
        jMenuItem3.setToolTipText("Lista e Cadastro de Produtos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Botão para fechar a janela
     */
    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFecharActionPerformed

    /**
     * Item Menu de Categoria
     */
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        FrmCategoria cat = new FrmCategoria();
        cat.setTitle("Categoria");
        cat.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * Item Menu de Produto
     */
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        FrmProduto prod = new FrmProduto();
        prod.setTitle("Produto");
        prod.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * Botão para atualizar a tela de pedidos
     */
    private void btnAtualizaTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizaTelaActionPerformed
        carregaTable();
        DefaultTableModel tbl = (DefaultTableModel) tablePedidoItem.getModel();
        tbl.setRowCount(0);//limpando a tabela
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtualizaTelaActionPerformed

    /**
     * Botão para finalizar entrega de pedido
     */
    private void btnFinalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizaActionPerformed
        int row = tablePedido.getSelectedRow();
        int col = 0;
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido a ser finalizado!");
        } else {
            Integer id = (Integer) tablePedido.getValueAt(row, col);
            finalizaPedido(id);
        }
    }//GEN-LAST:event_btnFinalizaActionPerformed

    /**
     * Menu de Categoria
     */
    private void jMenuCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCategoriaActionPerformed
        FrmCategoria cat = new FrmCategoria();
        cat.setTitle("Categoria");
        cat.setVisible(true);
    }//GEN-LAST:event_jMenuCategoriaActionPerformed

    /**
     * Menu de Produto
     */
    private void jMenuProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuProdActionPerformed
        FrmProduto prod = new FrmProduto();
        prod.setTitle("Produto");
        prod.setVisible(true);
    }//GEN-LAST:event_jMenuProdActionPerformed

    /**
     * Método para carregar itens pedidos a partir do pedido selecionado
     */
    private void carregaTableItemPedido(Integer id) {
        try {
            DAO dao = new DAO();
            List<PedidoItem> pedidoItemList = dao.findAll(PedidoItem.class, id);
            DefaultTableModel tbl = (DefaultTableModel) tablePedidoItem.getModel();
            tbl.setRowCount(0);//limpando a tabela
            for (PedidoItem pi : pedidoItemList) {
                pi.setProduto((Produto) dao.findById(Produto.class, pi.getIdProduto()));
                Object[] linha = new Object[6];
                linha[0] = pi.getProduto().getIdProduto();
                linha[1] = pi.getProduto().getDsProduto();
                linha[2] = pi.getDsObservacao();
                linha[3] = pi.getQtPedido();
                linha[4] = currencyFormat(pi.getVlPreco());
                linha[5] = currencyFormat(new BigDecimal(pi.getQtPedido().toString()).multiply(pi.getVlPreco()));
                tbl.addRow(linha);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar pedidos, tente novamente.", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Método que carrega os pedidos na tela de pedidos
     */
    private void carregaTable() {
        try {
            DAO dao = new DAO();

            List<Pedido> pedidoList = dao.findAll(Pedido.class, null);
            //carragar esta lista na JTable
            DefaultTableModel tbl = (DefaultTableModel) tablePedido.getModel();
            tbl.setRowCount(0);//limpando a tabela
            for (Pedido pedido : pedidoList) {
                if (pedido.getIdCliente() != null) {
                    pedido.setCliente((Cliente) dao.findById(Cliente.class, pedido.getIdCliente()));
                }
                List<PedidoItem> pedidoItemList = dao.findAll(PedidoItem.class, pedido.getIdPedido());
                BigDecimal vlTotal = BigDecimal.ZERO;
                for (PedidoItem pi : pedidoItemList) {
                    vlTotal = vlTotal.add(new BigDecimal(pi.getQtPedido().toString()).multiply(pi.getVlPreco()));
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Object[] linha = new Object[4];
                linha[0] = pedido.getIdPedido();
                linha[1] = pedido.getCliente().getDsCliente();
                linha[2] = sdf.format(pedido.getDtPedido());
                linha[3] = currencyFormat(vlTotal);
                tbl.addRow(linha);
            }
            DefaultTableModel tblItens = (DefaultTableModel) tablePedidoItem.getModel();
            tblItens.setRowCount(0);//limpando a tabela
            tablePedido.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int row = tablePedido.getSelectedRow();
                    int col = 0;
                    Integer id = (Integer) tablePedido.getValueAt(row, col);
                    carregaTableItemPedido(id);
                }

            });
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizaTela;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnFinaliza;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablePedido;
    private javax.swing.JTable tablePedidoItem;
    // End of variables declaration//GEN-END:variables

    /**
     * Método para finalizar um pedido
     */
    private void finalizaPedido(Integer id) {
        try {
            DAO dao = new DAO();
            Pedido pedido = (Pedido) dao.findById(Pedido.class, id);
            pedido.setFgEntregue(true);
            dao.update(pedido);
            carregaTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao finalizar o pedido, tente novamente.", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }
}
