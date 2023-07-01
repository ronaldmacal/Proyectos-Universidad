
import commands.Commands as commands
import commands.local as local
import commands.bucket_commands as bucket
def analizador_lexico(lenea_comando):
    _type_to = ""
    _type_from = ""
    _ip = ""
    _port = ""
    _name = ""
    _body = ""
    _path = ""
    _type = ""
    _from = ""
    _to = ""
    _comando = ""
    comandos = lenea_comando.split(" -")
    _comando = comandos[0]
    for parametro in comandos:
        if parametro.lower().startswith("type_to"):
            _type_to = parametro.split("->")[1]
        elif parametro.lower().startswith("type_from"):
            _type_from = parametro.split("->")[1]
        elif parametro.lower().startswith("ip"):
            _ip = parametro.split("->")[1]
        elif parametro.lower().startswith("port"):
            _port = parametro.split("->")[1]
        elif parametro.lower().startswith("name"):
            _name = parametro.split("->")[1]
        elif parametro.lower().startswith("body"):
            _body = parametro.split("->")[1]
        elif parametro.lower().startswith("path"):
            _path = parametro.split("->")[1]
        elif parametro.lower().startswith("type"):
            _type = parametro.split("->")[1]
        elif parametro.lower().startswith("from"):
            _from = parametro.split("->")[1]
        elif parametro.lower().startswith("to"):
            _to = parametro.split("->")[1]


    _body = _body.replace("\"", "")
    _path = _path.replace("\"", "")
    _type = _type.replace("\"", "")
    _from = _from.replace("\"", "")
    _from = _from[1:]
    _to = _to.replace("\"", "")
    _path = _path[1:-1]


    _comando = _comando.lower()
    _type = _type.lower()

    if(_type == "server"):
        if(_comando == "create"):
            commands.create_file(_name, _path, _body)
        elif(_comando == "delete"):
            commands.delete_file(_path, _name)
        elif(_comando == "rename"):
            local.metodo_rename_local(_path, _name)
        elif(_comando == "modify"):
            local.metodo_modify_local(_path, _body)
        elif(_comando == "delete_all"):
            print("delete_all")
        elif(_comando == "open"):
            print("open")
    
    elif(_type == "bucket"):
        if(_comando == "create"):
            bucket.create_in_bucket(_name, _path, _body)
        elif(_comando == "delete"):
            bucket.delete_in_bucket(_path, _name)
        elif(_comando == "rename"):
            bucket.rename_bucket(_path, _name)
        elif(_comando == "modify"):
            bucket.modify_in_bucket(_path, _body)
        elif(_comando == "delete_all"):
            bucket.delete_all_in_bucket()
        elif(_comando == "open"):
            print("open")


    if(_comando == "copy"):
        if(_type_to == "server" and _type_from == "server"):
            commands.copy(_from, _to)
        elif(_type_to == "server" and _type_from == "bucket"):
            print("copy server bucket")
        elif(_type_to == "bucket" and _type_from == "server"):
            print("copy bucket server")
        elif(_type_to == "bucket" and _type_from == "bucket"):
            bucket.copy_bucket_to_bucket(_from, _to)
    elif(_comando == "transfer"):
        if(_type_to == "server" and _type_from == "server"):
            print("transfer server server")
        elif(_type_to == "server" and _type_from == "bucket"):
            print("transfer server bucket")
        elif(_type_to == "bucket" and _type_from == "server"):
            print("transfer bucket server")
        elif(_type_to == "bucket" and _type_from == "bucket"):
            print("transfer bucket bucket")

    if(_comando == "backup"):
        if(_type_to == "server" and _type_from == "bucket"):
            print("backup server bucket")
        elif(_type_to == "bucket" and _type_from == "server"):
            print("backup bucket server")
    elif(_comando == "recovery"):
        if(_type_to == "server" and _type_from == "bucket"):
            print("recovery server bucket")
        elif(_type_to == "bucket" and _type_from == "server"):
            print("recovery bucket server")








# analizador_lexico("Create -name->calificacion2.txt -path->/carpeta_calificacion1/ -body->\"Contenido del archivo calificacion2 carpeta_calificacion1\" -Type->bucket")
# analizador_lexico("Transfer -from->/carpeta_calificacion1/\"carpeta ejemplo\"/ejemplo3/calificacion1.txt -to->/\"carpeta ejemplo\"/ -type_from->bucket -type_to->server")
