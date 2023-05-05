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
    public partial class invTransacciones : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Response.Redirect("moduloInventario");
        }

        protected void btncomprasaldo_Click(object sender, EventArgs e)
        {
            string idproducto = txtidproducto.Text;
            int cantidad =Convert.ToInt32( txtcantidad.Text);
            string cliente = txtcliente.Text;
            bool siexistecliente = false;
            string nit= this.Session["niterp"].ToString();
            //Validar que el cliente exista
            Conexion con = new Conexion();
            string sql = "select * from Clientes where id_erp='"+nit+"' and nit='"+cliente+"'";
            SqlCommand buscarcliente = new SqlCommand(sql, con.getConexion());
            SqlDataReader registroscliente = buscarcliente.ExecuteReader();
            if (registroscliente.Read())
            {
                siexistecliente = true;
            }
            if (siexistecliente == true)
            {
                //Verificar si es un saldo y extraerlo
                bool essaldo = false;
                int cantidadexistencia = 0;
                int ubicacion = 0;
                sql = "select * from LotesySaldos where tipo='Saldo' and idproducto='"+idproducto+"'";
                SqlCommand saldo = new SqlCommand(sql, con.getConexion());
                SqlDataReader registrosaldo = saldo.ExecuteReader();
                if (registrosaldo.Read())
                {
                    cantidadexistencia = registrosaldo.GetInt32(2);
                    ubicacion = registrosaldo.GetInt32(0);
                    essaldo = true;
                }
                if (essaldo == true)
                {
                    if (cantidad <= cantidadexistencia)
                    {
                        int resta = cantidadexistencia - cantidad;
                        sql = "update LotesySaldos set cantidad=" + resta + " where idtipo=" + ubicacion + "";
                        try {
                            SqlCommand actualizarsaldo = new SqlCommand(sql, con.getConexion());
                            actualizarsaldo.ExecuteNonQuery();
                            Response.Write("<script>alert('Venta realizada con éxito, nueva cantidad disponible: "+resta+"')</script>");
                            if (resta == 0)
                            {
                                string borrarubicacion = "delete from Ubicar where idtipo="+ubicacion;
                                string borrarlote = "delete from LotesySaldos where idtipo="+ubicacion;
                                SqlCommand delubicacion = new SqlCommand(borrarubicacion, con.getConexion());
                                delubicacion.ExecuteNonQuery();
                                SqlCommand delsaldo = new SqlCommand(borrarlote, con.getConexion());
                                delsaldo.ExecuteNonQuery();
                            }
                        }
                        catch (Exception ex)
                        {
                            throw ex;
                        }
                    }
                    else
                    {
                        Response.Write("<script>alert('No hay cantidad suficiente en existencia para vender, verifique la cantidad de producto. Cantidad de existencia " + cantidadexistencia + "')</script>");
                    }
                }

                //Si no es saldo verificar el lote y método de extraccion
                if (essaldo == false)
                {
                    bool esu = false;
                    sql = "select * from LotesySaldos where tipo='Lote-U' and idproducto='" + idproducto + "'";
                    SqlCommand loteu = new SqlCommand(sql, con.getConexion());
                    SqlDataReader reglote = loteu.ExecuteReader();
                    if (reglote.Read())
                    {
                        esu = true;
                    }
                    if (esu == true)
                    {
                        sql = "select * from LotesySaldos where tipo='Lote-U' and idproducto='" + idproducto+"' and fecha=(select MAX(fecha) from LotesySaldos)";
                    }
                    else
                    {
                        sql = "select * from LotesySaldos where tipo='Lote-P' and idproducto='" + idproducto + "' and fecha=(select MIN(fecha) from LotesySaldos)";
                    }
                    SqlCommand extlote = new SqlCommand(sql, con.getConexion());
                    SqlDataReader regext = extlote.ExecuteReader();
                    if (regext.Read())
                    {
                        cantidadexistencia = regext.GetInt32(2);
                        ubicacion = regext.GetInt32(0);
                    }
                    if (cantidad <= cantidadexistencia)
                    {
                        int resta = cantidadexistencia - cantidad;
                        sql = "update LotesySaldos set cantidad=" + resta + " where idtipo=" + ubicacion + "";
                        try
                        {
                            SqlCommand actualizarsaldo = new SqlCommand(sql, con.getConexion());
                            actualizarsaldo.ExecuteNonQuery();
                            Response.Write("<script>alert('Venta realizada con éxito, nueva cantidad disponible: " + resta + "')</script>");
                            if (resta == 0)
                            {
                                string borrarubicacion = "delete from Ubicar where idtipo=" + ubicacion;
                                string borrarlote = "delete from LotesySaldos where idtipo=" + ubicacion;
                                SqlCommand delubicacion = new SqlCommand(borrarubicacion, con.getConexion());
                                delubicacion.ExecuteNonQuery();
                                SqlCommand delsaldo = new SqlCommand(borrarlote, con.getConexion());
                                delsaldo.ExecuteNonQuery();
                            }
                        }
                        catch (Exception ex)
                        {
                            throw ex;
                        }
                    }
                    else
                    {
                        Response.Write("<script>alert('No hay cantidad suficiente en existencia para vender, verifique la cantidad de producto. Cantidad de existencia " + cantidadexistencia + "')</script>");

                    }
                }
            }
            else
            {
                Response.Write("<script>alert('El cliente de destino no esta registrado en el sistema')</scrip>");
            }
        }
    }
}