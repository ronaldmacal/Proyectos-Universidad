include macros.asm
.model small
.stack 
.data
    ;-----------------------------------------------------------------------------------------
    ;*************************** Variables de mostrar texto  *********************************
    inicio DB 10,13, "Modo consola iniciado: ","$"
    consola DB 10,13, "consolaapp> ","$"
    saliendo DB 10,13, "Saliendo de consola...","$"
    ;*************************** Variables de mostrar encabezado *****************************
    encab1 DB 10,13, "ARQUITECTURA DE COMPUTADORES Y ENSAMBLADORES 1","$"
    encab2 DB 10,13, "SECCION B","$"
    encab3 DB 10,13, "PRIMER SEMESTRE 2021","$"
    encab4 DB 10,13, "Ronald Oswaldo Macal de León","$"
    encab5 DB 10,13, "201612151","$"
    encab6 DB 10,13, "Proyecto 2, Assembler","$"
    ;*************************** Listado de comandos a utiliazar en el proyecto *************************
    cadena DB 30 dup(0) ;Vector para leer 
    csalir DB 'salir',0
.code

    menu:
    jmp menuprincipal
.exit
;-------------------------------------------------------------------------------------------------
;****************************  Metodos principales del flujo del programa ************************
menuprincipal:
    print inicio
    print consola

    ;Leer la cadena
    mov ah,3fh
    mov bx, 00
    mov cx,30
    mov dx,offset[cadena]
    int 21h

    ;Comando: Salir
    mov si, offset csalir
    mov di, offset cadena
    mov cx,6

    rep movsb
    jz salir
    jmp menuprincipal
.exit

;-------------------------------------------------------------------------------------------------
;****************************  Metodos secundarios del flujo del programa ************************
salir:
    print saliendo
.exit

END