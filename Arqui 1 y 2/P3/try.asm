include macros.asm
.model small
.stack 64
.data
    pendejo DB 10,13,"Hay uno","$"
    uni DB 0
    dece DB 0
    num DB 0
    numerohecho DB 0
    ;------------------------------------------------------------------
    ;*********Variables usadas para la lectura del archivo
    err1 DB 10,13,"Error, el archivo ingresado no existe. Vuelva a ingresar la ruta y la extension","$"
    err2 DB 10,13,"Error, no se puede cerrar el archivo","$"
    err3 DB 10,13,"Error al leer el archivo","$"
    cierre DB 10,13,"Archivo cerrado con exito","$"
    pregunta DB 10,13,"Ingrese la ruta del archivo que se desea abrir y extension",0ah,0dh,"  Ejemplo ruta: C:\entrada.xml","$"
    bufferentrada DB 50 dup('$')
    handlerentrada DW ?
    bufferinformacion DB 1 dup('$')
    salto DB 10,13, " ","$"
    aux DB "$"
    ;------------------------------------------------------------------
.code
    main:
    jmp abrirarchivo
.exit
abrirarchivo:
    print salto
    print pregunta
    print salto
    ObtenerRuta bufferentrada
    Abrir bufferentrada,handlerentrada
    mov aux,"i"
    jmp extraernumeros
.exit

extraernumeros:
    Leer handlerentrada,bufferinformacion,1
    cmp ax,0
    jz cerrararchivo
    mov dl,bufferinformacion
    Quenumero dl,aux,uni,dece,num,numerohecho
    cmp numerohecho,1
    jz guardarenarreglo
    mov aux,dl ;Guardar el anterior
    jmp extraernumeros
.exit
guardarenarreglo:
    ;Guardar el numero en el arreglo
    
    ret
.exit
cerrararchivo:
    Cerrar handlerentrada
    print salto
    print cierre
    print salto
.exit
error1:
    print salto
    print err1
    getChar
    jmp main
.exit
error2:
    print salto
    print err2
    getChar
    jmp main
.exit
error3:
    print salto
    print err3
    getChar
    jmp main
.exit
END
