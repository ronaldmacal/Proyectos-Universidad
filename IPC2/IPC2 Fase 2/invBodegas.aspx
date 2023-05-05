
<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="invBodegas.aspx.cs" Inherits="Proyecto201612151.Usuarios.invBodegas" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Manejo de Bodegas</title>
    <style type="text/css">
        .auto-style1 {
            width: 83%;
            height: 206px;
            overflow: scroll;
        }
        .auto-style2 {
            width: 83%;
            height: 208px;
            overflow: scroll;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div>
        <asp:Button  class="btn btn-danger" ID="Button1" runat="server" Text="Volver" OnClick="Button1_Click" />
        </div>
        <center><h1 class="jumbotron text-center"><br />Manejo de bodegas</div></h1></center>
        <table class="table table-dark">
        <tr>
        <td colspan="2">
        <center>
            <asp:Label ID="Label1" runat="server" Text="Listado general de bodegas con sus ubicaciones"></asp:Label>
            <br />
            <br />
            Ingrese ID bodega:&nbsp; <asp:TextBox type="number" ID="txtbuscar" runat="server" Width="130px"></asp:TextBox>
            <br />
            <br />
            <asp:Button class="btn btn-secondary" ID="btnmostrar" runat="server" Text="Mostrar Todos" OnClick="btnmostrar_Click"></asp:Button>
            &nbsp;
            <asp:Button class="btn btn-secondary" ID="btnbuscar" runat="server" Text="Buscar" OnClick="btnbuscar_Click" />
            <br />
            &nbsp;Bodegas
            <div class="auto-style1">
                <asp:GridView ID="tblbodegas" runat="server" BackColor="White" BorderColor="#999999" BorderStyle="Solid" BorderWidth="1px" CellPadding="3" ForeColor="Black" GridLines="Vertical" Width="424px">
                    <AlternatingRowStyle BackColor="#CCCCCC" />
                    <FooterStyle BackColor="#CCCCCC" />
                    <HeaderStyle BackColor="Black" Font-Bold="True" ForeColor="White" />
                    <PagerStyle BackColor="#999999" ForeColor="Black" HorizontalAlign="Center" />
                    <SelectedRowStyle BackColor="#000099" Font-Bold="True" ForeColor="White" />
                    <SortedAscendingCellStyle BackColor="#F1F1F1" />
                    <SortedAscendingHeaderStyle BackColor="#808080" />
                    <SortedDescendingCellStyle BackColor="#CAC9C9" />
                    <SortedDescendingHeaderStyle BackColor="#383838" />
                </asp:GridView>
                <br />
            </div>
            Pasillos<br />
            <div class="auto-style2">
                <asp:GridView ID="tblpasillo" runat="server" BackColor="White" BorderColor="#999999" BorderStyle="Solid" BorderWidth="1px" CellPadding="3" ForeColor="Black" GridLines="Vertical" Width="424px">
                    <AlternatingRowStyle BackColor="#CCCCCC" />
                    <FooterStyle BackColor="#CCCCCC" />
                    <HeaderStyle BackColor="Black" Font-Bold="True" ForeColor="White" />
                    <PagerStyle BackColor="#999999" ForeColor="Black" HorizontalAlign="Center" />
                    <SelectedRowStyle BackColor="#000099" Font-Bold="True" ForeColor="White" />
                    <SortedAscendingCellStyle BackColor="#F1F1F1" />
                    <SortedAscendingHeaderStyle BackColor="#808080" />
                    <SortedDescendingCellStyle BackColor="#CAC9C9" />
                    <SortedDescendingHeaderStyle BackColor="#383838" />
                </asp:GridView>
                </div>
                Estantes<br />
                <div class="auto-style1">
                <asp:GridView ID="tblestante" runat="server" BackColor="White" BorderColor="#999999" BorderStyle="Solid" BorderWidth="1px" CellPadding="3" ForeColor="Black" GridLines="Vertical" Width="424px">
                    <AlternatingRowStyle BackColor="#CCCCCC" />
                    <FooterStyle BackColor="#CCCCCC" />
                    <HeaderStyle BackColor="Black" Font-Bold="True" ForeColor="White" />
                    <PagerStyle BackColor="#999999" ForeColor="Black" HorizontalAlign="Center" />
                    <SelectedRowStyle BackColor="#000099" Font-Bold="True" ForeColor="White" />
                    <SortedAscendingCellStyle BackColor="#F1F1F1" />
                    <SortedAscendingHeaderStyle BackColor="#808080" />
                    <SortedDescendingCellStyle BackColor="#CAC9C9" />
                    <SortedDescendingHeaderStyle BackColor="#383838" />
                </asp:GridView>
                </div>
                Niveles<br />
                <div class="auto-style1">
                <asp:GridView ID="tblnivel" runat="server" BackColor="White" BorderColor="#999999" BorderStyle="Solid" BorderWidth="1px" CellPadding="3" ForeColor="Black" GridLines="Vertical" Width="424px">
                    <AlternatingRowStyle BackColor="#CCCCCC" />
                    <FooterStyle BackColor="#CCCCCC" />
                    <HeaderStyle BackColor="Black" Font-Bold="True" ForeColor="White" />
                    <PagerStyle BackColor="#999999" ForeColor="Black" HorizontalAlign="Center" />
                    <SelectedRowStyle BackColor="#000099" Font-Bold="True" ForeColor="White" />
                    <SortedAscendingCellStyle BackColor="#F1F1F1" />
                    <SortedAscendingHeaderStyle BackColor="#808080" />
                    <SortedDescendingCellStyle BackColor="#CAC9C9" />
                    <SortedDescendingHeaderStyle BackColor="#383838" />
                </asp:GridView>
                </div>
                <br />
        </center>
    </td>
  </tr>
  <tr>
    <td>
        <center>
            <asp:Label ID="Label2" runat="server" Text="Crear nueva bodega:"></asp:Label>
            <br />Ingresar ID:&nbsp; <asp:TextBox ID="txtidbodega" runat="server"></asp:TextBox>
            <br />
            <br />Nombre de bodega:&nbsp; <asp:TextBox ID="txtnombrebodega" runat="server" Width="170px"></asp:TextBox>
            <br />
            <br />Descripción bodega:&nbsp;  <asp:TextBox ID="txtdescripcionbodega" runat="server" Width="215px"></asp:TextBox>
            <br />
            <br />Dirección: &nbsp; <asp:TextBox ID="txtdireccionbodega" runat="server"></asp:TextBox>
            <br />
            <br /><asp:Button class="btn btn-secondary" ID="btningresarbodega" runat="server" Text="Ingresar" OnClick="btningresarbodega_Click"></asp:Button>&nbsp; &nbsp; <asp:Button class="btn btn-secondary" ID="btnlimpiarbodega" runat="server" Text="Limpiar" OnClick="btnlimpiarbodega_Click"></asp:Button>

        </center> 
    </td>
    <td>
       <center>
            <asp:Label ID="Label3" runat="server" Text="Crear nuevo pasillo:"></asp:Label>
            <br />Ingresar Pasillo:&nbsp; <asp:TextBox ID="txtnumpasillo" runat="server"></asp:TextBox>
            <br />
            <br />Ingresar Largo:&nbsp; <asp:TextBox ID="txtlartopasillo" runat="server" Width="105px"></asp:TextBox>
            <br />
            <br />Ingresar Ancho:&nbsp; <asp:TextBox ID="txtanchopasillo" runat="server" Width="105px"></asp:TextBox>
            <br />
            <br />Ingresar Bodega:&nbsp; <asp:TextBox ID="txtbodegapasillo" runat="server"></asp:TextBox>
            <br />
            <br /><asp:Button class="btn btn-secondary" ID="btningresarpasillo" runat="server" Text="Ingresar" OnClick="btningresarpasillo_Click"></asp:Button>&nbsp; &nbsp; <asp:Button class="btn btn-secondary" ID="btnlimpiarpasillo" runat="server" Text="Limpiar" OnClick="btnlimpiarpasillo_Click"></asp:Button>
        </center> 
    </td>
  </tr>
    <tr>
        <td>
            <center>
                <asp:Label ID="Label4" runat="server" Text="Crear nuevo Estante:"></asp:Label>
                <br />Ingresar Letra:&nbsp; <asp:TextBox ID="txtletraestante" runat="server" Width="80px"></asp:TextBox>
                <br />
                <br />Ingresar Largo:&nbsp; <asp:TextBox ID="txtlargoestante" runat="server" Width="110px"></asp:TextBox>
                <br />
                <br />Ingresar Ancho:&nbsp; <asp:TextBox ID="txtanchoestante" runat="server" Width="107px"></asp:TextBox>
                <br />
                <br />Ingresar Alto:&nbsp; <asp:TextBox ID="txtaltoestante" runat="server" Width="101px"></asp:TextBox>
                <br />
                <br />Ingresar Id Pasillo:&nbsp; <asp:TextBox ID="txtpasilloestante" runat="server" Width="108px"></asp:TextBox>
                <br />
                <br /><asp:Button class="btn btn-secondary" ID="btningresarestante" runat="server" Text="Ingresar" OnClick="btningresarestante_Click"></asp:Button>&nbsp; &nbsp; <asp:Button class="btn btn-secondary" ID="btnlimpiarestante" runat="server" Text="Limpiar" OnClick="btnlimpiarestante_Click"></asp:Button>
                <br />
                <br />
            </center> 
        </td>
        <td>
            <center>
                <asp:Label ID="Label5" runat="server" Text="Crear nuevo Nivel: "></asp:Label>
                <br />Ingresar # nivel:&nbsp; <asp:TextBox ID="txtnumeronivel" runat="server"></asp:TextBox>
                <br />
                <br />Ingresar Alto:&nbsp; <asp:TextBox ID="txtaltonivel" runat="server"></asp:TextBox>
                <br />
                <br />Ingresar Id Estante:&nbsp; <asp:TextBox ID="txtestantenivel" runat="server"></asp:TextBox>
                <br />
                <br /><asp:Button class="btn btn-secondary" ID="btningresarnivel" runat="server" Text="Ingresar" OnClick="btningresarnivel_Click"></asp:Button>&nbsp; &nbsp; <asp:Button class="btn btn-secondary" ID="btnlimpiarnivel" runat="server" Text="Limpiar" OnClick="btnlimpiarnivel_Click"></asp:Button>
                <br />
                <br />
            </center> 
        </td>
    </tr>
</table>
        <center><div id="copyright">Copyright&copy; 2020 - Página creada por 201612151</div></center>
    </form>
</body>
</html>
