spila segment stack
    DB 32 DUP('stack__')
spila ends
sdatos segment
    separador       DB "******************************************","$"
    menuerror       DB "<- Opcion invalida, vuelva a intentarlo  *","$"
    blanco          DB "                                        ","$"
    salidaprograma  DB " Fin programa. Saliendo...","$"
    ;Menu principal: inicial con las opciones de muestra
    menuinicio0     DB "*         MENU - JUEGO DE DAMAS          *","$"
    menuinicio1     DB "*----------------------------------------*","$"
    menuinicio2     DB "*  1. Registrar jugadores                *","$"
    menuinicio3     DB "*  2. Modo de juego                      *","$"
    menuinicio4     DB "*  3. Mostrar datos                      *","$"
    menuinicio5     DB "*  4. Salir                              *","$"
    menuinicio6     DB "*  Ingrese una opcion:                   *","$"
    menuinicio7     DB "*----------------------------------------*","$"

    ;Menu de registrar jugadores
    menuregistro0   DB "*        Registrar nuevo jugador         *","$"
    menuregistro1   DB "*----------------------------------------*","$"
    menuregistro2   DB "* 1. Registrar jugador 1                 *","$"
    menuregistro3   DB "* 2. Registrar jugador 2                 *","$"
    menuregistro4   DB "* Ingrese una opcion:                    *","$"
    menuregistro5   DB "*----------------------------------------*","$"
    menuregistro6   DB "* Ingrese un nombre:  ","$"

    ;Variables que guardan los datos de los jugadores
    jugador1        DB 20 DUP("$")
    jugador2        DB 20 DUP("$")

    ;Menu de Modo de juego
    menujuego0      DB "*              Modo de juego             *","$"
    menujuego1      DB "*----------------------------------------*","$"
    menujuego2      DB "* Turno actual: ","$"
    menujuego3      DB "* Color: Negras ","$"
    menujuego4      DB "* Color: Blancas ","$"
    menujuego5      DB "* Punteo actual: ","$"
    menujuego6      DB "* Tablero actual ","$"
    menujuego7      DB "* Ingrese un comando: ","$"
    juegoerror0     DB "* Error: Comando inexistente ","$"
    juegoerror1     DB "* Error: No existen jugadores suficientes ","$"
    juegoerror2     DB "* Error: Movimiento no valido o ficha invalida","$"

    ;Variables del modo juego
    punteojugador1  DB "000","$"
    punteojugador2  DB "000","$"
    turno           DB 0 ;Variable que indica de quien es que le toca jugar.
    detenerjuego    DB 0
    comando_actual  DB 10 DUP("$")

    ;Variables estáticas utilizadas para los movimientos de las fichas y coordenadas
    c_origen        DW 0
    c_destino       DW 0
    origeny         DB 0
    origenx         DB 0
    destinoy        DB 0
    destinox        DB 0
    origenxm1       DB 0
    origenym1       DB 0
    origenxm2       DB 0
    origenym2       DB 0
    origenxp1       DB 0
    origenyp1       DB 0
    origenxp2       DB 0
    origenyp2       DB 0

    destinoxm1      DB 0
    destinoym1      DB 0
    destinoxm2      DB 0
    destinoym2      DB 0
    destinoxp1      DB 0
    destinoyp1      DB 0
    destinoxp2      DB 0
    destinoyp2      DB 0

    ;Tablero
    encabezado_tab  DB "   1  2  3  4  5  6  7  8 ","$"
    ;1-> Indica ficha de jugador 1. 4->Indica ficha de jugador 2. 3->Espacio disponible para pasar
    tablerojuego    DB 0,1,0,1,0,1,0,1
                    DB 1,0,1,0,1,0,1,0
                    DB 0,1,0,1,0,1,0,1
                    DB 3,0,3,0,3,0,3,0
                    DB 0,3,0,3,0,3,0,3
                    DB 4,0,4,0,4,0,4,0
                    DB 0,4,0,4,0,4,0,4
                    DB 4,0,4,0,4,0,4,0
    
    ;Datos personales
    datospersonales0 DB "*----------------------------------------*","$"
    datospersonales1 DB "*     Ronald Oswaldo Macal de Leon       *","$"
    datospersonales2 DB "*               201612151                *","$"
    datospersonales3 DB "*              Practica  3               *","$"
    datospersonales4 DB "*----------------------------------------*","$"
sdatos ends

scodigo segment 'CODE'
    ASSUME SS:spila, DS:sdatos,CS:scodigo
    ;Macro que muestra las cadenas con salto de linea
;------------------------------------------------------------------------------------------------------------------
    imprimir macro cadena
        push ax
        push dx

        ;Imprimir cadena
        mov ah,09h
        mov dx, offset cadena
        int 21h

        ;Interrupcion salto de linea
        mov ah,02h
        mov dl,10d
        int 21h

        pop dx
        pop ax;Se limpian los registros dx, ax
    endm 
;------------------------------------------------------------------------------------------------------------------
    ;Macro que pinta el tablero sin el salto de linea
    pintar macro cadena
        push ax
        push dx

        ;Imprimir cadena
        mov ah,09h
        mov dx, offset cadena
        int 21h

        pop dx
        pop ax;Se limpian los registros dx, ax
    endm 
;------------------------------------------------------------------------------------------------------------------
    juego_enciclado proc
        push bx
        push si
    
    cargar_juego:
        imprimir separador
        imprimir menujuego0
        imprimir menujuego1
        pintar menujuego2 ;Mostrado para evitar escribir en la misma linea lo siguiente.

        mov si, offset turno
        mov bl, [si]
        cmp bl, 1 ;Revisa si es uno para poder indicar que jugador le toca, en este caso es 1
        je turno_jugador2

        turno_jugador1:
            imprimir jugador1
            imprimir menujuego3
            pintar menujuego5
            imprimir punteojugador1
            imprimir menujuego6
            jmp pintar_tablero_comando

        turno_jugador2:
            imprimir jugador2
            imprimir menujuego4
            pintar menujuego5
            imprimir punteojugador2
            imprimir menujuego6

        pintar_tablero_comando:
            imprimir menujuego7
            call pintartablero_pantalla
            comando:
                ;call ingreso_comando
                mov si, offset detenerjuego
                mov bl, [si]
                cmp bl,1
                je saltar
                jmp cargar_juego
                saltar:
        pop bx
        pop si
        ret
    juego_enciclado endp
;------------------------------------------------------------------------------------------------------------------
    valorTablero_carga_al macro y, x
        push bx
        push cx
        push dx
        push si
        push di

        
        xor ax,ax
        mov al, 8

        mov si, offset y
        mov cl, [si]

        mov si, offset x
        mov ch, [si]

        mul cl
        add al, ch
        adc al, 00
                   
        mov di, offset tablerojuego
        add di, ax
        
        mov al, [di]

        pop di
        pop si
        pop dx
        pop cx
        pop bx
    endm
;------------------------------------------------------------------------------------------------------------------
    cambiar_val_tab macro y, x, nuevo
        push ax
        push bx
        push cx
        push dx
        push si
        push di

        push ax
        xor ax,ax
        mov al, 8

        mov si, offset y
        mov cl, [si]

        mov si, offset x
        mov ch, [si]

        mul cl
        add al, ch
        adc al, 00
                   
        mov di, offset tablerojuego
        add di, ax

        pop ax
        mov [di], nuevo

        pop di
        pop si
        pop dx
        pop cx
        pop bx
        pop ax
    endm
;------------------------------------------------------------------------------------------------------------------
    ingreso_comando proc
        push si
        push bx
        push ax
        
        ingresa:
            mov si, offset comando_actual
            mov bl, 0
            imprimir menujuego7
            comandovalido:
                cmp bl, 4
                jg comando_si_valido:

                mov ah, 01
                int 21h

                cmp al, 13d
                je comando_si_valido

                mov [si], al
                inc bl
                inc si
                jmp comandovalido
            comando_si_valido:
                mov [si], 36d
                mov ah, 02
                mov dl, 10d
                int 21h

                    mov si, offset comando_actual
                    mov bl, [si]

                    cmp bl, 65d
                    jl comando_FF
                    cmp bl, 72d
                    jle comando_FV2
                    cmp bl, 97d
                    jl comando_FF
                    cmp bl, 104d
                    jle comando_FV2
                    jmp comando_FF

                    comando_FV2: ;Primer numero a revisar
                        inc si
                        mov bl, [si]

                        cmp bl, 88d
                        je comando_FF_salir

                        cmp bl, 120d
                        je comando_FF_salir

                        cmp bl, 49d
                        jl comando_FF

                        cmp bl, 56d
                        jg comando_FF

                    quitar_dospuntos:
                        inc si
                        mov bl, [si]

                        cmp bl, 58d
                        jne comando_FF
                        
                    segunda_letra:
                        inc si
                        mov bl, [si]
                        cmp bl, 65d
                        jl comando_FF
                        cmp bl, 72d
                        jle segundo_numero ;de la coordenada destino
                        cmp bl, 97d
                        jl comando_FF
                        cmp bl 104d
                        jg comando_FF
                        
                    segundo_numero:
                        inc si
                        mov bl, [si]

                        cmp bl, 49d
                        jl comando_FF
                        cmp bl, 56d
                        jg comando_FF_invalido
                        call ejecutar_movimiento
                        jmp salida 
                        
                    comando_FF:
                        cmp bl, 82d
                        je rep_comando_FF
                        cmp bl, 114d
                        je rep_comando_FF
                        jmp salida_comando_FF

                        rep_comando_FF:
                            inc si
                            mov bl, [si]

                            cmp bl, 69d
                            je comando_FF_repite1
                            cmp bl, 101d
                            je comando_FF_repite1
                            jmp comando_FF_invalido
                                
                        comando_FF_repite1:
                            inc si
                            mov bl, [si]
                            cmp bl, 80d
                            je salida

                            cmp bl, 112d
                            je salida
                            jmp comando_FF_invalido
                            
                        salida_comando_FF:
                            cmp bl, 69d
                            je salida_comando_FF_1
                            cmp bl, 101d
                            je salida_comando_FF_1
                            jmp comando_FF_invalido
                            
                        salida_comando_FF_1:
                            inc si
                            mov bl, [si]
                            cmp bl, 88d
                            je salida_comando_FF_2
                            cmp bl, 120d
                            je salida_comando_FF_2
                            jmp comando_FF_invalido
                            
                        salida_comando_FF_2:
                            inc si
                            mov bl, [si]
                            cmp bl, 73d
                            je salida_comando_FF_3
                            cmp bl, 105d
                            je salida_comando_FF_3
                            jmp comando_FF_invalido
                                
                        salida_comando_FF_3:
                            inc si
                            mov bl, [si]
                            cmp bl, 84d
                            je salida_comando_FF_4
                            cmp bl, 116d
                            je salida_comando_FF_4
                            jmp comando_FF_invalido
                            
                        salida_comando_FF_4:
                            mov si, offset detenerjuego
                            mov [si],1
                            jmp salida
                                
                        comando_FF_invalido:
                            imprimir juegoerror0
                            jmp ingresa
            salida:
        pop ax
        pop bx
        pop si
        ret
    ingreso_comando endp
;------------------------------------------------------------------------------------------------------------------
    cargar_valores_tablero_movmiento proc;prepareVars
    cargar_valores_tablero_movmiento endp
;------------------------------------------------------------------------------------------------------------------
    siguiente_turno proc ;siguiente_turno
    siguiente_turno endp
;------------------------------------------------------------------------------------------------------------------
    ejecutar_movimiento proc
        push ax
        push bx
        push cx
        push dx
        ; Posicion y ->cl  | Posicion x -> ch
        ; Origen ->bl      | Destino -> bh
        mov dl, 00000000        
        mov si, offset comando_actual

    obtener_posicion:
        mov cl,[si]
        cmp cl, 72d
        jle obtener_letra

        sub cl, 97d 
        jmp obtenter_coordenadas_tablero

    obtener_letra: 
        sub cl, 65d

    obtenter_coordenadas_tablero:
        inc si
        mov ch, [si]
        sub ch, 49d
        push cx 

        xor ax,ax
        mov al, 8
        mul cl
        add al, ch
        adc al, 00
                   
        mov di, offset tablerojuego
        add di, ax
        
        cmp dl, 3
        je coor_y_tab

    coor_x_tab:
        mov bl, [di] 
        push di
        inc si
        inc si
        inc dl
        jmp obtener_posicion:

    coor_y_tab:
        mov bh, [di] 
        push di
        pop di
        pop cx
        mov dh, ch
        mov dl, cl
        pop si
        pop cx
        call cargar_valores_tablero_movmiento
        mov si, offset turno
        mov al, [si]
        cmp al, 1
        je turno_j2


        turno_j1:
            cmp dl, cl
            jl j1_atras
            jg j1_alfrente
            je j1_mov_invalido
            j1_atras:
                cmp bl, 2
                je j1_atras_si
                cmp bl, 5
                je j1_atras_si
                jmp j1_atras_no
       
                j1_atras_si:
                    mov si, offset origenym2
                    mov al, [si]
                    cmp dl, al
                    je j1_atras_2
                    mov si, offset origenym1
                    mov al, [si]
                    cmp dl, al
                    je j1_atras_1
                    jmp j1_mov_invalido
                    
                    j1_atras_2:
                        mov si, offset origenxm2
                        mov al, [si]

                        cmp dh, al
                        je j1_atras_2_izq


                        mov si, offset origenxp2
                        mov al, [si]
                        cmp dh, al 
                        je j1_atras_2_derecha
                        jmp j1_mov_invalido

                        
                        j1_atras_2_izq:
                            valorTablero_carga_al destinoym2, destinoxm2
                            cmp al, 3
                            jne j1_mov_invalido

                            valorTablero_carga_al origenym1, origenxm1 
                            
                            cmp al, 4 
                            je j1_atras_2_izqcome
                            
                            cmp al, 5
                            je j1_atras_2_izqcome
                            jmp j1_mov_invalido

                            j1_atras_2_izqcome:
                                setValTab origeny, origenx, 3
                                setValTab origenym1, origenxm1, 3
                                setValTab destinoy, destinox, 2
                                call siguiente_turno
                                jmp cerrar_movimiento

                        j1_atras_2_derecha:
                            valorTablero_carga_al destinoym2, destinoxp2

                            cmp al, 1
                            jne j1_mov_invalido

                            valorTablero_carga_al origenym1, origenxp1

                            cmp al, 4 
                            je j1_retroceder_2_comerderecha

                            cmp al, 5
                            je j1_retroceder_2_comerderecha

                            jmp j1_mov_invalido

                            j1_retroceder_2_comerderecha:
                                setValTab origeny, origenx, 1
                                setValTab origenym1, origenxp1, 1
                                setValTab destinoy, destinox, 3
                                call siguiente_turno
                                jmp cerrar_movimiento

                    j1_atras_1:
                        mov si, offset origenxm1
                        mov al, [si]
                        cmp dh, al
                        je j1_atras_1_izquierda
                        mov si, offset origenxp1
                        mov al, [si]
                        cmp dh, al
                        je j1_atras_1_derecha
                        jmp j1_mov_invalido

                        j1_atras_1_izquierda:
                            valorTablero_carga_al origenym1, origenxm1 
                            cmp al, 1
                            je j1_atras_1_izquierda_mover
                            jmp j1_mov_invalido

                            j1_atras_1_izquierda_mover:
                                setValTab origeny, origenx, 3
                                setValTab destinoy, destinox, 2
                                call siguiente_turno
                                jmp cerrar_movimiento

                        j1_atras_1_derecha:
                            valorTablero_carga_al origenym1, origenxp1 
                        
                            cmp al, 3
                            je j1_atras_1_derecha_mover
                            jmp j1_mov_invalido

                            j1_atras_1_derecha_mover:
                                setValTab origeny, origenx, 3
                                setValTab destinoy, destinox, 2
                                call siguiente_turno
                                jmp cerrar_movimiento
                           
                j1_atras_no:
                    imprimir juegoerror2
                    jmp cerrar_movimiento
*****************************************************************************************************************************************
            j1_alfrente:
                mov si, offset origenyp2
                mov al, [si]
                cmp dl, al
                je rc_tj1_avanzar_2
                mov si, offset origenyp1
                mov al, [si]
                cmp dl, al
                je rc_tj1_avanzar_1
                jmp j1_mov_invalido
*****************************************************************************************************************************************
                rc_tj1_avanzar_2:
                    mov si, offset origenxm2
                    mov al, [si]
                    cmp dh, al
                    je rc_tj1_avanzar_2_izq
                    mov si, offset origenxp2
                    mov al, [si]
                    cmp dh, al
                    je rc_tj1_avanzar_2_der
                    jmp j1_mov_invalido

                    rc_tj1_avanzar_2_izq:
                        valorTablero_carga_al destinoyp2, destinoxm2
                        cmp al, 1
                        jne j1_mov_invalido
                        valorTablero_carga_al origenyp1, origenxm1 ;macro
                        cmp al, 4
                        je rc_tj1_avanzar_2_izq_comer
                        cmp al, 5
                        je rc_tj1_avanzar_2_izq_comer
                        jmp j1_mov_invalido

                        rc_tj1_avanzar_2_izq_comer:
                            setValTab origenyp1, origenxm1, 1

                            mov si, offset destinox
                            mov al, [si]
                            cmp al, 8
                            jne rc_tj1_avanzar_2_izq_comer_normal

                            rc_tj1_avanzar_2_izq_comer_coronar:
                                setValTab origeny, origenx, 1
                                setValTab destinoy, destinox, 3
                                call siguiente_turno
                                jmp cerrar_movimiento
                            rc_tj1_avanzar_2_izq_comer_normal:
                                mov si, offset c_origen
                                mov ax, [si]
                                mov si, ax
                                mov ah, [si]
                                setValTab destinoy, destinox, ah
                                setValTab origeny, origenx, 1
                                call siguiente_turno
                                jmp cerrar_movimiento

                    rc_tj1_avanzar_2_der:
                        valorTablero_carga_al destinoyp2, destinoxp2
                        cmp al, 1
                        jne j1_mov_invalido
                        valorTablero_carga_al origenyp1, origenxp1 ;macro
                        cmp al, 4
                        je rc_tj1_avanzar_2_der_comer
                        cmp al, 5
                        je rc_tj1_avanzar_2_der_comer
                        jmp j1_mov_invalido

                        rc_tj1_avanzar_2_der_comer:
                            setValTab origenyp1, origenxp1, 1

                            mov si, offset destinox
                            mov al, [si]
                            cmp al, 8
                            jne rc_tj1_avanzar_2_der_comer_normal

                            rc_tj1_avanzar_2_der_comer_coronar:
                                setValTab origeny, origenx, 1
                                setValTab destinoy, destinox, 3
                                call siguiente_turno
                                jmp cerrar_movimiento
                            rc_tj1_avanzar_2_der_comer_normal:
                                mov si, offset c_origen
                                mov ax, [si]
                                mov si, ax
                                mov ah, [si]
                                setValTab destinoy, destinox, ah
                                setValTab origeny, origenx, 1
                                call siguiente_turno
                                jmp cerrar_movimiento

                rc_tj1_avanzar_1: ;; avanzar solo uno
                    mov si, offset origenxm1
                    mov al, [si]
                    cmp dh, al
                    je rc_tj1_avanzar_1_izq
                    mov si, offset origenxp1
                    mov al, [si]
                    cmp dh, al
                    je rc_tj1_avanzar_1_der
                    jmp j1_mov_invalido

                    rc_tj1_avanzar_1_izq:
                        valorTablero_carga_al origenyp1, origenxm1 ;macro
                        cmp al, 1
                        je rc_tj1_avanzar_1_izq_mover
                        jmp j1_mov_invalido

                        rc_tj1_avanzar_1_izq_mover:
                            mov si, offset destinox
                            mov al, [si]
                            cmp al, 8
                            jne rc_tj1_avanzar_1_izq_mover_normal

                            rc_tj1_avanzar_1_izq_mover_coronar:
                                setValTab origeny, origenx, 1
                                setValTab destinoy, destinox, 3
                                call siguiente_turno
                                jmp cerrar_movimiento
                            rc_tj1_avanzar_1_izq_mover_normal:
                                mov si, offset c_origen
                                mov ax, [si]
                                mov si, ax
                                mov ah, [si]
                                setValTab destinoy, destinox, ah
                                setValTab origeny, origenx, 1
                                call siguiente_turno
                                jmp cerrar_movimiento

                    rc_tj1_avanzar_1_der:
                        valorTablero_carga_al origenyp1, origenxp1 ;macro
                        cmp al, 1
                        je rc_tj1_avanzar_1_der_mover
                        jmp j1_mov_invalido

                        rc_tj1_avanzar_1_der_mover:
                            mov si, offset destinox
                            mov al, [si]
                            cmp al, 8
                            jne rc_tj1_avanzar_1_der_mover_normal

                            rc_tj1_avanzar_1_der_mover_coronar:
                                setValTab origeny, origenx, 1
                                setValTab destinoy, destinox, 3
                                call siguiente_turno
                                jmp cerrar_movimiento
                            rc_tj1_avanzar_1_der_mover_normal:
                                mov si, offset c_origen
                                mov ax, [si]
                                mov si, ax
                                mov ah, [si]
                                setValTab destinoy, destinox, ah
                                setValTab origeny, origenx, 1
                                call siguiente_turno
                                jmp cerrar_movimiento
                           
            j1_mov_invalido:
                imprimir juegoerror2
                jmp cerrar_movimiento

        turno_j2:    

    cerrar_movimiento:
        pop dx
        pop cx
        pop bx
        pop ax
        ret
    ejecutar_movimiento endp
;------------------------------------------------------------------------------------------------------------------
    pintartablero_pantalla proc
        push ax
        push bx
        push cx
        push dx
        push si
        push di

        xor dx, dx
        mov dh, 8
        mov cl, 0 ;filas
        mov ch, 0 ;columnas

        imprimir encabezado_tab

        filas:
            cmp cl, dh ;compara el ciclo para verificar que no haya completado las 8 iteraciones
            jge final_tablero
            columnas:
                cmp ch, dh
                jge fin_columna

                cmp ch, 0 ;Primera vez en el ciclo de columnas, agregar la letra
                jg obtener_valores_matriz

                mov ah, 02
                mov dl, 32d
                int 21h

                mov dl, 65d
                add dl, cl
                int 21h

                mov ah, 02
                mov dl, 32d
                int 21h

                obtener_valores_matriz:
                    xor ax,ax ;Capturar el valor de la matriz
                    mov al, dh
                    mul cl
                    add al, ch
                    adc al, 00

                    mov si, offset tablerojuego
                    add si, ax

                    mov dl, [si]
                    push dx ;Guarda valor en pila
                    push ax ;Guarda valor en pila
                    cmp dl, 0
                    jne pintar_blanco
                        
                        mov ah, 02
                        mov dl, 219d
                        int 21h
                        jmp pintar_caracteres

                    pintar_blanco:
                        mov ah, 02
                        mov dl, 32d
                        int 21h
                    
                    pintar_caracteres:
                        pop ax
                        pop dx ;Valor guardados en la pila

                        ;Comparaciones para pintar caracteres
                        cmp dl, 1 ;blancas
                        je peon_blanco
                        cmp dl, 2 ;reina blancas
                        je reina_blanca
                        cmp dl, 4 ;negras
                        je peon_negro
                        cmp dl, 5 ;reina negras
                        je  reina_negra
                        jmp espacio_vacio

                            peon_blanco:
                                mov ah, 02
                                mov dl, 01
                                int 21h

                                mov ah, 02
                                mov dl, 32d
                                int 21h
                                jmp fin_fila

                            reina_blanca:
                                mov ah, 02
                                mov dl, 01
                                int 21h

                                mov ah, 02
                                mov dl, 253d
                                int 21h
                                jmp fin_fila

                            peon_negro:
                                mov ah, 02
                                mov dl, 02
                                int 21h

                                mov ah, 02
                                mov dl, 32d
                                int 21h
                                jmp fin_fila

                            reina_negra:
                                mov ah, 02
                                mov dl, 02
                                int 21h

                                mov ah, 02
                                mov dl, 253d
                                int 21h
                                jmp fin_fila

                            espacio_vacio:
                                cmp dl, 3 ;espacio vacio y disponible
                                je vacio_en_tablero

                                mov ah, 02
                                mov dl, 219d
                                int 21h

                                mov ah, 02
                                mov dl, 219d
                                int 21h

                                jmp fin_fila ;rev

                                vacio_en_tablero:
                                    mov ah, 02
                                    mov dl, 32d
                                    int 21h

                                    mov ah, 02
                                    mov dl, 32d
                                    int 21h
                        fin_fila:
                        inc ch
                        jmp columnas

                mov ah, 02
                mov dl, 32d
                int 21h 
            fin_columna:
                mov ah, 02
                mov dl, 32d
                int 21h

                mov dl, 65d
                add dl, cl
                int 21h

                mov ah, 2
                mov dl, 10
                int 21h

                mov ch, 0
                inc cl
                jmp filas 
    final_tablero:
        imprimir encabezado_tab

        pop ax
        pop bx
        pop cx
        pop dx
        pop si
        pop di
        ret
    pintartablero_pantalla endp
;------------------------------------------------------------------------------------------------------------------
    iniciar_jugar proc
        mov si, offset detenerjuego
        mov [si],00

        mov si, offset jugador1 ;Verifica si existe el jugador 1
        mov bl, [si]

        cmp bl,36d
        je no_existe

        mov si, offset jugador2 ;Verifica si existe el jugador 2
        mov bl, [si]

        cmp bl, 36d
        je no_existe

        jmp enciclar_el_juego

        no_existe:
            imprimir separador
            imprimir juegoerror1 ;imprimir que el jugador no existe o no hay suficientes jugadores
            imprimir separador
            jmp mostrarmenu

        enciclar_el_juego:
            push bx
            push si
        
        cargar_juego1:
            imprimir separador
            imprimir menujuego0
            imprimir menujuego1
            pintar menujuego2 ;Mostrado para evitar escribir en la misma linea lo siguiente.

            mov si, offset turno
            mov bl, [si]
            cmp bl, 1 ;Revisa si es uno para poder indicar que jugador le toca, en este caso es 1
            je turnojuega_jugador2

            turnojuega_jugador1:
                imprimir jugador1
                imprimir menujuego3
                pintar menujuego5
                imprimir punteojugador1
                imprimir menujuego6
                jmp pintar_tablero_comando

            turnojuega_jugador2:
                imprimir jugador2
                imprimir menujuego4
                pintar menujuego5
                imprimir punteojugador2
                imprimir menujuego6

            pintar_tablero_comando1:
                imprimir menujuego7
                call pintartablero_pantalla
                comandoleer:
                    call ingreso_comando
                    mov si, offset detenerjuego
                    mov bl, [si]
                    cmp bl,1
                    je saltar1
                    jmp cargar_juego1
                    saltar1:
            pop bx
            pop si
            jmp mostrarmenu
    iniciar_jugar endp
;------------------------------------------------------------------------------------------------------------------
    registrar_nuevojugador proc
        push ax
        push bx
        push dx
        push si
    menu_registro:
        ;Interrupcion salto de linea
        mov ah,02h
        mov dl,10d
        int 21h
        imprimir separador
        imprimir menuregistro0
        imprimir menuregistro1
        imprimir menuregistro2
        imprimir menuregistro3
        imprimir menuregistro4
        imprimir menuregistro5

        mov ah, 01 ;Lectura de caracter
        int 21h

            cmp al,49d ;cmp numero 1
            je registrar_jugador1

            cmp al,50d ;cmp numero 2
            je registrar_jugador2
            
            opcion_invalida:
                imprimir menuerror
                jmp menu_registro ;Opcion que repite el mismo menu

            registrar_jugador1:
                imprimir menuregistro6
                mov si, offset jugador1
                mov bl, 0
                jmp cargar_eljugador

            registrar_jugador2:
                imprimir menuregistro6
                mov si, offset jugador2  
                mov bl, 0
            
            cargar_eljugador:
                cmp bl,14d
                jge guardarPf

                mov ah,01
                int 21h

                cmp al,13d
                je guardarf

                mov [si],al
                inc si
                inc bl
                jmp cargar_eljugador

            guardarPf:
                mov ah, 02
                mov dl, 10d
                int 21h

            guardarf:
                mov [si],36d

        pop ax
        pop bx
        pop dx
        pop si
        ret 
    registrar_nuevojugador endp
;------------------------------------------------------------------------------------------------------------------
    mostrarmenu proc
        push ax
        push bx
        push si
        push di        ;La pila usada para limpiar los procesos en los registros

        ;Espacio en blanco
        mov ah,02h
        mov dl,10d
        int 21h

        ;Menu
        imprimir separador
        imprimir menuinicio0
        imprimir menuinicio1 
        imprimir menuinicio2
        imprimir menuinicio3
        imprimir menuinicio4
        imprimir menuinicio5
        imprimir menuinicio6
        imprimir menuinicio7
        imprimir blanco

        mov ah, 01 ;Interrupcion de lectura
        int 21h
            ;Comparar los valores del menu
            cmp al,49d ;cmp numero 1
            je mov_registrarjugadores ;je -> es igual

            cmp al,50d ;cmp numero 2
            je mod_juego

            cmp al,51d ;cmp numero 3
            je mos_datosalumno

            cmp al,52d ;cmp numero 4
            je mod_salida

            opcion_novalida:
                imprimir menuerror
                jmp mostrarmenu ;Opcion que repite el mismo menu
            
            mod_juego:
                call iniciar_jugar
                jmp mostrarmenu

            mos_datosalumno:
                imprimir blanco
                imprimir datospersonales0
                imprimir datospersonales1
                imprimir datospersonales2
                imprimir datospersonales3
                imprimir datospersonales4
                jmp mostrarmenu

            mov_registrarjugadores:
                call registrar_nuevojugador 
                jmp mostrarmenu ;Opcion que repite el mismo menu
            
            mod_salida:
                imprimir salidaprograma
                mov ax,4c00h
                int 21h

        regresar:
            push ax
            push bx
            push si
            push di;Limpieza de registros
    mostrarmenu endp
;------------------------------------------------------------------------------------------------------------------
    main proc 
        push ds
        mov si,0
        push si
        mov ax, sdatos
        mov ds, ax
        mov es, ax

        call mostrarmenu
    main endp
;------------------------------------------------------------------------------------------------------------------
scodigo ends
end main