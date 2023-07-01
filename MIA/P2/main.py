import os
import re
from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad
import binascii
from commands.Commands import create_file, delete_file, copy, transfer_local
from commands.local import metodo_rename_local,metodo_modify_local,metodo_add_local,metodo_exec_local,encontrar_carpeta,encontrar_archivo,crear_directorio
from nube.nube import metodo_create_nube,metodo_delete_nube,metodo_copy_nube,metodo_rename_nube,metodo_modify_nube,metodo_add_nube,metodo_transfer_nube
from datetime import datetime
# import Interfaz.Encrypt as encrypt
# from Interfaz import Encrypt
# from Interfaz.Encrypt import encrypt_ecb_aes

type_storage=True # True SI ES LOCAL, False SI ES EN LA NUBE
encriptado = "false"
log_key = "miaproyecto12345"
'''***************************************************************************************************************
#########################################  Analizador Lexico de comandos #########################################
***************************************************************************************************************'''
def analizador_lexico(linea_comando, _type_storage = True, _encriptado = "false", _key = "miaproyecto12345"):
    global type_storage, encriptado, log_key
    log_key = _key
    encriptado = _encriptado
    type_storage = _type_storage
    #Metodo para analizar cada linea de texto
    instruccion_comando=""
    name_valor=""
    mode_valor=""
    path_valor=""
    body_valor=""
    from_valor=""
    to_valor=""
    paso_name=False
    paso_mode=False
    paso_path=False
    paso_body=False
    paso_from=False
    paso_to=False
    #Divide la linea de comandos por espacios 
    arreglo_inicial=linea_comando.split(" ")
    instruccion_comando=arreglo_inicial[0].lower()
    for x in arreglo_inicial:
        arreglo_por_comando=x.split("->")
        if(len(arreglo_por_comando)>1):
            if(arreglo_por_comando[0].lower()=="-name"):
                name_valor=arreglo_por_comando[1]
                paso_name=True
            elif(arreglo_por_comando[0].lower()=="-mode"):
                mode_valor=arreglo_por_comando[1]
                paso_mode=True
            elif(arreglo_por_comando[0].lower()=="-path"):
                #Verificar que no tenga comillas
                # if(arreglo_por_comando[1].find("\"")==-1):
                #     path_valor=arreglo_por_comando[1]
                #     paso_path=True
                # else:
                #     path_valor=capturar_con_espacios(linea_comando,"th->")
                #     paso_path=True
                path_valor = path_with_spaces(linea_comando, "path")
                if path_valor[-1] == "/":
                    path_valor = path_valor[:-1]
                paso_path=True
                if(path_valor[0]=="/"):
                    path_valor=path_valor[1:]
            elif(arreglo_por_comando[0].lower()=="-from"):
                # if(arreglo_por_comando[1].find("\"")==-1):
                #     from_valor=arreglo_por_comando[1]
                #     paso_from=True
                # else:
                #     from_valor=capturar_con_espacios(linea_comando,"om->")
                #     paso_from=True
                from_valor = path_with_spaces(linea_comando, "from")
                paso_from=True
            elif(arreglo_por_comando[0].lower()=="-to"):
                # if(arreglo_por_comando[1].find("\"")==-1):
                #     to_valor=arreglo_por_comando[1]
                #     paso_to=True
                # else:
                #     paso_to=True
                #     to_valor=capturar_con_espacios(linea_comando,"to->")
                to_valor = path_with_spaces(linea_comando, "to")
                if to_valor[-1] == "/":
                    path_valor = path_valor[:-1]
                paso_to=True
            elif(arreglo_por_comando[0].lower()=="-body"):
                paso_body=True
                # body_valor=capturar_con_espacios(linea_comando,"dy->") 
                body_valor = path_with_spaces(linea_comando, "body")
                    
    if(instruccion_comando=="create"):
        agregar_a_bitacora(" - Input - Create - name:"+name_valor+" - path:"+path_valor)
        if(paso_name and paso_path and paso_body):
            if(type_storage):
                mensaje_salida=create_file(name_valor,path_valor,body_valor)
            else:
                mensaje_salida=metodo_create_nube(name_valor,path_valor,body_valor)
            agregar_a_bitacora(mensaje_salida)
        else:
            print("Error en el comando Create, verifique el ingreso del nombre, path y body sean correctos")
            agregar_a_bitacora(" - Output - Create - Error en el ingreso de datos. Nombre, path o body incorrectos.")
    elif(instruccion_comando=="delete"):
        if(paso_name):
            agregar_a_bitacora(" - Input - Delete - name:"+name_valor+" - path:"+path_valor)
        else:
            agregar_a_bitacora(" - Input - Delete - path:"+path_valor)
        if(paso_path):
            if(type_storage):
                mensaje_salida=delete_file(path_valor,name_valor)
            else:
                try:
                    mensaje_salida=metodo_delete_nube(path_valor,name_valor,paso_name)
                except:
                    print("Error en el comando Delete, archivo o directorio no encontrado")
                    mensaje_salida=" - Output - Delete - Error, archivo o directorio no encontrado"
            agregar_a_bitacora(mensaje_salida)
        else:
            print("Error en el comando Delete, debe ingresar el path obligatoriamente")
            agregar_a_bitacora(" - Output - Delete - Error en el ingreso de datos. Debe ingresar el path obligatoriamente")
    elif(instruccion_comando=="exec"):#Pendientes
        if(paso_path):
            metodo_exec_local(path_valor)
        else:
            print("Error en el comando exec, debe ingresar el path obligatoriamente")
    elif(instruccion_comando=="backup"):#Pendientes
        metodo_backup()
    elif(instruccion_comando=="copy"):
        agregar_a_bitacora(" - Input - Copy - from:"+from_valor+" | - to:"+to_valor)
        if(paso_from and paso_to):
            if(type_storage):
                mensaje_salida=copy(from_valor,to_valor)
            else:
                mensaje_salida=metodo_copy_nube(from_valor,to_valor)
            agregar_a_bitacora(mensaje_salida)
        else:
            print("Error en el comando Copy, ingrese los valores de origen y destino correctos")
            agregar_a_bitacora(" - Output - Copy - Error, los valores de origen y destino incorrectos")
    elif(instruccion_comando=="transfer"):
        agregar_a_bitacora(" - Input - Transfer - from:"+from_valor+" | - to:"+to_valor)
        if(paso_from and paso_to and paso_mode):
            mensaje_salida = ""
            if(mode_valor.lower() =="\"local\""):
                # metodo_transfer(from_valor,to_valor,mode_valor)
                try:
                    transfer_local(from_valor, to_valor)
                    mensaje_salida=" - Output - Transfer - Transferencia local exitosa."
                except:
                    print("Error en el comando Transfer, el modo de transferencia local.")
                    mensaje_salida=" - Output - Transfer - Error, el modo de transferencia local."
                # print("Error en el comando Transfer, el modo de transferencia solo funciona en la nube.")
                # mensaje_salida=" - Output - Transfer - Error, el modo de transferencia solo funciona en la nube."
            else:
                # mensaje_salida=metodo_transfer_nube(from_valor,to_valor)
                try:
                    mensaje_salida=metodo_transfer_nube(from_valor,to_valor)
                    print("Transferencia en la nube exitosa.")
                except:
                    print("Error en el comando Transfer, el origen, destino o modo del archivo incorrectos")
                    mensaje_salida=" - Output - Transfer - Error el origen, destino o modo del archivo incorrectos."
            agregar_a_bitacora(mensaje_salida)
        else:
            print("Error en el comando Transfer, ingrese el origen, destino y modo del archivo correctamente.")
            agregar_a_bitacora(" - Output - Transfer - Error el origen, destino o modo del archivo incorrectos.")
    elif(instruccion_comando=="rename"):
        agregar_a_bitacora(" - Input - Rename - Name:"+name_valor+" | - Path:"+path_valor)
        if(paso_path and name_valor):
            if(type_storage):
                mensaje_salida=metodo_rename_local(path_valor,name_valor)
            else:
                mensaje_salida=metodo_rename_nube(path_valor,name_valor)
            agregar_a_bitacora(mensaje_salida)
        else:
            print("Error en el comando Rename, verifique el path y el nombre del archivo sean los correctos.")
            agregar_a_bitacora(" - Output - Rename - Error, path o nombre incorrectos.")
    elif(instruccion_comando=="modify"):
        agregar_a_bitacora(" - Input - Modify - Body:"+body_valor+" | - Path:"+path_valor)
        if(paso_path and paso_body):
            if(type_storage):
                mensaje_salida=metodo_modify_local(path_valor,body_valor)
            else:
                mensaje_salida=metodo_modify_nube(path_valor,body_valor)
            agregar_a_bitacora(mensaje_salida)
        else:
            print("Error en el comando Modify, ingrese el valor correcto del path y el body del comando.")
            agregar_a_bitacora(" - Output - Modify - Error, valor incorrecto del path o body.")
    elif(instruccion_comando=="add"):
        agregar_a_bitacora(" - Input - Add - Path: "+path_valor)
        if(paso_path and paso_body):
            if(type_storage):
                mensaje_salida=metodo_add_local(path_valor,body_valor)
            else:
                mensaje_salida=metodo_add_nube(path_valor,body_valor)
            agregar_a_bitacora(mensaje_salida)
        else:
            print("Error en el comando Add, verifique el ingreso del path y el body sean correctos.")
            agregar_a_bitacora(" - Output - Add - Error, path o body incorrectos.")
    
def capturar_con_espacios(linea_comando,tipo):
    grabar=False
    acum_cadena=""
    contador=0
    for x in linea_comando:
        if(x=='\"'):
            if(grabar):
                grabar=False
            else:
                #Regresar 4 espacios
                armador=linea_comando[contador-4]+linea_comando[contador-3]+linea_comando[contador-2]+linea_comando[contador-1]
                if(armador==tipo):
                    grabar=True
        else:
            if(grabar):
                acum_cadena+=x 
        contador+=1
    return (acum_cadena)

'''***************************************************************************************************************
###########################################  Metodos para cada comando ###########################################
***************************************************************************************************************'''
def metodo_transfer(from_valor,to_valor,mode_valor):
    return("Metodo transfer local")

def metodo_backup():
    print("Aqui va el metodo del Backup")


def bitacora_encriptada(linea_bitacora):
    now = datetime.now()
    today=now.strftime("%d/%m/%Y, %H:%M:%S")
    linea_bitacora=today+str(linea_bitacora)
    path_logs="../Archivos/logs/"+str(datetime.now().year)+"/"+str(datetime.now().month)+"/"+str(datetime.now().day)+"/"
    if not encontrar_carpeta(path_logs):
        crear_directorio(path_logs)
    path_logs+="log_archivos_encriptado.txt"
    # e = Encrypt()
    if(encontrar_archivo(path_logs)):
        archivo=open(path_logs,"a")
        archivo.write("\n"+encrypt_ecb_aes(linea_bitacora))
        archivo.close()
    else:
        archivo=open(path_logs,"w+")
        archivo.write(encrypt_ecb_aes(linea_bitacora))
        archivo.close()



def bitacora_desencriptada(linea_bitacora):
    now = datetime.now()
    today=now.strftime("%d/%m/%Y, %H:%M:%S")
    linea_bitacora=today+str(linea_bitacora)
    path_logs="../Archivos/logs/"+str(datetime.now().year)+"/"+str(datetime.now().month)+"/"+str(datetime.now().day)+"/"
    if not encontrar_carpeta(path_logs):
        crear_directorio(path_logs)
    path_logs+="log_archivos.txt"
    if(encontrar_archivo(path_logs)):
        archivo=open(path_logs,"a")
        archivo.write("\n"+linea_bitacora)
        archivo.close()
    else:
        archivo=open(path_logs,"w+")
        archivo.write(linea_bitacora)
        archivo.close()

def agregar_a_bitacora(linea_bitacora):
    if(encriptado != True):
        bitacora_desencriptada(linea_bitacora)
    else:
        bitacora_encriptada(linea_bitacora)

def path_with_spaces(command, parameter):
    path_array = command.split(" -")
    for x in path_array:
        if x.lower().find(parameter) != -1:
            p = x.replace("\"", "")
            p = p.lower().replace(f"{parameter}->", "")
            p = p.strip()
            return p


def encrypt_ecb_aes(plaintext, key = "miaproyecto12345"):
    key_bytes = key.encode('utf-8')
    plaintext_bytes = plaintext.encode('utf-8')

    cipher = AES.new(key_bytes, AES.MODE_ECB)

    padded_plaintext = pad(plaintext_bytes, AES.block_size)

    ciphertext_bytes = cipher.encrypt(padded_plaintext)

    ciphertext_hex = binascii.hexlify(ciphertext_bytes).decode('utf-8')

    return ciphertext_hex
'''***************************************************************************************************************
###########################################   ###########################################
***************************************************************************************************************'''
# agregar_a_bitacora(" - Input - 2Prueba para agregar a la bitacora una linea")
# print("Ingresar cadena: ")
# cadena=input()
# analizador_lexico("create -name->prueba1.txt  -path->/carpeta1/nuevo/dkdk/jf8e/ -body->\"Este es el contenido del archivo1\"")
# ventana = formulario_login()
# archivo = open("archivo1.txt", "w+")
# archivo.close()

# os.rename("archivo1.txt","2.txt")

# print(path_with_spaces("create -name->prueba1.txt  -path->\"/Carpeta Ejemplo/\" -body->\"Este es el contenido del archivo1\" "))



