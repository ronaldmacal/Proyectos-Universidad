package Proyecto1;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald Macal
 */
public class frmAdministrador extends JFrame implements ActionListener {
    int dondeesta=0;
    JPanel panel;
    JLabel lbtitulo;
    JButton btnregresar;
    JLabel lbsubtitulo1;
    JLabel lbsubtitulo2;
    
    //Botones arriba tabla
    JButton btnclientes;
    JButton btnagenciasbanca;
    JButton btnagenciasauto;
    JButton btncajeros;
        
    //Botones bajo la tabla
    JButton btncrear;
    JButton btneditar;
    JButton btneliminar;
    JButton btnbuscar;
    
    //Botones abajo
    JButton btnempleados;
    JButton btnreportes;
    JButton btnsolicitudes;
    
    //Tabla principal
    JScrollPane scroll;
    JTable tblprincipal;
    
    public frmAdministrador(){
        lbtitulo=new JLabel("MÓDULO ADMINISTRATIVO");
        btnregresar=new JButton("Regresar");
        lbsubtitulo1=new JLabel("Opciones a mostrar en tablas");
        lbsubtitulo2=new JLabel("Otros módulos administrador");
        panel=new JPanel();
        
        //Botones arriba tabla
        btnclientes=new JButton("Clientes");
        btnagenciasbanca=new JButton("A. Bancaria");
        btnagenciasauto=new JButton("A. Autobanco");
        btncajeros=new JButton("Cajeros");
        
        //Botones bajo la tabla
        btncrear=new JButton("Nuevo");
        btneditar=new JButton("Editar");
        btneliminar=new JButton("Eliminar");
        btnbuscar=new JButton("Buscar");
        
        //Botones abajo
        btnempleados=new JButton("Empleados");
        btnreportes=new JButton("Reportes");
        btnsolicitudes=new JButton("Solicitudes");
        
        //Tabla principal
        tblprincipal=new JTable();
        scroll=new JScrollPane();
        scroll.setViewportView(tblprincipal);
    }
    public void inicializar(){
        //Propiedades básicas del formulario
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Módulo Administrador (BDE)");
        setSize(750, 500);
        setLocation(20,30);
        
        //Panel arriba propiedades
        panel.setSize(750, 500);
        panel.setLayout(null);
        
        lbtitulo.setBounds(20,10,250,20);
        panel.add(lbtitulo);
        btnregresar.setBounds(600,10,110,20);
        panel.add(btnregresar);
        btnregresar.addActionListener(this);
        lbsubtitulo1.setBounds(20,40,200,20);
        panel.add(lbsubtitulo1);
        
        //Cuatro botones en fila 1
        btnclientes.setBounds(35,60,125,30);
        panel.add(btnclientes);
        btnclientes.addActionListener(this);
        btnagenciasbanca.setBounds(165,60,125,30);
        panel.add(btnagenciasbanca);
        btnagenciasbanca.addActionListener(this);
        btnagenciasauto.setBounds(295,60,125,30);
        panel.add(btnagenciasauto);
        btnagenciasauto.addActionListener(this);
        btncajeros.setBounds(425,60,125,30);
        panel.add(btncajeros);
        btncajeros.addActionListener(this);
        
        //Agregar tabla
        scroll.setBounds(20,100,700,200);
        panel.add(scroll);
        
        //Cuatro botones en fila 2
        btncrear.setBounds(30,310,125,30);
        panel.add(btncrear);
        btncrear.addActionListener(this);
        btneditar.setBounds(165,310,125,30);
        panel.add(btneditar);
        btneditar.addActionListener(this);
        btneliminar.setBounds(300,310,125,30);
        panel.add(btneliminar);
        btneliminar.addActionListener(this);
        btnbuscar.setBounds(435,310,125,30);
        panel.add(btnbuscar);
        btnbuscar.addActionListener(this);
        
        //Cuato bonotes en la última fila
        lbsubtitulo2.setBounds(30,365,250,20);
        panel.add(lbsubtitulo2);
        
        btnempleados.setBounds(30,395,125,30);
        btnempleados.addActionListener(this);
        btnempleados.setBackground(Color.lightGray);
        panel.add(btnempleados);
        btnreportes.setBounds(175,395,125,30);
        btnreportes.addActionListener(this);
        btnreportes.setBackground(Color.lightGray);
        panel.add(btnreportes);
        btnsolicitudes.setBounds(310,395,125,30);
        btnsolicitudes.addActionListener(this);
        btnsolicitudes.setBackground(Color.lightGray);
        panel.add(btnsolicitudes);
        
        getContentPane().add(panel); 
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnregresar)){
            Inicio.inicio();
            this.dispose();
        }
        if(e.getSource().equals(btnempleados)){
            frmEmpleados emple=new frmEmpleados();
            emple.inicializar();
            emple.setVisible(true);
        }
        if(e.getSource().equals(btnsolicitudes)){
            frmSolicitudes sol=new frmSolicitudes();
            sol.inicializar();
            sol.setVisible(true);
        }
        if(e.getSource().equals(btnreportes)){
            frmReportes repo=new frmReportes();
            repo.inicializar();
            repo.setVisible(true);
        }
        //Funciones de llenado de la tabla
        if(e.getSource().equals(btnclientes)){
            DefaultTableModel md=new DefaultTableModel();
            String[] encabezado={"Codigo","Nombre","Dirección","Telefono","# Ahorro","# Monetaria","# Prestamo","# T. Credito"};
            md.setColumnIdentifiers(encabezado);
            md.setRowCount(0);
            String[] clientes=new String[8];
            for (int i = 0; i < Inicio.cantidadclientes; i++) {
                clientes[0]=Inicio.clientes[i][0];
                clientes[1]=Inicio.clientes[i][1];
                clientes[2]=Inicio.clientes[i][2];
                clientes[3]=Inicio.clientes[i][3];
                clientes[4]=Inicio.clientes[i][4];
                clientes[5]=Inicio.clientes[i][5];
                clientes[6]=Inicio.clientes[i][6];
                clientes[7]=Inicio.clientes[i][7];
                md.addRow(clientes);
            }
            tblprincipal.setModel(md);
            dondeesta=1;
        }
        if(e.getSource().equals(btnagenciasbanca)){
            DefaultTableModel md=new DefaultTableModel();
            String[] encabezado={"Nombre","# Agencia","Dirección","Telefono","# Cajas","# Escritorios","Efectivo (Q)"};
            md.setColumnIdentifiers(encabezado);
            md.setRowCount(0);
            String[] agencia=new String[7];
            for (int i = 0; i < Inicio.cantidadagenciabancaria; i++) {
                agencia[0]=Inicio.agenciabancaria[i][0];
                agencia[1]=Inicio.agenciabancaria[i][1];
                agencia[2]=Inicio.agenciabancaria[i][2];
                agencia[3]=Inicio.agenciabancaria[i][3];
                agencia[4]=Inicio.agenciabancaria[i][4];
                agencia[5]=Inicio.agenciabancaria[i][5];
                agencia[6]=Integer.toString(Inicio.efectivoagenciabancaria[i][1]);
                md.addRow(agencia);
            }
            tblprincipal.setModel(md);
            dondeesta=2;
        }
        if(e.getSource().equals(btnagenciasauto)){
            DefaultTableModel md=new DefaultTableModel();
            String[] encabezado={"Nombre","# Agencia","Dirección","Telefono","# Cajas","# Escritorios","Efectivo (Q)"};
            md.setColumnIdentifiers(encabezado);
            md.setRowCount(0);
            String[] autobanco=new String[7];
            for (int i = 0; i < Inicio.cantidadagenciaautobanco; i++) {
                autobanco[0]=Inicio.agenciaautobanco[i][0];
                autobanco[1]=Inicio.agenciaautobanco[i][1];
                autobanco[2]=Inicio.agenciaautobanco[i][2];
                autobanco[3]=Inicio.agenciaautobanco[i][3];
                autobanco[4]=Inicio.agenciaautobanco[i][4];
                autobanco[5]=Inicio.agenciaautobanco[i][5];
                autobanco[6]=Integer.toString(Inicio.efectivoautobanco[i][1]);
                md.addRow(autobanco);
            }
            tblprincipal.setModel(md);
            dondeesta=3;
        }
        if(e.getSource().equals(btncajeros)){
            DefaultTableModel md=new DefaultTableModel();
            String[] encabezado={"ID Cajero","Ubicacion","Estado","Efectivo (Q)","# Transacciones"};
            md.setColumnIdentifiers(encabezado);
            md.setRowCount(0);
            String[] cajero=new String[5];
            for (int i = 0; i < Inicio.cantidadcajeros; i++) {
                cajero[0]=Inicio.cajeros[i][0];
                cajero[1]=Inicio.cajeros[i][1];
                cajero[2]=Inicio.cajeros[i][2];
                cajero[3]=Integer.toString(Inicio.efectivocajeros[i][1]);
                cajero[4]=Integer.toString(Inicio.efectivocajeros[i][2]);
                md.addRow(cajero);
            }
            tblprincipal.setModel(md);
            dondeesta=4;
        }
        //Funciones dentro del formulario
        if(e.getSource().equals(btncrear)){crear();}
        if(e.getSource().equals(btneditar)){editar();}
        if(e.getSource().equals(btneliminar)){eliminar();}
        if(e.getSource().equals(btnbuscar)){buscar();}
    }
    //Métodos para el formulario
    public void crear(){
        switch(dondeesta){
            case 1:
                //Pedir valores del nuevo ingreso
                try {
                    Inicio.clientes[Inicio.cantidadclientes][0]=JOptionPane.showInputDialog("Ingrese el ID del cliente: ");
                    Inicio.clientes[Inicio.cantidadclientes][1]=JOptionPane.showInputDialog("Ingrese el nombre del cliente: ");
                    Inicio.clientes[Inicio.cantidadclientes][2]=JOptionPane.showInputDialog("Ingrese el dirección del cliente: ");
                    Inicio.clientes[Inicio.cantidadclientes][3]=JOptionPane.showInputDialog("Ingrese el Telefono celular del cliente: ");
                    Inicio.clientes[Inicio.cantidadclientes][4]="0";
                    Inicio.clientes[Inicio.cantidadclientes][5]="0";
                    Inicio.clientes[Inicio.cantidadclientes][6]="0";
                    Inicio.clientes[Inicio.cantidadclientes][7]="0";
                    Inicio.clientes[Inicio.cantidadclientes][8]="0";
                    Inicio.cantidadclientes=Inicio.cantidadclientes+1;
                    JOptionPane.showMessageDialog(null, "Nuevo cliente guardado");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error, datos inválidos");
                }  
                break;
            case 2:
                try {
                    Inicio.agenciabancaria[Inicio.cantidadagenciabancaria][0]=JOptionPane.showInputDialog("Ingrese el nombre de la agencia: ");
                    Inicio.agenciabancaria[Inicio.cantidadagenciabancaria][1]=JOptionPane.showInputDialog("Ingrese el ID de la agencia: ");
                    Inicio.agenciabancaria[Inicio.cantidadagenciabancaria][2]=JOptionPane.showInputDialog("Ingrese el dirección de la agencia: ");
                    Inicio.agenciabancaria[Inicio.cantidadagenciabancaria][3]=JOptionPane.showInputDialog("Ingrese el Telefono  de la agencia: ");
                    Inicio.agenciabancaria[Inicio.cantidadagenciabancaria][4]=JOptionPane.showInputDialog("Ingrese el numero de cajas: ");
                    Inicio.agenciabancaria[Inicio.cantidadagenciabancaria][5]=JOptionPane.showInputDialog("Ingrese el numero de escritorios en servicio: ");
                    Inicio.efectivoagenciabancaria[Inicio.cantidadagenciabancaria][0]=Integer.parseInt(Inicio.agenciabancaria[Inicio.cantidadagenciabancaria][1]);
                    Inicio.efectivoagenciabancaria[Inicio.cantidadagenciabancaria][1]=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto con el que inicia la agencia: "));
                    Inicio.cantidadagenciabancaria=Inicio.cantidadagenciabancaria+1;
                    JOptionPane.showMessageDialog(null, "Nueva agencia guardada");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error, datos inválidos");
                }
                break;
            case 3:
                try {
                    Inicio.agenciaautobanco[Inicio.cantidadagenciaautobanco][0]=JOptionPane.showInputDialog("Ingrese el nombre de la agencia: ");
                    Inicio.agenciaautobanco[Inicio.cantidadagenciaautobanco][1]=JOptionPane.showInputDialog("Ingrese el ID de la agencia: ");
                    Inicio.agenciaautobanco[Inicio.cantidadagenciaautobanco][2]=JOptionPane.showInputDialog("Ingrese el dirección de la agencia: ");
                    Inicio.agenciaautobanco[Inicio.cantidadagenciaautobanco][3]=JOptionPane.showInputDialog("Ingrese el Telefono  de la agencia: ");
                    Inicio.agenciaautobanco[Inicio.cantidadagenciaautobanco][4]=JOptionPane.showInputDialog("Ingrese el numero de cajas: ");
                    Inicio.agenciaautobanco[Inicio.cantidadagenciaautobanco][5]=JOptionPane.showInputDialog("Ingrese el numero de escritorios en servicio: ");
                    Inicio.efectivoautobanco[Inicio.cantidadagenciaautobanco][0]=Integer.parseInt(Inicio.agenciaautobanco[Inicio.cantidadagenciaautobanco][1]);
                    Inicio.efectivoautobanco[Inicio.cantidadagenciaautobanco][1]=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto con el que inicia la agencia: "));
                    Inicio.cantidadagenciaautobanco=Inicio.cantidadagenciaautobanco+1;
                    JOptionPane.showMessageDialog(null, "Nueva agencia con autobanco guardada");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error, datos inválidos");
                }
                break;
            case 4:
                try {
                    Inicio.cajeros[Inicio.cantidadcajeros][0]=JOptionPane.showInputDialog("Ingrese el ID del cajero: ");
                    Inicio.cajeros[Inicio.cantidadcajeros][1]=JOptionPane.showInputDialog("Ingrese ubicacion cajero: ");
                    Inicio.cajeros[Inicio.cantidadcajeros][2]="Activo";
                    Inicio.efectivocajeros[Inicio.cantidadcajeros][0]=Integer.parseInt(Inicio.cajeros[Inicio.cantidadcajeros][0]);
                    Inicio.efectivocajeros[Inicio.cantidadcajeros][1]=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el efecivo inicial del cajero: "));
                    Inicio.efectivocajeros[Inicio.cantidadcajeros][2]=0;
                    Inicio.cantidadcajeros=Inicio.cantidadcajeros+1;
                    JOptionPane.showMessageDialog(null, "Nuevo cajero guardado");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error, datos inválidos");
                }
                break;
        }
    }
    
    public void editar(){
        String id=JOptionPane.showInputDialog("Ingrese el ID del cliente o agencia. PD(Los valores numericos no pueden cambiarse)");
        switch(dondeesta){
            case 1:
                try {
                    for (int i = 0; i < Inicio.cantidadclientes; i++) {
                        if(id.equals(Inicio.clientes[i][0])){
                            Inicio.clientes[i][0]=JOptionPane.showInputDialog("ID acutal: "+id+". Ingrese el ID nuevo del cliente");
                            Inicio.clientes[i][1]=JOptionPane.showInputDialog("Nombre acutal: "+Inicio.clientes[i][1]+". Ingrese el nuevo nombre del cliente");
                            Inicio.clientes[i][2]=JOptionPane.showInputDialog("Direccion acutal: "+Inicio.clientes[i][2]+". Ingrese el nuevo direccion del cliente");
                            Inicio.clientes[i][3]=JOptionPane.showInputDialog("Telefono acutal: "+Inicio.clientes[i][3]+". Ingrese el nuevo telefono del cliente");
                            JOptionPane.showMessageDialog(null, "Valores cambiados correctamente");
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error, datos inválidos");
                }
                break;
            case 2:
                try {
                    for (int i = 0; i < Inicio.cantidadagenciabancaria; i++) {
                        if(id.equals(Inicio.agenciabancaria[i][1])){
                            Inicio.agenciabancaria[i][0]=JOptionPane.showInputDialog("Nombre acutal: "+Inicio.agenciabancaria[i][0]+". Ingrese el nuevo nombre de la agencia: ");
                            Inicio.agenciabancaria[i][1]=JOptionPane.showInputDialog("ID agencia acutal: "+Inicio.agenciabancaria[i][1]+". Ingrese ID agencia nueva:");
                            Inicio.agenciabancaria[i][2]=JOptionPane.showInputDialog("Direccion acutal: "+Inicio.agenciabancaria[i][2]+". Ingrese el nueva direccion: ");
                            Inicio.agenciabancaria[i][3]=JOptionPane.showInputDialog("Telefono acutal: "+Inicio.agenciabancaria[i][3]+". Ingrese el nuevo telefono de agencia");
                            Inicio.agenciabancaria[i][4]=JOptionPane.showInputDialog("# Cajas acutal: "+Inicio.agenciabancaria[i][4]+". Ingrese el # de Cajas");
                            Inicio.agenciabancaria[i][5]=JOptionPane.showInputDialog("# Escritorios acutal: "+Inicio.agenciabancaria[i][5]+". Ingrese el # de Escritorios: ");
                            JOptionPane.showMessageDialog(null, "Valores cambiados correctamente");
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error, datos inválidos");
                }
                break;
            case 3: 
                try {
                    for (int i = 0; i < Inicio.cantidadagenciaautobanco; i++) {
                        if(id.equals(Inicio.agenciaautobanco[i][1])){
                            Inicio.agenciaautobanco[i][0]=JOptionPane.showInputDialog("Nombre acutal: "+Inicio.agenciaautobanco[i][0]+". Ingrese el nuevo nombre de la agencia: ");
                            Inicio.agenciaautobanco[i][1]=JOptionPane.showInputDialog("ID agencia acutal: "+Inicio.agenciaautobanco[i][1]+". Ingrese ID agencia nueva:");
                            Inicio.agenciaautobanco[i][2]=JOptionPane.showInputDialog("Direccion acutal: "+Inicio.agenciaautobanco[i][2]+". Ingrese el nueva direccion: ");
                            Inicio.agenciaautobanco[i][3]=JOptionPane.showInputDialog("Telefono acutal: "+Inicio.agenciaautobanco[i][3]+". Ingrese el nuevo telefono de agencia");
                            Inicio.agenciaautobanco[i][4]=JOptionPane.showInputDialog("# Cajas acutal: "+Inicio.agenciaautobanco[i][4]+". Ingrese el # de Cajas");
                            Inicio.agenciaautobanco[i][5]=JOptionPane.showInputDialog("# Escritorios acutal: "+Inicio.agenciaautobanco[i][5]+". Ingrese el # de Escritorios: ");
                            JOptionPane.showMessageDialog(null, "Valores cambiados correctamente");
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error, datos inválidos");
                }
                break;
            case 4:
                try {
                    for (int i = 0; i < Inicio.cantidadcajeros; i++) {
                        if(id.equals(Inicio.cajeros[i][0])){
                            Inicio.cajeros[i][0]=JOptionPane.showInputDialog("ID cajero acutal: "+Inicio.cajeros[i][0]+". Ingrese ID cajero nuevo:");
                            Inicio.cajeros[i][1]=JOptionPane.showInputDialog("Direccion acutal: "+Inicio.cajeros[i][1]+". Ingrese el nueva direccion: ");
                            String[] opciones={"Activo","Inactivo"};
                            int seleccion=JOptionPane.showOptionDialog(null, "Ingrese el estado del cajero", "Estado de Cajero", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                            String estado="";
                            if(seleccion==0){estado="Activo";}else{estado="Inactivo";}
                            Inicio.cajeros[i][2]=estado;
                            
                            Inicio.efectivocajeros[i][0]=Integer.parseInt(Inicio.cajeros[i][0]);
                            
                            JOptionPane.showMessageDialog(null, "Valores cambiados correctamente");
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error, datos inválidos");
                }
                break;
        }
    }
    
    public void eliminar(){
        String id=JOptionPane.showInputDialog("Ingrese el ID de la agencia o cliente a eliminar: ");
        Boolean existe=false;
        switch(dondeesta){
            case 1:
                //Aqui inicia la eliminacion de los clientes
                for (int i = 0; i < Inicio.cantidadclientes; i++) {
                    if((Inicio.clientes[i][0]).equals(id)){
                        if((i+1)==Inicio.cantidadclientes){
                            Inicio.clientes[i][0]="";
                            Inicio.clientes[i][1]="";
                            Inicio.clientes[i][2]="";
                            Inicio.clientes[i][3]="";
                            eliminarcuenta(Inicio.clientes[i][4]);
                            Inicio.clientes[i][4]="";
                            eliminarcuenta(Inicio.clientes[i][5]);
                            Inicio.clientes[i][5]="";
                            eliminarprestaytarjeta(Inicio.clientes[i][6]);
                            Inicio.clientes[i][6]="";
                            eliminarprestaytarjeta(Inicio.clientes[i][7]);
                            Inicio.clientes[i][7]="";
                            Inicio.clientes[i][8]="";
                            
                            existe=true;
                        }else if(Inicio.cantidadclientes!=1){
                            for (int j = i; j <= Inicio.cantidadclientes-2; j++) {
                                Inicio.clientes[j][0]=Inicio.clientes[j+1][0];
                                Inicio.clientes[j][1]=Inicio.clientes[j+1][1];
                                Inicio.clientes[j][2]=Inicio.clientes[j+1][2];
                                Inicio.clientes[j][3]=Inicio.clientes[j+1][3];
                                eliminarcuenta(Inicio.clientes[j][4]);
                                Inicio.clientes[j][4]=Inicio.clientes[j+1][4];
                                eliminarcuenta(Inicio.clientes[j][5]);
                                Inicio.clientes[j][5]=Inicio.clientes[j+1][5];
                                eliminarprestaytarjeta(Inicio.clientes[j][6]);
                                Inicio.clientes[j][6]=Inicio.clientes[j+1][6];
                                eliminarprestaytarjeta(Inicio.clientes[j][7]);
                                Inicio.clientes[j][7]=Inicio.clientes[j+1][7];
                                Inicio.clientes[j][8]=Inicio.clientes[j+1][7];
                                existe=true;
                            }
                        }else{
                            Inicio.clientes[i][0]="";
                            Inicio.clientes[i][1]="";
                            Inicio.clientes[i][2]="";
                            Inicio.clientes[i][3]="";
                            eliminarcuenta(Inicio.clientes[i][4]);
                            Inicio.clientes[i][4]="";
                            eliminarcuenta(Inicio.clientes[i][5]);
                            Inicio.clientes[i][5]="";
                            eliminarprestaytarjeta(Inicio.clientes[i][6]);
                            Inicio.clientes[i][6]="";
                            eliminarprestaytarjeta(Inicio.clientes[i][7]);
                            Inicio.clientes[i][7]="";
                            Inicio.clientes[i][8]="";
                            existe=true;
                        }
                    }
                }
                if(existe==false){
                    JOptionPane.showMessageDialog(null, "No existe el cliente");
                }else{
                    Inicio.cantidadclientes=Inicio.cantidadclientes-1;
                    JOptionPane.showMessageDialog(null, "Cliente eliminado");
                }
                break;
                //Aqui terminan los clientes
                
            case 2:
                for (int i = 0; i < Inicio.cantidadagenciabancaria; i++) {
                    if((Inicio.agenciabancaria[i][1]).equals(id)){
                        if((i+1)==Inicio.cantidadagenciabancaria){
                            Inicio.agenciabancaria[i][0]="";
                            Inicio.agenciabancaria[i][1]="";
                            Inicio.agenciabancaria[i][2]="";
                            Inicio.agenciabancaria[i][3]="";
                            Inicio.agenciabancaria[i][4]="";
                            Inicio.agenciabancaria[i][5]="";
                            Inicio.efectivoagenciabancaria[i][0]=0;
                            Inicio.efectivoagenciabancaria[i][1]=0;
                            existe=true;
                        }else if(Inicio.cantidadagenciabancaria!=1){
                            for (int j = i; j <= Inicio.cantidadagenciabancaria-2; j++) {
                                Inicio.agenciabancaria[j][0]=Inicio.agenciabancaria[j+1][0];
                                Inicio.agenciabancaria[j][1]=Inicio.agenciabancaria[j+1][1];
                                Inicio.agenciabancaria[j][2]=Inicio.agenciabancaria[j+1][2];
                                Inicio.agenciabancaria[j][3]=Inicio.agenciabancaria[j+1][3];
                                Inicio.agenciabancaria[j][4]=Inicio.agenciabancaria[j+1][4];
                                Inicio.agenciabancaria[j][5]=Inicio.agenciabancaria[j+1][5];
                                Inicio.efectivoagenciabancaria[j][0]=Inicio.efectivoagenciabancaria[j+1][0];
                                Inicio.efectivoagenciabancaria[j][1]=Inicio.efectivoagenciabancaria[j+1][1];
                                existe=true;
                            }
                        }else{
                            Inicio.agenciabancaria[i][0]="";
                            Inicio.agenciabancaria[i][1]="";
                            Inicio.agenciabancaria[i][2]="";
                            Inicio.agenciabancaria[i][3]="";
                            Inicio.agenciabancaria[i][4]="";
                            Inicio.agenciabancaria[i][5]="";
                            Inicio.efectivoagenciabancaria[i][0]=0;
                            Inicio.efectivoagenciabancaria[i][1]=0;
                            existe=true;
                        }
                    }
                }
                if(existe==false){
                    JOptionPane.showMessageDialog(null, "No existe la agencia");
                }else{
                    Inicio.cantidadagenciabancaria=Inicio.cantidadagenciabancaria-1;
                    JOptionPane.showMessageDialog(null, "Agencia bancaria eliminada");
                }
                break;
            case 3:
                for (int i = 0; i < Inicio.cantidadagenciaautobanco; i++) {
                    if((Inicio.agenciaautobanco[i][1]).equals(id)){
                        if((i+1)==Inicio.cantidadagenciaautobanco){
                            Inicio.agenciaautobanco[i][0]="";
                            Inicio.agenciaautobanco[i][1]="";
                            Inicio.agenciaautobanco[i][2]="";
                            Inicio.agenciaautobanco[i][3]="";
                            Inicio.agenciaautobanco[i][4]="";
                            Inicio.agenciaautobanco[i][5]="";
                            Inicio.efectivoautobanco[i][0]=0;
                            Inicio.efectivoautobanco[i][1]=0;
                            existe=true;
                        }else if(Inicio.cantidadagenciaautobanco!=1){
                            for (int j = i; j <= Inicio.cantidadagenciaautobanco-2; j++) {
                                Inicio.agenciaautobanco[j][0]=Inicio.agenciaautobanco[j+1][0];
                                Inicio.agenciaautobanco[j][1]=Inicio.agenciaautobanco[j+1][1];
                                Inicio.agenciaautobanco[j][2]=Inicio.agenciaautobanco[j+1][2];
                                Inicio.agenciaautobanco[j][3]=Inicio.agenciaautobanco[j+1][3];
                                Inicio.agenciaautobanco[j][4]=Inicio.agenciaautobanco[j+1][4];
                                Inicio.agenciaautobanco[j][5]=Inicio.agenciaautobanco[j+1][5];
                                Inicio.efectivoautobanco[j][0]=Inicio.efectivoautobanco[j+1][0];
                                Inicio.efectivoautobanco[j][1]=Inicio.efectivoautobanco[j+1][1];
                                existe=true;
                            }
                        }else{
                            Inicio.agenciaautobanco[i][0]="";
                            Inicio.agenciaautobanco[i][1]="";
                            Inicio.agenciaautobanco[i][2]="";
                            Inicio.agenciaautobanco[i][3]="";
                            Inicio.agenciaautobanco[i][4]="";
                            Inicio.agenciaautobanco[i][5]="";
                            Inicio.efectivoautobanco[i][0]=0;
                            Inicio.efectivoautobanco[i][1]=0;
                            existe=true;
                        }
                    }
                }
                if(existe==false){
                    JOptionPane.showMessageDialog(null, "No existe la agencia con autobanco");
                }else{
                    Inicio.cantidadagenciaautobanco=Inicio.cantidadagenciaautobanco-1;
                    JOptionPane.showMessageDialog(null, "Agencia bancaria con autobanco eliminada");
                }
                break;
            case 4:
                for (int i = 0; i < Inicio.cantidadcajeros; i++) {
                    if((Inicio.cajeros[i][0]).equals(id)){
                        if((i+1)==Inicio.cantidadcajeros){
                            Inicio.cajeros[i][0]="";
                            Inicio.cajeros[i][1]="";
                            Inicio.cajeros[i][2]="";
                            Inicio.efectivocajeros[i][0]=0;
                            Inicio.efectivocajeros[i][1]=0;
                            Inicio.efectivocajeros[i][2]=0;
                            existe=true;
                        }else if(Inicio.cantidadcajeros!=1){
                            for (int j = i; j <= Inicio.cantidadcajeros-2; j++) {
                                Inicio.cajeros[j][0]=Inicio.cajeros[j+1][0];
                                Inicio.cajeros[j][1]=Inicio.cajeros[j+1][1];
                                Inicio.cajeros[j][2]=Inicio.cajeros[j+1][2];
                                Inicio.efectivocajeros[j][0]=Inicio.efectivocajeros[j+1][0];
                                Inicio.efectivocajeros[j][1]=Inicio.efectivocajeros[j+1][1];
                                Inicio.efectivocajeros[j][2]=Inicio.efectivocajeros[j+1][2];
                                existe=true;
                            }
                        }else{
                            Inicio.cajeros[i][0]="";
                            Inicio.cajeros[i][1]="";
                            Inicio.cajeros[i][2]="";
                            Inicio.efectivocajeros[i][0]=0;
                            Inicio.efectivocajeros[i][1]=0;
                            Inicio.efectivocajeros[i][2]=0;
                            existe=true;
                        }
                    }
                }
                if(existe==false){
                    JOptionPane.showMessageDialog(null, "No existe el cajero mencionado");
                }else{
                    Inicio.cantidadcajeros=Inicio.cantidadcajeros-1;
                    JOptionPane.showMessageDialog(null, "Cajero eliminado con exito");
                }
                break;
        }
    }
    //Metodo adjunto al aterior, para eliminar los numeros de cuenta que tenga el cliente:
    public void eliminarcuenta(String cuenta){
        for (int i = 0; i < Inicio.cantidadcuentas; i++) {
            if(Integer.toString(Inicio.efectivocuentas[i][0]).equals(cuenta)){
                if((i+1)==Inicio.cantidadcuentas){
                    Inicio.efectivocuentas[i][0]=0;
                    Inicio.efectivocuentas[i][1]=0;
                }else if(Inicio.cantidadcuentas!=1){
                    for (int j = i; j <= Inicio.cantidadcuentas-2; j++) {
                        Inicio.efectivocuentas[j][0]=Inicio.efectivocuentas[j+1][0];
                        Inicio.efectivocuentas[j][1]=Inicio.efectivocuentas[j+1][1];
                    }
                }else{
                    Inicio.efectivocuentas[i][0]=0;
                    Inicio.efectivocuentas[i][1]=0;
                }
            }
        }
    }
    public void eliminarprestaytarjeta(String prestaytarjeta){
        for (int i = 0; i < Inicio.cantidadprestaytarjeta; i++) {
            if(Integer.toString(Inicio.prestaytarjeta[i][0]).equals(prestaytarjeta)){
                if((i+1)==Inicio.cantidadprestaytarjeta){
                    Inicio.prestaytarjeta[i][0]=0;
                    Inicio.prestaytarjeta[i][1]=0;
                    Inicio.prestaytarjeta[i][2]=0;
                    Inicio.prestaytarjeta[i][3]=0;
                }else if(Inicio.cantidadprestaytarjeta!=1){
                    for (int j = i; j <= Inicio.cantidadprestaytarjeta-2; j++) {
                        Inicio.prestaytarjeta[j][0]=Inicio.prestaytarjeta[j+1][0];
                        Inicio.prestaytarjeta[j][1]=Inicio.prestaytarjeta[j+1][1];
                        Inicio.prestaytarjeta[j][2]=Inicio.prestaytarjeta[j+1][2];
                        Inicio.prestaytarjeta[j][3]=Inicio.prestaytarjeta[j+1][3];
                    }
                }else{
                    Inicio.prestaytarjeta[i][0]=0;
                    Inicio.prestaytarjeta[i][1]=0;
                    Inicio.prestaytarjeta[i][2]=0;
                    Inicio.prestaytarjeta[i][3]=0;
                }
            }
        }
    }
    
    public void buscar(){
        DefaultTableModel md=new DefaultTableModel();
        md.setRowCount(0);
        String id=(String) JOptionPane.showInputDialog("Ingrese el ID único del cliente o agencia");
        Boolean existe=false;
        switch(dondeesta){
            case 1:
                String[] data=new String[8];
                String[] encabezado={"Codigo","Nombre","Dirección","Telefono","# Ahorro","# Monetaria","# Prestamo","# T. Credito"};
                md.setColumnIdentifiers(encabezado);
                for (int i = 0; i < Inicio.cantidadclientes; i++) {
                    if(id.equals(Inicio.clientes[i][0])){
                        data[0]=Inicio.clientes[i][0];
                        data[1]=Inicio.clientes[i][1];
                        data[2]=Inicio.clientes[i][2];
                        data[3]=Inicio.clientes[i][3];
                        data[4]=Inicio.clientes[i][4];
                        data[5]=Inicio.clientes[i][5];
                        data[6]=Inicio.clientes[i][6];
                        data[7]=Inicio.clientes[i][7];
                        md.addRow(data);
                        existe=true;
                    }
                }
                if(existe==false){
                    JOptionPane.showMessageDialog(null, "No exite el cliente");
                }
                tblprincipal.setModel(md);
                break;
            case 2:
                String[] data1=new String[8];
                String[] encabezado2={"Nombre","# Agencia","Dirección","Telefono","# Cajas","# Escritorios","Efectivo (Q)"};
                md.setColumnIdentifiers(encabezado2);
                for (int i = 0; i < Inicio.cantidadagenciabancaria; i++) {
                    if(id.equals(Inicio.agenciabancaria[i][1])){
                        data1[0]=Inicio.agenciabancaria[i][0];
                        data1[1]=Inicio.agenciabancaria[i][1];
                        data1[2]=Inicio.agenciabancaria[i][2];
                        data1[3]=Inicio.agenciabancaria[i][3];
                        data1[4]=Inicio.agenciabancaria[i][4];
                        data1[5]=Inicio.agenciabancaria[i][5];
                        data1[6]=Integer.toString(Inicio.efectivoagenciabancaria[i][1]);
                        existe=true;
                        md.addRow(data1);
                    }
                    
                }
                if(existe==false){
                    JOptionPane.showMessageDialog(null, "No exite la agencia bancaria");
                }
                tblprincipal.setModel(md);
                break;
            case 3:
                String[] data3=new String[8];
                String[] encabezado3={"Nombre","# Agencia","Dirección","Telefono","# Cajas","# Escritorios","Efectivo (Q)"};
                md.setColumnIdentifiers(encabezado3);
                for (int i = 0; i < Inicio.cantidadagenciaautobanco; i++) {
                    if(id.equals(Inicio.agenciaautobanco[i][1])){
                        data3[0]=Inicio.agenciaautobanco[i][0];
                        data3[1]=Inicio.agenciaautobanco[i][1];
                        data3[2]=Inicio.agenciaautobanco[i][2];
                        data3[3]=Inicio.agenciaautobanco[i][3];
                        data3[4]=Inicio.agenciaautobanco[i][4];
                        data3[5]=Inicio.agenciaautobanco[i][5];
                        data3[6]=Integer.toString(Inicio.efectivoautobanco[i][1]);
                        md.addRow(data3);
                        existe=true;
                    }
                }
                if(existe==false){
                    JOptionPane.showMessageDialog(null, "No exite el autobanco");
                }
                tblprincipal.setModel(md);
                break;
            case 4:
                String[] data4=new String[8];
                String[] encabezado4={"ID Cajero","Ubicacion","Estado","Efectivo (Q)","# Transacciones"};
                md.setColumnIdentifiers(encabezado4);
                for (int i = 0; i < Inicio.cantidadcajeros; i++) {
                    if(id.equals(Inicio.cajeros[i][0])){
                        data4[0]=Inicio.cajeros[i][0];
                        data4[1]=Inicio.cajeros[i][1];
                        data4[2]=Inicio.cajeros[i][2];
                        data4[3]=Integer.toString(Inicio.efectivocajeros[i][1]);
                        data4[4]=Integer.toString(Inicio.efectivocajeros[i][2]);
                        md.addRow(data4);
                        existe=true;
                    }
                }
                if(existe==false){
                    JOptionPane.showMessageDialog(null, "No exite el cajero");
                }
                tblprincipal.setModel(md);
                break;
        }
    }
}
