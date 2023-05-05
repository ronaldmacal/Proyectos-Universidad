
<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Inicio.aspx.cs" Inherits="Proyecto201612151.Inicio" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Inicio de sesion</title>
</head>
<body style="height: 234px">
    <form id="form1" runat="server">
        <center><div ><h1 class="jumbotron text-center" >Inicio de sesión</h1></div>
            Usuario:&nbsp;
            <asp:TextBox ID="txtusuario" runat="server"></asp:TextBox>
            <br />
            <br />
            Contraseña:&nbsp;
            <asp:TextBox ID="txtcontra" runat="server"></asp:TextBox>
            <br />
            <asp:RadioButton ID="rbadmin" runat="server" GroupName="opcionesrb" Text="Administrador general" />
            <br />
            <asp:RadioButton ID="rbusuario" runat="server" GroupName="opcionesrb" Text="Usuario ERP" />
            <br />
            <asp:Button class="btn btn-outline-primary" ID="btniniciar" runat="server" Text="Iniciar " Width="75px" OnClick="btniniciar_Click" />
            &nbsp;
            <asp:Button class="btn btn-outline-primary" ID="btnborrar" runat="server" OnClick="Button1_Click" Text="Limpiar" Width="75px" />
            <br />
            <br />
            </form>
    </center>
    <center><div id="copyright">Copyright&copy; 2020 - Página creada por 201612151</div></center>
</body>
</html>
