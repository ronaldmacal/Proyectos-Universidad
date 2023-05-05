<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="moduloInventario.aspx.cs" Inherits="Proyecto201612151.Usuarios.moduloInventario" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Modulo de inventario</title>
</head>
<body>
    <form id="form1" runat="server">
        
        <div>
        <asp:Button  class="btn btn-danger" ID="Button1" runat="server" Text="Volver" OnClick="Button1_Click" />
        </div>
        <center><h1 class="jumbotron text-center"><br />Modulo de Inventarios</div></h1>
        <p>
        <asp:Button class="btn btn-dark" ID="btnbodegas" runat="server" Text="Manejo de bodegas" OnClick="btnbodegas_Click" Width="338px" />
            <p>
        <asp:Button class="btn btn-dark" ID="btncostos" runat="server" Text="Crear Lotes y Saldos (compras)" OnClick="btncostos_Click" Width="338px" />
            <p>
        <asp:Button class="btn btn-dark" ID="btntransacciones" runat="server" Text="Transacciones (ventas)" OnClick="btntransacciones_Click" Width="339px" />
            <p>
        <asp:Button class="btn btn-dark" ID="btntransferencias" runat="server" Text="Transferencias entre bodegas" OnClick="btntransferencias_Click" Width="335px" />
            <p>
        <asp:Button class="btn btn-dark" ID="btnreportes" runat="server" Text="Hacer Reportes" OnClick="btnreportes_Click" Width="335px" />
    </form>

    <center><div id="copyright">Copyright&copy; 2020 - Página creada por 201612151</div></center>

</body>
</html>
