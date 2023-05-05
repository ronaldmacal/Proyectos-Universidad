;Metodo principal de impresion
print macro buffer
    mov ax,@data 
    mov ds,ax
    mov ah,09h
    mov dx,offset buffer
    int 21h
endm

;Metodos de espera de caracter
getChar macro
    mov ah,01h
    int 21h
endm
;------------------------------------------------------------------------
;*********   Macros para ordenar archivos ************

;------------------------------------------------------------------------
;------------------------------------------------------------------------
;*********   Macros para cargar archivos ************
Obtenertexto macro buffer
    LOCAL ObtenerChar,FinOT
    xor si,si
    ObtenerChar:
        getChar
        cmp al,0dh
        je FinOT
        mov buffer[si],al
        inc si
        jmp ObtenerChar
    FinOT:
        mov al,24h
        mov buffer[si],al
endm

ObtenerRuta macro buffer
    LOCAL ObtenerChar,FinOT
    xor si,si
    ObtenerChar:
        getChar
        cmp al,0dh
        je FinOT
        mov buffer[si],al
        inc si
        jmp ObtenerChar
    FinOT:
        mov al,00h
        mov buffer[si],al
endm

Abrir macro buffer,handler
    mov ah,3dh
    mov al,02h
    lea dx,buffer
    int 21h
    jc error1
    mov handler,ax
endm

Cerrar macro handler
    mov ah,3eh
    mov bx,handler
    int 21h
    jc error2
endm

Leer macro handler,buffer,numbytes
    mov ah,3fh
    mov bx,handler
    mov cx,numbytes
    lea dx,offset buffer
    mov ah,3fh
    int 21h
    jc error3
endm

Limpiar macro buffer,numbytes,caracter
    LOCAL Repetir
    xor si,si
    xor cx,cx
    mov cx,numbytes
    Repetir:
        mov buffer[si],caracter
        inc si
    Loop Repetir
endm

;---------------------------------------------------------------------------------------
;*************    Macros para los numeros  ********************
Quenumero macro acual,anterior,unidad,decena,numero
    cmp numero,"1" ;si es uno
    jz esuno decena

    cmp numero,"2"
    jz esdos decena

    cmp numero,"3"

    cmp numero,"4"

    cmp numero,"5"

    cmp numero,"6"

    cmp numero,"7"

    cmp numero,"8"

    cmp numero,"9"

    cmp numero,"0"
endm
esuno macro numero:

.exit 
esdos:
.exit 
estres:
.exit 
escuatro:
.exit 
escinco:
.exit
esseis:
.exit
essiete:
.exit
esocho:
.exit
esnueve:
.exit
escero:
.exit