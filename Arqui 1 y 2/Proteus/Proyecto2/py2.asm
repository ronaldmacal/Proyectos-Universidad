spila segment stack
    DB 32 DUP ('stack___')
spila ends

sdatos segment
    header0     db     "|----------------------------------------|","$"
    header1     db     "|   Grupo #7                       PY2   |","$"
    header2     db     "| ************************************** |","$"
    header3     db     "|        CALCULADORA EN ASSEMBLER        |","$"
    menu_1      db     "| 1. Derivar Funcion                     |","$"
    menu_2      db     "| 2. Integrar Funcion                    |","$"
    menu_3      db     "| 3. Imprimir Lista de Funciones         |","$"
    menu_4      db     "| 4. Nueva Funcion                       |","$"
    menu_5      db     "| 5. Resolver Ecuacion                   |","$"
    menu_6      db     "| 6. Enviar a Arduino                    |","$"
    menu_7      db     "| 7. Salir                               |","$"
    menu_v      db     "|                                        |","$"
    menu_i      db     "| Ingresa opcion: ","$"
    headerL     db     "|----------------------------------------|","$"
    header5     db     "|        Modo Ingreso de Funciones       |","$"
    headerA     db     "| ************************************** |","$"
    
    ;headerL
    header6     db     "|          Modo Derivar Funcion          |","$"
    ;headerA

    ;headerL
    header7     db     "|         Modo Integrar Funcion          |","$"
    ;headerA

    ;headerL
    header8     db     "|           Lista de Funciones           |","$"
    header9     db     "| > Error, no hay funciones aun...       |","$"
    ;headerA


    menu_8      db     "| 1. Ingreso Manual                      |","$"
    menu_9      db     "| 2. Carga Masiva                        |","$"
    menu_volver db     "| 3. Volver                              |","$"
    menu_10     db     "| Ingresa la funcion: ","$"

    menu_11      db    "| 1. Elegir ID                           |","$"
    menu_12     db     "| 2. Volver                              |","$"
    menu_13     db     "| Ingresa id: ","$"
    menu_14     db     "| Ruta: ","$"

    menu_inc_f  db     "| No hay una funcion con ese ID          |","$"

    menu_inc    db     "| No es una funcion valida...            |","$"
    menu_err_of db     "| Error al abrir el archivo!!            |","$"

    menu_press  db     "| Presiona una tecla para volver...      ","$"


    startFunc   dw      0d
    endFunc     dw      0d

    fnumber     db      0

    xbase0      db      0
    xbase1      db      0
    xbase2      db      0
    xbase3      db      0
    xbase4      db      0

    bID         db      "$$$$$$", "$"

    tempPath    db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"

    Funciones   db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" ;1
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" ;25

    fileBuffer  db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" ;1
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
                db      "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" ;25
    
    flimit      db      "$"
                        
    footer0     db     "| ---------- Fin del programa -----------|","$"

    line_break  db      10d,13d, "$"
    line_space  db      32d,"$"
    line_char   db      "A","$"
    handle      dw      0
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

    printr macro cadena
        push ax
        push dx        

        mov ah, 09h
        mov dx, cadena
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

    showFunctions proc
        push bx
        push ax
        push dx

        mov ah, 02
        mov dl, 10d
        int 21h

        println headerL
        println header8
        println headerA

        mov si, offset Funciones
        swF_Li:
            inc si ; Puntero en primera funcion
            mov bh, [si]
            cmp bh, 105d ; i
            je swF_Ic
            cmp bh, 100d ; d
            je swF_Dc
            cmp bh, 36d; $
            je swF_F
            jmp swF_Fc

            swF_Ic:
                mov ah, 02
                mov dl, 124d
                int 21h ; Imprimir | 

                mov dl, 32d
                int 21h ; Imprimir " "

                mov ah, 02
                mov dl, 105d
                int 21h ; Imprimir i

                inc si
                swF_Ic_c:
                    mov bh, [si]
                    cmp bh, 36d
                    je swF_L1
                    mov dl, bh
                    int 21h ; Imprimir id
                    inc si
                    jmp swF_Ic_c
            swF_Dc:
                mov ah, 02
                mov dl, 124d
                int 21h ; Imprimir | 

                mov dl, 32d
                int 21h ; Imprimir " "
                
                mov ah, 02
                mov dl, 100d
                int 21h ; Imprimir d

                inc si
                swF_Dc_c:
                    mov bh, [si]
                    cmp bh, 36d
                    je swF_L1
                    mov dl, bh
                    int 21h ; Imprimir id
                    inc si
                    jmp swF_Dc_c
            swF_Fc:
                mov ah, 02
                mov dl, 124d
                int 21h ; Imprimir | 

                mov dl, 32d
                int 21h ; Imprimir " "
                
                mov dl, bh
                int 21h ; Imprimir id
                inc si
                swF_Fc_c:
                    mov bh, [si]
                    cmp bh, 36d
                    je swF_L1
                    mov dl, bh
                    int 21h ; Imprimir id
                    inc si
                    jmp swF_Fc_c
            swF_L1:
                
            mov dl, 46d
            int 21h ; Imprimir .

            mov dl, 32d
            int 21h ; Imprimir " "

            inc si
            printr si
            print line_break

            call getNextFrom
            jmp swF_Li
        swF_F:
            pop dx
            pop ax
            pop bx
        ret
    showFunctions endp

    selectID proc
        push ax
        push si
        push bx

        mov si, offset fnumber
        mov bl, [si]
        cmp bl, 0
        je sD_Empty

        print menu_13
        mov bl, 0d
        mov si, offset bID
        sD_I_li:
            cmp bl, 6d
            jge sD_I_li_l
            mov ah, 01
            int 21h
            
            cmp al, 13d
            je sD_F

            mov [si], al
            inc si
            inc bl
            jmp sD_I_li
            sD_I_li_l:
                mov [si], 36d
                mov ah, 02
                mov dl, 10d
                int 21h
                jmp sD_F

        sD_Empty:
            println header9
            println headerL
        sD_F:
            mov [si], 36d
            
            pop bx
            pop si
            pop ax
            ret
    selectID endp

    buscarIDtoBX proc
        push si
        push di

        mov si, offset Funciones
        bID_Next:
            mov di, offset flimit
            cmp si, di
            jge bID_FinEnError
            inc si
            cmp si, di
            jge bID_FinEnError
            dec si

            mov di, offset bID
            
            inc si ;Primer ID de funcion
            
            mov bl, [si] ;Primer char de func
            mov bh, [di] ;Primer char de bID

        bID_Next_Compare:
            cmp bl, bh
            je bID_PcharCoincide
            call getNextFrom
            inc si
            call getNextFrom
            jmp bID_Next

        bID_PcharCoincide: 
            inc di
            inc si

            mov bh, [di]
            mov bl, [si]

            cmp bh, 36d
            je bID_FinEnCoincidencia
            cmp bl, 36d
            jne bID_PcharCoincide_Sig
            call getNextFrom
            inc si
            call getNextFrom
            jmp bID_Next
            bID_PcharCoincide_Sig:
                mov bl, [si] ; Segundo char de func
                cmp bh, [di] ; Segundo char de bID
                jmp bID_Next_Compare

        
        bID_FinEnError:
            mov al, 0
            pop di
            pop si
            ret
        bID_FinEnCoincidencia:
            mov al, 1
            inc si
            mov bx, si
            pop di
            pop si
            ret
    buscarIDtoBX endp

    getNumberToAL proc
        push bx

        mov bl, [si] ;primer digito
        mov bh, [si +1] ; segundo digito

        cmp bl, 120d
        je gN_VarUnique
        cmp bl, 88d
        je gN_VarUnique
        cmp bh, 120d
        je gN_SingleD
        cmp bh, 43d
        je gN_SingleD
        cmp bh, 45d
        je gN_SingleD
        cmp bl, 88d
        je gN_SingleD
        cmp bh, 36d
        je gN_SingleD

        inc si
        inc si
        sub bl, 48d ; Decenas
        sub bh, 48d ; Unidades

        mov al, bl
        mov bl, 10d
        mul bl
        
        add al, bh
        jmp gN_F

        gN_VarUnique:
            mov al, 1
            jmp gN_F
        gN_SingleD:
        inc si
        mov al, 0
        sub bl, 48d ; Unidades
        add al, bl
        gN_F:
            pop bx
            ret
    getNumberToAL endp

    getPotToAH proc
        push bx

        mov bl, [si] ; variable
        mov bh, [si +1] ; potencia val

        cmp bl, 88d
        je gP_az
        cmp bl, 120d
        je gP_az

        ;caso potencia 0
        mov ah, 0
        jmp gP_LF

        gP_az:
            cmp bh, 94d
            je gP_m1

            ;caso potencia 1
            inc si
            mov ah, 1
            jmp gP_LF

        gP_m1:
            inc si
            inc si
            mov bh, [si] ; grado
            inc si
            sub bh, 48d
            mov ah, bh
        
        gP_LF:
            pop bx
            ret
    getPotToAH endp

    restaPot proc
        push si
        push bx
        push cx

        ;AL numero , AH potencia
        cmp ah, 0
        je rP_0
        cmp ah, 1
        je rP_1
        cmp ah, 2
        je rP_2
        cmp ah, 3
        je rP_3
        cmp ah, 4
        je rP_4
        jmp rP_LF

        rP_0:
            mov si, offset xbase0
            mov bh, [si]
            sub bh, al
            mov [si], bh
            jmp rP_LF
        rP_1:
            mov si, offset xbase1
            mov bh, [si]
            sub bh, al
            mov [si], bh
            jmp rP_LF
        rP_2:
            mov si, offset xbase2
            mov bh, [si]
            sub bh, al
            mov [si], bh
            jmp rP_LF
        rP_3:
            mov si, offset xbase3
            mov bh, [si]
            sub bh, al
            mov [si], bh
            jmp rP_LF
        rP_4:
            mov si, offset xbase4
            mov bh, [si]
            sub bh, al
            mov [si], bh
            jmp rP_LF

        rP_LF:
            pop cx
            pop bx
            pop si
            ret
    restaPot endp

    sumaPot proc
        push si
        push bx
        push cx

        ;AL numero , AH potencia
        cmp ah, 0
        je sP_0
        cmp ah, 1
        je sP_1
        cmp ah, 2
        je sP_2
        cmp ah, 3
        je sP_3
        cmp ah, 4
        je sP_4
        jmp sP_LF

        sP_0:
            mov si, offset xbase0
            mov bh, [si]
            add bh, al
            mov [si], bh
            jmp sP_LF
        sP_1:
            mov si, offset xbase1
            mov bh, [si]
            add bh, al
            mov [si], bh
            jmp sP_LF
        sP_2:
            mov si, offset xbase2
            mov bh, [si]
            add bh, al
            mov [si], bh
            jmp sP_LF
        sP_3:
            mov si, offset xbase3
            mov bh, [si]
            add bh, al
            mov [si], bh
            jmp sP_LF
        sP_4:
            mov si, offset xbase4
            mov bh, [si]
            add bh, al
            mov [si], bh
            jmp sP_LF
            
        sP_LF:
            pop cx
            pop bx
            pop si
            ret
    sumaPot endp

    convertToVars proc
        push ax
        push si
        push bx
        
        call buscarIDtoBX
        cmp al, 0
        je cTV_N_Found
        cmp al, 1
        je cTV_Found
        jmp cTV_LF

        cTV_Found:
            mov cl, 0 ; ok
            mov si, bx ; Posicion de la funcion en SI
        cTV_Eval:
            mov bl, [si]
            
            cmp bl, 45d ; -
            je cTV_E_Negativo
            cmp bl, 43d ; +
            je cTV_E_Positivo
            cmp bl, 120d ; x
            je cTV_E_Variable
            cmp bl, 88d ; X
            je cTV_E_Variable
            cmp bl, 36d ; $ - salir de loop
            je cTV_E_F
            cmp bl, 48d
            jl cTV_Error
            cmp bl, 57d
            jg cTV_Error

            ;caso numero
            call getNumberToAL ; AL = numero, SI = variable
            call getPotToAH    ; AH = potencia, SI = next
            call sumaPot       ; Suma los valores a la potencia necesaria
            jmp cTV_Eval

            cTV_E_Negativo:
                inc si
                call getNumberToAL ; AL = numero, SI = variable
                call getPotToAH    ; AH = potencia, SI = next
                call restaPot      ; Resta los valores a la potencia necesaria
                jmp cTV_Eval
            cTV_E_Positivo:
                inc si
                call getNumberToAL ; AL = numero, SI = variable
                call getPotToAH    ; AH = potencia, SI = next
                call sumaPot       ; Suma los valores a la potencia necesaria
                jmp cTV_Eval
            cTV_E_Variable:
                mov al, 1
                call getPotToAH     ; AH = potencia, SI = next
                call sumaPot
                jmp cTV_Eval
            cTV_Error:
                ;error
                jmp cTV_LF
            cTV_E_F:
                jmp cTV_LF

        cTV_N_Found:
            println menu_inc_f
            mov cl, 1 ; nok
            jmp cTV_LF

        cTV_LF:
            pop bx
            pop si
            pop ax
        ret
    convertToVars endp

    derivar proc
        push ax
        push bx
        push si
        push di

        call getNextStartForID ; SI en funciones siguiente
        mov di, offset bID
        
        ;escribir ID con d<id>
        mov [si], 100d ; escribir d
        inc si  ; aumentar SI

        d_ID_next:
            mov bl, [di]
            cmp bl, 36d
            je d_ID_w

            mov [si], bl
            inc di
            inc si
            jmp d_ID_next

        d_ID_w:
            push si
            mov si, offset xbase1
            mov di, offset xbase0
            mov al, [si] ;copio valor de x^1
            mov [di], al ; X1 se vuelve x0 al derivar

            mov si, offset xbase2
            mov di, offset xbase1
            mov al, [si] ;copio valor de x^2
            mov bl, 2
            mul bl ; multiplico al x 2 para encontrar la derivada
            mov [di], al ; X2 se vuelve X1 al derivar

            mov si, offset xbase3
            mov di, offset xbase2
            mov al, [si] ;copio valor de x^3
            mov bl, 3
            mul bl ; multiplico al x 3 para encontrar la derivada
            mov [di], al ; X3 se vuelve X2 al derivar

            mov si, offset xbase4
            mov di, offset xbase3
            mov al, [si] ;copio valor de x^4
            mov bl, 4
            mul bl ; multiplico al x 4 para encontrar la derivada
            mov [di], al ; X4 se vuelve X3 al derivar

            ;No hay grado 5 asi que X4 se vuelve 0
            mov [si], 0

            ; Escribir funcion derivada
            d_X4:
                pop si
                inc si ; comienzo de funcion derivada
                
                mov di, offset xbase4
                xor ah, ah ; Limpi AH para guardar residuo
                mov al, [di] ; Coloco el valor de x^4 en AL
                cmp al, 0
                je d_X3
                add al, 0
                jns d_x4_n

                ; Caso valor Negativo
                    not al
                    add al, 1b
                    mov [si], 45d
                    inc si
                d_x4_n:
                    mov bl, 10d ; Coloco 10 en BH para obtener las decenas
                    div bl ; Divido por 10, AL = Decenas, AH = unidades

                    ; Decenas
                    cmp al, 0d
                    je d_x4_n_u
                    add al, 48d ; lo convierto en valor ascii
                    mov [si], al   
                    inc si
                    ;Unidades
                    d_x4_n_u:
                        add ah, 48d
                        mov [si], ah
                        inc si
                        mov [si], 120d
                        inc si
                        mov [si], 94d
                        inc si
                        mov [si], 52d
                        inc si
                        
            d_X3:
                mov di, offset xbase3
                xor ah, ah ; Limpi AH para guardar residuo
                mov al, [di] ; Coloco el valor de x^4 en AL
                cmp al, 0
                je d_X2
                add al, 0
                jns d_x3_n

                ; Caso valor Negativo
                    not al
                    add al, 1b
                    mov [si], 45d
                    inc si
                    jmp d_x3_n
                ; d_x3_Ps: ; Caso valor Positivo
                ;     mov [si], 43d
                ;     inc si
                d_x3_n:
                    mov bl, 10d ; Coloco 10 en BH para obtener las decenas
                    div bl ; Divido por 10, AL = Decenas, AH = unidades

                    ; Decenas
                    cmp al, 0d
                    je d_x3_n_u
                    add al, 48d ; lo convierto en valor ascii
                    mov [si], al   
                    inc si
                    ;Unidades
                    d_x3_n_u:
                        add ah, 48d
                        mov [si], ah
                        inc si
                        mov [si], 120d
                        inc si
                        mov [si], 94d
                        inc si
                        mov [si], 51d
                        inc si
            
            d_X2:
                mov di, offset xbase2
                xor ah, ah ; Limpi AH para guardar residuo
                mov al, [di] ; Coloco el valor de x^4 en AL
                cmp al, 0
                je d_X1
                add al, 0
                jns d_x2_Ps

                ; Caso valor Negativo
                    not al
                    add al, 1b
                    mov [si], 45d
                    inc si
                    jmp d_x2_n
                d_x2_Ps: ; Caso valor Positivo
                    mov [si], 43d
                    inc si
                d_x2_n:
                    mov bl, 10d ; Coloco 10 en BH para obtener las decenas
                    div bl ; Divido por 10, AL = Decenas, AH = unidades

                    ; Decenas
                    cmp al, 0d
                    je d_x2_n_u
                    add al, 48d ; lo convierto en valor ascii
                    mov [si], al   
                    inc si
                    ;Unidades
                    d_x2_n_u:
                        add ah, 48d
                        mov [si], ah
                        inc si
                        mov [si], 120d
                        inc si
                        mov [si], 94d
                        inc si
                        mov [si], 50d
                        inc si

            d_X1:
                mov di, offset xbase1
                xor ah, ah ; Limpi AH para guardar residuo
                mov al, [di] ; Coloco el valor de x^4 en AL
                cmp al, 0
                je d_X0
                add al, 0
                jns d_x1_Ps

                ; Caso valor Negativo
                    not al
                    add al, 1b
                    mov [si], 45d
                    inc si
                    jmp d_x1_n
                d_x1_Ps: ; Caso valor Positivo
                    mov [si], 43d
                    inc si
                d_x1_n:
                    mov bl, 10d ; Coloco 10 en BH para obtener las decenas
                    div bl ; Divido por 10, AL = Decenas, AH = unidades

                    ; Decenas
                    cmp al, 0d
                    je d_x1_n_u
                    add al, 48d ; lo convierto en valor ascii
                    mov [si], al   
                    inc si
                    ;Unidades
                    d_x1_n_u:
                        add ah, 48d
                        mov [si], ah
                        inc si
                        mov [si], 120d
                        inc si

            d_X0:
                mov di, offset xbase0
                xor ah, ah ; Limpi AH para guardar residuo
                mov al, [di] ; Coloco el valor de x^4 en AL
                cmp al, 0
                je d_LF
                add al, 0
                jns d_x0_Ps

                ; Caso valor Negativo
                    not al
                    add al, 1b
                    mov [si], 45d
                    inc si
                    jmp d_x0_n
                d_x0_Ps: ; Caso valor Positivo
                    mov [si], 43d
                    inc si
                d_x0_n:
                    mov bl, 10d ; Coloco 10 en BH para obtener las decenas
                    div bl ; Divido por 10, AL = Decenas, AH = unidades

                    ; Decenas
                    cmp al, 0d
                    je d_x0_n_u
                    add al, 48d ; lo convierto en valor ascii
                    mov [si], al   
                    inc si
                    ;Unidades
                    d_x0_n_u:
                        add ah, 48d
                        mov [si], ah
                        inc si

        d_LF:

            
            mov di, offset xbase0
            mov bl, [di]
            cmp bl, 0
            jne d_LF_l
            mov di, offset xbase1
            mov bl, [di]
            cmp bl, 0
            jne d_LF_l
            mov di, offset xbase2
            mov bl, [di]
            cmp bl, 0
            jne d_LF_l
            mov di, offset xbase3
            mov bl, [di]
            cmp bl, 0
            jne d_LF_l
            mov di, offset xbase4
            mov bl, [di]
            cmp bl, 0
            jne d_LF_l

            mov [si], 48d
            inc si

            d_LF_l:
                mov si, offset xbase0
                mov [si], 0
                mov si, offset xbase1
                mov [si], 0
                mov si, offset xbase2
                mov [si], 0
                mov si, offset xbase3
                mov [si], 0
                mov si, offset xbase4
                mov [si], 0
            
            pop di
            pop si
            pop bx
            pop ax
            ret
    derivar endp

    integrar proc
        push ax
        push bx
        push si
        push di

        call getNextStartForID ; SI en funciones siguiente
        mov di, offset bID
        
        ;escribir ID con d<id>
        mov [si], 105d ; escribir i
        inc si  ; aumentar SI

        i_ID_next:
            mov bl, [di]
            cmp bl, 36d
            je i_ID_w

            mov [si], bl
            inc di
            inc si
            jmp i_ID_next

        i_ID_w:
            push si

            mov si, offset xbase3 ; Coloco el valor de X^3 en si
            mov di, offset xbase4 ; Coloco el valor de X^4 en di
            xor ax, ax   ; Limpio AX para dividir
            mov al, [si] ; Muevo X^3 en AL
            sar al, 1
            sar al, 1
            mov [di], al ; Muevo el nuevo valor de AL a X^4

            mov si, offset xbase2 ; Coloco el valor de X^2 en si
            mov di, offset xbase3 ; Coloco el valor de X^3 en di
            xor ax, ax   ; Limpio AX para dividir
            mov al, [si] ; Muevo X^2 en AL
            add al, 0
            jns i_ID_w_ps

            i_ID_w_ns:
                xor bh, bh
                not al
                add al, 1
                mov bl, 3
                div bl
                sub bh, al
                mov [di], bh
                jmp i_ID_w_psF

            i_ID_w_ps:
                mov bl, 3    ; Uso X^2/3 para sacar el coeficiente
                div bl
                mov [di], al ; Muevo el nuevo valor de AL a X^3

            i_ID_w_psF:
            mov si, offset xbase1 ; Coloco el valor de X^1 en si
            mov di, offset xbase2 ; Coloco el valor de X^2 en di
            xor ax, ax   ; Limpio AX para dividir
            mov al, [si] ; Muevo X^1 en AL
            sar al, 1
            mov [di], al ; Muevo el nuevo valor de AL a X^2

            mov si, offset xbase0 ; Coloco el valor de X^0 en si
            mov di, offset xbase1 ; Coloco el valor de X^1 en di
            mov al, [si] ; Muevo X^0 en AL
            mov [si], 0d ; No hay valor menor que X^0 asi que se vuelve 0
            mov [di], al ; Muevo el nuevo valor de AL a X^1

            ; Escribir funcion integrada
            i_X4:
                pop si
                inc si ; comienzo de funcion integrada
                
                mov di, offset xbase4
                xor ax, ax ; Limpi AH para guardar residuo
                mov al, [di] ; Coloco el valor de x^4 en AL
                cmp al, 0
                je i_X3
                add al, 0
                jns i_x4_n

                ; Caso valor Negativo
                    not al
                    add al, 1b
                    mov [si], 45d
                    inc si
                i_x4_n:
                    mov bl, 10d ; Coloco 10 en BH para obtener las decenas
                    div bl ; Divido por 10, AL = Decenas, AH = unidades

                    ; Decenas
                    cmp al, 0d
                    je i_x4_n_u
                    add al, 48d ; lo convierto en valor ascii
                    mov [si], al   
                    inc si
                    ;Unidades
                    i_x4_n_u:
                        add ah, 48d
                        mov [si], ah
                        inc si
                        mov [si], 120d
                        inc si
                        mov [si], 94d
                        inc si
                        mov [si], 52d
                        inc si
                        
            i_X3:
                mov di, offset xbase3
                xor ax, ax ; Limpi AH para guardar residuo
                mov al, [di] ; Coloco el valor de x^4 en AL
                cmp al, 0
                je i_X2
                add al, 0
                jns i_x3_Ps
                
                ; Caso valor Negativo
                    not al
                    add al, 1b
                    mov [si], 45d
                    inc si
                    jmp i_x3_n
                i_x3_Ps: ; Caso valor Positivo
                    mov [si], 43d
                    inc si
                i_x3_n:
                    mov bl, 10d ; Coloco 10 en BH para obtener las decenas
                    div bl ; Divido por 10, AL = Decenas, AH = unidades

                    ; Decenas
                    cmp al, 0d
                    je i_x3_n_u
                    add al, 48d ; lo convierto en valor ascii
                    mov [si], al   
                    inc si
                    ;Unidades
                    i_x3_n_u:
                        add ah, 48d
                        mov [si], ah
                        inc si
                        mov [si], 120d
                        inc si
                        mov [si], 94d
                        inc si
                        mov [si], 51d
                        inc si
            
            i_X2:
                mov di, offset xbase2
                xor ax, ax ; Limpi AH para guardar residuo
                mov al, [di] ; Coloco el valor de x^2 en AL
                cmp al, 0
                je i_X1
                add al, 0
                jns i_x2_Ps

                ; Caso valor Negativo
                    not al
                    add al, 1b
                    mov [si], 45d
                    inc si
                    jmp i_x2_n
                i_x2_Ps: ; Caso valor Positivo
                    mov [si], 43d
                    inc si
                i_x2_n:
                    mov bl, 10d ; Coloco 10 en BH para obtener las decenas
                    div bl ; Divido por 10, AL = Decenas, AH = unidades

                    ; Decenas
                    cmp al, 0d
                    je i_x2_n_u
                    add al, 48d ; lo convierto en valor ascii
                    mov [si], al   
                    inc si
                    ;Unidades
                    i_x2_n_u:
                        add ah, 48d
                        mov [si], ah
                        inc si
                        mov [si], 120d
                        inc si
                        mov [si], 94d
                        inc si
                        mov [si], 50d
                        inc si

            i_X1:
                mov di, offset xbase1
                xor ax, ax ; Limpi AH para guardar residuo
                mov al, [di] ; Coloco el valor de x^4 en AL
                cmp al, 0
                je i_X0
                add al, 0
                jns i_x1_Ps

                ; Caso valor Negativo
                    not al
                    add al, 1b
                    mov [si], 45d
                    inc si
                    jmp i_x1_n
                i_x1_Ps: ; Caso valor Positivo
                    mov [si], 43d
                    inc si
                i_x1_n:
                    mov bl, 10d ; Coloco 10 en Bl para obtener las decenas
                    div bl ; Divido por 10, AL = Decenas, AH = unidades

                    ; Decenas
                    cmp al, 0d
                    je i_x1_n_u
                    add al, 48d ; lo convierto en valor ascii
                    mov [si], al   
                    inc si
                    ;Unidades
                    i_x1_n_u:
                        add ah, 48d ; lo convierto en valor ascii
                        mov [si], ah
                        inc si
                        mov [si], 120d
                        inc si

            i_X0:
                mov di, offset xbase0
                xor ax, ax ; Limpi AH para guardar residuo
                mov al, [di] ; Coloco el valor de x^4 en AL
                cmp al, 0
                je i_LF
                add al, 0
                jns i_x0_Ps

                ; Caso valor Negativo
                    not al
                    add al, 1b
                    mov [si], 45d
                    inc si
                    jmp i_x0_n
                i_x0_Ps: ; Caso valor Positivo
                    mov [si], 43d
                    inc si
                i_x0_n:
                    mov bl, 10d ; Coloco 10 en BH para obtener las decenas
                    div bl ; Divido por 10, AL = Decenas, AH = unidades

                    ; Decenas
                    cmp al, 0d
                    je i_x0_n_u
                    add al, 48d ; lo convierto en valor ascii
                    mov [si], al   
                    inc si
                    ;Unidades
                    i_x0_n_u:
                        add ah, 48d
                        mov [si], ah
                        inc si

        i_LF:
            mov di, offset xbase0
            mov bl, [di]
            cmp bl, 0
            jne i_LF_l
            mov di, offset xbase1
            mov bl, [di]
            cmp bl, 0
            jne i_LF_l
            mov di, offset xbase2
            mov bl, [di]
            cmp bl, 0
            jne i_LF_l
            mov di, offset xbase3
            mov bl, [di]
            cmp bl, 0
            jne i_LF_l
            mov di, offset xbase4
            mov bl, [di]
            cmp bl, 0
            jne i_LF_l

            mov [si], 48d
            inc si

            mov [si], 146d
            inc si

            i_LF_l:
                mov si, offset xbase0
                mov [si], 0
                mov si, offset xbase1
                mov [si], 0
                mov si, offset xbase2
                mov [si], 0
                mov si, offset xbase3
                mov [si], 0
                mov si, offset xbase4
                mov [si], 0
            
            pop di
            pop si
            pop bx
            pop ax
            ret
    integrar endp

    getNextFrom proc
        push cx

        gNF_Li:
            mov ch, [si]

            cmp ch, 36d
            je gNF_L1

            inc si
            jmp gNF_Li
        
        gNF_L1:
            pop cx
            ret
    getNextFrom endp

    getNextStart proc
        push ax
        push bx
        
        mov bl, 0
        mov si, offset Funciones
        gNS_Li:
            mov ah, [si]
            mov al, [si+1]

            cmp ah, 36d
            je gNS_L1

            inc si
            jmp gNS_Li

        gNS_L1:
            cmp al, 36d
            je gNS_LF
            inc si
            inc bl
            jmp gNS_Li
        gNS_LF:
            inc si
            shr bl,1
            add bl, 48
            mov [si], bl
            inc si
            mov [si], 36d
            inc si
            pop bx
            pop ax
        ret
    getNextStart endp

    getNextStartForID proc
        push ax
        push bx
        
        mov si, offset Funciones
        gNSF_Li:
            mov ah, [si]
            mov al, [si+1]

            cmp ah, 36d
            je gNSF_L1

            inc si
            jmp gNSF_Li

        gNSF_L1:
            cmp al, 36d
            je gNSF_LF
            inc si
            inc bl
            jmp gNSF_Li
        gNSF_LF:
            inc si
            pop bx
            pop ax
        ret
    getNextStartForID endp

    saveFuncion proc
        push ax
        push bx
        push dx
        push si

        push ax
        mov ah, 02
        mov dl, 10d
        int 21h
        pop ax
        
        sF_Li:
            print menu_10
            call getNextStart ;put start on SI
            mov bl, 0
        sF_L2:
            cmp bl, 30d
            jge sF_Pf

            mov ah,01
            int 21h

            cmp al, 13d
            je sF_F

            mov [si],al
            inc si
            inc bl
            jmp sF_L2

        sF_Pf:
            mov ah, 02
            mov dl, 10d
            int 21h
        sF_F:
            mov si, offset fnumber
            mov al, [si]
            inc al
            mov [si], al

            pop si
            pop dx
            pop bx
            pop ax
            ret
    saveFuncion endp

    saveFromBuffer proc
        push ax
        push bx
        push dx
        push si
        push di

            mov di, offset fileBuffer
        sFB_Li:
            call getNextStart ;put start on SI
        sFB_L2:
            mov bl, [di]
            cmp bl, 36d
            je sFB_Pf
            cmp bl, 13d
            je sFB_L2_ig
            cmp bl, 10d
            je sFB_L2_ig
            cmp bl, 59d
            je sFB_L2_nx
            
            mov [si], bl
            inc si
            inc di
            jmp sFB_L2
            
            sFB_L2_ig:
                inc di
                jmp sFB_L2
            sFB_L2_nx:
                inc di
                mov bl, [di]
                cmp bl, 36d
                je sFB_Pf
                cmp bl, 13d
                je sFB_L2_nx
                cmp bl, 10d
                je sFB_L2_nx
                cmp bl, 59d
                je sFB_L2_nx
                jmp sFB_Li
        sFB_Pf:
            mov ah, 02
            mov dl, 10d
            int 21h
        sFB_F:
            mov si, offset fnumber
            mov al, [si]
            inc al
            mov [si], al

            pop di
            pop si
            pop dx
            pop bx
            pop ax
            ret
    saveFromBuffer endp

    delay proc ; ax -> tiempo
		push si
        push di

        mov si, ax
		mov di, ax
		delay1:
			dec si
			jz finDelay
			mov di, ax
			delay2:
				dec di
				jz delay1
			jmp delay2
		finDelay:
            pop di
            pop si
		    ret
	delay endp

    sendFunction proc
        push ax
        push bx

        call showFunctions
        call selectID
        call buscarIDtoBX
        cmp al, 0
        je sdF_N_Found
        cmp al, 1
        je sdF_Found
        jmp sdF_LF

        sdF_Found:
            mov si, bx ; Posicion de la funcion en SI

            sdF_send:
                mov bl,[si]
                mov dx, 3F8h
                mov al, bl
                out dx, al
                cmp bl, 36d
                je sdF_LF
                inc si
                mov ax, 100
                call delay
                jmp sdF_send
        sdF_N_Found:
            println menu_inc_f
            
        sdF_LF:
            pop bx
            pop ax
            ret
    sendFunction endp

    menuRegistro proc
        push ax
        push dx
    mR_i:
        println headerL
        println header5
        println headerA
        println menu_8
        println menu_9
        println menu_volver
        print menu_i
        
        mov ah, 01
        int 21h

        cmp al,49d
        je mR_L1
        cmp al,50d
        je mR_L2
        cmp al, 51d
        je mR_LF

        println menu_inc
        jmp mR_i ; opcion invalida, repito el menu

        mR_L1: ; ingresar nombre
            call saveFuncion
            jmp mR_LF
        mR_L2:
            mov ah, 02
            mov dl, 10d
            int 21h

            print menu_14
            mov si, offset tempPath
            mR_L2_Li:
                mov ah, 01
                int 21h

                cmp al, 13d
                je mR_L2_Lf
                mov [si], al
                inc si
                jmp mR_L2_Li
            mR_L2_Lf:
                mov [si], 0
                call openFile
                cmp bl, 1
                je mR_LF
                call readChars
                call closeFile
                call saveFromBuffer
        mR_LF:
            mov ah, 02
            mov dl, 10d
            int 21h 

            pop dx
            pop ax
            ret
    menuRegistro endp

    menuDerivar proc
        push ax
    mD_i:
        println headerL
        println header6
        println headerA
        println menu_11
        println menu_12
        print menu_i
        
        mov ah, 01
        int 21h

        cmp al,49d
        je mD_L1
        cmp al,50d
        je mD_LF

        println menu_inc
        jmp mD_i ; opcion invalida, repito el menu

        mD_L1: ; ingresar nombre
            call showFunctions
            call selectID
            call convertToVars
            cmp cl, 1
            je mD_LF
            call derivar
            pop ax
            ret
        mD_LF:
            print line_break
        pop ax
        ret
    menuDerivar endp

    menuIntegrar proc
        push ax
    mI_i:
        println headerL
        println header7
        println headerA
        println menu_11
        println menu_12
        print menu_i
        
        mov ah, 01
        int 21h

        cmp al,49d
        je mI_L1
        cmp al,50d
        je mI_LF

        println menu_inc
        jmp mI_i ; opcion invalida, repito el menu

        mI_L1: ; ingresar nombre
            call showFunctions
            call selectID
            call convertToVars
            cmp cl, 1
            je mI_LF
            call integrar
            pop ax
            ret
        mI_LF:
            print line_break
        pop ax
        ret
    menuIntegrar endp

    openFile proc
        push ax
        push dx

        mov bl, 0
        mov ah, 3Dh
        mov al, 2
        mov dx, offset tempPath
        int 21h
        jc oF_Error
        mov handle, ax
        jmp oF_F

        oF_Error:
            println menu_err_of
            println menu_press
            mov bl, 1
            mov ah, 01
            int 21h
        oF_F:
            pop ax
            pop dx
            ret
    openFIle endp

    readChars proc
        mov ah, 3Fh
        mov bx, handle
        mov cx, 350
        mov dx, offset fileBuffer
        int 21h
        ret
    readChars endp

    closeFile proc
        push ax
        push bx

        mov ah, 3Eh
        mov bx, handle
        int 21h

        pop bx
        pop ax
        ret
    closeFile endp

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
        println menu_5
        println menu_6
        println menu_7
        println menu_v
        print menu_i
        
        mov ah, 01
        int 21h

            println menu_v
            
            cmp al,49d ;1
            je pm_LF_1

            cmp al,50d ;2
            je pm_LF_2

            cmp al,51d ;3
            je pm_LF_3

            cmp al,52d ;4
            je pm_LF_4

            cmp al,53d ;5
            je pm_LF_5

            cmp al,54d ;6
            je pm_LF_6

            cmp al,55d ;7
            je pm_LF_7
            
            pm_LF_Inc:
                println menu_inc
                jmp pm_i ; opcion invalida, repito el menu

            pm_LF_1:
                call menuDerivar
                jmp pm_i
            pm_LF_2:
                call menuIntegrar
                jmp pm_i
            pm_LF_3:
                mov si, offset fnumber
                mov bl, [si]
                cmp bl, 0
                je pm_LF_3_empty
                call showFunctions
                println menu_press 
                mov ah, 01
                int 21h
                jmp pm_i
                pm_LF_3_empty:
                    println header9
                    jmp pm_i
            pm_LF_4:
                call menuRegistro
                jmp pm_i
            pm_LF_5:
                ;call resolver
                jmp pm_i
            pm_LF_6:
                call sendFunction
                jmp pm_i
            pm_LF_7:
                ; End of program
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