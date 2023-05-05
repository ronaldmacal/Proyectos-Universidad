<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="costosLotesySaldos.aspx.cs" Inherits="Proyecto201612151.Usuarios.costosLotesySaldos" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Crear Lotes y Saldos</title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
        <asp:Button  class="btn btn-danger" ID="Button1" runat="server" Text="Volver" OnClick="Button1_Click" />
        </div>
        <center><h1 class="jumbotron text-center"><br />Crear lotes y saldos</div></h1></center>

        <table class="table table-dark">
            <tr>
                <td colspan="2">
                    <center>
                        <asp:Label ID="Label4" runat="server" Text="Productos registrados"></asp:Label>
                <asp:GridView ID="tblclientes" runat="server" BackColor="White" BorderColor="#999999" BorderStyle="Solid" BorderWidth="1px" CellPadding="3" ForeColor="Black" GridLines="Vertical" Width="424px">
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

                    </center>
                </td>
            </tr>
            <tr>
                <td>
                    <center>
                        <asp:Label ID="Label1" runat="server" Text="Crear Lote"></asp:Label>
                        <br />Ingresar producto:&nbsp; <asp:TextBox ID="txtloteidproducto" runat="server"></asp:TextBox>
                        <br />
                        <br />Ingresar Cantidad:&nbsp; <asp:TextBox ID="txtlotecantidad" runat="server"></asp:TextBox>
                        <br />
                        <br />Ingresar Costo:&nbsp; <asp:TextBox ID="txtlotecosto" runat="server"></asp:TextBox>
                        <br />
                        <br />Ingresar Fecha ingreso:&nbsp; <asp:TextBox placeholder="YYYY-MM-DD" ID="txtlotefecha" runat="server"></asp:TextBox>
                        <br />
                        Ingresar tipo: &nbsp;<asp:DropDownList ID="dpwtipoingreso" runat="server">
                            <asp:ListItem Value="U">UEPS</asp:ListItem>
                            <asp:ListItem Value="P">PEPS</asp:ListItem>
                        </asp:DropDownList>
                        <br />
                        <br />
                    </center>
                </td>
                <td>
                    <center>
                        <asp:Label ID="Label2" runat="server" Text="Crear Saldo"></asp:Label>
                        <br />Ingresar producto:&nbsp; <asp:TextBox ID="txtsaldoidproducto" runat="server"></asp:TextBox>
                        <br />
                        <br />Ingresar Cantidad:&nbsp; <asp:TextBox ID="txtsaldocantidad" runat="server"></asp:TextBox>
                        <br />
                        <br />Ingresar Costo:&nbsp; <asp:TextBox ID="txtsaldocosto" runat="server"></asp:TextBox>
                        <br />
                    </center>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <center>
                        <asp:Label ID="Label3" runat="server" Text="Ubicar Saldo o Lote"></asp:Label>
                        :<br />
                        Esta parte es para trazar la ruta a la cual se va ubicar el producto<br />Ingresar bodega:&nbsp;&nbsp;&nbsp;<asp:TextBox ID="txtbodega" runat="server" Width="102px"></asp:TextBox>
                        <br />
                        <br />Ingresar pasillos:&nbsp;&nbsp;&nbsp;<asp:TextBox ID="txtpasillo" runat="server" Width="106px"></asp:TextBox>
                        <br />
                        <br />Ingresar estante:&nbsp;&nbsp;&nbsp;<asp:TextBox ID="txtestante" runat="server" Width="108px"></asp:TextBox>
                        <br />
                        <br />Ingresar nivel:&nbsp;&nbsp;&nbsp;<asp:TextBox ID="txtnivel" runat="server" Width="110px"></asp:TextBox>
                        <br />
                        <br />Ingresar proveedor:&nbsp; <asp:TextBox ID="txtproveedor" runat="server"></asp:TextBox>
                        <br />
                        <br />
                        <asp:Button class="btn btn-secondary" ID="btncompralote" runat="server" Text="Ingresar lote y ubicarlo" Width="249px" OnClick="btncompralote_Click"></asp:Button>
                        <br />
                        <br />
                        <asp:Button class="btn btn-secondary" ID="btncomprasaldo" runat="server" Text="Ingresar saldo y ubicarlo" Width="249px" OnClick="btncomprasaldo_Click"></asp:Button>
                        <br />
                        <br />
                        Dudas al ingresar compra:<br />
                        1.&nbsp; Ingrese los datos del saldo o lote<br />
                        2. Ingrese el proveedor al cual se le está realizando la compra<br />
                        3. De click en realizar compra y ubicarla en el sistema<br />
                        PD: Los botones no estarán disponibles sino se ubica primero el producto<br />
                        <br />
                    </center>
                </td>
            </tr>
        </table>
    </form>
    <center><div id="copyright">Copyright&copy; 2020 - Página creada por 201612151</div></center>
</body>
</html>
