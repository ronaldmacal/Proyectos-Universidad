package Proyecto1;

public class Inicio {
    //Declarar vectores y matrices a utilizar en todo el proyecto
    public static String agenciabancaria[][]=new String[15][7];
    public static int efectivoagenciabancaria[][]=new int[15][2];
    public static String agenciaautobanco[][]=new String[15][7];
    public static int efectivoautobanco[][]=new int[15][2];
    public static String oficinascentrales[]=new String[6];
    public static String cajeros[][]=new String[20][3]; 
    public static int efectivocajeros[][]=new int[20][3];
    public static int efectivocallcenter=4000000;
    public static String empleados[][]=new String[150][3];
    
    public static int cantidadempleados=52;
    public static int cantidadagenciabancaria=5;
    public static int cantidadagenciaautobanco=5;
    public static int cantidadcajeros=10;
    
    
    //Prestamos y tarjetas
    public static int solprestaytarjeta[][]=new int[100][5];
    public static int solcantidadprestaytarjeta=0;
    
    //Valores si realizados ya que los dos anteriores son los de solicitud
    public static int prestaytarjeta[][]=new int[100][4];
    public static int cantidadprestaytarjeta=7;
    
    //Declarar clientes
    public static String clientes[][]=new String[100][9];
    public static int efectivocuentas[][]=new int[500][2];
    public static int cheques[][]=new int[100][27];
    public static int cantidadclientes=10;
    public static int cantidadcuentas=10;
    
    //Procesos de cálculo
    
    
        public static void main(String[] args) {
            inicializartodoslosvalores();
            inicio();
        }
        public static void inicio(){
            frmInicio inicio=new frmInicio();
            inicio.inicializar();
            inicio.setVisible(true);
        }
        public static void inicializartodoslosvalores(){
            //Iniciar 5 agencias bancarias
            agenciabancaria[0][0]="ECCO Centro Los Álamos";agenciabancaria[0][1]="11";agenciabancaria[0][2]="Zona 21, Ciudad Capital";agenciabancaria[0][3]="2442-5603";agenciabancaria[0][4]="4";agenciabancaria[0][5]="2";
            agenciabancaria[1][0]="CC Pradera";agenciabancaria[1][1]="12";agenciabancaria[1][2]="Zona 14, Ciudad Capital";agenciabancaria[1][3]="2442-2230";agenciabancaria[1][4]="6";agenciabancaria[1][5]="3";
            agenciabancaria[2][0]="El Pórtico";agenciabancaria[2][1]="13";agenciabancaria[2][2]="Zona 3, Villa Nueva, Quetzaltenango";agenciabancaria[2][3]="2442-8802";agenciabancaria[2][4]="4";agenciabancaria[2][5]="4";
            agenciabancaria[3][0]="CC Santa Clara";agenciabancaria[3][1]="14";agenciabancaria[3][2]="Zona 18, Ciudad Capital";agenciabancaria[3][3]="2442-0027";agenciabancaria[3][4]="4";agenciabancaria[3][5]="2";
            agenciabancaria[4][0]="CC El frutal";agenciabancaria[4][1]="15";agenciabancaria[4][2]="Zona 1, Fraijanes, Escuintla";agenciabancaria[4][3]="2443-9015";agenciabancaria[4][4]="3";agenciabancaria[4][5]="1";
            efectivoagenciabancaria[0][0]=11;efectivoagenciabancaria[0][1]=300000;
            efectivoagenciabancaria[1][0]=12;efectivoagenciabancaria[1][1]=800000;
            efectivoagenciabancaria[2][0]=13;efectivoagenciabancaria[2][1]=875000;
            efectivoagenciabancaria[3][0]=14;efectivoagenciabancaria[3][1]=400000;
            efectivoagenciabancaria[4][0]=15;efectivoagenciabancaria[4][1]=900000;
            
            //Iniciar 5 agencias con autobanco
            agenciaautobanco[0][0]="Grandes Cedros ";agenciaautobanco[0][1]="21";agenciaautobanco[0][2]="Zona 12, Ciudad Capital";agenciaautobanco[0][3]="2433-1198";agenciaautobanco[0][4]="4";agenciaautobanco[0][5]="2";
            agenciaautobanco[1][0]="Villa Hermosa 2";agenciaautobanco[1][1]="22";agenciaautobanco[1][2]="Zona 2, Escuintla";agenciaautobanco[1][3]="2442-4677";agenciaautobanco[1][4]="4";agenciaautobanco[1][5]="2";
            agenciaautobanco[2][0]="Viena";agenciaautobanco[2][1]="23";agenciaautobanco[2][2]="San Bernandino, Zona 15 Ciudad Capital";agenciaautobanco[2][3]="2442-0103";agenciaautobanco[2][4]="4";agenciaautobanco[2][5]="2";
            agenciaautobanco[3][0]="Avenida Petapa";agenciaautobanco[3][1]="24";agenciaautobanco[3][2]="Zona 1, Ciudad Capital";agenciaautobanco[3][3]="2456-8777";agenciaautobanco[3][4]="4";agenciaautobanco[3][5]="2";
            agenciaautobanco[4][0]="Avenida Reforma";agenciaautobanco[4][1]="25";agenciaautobanco[4][2]="Zona 14, Ciudad Capital";agenciaautobanco[4][3]="2431-1200";agenciaautobanco[4][4]="4";agenciaautobanco[4][5]="2";
            efectivoautobanco[0][0]=21;efectivoautobanco[0][1]=300000;
            efectivoautobanco[1][0]=22;efectivoautobanco[1][1]=245000;
            efectivoautobanco[2][0]=23;efectivoautobanco[2][1]=500000;
            efectivoautobanco[3][0]=24;efectivoautobanco[3][1]=233800;
            efectivoautobanco[4][0]=25;efectivoautobanco[4][1]=455000;
            
            //Iniciar Edificio de Oficinas Centrales
            oficinascentrales[0]="31";oficinascentrales[1]="32";oficinascentrales[2]="33";
            oficinascentrales[3]="34";oficinascentrales[4]="35";oficinascentrales[5]="36";
            
            //Iniciar 10 cajeros automaticos
            cajeros[0][0]="41";cajeros[0][1]="Zona 10, CC El Chiringuito";cajeros[0][2]="Activo";
            cajeros[1][0]="42";cajeros[1][1]="CC Miraflores";cajeros[1][2]="Activo";
            cajeros[2][0]="43";cajeros[2][1]="CC Metrocentro";cajeros[2][2]="Activo";
            cajeros[3][0]="44";cajeros[3][1]="Zona 9, CC Atansio TZUL";cajeros[3][2]="Activo";
            cajeros[4][0]="45";cajeros[4][1]="Santa Monica Zona 6";cajeros[4][2]="Activo";
            cajeros[5][0]="46";cajeros[5][1]="Santa Teresa, Zona 21 Sololá";cajeros[5][2]="Inactivo";
            cajeros[6][0]="47";cajeros[6][1]="CC Los Proceres";cajeros[6][2]="Activo";
            cajeros[7][0]="48";cajeros[7][1]="Aguilar Batres";cajeros[7][2]="Activo";
            cajeros[8][0]="49";cajeros[8][1]="Avenida Petapa";cajeros[8][2]="Activo";
            cajeros[9][0]="411";cajeros[9][1]="Aeropuerto la Aurora";cajeros[9][2]="Inactivo";
            efectivocajeros[0][0]=41;efectivocajeros[0][1]=300000;efectivocajeros[0][2]=0;
            efectivocajeros[1][0]=42;efectivocajeros[1][1]=340000;efectivocajeros[1][2]=0;
            efectivocajeros[2][0]=43;efectivocajeros[2][1]=500000;efectivocajeros[2][2]=0;
            efectivocajeros[3][0]=44;efectivocajeros[3][1]=230000;efectivocajeros[3][2]=0;
            efectivocajeros[4][0]=45;efectivocajeros[4][1]=230000;efectivocajeros[4][2]=0;
            efectivocajeros[5][0]=46;efectivocajeros[5][1]=230000;efectivocajeros[5][2]=0;
            efectivocajeros[6][0]=47;efectivocajeros[6][1]=230000;efectivocajeros[6][2]=0;
            efectivocajeros[7][0]=48;efectivocajeros[7][1]=230000;efectivocajeros[7][2]=0;
            efectivocajeros[8][0]=49;efectivocajeros[8][1]=230000;efectivocajeros[8][2]=0;
            efectivocajeros[9][0]=411;efectivocajeros[9][1]=230000;efectivocajeros[9][2]=0;
            
            //Iniciar 10 clientes
            clientes[0][0]="100001";clientes[0][1]="Ronald Oswaldo Macal de Leon";clientes[0][2]="Villa Nueva";clientes[0][3]="5151-5912";clientes[0][4]="51";clientes[0][5]="61";clientes[0][6]="0";clientes[0][7]="0";clientes[0][8]="0";
            clientes[1][0]="100002";clientes[1][1]="Julian Andre Gonzales Perez";clientes[1][2]="Zona 21, Ciudad Capital";clientes[1][3]="3039-5121";clientes[1][4]="52";clientes[1][5]="0";clientes[1][6]="0";clientes[1][7]="81";clientes[1][8]="0";
            clientes[2][0]="100003";clientes[2][1]="Lionel Andres Messi Fernandez";clientes[2][2]="Villa Hermosa";clientes[2][3]="5677-0912";clientes[2][4]="0";clientes[2][5]="62";clientes[2][6]="71";clientes[2][7]="0";clientes[2][8]="0";
            clientes[3][0]="100004";clientes[3][1]="Fabian Orante Perez Mendez";clientes[3][2]="Zona 6, Ciudad Capital";clientes[3][3]="4412-3300";clientes[3][4]="53";clientes[3][5]="0";clientes[3][6]="0";clientes[3][7]="82";clientes[3][8]="0";
            clientes[4][0]="100005";clientes[4][1]="Joseph Alfredo Reyes Hernandez";clientes[4][2]="Villa Nueva";clientes[4][3]="4413-3300";clientes[4][4]="0";clientes[4][5]="63";clientes[4][6]="0";clientes[4][7]="83";clientes[4][8]="0";
            clientes[5][0]="100006";clientes[5][1]="Francisco Antonio Escamilla Lopez";clientes[5][2]="Vista Hermosa";clientes[5][3]="2442-3300";clientes[5][4]="0";clientes[5][5]="64";clientes[5][6]="0";clientes[5][7]="84";clientes[5][8]="0";
            clientes[6][0]="100007";clientes[6][1]="Lucia Fernandez Gonzales Ramirez";clientes[6][2]="El jocotillo Zona 7, Ciudad Capital";clientes[6][3]="5254-9011";clientes[6][4]="0";clientes[6][5]="0";clientes[6][6]="72";clientes[6][7]="0";clientes[6][8]="0";
            clientes[7][0]="100008";clientes[7][1]="Kobe Dez Bryant James";clientes[7][2]="Zona 9, Ciudad Capital";clientes[7][3]="3056-9011";clientes[7][4]="54";clientes[7][5]="0";clientes[7][6]="0";clientes[7][7]="85";clientes[7][8]="0";
            clientes[8][0]="100009";clientes[8][1]="Lily Jane Aldrin Ponce";clientes[8][2]="Zona 10, Ciudad Capital";clientes[8][3]="5679-1200";clientes[8][4]="0";clientes[8][5]="65";clientes[8][6]="0";clientes[8][7]="0";clientes[8][8]="0";
            clientes[9][0]="100010";clientes[9][1]="Alejandro Estuardo Lara Mejorado";clientes[9][2]="Zona 11, Ciudad Capital";clientes[9][3]="3100-0000";clientes[9][4]="0";clientes[9][5]="66";clientes[9][6]="0";clientes[9][7]="0";clientes[9][8]="0";
            efectivocuentas[0][0]=51;efectivocuentas[0][1]=900;
            efectivocuentas[1][0]=52;efectivocuentas[1][1]=700;
            efectivocuentas[2][0]=53;efectivocuentas[2][1]=630;
            efectivocuentas[3][0]=54;efectivocuentas[3][1]=900;
            efectivocuentas[4][0]=61;efectivocuentas[4][1]=3500;
            efectivocuentas[5][0]=62;efectivocuentas[5][1]=2500;
            efectivocuentas[6][0]=63;efectivocuentas[6][1]=3000;
            efectivocuentas[7][0]=64;efectivocuentas[7][1]=1200;
            efectivocuentas[8][0]=65;efectivocuentas[8][1]=9000;
            efectivocuentas[9][0]=66;efectivocuentas[9][1]=5000;
            
            //Agregar valores a las tarjetas y prestamos por defecto a los clientes
            prestaytarjeta[0][0]=71;prestaytarjeta[0][1]=7000;prestaytarjeta[0][2]=300;prestaytarjeta[0][3]=0;
            prestaytarjeta[1][0]=72;prestaytarjeta[1][1]=3000;prestaytarjeta[1][2]=200;prestaytarjeta[1][3]=0;
            prestaytarjeta[2][0]=81;prestaytarjeta[2][1]=936;prestaytarjeta[2][2]=0;prestaytarjeta[2][3]=5000;
            prestaytarjeta[3][0]=82;prestaytarjeta[3][1]=150;prestaytarjeta[3][2]=0;prestaytarjeta[3][3]=3000;
            prestaytarjeta[4][0]=83;prestaytarjeta[4][1]=2500;prestaytarjeta[4][2]=0;prestaytarjeta[4][3]=3000;
            prestaytarjeta[5][0]=84;prestaytarjeta[5][1]=14000;prestaytarjeta[5][2]=4000;prestaytarjeta[5][3]=20000;
            prestaytarjeta[6][0]=85;prestaytarjeta[6][1]=239;prestaytarjeta[6][2]=100;prestaytarjeta[6][3]=3000;
            
            
            
            //Creacion de cheques para clientes con cuenta monetaria
            cheques[0][0]=100001;cheques[0][1]=61;
            cheques[1][0]=100003;cheques[1][1]=62;
            cheques[2][0]=100005;cheques[2][1]=63;
            cheques[3][0]=100006;cheques[3][1]=64;
            cheques[4][0]=100009;cheques[4][1]=65;
            cheques[5][0]=100010;cheques[5][1]=66;
            //Llenado de emision de cheques, al inicio todos los cheques estan en cero
            for (int i = 0; i <= 5; i++) {
                for (int j = 2; j <25; j++) {
                    cheques[i][j]=0;
                }
            }
            
            for (int i = 0; i < cantidadempleados; i++) {
                empleados[i][2]=Integer.toString(i+1);
            }
            //Crear los 3 empleados por Agencia Bancaria
            empleados[0][0]="Leonard Bustafe Cooper Nowtz";empleados[0][1]="11";
            empleados[1][0]="Haley Steffenson Johnson";empleados[1][1]="11";
            empleados[2][0]="María José Mellado Gonzales";empleados[2][1]="11";
            empleados[3][0]="Bani Alberto Pangan García";empleados[3][1]="12";
            empleados[4][0]="José Andres Gonzales Perez";empleados[4][1]="12";
            empleados[5][0]="Eva Nohemi Mendez Medina";empleados[5][1]="12";
            empleados[6][0]="Alex Sosa Fernandez";empleados[6][1]="13";
            empleados[7][0]="Dylan Javier García Mendez";empleados[7][1]="13";
            empleados[8][0]="Grabriel Azul Marquez Hernandez";empleados[8][1]="13";
            empleados[9][0]="Abraham García Ajpop";empleados[9][1]="14";
            empleados[10][0]="Pablo Javier Franco Perez";empleados[10][1]="14";
            empleados[11][0]="Matthew David Regalado Pared";empleados[11][1]="14";
            empleados[12][0]="Victoriano Antonio Lux Recinos";empleados[12][1]="15";
            empleados[13][0]="Sofía Antonia Vergara Paredes";empleados[13][1]="15";
            empleados[14][0]="Anna Estefanía Valladares Müller";empleados[14][1]="15";
            
            
            //Crear los 3 empleados por Agencia Autobanco
            empleados[15][0]="Otto Daniel Stephenson Herrera";empleados[15][1]="21";
            empleados[16][0]="Tomas Andres Ruiz Gonzales";empleados[16][1]="21";
            empleados[17][0]="María Elena Contreras Ajpop";empleados[17][1]="21";
            empleados[18][0]="Francisco Sinatra Harrison";empleados[18][1]="22";
            empleados[19][0]="Freddy Antonio McCarthney Jonhson";empleados[19][1]="22";
            empleados[20][0]="Predro Wilfredo Polanco Herrera";empleados[20][1]="22";
            empleados[21][0]="Alexander Joe Hill Sky";empleados[21][1]="23";
            empleados[22][0]="David Alejandro Carrson Martinez";empleados[22][1]="23";
            empleados[23][0]="Dean Donnie Winchester Pie";empleados[23][1]="23";
            empleados[24][0]="Castiel Fall Skyile Wayns";empleados[24][1]="24";
            empleados[25][0]="Pedro Manuel Josefa del Carmen";empleados[25][1]="24";
            empleados[26][0]="Elvin Astroid Macal de Leon";empleados[26][1]="24";
            empleados[27][0]="Saul Olegario Ramirez Ramirez";empleados[27][1]="25";
            empleados[28][0]="Mark Andre Twin Estep";empleados[28][1]="25";
            empleados[29][0]="Anabela Cordova Ruiz";empleados[29][1]="25";
            
            //Crear los 2 empleados por Oficinas Centrales
            empleados[30][0]="Mario Augusto Pineda Morales";empleados[30][1]="31";
            empleados[31][0]="Eder Giddalthi Salazar Garcia";empleados[31][1]="31";
            empleados[32][0]="Bryam Gamaliel Flores Barrios";empleados[32][1]="32";
            empleados[33][0]="Jose Emanuel Jimenez Schula";empleados[33][1]="32";
            empleados[34][0]="Mynor Oswaldo Escobar Larios";empleados[34][1]="33";
            empleados[35][0]="Luis Alfredo Vejo Mendoza";empleados[35][1]="33";
            empleados[36][0]="Alan Estuardo Castro Ruiz";empleados[36][1]="34";
            empleados[37][0]="Aristides Castillo Gonzales";empleados[37][1]="34";
            empleados[38][0]="Ervin Josue Villavicencio Camey";empleados[38][1]="35";
            empleados[39][0]="Edwin Alfredo Lopez Gomez";empleados[39][1]="35";
            empleados[40][0]="Karen Nohemi López Barrios";empleados[40][1]="36";
            empleados[41][0]="Celeste Waleska Marisol Winter";empleados[41][1]="36";
            
            //Crear 10 empleados de Call-Center
            empleados[42][0]="Silvia Elizabeth Raxon Ochoa";empleados[42][1]="50";
            empleados[43][0]="Kevin Ricardo Pinto Montenegro";empleados[43][1]="50";
            empleados[44][0]="Mynor Eduardo Realique Boc";empleados[44][1]="50";
            empleados[45][0]="Jorge Adolfo Joj Gallina";empleados[45][1]="50";
            empleados[46][0]="Maria Alejandra Rivera Vela";empleados[46][1]="50";
            empleados[47][0]="Jonathan Gonzales Castillo";empleados[47][1]="50";
            empleados[48][0]="Jose Daniel Lopez Medina";empleados[48][1]="50";
            empleados[49][0]="Josue Miguel Gonzales Chavez";empleados[49][1]="50";
            empleados[50][0]="Fran Ludwing Rivas Lucas";empleados[50][1]="50";
            empleados[51][0]="Kevin Gustavo Cruz Mancilla";empleados[51][1]="50";
            
            
        }
        
}
