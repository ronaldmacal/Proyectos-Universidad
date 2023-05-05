spila segment stack
    DB 322 DUP('stack__')
spila ends
sdatos segment
    line_break     db      10d,13d, "$"
    line_space     db      32d,"$"
    encabezado1    db     " Universidad de San Carlos de Guatemala ","$"
    encabezado2    db     " Facultad de Ingenieria","$"
    encabezado3    db     " Arquitectura de Computadoras y Ensambladores A     ","$"
    encabezado4    db     " Segundo Semestre 2021","$"
    encabezado5    db     " Examen Final de Laboratorio","$"
    
    encabezadop1     db     "----------------------------------------","$"
    encabezadop2     db     "      Ronald Oswaldo Macal de Leon","$"
    encabezadop3     db     "           CARNET: 201612151","$"
    encabezadop4     db     " ************************************** ","$"
    encabezadop5     db     "        Numeros romanos a decimal","$"
    resultadofinal   db     "Numero decimal: ","$"
    opcion           db     "Ingresar numero romano: ","$"

    numero          db      "0000$$","$"
    numeroromano    db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$","$"
sdatos ends
scodigo segment 'CODE'
    ASSUME SS:spila, DS:sdatos, CS:scodigo
     imprimir macro cadena
        push ax
        push dx        

        mov ah, 09h
        mov dx, offset cadena
        int 21h

        mov ah, 02h 
        mov dl, 10d
        int 21h

        pop dx
        pop ax
    endm

    imprimirj macro cadena
        push ax
        push dx        

        mov ah, 09h
        mov dx, offset cadena
        int 21h

        pop dx
        pop ax
    endm

    siimprimir macro cadena
        push ax
        push dx        

        mov ah, 09h
        mov dx, cadena
        int 21h

        pop dx
        pop ax
    endm

    guardarNumero proc
        push ax
        push bx
        push dx
        push si
        
        L1:
            print opcion
            mov si, offset numero
            mov bl, 0
        
        L2:        
            cmp bl, 4d
            jge final

            mov ah,01
            int 21h

            mov [si],al

            inc si
            inc bl
            jmp L2

        final:
            mov [si],36d
            pop si
            pop dx
            pop bx
            pop ax
            ret
    guardarNumero endp

    borrarNumero proc
    push si
        push bx
        mov si, offset numero
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        mov [si], 36d
        inc si
        pop bx
        pop si
        ret
    borrarNumero endp

    mostrarmenu proc
        push ax
        push bx
        push si
        push di
    menuprincipal:
        imprimir encabezado1
        imprimir encabezado2
        imprimir encabezado3
        imprimir encabezado4
        imprimir encabezado5
        imprimir encabezadop1
        imprimir encabezadop2
        imprimir encabezadop3
        imprimir encabezadop4
        imprimir encabezadop5
        
        call guardarNumero
        imprimirj line_break
        imprimirj resultadofinal
        jmp menuprincipal
    limpiarpila:
        pop di
        pop si
        pop bx
        pop ax
    mostrarmenu endp

    main proc
        push ds
        mov si,0
        push si
        mov ax, sdatos
        mov ds, ax
        mov es, ax
        call mostrarmenu
    main endp
scodigo ends
end main