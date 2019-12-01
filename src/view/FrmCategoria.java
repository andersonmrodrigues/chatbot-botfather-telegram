/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dal.DAO;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Categoria;

/**
 *
 * @author anderson
 */
public class FrmCategoria extends JDialog {

    private final Set<Integer> pressed = new HashSet<Integer>();

    /**
     * Creates new form FrmCategoria
     */
    public FrmCategoria() {
        initComponents();
        this.setModal(true);
        carregaTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCategoria = new javax.swing.JTable();
        btnAddCat = new javax.swing.JButton();
        btnEditCat = new javax.swing.JButton();
        btnRmvCat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableCategoriaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableCategoria);

        btnAddCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/Add-icon.png"))); // NOI18N
        btnAddCat.setText("Adicionar");
        btnAddCat.setToolTipText("Adicionar Categoria(Ctrl+I)");
        btnAddCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCatActionPerformed(evt);
            }
        });

        btnEditCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/edit.png"))); // NOI18N
        btnEditCat.setText("Editar");
        btnEditCat.setToolTipText("Editar Categoria (Ctrl+E)");
        btnEditCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCatActionPerformed(evt);
            }
        });

        btnRmvCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/Button-Delete-icon.png"))); // NOI18N
        btnRmvCat.setText("Remover");
        btnRmvCat.setToolTipText("Remover Categoria(Ctrl+R)");
        btnRmvCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRmvCatActionPerformed(evt);
            }
        });

        jLabel1.setText("Categoria");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnAddCat, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEditCat, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRmvCat, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(30, Short.MAX_VALUE)))
                    .addComponent(jLabel1)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddCat)
                    .addComponent(btnEditCat)
                    .addComponent(btnRmvCat))
                .addGap(0, 34, Short.MAX_VALUE))
        );

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icones/Users-Exit-icon.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnFechar)
                        .addContainerGap(315, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFechar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRmvCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRmvCatActionPerformed
        try {
            int row = tableCategoria.getSelectedRow();
            if (row != -1) {
                int column = 0;
                Integer id = (Integer) tableCategoria.getValueAt(row, column);
                DAO dao = new DAO();
                Categoria c = new Categoria();
                c.setIdCategoria(id);
                dao.removeById(c);
                carregaTable();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma categoria na tabela.", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao remover a categoria, tente novamente.", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRmvCatActionPerformed

    private void btnEditCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCatActionPerformed
        // TODO add your handling code here:
        try {
            int row = tableCategoria.getSelectedRow();
            if (row != -1) {
                int column = 0;
                Integer id = (Integer) tableCategoria.getValueAt(row, column);
                DAO dao = new DAO();
                Categoria c = (Categoria) dao.findById(Categoria.class, id);
                if (c != null) {
                    FrmFormCategoria cat = new FrmFormCategoria(c);
                    cat.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma categoria na tabela.", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao iniciar edição de categoria, tente novamente.", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        carregaTable();
    }//GEN-LAST:event_btnEditCatActionPerformed

    private void btnAddCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCatActionPerformed
        FrmFormCategoria cat = new FrmFormCategoria();
        cat.setTitle("Categoria");
        cat.setVisible(true);
        carregaTable();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddCatActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFecharActionPerformed

    private void tableCategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCategoriaKeyPressed
        if (evt.isControlDown()) {
            if (evt.getKeyCode() == KeyEvent.VK_I) {
                FrmFormCategoria cat = new FrmFormCategoria();
                cat.setTitle("Categoria");
                cat.setVisible(true);
                carregaTable();
            } else if (evt.getKeyCode() == KeyEvent.VK_E) {
                try {
                    int row = tableCategoria.getSelectedRow();
                    if (row != -1) {
                        int column = 0;
                        Integer id = (Integer) tableCategoria.getValueAt(row, column);
                        DAO dao = new DAO();
                        Categoria c = (Categoria) dao.findById(Categoria.class, id);
                        if (c != null) {
                            FrmFormCategoria cat = new FrmFormCategoria(c);
                            cat.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Selecione uma categoria na tabela.", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Erro ao iniciar edição de categoria, tente novamente.", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
                carregaTable();
            } else if (evt.getKeyCode() == KeyEvent.VK_R) {
                try {
                    int row = tableCategoria.getSelectedRow();
                    if (row != -1) {
                        int column = 0;
                        Integer id = (Integer) tableCategoria.getValueAt(row, column);
                        DAO dao = new DAO();
                        Categoria c = new Categoria();
                        c.setIdCategoria(id);
                        dao.removeById(c);
                        carregaTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Selecione uma categoria na tabela.", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover a categoria, tente novamente.", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_tableCategoriaKeyPressed

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
            java.util.logging.Logger.getLogger(FrmCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCategoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCat;
    private javax.swing.JButton btnEditCat;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnRmvCat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCategoria;
    // End of variables declaration//GEN-END:variables

    private void carregaTable() {
        try {
            DAO dao = new DAO();
            List<Categoria> categoriaList = dao.findAll(Categoria.class, null);
            //carragar esta lista na JTable
            DefaultTableModel tbl = (DefaultTableModel) tableCategoria.getModel();
            tbl.setRowCount(0);//limpando a tabela
            for (Categoria c : categoriaList) {
                Object[] linha = new Object[2];
                linha[0] = c.getIdCategoria();
                linha[1] = c.getDsCategoria();
                tbl.addRow(linha);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }

    }
}
