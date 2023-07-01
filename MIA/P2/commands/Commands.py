import os 
import shutil


class FolderNotFound(Exception):
    pass

def find_folder(folder_name):
    if os.path.exists(folder_name):
        return True
    else:
        return False

def find_file(file_name, path):
    if os.path.isfile(path + "/" + file_name):
        return True
    else:
        return False


def create_folder(folder_name):
    if not find_folder(folder_name):
        os.makedirs(folder_name)
        print("Directorio creado correctamente")
        

def create_file(file, path1, body):
    path = "../Archivos/" + path1
    if find_file(file, path):
        print("El archivo ya existe")
        return(" - Output - Create - El archivo ya existe.")
    else:
        create_folder(path)
        archivo = open(path + "/" + file, "w+")
        archivo.write(body)
        archivo.close()
        print("Archivo creado correctamente")
        return(" - Output - Create - Archivo creado correctamente")


def delete_file(path1, name = ""):
    path = "../Archivos/" + path1
    # print(path)
    if find_folder(path):
        if name == "":
            shutil.rmtree(path)
            print("Directorio eliminado correctamente")
            return(" - Output - Delete - Directorio eliminado correctamente")
        else:
            if find_file(name, path):
                os.remove(path + "/" + name)
                print("Archivo eliminado correctamente")
                return(" - Output - Delete - Archivo eliminado correctamente")
            else:
                print("El archivo no existe")
                return(" - Output - Delete - Archivo no existe")
    else:
        print("El directorio no existe")
        return(" - Output - Delete - Directorio no existe")



def copy_file(from_path, to_path):
    file = from_path.split("/")[-1]
    if os.path.isfile(from_path) != True:
        raise FileNotFoundError("El archivo no existe")
    if os.path.isfile(to_path + "/" + file) or os.path.isfile(to_path + file):
        raise FileExistsError("El archivo ya existe")   
    if os.path.exists(to_path) != True:
        raise FolderNotFound("El directorio destino no existe")
         
    shutil.copy(from_path, to_path)

def copy_folder(from_path, to_path):
    if os.path.exists(to_path) != True:
        raise FolderNotFound("El directorio destino no existe")
    if os.path.exists(from_path) != True:
        raise FolderNotFound("El directorio origen no existe")
    
    files = os.listdir(from_path)
    for file in files:
        file_path = os.path.join(from_path, file)
        
        if os.path.isfile(file_path):
            copy_file(file_path, to_path)
        elif os.path.isdir(file_path):
            if to_path[-1] == "/":
                shutil.copytree(file_path, to_path + file)
            else:
                shutil.copytree(file_path, to_path + "/" + file)
    


def copy(from_path1, to_path1):
    from_path = "../Archivos/" + from_path1
    to_path = "../Archivos/" + to_path1

    try:
        if from_path1.find(".") != -1:
            copy_file(from_path, to_path)
            print("Archivo copiado correctamente")
            return(" - Output - Copy - Archivo copiado correctamente")
        else: 
            copy_folder(from_path, to_path)  
            print("Directorio copiado correctamente")
            return(" - Output - Copy - Directorio copiado correctamente")

        # print(os.path.isdir(from_path))
        # print(from_path)

    except FileExistsError:
        print("El archivo ya existe")
        return(" - Output - Copy - El archivo ya existe")
    except FolderNotFound:
        print("El directorio no existe")
        return(" - Output - Copy - El directorio no existe")
    except FileNotFoundError:
        print("El archivo no existe")
        return(" - Output - Copy - El archivo no existe")
    except:
        print("Error desconocido")
        return(" - Output - Copy - Error desconocido")

def transfer_file_local(from_path, to_path):

    file = from_path.split("/")[-1]
    tmp2 = file.split(".")[0]
    extension = file.split(".")[-1]
    file = file.split(".")[0]
    tmp = 1

    while os.path.isfile(to_path + "/" + file + "." + extension) == True:
        file = tmp2 + "(" + str(tmp) + ")"
        tmp += 1
    print(to_path + "/" + file)
    print(os.path.isfile(to_path + "/" + file + "." + extension))
    if os.path.isfile(from_path) != True:
        raise FileNotFoundError("El archivo no existe")
    if os.path.isfile(to_path + "/" + file + "." + extension):
        raise FileExistsError("El archivo ya existe")   
    if os.path.exists(to_path) != True:
        os.mkdir(to_path)
    
    shutil.move(from_path, to_path + "/" + file + "." + extension)
    print("Archivo movido correctamente")
    return(" - Output - Transfer - Archivo movido correctamente")

# transfer_file_local("../Archivos/carpeta1/nuevo_cali2d.txt", "../Archivos/carpeta calificacion/x")


def transfer_folder_local(from_path, to_path):
    if os.path.exists(to_path) != True:
        os.mkdir(to_path)
    if os.path.exists(from_path) != True:
        raise FolderNotFound("El directorio origen no existe")
    
    files = os.listdir(from_path)
    for file in files:
        file_path = os.path.join(from_path, file)
        
        if os.path.isfile(file_path):
            transfer_file_local(file_path, to_path)
        elif os.path.isdir(file_path):
            if to_path[-1] == "/":
                shutil.move(file_path, to_path + file)
            else:
                shutil.move(file_path, to_path + "/" + file)



def transfer_local(from_path1, to_path1):
    from_path = "../Archivos/" + from_path1
    to_path = "../Archivos/" + to_path1
    try:
        if from_path1.find(".") != -1:
            transfer_file_local(from_path, to_path)
        else:
            transfer_folder_local(from_path, to_path)
    except FileExistsError:
        print("El archivo ya existe")
        return(" - Output - Transfer - El archivo ya existe")
    except FolderNotFound:
        print("El directorio no existe")
        return(" - Output - Transfer - El directorio no existe")
    except FileNotFoundError:
        print("El archivo no existe")
        return(" - Output - Transfer - El archivo no existe")
    # except:
    #     print("Error desconocido")
    #     return(" - Output - Transfer - Error desconocido")

# transfer_local("carpeta1/", "carpeta calificacion/x")

# copy("carpeta1/nuevo_cali2.txt", "carpeta Calificacion")


# create_file("x.txt", "/x/", "hola")
# create_file("y.txt", "/y/", "hola")
# create_file("z.txt", "/z/", "hola")
# copy("x/x.txt", "y/z")
# copy("/x/", "/z/")
# delete_file("/x/")
# delete_file("/y/")
# delete_file("y_new2", "")



