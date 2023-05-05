spila segment stack
    DB 32 DUP('stack__')
spila ends
sdatos segment
    linea DB "**************************************************","$"
    nombre DB "*   Ronald Oswaldo Macal de Leon-201612151-HT2   *","$"
    finpro DB  "*               Final del programa               *"
    divisible DB "   -Divisible","$"
    nodivisible DB "   -No divisible","$"
    lista1 DW 20,50,98,54,35,789,415,347,500,600
    lista2 DW 10,5,2,5,7,63,11,21,50,60
sdatos ends
scodigo segment 'CODE'
    ASSUME SS:spila, DS:sdatos, CS:scodigo

    main proc
    mov ax,sdatos
    mov ds,ax

    mov ah,9
    mov dx, offset linea
    int 21h

    mov ah,2
    mov dl,10
    int 21h

    mov ah,9
    mov dx, offset nombre
    int 21h

    mov ah,2
    mov dl,10
    int 21h

    mov ah,9
    mov dx, offset linea
    int 21h

    mov ah,2
    mov dl,10
    int 21h

    mov bx,18d
    mov cx,0

    L0:
        cmp cx,bx
        jle L1 
        jmp L4
    
    L1:
        mov si, offset lista1
        add si,cx
        mov dx,[si]
        
        mov si, offset lista2
        add si, cx

        L1_1:
            sub dx,[si]
            cmp dx,0
            jl L1_2
            je L1_3
            jmp L1_1

            L1_2:
                mov ah,9
                mov dx, offset nodivisible
                int 21h

                mov ah,2
                mov dl,10
                int 21h

                jmp L1_4
            L1_3:
                mov ah, 9
                mov dx, offset divisible
                int 21h

                mov ah,2
                mov dl,10
                int 21h

            L1_4:
                inc cx
                inc cx
                jmp L0
    L4:
        mov ah,9
        mov dx, offset finpro
        int 21h

    main endp
scodigo ends
end main 