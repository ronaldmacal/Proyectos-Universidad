/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2ps19;

/**
 *
 * @author Ronald Macal
 */
public class frmCliente extends javax.swing.JFrame {
public static String idclienteacceso="";
    /**
     * Creates new form frmCliente
     */
    public frmCliente() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnAutomoviles = new javax.swing.JButton();
        btnProgreso = new javax.swing.JButton();
        btnFacturas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cliente");

        btnRegresar.setBackground(new java.awt.Color(0, 0, 51));
        btnRegresar.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Cerrar Sesión");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("Clientes");

        btnNuevo.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto2ps19/Imagenes/registrar.png"))); // NOI18N
        btnNuevo.setText("Nuevo Auto");
        btnNuevo.setToolTipText("");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnAutomoviles.setBackground(new java.awt.Color(255, 255, 255));
        btnAutomoviles.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAutomoviles.setForeground(new java.awt.Color(0, 0, 0));
        btnAutomoviles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto2ps19/Imagenes/auto.png"))); // NOI18N
        btnAutomoviles.setText("Ver Auto");
        btnAutomoviles.setToolTipText("");
        btnAutomoviles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutomovilesActionPerformed(evt);
            }
        });

        btnProgreso.setBackground(new java.awt.Color(255, 255, 255));
        btnProgreso.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnProgreso.setForeground(new java.awt.Color(0, 0, 0));
        btnProgreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto2ps19/Imagenes/progreso.png"))); // NOI18N
        btnProgreso.setText("Taller");
        btnProgreso.setToolTipText("");
        btnProgreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProgresoActionPerformed(evt);
            }
        });

        btnFacturas.setBackground(new java.awt.Color(255, 255, 255));
        btnFacturas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnFacturas.setForeground(new java.awt.Color(0, 0, 0));
        btnFacturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto2ps19/Imagenes/facturas.png"))); // NOI18N
        btnFacturas.setText("Facturas");
        btnFacturas.setToolTipText("");
        btnFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRegresar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnAutomoviles, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAutomoviles, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnRegresar)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        frmInicio inicio=new frmInicio();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        frmClienteNuevoAuto nuevo=new frmClienteNuevoAuto();
        nuevo.setTitle("Nuevo automóvil");
        nuevo.setVisible(true);
        frmClienteNuevoAuto.idcliente=idclienteacceso;
        this.dispose();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAutomovilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutomovilesActionPerformed
        frmAutomoviles autos=new frmAutomoviles();
        autos.setVisible(true);
        frmAutomoviles.desdecliente=true;
        frmAutomoviles.idcliente=idclienteacceso;
        this.dispose();
    }//GEN-LAST:event_btnAutomovilesActionPerformed

    private void btnProgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProgresoActionPerformed
        frmTaller taller=new frmTaller();
        taller.setVisible(true);
        taller.setTitle("Generar orden para automoviles");
        frmTaller.idcliente=idclienteacceso;
        this.dispose();
    }//GEN-LAST:event_btnProgresoActionPerformed

    private void btnFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturasActionPerformed
        frmFacturas fac=new frmFacturas();
        fac.setVisible(true);
        fac.setTitle("Facturas a pagar");
        frmFacturas.idcliente=idclienteacceso;
        this.dispose();
    }//GEN-LAST:event_btnFacturasActionPerformed

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
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAutomoviles;
    private javax.swing.JButton btnFacturas;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnProgreso;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}