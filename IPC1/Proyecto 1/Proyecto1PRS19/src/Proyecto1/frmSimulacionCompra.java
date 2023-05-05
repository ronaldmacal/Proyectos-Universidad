package Proyecto1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald Macal
 */
public class frmSimulacionCompra extends JFrame implements ActionListener {
    JPanel panel;
    JScrollPane scroll;
    JTable tbltarjeta;
    JButton btnmostrar;
    JButton btnsimulacion;
    
    public frmSimulacionCompra(){
        panel=new JPanel();
        scroll=new JScrollPane();
        tbltarjeta=new JTable();
        scroll.setViewportView(tbltarjeta);
        btnmostrar=new JButton("Mostrar");
        btnsimulacion=new JButton("Simulacion");
    }
    
    public void inicializar(){
        //Propiedades básicas del formulario
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Simulador de compras con Tarjeta (BDE)");
        setSize(500, 250);
        setLocation(20,30);
        
        //Inicio del trabajo en el Panel
        panel.setSize(500, 250);
        panel.setLayout(null);
        
        btnmostrar.setBounds(20, 140, 110, 20);
        panel.add(btnmostrar);
        btnmostrar.addActionListener(this);
        
        btnsimulacion.setBounds(145,140,110,20);
        panel.add(btnsimulacion);
        btnsimulacion.addActionListener(this);
        
        scroll.setBounds(20, 20, 350, 100);
        panel.add(scroll);
        
        getContentPane().add(panel);
    }
    
    public void mostrardatostarjetas(){
        DefaultTableModel md=new DefaultTableModel();
        String[] datos=new String[3];
        String[] encabezado={"ID Cliente","Nombre","# Tarjeta"};
        md.setColumnIdentifiers(encabezado);
        md.setRowCount(0);
        tbltarjeta.setModel(md);
        for (int i = 0; i < Inicio.cantidadclientes; i++) {
            if((Inicio.clientes[i][7])!="0"){
                datos[0]=Inicio.clientes[i][0];
                datos[1]=Inicio.clientes[i][1];
                datos[2]=Inicio.clientes[i][7];
                md.addRow(datos);
            }
        }
    }
    public void simularcompra(){
        String[] opciones={"Ropa y accesorios","Alimentos","Gasolina","Gastos medicos","Otros"};
        JOptionPane.showInputDialog(null, "Seleccione una transacción: ", "Agencias BDE", JOptionPane.DEFAULT_OPTION,null, opciones, opciones[0]);
        String notarjeta=JOptionPane.showInputDialog("Ingrese el número de tarjeta");
        int costo=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el costo de la compra"));
        int disponiblegasto=0;
        int posicion=0;
        //Encontrar el dinero disponible para gastar
        for (int i = 0; i < Inicio.cantidadprestaytarjeta; i++) {
            if(notarjeta.equals(Integer.toString(Inicio.prestaytarjeta[i][0]))){
                disponiblegasto=Inicio.prestaytarjeta[i][3]-Inicio.prestaytarjeta[i][1];
                posicion=i;
            }
        }
        if(costo>disponiblegasto){
            JOptionPane.showMessageDialog(null, "Limite de cuenta excedido en esta compra o error en los datos proporcionados. No se puede realizar");
        }else{
            Inicio.prestaytarjeta[posicion][1]=Inicio.prestaytarjeta[posicion][1]+costo;
            JOptionPane.showMessageDialog(null, "El nuevo monto a deber es: "+Inicio.prestaytarjeta[posicion][1]);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnmostrar)){
            mostrardatostarjetas();
        }
        if(e.getSource().equals(btnsimulacion)){
            simularcompra();
        }
    }
    
}
