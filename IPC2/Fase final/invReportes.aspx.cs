using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using iTextSharp.text;
using iTextSharp.text.pdf;
using System.IO;
using System.Data;
using System.Data.SqlClient;
using System.Data.Sql;

namespace Proyecto201612151.Usuarios
{
    public partial class invReportes : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Response.Redirect("moduloInventario");
        }

        protected void btninvbodega_Click(object sender, EventArgs e)
        {
            string idbodega = txtbodega.Text;
            string niterp = this.Session["niterp"].ToString();
            Document invbodega = new Document(PageSize.LETTER);
            // Indicamos donde vamos a guardar el documento
            PdfWriter writer = PdfWriter.GetInstance(invbodega,
                                        new FileStream(@"C:\Users\Bark__000\Desktop\100% Barça\FIUSAC\IPC2\Proyecto\Proyecto201612151\Proyecto201612151\Reportes\ReporteBodega201612151.pdf", FileMode.Create));
            invbodega.AddTitle("Reporte de Bodegas");
            invbodega.AddCreator("201612151");
            invbodega.Open();
            Paragraph title = new Paragraph();
            title.Font = FontFactory.GetFont(FontFactory.TIMES, 18f, BaseColor.BLUE);
            title.Add("Reporte de Bodega");
            invbodega.Add(title);
            Conexion con = new Conexion();

            //Datos de la bodega
            Chunk c = new Chunk("Descripción basica de la bodega ingresada.");
            invbodega.Add(c);
            PdfPTable tblbodega = new PdfPTable(4);
            tblbodega.WidthPercentage = 100;
            PdfPCell cabeza = new PdfPCell(new Phrase("Bodega"));
            cabeza.Colspan = 4;
            tblbodega.HorizontalAlignment = 1;
            tblbodega.AddCell(cabeza);
            tblbodega.AddCell("Id Bodega");
            tblbodega.AddCell("Nombre");
            tblbodega.AddCell("Descripción");
            tblbodega.AddCell("Dirección");
            string sql = "select * from Bodegas where idbodega='"+idbodega+"'";
            SqlCommand cmdlote = new SqlCommand(sql, con.getConexion());
            SqlDataReader registrosbodega = cmdlote.ExecuteReader();
            while (registrosbodega.Read())
            {
                tblbodega.AddCell(registrosbodega.GetString(0));
                tblbodega.AddCell(registrosbodega.GetString(1));
                tblbodega.AddCell(registrosbodega.GetString(2));
                tblbodega.AddCell(registrosbodega.GetString(3));
            }
            invbodega.Add(tblbodega);
            //Ubicación productos
            Chunk c1 = new Chunk("Descripción de los diferentes lotes ubicados en esta bodega: "+idbodega);
            invbodega.Add(c1);
            PdfPTable tblubica = new PdfPTable(4);
            tblubica.WidthPercentage = 100;
            PdfPCell cabeza1 = new PdfPCell(new Phrase("Ubicaciones registradas en esta bodega"));
            cabeza1.Colspan=4;
            tblubica.HorizontalAlignment = 1;
            tblubica.AddCell(cabeza1);
            tblubica.AddCell("Id Ubicacion");
            tblubica.AddCell("Id Tipo Saldo/Lote");
            tblubica.AddCell("Id Bodega");
            tblubica.AddCell("Id Nivel");
            List<string> listalotes = new List<string>();
            sql = "select * from Ubicar where idbodega='" + idbodega + "'";
            SqlCommand cmdubica = new SqlCommand(sql, con.getConexion());
            SqlDataReader regubica = cmdubica.ExecuteReader();
            while (regubica.Read())
            {
                listalotes.Add(Convert.ToString(regubica.GetInt32(1)));
                tblubica.AddCell(Convert.ToString(regubica.GetInt32(0)));
                tblubica.AddCell(Convert.ToString(regubica.GetInt32(1)));
                tblubica.AddCell(regubica.GetString(2));
                tblubica.AddCell(Convert.ToString(regubica.GetInt32(3)));
            }
            invbodega.Add(tblubica);

            //Lotes y saldos en esa bodega (costo promedio)
            Chunk c3 = new Chunk("Lotes y saldos ubicados en la bodega ingresada");
            invbodega.Add(c3);
            PdfPTable tblubicacion = new PdfPTable(6);
            tblubicacion.WidthPercentage = 100;
            PdfPCell cabeza2 = new PdfPCell(new Phrase("Ubicaciones de los productos"));
            cabeza2.Colspan = 6;
            cabeza2.HorizontalAlignment = 1;
            tblubicacion.AddCell(cabeza2);
            tblubicacion.AddCell("Id Tipo");
            tblubicacion.AddCell("Producto");
            tblubicacion.AddCell("Cantidad");
            tblubicacion.AddCell("Costo promedio");
            tblubicacion.AddCell("Tipo");
            tblubicacion.AddCell("Fecha");
            List<string> productos = new List<string>();
            for (int i = 0; i < listalotes.Count(); i++)
            {
                sql = "select * from LotesySaldos where idtipo=" + listalotes[i];
                SqlCommand cmdubi = new SqlCommand(sql, con.getConexion());
                SqlDataReader regubi = cmdubi.ExecuteReader();
                if (regubi.Read())
                {
                    productos.Add(regubi.GetString(1));
                    tblubicacion.AddCell(Convert.ToString(regubi.GetInt32(0)));
                    tblubicacion.AddCell(regubi.GetString(1));
                    tblubicacion.AddCell(Convert.ToString(regubi.GetInt32(2)));
                    tblubicacion.AddCell(Convert.ToString(regubi.GetDecimal(3)));
                    tblubicacion.AddCell(regubi.GetString(4));
                    tblubicacion.AddCell(Convert.ToString(regubi.GetDateTime(5)));
                }
            }
            invbodega.Add(tblubicacion);

            Chunk c4 = new Chunk("Productos guardados en esta bodega");
            invbodega.Add(c4);
            PdfPTable tblproductos = new PdfPTable(8);
            tblproductos.WidthPercentage = 100;
            PdfPCell cell = new PdfPCell(new Phrase("Descripción de producto"));
            cell.Colspan = 8;
            cell.HorizontalAlignment = 1;
            tblproductos.AddCell(cell);
            tblproductos.AddCell("Codigo");
            tblproductos.AddCell("Barras");
            tblproductos.AddCell("Nombre");
            tblproductos.AddCell("Desc.");
            tblproductos.AddCell("Des. Pres.");
            tblproductos.AddCell("Pres.");
            tblproductos.AddCell("Des. Clas.");
            tblproductos.AddCell("Clas.");
            for (int i = 0; i < productos.Count(); i++)
            {
                sql = "select * from Productos_registro where id_erp='" + niterp + "' and codigo='" + productos[i] + "'";
                SqlCommand cmdubi = new SqlCommand(sql, con.getConexion());
                SqlDataReader regubi = cmdubi.ExecuteReader();
                while (regubi.Read())
                {
                    tblproductos.AddCell(regubi.GetString(0));
                    tblproductos.AddCell(regubi.GetString(1));
                    tblproductos.AddCell(regubi.GetString(2));
                    tblproductos.AddCell(regubi.GetString(3));
                    tblproductos.AddCell(regubi.GetString(4));
                    tblproductos.AddCell(regubi.GetString(5));
                    tblproductos.AddCell(regubi.GetString(6));
                    tblproductos.AddCell(regubi.GetString(7));
                }
            }
            invbodega.Add(tblproductos);


            invbodega.Close();
            writer.Close();
            Response.Write("<script>alert('Documento creado con éxito, dirijase a la carpeta Reportes para verlo')</script>");

        }

        protected void btninvproducto_Click(object sender, EventArgs e)
        {
            string idproducto = txtproducto.Text;
            string niterp= this.Session["niterp"].ToString();
            Document invproducto = new Document(PageSize.LETTER);
            // Indicamos donde vamos a guardar el documento
            PdfWriter writer = PdfWriter.GetInstance(invproducto,
                                        new FileStream(@"C:\Users\Bark__000\Desktop\100% Barça\FIUSAC\IPC2\Proyecto\Proyecto201612151\Proyecto201612151\Reportes\ReporteProducto201612151.pdf", FileMode.Create));
            invproducto.AddTitle("Reporte de Producto");
            invproducto.AddCreator("201612151");
            invproducto.Open();

            Paragraph title = new Paragraph();
            title.Font = FontFactory.GetFont(FontFactory.TIMES, 18f, BaseColor.BLUE);
            title.Add("Reporte de Producto");
            invproducto.Add(title);

            //Mostrar los datos del producto
            Chunk c1 = new Chunk("Reporte mostrado por medio de tablas. A continuación una descripción del producto.");
            invproducto.Add(c1);
            PdfPTable tblproductos = new PdfPTable(8);
            tblproductos.WidthPercentage = 100;
            PdfPCell cell = new PdfPCell(new Phrase("Descripción de producto"));
            cell.Colspan = 8;
            cell.HorizontalAlignment = 1;
            tblproductos.AddCell(cell);
            tblproductos.AddCell("Codigo");
            tblproductos.AddCell("Barras");
            tblproductos.AddCell("Nombre");
            tblproductos.AddCell("Desc.");
            tblproductos.AddCell("Des. Pres.");
            tblproductos.AddCell("Pres.");
            tblproductos.AddCell("Des. Clas.");
            tblproductos.AddCell("Clas.");
            Conexion con = new Conexion();
            string sql = "select * from Productos_registro where id_erp='"+niterp+"' and codigo='"+idproducto+"'";
            SqlCommand cmd = new SqlCommand(sql, con.getConexion());
            SqlDataReader registro = cmd.ExecuteReader();
            if (registro.Read())
            {
                tblproductos.AddCell(registro.GetString(0));
                tblproductos.AddCell(registro.GetString(1));
                tblproductos.AddCell(registro.GetString(2));
                tblproductos.AddCell(registro.GetString(3));
                tblproductos.AddCell(registro.GetString(4));
                tblproductos.AddCell(registro.GetString(5));
                tblproductos.AddCell(registro.GetString(6));
                tblproductos.AddCell(registro.GetString(7));
            }
            invproducto.Add(tblproductos);

            //Agreagar Lotesysaldos del producto
            Chunk c2 = new Chunk("Lotes o Saldos registrados para el producto. En el caso de los lotes -U corresponden a UEPS y -P a PEPS");
            invproducto.Add(c2);
            PdfPTable tbllote = new PdfPTable(5);
            tbllote.WidthPercentage = 100;
            PdfPCell cabeza1 = new PdfPCell(new Phrase("Lotes o Saldos"));
            cabeza1.Colspan = 5;
            cabeza1.HorizontalAlignment = 1;
            tbllote.AddCell(cabeza1);
            tbllote.AddCell("Id Tipo");
            tbllote.AddCell("Cantidad");
            tbllote.AddCell("Costo Promedio");
            tbllote.AddCell("Tipo");
            tbllote.AddCell("Fecha");
            sql = "select idtipo,cantidad,costopromedio,tipo,fecha from LotesySaldos where idproducto='"+idproducto+"'";
            SqlCommand cmdlote = new SqlCommand(sql, con.getConexion());
            SqlDataReader registroslote = cmdlote.ExecuteReader();
            List<string> lotes = new List<string>();
            while (registroslote.Read())
            {
                lotes.Add(Convert.ToString(registroslote.GetInt32(0)));
                tbllote.AddCell(Convert.ToString(registroslote.GetInt32(0)));
                tbllote.AddCell(Convert.ToString(registroslote.GetInt32(1)));
                tbllote.AddCell(Convert.ToString(registroslote.GetDecimal(2)));
                tbllote.AddCell(registroslote.GetString(3));
                tbllote.AddCell(Convert.ToString(registroslote.GetDateTime(4)));
            }
            invproducto.Add(tbllote);

            //Agregar ubicaciones del producto
            Chunk c3 = new Chunk("Ubicaciones de los productos");
            invproducto.Add(c3);
            PdfPTable tblubicacion = new PdfPTable(4);
            tblubicacion.WidthPercentage = 100;
            PdfPCell cabeza2 = new PdfPCell(new Phrase("Ubicaciones de los productos"));
            cabeza2.Colspan = 4;
            cabeza2.HorizontalAlignment = 1;
            tblubicacion.AddCell(cabeza2);
            tblubicacion.AddCell("Id Ubicacion");
            tblubicacion.AddCell("Id Tipo");
            tblubicacion.AddCell("# Bodega");
            tblubicacion.AddCell("# Nivel");
            for(int i = 0; i < lotes.Count(); i++)
            {
                sql = "select * from Ubicar where idtipo="+lotes[i];
                SqlCommand cmdubi = new SqlCommand(sql, con.getConexion());
                SqlDataReader regubi = cmdubi.ExecuteReader();
                if (regubi.Read())
                {
                    tblubicacion.AddCell(Convert.ToString(regubi.GetInt32(0)));
                    tblubicacion.AddCell(Convert.ToString(regubi.GetInt32(1)));
                    tblubicacion.AddCell(regubi.GetString(2));
                    tblubicacion.AddCell(Convert.ToString(regubi.GetInt32(3)));
                }
            }
            
            invproducto.Add(tblubicacion);

            invproducto.Close();
            writer.Close();
            Response.Write("<script>alert('Documento creado con éxito, dirijase a la carpeta Reportes para verlo')</script>");

        }
    }
}