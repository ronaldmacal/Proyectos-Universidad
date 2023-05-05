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
    public partial class costosLotesySaldos : System.Web.UI.Page
    {
        public static int n=0;
        protected void Page_Load(object sender, EventArgs e)
        {
            string nit = this.Session["niterp"].ToString();
            string sql = "select codigo as 'Código',codigo_barras as 'Cod. Barras',nombre as 'Nombre',descripcion as 'Descripción',descripcion_presentacion as 'Presentación', abreviatura_presentacion as 'Abrev. Presentacion',descripcion_clasificacion as 'Clasificacion',abreviatura_clasificacion as 'Abrev. Clasificación' from Productos_registro where id_erp='" + nit + "'";
            Conexion con = new Conexion();
            SqlDataAdapter cmd = new SqlDataAdapter(sql, con.getConexion());
            DataTable data = new DataTable();
            cmd.Fill(data);
            tblclientes.DataSource = data;
            tblclientes.DataBind();
        }
        protected void Button1_Click(object sender, EventArgs e)
        {
            Response.Redirect("moduloInventario");
        }

        public static bool verificarbodega(string bodega,string pasillo,string estante,string nivel,string nit)
        {
            
            bool paso = false;
            //Pasos para verificar la bodega
            Conexion con = new Conexion();
            bool pasobodega = false;
            bool pasopasillo = false;
            bool pasoestante = false;
            bool pasonivel = false;
            string sql = "select * from Bodegas where idbodega='"+bodega+"' and id_erp='"+nit+"'";
            SqlCommand cmd = new SqlCommand(sql, con.getConexion());
            SqlDataReader registro = cmd.ExecuteReader();
            if (registro.Read())
            {
                pasobodega = true;
            }
            if (pasobodega == true)
            {
                n = 1;
                string identificadorpasillo = "";
                sql = "select * from Pasillos where numpasillo="+pasillo+" and idbodega='"+bodega+"' and niterp='"+nit+"'";
                SqlCommand cmdpas = new SqlCommand(sql, con.getConexion());
                SqlDataReader reg = cmdpas.ExecuteReader();
                if (reg.Read())
                {
                    pasopasillo = true;
                    identificadorpasillo = Convert.ToString(reg.GetInt32(1));
                }
                if (pasopasillo == true)
                {
                    n = 2;
                    sql = "select * from Estantes where idpasillo="+identificadorpasillo+" and niterp='"+nit+"'";
                    SqlCommand cmdestante = new SqlCommand(sql, con.getConexion());
                    SqlDataReader regestante = cmdestante.ExecuteReader();
                    if (regestante.Read())
                    {
                        pasoestante = true;
                    }
                    if (pasoestante == true)
                    {
                        n = 3;
                        sql = "select * from Niveles where numnivel="+nivel+" and idestante="+estante+" and niterp='"+nit+"'";
                        SqlCommand cmdnivel = new SqlCommand(sql, con.getConexion());
                        SqlDataReader regnivel = cmdnivel.ExecuteReader();
                        if (regnivel.Read())
                        {
                            pasonivel = true;
                            n = 4;
                        }
                    }
                }
            }
            if(pasobodega==true && pasoestante==true && pasonivel==true && pasopasillo == true)
            {
                n = 5;
                paso = true;
            }
            return paso;
        }

        public static bool proveedor(string proveedor,string nit)
        {
            bool paso = false;
            Conexion con = new Conexion();
            string sql = "select * from Proveedor where nit_proveedor='"+proveedor+"' and id_erp='"+nit+"'";
            SqlCommand cmd = new SqlCommand(sql, con.getConexion());
            SqlDataReader registro = cmd.ExecuteReader();
            if (registro.Read())
            {
                paso = true;
            }
            return paso;
        }
        public static bool producto(string producto,string nit)
        {
            bool paso = false;
            Conexion con = new Conexion();
            string sql = "select * from Productos_registro where codigo='"+producto+"' and id_erp='"+nit+"'";
            SqlCommand cmd = new SqlCommand(sql, con.getConexion());
            SqlDataReader registro = cmd.ExecuteReader();
            if (registro.Read())
            {
                paso = true;
            }
            return paso;
        }
        protected void btncompralote_Click(object sender, EventArgs e)
        {
            bool guardar = false;
            string nit= this.Session["niterp"].ToString();
            string bodega = txtbodega.Text;
            string pasillo = txtpasillo.Text;
            string estante = txtestante.Text;
            string nivel = txtnivel.Text;
            string nitproveedor = "";
            string idproducto = "";
            bool pasoproducto = false;
            bool pasopro = false;
            bool paso = verificarbodega(bodega,pasillo,estante,nivel,nit);
            txtsaldocantidad.Text =Convert.ToString(n);
            if (paso == true)
            {
                nitproveedor = txtproveedor.Text;
                pasopro = proveedor(nitproveedor, nit);
                if (pasopro == true)
                {
                    idproducto = txtloteidproducto.Text;
                    pasoproducto = producto(idproducto, nit);
                    if (pasoproducto == true)
                    {
                        guardar = true;
                    }
                }
                else
                {
                    Response.Write("<script>alert('El proveedor no está registrado en su ERP')</script>");
                }
            }
            else
            {
                Response.Write("<script>alert('Existe un error en el ingreso de datos de ubicación. Revise la bodega, pasillo, estante o nivel ingresados!!')</script>");
            }
            
            if (guardar == true)
            {
                string fechahoy =txtlotefecha.Text;
                string tiposalida = dpwtipoingreso.SelectedItem.Value;
                //Desde aquí guardar saldo
                //Agregar a Lotes y Saldos
                string sql = "insert into LotesySaldos (idproducto,cantidad,costopromedio,tipo,fecha) values('" + idproducto + "'," + txtlotecantidad.Text + "," + txtlotecosto.Text + ",'Lote-"+tiposalida+"','" + fechahoy + "')";
                txtlotecosto.Text = sql;
                Conexion con = new Conexion();
                SqlCommand cmd = new SqlCommand(sql, con.getConexion());
                cmd.ExecuteNonQuery();

                //Buscar el saldo anterior antes de ingresarlo
                sql = "SELECT TOP 1 * FROM LotesySaldos ORDER BY idtipo DESC";
                SqlCommand leer = new SqlCommand(sql, con.getConexion());
                SqlDataReader registro = leer.ExecuteReader();
                string idtipo = "";
                if (registro.Read())
                {
                    idtipo = Convert.ToString(registro.GetInt32(0));
                }
                //Encontrar el nivel primero
                sql = "select * from Niveles where numnivel=" + nivel + " and idestante=" + estante + " and niterp='" + nit + "'";
                SqlCommand cmdquenivel = new SqlCommand(sql, con.getConexion());
                SqlDataReader registroquenivel = cmdquenivel.ExecuteReader();
                string idnivel = "";
                if (registroquenivel.Read())
                {
                    idnivel = Convert.ToString(registroquenivel.GetInt32(0));
                }

                //Agregar Ubicacion
                sql = "insert into Ubicar (idtipo,idbodega,idnivel) values(" + idtipo + ",'" + bodega + "'," + idnivel + ")";
                SqlCommand ubicar = new SqlCommand(sql, con.getConexion());
                ubicar.ExecuteNonQuery();
                Response.Write("<script>alert('Nuevo Lote creado con éxito y guardado en la bodega!!')</script>");

            }
        }

        protected void btncomprasaldo_Click(object sender, EventArgs e)
        {
            bool guardar = false;
            string nit = this.Session["niterp"].ToString();
            string bodega = txtbodega.Text;
            string pasillo = txtpasillo.Text;
            string estante = txtestante.Text;
            string nivel = txtnivel.Text;
            string idproducto = "";
            string nitproveedor = "";
            bool paso = verificarbodega(bodega, pasillo, estante, nivel,nit);
            if (paso == true)
            {
                nitproveedor = txtproveedor.Text;
                bool pasopro=proveedor(nitproveedor,nit);
                if (pasopro == true)
                {
                    idproducto = txtsaldoidproducto.Text;
                    bool pasoproducto = producto(idproducto, nit);
                    if (pasoproducto == true)
                    {
                        guardar = true;
                    }
                }
                else
                {
                    Response.Write("<script>alert('El proveedor no está registrado en su ERP')</script>");
                }
            }
            else
            {
                Response.Write("<script>alert('Existe un error en el ingreso de datos de ubicación. Revise la bodega, pasillo, estante o nivel ingresados!!')</script>");
            }
            if (guardar == true)
            {
                try
                {
                    string fechahoy = DateTime.Now.ToString("yyyy-MM-dd");
                    //Desde aquí guardar saldo
                    //Agregar a Lotes y Saldos
                    string sql = "insert into LotesySaldos (idproducto,cantidad,costopromedio,tipo,fecha) values('"+ idproducto + "',"+txtsaldocantidad.Text+","+txtsaldocosto.Text+",'Saldo','"+fechahoy+"')";
                    Conexion con = new Conexion();
                    SqlCommand cmd = new SqlCommand(sql, con.getConexion());
                    cmd.ExecuteNonQuery();

                    //Buscar el saldo anterior antes de ingresarlo
                    sql = "SELECT TOP 1 * FROM LotesySaldos ORDER BY idtipo DESC";
                    SqlCommand leer = new SqlCommand(sql, con.getConexion());
                    SqlDataReader registro = leer.ExecuteReader();
                    string idtipo = "";
                    if (registro.Read())
                    {
                        idtipo = Convert.ToString(registro.GetInt32(0));
                    }

                    //Encontrar el nivel primero
                    sql = "select * from Niveles where numnivel="+nivel+" and idestante="+estante+" and niterp='"+nit+"'";
                    SqlCommand cmdquenivel = new SqlCommand(sql, con.getConexion());
                    SqlDataReader registroquenivel = cmdquenivel.ExecuteReader();
                    string idnivel = "";
                    if (registroquenivel.Read())
                    {
                        idnivel = Convert.ToString(registroquenivel.GetInt32(0));
                    }
                    //Agregar Ubicacion
                    sql = "insert into Ubicar (idtipo,idbodega,idnivel) values ("+idtipo+",'"+bodega+"',"+idnivel+")";
                    SqlCommand ubicar = new SqlCommand(sql, con.getConexion());
                    ubicar.ExecuteNonQuery();
                    Response.Write("<script>alert('Nuevo Saldo creado con éxito!!')</script>");
                }
                catch (Exception ex)
                {
                    throw ex;
                }
            }


        }
    }
}