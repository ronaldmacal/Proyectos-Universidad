package Proyecto1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.JTextArea;

/**
 *
 * @author Ronald Macal
 */
public class frmMostrarProcesos extends JFrame implements ActionListener {
    JPanel panel;
    JTextArea registro;
    JScrollPane scroll;
    JLabel etiqueta;
    
    public frmMostrarProcesos(){
        registro=new JTextArea();
        panel=new JPanel();
        scroll=new JScrollPane(registro);
        registro.setEditable(false);
        etiqueta=new JLabel("Registro de transacción:");
        
    }
    
    public void inicializar(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Registro de transacciones");
        setSize(340, 300);
        setLocation(20,30);
        
        panel.setSize(340, 300);
        panel.setLayout(null);
        etiqueta.setBounds(5, 10, 200, 15);
        panel.add(etiqueta);
        
        scroll.setBounds(10, 27, 300, 200);
        panel.add(scroll);
        
        
        registro.append("Módulo de Transacciones BDE");
        registro.append(System.getProperty("line.separator"));
        String mostarfecha;
        Date fecha=new Date();
        SimpleDateFormat formatoFecha=new SimpleDateFormat("hh: mm: ss a dd-MMM-YYYY");
        mostarfecha=formatoFecha.format(fecha);
        registro.append("Fecha y Hora: "+mostarfecha);
        registro.append(System.getProperty("line.separator"));
        
        getContentPane().add(panel);
    }
    
    public void mostrardata(String data){
        registro.append(data);
        registro.append(System.getProperty("line.separator"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
