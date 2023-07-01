import os

def crear_directorio(folder_name):
    if not encontrar_carpeta(folder_name):
        os.makedirs(folder_name)

def encontrar_carpeta(folder_name):
    if os.path.exists(folder_name):
        return True
    else:
        return False

def encontrar_archivo(path):
    if os.path.isfile(path):
        return True
    else:
        return False

def metodo_rename_local(path_valor,name_valor):
    #Primero capturo la direccion para renombrar el nuevo archivo de la ruta anterior
    path_nuevo=""
    arreglo_direccion=path_valor.split("/")
    for x in arreglo_direccion:
        if(x!=arreglo_direccion[len(arreglo_direccion)-1]):
            path_nuevo+=x+"/"
    #Try para usar la funcion de renombrar un archivo
    path_nuevo+=name_valor
    try:
        # print("../Archivos/"+path_valor, "../Archivos/" + path_nuevo)
        os.rename("../Archivos/"+path_valor, "../Archivos/" + path_nuevo)
    except FileNotFoundError:
        print("Error al encontrar la ruta del archivo")
        return(" - Output - Rename - Error al encontrar la ruta del archivo")
    else:
        print("Archivo renombrado con exito")
        return(" - Output - Rename - Archivo renombrado con exito")
    
def metodo_modify_local(path_valor,body_valor):
    if os.path.isfile("../Archivos/" + path_valor):
        try:
            with open("../Archivos/" + path_valor,"w") as file:
                file.write(body_valor)
        except FileNotFoundError:
            print("Error, archivo no encontrado")
            return(" - Output - Modify - Error, archivo no encontrado")
        else:
            print("Texto modificado con exito")
            return(" - Output - Modify - Texto modificado con exito")
    else:
        print("Error, archivo no encontrado")
        return(" - Output - Modify - Error, archivo no encontrado")
    
def metodo_add_local(path_valor,body_valor):
    try:
        with open("../Archivos/" + path_valor,"a") as file:
            file.write("\n"+body_valor)
    except FileNotFoundError:
        print("Error, archivo no encontrado")
        return(" - Output - Add - Error, archivo no encontrado")
    else:
        print("Texto agregado  con exito")
        return(" - Output - Add - Texto agregado con exito")

def metodo_exec_local(path_valor):
    print("Aqui va el metodo Exec")#Mio