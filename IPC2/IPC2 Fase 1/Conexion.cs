using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Sql;
using System.Data.SqlClient;
using System.Data;

namespace Proyecto201612151
{
    public class Conexion
    {
        public  SqlConnection getConexion()
        {
            try
            {


                string cadena = @"Data Source=(localdb)\Macal;Initial Catalog=proyectoerp;Integrated Security=True";
                SqlConnection cm = new SqlConnection(cadena);
                cm.Open();
                return cm;
            }
            catch (Exception)
            {
                return null;
            }
        }
    }
}