<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="invTransacciones.aspx.cs" Inherits="Proyecto201612151.Usuarios.invTransacciones" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Compra y Venta</title>
</head>
<body>
    <form id="form1" runat="server">
         <div>
        <asp:Button  class="btn btn-danger" ID="Button1" runat="server" Text="Volver" OnClick="Button1_Click" />
        </div>
        <center><h1 class="jumbotron text-center"><br />Transacciones</div> (Ventas)</h1>
        <p>
        <table class="table table-dark">
            <tr>
                <td>
                    <center>
                        Transacciones de salida:<br />
                        Ingrese la descripción de la salida:<br />Ingresar ID Producto:&nbsp;&nbsp;&nbsp;<asp:TextBox ID="txtidproducto" runat="server" Width="126px"></asp:TextBox>
                        &nbsp;&nbsp;<br />
                        <br />Ingresar&nbsp; cantidad:&nbsp;&nbsp;&nbsp;<asp:TextBox type="number" ID="txtcantidad" runat="server" Width="108px"></asp:TextBox>
                        <br />
                        <br />Ingresar cliente:&nbsp;&nbsp;&nbsp;<asp:TextBox ID="txtcliente" runat="server" Width="110px"></asp:TextBox>
                        &nbsp;<br />
                        <br />
                        <asp:Button class="btn btn-secondary" ID="btnsalida" runat="server" Text="Ingresar salida" Width="249px" OnClick="btncomprasaldo_Click"></asp:Button>
                        <br />
                        <br />
                        Dudas al ingresar venta:<br />
                        1.&nbsp; El producto ya debe estar registrado en el sistema<br />
                        2.&nbsp; El cliente debe estar registrado en el sistema<br />
                        3. La cantidad que se desa sacar debe estar disponible para salir.<br />
                        <br />
                    </center>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
