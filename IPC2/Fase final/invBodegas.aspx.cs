using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;
using System.Data.Sql;

namespace Proyecto201612151.Usuarios
{
    public partial class invBodegas : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Response.Redirect("moduloInventario");
        }

        protected void btnmostrar_Click(object sender, EventArgs e)
        {
            string niterp = this.Session["niterp"].ToString(); 
            Conexion con = new Conexion();
            string sql = "select idbodega as 'Identificador',nombre as 'Nombre',descripcion as 'Descripción',direccion as 'Dirección' from Bodegas where id_erp='" + niterp + "'";
            SqlDataAdapter cmd = new SqlDataAdapter(sql, con.getConexion());
            DataTable data = new DataTable();
            cmd.Fill(data);
            tblbodegas.DataSource = data;
            tblbodegas.DataBind();

            sql = "select numpasillo as 'Numero',largo as 'Largo', ancho as 'Ancho', idbodega as 'Id Bodega' from Pasillos where niterp='" + niterp + "'";
            SqlDataAdapter cmdpasillo = new SqlDataAdapter(sql, con.getConexion());
            DataTable pasillo = new DataTable();
            cmdpasillo.Fill(pasillo);
            tblpasillo.DataSource = pasillo;
            tblpasillo.DataBind();

            sql = "select idestante as 'Identificador',letra as 'Letra',largo as 'Largo',ancho as 'Ancho',altura as 'Altura',idpasillo as 'Pasillo' from Estantes where niterp='"+niterp+"'";
            SqlDataAdapter cmdestante = new SqlDataAdapter(sql, con.getConexion());
            DataTable dataestante = new DataTable();
            cmdestante.Fill(dataestante);
            tblestante.DataSource = dataestante;
            tblestante.DataBind();

            sql = " select numnivel as 'Numero Nivel',largo as 'Largo', ancho as 'Ancho',altura as 'Altura',idestante as 'Estante' from Niveles where niterp='" + niterp + "'";
            SqlDataAdapter cmdnivel = new SqlDataAdapter(sql, con.getConexion());
            DataTable datanivel = new DataTable();
            cmdnivel.Fill(datanivel);
            tblnivel.DataSource = datanivel;
            tblnivel.DataBind();
        }

        protected void btnlimpiarbodega_Click(object sender, EventArgs e)
        {
            txtidbodega.Text = "";
            txtnombrebodega.Text = "";
            txtdescripcionbodega.Text = "";
            txtdireccionbodega.Text = "";
        }

        protected void btnlimpiarpasillo_Click(object sender, EventArgs e)
        {
            txtnumpasillo.Text = "";
            txtlartopasillo.Text = "";
            txtanchopasillo.Text = "";
            txtbodegapasillo.Text = "";
        }

        protected void btnlimpiarestante_Click(object sender, EventArgs e)
        {
            txtletraestante.Text = "";
            txtlargoestante.Text = "";
            txtanchoestante.Text = "";
            txtaltoestante.Text = "";
            txtpasilloestante.Text = "";
        }

        protected void btnlimpiarnivel_Click(object sender, EventArgs e)
        {
            txtnumeronivel.Text = "";
            txtaltonivel.Text = "";
            txtestantenivel.Text = "";
        }

        protected void btnbuscar_Click(object sender, EventArgs e)
        {
            string idbodega = txtbuscar.Text;
            string niterp = this.Session["niterp"].ToString();
            Conexion con = new Conexion();
            string sql = "select idbodega as 'Identificador',nombre as 'Nombre',descripcion as 'Descripción',direccion as 'Dirección' from Bodegas where id_erp='"+niterp+"' and idbodega='"+idbodega+"'";
            SqlDataAdapter cmd = new SqlDataAdapter(sql, con.getConexion());
            DataTable data = new DataTable();
            cmd.Fill(data);
            tblbodegas.DataSource = data;
            tblbodegas.DataBind();
        }

        protected void btningresarbodega_Click(object sender, EventArgs e)
        {
            string idbodega = txtidbodega.Text;
            string nombre = txtnombrebodega.Text;
            string descripcion = txtdescripcionbodega.Text;
            string direccion = txtdireccionbodega.Text;
            bool paso = true;
            string niterp = this.Session["niterp"].ToString();
            Conexion con = new Conexion();
            string sql = "select * from Bodegas where id_erp='"+niterp+"' and idbodega='"+idbodega+"'";
            SqlCommand cmd = new SqlCommand(sql, con.getConexion());
            SqlDataReader registro = cmd.ExecuteReader();
            if (registro.Read())
            {
                paso = false;
            }
            if (paso == true)
            {
                try
                {
                    sql = "insert into Bodegas values('" + idbodega + "','" + nombre + "','" + descripcion + "','" + direccion + "','"+niterp+"')";
                    SqlCommand com = new SqlCommand(sql, con.getConexion());
                    com.ExecuteNonQuery();
                    Response.Write("<script>alert('Bodega guardada con éxito')</script>");
                }
                catch (Exception ex)
                {
                    throw ex;
                }
            }
            else
            {
                Response.Write("<script>alert('El id de bodega ingresado ya existe en el sistema.')</script>");
            }
            
        }

        protected void btningresarpasillo_Click(object sender, EventArgs e)
        {
            string num = txtnumpasillo.Text;
            string largo = txtlartopasillo.Text;
            string ancho = txtanchopasillo.Text;
            string bodega = txtbodegapasillo.Text;
            string niterp = this.Session["niterp"].ToString();
            bool paso1 = false;
            bool paso2 = true;
            //Revisar primero que la bodega exista
            Conexion con = new Conexion();
            string sql = "select * from Bodegas where idbodega='"+bodega+"'";
            SqlCommand cmd = new SqlCommand(sql, con.getConexion());
            SqlDataReader registro = cmd.ExecuteReader();
            if (registro.Read())
            {
                paso1 = true;
            }
            if (paso1 == true)
            {
                //Revisar que el pasillo no exista
                sql = "select * from Pasillos where numpasillo='" + num + "' and niterp='" + niterp + "' and idbodega='" + bodega + "'";
                SqlCommand cmdpass = new SqlCommand(sql, con.getConexion());
                SqlDataReader reg = cmdpass.ExecuteReader();
                if (reg.Read())
                {
                    paso2 = false;
                }
                if (paso2 == true)
                {
                    //Ingresar el pasillo al ERP
                    sql = "insert into Pasillos (numpasillo,largo,ancho,idbodega,niterp) values('" + num + "','" + largo + "','" + ancho + "','" + bodega + "','" + niterp + "')";
                    SqlCommand ingreso = new SqlCommand(sql, con.getConexion());
                    ingreso.ExecuteNonQuery();
                    Response.Write("<script>alert('El pasillo fue creado con éxito')</script>");
                }
                else
                {
                    Response.Write("<script>alert('El pasillo ya existe en la bodega indicada, vuelva a intentarlo')</script>");
                }
            }
            else
            {
                Response.Write("<script>alert('La bodega ingresada no existe en el registro')</script>");
            }
        }

        protected void btningresarestante_Click(object sender, EventArgs e)
        {
            string letra = txtletraestante.Text;
            string largo = txtlargoestante.Text;
            string ancho = txtanchoestante.Text;
            string alto = txtaltoestante.Text;
            string pasillo = txtpasilloestante.Text;
            string niterp= this.Session["niterp"].ToString();
            bool paso1 = false;
            bool paso2 = true;
            //Revisar que exista el pasillo en el nit
            Conexion con = new Conexion();
            string sql = "select * from Pasillos where numpasillo='" + pasillo + "' and niterp='"+niterp+"'";
            SqlCommand cmd = new SqlCommand(sql, con.getConexion());
            SqlDataReader registro = cmd.ExecuteReader();
            if (registro.Read())
            {
                paso1 = true;
            }
            if (paso1 == true)
            {
                //Revisar que la letra no exista en el pasillo
                sql = "select * from Estantes where letra='" + letra + "' and idpasillo='" + pasillo + "' and niterp='" + niterp + "'";
                SqlCommand cmdpass = new SqlCommand(sql, con.getConexion());
                SqlDataReader reg = cmdpass.ExecuteReader();
                if (reg.Read())
                {
                    paso2 = false;
                }
                if (paso2 == true)
                {
                    //Ingresar el estante del ERP
                    sql = "insert into Estantes (letra,largo,ancho,altura,idpasillo,niterp) values('" + letra + "','" + largo + "','" + ancho + "','" + alto + "','" + pasillo + "','"+niterp+"')";
                    SqlCommand ingreso = new SqlCommand(sql, con.getConexion());
                    ingreso.ExecuteNonQuery();
                    Response.Write("<script>alert('El estante fue creado con éxito')</script>");
                }
                else
                {
                    Response.Write("<script>alert('La letra indicada ya existe en el pasillo, revise y vuelva a intentarlo')</script>");
                }
            }
            else
            {
                Response.Write("<script>alert('El pasillo no existe en sus pasillos, vuelva a intentarlo')</script>");
            }

        }

        protected void btningresarnivel_Click(object sender, EventArgs e)
        {
            string nivel = txtnumeronivel.Text;
            string alto = txtaltonivel.Text;
            string estante = txtestantenivel.Text;
            string niterp = this.Session["niterp"].ToString();
            string largo = "";
            string idestante = "";
            string ancho = "";
            bool paso1 = false;
            bool paso2 = true;
            Conexion con = new Conexion();
            string sql = "select * from Estantes where letra='" + estante + "' and niterp='" + niterp + "'";
            SqlCommand cmd = new SqlCommand(sql, con.getConexion());
            SqlDataReader registro = cmd.ExecuteReader();
            if (registro.Read())
            {
                largo =Convert.ToString(registro.GetDecimal(2));
                ancho = Convert.ToString(registro.GetDecimal(3));
                idestante = Convert.ToString(registro.GetInt32(0));
                paso1 = true;
            }
            if (paso1 == true)
            {
                //Revisar que el nivel no exista
                sql = "select * from Niveles where numnivel='" + nivel + "' and idestante='" + idestante + "' and niterp='" + niterp + "'";
                SqlCommand cmdpass = new SqlCommand(sql, con.getConexion());
                SqlDataReader reg = cmdpass.ExecuteReader();
                if (reg.Read())
                {
                    paso2 = false;
                }
                if (paso2 == true)
                {
                    //Ingresar el nivel
                    sql = "insert into Niveles (numnivel,largo,ancho,altura,idestante,niterp) values('" + nivel + "','" + largo + "','" + ancho + "','" + alto + "','" + idestante + "','" + niterp + "')";
                    SqlCommand ingreso = new SqlCommand(sql, con.getConexion());
                    ingreso.ExecuteNonQuery();
                    Response.Write("<script>alert('El estante fue creado con éxito')</script>");
                }
                else
                {
                    Response.Write("<script>alert('La letra indicada ya existe en el pasillo, revise y vuelva a intentarlo')</script>");
                }
            }
            else
            {
                Response.Write("<script>alert('El estante ingresado no coincide con los registrados, vuelva a intentarlo')</script>");
            }
        }
    }
}