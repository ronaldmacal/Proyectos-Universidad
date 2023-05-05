.model small
.stack
.data
    opcion1 DB 10,13,"GENERAR REPORTE","$"
.code
    main:
    mov ax, seg @data
    mov ds,ax ;Cargar la data

    mov ah,09h ;Imprimir cadena
    lea dx,opcion1
    int 21h

.exit
END