using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Proyecto201612151.Usuarios
{
    public partial class moduloInventario : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Response.Redirect("PrincipalAdministrador");
        }

        protected void btnbodegas_Click(object sender, EventArgs e)
        {
            Response.Redirect("invBodegas");
        }

        protected void btntransacciones_Click(object sender, EventArgs e)
        {
            Response.Redirect("invTransacciones");
        }

        protected void btntransferencias_Click(object sender, EventArgs e)
        {
            Response.Redirect("invTransferencias");
        }

        protected void btnreportes_Click(object sender, EventArgs e)
        {
            Response.Redirect("invReportes");
        }

        protected void btncostos_Click(object sender, EventArgs e)
        {
            Response.Redirect("costosLotesySaldos");
        }
    }
}