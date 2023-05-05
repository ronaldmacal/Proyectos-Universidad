package Practica1;
import java.util.Scanner;
import java.util.Random;


public class inicio {
    public static String nompokemon[]=new String[6];
    public static int vidayataque[][]=new int[2][6];
    public static int contadorpokemon[]=new int[6];
    
    //Main principal, contiene los ciclos de llenado de nombres y vida de los pokemones
    public static void main(String[] args) {
        //Ciclo for para el llenado de nombres por default a pokemones
        for(int i=0;i<=5;i++){
            switch(i){
                case 0:
                    nompokemon[i]="Weedle";
                    break;
                case 1:
                    nompokemon[i]="Squirtle";
                    break;
                case 2:
                    nompokemon[i]="Mew";
                    break;
                case 3:
                    nompokemon[i]="Metapod";
                    break;
                case 4:
                    nompokemon[i]="Dragonair";
                    break;
                case 5:
                    nompokemon[i]="Ditto";
                    break;   
            }
        }
        //Creacion de vida y ataque aleatorio para cada pokemon
        Random aleatorio=new Random();
        for(int i=0;i<=5;i++){
            vidayataque[0][i]=50+aleatorio.nextInt(50);
            vidayataque[1][i]=5+aleatorio.nextInt(15);
            //Llenado del vector contador para los metodos de pokemon menos usado y más usado
            contadorpokemon[i]=0;
        }
        
        menu();
    }
    
    //Menu principal
    public static void menu(){
        Scanner leer=new Scanner(System.in);
        String menu="";
        System.out.println("\n********************************");
        System.out.println("MENÚ PRINCIPAL");
        System.out.println("    1.Ver todos los pokémon");
        System.out.println("    2.Editar pokémon");
        System.out.println("    3.Registro de partidas");
        System.out.println("    4.Batallar");
        System.out.println("    5.Lista de pokémon mas utilizados");
        System.out.println("    6.Lista de pokémon menos utilizados");
        System.out.println("    7.Salir");
        System.out.println("Ingrese un número: ");
        menu=leer.next();
        switch(menu){
            case "1":
                vertodoslospokemon();
                break;
            case "2":
                editarlospokemon();
                break;
            case "3":
                
                break;
            case "4":
                batalla();
                break;
            case "5":
                pokemonmasutilizado();
                break;
            case "6":
                pokemonmenosutilizado();
                break;
            case "7": 
                System.exit(0);
            default:
                System.out.println("Número desconocido o caracter inválido");
                menu();
        }
    }
    
    //Método que muestra el historial de partidas del juego.
    public static void historialdepartidas(){
        
    }
    
    //Método para calcular el pokemon más utilizado
    public static void pokemonmenosutilizado(){
        int menor=0;
        menor=contadorpokemon[0];
        System.out.println("Los pokemon menos utilizados");
        for(int i=0;i<=5;i++){
            if(contadorpokemon[i]<=menor){
                menor=contadorpokemon[i];
            }
        }
        for(int i=0;i<=5;i++){
            if(menor==contadorpokemon[i]){
                switch(i){
                    case 0:
                        weedle();
                        break;
                    case 1:
                        squirtle();
                        break;
                    case 2:
                        mew();
                        break;
                    case 3:
                        metapod();
                        break;
                    case 4:
                        dragonair();
                        break;
                    case 5:
                        ditto();
                        break;
                    default:
                        System.out.println("Uno de los pokemones seleccionados no existe, vuelva a intentarlo");
                        menu();
                        break;
                }
                System.out.println("Nombre: "+nompokemon[i]);
                System.out.println("Ataque: "+vidayataque[1][i]);
                System.out.println("Vida: "+vidayataque[0][i]);
                System.out.println("Veces usado: "+menor);
            }
        }
        menu();
    }
    
    //Método para calcular el pokemon menos utilizado
    public static void pokemonmasutilizado(){
        int mayor=0;
        mayor=contadorpokemon[0];
        for(int i=0;i<=5;i++){
            if(contadorpokemon[i]>=mayor){
                mayor=contadorpokemon[i];
            }
        }
        for(int i=0;i<=5;i++){
            if(mayor==contadorpokemon[i]){
                switch(i){
                    case 0:
                        weedle();
                        break;
                    case 1:
                        squirtle();
                        break;
                    case 2:
                        mew();
                        break;
                    case 3:
                        metapod();
                        break;
                    case 4:
                        dragonair();
                        break;
                    case 5:
                        ditto();
                        break;
                    default:
                        System.out.println("Uno de los pokemones seleccionados no existe, vuelva a intentarlo");
                        menu();
                        break;
                }
                System.out.println("Nombre: "+nompokemon[i]);
                System.out.println("Ataque: "+vidayataque[1][i]);
                System.out.println("Vida: "+vidayataque[0][i]);
                System.out.println("Veces usado: "+mayor);
            }
        }
        menu();
    }
    
    //Método para cargar valores al contador 
    public static void agregaracontador(int contador){
        switch (contador) {
            case 1:
                contadorpokemon[0]=contadorpokemon[0]+1;
                break;
            case 2:
                contadorpokemon[1]=contadorpokemon[1]+1;
                break;
            case 3:
                contadorpokemon[2]=contadorpokemon[2]+1;
                break;
            case 4:
                contadorpokemon[3]=contadorpokemon[3]+1;
                break;
            case 5:   
                contadorpokemon[4]=contadorpokemon[4]+1;
                break;
            case 6:
                contadorpokemon[5]=contadorpokemon[5]+1;
                break;
            default:
                break;
        }
    }
    
    //Metodo para la creación de la batalla y el juego principal
    public static void batalla(){
        boolean paso=true;//<-- Esta variable es solo utilizada para evitar que se seleccione un pokemon que ya está muerto
        int vidapokemon[][]=new int[2][2];
        Scanner leer=new Scanner(System.in);
        //Variables para saber cuando estan muertos o vivos.
        boolean estado1pk1=true;
        boolean estado1pk2=true;
        boolean estado2pk1=true;
        boolean estado2pk2=true;
        
        String jugador1="";
        String jugador2="";
        int j1pokemon1=0;
        int j1pokemon2=0;
        int j2pokemon1=0;
        int j2pokemon2=0;
        //Solicitar todos los datos para poder batallar
        System.out.println("_____________________________________________________________________________________________");
        System.out.println("Ingrese el nombre del jugador #1");
        jugador1=leer.next();
        System.out.println(jugador1+" ingrese numero del primer pokemon a utilizar: ");
        j1pokemon1=leer.nextInt();
        System.out.println(jugador1+" ingrese numero del segundo pokemon a utilizar: ");
        j1pokemon2=leer.nextInt();
        System.out.println("Ingrese el nombre del jugador #2");
        jugador2=leer.next();
        System.out.println(jugador2+" ingrese numero del primer pokemon a utilizar: ");
        j2pokemon1=leer.nextInt();
        System.out.println(jugador2+" ingrese numero del segundo pokemon a utilizar: ");
        j2pokemon2=leer.nextInt();
        
        //Registrar las vidas y ataques de los jugadores en los vectores del metodo batalla
            try{
                vidapokemon[0][0]=vidayataque[0][j1pokemon1-1];
                vidapokemon[0][1]=vidayataque[0][j1pokemon2-1];
                vidapokemon[1][0]=vidayataque[0][j2pokemon1-1];
                vidapokemon[1][1]=vidayataque[0][j2pokemon2-1];                
            }catch (Exception e){
                System.out.println("El numero ingresado en la selección de pokemon no existe");menu();
            }
        //Colocar los contadores para acumular los valores de los pokemon más usados.
        agregaracontador(j1pokemon1);
        agregaracontador(j1pokemon2);
        agregaracontador(j2pokemon1);
        agregaracontador(j2pokemon2);
        
        //Iniciar la batalla entre los 4 pokemon
        boolean finalizado=false;
        System.out.println("______________________________________________________________________________________________");
        System.out.println("INICIA LA BATALLA");
        
        int ataquearealizar=0;
        //Son numeros auxiliares para leer los datos dentro del método
        int n1=0;
        int j1seleccionado=0;
        int j2seleccionado=0;
        
        while(finalizado==false){
        //TURNO DEL JUGADOR 1 SOBRE EL JUGADOR 2. AQUI INICIA.
            paso=true;
            System.out.println("Turno J1: "+jugador1);
            
            //Seleccionar Pokemon Jugador 1
            System.out.println("Qué pokemon desea utilizar? 1."+nompokemon[j1pokemon1-1]+ "o 2."+nompokemon[j1pokemon2-1]);
            n1=leer.nextInt();
            //Registrar cual de los dos pokemones ataca primero
            if(n1==1){
                if(estado1pk1==false){
                    System.out.println("El pokemon seleccionado está muerto");
                    paso=false;
                }else{j1seleccionado=j1pokemon1-1;}
            }else{
                if(estado1pk2==false){
                    System.out.println("El pokemon seleccionado está muerto");
                    paso=false;
                }else{j1seleccionado=j1pokemon2-1;}
            }
            if(paso==true){
                switch(j1seleccionado){
                    case 0:
                        weedle();
                        break;
                    case 1:
                        squirtle();
                        break;
                    case 2:
                        mew();
                        break;
                    case 3:
                        metapod();
                        break;
                    case 4:
                        dragonair();
                        break;
                    case 5:
                        ditto();
                        break;
                    default:
                        System.out.println("Uno de los pokemones seleccionados no existe, vuelva a intentarlo");
                        menu();
                        break;
                }
                System.out.println("Nombre: "+nompokemon[j1seleccionado]);
                System.out.println("Vida: "+vidapokemon[0][n1-1]);
                System.out.println("Ataque: "+vidayataque[1][j1seleccionado]);ataquearealizar=vidayataque[1][j1seleccionado];
                System.out.println("");
                System.out.println("Que pokemon de "+jugador2+" desea atacar? 1."+nompokemon[j2pokemon1-1]+ " o 2."+nompokemon[j2pokemon2-1]);
            n1=leer.nextInt();
            if(n1==1){j2seleccionado=j2pokemon1-1;
            }else{j2seleccionado=j2pokemon2-1;}
            switch(j2seleccionado){
                case 0:
                    weedle();
                    break;
                case 1:
                    squirtle();
                    break;
                case 2:
                    mew();
                    break;
                case 3:
                    metapod();
                    break;
                case 4:
                    dragonair();
                    break;
                case 5:
                    ditto();
                    break;
                default:
                    System.out.println("Uno de los pokemones seleccionados no existe, vuelva a intentarlo");
                    menu();
                    break;
            }
            System.out.println("Nombre: "+nompokemon[j2seleccionado]);
            System.out.println("Ataque: "+vidayataque[1][j2seleccionado]);
            //Bajar la vida del pokemon atacado
            vidapokemon[1][n1-1]-=ataquearealizar;
            //Validar que no esten muertos los dos pokemon
                if(vidapokemon[1][n1-1]<=0){
                    if(n1==1){
                        estado2pk1=false;
                        System.out.println("Estado: Muerto");
                    }else{
                        estado2pk2=false;
                        System.out.println("Estado: Muerto");
                    }
                }else{
                    System.out.println("Vida actual: "+vidapokemon[1][n1-1]);
                    System.out.println("Estado: Vivo");
                }
                if(estado2pk1==false&&estado2pk2==false){
                    System.out.println("Juego finalizado: ganador Jugador #1: "+jugador1);
                    menu();
                }
            }
            paso=true;
            ataquearealizar=0;
            
            //TURNO DEL JUGADOR 2 SOBRE EL JUGADOR 1. AQUI INICIA.
            System.out.println("********************************************************************************************");
            System.out.println("Turno J2: "+jugador2);
            
            //Seleccionar Pokemon Jugador 2
            n1=0;
            System.out.println("Qué pokemon desea utilizar? 1."+nompokemon[j2pokemon1-1]+ " o 2."+nompokemon[j2pokemon2-1]);
            n1=leer.nextInt();
            //Registrar cual de los dos pokemones ataca primero
            if(n1==1){
                if(estado2pk1==false){
                    System.out.println("El pokemon seleccionado está muerto");
                    paso=false;
                }else{j2seleccionado=j2pokemon1-1;}
            }else{
                if(estado1pk2==false){
                    System.out.println("El pokemon seleccionado está muerto");
                    paso=false;
                }else{j2seleccionado=j2pokemon2-1;}
            }
            if(paso==true){
                switch(j2seleccionado){
                    case 0:
                        weedle();
                        break;
                    case 1:
                        squirtle();
                        break;
                    case 2:
                        mew();
                        break;
                    case 3:
                        metapod();
                        break;
                    case 4:
                        dragonair();
                        break;
                    case 5:
                        ditto();
                        break;
                    default:
                        System.out.println("Uno de los pokemones seleccionados no existe, vuelva a intentarlo");
                        menu();
                        break;
                }
                System.out.println("Nombre: "+nompokemon[j2seleccionado]);
                System.out.println("Vida: "+vidapokemon[1][n1-1]);
                System.out.println("Ataque: "+vidayataque[1][j2seleccionado]);ataquearealizar=vidayataque[1][j2seleccionado];
                System.out.println("");
                n1=0;
                System.out.println("Que pokemon de "+jugador1+" desea atacar? 1."+nompokemon[j1pokemon1-1]+ " o 2."+nompokemon[j1pokemon2-1]);
                n1=leer.nextInt();
                if(n1==1){j1seleccionado=j1pokemon1-1;
                }else{j1seleccionado=j1pokemon2-1;}
                switch(j1seleccionado){
                    case 0:
                        weedle();
                        break;
                    case 1:
                        squirtle();
                        break;
                    case 2:
                        mew();
                        break;
                    case 3:
                        metapod();
                        break;
                    case 4:
                        dragonair();
                        break;
                    case 5:
                        ditto();
                        break;
                    default:
                        System.out.println("Uno de los pokemones seleccionados no existe, vuelva a intentarlo");
                        menu();
                        break;
                }
                System.out.println("Nombre: "+nompokemon[j1seleccionado]);
                System.out.println("Ataque: "+vidayataque[1][j1seleccionado]);
                //Bajar la vida del pokemon atacado
                vidapokemon[0][n1-1]-=ataquearealizar;
                //Validar que no esten muertos los dos pokemon
                if(vidapokemon[0][n1-1]<=0){
                    if(n1==1){
                        estado1pk1=false;
                        System.out.println("Estado: Muerto");
                    }else{
                        estado1pk2=false;
                        System.out.println("Estado: Muerto");
                    }
                }else{
                    System.out.println("Vida actual: "+vidapokemon[0][n1-1]);
                    System.out.println("Estado: Vivo");
                }

                if(estado1pk1==false&&estado1pk2==false){
                    System.out.println("Juego finalizado: ganador Jugador #1: "+jugador2);
                    menu();
                }
            }
        }
        
    }
    
    
    
    //Metodo para poder editar los pokemones
    public static void editarlospokemon(){
        String numero="";
        System.out.println("Porfavor ingrese el numero del pokemon a editar: ");
        Scanner leer=new Scanner(System.in);
        numero=leer.next();
        int pokemon=0;
        switch(numero){
            case "1":
                pokemon=0;
                break;
            case "2":
                pokemon=1;
                break;
            case "3":
                pokemon=2;
                break;
            case "4":
                pokemon=3;
                break;
            case "5":
                pokemon=4;
                break;
            case "6":
                pokemon=5;
                break;
            default:
                System.out.println("Porfavor ingrese unicamente un numero.");
                menu();
        }
        //Ingreso de nuevos valores si se cumplió lo anterior
        String nombre="";
        int vida=0;
        int ataque=0;
        System.out.println("Ingrese el nuevo nombre: ");
        nombre=leer.next();
        System.out.println("Ingrese el nuevo valor de vida del pokemon, un numero de 50-100");
        vida=leer.nextInt();
        System.out.println("Ingrese el nuevo valor de ataque del pokemon, un numero de 5-20");
        ataque=leer.nextInt();
        //Confirmar que los valores esten en el rango solicitado
        if(vida>=50&&vida<=100){
            vidayataque[0][pokemon]=vida;
        }else{System.out.println("El valor ingresado de la vida no esta en el rango");menu();}
        if(ataque>=5&&ataque<=20){
            vidayataque[1][pokemon]=ataque;
        }else{System.out.println("El valor ingresado del ataque no esta en el rango");menu();}
        nompokemon[pokemon]=nombre;
        System.out.println("Cambio realizado con éxito!");
        menu();
    }
    
    //Metodo para mostrar los 6 pokemones guardados aunque ya esten editados
    public static void vertodoslospokemon(){
        System.out.println("______________________________________________________________________________________________");
        System.out.println("Listado general de pokemones registrados");
        for(int i=0;i<=5;i++){
            switch(i){
                case 0:
                    weedle();
                    break;
                case 1:
                    squirtle();
                    break;
                case 2:
                    mew();
                    break;
                case 3:
                    metapod();
                    break;
                case 4:
                    dragonair();
                    break;
                case 5:
                    ditto();
                    break;
            }
            System.out.println("Nombre de Pokemon #"+(i+1)+": "+nompokemon[i]);
            System.out.println("Vida de Pokemon: "+vidayataque[0][i]);
            System.out.println("Ataque de pokemon: "+vidayataque[1][i]);
            System.out.println("______________________________________________________________________________________________");
            System.out.println("");
        }
        menu();
    }
    
    
    //Métodos solamente para llamar los dibujos de cada pokemon
    public static void weedle(){
        System.out.println("                      @                                                        ");
        System.out.println("                        *                                                      ");
        System.out.println("                     @   ,                                                     ");
        System.out.println("                          %                                                    ");
        System.out.println("                    &      #                                                   ");
        System.out.println("                            @                                                  ");
        System.out.println("                   @         @                                                 ");
        System.out.println("                 @*           @ *%                                             ");
        System.out.println("              @    #        %       @                                          ");
        System.out.println("            #                         #                                        ");
        System.out.println("           @.@@          @ @@           *                                      ");
        System.out.println("          % @@           .@@             #                                     ");
        System.out.println("         %                               ,,                                    ");
        System.out.println("        @         ,*                      @                                    ");
        System.out.println("      ,             &                     @                                    ");
        System.out.println("      @             @                     @                                    ");
        System.out.println("       *           #,                     @                                    ");
        System.out.println("         @@,,,,,(@.                      @                                     ");
        System.out.println("                                        @                         ,@           ");
        System.out.println("            #                          ,                          %  .         ");
        System.out.println("             .*                     @.                                 @       ");
        System.out.println("                *&              %@*  @                             ,    ,      ");
        System.out.println("                      .,,,,           @                            %           ");
        System.out.println("                                      ,                            *,@@@       ");
        System.out.println("                @@             @   @  ,                          %      *      ");
        System.out.println("               @ @                 ,, #                        %@#@@#          ");
        System.out.println("               ..,             @   @ **@                     *        @   ,    ");
        System.out.println("                  *                 @    #            *%    ,@*     %  @#      ");
        System.out.println("                    #            &@       &%@@      @           @   %*%,       ");
        System.out.println("                        %  ,@@@%     *@ @   %    *       #       @    &        ");
        System.out.println("                      @           ,    %,#,     ,       *    #   @  ,,         ");
        System.out.println("                      ,            &* @# @       @       @    ,@%@             ");
        System.out.println("                      @,                @  ,.    @   @  @,      @              ");
        System.out.println("                        % @*         @#     & ,& .  @   @@   *@                ");
        System.out.println("                                &              **     , %                      ");
        System.out.println("                                @@           %        #                        ");
        System.out.println("                                 &@@ @@@@%                                     ");
        System.out.println("                                                     #.                *       ");
    }
    
    public static void squirtle(){
        System.out.println("                          (%.      ,%*                                         ");
        System.out.println("                      #                  %                                     ");
        System.out.println("                   %                       .                                   ");
        System.out.println("                 %                /          #                                 ");
        System.out.println("                 %                .            (                               ");
        System.out.println("                (                %    (,        %                              ");
        System.out.println("               /#%              % %%%% ,,        %                             ");
        System.out.println("                %                (%%%                                          ");
        System.out.println("               (%                 %#  /.          *                            ");
        System.out.println("             #                   /#(,%            *                            ");
        System.out.println("                %   #        ,%%#,       ,       %                             ");
        System.out.println("                                                %                              ");
        System.out.println("                %                               %                              ");
        System.out.println("                  %                          %   %                             ");
        System.out.println("                   /%%                     %   .   .                           ");
        System.out.println("            /%           ##           *%/      (    %                          ");
        System.out.println("         (         %                 %           %#. *                         ");
        System.out.println("      (                             #             %  %*                        ");
        System.out.println("                   .               %              %    %                       ");
        System.out.println(" %   #                  .%#.      #               %     %                      ");
        System.out.println(" %   ,         *           %                     ,                             ");
        System.out.println("              (                  #  , #         .        %                     ");
        System.out.println("    *#%%%%%%( #(                %              %         #                     ");
        System.out.println("                (              .             %/   %      (                     ");
        System.out.println("                          ,      *%       %       (      %                     ");
        System.out.println("              #     %*    #                       *     (#        %%/.,#%,     ");
        System.out.println("              / %         %   ./####(%            #    #       %            /  ");
        System.out.println("              /(          %                   (   (  %  %   ,.               # ");
        System.out.println("                #         #             (%       # %   *   #                  %");
        System.out.println("           %     %        (            *           %     /          #%(        ");
        System.out.println("          /       #       *          *              .* ,         %             ");
        System.out.println("          /         /   #.  %                       %%          (             .");
        System.out.println("                               /%   %               ,           #             %");
        System.out.println("                        (.          %                            ,           % ");
        System.out.println("                        .    (%%.,%%#/               ,             /       %   ");
        System.out.println("        % .                           /              %                  */     ");
        System.out.println("         /           #                 %                    /%%(/(%%/          ");
        System.out.println("            *  %(                                    .                         ");
        System.out.println("                                        (            *                         ");
        System.out.println("                                        %%%%%     %%% /                        ");
    }
    
    public static void mew(){
        System.out.println("                       ,,,,,                                                   ");
        System.out.println("            *@,                #                                               ");
        System.out.println("           #                 @                                                 ");
        System.out.println("          @              %@*                                                   ");
        System.out.println("         @ @                                                                   ");
        System.out.println("        @ @                            *@@#  .%@@&                             ");
        System.out.println("        @@                   @    ,&@*              @      @                   ");
        System.out.println("        &%                   @                             *                   ");
        System.out.println("        @.@                  #                            @                    ");
        System.out.println("         @ &                  @@                         @                     ");
        System.out.println("           @ #                @    @  @           @ @%@   @                    ");
        System.out.println("            @  @.            @    @  &,@         @.@ @ *#  @                   ");
        System.out.println("              @*  *@%        @    @ , #&%         @  @  %  @                   ");
        System.out.println("                 *@@    ,@@@# @   *@@@@@@        .@@@@&   @                    ");
        System.out.println("                        @@@*,  @                         @                     ");
        System.out.println("                                 #@& @                @.                       ");
        System.out.println("                                      @*         .@    @#                      ");
        System.out.println("                                     @   @@%%%%@*@   %@    @                   ");
        System.out.println("                                   @@            @       *@  *@                ");
        System.out.println("                               #@                  ##        @  @              ");
        System.out.println("                         ,&%%*                        @        @  &            ");
        System.out.println("                          .*   *@@ @              @*     ,,      # @           ");
        System.out.println("                                  @               @   @ @ #      .* @          ");
        System.out.println("                                #                  @   *&         @ @          ");
        System.out.println("                               @                    &             % .          ");
        System.out.println("                              ,                      .            @ ,          ");
        System.out.println("                              @     @             @  @            @ @          ");
        System.out.println("                              @      @            %  @           @ .,          ");
        System.out.println("                              @       @           @  @          @  *           ");
        System.out.println("                              #        @         &   @        ** #*            ");
        System.out.println("                               @       @        @   @     @@    @              ");
        System.out.println("                                @     @      *@    @         @                 ");
        System.out.println("                                 @   @       @   .@@@@@@@%                     ");
        System.out.println("                           ,@@*      @      ,     &                            ");
        System.out.println("                     @@             *#       .@      @@                        ");
        System.out.println("                @@.             @@.             *@       #@*                   ");
        System.out.println("             @.           .@@                       @         *@*              ");
        System.out.println("           @@#         @#                              @*          @#          ");
        System.out.println("          @ *@      @*                                    @.        @ @        ");
        System.out.println("         @ @     @                                           @    *@  #@       ");
        System.out.println("           @@@#                                                @/    @ %,      ");
        System.out.println("                                                                  @* %@@       ");
    }
    
    public static void metapod(){
        System.out.println("                   %(%                                                         ");
        System.out.println("                /#    *                                                        ");
        System.out.println("              /.      %                                                        ");
        System.out.println("             %        (                                                        ");
        System.out.println("            ,          %                                                       ");
        System.out.println("           #            ,                                                      ");
        System.out.println("          %             (                                                      ");
        System.out.println("         ,               %                                                     ");
        System.out.println("         /                %                                                    ");
        System.out.println("        %                  %                                                   ");
        System.out.println("       %                    %                                                  ");
        System.out.println("      %                  %%#  %                                                ");
        System.out.println("     #                 %      %%                                               ");
        System.out.println("    *                 %,        %(                                             ");
        System.out.println("    *%                    %%%%  # %                                            ");
        System.out.println("   %  %                   %%%%%.*,  %.                                         ");
        System.out.println("  (    %            * %         .*    #%                                       ");
        System.out.println("  #     .         /    %        %        %                                     ");
        System.out.println(" (      %       %        %.   %             %*                                 ");
        System.out.println(" #*   %    %   *                                %(                             ");
        System.out.println("   %%         %                                     %(                         ");
        System.out.println("%           .    %                                      %                      ");
        System.out.println("/                   %                                  #.                      ");
        System.out.println(" .         (          %                               %                        ");
        System.out.println(" %        %             ./                          ,                          ");
        System.out.println(" #                         #                       %                           ");
        System.out.println("  %      %                 %                      %                            ");
        System.out.println("%  .     *                #                   *  %                             ");
        System.out.println(" ( , %#%,                #                     %/                              ");
        System.out.println("  % .     .%            #                       %                              ");
        System.out.println("    # #      %         %                          %                            ");
        System.out.println("       /%       %     %                             %                          ");
        System.out.println("          ./      /%#%                                %                        ");
        System.out.println("            %        #                                 %                       ");
        System.out.println("            %.        .                                 %                      ");
        System.out.println("              #%        .                                (%                    ");
        System.out.println("                %        (                               %  (                  ");
        System.out.println("                  %       %                              #    %                ");
        System.out.println("                    %      ,/                           #       %              ");
        System.out.println("                       %      #(                       %         , %.          ");
        System.out.println("                          %(       %#                 , %       .     %        ");
        System.out.println("                                 %%.    ##         #     ,/    ,       %%.     ");
        System.out.println("                                      .%.   %   %#            % ,      %   /%/ ");
        System.out.println("                                          ,%.     #%        *,   ,%(/#%        ");
        System.out.println("                                               %%       *#%*         %        *");
        System.out.println("                                                       %       ,%%%%      ,%.  ");
    }
    
    public static void dragonair(){
        System.out.println("                                    %                 @                        ");
        System.out.println("                                    @ .              @  @                      ");
        System.out.println("                                    @ #               , @                      ");
        System.out.println("                                    @ , @,     .@   *   &                      ");
        System.out.println("                                   ,  / #*        .*   @ ,                     ");
        System.out.println("                                   , @ @  @      (      @                      ");
        System.out.println("                                     @      #@@@   .  * ,                      ");
        System.out.println("                                     @     @@@@@%&&  ( @                       ");
        System.out.println("                                    *      @@@@@  @  @                         ");
        System.out.println("                                  @                .                           ");
        System.out.println("                                  @                                            ");
        System.out.println("                                   @      **@    .                             ");
        System.out.println("                                       ,.,  @   *                              ");
        System.out.println("     #@                                .     @ #                               ");
        System.out.println("    &@                                  #   # @                                ");
        System.out.println("     ,                                 @ *   @                                 ");
        System.out.println("   , ,                               #  ,   @                                  ");
        System.out.println("   &   *                            @   .  @                                   ");
        System.out.println("       #                               @  &                                    ");
        System.out.println("   @    *                        #     .  ,                                    ");
        System.out.println("        *                       ,     ,  ,                                     ");
        System.out.println("   #..&&                       .      ,  ,               ,#,,,,                ");
        System.out.println("     . &                       *          .     #@,                 ,&         ");
        System.out.println("     &  @                     &           @&&                           *      ");
        System.out.println("     %   *                    @        ,  .                              #     ");
        System.out.println("      #     @                #&        @    *               &                  ");
        System.out.println("        #         *&@@@@@*    @         #     @.      ,&@(                @@   ");
        System.out.println("          @&                             *                              @  %   ");
        System.out.println("             @(,&@*           .@          *.                     ,,&@*     %   ");
        System.out.println("                  *@(           @            @@        *@&                @    ");
        System.out.println("                                 *.                                    *@      ");
        System.out.println("                                    @.                          *#@@*          ");
    }
    
    public static void ditto(){
        System.out.println("                                   #@%                                         ");
        System.out.println("                                @       ,@@,,&@@%%@.                           ");
        System.out.println("                             ##                      @                         ");
        System.out.println("                @                                     #                        ");
        System.out.println("                                  @                    @                       ");
        System.out.println("              @                  @#          @          *                      ");
        System.out.println("              #                   .# &@&,                @                     ");
        System.out.println("               @                        ,*%%%,             *                   ");
        System.out.println("                                                             @.                ");
        System.out.println("                &                                                @             ");
        System.out.println("                ,                                                  @           ");
        System.out.println("                ,                                                   @          ");
        System.out.println("                @                                                   .          ");
        System.out.println("               @                                                  &            ");
        System.out.println("              .                                                 *              ");
        System.out.println("             @                                                 ,.              ");
        System.out.println("            @                                                  ,               ");
        System.out.println("          @                                                     @              ");
        System.out.println("        #                                                        @             ");
        System.out.println("       @                                                          *.           ");
        System.out.println("      @                                                             @          ");
        System.out.println("     %                                                                @        ");
        System.out.println("     %                                                                @        ");
        System.out.println("     @                                                                 @       ");
        System.out.println("      @                                                                ,       ");
        System.out.println("       @                                                               ,,      ");
        System.out.println("          %@@# ,#%%                                                    @       ");
        System.out.println("                       %@#                                            @        ");
        System.out.println("                           .@@                   #@%,            ,*.           ");
        System.out.println("                                ,%@@@&%&@&@@%,                                 ");
    }
}
