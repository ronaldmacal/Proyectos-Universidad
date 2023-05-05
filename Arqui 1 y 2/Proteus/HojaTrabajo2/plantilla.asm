spila segment stack
    DB 32 DUP('stack__')
spila ends
sdatos segment

sdatos ends

scodigo sement 'CODE'
    ASSUME SS:spila, DS:sdatos,CS:scodigo

    main proc far
        push DS
        push 0
        mov ax,sdatos
        mov ds,ax
        mov es,ax
scodigo ends
endmain