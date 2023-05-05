spila segment stack
    DB 32 DUP ('stack___')
spila ends

sdatos segment
    header0     db     "|----------------------------------------|","$"
    header1     db     "|  Steven Jocol   -   201602938  -  P3   |","$"
    header2     db     "| ************************************** |","$"
    header3     db     "|       JUEGO DE DAMAS EN ASEMBLER       |","$"
    menu_1      db     "| 1. Registrar jugadores                 |","$"
    menu_2      db     "| 2. Jugar/Reanudar                      |","$"
    menu_3      db     "| 3. Salir                               |","$"
    menu_4      db     "|                                        |","$"
    menu_5      db     "| Ingresa opcion: ","$"
    header4     db     "|----------------------------------------|","$"
    header5     db     "|        Modo Registrar Jugadores        |","$"
    header6     db     "| ************************************** |","$"
    menu_6      db     "| 1. Registrar jugador 1                 |","$"
    menu_7      db     "| 2. Registrar jugador 2                 |","$"
    menu_8      db     "| Ingresa un nombre: ","$"
    menu_inc    db     "| No ingresaste una opcion valida...     |","$"
    
    
    header7     db     "|----------------------------------------|","$"
    header8     db     "|               Modo Juego               |","$"
    header9     db     "| ************************************** |","$"
    header10    db     "| Turno de: ","$"
    header11    db     "| Punteo actual: ","$"
    header12    db     "| Fichas: Negras","$"
    header13    db     "| Fichas: Blancas","$"
    
    juego1      db     "| Ingresa comando: ","$"
    juego2      db     "| Comando invalido...                    |","$"
    juego3      db     "| ERROR! -> Faltan jugadores...          |","$"
    juego4      db     "| ERROR! -> No es tu ficha...            |","$"
    juego5      db     "| ERROR! -> Movimiento invalido...       |","$"
    juego6      db     "| Origen: ","$"
    juego7      db     "| Destino: ","$"
    footer0     db     "| ---------- Fin del programa -----------|","$"

    tabLimit    db     "    1  2  3  4  5  6  7  8 ","$"

    comando     db "$$$$$","$"
    punteo1     db "000000","$"
    punteo2     db "000000","$"
    jugador1    db 15 DUP("$")
    jugador2    db 15 DUP("$")
    turno       db 0
    stopgame    db 0
    
    tablero    db 0,2,0,2,0,2,0,2
               db 2,0,2,0,2,0,2,0
               db 0,2,0,2,0,2,0,2
               db 1,0,1,0,1,0,1,0
               db 0,1,0,1,0,1,0,1
               db 4,0,4,0,4,0,4,0
               db 0,4,0,4,0,4,0,4
               db 4,0,4,0,4,0,4,0

    ub_o       dw 0
    ub_d       dw 0

    opos_y     db 0
    opos_x     db 0
    opos_ym2   db 0
    opos_ym1   db 0
    opos_xm2   db 0
    opos_xm1   db 0

    opos_yp2   db 0
    opos_yp1   db 0
    opos_xp2   db 0
    opos_xp1   db 0

    dpos_y     db 0
    dpos_x     db 0

    dpos_ym2   db 0
    dpos_ym1   db 0
    dpos_xm2   db 0
    dpos_xm1   db 0

    dpos_yp2   db 0
    dpos_yp1   db 0
    dpos_xp2   db 0
    dpos_xp1   db 0



    
    
sdatos ends

scodigo segment 'CODE'

    ASSUME SS:spila, DS:sdatos, CS:scodigo
    
    println macro cadena
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

    print macro cadena
        push ax
        push dx        

        mov ah, 09h
        mov dx, offset cadena
        int 21h

        pop dx
        pop ax
    endm

    saveName proc
        push ax
        push bx
        push dx
        push si

        push ax
        mov ah, 02
        mov dl, 10d
        int 21h
        pop ax

        cmp al, 49d
        je sN_L1
        cmp al, 50d
        je sN_L2

        sN_L1:
            print menu_8
            mov si, offset jugador1
            mov bl, 0
            jmp sN_L3
        
        sN_L2:
            print menu_8
            mov si, offset jugador2  
            mov bl, 0

        sN_L3:
            cmp bl, 14d
            jge sN_Pf

            mov ah,01
            int 21h

            cmp al, 13d
            je sN_F

            mov [si],al
            inc si
            inc bl
            jmp sN_L3

        sN_Pf:
            mov ah, 02
            mov dl, 10d
            int 21h
        sN_F:
            mov [si],36d

        pop si
        pop dx
        pop bx
        pop ax
        ret
    saveName endp

    menuRegistro proc
        push ax
    mR_i:
        println header4
        println header5
        println header6
        println menu_6
        println menu_7
        print menu_5
        
        mov ah, 01
        int 21h

        cmp al,49d
        je mR_L1
        cmp al,50d
        je mR_L1

        println menu_inc
        jmp mR_i ; opcion invalida, repito el menu

        mR_L1: ; ingresar nombre
            call saveName
        pop ax
        ret
    menuRegistro endp

    prepareVars proc
        push ax
        push bx
        push cx
        push dx
        push si
        push di

        ;si -> ubicacion origen
        ;di -> ubicacion destino

        ;cl -> Pos Y origen
        ;ch -> Pos X origen
        ;dl -> Pos Y destino
        ;dh -> Pos X destino

        ;bl valor origen
        ;bh valor destino

        push bx
        mov bx, offset ub_o
        mov [bx], si

        mov bx, offset ub_d
        mov [bx], di
        pop bx

        mov di, offset opos_y
        mov al, cl
        mov [di], al
        
        inc al
        inc al
        mov di, offset opos_yp2
        mov [di], al
        
        dec al
        mov di, offset opos_yp1
        mov [di], al

        dec al
        dec al
        mov di, offset opos_ym1
        mov [di], al

        dec al
        mov di, offset opos_ym2
        mov [di], al

        ;;

        mov di, offset opos_x
        mov al, ch
        mov [di], al
        
        inc al
        inc al
        mov di, offset opos_xp2
        mov [di], al
        
        dec al
        mov di, offset opos_xp1
        mov [di], al

        dec al
        dec al
        mov di, offset opos_xm1
        mov [di], al

        dec al
        mov di, offset opos_xm2
        mov [di], al

        ;;

        mov di, offset dpos_y
        mov al, dl
        mov [di], al
        
        inc al
        inc al
        mov di, offset dpos_yp2
        mov [di], al
        
        dec al
        mov di, offset dpos_yp1
        mov [di], al

        dec al
        dec al
        mov di, offset dpos_ym1
        mov [di], al

        dec al
        mov di, offset dpos_ym2
        mov [di], al

        ;;

        mov di, offset dpos_x
        mov al, dh
        mov [di], al
        
        inc al
        inc al
        mov di, offset dpos_xp2
        mov [di], al
        
        dec al
        mov di, offset dpos_xp1
        mov [di], al

        dec al
        dec al
        mov di, offset dpos_xm1
        mov [di], al

        dec al
        mov di, offset dpos_xm2
        mov [di], al

        pop di
        pop si
        pop dx
        pop cx
        pop bx
        pop ax
        ret
    prepareVars endp

    valTab_toAL macro posy, posx
        push bx
        push cx
        push dx
        push si
        push di

        
        xor ax,ax
        mov al, 8

        mov si, offset posy
        mov cl, [si]

        mov si, offset posx
        mov ch, [si]

        mul cl
        add al, ch
        adc al, 00
                   
        mov di, offset tablero
        add di, ax
        
        mov al, [di] ; guardando valr en al

        pop di
        pop si
        pop dx
        pop cx
        pop bx
    endm

    setValTab macro posy, posx, newval
        push ax
        push bx
        push cx
        push dx
        push si
        push di

        push ax
        xor ax,ax
        mov al, 8

        mov si, offset posy
        mov cl, [si]

        mov si, offset posx
        mov ch, [si]

        mul cl
        add al, ch
        adc al, 00
                   
        mov di, offset tablero
        add di, ax

        pop ax
        mov [di], newval

        pop di
        pop si
        pop dx
        pop cx
        pop bx
        pop ax
    endm

    changeTurno proc
        push bx
        push cx
        push dx
        push si
        push di

        ; mov si, offset turno
        ; mov al, [si]

        ; cmp al, 00
        ; je incturn
        ; jg resturn

        ; resturn:
        ;     mov [si], 00
        ;     jmp changedturn
        ; incturn:
        ;     mov [si], 1
            
        ; changedturn:
        pop di
        pop si
        pop dx
        pop cx
        pop bx
        ret
    changeTurno endp

    runComando proc
        push ax
        push bx
        push cx
        push dx

        ; dl -> origen, destino
        ; cl -> Pos y
        ; ch -> Pos x
        ; bl -> Valor origen
        ; bh -> Valor destino

        mov dl, 00000000        
        mov si, offset comando

    lc_cs_get_pos:
        mov cl,[si]
        cmp cl, 72d
        jle lc_cs_may
    
    ;caso comando minusculas
        sub cl, 97d 
        jmp lc_cs_xy
    
    ;caso comando mayusculas
    lc_cs_may: 
        sub cl, 65d

    lc_cs_xy:
        ;push cl ; guardo pos y en pila
        inc si
        mov ch, [si]
        sub ch, 49d
        push cx ; guardo pos x en pila

        xor ax,ax
        mov al, 8
        mul cl
        add al, ch
        adc al, 00
                   
        mov di, offset tablero
        add di, ax
        
        cmp dl, 1
        je lc_cs_y

    lc_cs_x:
        mov bl, [di] ; guardando origen
        push di
        inc si
        inc si
        inc dl
        jmp lc_cs_get_pos
    lc_cs_y:
        mov bh, [di] ; guardando destino
        push di

        ;si -> ubicacion origen
        ;di -> ubicacion destino
        ;cl -> Pos Y origen
        ;ch -> Pos X origen
        ;dl -> Pos Y destino
        ;dh -> Pos X destino

        ;bl valor origen
        ;bh valor destino

        pop di
        pop cx
        mov dh, ch
        mov dl, cl
        ;pop dh
        ;pop dl
        pop si
        pop cx

        call prepareVars

        mov si, offset turno
        mov al, [si]
        cmp al, 1
        je rc_turno_j2
        rc_turno_j1:
            cmp dl, cl
            jl rc_tj1_retroceder
            jg rc_tj1_avanzar
            je rc_tj1_error

            rc_tj1_retroceder:
                cmp bl, 3
                je rc_tj1_retroceder_si
                cmp bl, 5
                je rc_tj1_retroceder_si
                jmp rc_tj1_retroceder_no

                rc_tj1_retroceder_si:
                    mov si, offset opos_ym2
                    mov al, [si]
                    cmp dl, al
                    je rc_tj1_retroceder_2
                    mov si, offset opos_ym1
                    mov al, [si]
                    cmp dl, al
                    je rc_tj1_retroceder_1
                    jmp rc_tj1_error

                    rc_tj1_retroceder_2:
                        mov si, offset opos_xm2
                        mov al, [si]
                        cmp dh, al
                        je rc_tj1_retroceder_2_izq
                        mov si, offset opos_xp2
                        mov al, [si]
                        cmp dh, al
                        je rc_tj1_retroceder_2_der
                        jmp rc_tj1_error

                        rc_tj1_retroceder_2_izq:
                            valTab_toAL dpos_ym2, dpos_xm2
                            cmp al, 1
                            jne rc_tj1_error
                            valTab_toAL opos_ym1, opos_xm1 ;macro
                            cmp al, 4
                            je rc_tj1_retroceder_2_izq_comer
                            cmp al, 5
                            je rc_tj1_retroceder_2_izq_comer
                            jmp rc_tj1_error

                            rc_tj1_retroceder_2_izq_comer:
                                setValTab opos_y, opos_x, 1
                                setValTab opos_ym1, opos_xm1, 1
                                setValTab dpos_y, dpos_x, 3
                                call changeTurno
                                jmp rc_fin

                        rc_tj1_retroceder_2_der:
                            valTab_toAL dpos_ym2, dpos_xp2
                            cmp al, 1
                            jne rc_tj1_error
                            valTab_toAL opos_ym1, opos_xp1 ;macro
                            cmp al, 4
                            je rc_tj1_retroceder_2_der_comer
                            cmp al, 5
                            je rc_tj1_retroceder_2_der_comer
                            jmp rc_tj1_error

                            rc_tj1_retroceder_2_der_comer:
                                setValTab opos_y, opos_x, 1
                                setValTab opos_ym1, opos_xp1, 1
                                setValTab dpos_y, dpos_x, 3
                                call changeTurno
                                jmp rc_fin

                    rc_tj1_retroceder_1: ;; retroceder solo uno
                        mov si, offset opos_xm1
                        mov al, [si]
                        cmp dh, al
                        je rc_tj1_retroceder_1_izq
                        mov si, offset opos_xp1
                        mov al, [si]
                        cmp dh, al
                        je rc_tj1_retroceder_1_der
                        jmp rc_tj1_error

                        rc_tj1_retroceder_1_izq:
                            valTab_toAL opos_ym1, opos_xm1 ;macro
                            cmp al, 1
                            je rc_tj1_retroceder_1_izq_mover
                            jmp rc_tj1_error

                            rc_tj1_retroceder_1_izq_mover:
                                setValTab opos_y, opos_x, 1
                                setValTab dpos_y, dpos_x, 3
                                call changeTurno
                                jmp rc_fin

                        rc_tj1_retroceder_1_der:
                            valTab_toAL opos_ym1, opos_xp1 ;macro
                            cmp al, 1
                            je rc_tj1_retroceder_1_der_mover
                            jmp rc_tj1_error

                            rc_tj1_retroceder_1_der_mover:
                                setValTab opos_y, opos_x, 1
                                setValTab dpos_y, dpos_x, 3
                                call changeTurno
                                jmp rc_fin
                           
                rc_tj1_retroceder_no:
                    println juego5
                    jmp rc_fin

            rc_tj1_avanzar:
                mov si, offset opos_yp2
                mov al, [si]
                cmp dl, al
                je rc_tj1_avanzar_2
                mov si, offset opos_yp1
                mov al, [si]
                cmp dl, al
                je rc_tj1_avanzar_1
                jmp rc_tj1_error

                rc_tj1_avanzar_2:
                    mov si, offset opos_xm2
                    mov al, [si]
                    cmp dh, al
                    je rc_tj1_avanzar_2_izq
                    mov si, offset opos_xp2
                    mov al, [si]
                    cmp dh, al
                    je rc_tj1_avanzar_2_der
                    jmp rc_tj1_error

                    rc_tj1_avanzar_2_izq:
                        valTab_toAL dpos_yp2, dpos_xm2
                        cmp al, 1
                        jne rc_tj1_error
                        valTab_toAL opos_yp1, opos_xm1 ;macro
                        cmp al, 4
                        je rc_tj1_avanzar_2_izq_comer
                        cmp al, 5
                        je rc_tj1_avanzar_2_izq_comer
                        jmp rc_tj1_error

                        rc_tj1_avanzar_2_izq_comer:
                            setValTab opos_yp1, opos_xm1, 1

                            mov si, offset dpos_x
                            mov al, [si]
                            cmp al, 8
                            jne rc_tj1_avanzar_2_izq_comer_normal

                            rc_tj1_avanzar_2_izq_comer_coronar:
                                setValTab opos_y, opos_x, 1
                                setValTab dpos_y, dpos_x, 3
                                call changeTurno
                                jmp rc_fin
                            rc_tj1_avanzar_2_izq_comer_normal:
                                mov si, offset ub_o
                                mov ax, [si]
                                mov si, ax
                                mov ah, [si]
                                setValTab dpos_y, dpos_x, ah
                                setValTab opos_y, opos_x, 1
                                call changeTurno
                                jmp rc_fin

                    rc_tj1_avanzar_2_der:
                        valTab_toAL dpos_yp2, dpos_xp2
                        cmp al, 1
                        jne rc_tj1_error
                        valTab_toAL opos_yp1, opos_xp1 ;macro
                        cmp al, 4
                        je rc_tj1_avanzar_2_der_comer
                        cmp al, 5
                        je rc_tj1_avanzar_2_der_comer
                        jmp rc_tj1_error

                        rc_tj1_avanzar_2_der_comer:
                            setValTab opos_yp1, opos_xp1, 1

                            mov si, offset dpos_x
                            mov al, [si]
                            cmp al, 8
                            jne rc_tj1_avanzar_2_der_comer_normal

                            rc_tj1_avanzar_2_der_comer_coronar:
                                setValTab opos_y, opos_x, 1
                                setValTab dpos_y, dpos_x, 3
                                call changeTurno
                                jmp rc_fin
                            rc_tj1_avanzar_2_der_comer_normal:
                                mov si, offset ub_o
                                mov ax, [si]
                                mov si, ax
                                mov ah, [si]
                                setValTab dpos_y, dpos_x, ah
                                setValTab opos_y, opos_x, 1
                                call changeTurno
                                jmp rc_fin

                rc_tj1_avanzar_1: ;; avanzar solo uno
                    mov si, offset opos_xm1
                    mov al, [si]
                    cmp dh, al
                    je rc_tj1_avanzar_1_izq
                    mov si, offset opos_xp1
                    mov al, [si]
                    cmp dh, al
                    je rc_tj1_avanzar_1_der
                    jmp rc_tj1_error

                    rc_tj1_avanzar_1_izq:
                        valTab_toAL opos_yp1, opos_xm1 ;macro
                        cmp al, 1
                        je rc_tj1_avanzar_1_izq_mover
                        jmp rc_tj1_error

                        rc_tj1_avanzar_1_izq_mover:
                            mov si, offset dpos_x
                            mov al, [si]
                            cmp al, 8
                            jne rc_tj1_avanzar_1_izq_mover_normal

                            rc_tj1_avanzar_1_izq_mover_coronar:
                                setValTab opos_y, opos_x, 1
                                setValTab dpos_y, dpos_x, 3
                                call changeTurno
                                jmp rc_fin
                            rc_tj1_avanzar_1_izq_mover_normal:
                                mov si, offset ub_o
                                mov ax, [si]
                                mov si, ax
                                mov ah, [si]
                                setValTab dpos_y, dpos_x, ah
                                setValTab opos_y, opos_x, 1
                                call changeTurno
                                jmp rc_fin

                    rc_tj1_avanzar_1_der:
                        valTab_toAL opos_yp1, opos_xp1 ;macro
                        cmp al, 1
                        je rc_tj1_avanzar_1_der_mover
                        jmp rc_tj1_error

                        rc_tj1_avanzar_1_der_mover:
                            mov si, offset dpos_x
                            mov al, [si]
                            cmp al, 8
                            jne rc_tj1_avanzar_1_der_mover_normal

                            rc_tj1_avanzar_1_der_mover_coronar:
                                setValTab opos_y, opos_x, 1
                                setValTab dpos_y, dpos_x, 3
                                call changeTurno
                                jmp rc_fin
                            rc_tj1_avanzar_1_der_mover_normal:
                                mov si, offset ub_o
                                mov ax, [si]
                                mov si, ax
                                mov ah, [si]
                                setValTab dpos_y, dpos_x, ah
                                setValTab opos_y, opos_x, 1
                                call changeTurno
                                jmp rc_fin
                           
            rc_tj1_error:
                println juego5
                jmp rc_fin


            
        rc_turno_j2:    

    rc_fin:
        pop dx
        pop cx
        pop bx
        pop ax
        ret
    runComando endp

    leer_comando proc
        push si
        push bx
        push ax

        com_i:
            mov si, offset comando
            mov bl, 0
            print juego1

            com_L1:
                cmp bl, 4
                jg com_L2

                mov ah, 01
                int 21h

                cmp al, 13d
                je com_L2
                
                mov [si], al
                inc bl
                inc si
                jmp com_L1
            
            com_L2:
                mov [si], 36d

                mov ah, 02
                mov dl, 10d
                int 21h
                
                ;Is valid comand?
                    mov si, offset comando
                    ;Primera letra
                    mov bl, [si]
                    
                    cmp bl, 65d
                    jl com_vc_FF
                    cmp bl, 72d
                    jle com_vc_FV2
                    cmp bl, 97d
                    jl com_vc_FF
                    cmp bl, 104d
                    jle com_vc_FV2
                    jmp com_vc_FF

                    com_vc_FV2:
                        ;Primer Numero
                        inc si
                        mov bl, [si]

                        cmp bl, 88d
                        je com_vc_FF_exit2
                        cmp bl, 120d
                        je com_vc_FF_exit2
                    
                        cmp bl, 49d
                        jl com_vc_FF
                        cmp bl, 56d
                        jg com_vc_FF
                    com_vc_FV3:
                        ;Dos puntos
                        inc si
                        mov bl, [si]
                    
                        cmp bl, 58d
                        jne com_vc_FF
                    com_vc_FV4:
                        ;Segunda letra
                        inc si
                        mov bl, [si]
                        
                        cmp bl, 65d
                        jl com_vc_FF
                        cmp bl, 72d
                        jle com_vc_FV5
                        cmp bl, 97d
                        jl com_vc_FF
                        cmp bl, 104d
                        jg com_vc_FF
                    com_vc_FV5:
                        ;segundo numero
                        inc si
                        mov bl, [si]
                    
                        cmp bl, 49d
                        jl com_vc_FF
                        cmp bl, 56d
                        jg com_vc_FF_F
                        call runComando
                        jmp com_L3
                    com_vc_FF:
                        cmp bl, 82d
                        je com_vc_FF_rep
                        cmp bl, 114d
                        je com_vc_FF_rep
                        jmp com_vc_FF_exit

                        com_vc_FF_rep:
                            inc si
                            mov bl, [si]

                            cmp bl, 69d
                            je com_vc_FF_rep1
                            cmp bl, 101d
                            je com_vc_FF_rep1
                            jmp com_vc_FF_F
                        com_vc_FF_rep1:
                            inc si
                            mov bl, [si]

                            cmp bl, 80d
                            je com_vc_FF_rep2
                            cmp bl, 112d
                            je com_vc_FF_rep2
                            jmp com_vc_FF_F
                        com_vc_FF_rep2:
                            ;call runComando
                            jmp com_L3
                        com_vc_FF_exit:
                            cmp bl, 69d
                            je com_vc_FF_exit1
                            cmp bl, 101d
                            je com_vc_FF_exit1
                            jmp com_vc_FF_F
                        com_vc_FF_exit1:
                            inc si
                            mov bl, [si]

                            cmp bl, 88d
                            je com_vc_FF_exit2
                            cmp bl, 120d
                            je com_vc_FF_exit2
                            jmp com_vc_FF_F
                        com_vc_FF_exit2:
                            inc si
                            mov bl, [si]

                            cmp bl, 73d
                            je com_vc_FF_exit3
                            cmp bl, 105d
                            je com_vc_FF_exit3
                            jmp com_vc_FF_F
                        com_vc_FF_exit3:
                            inc si
                            mov bl, [si]

                            cmp bl, 84d
                            je com_vc_FF_exit4
                            cmp bl, 116d
                            je com_vc_FF_exit4
                            jmp com_vc_FF_F
                        com_vc_FF_exit4:
                            mov si, offset stopgame
                            mov [si], 1
                            jmp com_L3
                        com_vc_FF_F:
                            println juego2
                            jmp com_i
                ;End verification
            com_L3:
        pop ax
        pop bx
        pop si    
        ret 
    leer_comando endp

    imprimirTablero proc
        push ax
        push bx
        push cx
        push dx
        push si
        push di        

        xor dx, dx
        mov dh, 8 ; total 0-7 = 8
        mov cl, 0 ; filas
        mov ch, 0 ; columnas

        println tabLimit

        it_L0: ;ciclo de filas
            cmp cl,dh
            jge it_LF
            it_L1: ;ciclo de columnas
                cmp ch, dh
                jge it_L1_F

                ;iprimiendo linea aqui
                cmp ch, 0
                jg it_L1_S
                ;println col
                mov ah, 02
                mov dl, 32d
                int 21h

                mov dl, 65d
                add dl, cl
                int 21h

                mov ah, 02
                mov dl, 32d
                int 21h

                it_L1_S: ; caso >= segunda
                    
                    ;get tablero value
                    xor ax,ax
                    mov al, dh
                    mul cl
                    add al, ch
                    adc al, 00
                    
                    mov si, offset tablero
                    add si, ax
                    
                    mov dl, [si]

                    push dx ;guardar valores
                    push ax ;guardar valores
                    cmp dl,0
                    jne it_L1_V0
                        mov ah, 02
                        mov dl, 219d
                        int 21h
                        jmp it_L1_V0_F
                    it_L1_V0:
                        mov ah, 02
                        mov dl, 32d
                        int 21h
                    it_L1_V0_F: 
                    pop ax ; recuperar valores
                    pop dx ; recuperar valores

                    ;saber como esta ocupado el espacio
                    ;mov dl, [si]
                    cmp dl, 2
                    je it_F_1
                    cmp dl, 3
                    je it_F_12
                    cmp dl, 4
                    je it_F_2
                    cmp dl, 5
                    je it_F_22
                    jmp it_F_V
                        it_F_1: ;jugador 1 peon 01h ascii
                            mov ah, 02
                            mov dl, 01
                            int 21h

                            mov ah, 02
                            mov dl, 32d
                            int 21h
                            jmp it_F_F
                        it_F_12: ;jugador 1 reina
                            mov ah, 02
                            mov dl, 01
                            int 21h

                            mov ah, 02
                            mov dl, 253d
                            int 21h
                            jmp it_F_F
                        it_F_2: ;jugador 2 peon
                            mov ah, 02
                            mov dl, 02
                            int 21h

                            mov ah, 02
                            mov dl, 32d
                            int 21h
                            jmp it_F_F
                        it_F_22: ;jugador 2 reina
                            mov ah, 02
                            mov dl, 02
                            int 21h

                            mov ah, 02
                            mov dl, 253d
                            int 21h
                            jmp it_F_F
                        it_F_V: ;vacia
                            cmp dl,1
                            je it_F_V_1                          

                            mov ah, 02
                            mov dl, 219d
                            int 21h

                            mov ah, 02
                            mov dl, 219d
                            int 21h

                            jmp it_F_F

                            it_F_V_1:
                                mov ah, 02
                                mov dl, 32d
                                int 21h

                                mov ah, 02
                                mov dl, 32d
                                int 21h
                    it_F_F:                    
                    inc ch
                    jmp it_L1
            it_L1_F: ; fin ciclo de columnas
                mov ah, 02
                mov dl, 32d
                int 21h

                mov dl, 65d
                add dl, cl
                int 21h

                mov ah, 2
                mov dl, 10
                int 21h
                ;println row

                mov ch, 0
                inc cl
                jmp it_L0
    it_LF: ; fin ciclo de filas
        println tabLimit

        pop di
        pop si
        pop dx
        pop cx
        pop bx
        pop ax
        ret
    imprimirTablero endp

    cicloJuego proc
        push bx
        push si

    cJ_i:
        println header7
        println header8
        println header9
        print header10
        mov si, offset turno
        mov bl, [si]
        cmp bl, 1
        je cJ_L2
        cJ_L1:
            println jugador1
            print header11
            println punteo1
            println header12
            jmp cJ_t
        cJ_L2:
            println jugador2
            print header11
            println punteo2
            println header12

        cJ_t:
            println header7
            call imprimirTablero
            cJ_Com:
                call leer_comando
                mov si, offset stopgame
                mov bl, [si]
                cmp bl,1
                je cJ_E
                jmp cJ_i
            cJ_E:
        pop si
        pop bx
        ret
    cicloJuego endp

    printMenu proc   
        push ax
        push bx
        push si
        push di
    pm_i:
        println header0
        println header1
        println header2
        println header3
        println header2
        
        println menu_1
        println menu_2
        println menu_3
        println menu_4
        print menu_5
        
        mov ah, 01
        int 21h

            println menu_4
            
            cmp al,49d
            je pm_LF_1

            cmp al,50d
            je pm_LF_2

            cmp al,51d
            je pm_LF_3
            
            pm_LF_Inc:
                println menu_inc
                jmp pm_i ; opcion invalida, repito el menu

            pm_LF_1:
                call menuRegistro
                jmp pm_i

            pm_LF_2:
                mov si, offset stopgame
                mov [si],00

                mov si, offset jugador1
                mov bl, [si]

                cmp bl, 36d
                je cJ_n_py

                mov si, offset jugador2
                mov bl, [si]
                
                cmp bl, 36d
                je cJ_n_py

                jmp cJ_y_py

                cJ_n_py:
                    println header9
                    println juego3                     
                    println header9
                    jmp pm_i
                
                cJ_y_py:
                    call cicloJuego
                    jmp pm_i

            pm_LF_3:
                println footer0

        pm_LF_F:
            pop di
            pop si
            pop bx
            pop ax
            ret
    printMenu endp

    main proc
        push ds
        mov si,0
        push si
        mov ax, sdatos
        mov ds, ax
        mov es, ax

        call printMenu
        
    main endp

scodigo ends
end main