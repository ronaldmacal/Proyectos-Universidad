package Proyecto1;
import javax.swing.JOptionPane;

/**
 *
 * @author Ronald Macal
 */
public class COperaciones {
    public COperaciones() {
    }
    public void disminuir(int agenciaseleccionada,String noagencia,int monto){
        switch(agenciaseleccionada){
            case 1:
                for (int i = 0; i < Inicio.cantidadagenciabancaria; i++) {
                    if(noagencia.equals(Inicio.agenciabancaria[i][1])){
                        Inicio.efectivoagenciabancaria[i][1]=Inicio.efectivoagenciabancaria[i][1]-monto;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < Inicio.cantidadagenciaautobanco; i++) {
                    if(noagencia.equals(Inicio.agenciaautobanco[i][1])){
                        Inicio.efectivoautobanco[i][1]=Inicio.efectivoautobanco[i][1]-monto;
                    }
                }
                break;
        }
    }
    public void aumentar(int agenciaseleccionada,String noagencia,int monto){
        switch(agenciaseleccionada){
            case 1:
                for (int i = 0; i < Inicio.cantidadagenciabancaria; i++) {
                    if(noagencia.equals(Inicio.agenciabancaria[i][1])){
                        Inicio.efectivoagenciabancaria[i][1]=Inicio.efectivoagenciabancaria[i][1]+monto;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < Inicio.cantidadagenciaautobanco; i++) {
                    if(noagencia.equals(Inicio.agenciaautobanco[i][1])){
                        Inicio.efectivoautobanco[i][1]=Inicio.efectivoautobanco[i][1]+monto;
                    }
                }
                break;
        }
    }
    public void deposito(int cuentadestino,int monto, int agenciaseleccionada,String noagencia,int tipopago,String idcliente){
        aumentar(agenciaseleccionada,noagencia,monto);
        frmMostrarProcesos mostrar=new frmMostrarProcesos();
        mostrar.inicializar();mostrar.setVisible(true);
        mostrar.registro.append("Depósito: ");mostrar.registro.append(System.getProperty("line.separator"));
        Boolean paso=false;
        try {
            //Sentencia para saber si es agencia bancaria o autobanco
            if(agenciaseleccionada==1){
                
                //Ciclo para encontrar la agencia
                for (int i = 0; i <= Inicio.cantidadagenciabancaria-1; i++) {
                    if(noagencia.equals(Inicio.agenciabancaria[i][1])){
                        //Ciclo para encontrar cuenta
                        for (int j = 0; j <= Inicio.cantidadcuentas-1; j++) {
                            if(Inicio.efectivocuentas[j][0]==cuentadestino){
                                Inicio.efectivocuentas[j][1]=Inicio.efectivocuentas[j][1]+monto;
                                mostrar.registro.append("Nuevo Saldo: "+Inicio.efectivocuentas[j][1]);mostrar.registro.append(System.getProperty("line.separator"));
                                agregartransaccion(idcliente);
                                //Modificar la cantidad en la agencia
                                Inicio.efectivoagenciabancaria[i][1]=Inicio.efectivoagenciabancaria[i][1]+monto;
                                paso=true;
                            }
                        }
                    }
                }
            }else if(agenciaseleccionada==2){
                //Ciclo para encontrar la agencia
                for (int i = 0; i <= Inicio.cantidadagenciaautobanco-1; i++) {
                    if(noagencia.equals(Inicio.agenciaautobanco[i][1])){
                        //Ciclo para encontrar cuenta
                        for (int j = 0; j <= Inicio.cantidadcuentas-1; j++) {
                            if(Inicio.efectivocuentas[j][0]==cuentadestino){
                                Inicio.efectivocuentas[j][1]=Inicio.efectivocuentas[j][1]+monto;
                                mostrar.registro.append("Nuevo Saldo: "+Inicio.efectivocuentas[j][1]);mostrar.registro.append(System.getProperty("line.separator"));
                                agregartransaccion(idcliente);
                                //Modificar la cantidad en la agencia
                                Inicio.efectivoautobanco[i][1]=Inicio.efectivoautobanco[i][1]+monto;
                                paso=true;
                            }
                        }
                    }
                }
            }
            if(paso==true){
                mostrar.registro.append("Monto: "+monto);mostrar.registro.append(System.getProperty("line.separator"));
                mostrar.registro.append("#Cuenta destino: "+cuentadestino);mostrar.registro.append(System.getProperty("line.separator"));
                if(tipopago==0){mostrar.registro.append("Deposito en efectivo");}
                else{mostrar.registro.append("Deposito con cheque");}
                mostrar.registro.append(System.getProperty("line.separator"));
                mostrar.registro.append("Depósito creado con éxito ");mostrar.registro.append(System.getProperty("line.separator"));
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR, DEPOSITO NO HECHO CON EXITO, VOLVER A INTENTAR Y REVISAR LOS DATOS INGRESADOS");
        }
    }
    
    public void retiro(int cuentaretiro,int monto, int agenciaseleccionada,String noagencia,String idcliente,Boolean cajeroactivo){
        disminuir(agenciaseleccionada,noagencia,monto);
        frmMostrarProcesos mostrar=new frmMostrarProcesos();
        mostrar.inicializar();mostrar.setVisible(true);
        mostrar.registro.append("Retiro: ");mostrar.registro.append(System.getProperty("line.separator"));
        Boolean paso=false;
        try {
            //Sentencia para saber si es agencia bancaria o autobanco
            if(agenciaseleccionada==1){
                //Ciclo para encontrar la agencia
                for (int i = 0; i <= Inicio.cantidadagenciabancaria-1; i++) {
                    if(noagencia.equals(Inicio.agenciabancaria[i][1])){
                        //Ciclo para encontrar cuenta
                        for (int j = 0; j <= Inicio.cantidadcuentas-1; j++) {
                            if(Inicio.efectivocuentas[j][0]==cuentaretiro){
                                
                                if((Inicio.efectivocuentas[j][1]-monto)<0 || (Inicio.efectivoagenciabancaria[i][1]-monto)<0){
                                    mostrar.registro.append("La cuenta no tiene el suficiente dinero o la agencia no cuenta con ese efectivo");mostrar.registro.append(System.getProperty("line.separator"));
                                }else{
                                    Inicio.efectivocuentas[j][1]=Inicio.efectivocuentas[j][1]-monto;
                                    mostrar.registro.append("Nuevo Saldo: "+Inicio.efectivocuentas[j][1]);mostrar.registro.append(System.getProperty("line.separator"));
                                    agregartransaccion(idcliente);
                                    //Modificar la cantidad en la agencia
                                    Inicio.efectivoagenciabancaria[i][1]=Inicio.efectivoagenciabancaria[i][1]-monto;
                                    paso=true;
                                }
                            }
                        }
                    }
                }
            }else if(agenciaseleccionada==2){
                //Ciclo para encontrar la agencia
                for (int i = 0; i <= Inicio.cantidadagenciaautobanco-1; i++) {
                    if(noagencia.equals(Inicio.agenciaautobanco[i][1])){
                        //Ciclo para encontrar cuenta
                        for (int j = 0; j <= Inicio.cantidadcuentas-1; j++) {
                            if(Inicio.efectivocuentas[j][0]==cuentaretiro){
                                if((Inicio.efectivocuentas[j][1]-monto)<0 || (Inicio.efectivoautobanco[i][1]-monto)<0){
                                    mostrar.registro.append("La cuenta no tiene el suficiente dinero o la agencia no cuenta con ese efectivo");mostrar.registro.append(System.getProperty("line.separator"));
                                }else{
                                    Inicio.efectivocuentas[j][1]=Inicio.efectivocuentas[j][1]-monto;
                                    mostrar.registro.append("Nuevo Saldo: "+Inicio.efectivocuentas[j][1]);mostrar.registro.append(System.getProperty("line.separator"));
                                    agregartransaccion(idcliente);
                                    //Modificar la cantidad en la agencia
                                    Inicio.efectivoautobanco[i][1]=Inicio.efectivoautobanco[i][1]-monto;
                                    paso=true;
                                }
                            }
                        }
                    }
                }
            }else{
                //Agregar cajero para el retiro
                //Ciclo para encontrar la agencia
                if(cajeroactivo==true){
                for (int i = 0; i <= Inicio.cantidadcajeros-1; i++) {
                    if(noagencia.equals(Inicio.cajeros[i][0])){
                        //Ciclo para encontrar cuenta
                        for (int j = 0; j <= Inicio.cantidadcuentas-1; j++) {
                            if(Inicio.efectivocuentas[j][0]==cuentaretiro){
                                if((Inicio.efectivocuentas[j][1]-monto)<0 || (Inicio.efectivocajeros[i][1]-monto)<0){
                                    mostrar.registro.append("La cuenta no tiene el suficiente dinero o la agencia no cuenta con ese efectivo");mostrar.registro.append(System.getProperty("line.separator"));
                                }else{
                                    Inicio.efectivocuentas[j][1]=Inicio.efectivocuentas[j][1]-monto;
                                    mostrar.registro.append("Nuevo Saldo: "+Inicio.efectivocuentas[j][1]);mostrar.registro.append(System.getProperty("line.separator"));
                                    //Modificar la cantidad en la agencia
                                    Inicio.efectivocajeros[i][1]=Inicio.efectivocajeros[i][1]-monto;
                                    paso=true;
                                    Inicio.efectivocajeros[i][2]=Inicio.efectivocajeros[i][2]+1;
                                    agregartransaccion(idcliente);
                                }
                            }
                        }
                    }
                }
                }else{JOptionPane.showMessageDialog(null, "Cajero: "+noagencia+" está inactivo");}
            }
            if(paso==true){
                mostrar.registro.append("Monto: "+monto);mostrar.registro.append(System.getProperty("line.separator"));
                mostrar.registro.append("#Cuenta retiro: "+cuentaretiro);mostrar.registro.append(System.getProperty("line.separator"));
                mostrar.registro.append("Retiro creado con éxito");mostrar.registro.append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR, RETIRO NO HECHO CON EXITO, VOLVER A INTENTAR Y REVISAR LOS DATOS INGRESADOS");
        }
    }
    
    public void pagoservicios(String noagencia,int agenciaseleccionada,int monto,int tiposervicio,String idcliente,int tipopago){
        aumentar(agenciaseleccionada, noagencia, monto);
        frmMostrarProcesos mostrar=new frmMostrarProcesos();
        mostrar.inicializar();mostrar.setVisible(true);
        mostrar.registro.append("Pago de servicios: ");mostrar.registro.append(System.getProperty("line.separator"));
        Boolean paso=false;
        try {
            if(agenciaseleccionada==4){
                int cuentaorigen=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cuenta de origen del dinero"));
                for (int i = 0; i < Inicio.cantidadcuentas; i++) {
                if(Inicio.efectivocuentas[i][0]==cuentaorigen){
                    if((Inicio.efectivocuentas[i][1]-monto)<0){
                        mostrar.registro.append("No hay dinero suficiente.");mostrar.registro.append(System.getProperty("line.separator"));
                    }else{
                        mostrar.registro.append("Call-center BDE ");mostrar.registro.append(System.getProperty("line.separator"));
                        Inicio.efectivocuentas[i][1]=Inicio.efectivocuentas[i][1]-monto;
                        Inicio.efectivocallcenter=Inicio.efectivocallcenter+monto;
                        mostrar.registro.append("Nuevo Saldo: "+Inicio.efectivocuentas[i][1]);mostrar.registro.append(System.getProperty("line.separator"));
                        agregartransaccion(idcliente);
                        paso=true;    
                    }
                }
                }
            }else{
                if(agenciaseleccionada==1){
                //Ciclo para encontrar la agencia
                int cuentaorigen=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cuenta de origen del dinero"));
                if(tipopago==0){
                    for (int i = 0; i <= Inicio.cantidadagenciabancaria-1; i++) {
                    if(noagencia.equals(Inicio.agenciabancaria[i][1])){
                        //Ciclo para encontrar cuenta
                        for (int j = 0; j <= Inicio.cantidadcuentas-1; j++) {
                            if(Inicio.efectivocuentas[j][0]==cuentaorigen){
                                if((Inicio.efectivocuentas[j][1]-monto)<0){
                                    mostrar.registro.append("La cuenta no tiene el suficiente dinero o la agencia no cuenta con ese efectivo");mostrar.registro.append(System.getProperty("line.separator"));
                                }else{
                                    Inicio.efectivocuentas[j][1]=Inicio.efectivocuentas[j][1]-monto;
                                    mostrar.registro.append("Nuevo Saldo: "+Inicio.efectivocuentas[j][1]);mostrar.registro.append(System.getProperty("line.separator"));
                                    //Modificar la cantidad en la agencia
                                    Inicio.efectivoagenciabancaria[i][1]=Inicio.efectivoagenciabancaria[i][1]+monto;
                                    agregartransaccion(idcliente);
                                    paso=true;
                                }
                            }
                        }
                    }
                    }
                }
                
            }else if(agenciaseleccionada==2){
                //Ciclo para encontrar la agencia
                for (int i = 0; i <= Inicio.cantidadagenciaautobanco-1; i++) {
                    if(noagencia.equals(Inicio.agenciaautobanco[i][1])){
                        int cuentaorigen=Integer.parseInt(JOptionPane.showInputDialog("Ingresar cuenta de origen"));
                        //Ciclo para encontrar cuenta
                        for (int j = 0; j <= Inicio.cantidadcuentas-1; j++) {
                            if(Inicio.efectivocuentas[j][0]==cuentaorigen){
                                if((Inicio.efectivocuentas[j][1]-monto)<0){
                                    mostrar.registro.append("La cuenta no tiene el suficiente dinero o la agencia no cuenta con ese efectivo");mostrar.registro.append(System.getProperty("line.separator"));
                                }else{
                                    Inicio.efectivocuentas[j][1]=Inicio.efectivocuentas[j][1]-monto;
                                    mostrar.registro.append("Nuevo Saldo: "+Inicio.efectivocuentas[j][1]);mostrar.registro.append(System.getProperty("line.separator"));
                                    //Modificar la cantidad en la agencia
                                    Inicio.efectivoautobanco[i][1]=Inicio.efectivoautobanco[i][1]+monto;
                                    agregartransaccion(idcliente);
                                    paso=true;
                                }
                            }
                        }
                    }
                }
            }
                
            }
            if(paso==true){
                mostrar.registro.append("Monto: "+monto);mostrar.registro.append(System.getProperty("line.separator"));
                switch(tiposervicio){
                    case 0:
                        mostrar.registro.append("Tipo servicio: Luz");mostrar.registro.append(System.getProperty("line.separator"));
                        break;
                    case 1:
                        mostrar.registro.append("Tipo servicio: Agua");mostrar.registro.append(System.getProperty("line.separator"));
                        break;
                    case 2:
                        mostrar.registro.append("Tipo servicio: Telefono");mostrar.registro.append(System.getProperty("line.separator"));
                        break;
                }
                mostrar.registro.append("Pago de servicio realizado con éxito");mostrar.registro.append(System.getProperty("line.separator"));
            }
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR, NO SE PUDO REALIZAR EL PAGO DE SERVICIOS");
        }
        
        
        
    }
    
    public void pagotarjeta(String notarjeta,int monto,int tipopago,int agenciaseleccionada,String noagencia,String idcliente){
        aumentar(agenciaseleccionada, noagencia, monto);
        frmMostrarProcesos mostrar=new frmMostrarProcesos();
        mostrar.inicializar();mostrar.setVisible(true);
        mostrar.registro.append("Abono a Tarjeta de Crédito: ");mostrar.registro.append(System.getProperty("line.separator"));
        Boolean paso=false;
        short posicion=0;
        //Sentencia para saber si es agencia bancaria o autobanco
            if(agenciaseleccionada==1){
                 //Ciclo para encontrar la agencia
                for (int i = 0; i <= Inicio.cantidadagenciabancaria-1; i++) {
                    if(noagencia.equals(Inicio.agenciabancaria[i][1])){
                        for (int j = 0; j < Inicio.cantidadprestaytarjeta; j++) {
                            if(Integer.parseInt(notarjeta)==Inicio.prestaytarjeta[j][0]){
                                Inicio.prestaytarjeta[j][2]=Inicio.prestaytarjeta[j][2]+monto;
                                Inicio.prestaytarjeta[j][1]=Inicio.prestaytarjeta[j][1]-monto;
                                Inicio.efectivoagenciabancaria[i][1]=Inicio.efectivoagenciabancaria[i][1]+monto;
                                paso=true;
                                agregartransaccion(idcliente);
                                posicion=Short.parseShort(Integer.toString(j));
                            }
                        }
                    }
                }
            }else{
                //Ciclo para encontrar la agencia
                for (int i = 0; i <= Inicio.cantidadagenciaautobanco-1; i++) {
                    if(noagencia.equals(Inicio.agenciaautobanco[i][1])){
                        for (int j = 0; j < Inicio.cantidadprestaytarjeta; j++) {
                            if(Integer.parseInt(notarjeta)==Inicio.prestaytarjeta[j][0]){
                                Inicio.prestaytarjeta[j][2]=Inicio.prestaytarjeta[j][2]+monto;
                                Inicio.prestaytarjeta[j][1]=Inicio.prestaytarjeta[j][1]-monto;
                                Inicio.efectivoautobanco[i][1]=Inicio.efectivoautobanco[i][1]+monto;
                                paso=true;
                                agregartransaccion(idcliente);
                                posicion=Short.parseShort(Integer.toString(j));
                            }
                        }
                    }
                }
            }
            if(paso==true){
               mostrar.registro.append("# Tarjeta: "+notarjeta);mostrar.registro.append(System.getProperty("line.separator"));
               mostrar.registro.append("Abono: "+monto);mostrar.registro.append(System.getProperty("line.separator"));
               mostrar.registro.append("Limite de credito: "+Inicio.prestaytarjeta[posicion][3]);mostrar.registro.append(System.getProperty("line.separator"));
            }      
    }
    
    public void prestamo(String noprestamo,int monto,int tipopago,int agenciaseleccionada,String noagencia, String idcliente){
        aumentar(agenciaseleccionada, noagencia, monto);
        frmMostrarProcesos mostrar=new frmMostrarProcesos();
        mostrar.inicializar();mostrar.setVisible(true);
        mostrar.registro.append("Abono a Préstamo: ");mostrar.registro.append(System.getProperty("line.separator"));
        Boolean paso=false;
        //Sentencia para saber si es agencia bancaria o autobanco
        short posicion=0;
            if(agenciaseleccionada==1){
                 //Ciclo para encontrar la agencia
                for (int i = 0; i <= Inicio.cantidadagenciabancaria-1; i++) {
                    if(noagencia.equals(Inicio.agenciabancaria[i][1])){
                        for (int j = 0; j < Inicio.cantidadprestaytarjeta; j++) {
                            if(Integer.parseInt(noprestamo)==Inicio.prestaytarjeta[j][0]){
                                if(Inicio.prestaytarjeta[j][2]==Inicio.prestaytarjeta[j][1]){
                                    mostrar.registro.append("El préstamo ya está pagado");mostrar.registro.append(System.getProperty("line.separator"));
                                }else{
                                    Inicio.prestaytarjeta[j][2]=Inicio.prestaytarjeta[j][2]+monto;
                                    Inicio.efectivoagenciabancaria[i][1]=Inicio.efectivoagenciabancaria[i][1]+monto;
                                    paso=true;
                                    agregartransaccion(idcliente);
                                    posicion=Short.parseShort(Integer.toString(j));
                                }
                            }
                        }
                    }
                }
            }else{
                //Ciclo para encontrar la agencia
                for (int i = 0; i <= Inicio.cantidadagenciaautobanco-1; i++) {
                    if(noagencia.equals(Inicio.agenciaautobanco[i][1])){
                        for (int j = 0; j < Inicio.cantidadprestaytarjeta; j++) {
                            if(Integer.parseInt(noprestamo)==Inicio.prestaytarjeta[j][0]){
                                if(Inicio.prestaytarjeta[j][2]==Inicio.prestaytarjeta[j][1]){
                                    mostrar.registro.append("El préstamo ya está pagado");mostrar.registro.append(System.getProperty("line.separator"));
                                }else{
                                    Inicio.prestaytarjeta[j][2]=Inicio.prestaytarjeta[j][2]+monto;
                                    Inicio.efectivoautobanco[i][1]=Inicio.efectivoautobanco[i][1]+monto;
                                    paso=true;
                                    agregartransaccion(idcliente);
                                    posicion=Short.parseShort(Integer.toString(j));
                                }
                            }
                        }
                    }
                }
            }
            if(paso==true){
                mostrar.registro.append("# Prestamo: "+noprestamo);mostrar.registro.append(System.getProperty("line.separator"));
                mostrar.registro.append("Abono: "+monto);mostrar.registro.append(System.getProperty("line.separator"));
                mostrar.registro.append("Lo que debe: "+(Inicio.prestaytarjeta[posicion][1]-Inicio.prestaytarjeta[posicion][2]));mostrar.registro.append(System.getProperty("line.separator"));
            }
    }
    
    public void consultasaldo(String idcliente,Boolean cajeroactivo){
        frmMostrarProcesos mostrar=new frmMostrarProcesos();
        mostrar.inicializar();mostrar.setVisible(true);
        mostrar.registro.append("Saldo actual del cliente: ");mostrar.registro.append(System.getProperty("line.separator"));
        if(cajeroactivo==true){
        int cuenta=Integer.parseInt(JOptionPane.showInputDialog("Cuenta para mostrar saldo"));
        for (int i = 0; i < Inicio.cantidadcuentas-1; i++) {
            if(cuenta==Inicio.efectivocuentas[i][0]){
                mostrar.registro.append("Saldo actual de la cuenta es: "+Inicio.efectivocuentas[i][1]);mostrar.registro.append(System.getProperty("line.separator"));
            }
        }
        }else{JOptionPane.showMessageDialog(null, "Cajero está inactivo");}
    }

    private void agregartransaccion(String idcliente) {
        for (int i = 0; i < Inicio.cantidadclientes; i++) {
            if(Inicio.clientes[i][0].equals(idcliente)){
                Inicio.clientes[i][8]=Integer.toString(Integer.parseInt(Inicio.clientes[i][8])+1);
            }
        }
    }
    
}
