package Proyecto1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald Macal
 */
public class frmSolicitudes extends JFrame implements ActionListener{
    JPanel panel;
    JLabel titulo;
    JButton btnmostrar;
    JButton btnautorizar;
    JButton btnrechazar;
    JLabel subtitulo;
    JTextField txtnosolicitud;
    JTable tblprincipal;
    JScrollPane scroll;
    
    public frmSolicitudes(){
        panel=new JPanel();
        titulo=new JLabel("Autorizacion de solicitudes de Préstamo y Tarjetas");
        btnmostrar=new JButton("Mostrar");
        btnautorizar=new JButton("Autorizar");
        btnrechazar=new JButton("Rechazar");
        subtitulo=new JLabel("Ingrese el número de solicitud: ");
        txtnosolicitud=new JTextField();
        
        tblprincipal=new JTable();
        scroll=new JScrollPane();
        scroll.setViewportView(tblprincipal);
        
    }
    
    public void inicializar(){
        //Propiedades básicas del formulario
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Módulo de solicitudes (BDE)");
        setSize(660, 450);
        setLocation(20,30);
        
        //Inicio del trabajo en el Panel
        panel.setSize(660, 450);
        panel.setLayout(null);
        
        titulo.setBounds(20,20,450,20);
        panel.add(titulo);
        
        btnmostrar.setBounds(20,45,125,20);
        panel.add(btnmostrar);
        btnmostrar.addActionListener(this);
        
        //Agregar tabla
        scroll.setBounds(20,80,600,200);
        panel.add(scroll);
        
        subtitulo.setBounds(60,300,250,20);
        panel.add(subtitulo);
        
        txtnosolicitud.setBounds(115,330,120,20);
        panel.add(txtnosolicitud);
        
        btnautorizar.setBounds(60,360,120,20);
        panel.add(btnautorizar);
        btnautorizar.addActionListener(this);
        btnrechazar.setBounds(190,360,120,20);
        panel.add(btnrechazar);
        btnrechazar.addActionListener(this);
        getContentPane().add(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnmostrar)){
            DefaultTableModel md=new DefaultTableModel();
            String[] encabezado={"# Prestamo o tarjeta","Monto adeudado","Abono","Limite"};
            md.setColumnIdentifiers(encabezado);
            md.setRowCount(0);
            String[] solicitud=new String[4];
            for (int i = 0; i < Inicio.solcantidadprestaytarjeta; i++) {
                solicitud[0]=Integer.toString(Inicio.solprestaytarjeta[i][0]);
                solicitud[1]=Integer.toString(Inicio.solprestaytarjeta[i][1]);
                solicitud[2]=Integer.toString(Inicio.solprestaytarjeta[i][2]);
                solicitud[3]=Integer.toString(Inicio.solprestaytarjeta[i][3]);
                md.addRow(solicitud);
            }
            tblprincipal.setModel(md);
        }
        //Funciones del formulario
        if(e.getSource().equals(btnautorizar)){
            autorizar();
            rechazar(false);
        }
        if(e.getSource().equals(btnrechazar)){
            rechazar(true);
        }
    }
    public void rechazar(Boolean rechazo){
        Boolean existe=false;
            String nosolicitud=txtnosolicitud.getText();
            
            for (int i = 0; i < Inicio.solcantidadprestaytarjeta; i++) {
                
                if((Integer.toString(Inicio.solprestaytarjeta[i][0])).equals(nosolicitud)){
                    if((i+1)==Inicio.solcantidadprestaytarjeta){
                        Inicio.solprestaytarjeta[i][0]=0;
                        Inicio.solprestaytarjeta[i][1]=0;
                        Inicio.solprestaytarjeta[i][2]=0;
                        Inicio.solprestaytarjeta[i][3]=0;
                        existe=true;
                    }else if(Inicio.solcantidadprestaytarjeta!=1){
                        for (int j = i; j <= Inicio.solcantidadprestaytarjeta-2; j++) {
                            Inicio.solprestaytarjeta[j][0]=Inicio.solprestaytarjeta[j+1][0];
                            Inicio.solprestaytarjeta[j][1]=Inicio.solprestaytarjeta[j+1][1];
                            Inicio.solprestaytarjeta[j][2]=Inicio.solprestaytarjeta[j+1][2];
                            Inicio.solprestaytarjeta[j][3]=Inicio.solprestaytarjeta[j+1][3];
                            existe=true;
                        }
                    }else{
                        Inicio.solprestaytarjeta[0][0]=0;
                        Inicio.solprestaytarjeta[0][1]=0;
                        Inicio.solprestaytarjeta[0][2]=0;
                        Inicio.solprestaytarjeta[0][3]=0;
                        existe=true;
                    }
                }
            }
            if(existe==false){
                JOptionPane.showMessageDialog(null, "No existe la solicitud");
            }else{
                Inicio.solcantidadprestaytarjeta=Inicio.solcantidadprestaytarjeta-1;
                if(rechazo==true){
                    JOptionPane.showMessageDialog(null, "Solicitud rechazada");
                }else{
                    JOptionPane.showMessageDialog(null, "Solicitud aceptada");
                }
                
            }
    }
    
    public void autorizar(){
        String nosolicitud=txtnosolicitud.getText();
        Boolean paso=false;
        int id=0;
        String[] opciones={"Prestamo","Tarjeta de credito"};
        String seleccionada= (String) JOptionPane.showInputDialog(null, "Que tipo es: ", "Agencias BDE", JOptionPane.DEFAULT_OPTION,null, opciones, opciones[0]);
        
        String comparacion="";
        int[] datos=new int[5];
        for (int i = 0; i < Inicio.solcantidadprestaytarjeta; i++) {
            comparacion=Integer.toString(Inicio.solprestaytarjeta[i][0]);
            if(nosolicitud.equals(comparacion)){
                datos[0]=Inicio.solprestaytarjeta[i][0];
                datos[1]=Inicio.solprestaytarjeta[i][1];
                datos[2]=Inicio.solprestaytarjeta[i][2];
                datos[3]=Inicio.solprestaytarjeta[i][3];
                datos[4]=Inicio.solprestaytarjeta[i][4];
                paso=true;
            } else {}
        }
        if(paso==true){
            Inicio.prestaytarjeta[Inicio.cantidadprestaytarjeta][0]=datos[0];
            Inicio.prestaytarjeta[Inicio.cantidadprestaytarjeta][1]=datos[1];
            Inicio.prestaytarjeta[Inicio.cantidadprestaytarjeta][2]=datos[2];
            Inicio.prestaytarjeta[Inicio.cantidadprestaytarjeta][3]=datos[3];
            Inicio.cantidadprestaytarjeta=Inicio.cantidadprestaytarjeta+1;
            
            String cliente="";
            for (int i = 0; i < Inicio.cantidadclientes; i++) {
                cliente=Inicio.clientes[i][0];
                if(cliente.equals(Integer.toString(datos[4]))){
                    if(seleccionada.equals("Prestamo")){
                        Inicio.clientes[i][6]=nosolicitud;
                    }else{
                        Inicio.clientes[i][7]=nosolicitud;
                    }
                }
            }
            
        }
    }
    
}
