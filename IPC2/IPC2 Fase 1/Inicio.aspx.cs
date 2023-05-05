using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;
using System.Data.Sql;


namespace Proyecto201612151
{
    public partial class Inicio : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            ((TextBox)txtcontra).Text = string.Empty;
            ((TextBox)txtusuario).Text = string.Empty;
        }

        protected void btniniciar_Click(object sender, EventArgs e)
        {
            string paso = "";
            string usuario = "";
            string contra = "";
            bool  seleccionado = false; 
            if (rbadmin.Checked)
            {
                seleccionado = true;
                paso = "Administrador_general";
            }else if (rbusuario.Checked)
            {
                seleccionado = true;
                paso = "Administrador_colaborador";
            }
            if (seleccionado)
            {
                string niterp = "";
                string usuarioerp = "";
                usuario = txtusuario.Text;
                contra = txtcontra.Text;
                string sql = "select * from "+paso+" where usuario='"+usuario+"' and contrasena='"+contra+"'";
                Conexion con = new Conexion();
                SqlCommand cmd = new SqlCommand(sql,con.getConexion());
                SqlDataReader registro= cmd.ExecuteReader();
                if (registro.Read())
                {
                    if (paso == "Administrador_general")
                    {
                        Response.Redirect("Administrador/PrincipalAdministradorGeneral");
                        
                    }else if(paso== "Administrador_colaborador")
                    {
                        niterp = registro.GetString(7);
                        usuarioerp = registro.GetString(0);
                        Session["niterp"] = niterp;
                        Session["idusuario"] = usuarioerp;
                        Response.Redirect("Usuarios/PrincipalAdministrador");
                    }
                }
                else
                {
                    Response.Write("<script>alert('No se pudo iniciar sesión, verifique sus datos y vuelva a intentarlo')</script>");
                }

            }

        }
    }
}