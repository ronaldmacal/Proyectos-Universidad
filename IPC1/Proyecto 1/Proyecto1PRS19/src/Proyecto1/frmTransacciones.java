package Proyecto1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Ronald Macal
 */
public class frmTransacciones extends JFrame implements ActionListener {
    JLabel lbtitulo;
    JLabel lb1;
    JTextField txtcliente;
    JButton btnregresar;
    JButton btntransaccion;
    JButton btnsolicitud;
    JButton btnnuevacuenta;
    JButton btnsimulacion;
    JPanel panel;
    JButton btnmostrar;
    JTable tblcliente;
    JScrollPane scrollPane;
    
    public frmTransacciones(){
        tblcliente=new JTable(); 
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(tblcliente);
             
        panel=new JPanel();
        txtcliente=new JTextField();
        btnregresar=new JButton("Regresar");
        btntransaccion=new JButton("Transacción");
        btnsolicitud=new JButton("Prestamo o Tarjeta");
        btnnuevacuenta=new JButton("Nueva cuenta");
        btnsimulacion=new JButton("Simulacion Tarjeta");
        btnmostrar=new JButton("Mostrar");
        lbtitulo=new JLabel("MÓDULO DE TRANSACCIONES");
        lb1=new JLabel("Ingresar cliente para hacer la transacción: ");
    }
    
    public void inicializar(){
        //Propiedades básicas del formulario
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Módulo de Transacciones (BDE)");
        setSize(850, 450);
        setLocation(20,30);
        
        //Inicio del trabajo en el Panel
        panel.setSize(850, 450);
        panel.setLayout(null);
        lbtitulo.setBounds(5, 10, 200, 15);
        panel.add(lbtitulo);
        
        scrollPane.setBounds(20, 75, 650, 150);
        panel.add(scrollPane);
        
        btnmostrar.setBounds(20, 40, 100, 20);
        panel.add(btnmostrar);
        btnmostrar.addActionListener(this);
        
        btnsolicitud.setBounds(140,300,150,30);
        panel.add(btnsolicitud);
        btnsolicitud.addActionListener(this);
        
        btnnuevacuenta.setBounds(300,300,130,30);
        panel.add(btnnuevacuenta);
        btnnuevacuenta.addActionListener(this);
        
        lb1.setBounds(20, 220, 300, 50);
        panel.add(lb1);
        
        txtcliente.setBounds(175,265,90,25);
        panel.add(txtcliente);
        
        btnregresar.setBounds(650, 20, 90, 20);
        btnregresar.addActionListener(this);
        panel.add(btnregresar);
        
        btnsimulacion.setBounds(600, 45, 200, 20);
        panel.add(btnsimulacion);
        btnsimulacion.addActionListener(this);
        
        btntransaccion.setBounds(20,300,110,30);
        panel.add(btntransaccion);
        btntransaccion.addActionListener(this);
        
        getContentPane().add(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnmostrar)){
            DefaultTableModel md=new DefaultTableModel();
               
            String[][] datos={};
            String[] encabezado={"Codigo","Nombre","Telefono","# Ahorro","# Monetaria","# Prestamo","# T. Credito"};
            md.setColumnIdentifiers(encabezado);
            md.setRowCount(0);
            Object[] clientes=new Object[7];
            tblcliente.setModel(md);
            for(int j=0;j<Inicio.cantidadclientes;j++){
                clientes[0]=Inicio.clientes[j][0];
                clientes[1]=Inicio.clientes[j][1];
                clientes[2]=Inicio.clientes[j][3];
                clientes[3]=Inicio.clientes[j][4];
                clientes[4]=Inicio.clientes[j][5];
                clientes[5]=Inicio.clientes[j][6];
                clientes[6]=Inicio.clientes[j][7];
                md.addRow(clientes);
            }
            
        }
        if(e.getSource().equals(btnregresar)){
            Inicio.inicio();
            this.dispose();
        }
        if(e.getSource().equals(btntransaccion)){
            String idcliente;
            idcliente=txtcliente.getText();
            hacertransaccion(idcliente);
        }
        if(e.getSource().equals(btnnuevacuenta)){
            crearnuevacuenta();
        }
        if(e.getSource().equals(btnsolicitud)){
            String idcliente=txtcliente.getText();
            solicitarprestaytarjeta(idcliente);
        }
        if(e.getSource().equals(btnsimulacion)){
            frmSimulacionCompra simu=new frmSimulacionCompra();
            simu.inicializar();
            simu.setVisible(true);
        }
    
    }
    
    public void crearnuevacuenta(){
        Boolean paso=false;Boolean ahorro=false;Boolean monetaria=false;int posicion=0;
        String idcliente=txtcliente.getText();
        
        String[] opciones={"Ahorro","Monetaria"};
        String seleccion=(String) JOptionPane.showInputDialog(null, "Seleccione un tipo de cuenta: ", "Crear nueva cuenta", JOptionPane.DEFAULT_OPTION,null, opciones, opciones[0]);
        //Buscar primero si el cliente existe
        
        for (int i = 0; i < Inicio.cantidadclientes; i++) {
            if(idcliente.equals(Inicio.clientes[i][0])){
                posicion=i;
                paso=true;
               if(Inicio.clientes[i][4]!="0" && Inicio.clientes[i][5]!="0"){
                   JOptionPane.showMessageDialog(null, "El cliente ya tiene cuenta de ahorro y monetaria");
                   paso=false;
               }else if(Inicio.clientes[i][4]!="0"){
                   ahorro=true;
               }else if(Inicio.clientes[i][5]!="0"){
                   monetaria=true;
               }
            }
        }
        
        if(paso==true){
            switch(seleccion){
                case "Ahorro":
                    if(ahorro==true){
                        JOptionPane.showMessageDialog(null, "El cliente ya tiene cuenta de ahorro");
                    }else{
                        crearcuenta(4,posicion);
                    }
                    break;
                case "Monetaria":
                    if(monetaria==true){
                        JOptionPane.showMessageDialog(null, "El cliente ya tiene cuenta monetaria");
                    }else{
                        crearcuenta(5,posicion);
                    }
                    break;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Revise y vuelva intentarlo. Sugerencia:Ingrese un ID cliente válido");
        }
        
    }
    //Submetodo de crear nueva cuenta
    public void crearcuenta(int tipo,int posicion){
        int nocuenta=0;
        int cantidad=0;
        nocuenta=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cuenta nueva"));
        cantidad=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad inicial para su cuenta nueva"));
        
        //Llenar los valores de una nueva cuenta
        Inicio.clientes[posicion][tipo]=Integer.toString(nocuenta);
        Inicio.efectivocuentas[Inicio.cantidadcuentas][0]=nocuenta;
        Inicio.efectivocuentas[Inicio.cantidadcuentas][1]=cantidad;
        JOptionPane.showMessageDialog(null, "Cuenta creada con éxito");
        Inicio.cantidadcuentas=Inicio.cantidadcuentas+1;
    }
    
    public void solicitarprestaytarjeta(String idcliente){
        int noprestaytarjeta=0;
        int monto=0;
        int limite=0;
        
        String[] opciones={"Préstamo","Tarjeta Crédito"};
        String seleccion=(String) JOptionPane.showInputDialog(null, "Seleccione una solicitud: ", "Solicitar un préstamo", JOptionPane.DEFAULT_OPTION,null, opciones, opciones[0]);
        switch(seleccion){
            case "Préstamo":
                noprestaytarjeta=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de prestamo"));
                monto=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto del prestamo"));
                Inicio.solprestaytarjeta[Inicio.solcantidadprestaytarjeta][0]=noprestaytarjeta;
                Inicio.solprestaytarjeta[Inicio.solcantidadprestaytarjeta][1]=monto;
                Inicio.solprestaytarjeta[Inicio.solcantidadprestaytarjeta][2]=0;
                Inicio.solprestaytarjeta[Inicio.solcantidadprestaytarjeta][3]=0;
                Inicio.solprestaytarjeta[Inicio.solcantidadprestaytarjeta][4]=Integer.parseInt(idcliente);
                JOptionPane.showMessageDialog(null, "Solicitud de préstamo enviada");
                Inicio.solcantidadprestaytarjeta=Inicio.solcantidadprestaytarjeta+1;
                break;
            case "Tarjeta Crédito":
                noprestaytarjeta=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de tarjeta"));
                limite=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el limite de la tarjeta"));
                Inicio.solprestaytarjeta[Inicio.solcantidadprestaytarjeta][0]=noprestaytarjeta;
                Inicio.solprestaytarjeta[Inicio.solcantidadprestaytarjeta][1]=0;
                Inicio.solprestaytarjeta[Inicio.solcantidadprestaytarjeta][2]=0;
                Inicio.solprestaytarjeta[Inicio.solcantidadprestaytarjeta][3]=limite;
                Inicio.solprestaytarjeta[Inicio.solcantidadprestaytarjeta][4]=Integer.parseInt(idcliente);
                Inicio.solcantidadprestaytarjeta=Inicio.solcantidadprestaytarjeta+1;
                JOptionPane.showMessageDialog(null, "Solicitud de tarjeta enviada");
                break;
        }
    }
    
    public void hacertransaccion(String idcliente){
        String agencias[]={"Agencia Bancaria","Agencia con Autobanco", "Cajero","Call-Center"};
        String resp = (String) JOptionPane.showInputDialog(null, "Seleccione la agencia", "Agencias BDE", JOptionPane.DEFAULT_OPTION,null, agencias, agencias[0]);
        
        String seleccionada="";String noagencia="";
        
        int agenciaseleccionada=0;
        switch(resp){
            case "Agencia Bancaria":
                String opciones1[]={"Depósito","Retiro","C. Cheque","P. Servicios","T. Crédito","Préstamo","Consulta Saldo"};
                seleccionada= (String) JOptionPane.showInputDialog(null, "Seleccione una transacción: ", "Agencias BDE", JOptionPane.DEFAULT_OPTION,null, opciones1, opciones1[0]);
                agenciaseleccionada=1;
                break;
            case "Agencia con Autobanco":
                String opciones2[]={"Depósito","Retiro","C. Cheque","P. Servicios","T. Crédito","Préstamo","Consulta Saldo"};
                seleccionada= (String) JOptionPane.showInputDialog(null, "Seleccione una transacción: ", "Agencias BDE", JOptionPane.DEFAULT_OPTION,null, opciones2, opciones2[0]);
                agenciaseleccionada=2;
                break;
            case "Cajero":
                String opciones3[]={"Retiro","Consulta Saldo"};
                seleccionada= (String) JOptionPane.showInputDialog(null, "Seleccione una transacción: ", "Agencias BDE", JOptionPane.DEFAULT_OPTION,null, opciones3, opciones3[0]);
                agenciaseleccionada=3;
                break;
            case "Call-Center":
                String opciones4[]={"P. Servicios","T. Crédito","Préstamo","Consulta Saldo"};
                seleccionada= (String) JOptionPane.showInputDialog(null, "Seleccione una transacción: ", "Agencias BDE", JOptionPane.DEFAULT_OPTION,null, opciones4, opciones4[0]);
                agenciaseleccionada=4;
                noagencia="0";
                break;
        }
        //Ingreso de numero agencia o cajero
        Boolean cajeroactivo=true;
        if(agenciaseleccionada!=4){
            if(agenciaseleccionada==3){
                noagencia=(String) JOptionPane.showInputDialog("Ingrese el número de cajero");
                for (int i = 0; i < Inicio.cantidadcajeros; i++) {
                    if(noagencia.equals(Inicio.cajeros[i][0])){
                        
                        if((Inicio.cajeros[i][2]).equals("Inactivo")){
                            
                            cajeroactivo=false;
                        }
                    }
                }
            }else{
                noagencia=(String) JOptionPane.showInputDialog("Ingrese el número de agencia");
            }
            
        }
        
        int monto=0;
        int ncuentamonetaria=0;
        String[] opciones={"Efectivo","Cheque"};
        int tipopago;
        COperaciones op=new COperaciones();
        switch(seleccionada){
            case "Depósito":
                ncuentamonetaria=Integer.parseInt((String) JOptionPane.showInputDialog("Ingrese el numero de cuenta monetaria o de ahorro"));
                tipopago=JOptionPane.showOptionDialog(null, "Ingrese su tipo de pago:", "Tipo Pago", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                monto=Integer.parseInt((String) JOptionPane.showInputDialog("Ingrese el monto"));
                op.deposito(ncuentamonetaria,monto,agenciaseleccionada,noagencia,tipopago,idcliente);
                break;
            case "Retiro":
                monto=Integer.parseInt((String) JOptionPane.showInputDialog("Ingrese el monto"));
                ncuentamonetaria=Integer.parseInt((String) JOptionPane.showInputDialog("Ingrese el numero de cuenta monetaria o de ahorro"));
                op.retiro(ncuentamonetaria, monto, agenciaseleccionada,noagencia,idcliente,cajeroactivo);
                break;
            case "P. Servicios":
                monto=Integer.parseInt((String) JOptionPane.showInputDialog("Ingrese el monto"));
                String[] opciones1={"Luz","Agua","Telefono"};
                int tiposervicio=JOptionPane.showOptionDialog(null, "Ingrese su tipo de servicio a pagar:", "Pago de servicios", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones1, opciones1[0]);
                tipopago=JOptionPane.showOptionDialog(null, "Ingrese su tipo de pago:", "Tipo Pago", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                op.pagoservicios(noagencia,agenciaseleccionada,monto,tiposervicio,idcliente,tipopago);
                break;
            case "C. Cheque":
                int cuentaretiro=Integer.parseInt((String) JOptionPane.showInputDialog("Ingrese el monto"));
                monto=Integer.parseInt((String) JOptionPane.showInputDialog("Ingrese la cuenta a la que esta afiliada el cheque"));
                op.retiro(cuentaretiro, monto,  agenciaseleccionada, noagencia, idcliente,cajeroactivo);
                int continuar = JOptionPane.showConfirmDialog(null, "Desea depositar el dinero en otra cuenta");
                if(continuar==0){
                    String nocuentabeneficiada=(String)JOptionPane.showInputDialog("Ingrese la cuenta de destino del cheque");
                    int tipopago1=0;
                    op.deposito(Integer.parseInt(nocuentabeneficiada), monto, agenciaseleccionada, noagencia, tipopago1, idcliente);
                }
                break;
            
            case "T. Crédito":
                String notarjeta=JOptionPane.showInputDialog("Ingrese el numero de tarjeta para hacer un abono");
                monto=Integer.parseInt((String) JOptionPane.showInputDialog("Ingrese el monto"));
                tipopago=JOptionPane.showOptionDialog(null, "Ingrese su tipo de pago:", "T. Crédito", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                op.pagotarjeta(notarjeta, monto, tipopago,agenciaseleccionada,noagencia,idcliente);
                break;
            case "Préstamo":
                String noprestamo=JOptionPane.showInputDialog("Ingrese numero de prestamo para abonar");
                monto=Integer.parseInt((String) JOptionPane.showInputDialog("Ingrese el monto"));
                tipopago=JOptionPane.showOptionDialog(null, "Ingrese su tipo de pago:", "Prestamo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                op.prestamo(noprestamo, monto, tipopago,agenciaseleccionada,noagencia,idcliente);
                break;
            case "Consulta Saldo":
                op.consultasaldo(idcliente,cajeroactivo);
                break;
        }
        int continuar = JOptionPane.showConfirmDialog(null, "Desea hacer otra transaccion con este cliente");  
        if(continuar==0){
            hacertransaccion(idcliente);
        }
    }
    
}
