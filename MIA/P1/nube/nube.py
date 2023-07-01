from pydrive2.auth import GoogleAuth
from pydrive2.drive import GoogleDrive

directorio_credenciales = 'credentials_module.json'
id_carpeta_nube="1hPRI__IBHidPjx-2VO8cvaMFX9TEP-5e"

#Metodo de inicio de sesion
def login():
    GoogleAuth.DEFAULT_SETTINGS['client_config_file'] = directorio_credenciales
    gauth = GoogleAuth()
    gauth.LoadCredentialsFile(directorio_credenciales)

    if gauth.credentials is None:
        gauth.LocalWebserverAuth()
    elif gauth.access_token_expired:
        gauth.Refresh()
    else:
        gauth.Authorize()

    gauth.SaveCredentialsFile(directorio_credenciales)
    credenciales = GoogleDrive(gauth)
    return credenciales

def metodo_create_nube(nombre_archivo, path, contenido):
    credenciales = login()
    id_ultima_carpeta=revisar_directorio(path)
    #Verificar el nombre del archivo y verificar que no exista el archivo en esa ruta
    query="'"+id_ultima_carpeta+"' in parents and trashed=false"
    file_list=credenciales.ListFile({'q': query}).GetList()
    existe=False
    # name_title=nombre_archivo.split(".")
    # nombre_archivo=name_title[0]
    for file in file_list:
        if(file['title']==nombre_archivo):
            existe=True
    if(existe):
        print("Nombre repetido, revise el nombre del archivo")
        return(" - Output - Create - El archivo ya existe.")
    else:
        print(nombre_archivo)
        archivo = credenciales.CreateFile({'title': nombre_archivo,
                                        'parents': [{"kind": "drive#fileLink",
                                                        "id": id_ultima_carpeta}]})
        archivo.SetContentString(contenido)
        archivo.Upload()
        print("Archivo en la nube creado con éxito.")
        return(" - Output - Create - Archivo creado correctamente")

def metodo_delete_nube(path_valor,name_valor,tipo_eliminacion):
    if path_valor[0] == "/":
        path_valor = path_valor[1:]
    if path_valor[-1] == "/":
        path_valor = path_valor[:-1]
        
    id_padre=encontrar_padre_carpeta(path_valor)
    if(id_padre=="no"):
        print("Carpeta o archivo no ubicada en la nube")
        return(" - Output - Delete - Carpeta o archivo no ubicado en la nube")
    credenciales = login()
    if(tipo_eliminacion):#es un archivo
        id_ubicacion=encontrar_carpeta_ubicacion(path_valor)
        query="'"+id_ubicacion+"' in parents and trashed=false"
        file_list = credenciales.ListFile({'q': query}).GetList()
        for file in file_list:
            if(file['title']==name_valor):
                file.Trash()
                print("Archivo eliminado con exito")
                return(" - Output - Delete - Archivo eliminado correctamente")
            
    else:#es una carpeta
        nuevo_path=path_valor.split("/")
        name_origen=nuevo_path[len(nuevo_path)-1]
        query="'"+id_padre+"' in parents and trashed=false"
        file_list = credenciales.ListFile({'q': query}).GetList()
        for file in file_list:
            if(file['title']==name_origen):
                file.Trash()
                print("Carpeta eliminada con exito")
                return(" - Output - Delete - Directorio eliminado correctamente")       
    print("Delete archivo en la NUBE")

def metodo_copy_nube(from_valor,to_valor):
    # print(from_valor, to_valor)
    if from_valor[0] == "/":
        from_valor = from_valor[1:]
    if to_valor[0] == "/":
        to_valor = to_valor[1:]
    if from_valor[-1] == "/":
        from_valor = from_valor[:-1]
    if to_valor[-1] == "/":
        to_valor = to_valor[:-1]
    # print(from_valor, to_valor)

    id_carpeta_destino = encontrar_carpeta_ubicacion(to_valor)
    name_archivo,path_nuevo=extraer_nombre_de_path(from_valor)
    id_path_origen=encontrar_carpeta_ubicacion(path_nuevo)    
    # name_title=name_archivo.split(".")
    # name_archivo=name_title[0]
    credenciales = login()
    if(id_carpeta_destino=="no" or id_path_origen=="no"):
        print("Ruta de destino no encontrada.")
        return(" - Output - Copy - Ruta de destino no encontrada.")
    else:
        if(len(name_archivo)>1):
            query="'"+id_path_origen+"' in parents and trashed=false"
            file_list = credenciales.ListFile({'q': query}).GetList()
            for file in file_list:
                if(file['title']==name_archivo):
                    content = file.GetContentString()
                    archivo = credenciales.CreateFile({'title': name_archivo,
                                        'parents': [{"kind": "drive#fileLink",
                                                        "id": id_carpeta_destino}]})
                    archivo.SetContentString(content)
                    archivo.Upload()
                    print("Archivo copiado con exito a nueva carpeta")
                    return(" - Output - Copy - Archivo copiado con exito a nueva carpeta")
        else:
            #Para un texto plano: text/plain
            #Para un folder: application/vnd.google-apps.folder
            id_path_origen=encontrar_carpeta_ubicacion(from_valor)
            bucle_carpetas_archivos(id_path_origen,id_carpeta_destino)
            print("Copiar una carpeta nueva a otra carpeta")
            return( "- Output - Copy - Copiar una carpeta nueva a otra carpeta")
    print("Copy archivo en la NUBE")

def bucle_carpetas_archivos(id_carpeta_origen,id_carpeta_destino):
    credenciales=login()
    query="'"+id_carpeta_origen+"' in parents and trashed=false"
    file_list = credenciales.ListFile({'q': query}).GetList()
    for file in file_list:
        if(file['mimeType']=='text/plain'):
            name_archivo=file['title']
            content = file.GetContentString()
            archivo = credenciales.CreateFile({'title': name_archivo,
                                        'parents': [{"kind": "drive#fileLink",
                                                        "id": id_carpeta_destino}]})
            archivo.SetContentString(content)
            archivo.Upload()
    for file in file_list:
        if(file['mimeType']=='application/vnd.google-apps.folder'):
            name_archivo=file['title']
            newfolder = credenciales.CreateFile({'title': name_archivo, 
                                            "parents": [{"kind": "drive#fileLink", 
                                                        "id": id_carpeta_destino}],
                                            "mimeType": "application/vnd.google-apps.folder"})
            newfolder.Upload()
            bucle_carpetas_archivos(file['id'],newfolder['id'])
    
        
def metodo_rename_nube(path_valor,name_valor):
    if path_valor[0] == "/":
        path_valor = path_valor[1:]
    if path_valor[-1] == "/":
        path_valor = path_valor[:-1]

    name_origen,path_nuevo=extraer_nombre_de_path(path_valor)
    id_carpeta_ubicacion=encontrar_carpeta_ubicacion(path_nuevo)
    # name_title=name_origen.split(".")
    # name_origen=name_title[0]
    if(id_carpeta_ubicacion=="no"):
        print("Carpeta o archivo no ubicada en la nube")
        return(" - Output - Rename - Error al encontrar la ruta del archivo")
    else:
        credenciales = login()
        query="'"+id_carpeta_ubicacion+"' in parents and trashed=false"
        file_list = credenciales.ListFile({'q': query}).GetList()
        for file in file_list:
            if(file['title']==name_origen):
                file['title']=name_valor
                file.Upload()
                print("Archivo renombrado con exito")
                return(" - Output - Rename - Archivo renombrado con exito")
    print("Rename Nube")
    print("Carpeta o archivo no ubicada en la nube")
    return(" - Output - Rename - Error al encontrar la ruta del archivo")

def metodo_modify_nube(path_valor,body_valor):
    if path_valor[0] == "/":
        path_valor = path_valor[1:]
    if path_valor[-1] == "/":
        path_valor = path_valor[:-1]

    name_origen,path_nuevo=extraer_nombre_de_path(path_valor)
    id_carpeta_ubicacion=encontrar_carpeta_ubicacion(path_nuevo)
    # name_title=name_origen.split(".")
    # name_origen=name_title[0]
    # name_origen = name_origen
    if(id_carpeta_ubicacion=="no"):
        print("Archivo no ubicado en la nube")
        return(" - Output - Modify - Error, archivo no encontrado")
    else:
        credenciales = login()
        query="'"+id_carpeta_ubicacion+"' in parents and trashed=false"
        file_list = credenciales.ListFile({'q': query}).GetList()
        for file in file_list:
            if(file['title']==name_origen):
                file.SetContentString(body_valor)
                file.Upload()
                print("Archivo modificado con exito")
                return(" - Output - Modify - Texto modificado con exito")
    print("Modify nube")

def metodo_add_nube(path_valor,body_valor):
    if path_valor[0] == "/":
        path_valor = path_valor[1:]
    if path_valor[-1] == "/":
        path_valor = path_valor[:-1]

    name_origen,path_nuevo=extraer_nombre_de_path(path_valor)
    id_carpeta_ubicacion=encontrar_carpeta_ubicacion(path_nuevo)
    # name_title=name_origen.split(".")
    # name_origen=name_title[0]
    if(id_carpeta_ubicacion=="no"):
        print("Carpeta o archivo no ubicada en la nube")
        return(" - Output - Add - Error, archivo no encontrado")
    else:
        credenciales = login()
        query="'"+id_carpeta_ubicacion+"' in parents and trashed=false"
        file_list = credenciales.ListFile({'q': query}).GetList()
        for file in file_list:
            if(file['title']==name_origen):
                content = file.GetContentString()
                file.SetContentString(content+"\n"+body_valor)
                file.Upload()
                print("Archivo modificado con exito, se ha agregado el texto")
                return(" - Output - Add - Texto agregado con exito")
    print("Add nube")

def metodo_transfer_nube(from_valor,to_valor):
    if from_valor[0] == "/":
        from_valor = from_valor[1:]
    if to_valor[0] == "/":
        to_valor = to_valor[1:]
    if from_valor[-1] == "/":
        from_valor = from_valor[:-1]
    if to_valor[-1] == "/":
        to_valor = to_valor[:-1]



    id_carpeta_destino = encontrar_carpeta_ubicacion(to_valor)
    id_path_origen=encontrar_carpeta_ubicacion(from_valor)
    
    content=""
    # name_title=name_archivo.split(".")
    # name_archivo=name_title[0]
    if(id_carpeta_destino=="no" or id_path_origen=="no"):
        print("Ruta de destino no encontrada.")
        return(" - Output - Transfer - Ruta de origen o destino no encontrada")
    else:
        credenciales = login()
        id_padre=encontrar_padre_carpeta(from_valor)
        query="'"+id_padre+"' in parents and trashed=false"
        carpeta=False
        file_list = credenciales.ListFile({'q': query}).GetList()
        for file in file_list:
            if(file['id']==id_path_origen):
                if(file['mimeType']=='application/vnd.google-apps.folder'):
                    carpeta=True
        if(carpeta):
            print("Transferir una carpeta nueva a otra carpeta")
            #Para un texto plano: text/plain
            #Para un folder: application/vnd.google-apps.folder
            id_path_origen=encontrar_carpeta_ubicacion(from_valor)
            bucle_carpetas_archivos(id_path_origen,id_carpeta_destino)
            query="'"+id_path_origen+"' in parents and trashed=false"
            file_list = credenciales.ListFile({'q': query}).GetList()
            for file in file_list:
                file.Delete()
            return(" - Output - Transfer - Carpeta transferida con exito a nueva carpeta")
        else:
            query="'"+id_padre+"' in parents and trashed=false"
            file_list = credenciales.ListFile({'q': query}).GetList()
            arreglo_nombre=from_valor.split("/")
            name_archivo=arreglo_nombre[len(arreglo_nombre)-1]
            for file in file_list:
                if(file['title']==name_archivo):
                    content = file.GetContentString()
                    file.Delete()
                    archivo = credenciales.CreateFile({'title': name_archivo,
                                        'parents': [{"kind": "drive#fileLink",
                                                        "id": id_carpeta_destino}]})
                    
                    archivo.SetContentString(content)
                    archivo.Upload()
                    print("Archivo transferido con exito a nueva carpeta")
                    return(" - Output - Transfer - Archivo transferido con exito a nueva carpeta")
    print("Transfer archivo en la NUBE")
  

       
def extraer_nombre_de_path(path_valor):
    arreglo_general=path_valor.split("/")
    name_valor=arreglo_general[len(arreglo_general)-1]
    path_nuevo=""
    path_nuevo+=arreglo_general[0]
    for x in arreglo_general:
        if(x is not arreglo_general[0] and x is not arreglo_general[len(arreglo_general)-1]):
            path_nuevo+="/"+x
    return name_valor,path_nuevo

def encontrar_padre_carpeta(path_valor):
    credenciales=login()
    id_actual=id_carpeta_nube
    id_padre=""
    arreglo_path=path_valor.split("/")
    if(len(arreglo_path)==1):
        return id_actual
    else:
        for x in range(len(arreglo_path)):
            query="'"+id_actual+"' in parents and trashed=false"
            id_padre=id_actual
            file_list = credenciales.ListFile({'q': query}).GetList()
            for file in file_list:
                if(len(arreglo_path)>0):
                    if(arreglo_path[0]==file['title']):
                        id_actual=file['id']
                        arreglo_path.pop(0)
    if(len(arreglo_path)==0):
        return id_padre
    return "no"
                    

def encontrar_carpeta_ubicacion(path_valor):
    credenciales=login()    
    id_actual=id_carpeta_nube
    arreglo_path=path_valor.split("/")
    for x in range(len(arreglo_path)):
        query="'"+id_actual+"' in parents and trashed=false"
        file_list = credenciales.ListFile({'q': query}).GetList()
        for file in file_list:
            #print("*"+arreglo_path[0]+" --> "+file['title'])
            if(len(arreglo_path)>0):
                if(arreglo_path[0]==file['title']):
                    id_actual=file['id']
                    arreglo_path.pop(0)
    if(len(arreglo_path)==0):
        return id_actual    
    return "no"

#Este metodo es el encargado de revisar el directorio, sino existe llama al metodo crear directorio y si ya existe captura el id de la carpeta final
def revisar_directorio(path_valor):
    credenciales=login()    
    id_actual=id_carpeta_nube
    arreglo_path=path_valor.split("/")
    for x in range(len(arreglo_path)):
        query="'"+id_actual+"' in parents and trashed=false"
        file_list = credenciales.ListFile({'q': query}).GetList()
        for file in file_list:
            if(len(arreglo_path)>0):
                if(arreglo_path[0]==file['title']):
                    id_actual=file['id']
                    arreglo_path.pop(0)
    if(len(arreglo_path)==0):
        return id_actual
    else:
        path_nuevo=""
        path_nuevo+=arreglo_path[0]
        i=0
        for x in arreglo_path:
            if(i != 0):
                path_nuevo=path_nuevo+"/"+x
            else:
                i=1
        id_actual=metodo_crear_directorio(path_nuevo,id_actual)
    return id_actual

#Crea en ciclo el directorio al saber que el directorio indicado no existe. Recibe un path completo que crea.
def metodo_crear_directorio(path_valor,id_origen):
    credenciales = login()
    id_actual = id_origen
    directorio=path_valor.split("/")
    for x in directorio:
        newfolder = credenciales.CreateFile({'title': x, 
                                            "parents": [{"kind": "drive#fileLink", 
                                                        "id": id_actual}],
                                            "mimeType": "application/vnd.google-apps.folder"})
        newfolder.Upload()
        id_actual=newfolder['id']
    return id_actual

#if __name__ == '__main__':
    # metodo_crear_directorio("personal/drivenew/xd")
    #metodo_create_nube("Ejemplo3.txt","archivos","Este es el contenido del archivo1")
    # metodo_create_nube("proof.txt", "prueba1", "Transfer contenido para probar si acepta el nombre repetido de un archivo2xxfdsfsdf")
    #metodo_rename_nube("usr/cp3/f3/update2","update3")
    #metodo_rename_nube("usr/cp3/f3/update2/sunflower_by_posty_vr2.txt","lamoleprueba 1")
    #metodo_modify_nube("usr/cp3/f3/folderupdate/sunflower_by_posty_vr2.txt","Nuevo contenido como prueba del metodo modify")
    #metodo_add_nube("usr/cp3usr/cp3/f3/folderupdate/sunflower_by_posty_vr2.txt","Linea 2 Nuevo contenido como prueba del metodo modify")
    #metodo_delete_nube("usr/cp3/f3","new_name_sunflower.txt",True)
    #metodo_delete_nube("carpeta77","",False)
    #metodo_delete_nube("carpeta1/ejemplo/prueba","",False)
    #metodo_delete_nube("carpeta calificacion","calificacion3.txt",True)
    #metodo_copy_nube("usr/cp3/f3/update3/lamoleprueba 1.txt","usr")
    #metodo_transfer("usr/cp3/f3/update3/prueba transfer.txt","usr/cp1/f1")
    #carpetas_archivos("1ejzzeCzVRru72-vhqo82hfoab8gc7EUg")
    #metodo_copy_nube("usr/cp3/f3","usr/cp2/f2")
    #metodo_transfer("usr/cp3/f3","usr/cp1/f1")
    #metodo_transfer_nube("carpeta calificacion","carpeta prueba 2/carpetaalfa/carpetabetta")
    #var=encontrar_padre_carpeta("carpeta1/ejemplo/prueba/calificacion4.txt")
    #print(var)