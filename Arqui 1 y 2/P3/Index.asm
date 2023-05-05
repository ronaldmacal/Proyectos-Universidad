include maccopy.asm
.model small
.stack
.data
    ;------------------------------------------------------------------------
    ;********* Variables usadas para la lectura del archivo *********
    err1 DB 10,13,"Error, el archivo ingresado no existe. Vuelva a ingresar la ruta y la extension","$"
    err2 DB 10,13,"Error, no se puede cerrar el archivo","$"
    err3 DB 10,13,"Error al leer el archivo","$"
    cierre DB 10,13,"Numeros cargados con exito","$"
    pregunta DB 10,13,"Ingrese la ruta del archivo que se desea abrir y extension",0ah,0dh,"  Ejemplo ruta: entrada.xml, no exeder 50 caracteres","$"
    bufferentrada DB 50 dup('$')
    handlerentrada DW ?
    bufferinformacion DB 300 dup('$')
    ;------------------------------------------------------------------------
    ;------------------------------------------------------------------------
    ;********* Varibales usadas dentro del inicio *********
    salto DB 10,13, " ","$"
    encab1 DB 10,13,"UNIVERSIDAD DE SAN CARLOS DE GUATEMALA","$"
    encab2 DB 10,13,"FACULTAD DE INGENIERIA","$"
    encab3 DB 10,13,"ESCUELA DE CIENCIAS Y SISTEMAS","$"
    encab4 DB 10,13,"ARQUITECTURA DE COMPUTADORES Y ENSAMBLADORES 1 A","$"
    encab5 DB 10,13,"SECCION B","$"
    encab6 DB 10,13,"Ronald Oswaldo Macal de Leon","$"
    encab7 DB 10,13,"201612151","$"
    encab8 DB 10,13,"Practica 4","$"
    encab9 DB 10,13,"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%","$"
    menu1 DB 10,13,"Menu Principal","$"
    menu2 DB 10,13,"1. Cargar archivo","$"
    menu3 DB 10,13,"2. Ordenar","$"
    menu4 DB 10,13,"3. Generar Reporte","$"
    menu5 DB 10,13,"4. Salir","$"
    menu6 DB 10,13,"Elija una opcion: ","$"
    menu7 DB 10,13,"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%","$"
    opcion1 DB 10,13,"METODO CARGAR ARCHIVO","$"
    opcion2 DB 10,13,"METODO ORDENAR","$"
    opcion3 DB 10,13,"METODO GENERAR REPORTE","$"
    salida DB 10,13,"Adios, vuelve pronto... :)","$"
    opcion DB ? 
.code
    menu:
    jmp menuprincipal
    
;Fin    
.exit
menuprincipal:
    print encab1
    print encab2
    print encab3
    print encab4
    print encab5
    print encab6
    print encab7
    print encab8
    print encab9
    print menu1
    print menu2
    print menu3
    print menu4
    print menu5
    print menu6
    print menu7
    ;Final del menu, esperar una opcion
        ;Leer entrada
    print salto

    mov ah,01h
    int 21h
    sub al,30h
    mov opcion,al

    cmp opcion,1
    jz cargararchivo

    cmp opcion,2
    jz ordenararchivo

    cmp opcion,3
    jz generarreporte

    cmp opcion,4
    jz salir
    
    jmp menuprincipal
.exit
;------------------------------------------------------------------------
;*********   Metodos principales del flujo del programa ************
cargararchivo:
    print opcion1
    print salto
    print pregunta
    print salto
    Limpiar bufferentrada,SIZEOF bufferentrada,24h
    ObtenerRuta bufferentrada
    Abrir bufferentrada,handlerentrada
    Limpiar bufferinformacion,SIZEOF bufferinformacion,24h
    Leer handlerentrada,bufferinformacion,SIZEOF bufferinformacion
    jmp cerrararchivo
.exit
ordenararchivo:
    print opcion2
.exit
generarreporte:
    print opcion3
.exit
salir:
    print salida
.exit
;------------------------------------------------------------------------
;*********   Metodos secundarios para el programa ************
;*********   Metodos para cargar archivo   ************
cerrararchivo:
    Cerrar handlerentrada
    jmp extraernumeros
.exit
error1:
    print salto
    print err1
    getChar
    jmp cargararchivo
.exit
error2:
    print salto
    print err2
    getChar
    jmp cargararchivo
.exit
error3:
    print salto
    print err3
    getChar
    jmp cargararchivo
.exit
extraernumeros:
    ;Parte para extraer numeros
    print bufferinformacion
    ;Aqui hay que agregar la nueva informacion
    print salto
    print cierre
    print salto
    jmp menuprincipal
.exit

;*********   Metodos para ordenar numeros   ************


END