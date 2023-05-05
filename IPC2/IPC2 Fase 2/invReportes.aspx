<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="invReportes.aspx.cs" Inherits="Proyecto201612151.Usuarios.invReportes" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Reportes PDF</title>
</head>
<body>
    <form id="form1" runat="server">
         <div>
        <asp:Button  class="btn btn-danger" ID="Button1" runat="server" Text="Volver" OnClick="Button1_Click" />
        </div>
        <center><h1 class="jumbotron text-center"><br />Reportes</div></h1>
        <p>
        <asp:Label ID="Label1" runat="server" Text="Inventario de bodega"></asp:Label>
        <br />Ingresar número de bodega:&nbsp; <asp:TextBox ID="txtbodega" runat="server" Width="133px"></asp:TextBox>
         <br />
        <asp:Button  class="btn btn-secondary" ID="btninvbodega" runat="server" Text="Mostrar reporte" OnClick="btninvbodega_Click"></asp:Button>
             <br />
             <br />
            <br />
        <asp:Label ID="Label2" runat="server" Text="Inventario de producto"></asp:Label>
        <br />Ingresar codigo producto:&nbsp; <asp:TextBox ID="txtproducto" runat="server" Width="133px"></asp:TextBox>
         <br />
        <asp:Button  class="btn btn-secondary" ID="btninvproducto" runat="server" Text="Mostrar reporte" OnClick="btninvproducto_Click"></asp:Button>
             <br />

    </form>
</body>
</html>
