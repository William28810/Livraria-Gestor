/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import data.ItemVendaDao;
import data.VendaDao;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import model.ItemVenda;
import model.Venda;

/**
 *
 * @author sidne
 */
public class TelaRegistroVenda extends javax.swing.JFrame {

    private PainelVendas painelVendas;
    private List<ItemVenda> itensVenda;
    Venda venda = new Venda();
    
    
    /**
     * Creates new form TelaRegistroVenda
     */
    public TelaRegistroVenda(PainelVendas painelVendas) {
        initComponents();
        this.painelVendas = painelVendas;
        inicializarVenda();
        setLocationRelativeTo(null);
    }
    
    public TelaRegistroVenda() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void inicializarVenda(){
       
        
       venda = new Venda();
       
       LocalDateTime agora = LocalDateTime.now();
       
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
       
       txtDataHora.setText(agora.format(formatter));
       
       txtItens.setText("");
       txtCliente.setText("");
       txtFuncionario.setText("");
       txtValorTotal.setText("");
      
    }
    
    private void adicionarItemVenda(){
        try{
            int quantidade = Integer.parseInt(txtItens.getText().trim());
            
            if(quantidade <= 0){
                JOptionPane.showMessageDialog(this, "Quantidade inválida.");
                return;
            }
            
            ItemVenda item = new ItemVenda();
            
            double valor = Double.parseDouble(txtValorTotal.getText().trim());
            
            // VALORES SIMPLIFICADOS
            item.setIdLivro(1);
            item.setQuantidade(quantidade);
            item.setPrecoReal(valor);
            
            venda.getItens().add(item);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro ao adicionar item: " + e.getMessage());
        }
    }
    
    private void obterVendaFormulario(){
        try {
            
            String dataHoraTxt = txtDataHora.getText().trim();
            String clienteTxt = txtCliente.getText().trim();
            String funcionarioTxt = txtFuncionario.getText().trim();
            String valorTxt = txtValorTotal.getText().trim();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraTxt, formatter);
            
            int idCliente = Integer.parseInt(clienteTxt);
            int idFuncionario = Integer.parseInt(funcionarioTxt);
            double valorTotal = Double.parseDouble(valorTxt);
            
            venda.setDataHora(dataHora);
            venda.setIdCliente(idCliente);
            venda.setIdFuncionario(idFuncionario);
            venda.setValorTotal(valorTotal);
            
        }catch(Exception e){
            
            JOptionPane.showConfirmDialog(this, "Erro ao obter dados de venda.");
            e.printStackTrace();
        }
    }
    
    private ItemVenda obterItemFormulario(int idVenda){
        try{
            int idLivro = Integer.parseInt(txtItens.getText().trim());
            double preco = Double.parseDouble(txtValorTotal.getText().trim());
            
            ItemVenda item = new ItemVenda();
            
            item.setIdVenda(idVenda);
            item.setIdLivro(idLivro);
            item.setQuantidade(1);
            item.setPrecoReal(preco);
            
            return item;
            
        }catch(Exception e){
         
            JOptionPane.showMessageDialog(this, "Erro ao obter dados do item.");
            e.printStackTrace();
            
            return null;
        }
        
    }
    
    private void salvarVenda(){
        if(txtItens.getText().trim().isEmpty() || 
           txtDataHora.getText().trim().isEmpty() ||
           txtCliente.getText().trim().isEmpty() ||
           txtFuncionario.getText().trim().isEmpty() ||
           txtValorTotal.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha todos os campos..");
        }
        
        try{
            obterVendaFormulario();
            
            VendaDao vendaDao = new VendaDao();
            
            int idVendaGerado = vendaDao.registroVenda(venda);
            
            if(idVendaGerado == 0){
                JOptionPane.showMessageDialog(this, "Erro ao registrar venda.");
                return;
            }
            
            ItemVendaDao itemDao = new ItemVendaDao();
            
            for(ItemVenda item : venda.getItens()){
                item.setIdVenda(idVendaGerado);
                itemDao.inserirItemVenda(item);
            }
            
            JOptionPane.showMessageDialog(this, "Venda registrada com sucesso.");
            
            if(painelVendas != null){
                painelVendas.carregarVendas();
            }
            
            this.dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro ao salvar venda.");
            e.printStackTrace();
        }
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
        jLabel1 = new javax.swing.JLabel();
        txtItens = new javax.swing.JTextField();
        txtDataHora = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        txtFuncionario = new javax.swing.JTextField();
        txtValorTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JToggleButton();
        btnLimpar = new javax.swing.JToggleButton();
        btnCancelar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Livraria Gestor");

        jPanel1.setBackground(new java.awt.Color(31, 58, 95));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REGISTRO DE VENDA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        txtValorTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorTotalActionPerformed(evt);
            }
        });

        jLabel2.setText("Itens");

        jLabel3.setText("Data/Hora");

        jLabel4.setText("Cliente");

        jLabel5.setText("Funcionário");

        jLabel6.setText("Valor Total");

        btnSalvar.setBackground(new java.awt.Color(31, 58, 95));
        btnSalvar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnLimpar.setBackground(new java.awt.Color(31, 58, 95));
        btnLimpar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpar.setText("LIMPAR");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(31, 58, 95));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCliente)
                    .addComponent(txtFuncionario)
                    .addComponent(txtDataHora)
                    .addComponent(txtItens, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(txtValorTotal)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtItens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpar)
                    .addComponent(btnCancelar))
                .addGap(0, 37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtValorTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorTotalActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        adicionarItemVenda();
        salvarVenda();
        
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        txtItens.setText("");
        txtDataHora.setText("");
        txtCliente.setText("");
        txtFuncionario.setText("");
        txtValorTotal.setText("");
        
        txtItens.requestFocus();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaRegistroVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRegistroVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRegistroVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRegistroVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRegistroVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnCancelar;
    private javax.swing.JToggleButton btnLimpar;
    private javax.swing.JToggleButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDataHora;
    private javax.swing.JTextField txtFuncionario;
    private javax.swing.JTextField txtItens;
    private javax.swing.JTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables
}
