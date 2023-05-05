package Proyecto1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ronald Macal
 */
public class frmReportes extends JFrame implements ActionListener{
    JPanel panel;
    JComboBox combo;
    JLabel titulo;
    JButton btngenerar;
    
    public frmReportes(){
        panel=new JPanel();
        titulo=new JLabel("REPORTES:");
        btngenerar=new JButton("Generar reporte");
        combo=new JComboBox();
        combo.addItem("1. Listado de agnecias (con y sin autobanco)");
        combo.addItem("2. Listado de cajeros");
        combo.addItem("3. Listado completo de clientes");
        combo.addItem("4. Top 3, de clientes que poseen más cuentas");
        combo.addItem("5. Top 3, de clientes con mayor suma de dinero");
        combo.addItem("6. Top 3, de clientes que deben más al banco (tarjetas y préstamos)");
        combo.addItem("7. Top 3, de agencias más utilizadas por los clientes");
        combo.addItem("8. Top 2, de operaciones más realizadas en el Call-Center");
        combo.addItem("9. Sumatoria del efectivo disponible de todas las agencias");
        combo.addItem("10. Reporte general de un cliente específico");
        combo.addItem("11. Monto de efectivo disponible en cada agencia");
        combo.addItem("12. Listado de empleados en cada agencia");
        combo.addItem("13. Listado de empleados en oficinas centrales, por departamento");
        combo.addItem("14. Agencia con mayor número de empleados");
        combo.addItem("15. Top 3, de clientes con mayor número de compras en su tarjeta de crédito");
        
    }
    
    public void inicializar(){
        //Propiedades básicas del formulario
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Módulo de Reportes (BDE)");
        setSize(550, 150);
        setLocation(20,30);
        
        //Inicio del trabajo en el Panel
        panel.setSize(550, 150);
        panel.setLayout(null);
        
        titulo.setBounds(20,20,150,20);
        panel.add(titulo);
        combo.setBounds(30,40,480,20);
        panel.add(combo);
        combo.addActionListener(this);
        
        btngenerar.setBounds(190,70,200,20);
        panel.add(btngenerar);
        btngenerar.addActionListener(this);
        
        getContentPane().add(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btngenerar)){
            switch(combo.getSelectedIndex()){
                case 0:
                    try {
                        listadocompletoagencias();
                        JOptionPane.showMessageDialog(null, "Documento creado");
                        try {
                            File path = new File ("ListadoAgencias.pdf");
                            Desktop.getDesktop().open(path);
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 1:
                    try {
                        listadocajeros();
                        JOptionPane.showMessageDialog(null, "Documento creado");
                        try {
                            File path = new File ("ListadoCajeros.pdf");
                            Desktop.getDesktop().open(path);
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 2:
                    try {
                        listadoclientes();
                        JOptionPane.showMessageDialog(null, "Documento creado");
                        try {
                            File path = new File ("ListadoClientes.pdf");
                            Desktop.getDesktop().open(path);
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 3:
                    try {
                        topmascuentas();
                        JOptionPane.showMessageDialog(null, "Documento creado");
                        try {
                            File path = new File ("ClientesmasCuentas.pdf");
                            Desktop.getDesktop().open(path);
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 4:
                    try {
                        mayorsumadinero();
                        JOptionPane.showMessageDialog(null, "Documento creado");
                        try {
                            File path = new File ("MayorEfectivo.pdf");
                            Desktop.getDesktop().open(path);
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 5:
                    //Top 3 de clientes que deben más al banco
                    
                    break;
                case 6:
                    //Top 3 agencias más utilizadas por los clientes
                    
                    break;
                case 7:
                    //Top 2 de operaciones más realizadas en call-Center
                    
                    break;
                case 8:
                    try {
                        sumatoriaefectivo();
                        JOptionPane.showMessageDialog(null, "Documento creado");
                        try {
                            File path = new File ("EfectivoAgencia.pdf");
                            Desktop.getDesktop().open(path);
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 9:
                    try {
                        descripcioncliente();
                        JOptionPane.showMessageDialog(null, "Documento creado");
                        try {
                            File path = new File ("DescripcionCliente.pdf");
                            Desktop.getDesktop().open(path);
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 10:
                    try {
                        montoencadaagencia();
                        JOptionPane.showMessageDialog(null, "Documento creado");
                        try {
                            File path = new File ("MontoxAgencia.pdf");
                            Desktop.getDesktop().open(path);
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 11:
                    try {
                        empleadosenagencia();
                        JOptionPane.showMessageDialog(null, "Documento creado");
                        try {
                            File path = new File ("EmpleadosAgencia.pdf");
                            Desktop.getDesktop().open(path);
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 12:
                    try {
                        oficinascentrales();
                        JOptionPane.showMessageDialog(null, "Documento creado");
                        try {
                            File path = new File ("OficinasCentrales.pdf");
                            Desktop.getDesktop().open(path);
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 13:
                    
                    break;
                case 14:
                    
                    break;    
            }
        }
    }
    public void oficinascentrales()throws FileNotFoundException, DocumentException{
        Document documento=new Document();
        FileOutputStream ficheroPDF=new FileOutputStream("OficinasCentrales.pdf");
        PdfWriter.getInstance(documento, ficheroPDF);
        documento.open();
        Paragraph titulo = new Paragraph("Listado de empleados en oficinas centrales \n\n",
                FontFactory.getFont("arial",
                        24,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );
        documento.add(titulo);
        
        
        PdfPTable gerencia=new PdfPTable(2);
        gerencia.addCell("Nombre");gerencia.addCell("ID Empleado");
        PdfPTable marketing=new PdfPTable(2);
        marketing.addCell("Nombre");marketing.addCell("ID Empleado");
        PdfPTable informatica=new PdfPTable(2);
        informatica.addCell("Nombre");informatica.addCell("ID Empleado");
        PdfPTable financiero=new PdfPTable(2);
        financiero.addCell("Nombre");financiero.addCell("ID Empleado");
        PdfPTable reclamos=new PdfPTable(2);
        reclamos.addCell("Nombre");reclamos.addCell("ID Empleado");
        PdfPTable cobros=new PdfPTable(2);
        cobros.addCell("Nombre");cobros.addCell("ID Empleado");
        
        for (int i = 0; i < Inicio.cantidadempleados; i++) {
            switch(Inicio.empleados[i][1]){
                case "31":
                    gerencia.addCell(Inicio.empleados[i][0]);
                    gerencia.addCell(Inicio.empleados[i][2]);
                    break;
                case "32":
                    marketing.addCell(Inicio.empleados[i][0]);
                    marketing.addCell(Inicio.empleados[i][2]);
                    break;
                case "33":
                    informatica.addCell(Inicio.empleados[i][0]);
                    informatica.addCell(Inicio.empleados[i][2]);
                    break;
                case "34":
                    financiero.addCell(Inicio.empleados[i][0]);
                    financiero.addCell(Inicio.empleados[i][2]);
                    break;
                case "35":
                    reclamos.addCell(Inicio.empleados[i][0]);
                    reclamos.addCell(Inicio.empleados[i][2]);
                    break;
                case "36":
                    cobros.addCell(Inicio.empleados[i][0]);
                    cobros.addCell(Inicio.empleados[i][2]);
                    break;    
            }
        }
        
        Paragraph etiqueta = new Paragraph("Departamento Gerencia\n\n",
                FontFactory.getFont("arial",
                        13,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );documento.add(etiqueta);
        documento.add(gerencia);
        Paragraph etiqueta1 = new Paragraph("Departamento Marketing\n\n",
                FontFactory.getFont("arial",
                        13,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );documento.add(etiqueta1);
        documento.add(marketing);
        Paragraph etiqueta2 = new Paragraph("Departamento Informatica\n\n",
                FontFactory.getFont("arial",
                        13,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );documento.add(etiqueta2);
        documento.add(informatica);
        Paragraph etiqueta3 = new Paragraph("Departamento Financiero\n\n",
                FontFactory.getFont("arial",
                        13,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );documento.add(etiqueta3);
        documento.add(financiero);
        Paragraph etiqueta4 = new Paragraph("Departamento Reclamos\n\n",
                FontFactory.getFont("arial",
                        13,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );documento.add(etiqueta4);
        documento.add(reclamos);
        Paragraph etiqueta5 = new Paragraph("Departamento Cobros\n\n",
                FontFactory.getFont("arial",
                        13,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );documento.add(etiqueta5);
        documento.add(cobros);
        documento.close();
    }
    public void empleadosenagencia() throws FileNotFoundException, DocumentException{
        Document documento=new Document();
        FileOutputStream ficheroPDF=new FileOutputStream("EmpleadosAgencia.pdf");
        PdfWriter.getInstance(documento, ficheroPDF);
        documento.open();
        Paragraph titulo = new Paragraph("Listado de empleados en cada agencia \n\n",
                FontFactory.getFont("arial",
                        24,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );
        documento.add(titulo);
        PdfPTable empleados=new PdfPTable(3);
        empleados.addCell("Nombre");
        empleados.addCell("# Agencia");
        empleados.addCell("ID Empleado");
        
        for (int i = 0; i < Inicio.cantidadempleados; i++) {
            empleados.addCell(Inicio.empleados[i][0]);
            empleados.addCell(Inicio.empleados[i][1]);
            empleados.addCell(Inicio.empleados[i][2]);
        }
        documento.add(empleados);
        documento.close();
    }
    public void montoencadaagencia() throws FileNotFoundException, DocumentException{
        Document documento=new Document();
        FileOutputStream ficheroPDF=new FileOutputStream("MontoxAgencia.pdf");
        PdfWriter.getInstance(documento, ficheroPDF);
        documento.open();
        Paragraph titulo = new Paragraph("Monto por agencia \n\n",
                FontFactory.getFont("arial",
                        24,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );
        documento.add(titulo);
        PdfPTable tabla=new PdfPTable(2);
        tabla.addCell("ID Agencia");
        tabla.addCell("Efectivo");
        for (int i = 0; i < Inicio.cantidadagenciabancaria; i++) {
            tabla.addCell(Integer.toString(Inicio.efectivoagenciabancaria[i][0]));
            tabla.addCell(Integer.toString(Inicio.efectivoagenciabancaria[i][1]));
        }
        for (int i = 0; i < Inicio.cantidadagenciaautobanco; i++) {
            tabla.addCell(Integer.toString(Inicio.efectivoautobanco[i][0]));
            tabla.addCell(Integer.toString(Inicio.efectivoautobanco[i][1]));
        }
        documento.add(tabla);
        documento.close();
    }
    public void descripcioncliente() throws FileNotFoundException, DocumentException{
        Document documento=new Document();
        FileOutputStream ficheroPDF=new FileOutputStream("DescripcionCliente.pdf");
        PdfWriter.getInstance(documento, ficheroPDF);
        documento.open();
        Paragraph titulo = new Paragraph("Descripción general del cliente \n\n",
                FontFactory.getFont("arial",
                        24,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );
        documento.add(titulo);
        String id=JOptionPane.showInputDialog("Ingrese el ID del cliente para mostrar las descripciones");
        PdfPTable tabla=new PdfPTable(4);
        PdfPTable tabla1=new PdfPTable(4);
        tabla.addCell("ID Cliente");
        tabla.addCell("Nombre");
        tabla.addCell("Direccion");
        tabla.addCell("Telefono");
        tabla1.addCell("# Ahorro");
        tabla1.addCell("# Monetaria");
        tabla1.addCell("# Prestamo");
        tabla1.addCell("# Tarjeta");
        for (int i = 0; i < Inicio.cantidadclientes; i++) {
            if(id.equals(Inicio.clientes[i][0])){
                tabla.addCell(Inicio.clientes[i][0]);
                tabla.addCell(Inicio.clientes[i][1]);
                tabla.addCell(Inicio.clientes[i][2]);
                tabla.addCell(Inicio.clientes[i][3]);
                tabla1.addCell(Inicio.clientes[i][4]);
                tabla1.addCell(Inicio.clientes[i][5]);
                tabla1.addCell(Inicio.clientes[i][6]);
                tabla1.addCell(Inicio.clientes[i][7]);
            }
        }
        documento.add(tabla);
        documento.add(tabla1);
        documento.close();
    }
    public void sumatoriaefectivo()throws FileNotFoundException, DocumentException{
        Document documento=new Document();
        FileOutputStream ficheroPDF=new FileOutputStream("EfectivoAgencia.pdf");
        PdfWriter.getInstance(documento, ficheroPDF);
        documento.open();
        Paragraph titulo = new Paragraph("Sumatoria del efectivo disponible de todas las agencias \n\n",
                FontFactory.getFont("arial",
                        24,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );
        documento.add(titulo);
        int sumatoria=0;
        for (int i = 0; i < Inicio.cantidadagenciabancaria; i++) {
            sumatoria=sumatoria+Inicio.efectivoagenciabancaria[i][1];
        }
        for (int i = 0; i < Inicio.cantidadagenciaautobanco; i++) {
            sumatoria=sumatoria+Inicio.efectivoautobanco[i][1];
        }
        Paragraph etiqueta = new Paragraph("La sumatoria total es:  "+sumatoria,
                FontFactory.getFont("arial",
                        14,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );
        documento.add(etiqueta);
        documento.close();
    }
    public void mayorsumadinero()throws FileNotFoundException, DocumentException{
        Document documento=new Document();
        FileOutputStream ficheroPDF=new FileOutputStream("MayorEfectivo.pdf");
        PdfWriter.getInstance(documento, ficheroPDF);
        documento.open();
        Paragraph titulo = new Paragraph("Top 3, Clientes con mayor suma de dinero \n\n",
                FontFactory.getFont("arial",
                        24,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );
        documento.add(titulo);
        
        PdfPTable tabla=new PdfPTable(2);
        tabla.addCell("ID Cliente");
        tabla.addCell("Efectivo");
        
        int uno=0;int dos=0;int tres=0;int efecuno=0; int efecdos=0; int efectres=0;
        int[] efectivo=new  int[Inicio.cantidadclientes];
        int ahorro=0;int monetaria=0;
        for (int i = 0; i < Inicio.cantidadclientes; i++) {
            ahorro=Integer.parseInt(Inicio.clientes[i][4]);
            monetaria=Integer.parseInt(Inicio.clientes[i][5]);
            for (int j = 0; j < Inicio.cantidadcuentas; j++) {
                if(ahorro==Inicio.efectivocuentas[j][0]){
                    efectivo[i]=efectivo[i]+Inicio.efectivocuentas[i][1];
                }else if(monetaria==Inicio.efectivocuentas[j][0]){
                    efectivo[i]=efectivo[i]+Inicio.efectivocuentas[i][1];
                }
            }
        }
        //Evaluar cual es el valor mayor en todo vector
        int nmayor=efectivo[0];
        for (int i = 0; i < Inicio.cantidadcuentas; i++) {
            if(nmayor<=efectivo[i]){
                uno=i;
                nmayor=efectivo[i];
                efecuno=nmayor;
                
            }
        }
        efectivo[uno]=0;
        nmayor=efectivo[0];
        for (int i = 0; i < Inicio.cantidadcuentas; i++) {
            if(nmayor<=efectivo[i]){
                nmayor=efectivo[i];
                efecdos=nmayor;
                dos=i;
            }
        }
        efectivo[dos]=0;
        nmayor=efectivo[0];
        for (int i = 0; i < Inicio.cantidadcuentas; i++) {
            if(nmayor<=efectivo[i]){
                nmayor=efectivo[i];
                efectres=nmayor;
                tres=i;
            }
        }
        //Ingresar datos a tabla
        tabla.addCell(Inicio.clientes[uno][0]);
        tabla.addCell(Integer.toString(efecuno));
        tabla.addCell(Inicio.clientes[dos][0]);
        tabla.addCell(Integer.toString(efecdos));
        tabla.addCell(Inicio.clientes[tres][0]);
        tabla.addCell(Integer.toString(efectres));
        documento.add(tabla);
        documento.close();
    }
    public void topmascuentas() throws FileNotFoundException, DocumentException{
        Document documento=new Document();
        FileOutputStream ficheroPDF=new FileOutputStream("ClientesmasCuentas.pdf");
        PdfWriter.getInstance(documento, ficheroPDF);
        documento.open();
        Paragraph titulo = new Paragraph("Top 3, Clientes que poseen más cuentas \n\n",
                FontFactory.getFont("arial",
                        24,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );
        documento.add(titulo);
        PdfPTable tabla=new PdfPTable(4);
        tabla.addCell("# Cliente");
        tabla.addCell("Nombre");
        tabla.addCell("Cta. Ahorro");
        tabla.addCell("Cta. Monetaria");
        
        int auxiliar=0;
        for (int i = 0; i < Inicio.cantidadclientes; i++) {
            if((Inicio.clientes[i][4])!="0" && (Inicio.clientes[i][5])!="0"){
                tabla.addCell(Inicio.clientes[i][0]);
                tabla.addCell(Inicio.clientes[i][1]);
                tabla.addCell(Inicio.clientes[i][4]);
                tabla.addCell(Inicio.clientes[i][5]);
                auxiliar=auxiliar+1;
            }
        }
        if(auxiliar<3){
            for (int i = 0; i < Inicio.cantidadclientes; i++) {
                if((Inicio.clientes[i][4])!="0" && (Inicio.clientes[i][5])!="0"){
                    
                }else if((Inicio.clientes[i][4])!="0" || (Inicio.clientes[i][5])!="0"){
                    tabla.addCell(Inicio.clientes[i][0]);
                    tabla.addCell(Inicio.clientes[i][1]);
                    tabla.addCell(Inicio.clientes[i][4]);
                    tabla.addCell(Inicio.clientes[i][5]);
                    auxiliar=auxiliar+1;
                }
            }
        }
        documento.add(tabla);
        documento.close();
    }
    public void listadoclientes() throws FileNotFoundException, DocumentException{
        Document documento=new Document();
        FileOutputStream ficheroPDF=new FileOutputStream("ListadoClientes.pdf");
        PdfWriter.getInstance(documento, ficheroPDF);
        documento.open();
        Paragraph titulo = new Paragraph("Listado general de Clientes \n\n",
                FontFactory.getFont("arial",
                        24,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );
        documento.add(titulo);
        PdfPTable tabla=new PdfPTable(4);
        tabla.addCell("# Cliente");
        tabla.addCell("Nombre");
        tabla.addCell("Direccion");
        tabla.addCell("Telefono");
        
        
        for (int i = 0; i < Inicio.cantidadclientes; i++) {
            tabla.addCell(Inicio.clientes[i][0]);
            tabla.addCell(Inicio.clientes[i][1]);
            tabla.addCell(Inicio.clientes[i][2]);
            tabla.addCell(Inicio.clientes[i][3]);
        }
        documento.add(tabla);
        
        PdfPTable tabla2=new PdfPTable(5);
        tabla2.addCell("# Cliente");
        tabla2.addCell("cta. Ahorro");
        tabla2.addCell("cta. Monetaria");
        tabla2.addCell("# Prestamo");
        tabla2.addCell("# Tarjeta");
        
        
        for (int i = 0; i < Inicio.cantidadclientes; i++) {
            tabla2.addCell(Inicio.clientes[i][0]);
            tabla2.addCell(Inicio.clientes[i][4]);
            tabla2.addCell(Inicio.clientes[i][5]);
            tabla2.addCell(Inicio.clientes[i][6]);
            tabla2.addCell(Inicio.clientes[i][7]);
            
        }
        documento.add(tabla2);
        
        Paragraph etiqueta = new Paragraph("Los valores 0, equivalen al lugar donde no hay datos \n\n",
                FontFactory.getFont("arial",
                        12,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );
        documento.add(etiqueta);
        documento.close();
    }
    public void listadocajeros() throws FileNotFoundException, DocumentException{
        Document documento=new Document();
        FileOutputStream ficheroPDF=new FileOutputStream("ListadoCajeros.pdf");
        PdfWriter.getInstance(documento, ficheroPDF);
        documento.open();
        Paragraph titulo = new Paragraph("Listado general cajeros \n\n",
                FontFactory.getFont("arial",
                        24,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );
        documento.add(titulo);
        PdfPTable tabla=new PdfPTable(5);
        tabla.addCell("# Cajero");
        tabla.addCell("Direccion");
        tabla.addCell("Estado");
        tabla.addCell("Efectivo (Q)");
        tabla.addCell("Transaccion");
        
        for (int i = 0; i < Inicio.cantidadcajeros; i++) {
            tabla.addCell(Inicio.cajeros[i][0]);
            tabla.addCell(Inicio.cajeros[i][1]);
            tabla.addCell(Inicio.cajeros[i][2]);
            tabla.addCell(Integer.toString(Inicio.efectivocajeros[i][1]));
            tabla.addCell(Integer.toString(Inicio.efectivocajeros[i][2]));
        }
        documento.add(tabla);
        documento.close();
    }
    public void listadocompletoagencias() throws FileNotFoundException, DocumentException{
        Document documento=new Document();
        FileOutputStream ficheroPDF=new FileOutputStream("ListadoAgencias.pdf");
        PdfWriter.getInstance(documento, ficheroPDF);
        documento.open();
        Paragraph titulo = new Paragraph("Listado general de agencias bancarias y autobancos \n\n",
                FontFactory.getFont("arial",
                        24,
                        Font.BOLD,
                        BaseColor.DARK_GRAY
                        )
        );
        documento.add(titulo);
        
        //Crear tabla
        PdfPTable tabla = new PdfPTable(5);
        tabla.addCell("Nombre");
        tabla.addCell("# Agencia");
        tabla.addCell("Dirección");
        tabla.addCell("Telefono");
        tabla.addCell("Efectivo (Q)");
        for (int i = 0; i < Inicio.cantidadagenciabancaria; i++) {
            tabla.addCell(Inicio.agenciabancaria[i][0]);
            tabla.addCell(Inicio.agenciabancaria[i][1]);
            tabla.addCell(Inicio.agenciabancaria[i][2]);
            tabla.addCell(Inicio.agenciabancaria[i][3]);
            tabla.addCell(Integer.toString(Inicio.efectivoagenciabancaria[i][1]));
        }
        for (int i = 0; i < Inicio.cantidadagenciaautobanco; i++) {
            tabla.addCell(Inicio.agenciaautobanco[i][0]);
            tabla.addCell(Inicio.agenciaautobanco[i][1]);
            tabla.addCell(Inicio.agenciaautobanco[i][2]);
            tabla.addCell(Inicio.agenciaautobanco[i][3]);
            tabla.addCell(Integer.toString(Inicio.efectivoautobanco[i][1]));
        }
        documento.add(tabla);
        documento.close();
    }
}
