/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2ps19;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald Macal
 */
public class frmProcesos extends javax.swing.JFrame {
    DefaultTableModel modespera=new DefaultTableModel();
    DefaultTableModel modatencion=new DefaultTableModel();
    DefaultTableModel modlistos=new DefaultTableModel();
    public static int mecanicosdisponibles=0;
    /**
     * Creates new form frmProcesos
     */
    public frmProcesos() {
        initComponents();
        String[] enespera={"Orden","Auto","Cliente"};
        String[] enatencion={"Orden","Auto","Cliente","Mecanico"};
        String[] enlistos={"Orden","Auto","Cliente","Servicio","Total"};
        modespera.setColumnIdentifiers(enespera);
        modatencion.setColumnIdentifiers(enatencion);
        modlistos.setColumnIdentifiers(enlistos);
        tblEspera.setModel(modespera);
        tblAtencion.setModel(modatencion);
        tblListos.setModel(modlistos);
        mecanicosdisponibles=Proyecto2PS19.empleados.mecanicos();
        if(Proyecto2PS19.colaespera.estVacia()&&Proyecto2PS19.colatrabajo.estVacia()&&Proyecto2PS19.pilafacturas.empty()){
            JOptionPane.showMessageDialog(null, "No existen ordenes de trabajo");
        }
        cargarfacturas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEspera = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAtencion = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblListos = new javax.swing.JTable();
        btnMostra = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        btnMostra1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Cola de espera:");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel2.setText("Progreso de carros:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Carros en atención:");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("Carros listos:");

        tblEspera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Orden", "Auto", "Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblEspera);

        tblAtencion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Orden", "Auto", "Cliente", "Mecanico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAtencion);

        tblListos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Orden", "Auto", "Cliente", "Servicio", "Total (Q)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblListos);

        btnMostra.setBackground(new java.awt.Color(0, 0, 51));
        btnMostra.setForeground(new java.awt.Color(255, 255, 255));
        btnMostra.setText("Mostrar");
        btnMostra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostraActionPerformed(evt);
            }
        });

        btnFacturar.setBackground(new java.awt.Color(0, 0, 51));
        btnFacturar.setForeground(new java.awt.Color(255, 255, 255));
        btnFacturar.setText("Facturar servicios");
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });

        btnMostra1.setBackground(new java.awt.Color(0, 0, 51));
        btnMostra1.setForeground(new java.awt.Color(255, 255, 255));
        btnMostra1.setText("Regresar");
        btnMostra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostra1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(169, 169, 169))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(btnMostra)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(346, 346, 346)
                        .addComponent(jLabel4)))
                .addContainerGap(194, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMostra1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 28, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(557, Short.MAX_VALUE)
                    .addComponent(btnFacturar)
                    .addGap(149, 149, 149)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnMostra))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMostra1)
                .addGap(19, 19, 19))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(237, Short.MAX_VALUE)
                    .addComponent(btnFacturar)
                    .addGap(221, 221, 221)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMostra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostra1ActionPerformed
        frmAdministrador admin=new frmAdministrador();
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMostra1ActionPerformed
    public void cargarfacturas(){
        modlistos.setNumRows(0);
        if(!Proyecto2PS19.pilafacturas.empty()){
            Object[] datos=new Object[Proyecto2PS19.cantidadfacturas];
            String aux="";
            for (int i = 0; i < Proyecto2PS19.cantidadfacturas; i++) {
                datos[i]=Proyecto2PS19.pilafacturas.pop();
                aux=(String) datos[i];
                String[] repuesto=aux.split("-");
                modlistos.addRow(repuesto);
            }
            for (int i = Proyecto2PS19.cantidadfacturas-1; i >=0 ; i--) {
                Proyecto2PS19.pilafacturas.push(datos[i]);
            }
        }
        
        
    }
    
    
    private void btnMostraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostraActionPerformed
    //Mostrar los que estan trabajando en ese momento  
    String cadena=" ";
    if(Proyecto2PS19.colaespera.tamaCola()>=mecanicosdisponibles){
        while(Proyecto2PS19.colatrabajo.tamaCola()<mecanicosdisponibles){
            cadena=Proyecto2PS19.colaespera.eliminar();
            Proyecto2PS19.colatrabajo.insertar(cadena);
        }
    }
    cadena=Proyecto2PS19.empleados.nombremecanico();
    String[] mecanicos=cadena.split(":");
    cadena=Proyecto2PS19.colatrabajo.mostrar();
    String[] colatrabajo=cadena.split(":");
    modatencion.setNumRows(0);
    String aux;
    for (int i = 0; i < colatrabajo.length; i++) {
        aux=Proyecto2PS19.ordentrabajo.datosOrden(colatrabajo[i]);
        String[] infoTrabajo=aux.split(":");
        String[] tabla={infoTrabajo[0],infoTrabajo[1],infoTrabajo[2],mecanicos[i]};
        modatencion.addRow(tabla);
    }
        
    //Mostrar los que están en espera
    modespera.setNumRows(0);
    if(Proyecto2PS19.colaespera.tamaCola()==0){
        JOptionPane.showMessageDialog(null, "No hay trabajos en espera.");
    }else{
        cadena=Proyecto2PS19.colaespera.mostrar();
        String[] espera=cadena.split(":");
        for (int i = 0; i < espera.length; i++) {
            cadena=Proyecto2PS19.ordentrabajo.datosOrden(espera[i]);
            String[] enespera=cadena.split(":");
            String[] tabla={enespera[0],enespera[1],enespera[2]};
            modespera.addRow(tabla);
        }
    }    
    }//GEN-LAST:event_btnMostraActionPerformed

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        String orden=Proyecto2PS19.colatrabajo.inicioCola();
        if(orden!=null){
            Proyecto2PS19.colatrabajo.eliminar();
            String repuestos=JOptionPane.showInputDialog("Ingrese el listado de los repuestos separados por una coma(,)");
            String manoobra=JOptionPane.showInputDialog("Ingrese el costo mano de obra (Q), solo numeros");
            String costototal=JOptionPane.showInputDialog("Ingrese el costo total (Q), solo numeros");
            String cadena=Proyecto2PS19.ordentrabajo.facturardatos(orden);
            String[] factura=cadena.split("-");
            cadena=orden+"-"+factura[2]+"-"+costototal;
            Proyecto2PS19.pilafacturas.push(cadena);
            String marca=JOptionPane.showInputDialog("Ingrese la marca del auto");
            String modelo=JOptionPane.showInputDialog("Ingrese el modelo del auto");
            String linea=Proyecto2PS19.idservicios+"-"+factura[3]+"-"+marca+"-"+modelo+"-"+repuestos+"-"+manoobra+"-"+costototal;
            Proyecto2PS19.servicios.agregaralInicio(linea);
            JOptionPane.showMessageDialog(null, "Servicio guardado con éxito");
        }
    }//GEN-LAST:event_btnFacturarActionPerformed

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
            java.util.logging.Logger.getLogger(frmProcesos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmProcesos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmProcesos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmProcesos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmProcesos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnMostra;
    private javax.swing.JButton btnMostra1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblAtencion;
    private javax.swing.JTable tblEspera;
    private javax.swing.JTable tblListos;
    // End of variables declaration//GEN-END:variables
}