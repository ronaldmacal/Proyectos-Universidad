print macro buffer
    mov ax,@data 
    mov ds,ax
    mov ah,09h
    mov dx,offset buffer
    int 21h
endm

getChar macro
    mov ah,01h
    int 21h
endm