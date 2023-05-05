package Proyecto1;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Ronald Macal
 */
public class frmInicio extends JFrame implements ActionListener {
    String usuario,password;
    JPanel panel;
    JButton btningresar;
    JButton btnentrarcomoempleado;
    JButton btnlimpiar;
    JPasswordField txtcontrasena;
    JTextField txtusuario;
    

    public frmInicio(){
        panel=new JPanel();
        btningresar=new JButton("Ingresar");
        btnentrarcomoempleado=new JButton("Ingresar");
        btnlimpiar=new JButton("Limpiar");
        txtcontrasena=new JPasswordField();
        txtusuario=new JTextField();
    }
    
    public void inicializar(){
        //Propiedades básicas del formulario
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Inicio BDE");
        setSize(500, 200);
        

        //Inicio del trabajo en el Panel
        panel.setSize(500, 300);
        panel.setLayout(null);
        
        //Etiquetas para colocar en el formulario
        JLabel lbencabezado=new JLabel("BIENVENIDO AL BANCO DEL EXTERIOR BDE");JLabel lbuser=new JLabel("Usuario: ");JLabel lbpass=new JLabel("Contraseña: ");JLabel lbempleado=new JLabel("Ingresar como empleado: ");        
        
        lbencabezado.setBounds(10, 3, 300, 40);
        lbuser.setBounds(40,30,200,40);
        txtusuario.setBounds(120,40,90,20);
        lbpass.setBounds(40,60,200,40);
        txtcontrasena.setBounds(120,70,90,20);
        btnlimpiar.setBounds(80,100,100,20);
        btningresar.setBounds(80,127,100,20);
        
        lbempleado.setBounds(300,30,200,40);
        btnentrarcomoempleado.setBounds(325,65,100,20);
        
        //Agregar todo al panel
        panel.add(lbencabezado);
        panel.add(lbuser);
        panel.add(lbpass);
        panel.add(lbempleado);
        panel.add(txtusuario);
        panel.add(txtcontrasena);
        panel.add(btnlimpiar);
        panel.add(btningresar);
        panel.add(btnentrarcomoempleado);
        
        //Accionar botones
        btnlimpiar.addActionListener(this);
        btningresar.addActionListener(this);
        btnentrarcomoempleado.addActionListener(this);
        
        //Agregar el panel al formulario
        getContentPane().add(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnlimpiar)){
            txtusuario.setText("");
            txtcontrasena.setText("");
        }
        if (e.getSource().equals(btningresar)) {
            usuario=txtusuario.getText();
            password=txtcontrasena.getText();
            
            //if("admin".equals(usuario) && "123456".equals(password)){
                frmAdministrador admin=new frmAdministrador();
                admin.inicializar();
                admin.setVisible(true);
                this.dispose();
            //}else{
                //JOptionPane.showMessageDialog(null, "Error, usuario o contraseña incorrecta. Vuelva a intentarlo (Ingrese el usuario en minusculas).");
            //}
        }
        if(e.getSource().equals(btnentrarcomoempleado)){
            frmTransacciones trans=new frmTransacciones();
            trans.inicializar();
            trans.setVisible(true);
            this.dispose();
        }
    }
    
    
}
