import boto3
aws_access_key_id = 'AKIA4G2S4MZD53UTQ54I'
aws_secret_access_key = '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'
region_name = 'us-east-2'
bucket_name = 'bucket-miaproyecto2-g10'
s3 = boto3.client(
    's3',
    aws_access_key_id=aws_access_key_id,
    aws_secret_access_key=aws_secret_access_key,
    region_name=region_name
)



def create_in_bucket(name, path, body):
    try:
        s3.head_object(Bucket=bucket_name, Key='Archivos/' + path + '/' + name)
        print("Error el archivo ya existe")
        return "Error el archivo ya existe"
    except:

        s3.put_object(
            Bucket=bucket_name, 
            Key='Archivos/' + path + '/' + name, 
            Body=body,
        )
        print("Archivo creado exitosamente")
        return "Archivo creado exitosamente"


def delete_in_bucket(path, name = ""):
    if name == "":
        response = s3.list_objects_v2(Bucket=bucket_name, Prefix='Archivos/' + path + '/')
        if 'Contents' in response:
            objects = response['Contents']
            delete_keys = {'Objects': [{'Key': obj['Key']} for obj in objects]}
            s3.delete_objects(Bucket=bucket_name, Delete=delete_keys)
            print("Carpeta eliminada exitosamente")
            return "Carpeta eliminada exitosamente"
        else:
            print("Error la carpeta no existe")
            return "Error la carpeta no existe"
    else:
        try:
            s3.head_object(Bucket=bucket_name, Key='Archivos/' + path + '/' + name)
            s3.delete_object(
                Bucket=bucket_name, 
                Key='Archivos/' + path + '/' + name, 
            )
            print("Archivo eliminado exitosamente")
        except:
            print("Error el archivo no existe")
            return "Error el archivo no existe"




def copy_bucket_to_bucket(_from, _to):
    if _from.find(".") != -1:
        try:
            s3.head_object(Bucket=bucket_name, Key='Archivos/' + _from)
            response = s3.list_objects_v2(Bucket=bucket_name, Prefix='Archivos/' + _to + '/')
            if 'Contents' not in response:
                print("Error la carpeta destino no existe")
                return "Error la carpeta destino no existe"
            try:
                s3.head_object(Bucket=bucket_name, Key='Archivos/' + _to + '/' + _from.split("/")[-1])
                print("Error el archivo ya existe")
                return "Error el archivo ya existe"
            except:
                s3.copy_object(
                    Bucket=bucket_name,
                    CopySource={'Bucket': bucket_name, 'Key': 'Archivos/' + _from},
                    Key='Archivos/' + _to + '/' + _from.split("/")[-1]
                )
                print("Archivo copiado exitosamente")
                return "Archivo copiado exitosamente"
        except:
            print("Error el archivo no existe")
            return "Error el archivo no existe"
    else:
        try:
            # s3.head_object(Bucket=bucket_name, Key='Archivos/' + _from)
            response = s3.list_objects_v2(Bucket=bucket_name, Prefix='Archivos/' + _to + '/')
            if 'Contents' not in response:
                print("Error la carpeta destino no existe")
                return "Error la carpeta destino no existe"
            try:
                s3.head_object(Bucket=bucket_name, Key='Archivos/' + _to + '/' + _from.split("/")[-1])
                print("Error la carpeta ya existe")
                return "Error la carpeta ya existe"
            except:
                response = s3.list_objects_v2(Bucket=bucket_name, Prefix='Archivos/' + _from + '/')
                if 'Contents' in response:
                    objects = response['Contents']
                    for obj in objects:
                        s3.copy_object(
                            Bucket=bucket_name,
                            CopySource={'Bucket': bucket_name, 'Key': obj['Key']},
                            Key='Archivos/' + _to + '/' + obj['Key'].split("/")[-1]
                        )
                    print("Carpeta copiada exitosamente")
                    return "Carpeta copiada exitosamente"
                else:
                    print("Error la carpeta no existe")
                    return "Error la carpeta no existe"
        except:
            print("Error la carpeta no existe")
            return "Error la carpeta no existe"
        
def rename_bucket(file, new_name):
    tmp = file.split("/")
    tmp.pop()
    path = ""
    for folder in tmp:
        path += folder + "/"
    
    try:
        s3.head_object(Bucket=bucket_name, Key='Archivos/' + file)
        s3.copy_object(
            Bucket=bucket_name,
            CopySource={'Bucket': bucket_name, 'Key': 'Archivos/' + file},
            Key='Archivos/' + path + new_name
        )
        s3.delete_object(
            Bucket=bucket_name, 
            Key='Archivos/' + file, 
        )
        print("Archivo renombrado exitosamente")
        return "Archivo renombrado exitosamente"
    except:
        print("Error el archivo no existe")
        return "Error el archivo no existe"
    

def modify_in_bucket(file, body):
    try:
        s3.head_object(Bucket=bucket_name, Key='Archivos/' + file)
        s3.put_object(
            Bucket=bucket_name, 
            Key='Archivos/' + file, 
            Body=body,
        )
        print("Archivo modificado exitosamente")
        return "Archivo modificado exitosamente"
    except:
        print("Error el archivo no existe")
        return "Error el archivo no existe"

def delete_all_in_bucket():
    response = s3.list_objects_v2(Bucket=bucket_name, Prefix='Archivos/')
    if 'Contents' in response:
        objects = response['Contents']
        delete_keys = {'Objects': [{'Key': obj['Key']} for obj in objects]}
        s3.delete_objects(Bucket=bucket_name, Delete=delete_keys)
        print("Carpeta eliminada exitosamente")
        return "Carpeta eliminada exitosamente"
    else:
        print("Error la carpeta no existe")
        return "Error la carpeta no existe"

# delete_all_in_bucket()

# modify_in_bucket('nueva/file2.txt', 'Adios mundo')
# rename_bucket('nueva/file.txt', 'file2.txt')

# copy_bucket_to_bucket('nueva/file.txt', 'nuevas')

# create_in_bucket('file1.txt', 'nuevas', 'Hola mundo')
# delete_in_bucket('nueva', 'file.txt')




