package Proyecto1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald Macal
 */
public class frmEmpleados extends JFrame implements ActionListener{
    JPanel panel;
    JLabel titulo;
    JButton btnmostrar;//Botones
    JButton btncrear;
    JButton btneditar;
    JButton btneliminar;
    JButton btnbuscar;
    JButton btnasignar;
    //Tabla principal
    JScrollPane scroll;
    JTable tblprincipal;
    
    public frmEmpleados(){
        titulo=new JLabel("EMPLEADOS");
        panel=new JPanel();
        btnmostrar=new JButton("Mostrar");
        btncrear=new JButton("Crear");
        btneditar=new JButton("Editar");
        btneliminar=new JButton("Eliminar");
        btnbuscar=new JButton("Buscar");
        btnasignar=new JButton("Asignar");
        //Tabla principal
        tblprincipal=new JTable();
        scroll=new JScrollPane();
        scroll.setViewportView(tblprincipal);
    }
    
    public void inicializar(){
        //Propiedades básicas del formulario
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Módulo de empleados (BDE)");
        setSize(700, 360);
        setLocation(20,30);
        
        //Inicio del trabajo en el Panel
        panel.setSize(700, 360);
        panel.setLayout(null);
        titulo.setBounds(20,20,200,20);
        panel.add(titulo);
        
        btnmostrar.setBounds(20,45,125,20);
        panel.add(btnmostrar);
        btnmostrar.addActionListener(this);
        
        //Agregar tabla
        scroll.setBounds(20,75,600,200);
        panel.add(scroll);
        
        btncrear.setBounds(40,285,100,30);
        panel.add(btncrear);
        btncrear.addActionListener(this);
        btneditar.setBounds(150,285,100,30);
        panel.add(btneditar);
        btneditar.addActionListener(this);
        btneliminar.setBounds(260,285,100,30);
        panel.add(btneliminar);
        btneliminar.addActionListener(this);
        btnbuscar.setBounds(370,285,100,30);
        panel.add(btnbuscar);
        btnbuscar.addActionListener(this);
        btnasignar.setBounds(480,285,100,30);
        panel.add(btnasignar);
        btnasignar.addActionListener(this);
        
        getContentPane().add(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnmostrar)){
            DefaultTableModel md=new DefaultTableModel();
            String[] encabezado={"Nombre","Agencia afiliada","ID Empleado"};
            md.setColumnIdentifiers(encabezado);
            md.setRowCount(0);
            String[] empleados=new String[3];
            for (int i = 0; i < Inicio.cantidadempleados; i++) {
                empleados[0]=Inicio.empleados[i][0];
                empleados[1]=Inicio.empleados[i][1];
                empleados[2]=Inicio.empleados[i][2];
                md.addRow(empleados);
            }
            tblprincipal.setModel(md);
        }
        //Funciones del formulario
        if(e.getSource().equals(btncrear)){crear();}
        if(e.getSource().equals(btneditar)){editar();}
        if(e.getSource().equals(btneliminar)){eliminar();}
        if(e.getSource().equals(btnbuscar)){buscar();}
        if(e.getSource().equals(btnasignar)){asignar();}
    }
    
    public void crear(){
        try {
            Inicio.empleados[Inicio.cantidadempleados][0]=JOptionPane.showInputDialog("Ingrese el nombre del empleado: ");
            Inicio.empleados[Inicio.cantidadempleados][1]=JOptionPane.showInputDialog("Ingrese la agencia donde trabajará: ");
            Inicio.empleados[Inicio.cantidadempleados][2]=JOptionPane.showInputDialog("Ingrese ID Empleado: ");
            Inicio.cantidadempleados=Inicio.cantidadempleados+1;
            JOptionPane.showMessageDialog(null, "Nueva empleado guardado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, datos inválidos");
        }
    }
    
    public void editar(){
        String id=JOptionPane.showInputDialog("Ingrese el ID del empleado. PD(Los valores numericos no pueden cambiarse)");
        try {
            for (int i = 0; i < Inicio.cantidadempleados; i++) {
                if(id.equals(Inicio.empleados[i][2])){
                    Inicio.empleados[i][0]=JOptionPane.showInputDialog("Nombre acutal: "+Inicio.empleados[i][0]+". Ingrese el nuevo nombre del cliente");
                    Inicio.empleados[i][1]=JOptionPane.showInputDialog("Trabaja en: "+Inicio.empleados[i][1]+". Ingrese el nuevo lugar de trabajo:");
                    Inicio.empleados[i][2]=JOptionPane.showInputDialog("ID Actual: "+Inicio.empleados[i][2]+". Ingrese el nuevo ID del empleado");
                    JOptionPane.showMessageDialog(null, "Valores cambiados correctamente");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, datos inválidos");
        }
    
    }
    public void eliminar(){
        Boolean existe=false;
        String id=JOptionPane.showInputDialog("Ingrese el ID del empleado a eliminar: ");
        for (int i = 0; i < Inicio.cantidadempleados; i++) {
            if((Inicio.empleados[i][2]).equals(id)){
                if((i+1)==Inicio.cantidadempleados){
                    Inicio.empleados[i][0]="";
                    Inicio.empleados[i][1]="";
                    Inicio.empleados[i][2]="";

                    existe=true;
                }else if(Inicio.cantidadempleados!=1){
                    for (int j = i; j <= Inicio.cantidadempleados-2; j++) {
                        Inicio.empleados[j][0]=Inicio.empleados[j+1][0];
                        Inicio.empleados[j][1]=Inicio.empleados[j+1][1];
                        Inicio.empleados[j][2]=Inicio.empleados[j+1][2];

                        existe=true;
                    }
                }else{
                    Inicio.empleados[0][0]="";
                    Inicio.empleados[0][1]="";
                    Inicio.empleados[0][2]="";

                    existe=true;
                }
            }
        }
        if(existe==false){
            JOptionPane.showMessageDialog(null, "No existe el empleado");
        }else{
            Inicio.cantidadempleados=Inicio.cantidadempleados-1;
            JOptionPane.showMessageDialog(null, "Empleado eliminado");
        }
    }
    
    public void buscar(){
        Boolean existe=false;
        DefaultTableModel md=new DefaultTableModel();
        md.setRowCount(0);
        String id=(String) JOptionPane.showInputDialog("Ingrese el ID único del empleado");
        String[] data=new String[3];
        String[] encabezado={"Nombre","Agencia","ID Único"};
        md.setColumnIdentifiers(encabezado);
            for (int i = 0; i < Inicio.cantidadempleados; i++) {
                if(id.equals(Inicio.empleados[i][2])){
                    data[0]=Inicio.empleados[i][0];
                    data[1]=Inicio.empleados[i][1];
                    data[2]=Inicio.empleados[i][2];
                    md.addRow(data);
                    existe=true;
                }
            }
        if(existe==false){
                JOptionPane.showMessageDialog(null, "No exite el cliente");
        }
        tblprincipal.setModel(md);
    }
    
    public void asignar(){
        String id=JOptionPane.showInputDialog("Ingrese el ID del empleado para asignarlo:");
        try {
            for (int i = 0; i < Inicio.cantidadempleados; i++) {
                if(id.equals(Inicio.empleados[i][2])){
                    Inicio.empleados[i][1]=JOptionPane.showInputDialog("Ingrese el número de agencia nueva a asignar: ");
                    JOptionPane.showMessageDialog(null, "Asignacion hecha, si no ingreso agencia válida intente de nuevo");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, datos inválidos");
        }
    }
}
