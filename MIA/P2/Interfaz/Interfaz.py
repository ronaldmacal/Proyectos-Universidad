import tkinter as tk
import Login as login
import sys
import Encrypt as encrypt
sys.path.append("..")
import main
'''***************************************************************************************************************
################################################   Crear Ventana  ################################################
***************************************************************************************************************'''
type_variable = True
encrypt_log_variable = "false"
encrypt_read_variable = "false"
llave_variable = "miaproyecto12345"

def main_window():
    

    window = tk.Tk()

    window.title("Archivos")
    window.geometry("1000x500")


    label = tk.Label(window, text="Consola")
    label.place(x=20, y=50)

    window.configure(bg='yellow')
    label.configure(bg='yellow')

    text_area = tk.Text(window, height=20, width=90)
    text_area.place(x=30, y=80)

    def on_key_pressed(event):
        commands = text_area.get("1.0", "end-1c")
        commands = commands.split("\n")
        # print(commands)
        run_commands(commands)

    text_area.bind("<KP_Enter>", on_key_pressed)


    # def on_key_pressed(event):
    #     if event.keysym == 'Return' or event.keysym == 'KP_Enter':  # Check for Enter/Return key
    #         input_text = text_area.get("1.0", "end-1c")  # Retrieve the text from the text box
    #         print(input_text)



    '''***************************************************************************************************************
    ###########################################   Metodos para cada botón  ###########################################
    ***************************************************************************************************************'''

    def metodo_configure():
        configure_options()
    def metodo_transfer():
        transfer_window()
    def metodo_create():    
        create_window()
    def metodo_rename():
        rename_window()
    def metodo_delete():
        delete_window()
    def metodo_modify():
        modify_window()
    def metodo_copy():
        copy_window()
    def metodo_add():
        add_window()
    def metodo_backup():
        print("backup")
    def metodo_exec():
        exec_window()
    def metodo_cerrar_sesion():
        window.destroy()
        login.main_window()
        

    '''***************************************************************************************************************
    ###########################################        Crear Botones       ###########################################
    ***************************************************************************************************************'''

    button_configure = tk.Button(window, text="Configure", width=10, height=2, command=metodo_configure)
    button_transfer = tk.Button(window, text="Transfer", width=10, height=2, command=metodo_transfer)
    button_create = tk.Button(window, text="Create", width=10, height=2, command=metodo_create)
    button_rename = tk.Button(window, text="Rename", width=10, height=2, command=metodo_rename)
    button_delete = tk.Button(window, text="Delete", width=10, height=2, command=metodo_delete)
    button_modify = tk.Button(window, text="Modify", width=10, height=2, command=metodo_modify)
    button_copy = tk.Button(window, text="Copy", width=10, height=2, command=metodo_copy)
    button_add = tk.Button(window, text="Add", width=10, height=2, command=metodo_add)
    button_backup = tk.Button(window, text="Backup", width=10, height=2, command=metodo_backup)
    button_exec = tk.Button(window, text="Exec", width=10, height=2, command=metodo_exec)
    button_cerrar_sesion = tk.Button(window, text="Cerrar Sesion", width=10, height=2, command=metodo_cerrar_sesion)

    button_configure.place(x=710, y=30)
    button_transfer.place(x=850, y=30)
    button_create.place(x=710, y=100)
    button_rename.place(x=850, y=100)
    button_delete.place(x=710, y=170)
    button_modify.place(x=850, y=170)
    button_copy.place(x=710, y=240)
    button_add.place(x=850, y=240)
    button_backup.place(x=710, y=310)
    button_exec.place(x=850, y=310)
    button_cerrar_sesion.place(x=710, y=380)

    
    window.mainloop()



    '''***************************************************************************************************************
    ##############################################  Conexión de métodos  #############################################
    ***************************************************************************************************************'''

def configure_options():

    window = tk.Tk()

    window.title("Configure")
    window.geometry("500x500")

    type_label = tk.Label(window, text="type")
    type_label.place(x=20, y=50)

    encrypt_log_label = tk.Label(window, text="encrypt_log")
    encrypt_log_label.place(x=20, y=100)

    encrypt_read_label = tk.Label(window, text="encrypt_read")
    encrypt_read_label.place(x=20, y=150)

    llave_label = tk.Label(window, text="llave")
    llave_label.place(x=20, y=200)


    #drop down menu
    type_options = ["local", "nube"]
    type_variable = tk.StringVar(window)
    type_variable.set(type_options[0])
    type_menu = tk.OptionMenu(window, type_variable, *type_options)
    type_menu.place(x=100, y=50)
    

    encrypt_log_options = ["true", "false"]
    encrypt_log_variable = tk.StringVar(window)
    encrypt_log_variable.set(encrypt_log_options[0])
    encrypt_log_menu = tk.OptionMenu(window, encrypt_log_variable, *encrypt_log_options)
    encrypt_log_menu.place(x=100, y=100)

    encrypt_read_options = ["true", "false"]
    encrypt_read_variable = tk.StringVar(window)
    encrypt_read_variable.set(encrypt_read_options[0])
    encrypt_read_menu = tk.OptionMenu(window, encrypt_read_variable, *encrypt_read_options)
    encrypt_read_menu.place(x=100, y=150)

    #text field
    llave_entry = tk.Entry(window)
    llave_entry.place(x=100, y=200)

    save_configure_options_button = tk.Button(window, text="Guardar", width=10, height=2, command=lambda: save_configure_options(type_variable, encrypt_log_variable, encrypt_read_variable, llave_entry))
    save_configure_options_button.place(x=200, y=400)
    
    window.mainloop()



def save_configure_options(type, encrypt_log, encrypt_read, llave):
    global type_variable, encrypt_log_variable, encrypt_read_variable, llave_variable
    
    if type.get() == "local":
        type_variable= True
    else:
        type_variable= False
    
    encrypt_log_variable = encrypt_log.get()
    encrypt_read_variable = encrypt_read.get()
    llave_variable = llave.get()
    # print(llave_variable)

def create_window():
    global type_variable
    window = tk.Tk()

    window.title("Create")
    window.geometry("500x500")

    path_label = tk.Label(window, text="path")
    path_label.place(x=20, y=50)

    name_label = tk.Label(window, text="name")
    name_label.place(x=20, y=100)

    text_label = tk.Label(window, text="text")
    text_label.place(x=20, y=150)

    path_entry = tk.Entry(window)
    path_entry.place(x=100, y=50)

    name_entry = tk.Entry(window)
    name_entry.place(x=100, y=100)

    text_entry = tk.Text(window, height=10, width=30)
    text_entry.place(x=100, y=150)

    
    create_button = tk.Button(window, text="Crear", width=10, height=2, command= lambda: main.analizador_lexico("create -name->" + name_entry.get() + " -path->" + path_entry.get() + " -body->\"" + text_entry.get("1.0", "end-1c") + "\"", type_variable, encrypt_log_variable))
    create_button.place(x=200, y=400)

    window.mainloop()


def rename_window():
    window = tk.Tk()

    window.title("Rename")
    window.geometry("500x500")

    path_label = tk.Label(window, text="path")
    path_label.place(x=20, y=50)

    new_name_label = tk.Label(window, text="new_name")
    new_name_label.place(x=20, y=100)

    path_entry = tk.Entry(window)
    path_entry.place(x=100, y=50)

    new_name_entry = tk.Entry(window)
    new_name_entry.place(x=100, y=100)

    rename_button = tk.Button(window, text="Renombrar", width=10, height=2, command=lambda: main.analizador_lexico("rename -path->" + path_entry.get() + " -name->" + new_name_entry.get(), type_variable, encrypt_log_variable))
    rename_button.place(x=200, y=400)

    window.mainloop()

def delete_window():
    window = tk.Tk()

    window.title("Delete")
    window.geometry("500x500")

    path_label = tk.Label(window, text="path")
    path_label.place(x=20, y=50)

    name_label = tk.Label(window, text="name")
    name_label.place(x=20, y=100)

    path_entry = tk.Entry(window)
    path_entry.place(x=100, y=50)

    name_entry = tk.Entry(window)
    name_entry.place(x=100, y=100)

    delete_button = tk.Button(window, text="Eliminar", width=10, height=2, command=lambda: main.analizador_lexico("delete -path->" + path_entry.get() + " -name->" + name_entry.get(), type_variable, encrypt_log_variable))
    delete_button.place(x=200, y=400)

    window.mainloop()

def modify_window():
    window = tk.Tk()

    window.title("Modify")
    window.geometry("500x500")

    path_label = tk.Label(window, text="path")
    path_label.place(x=20, y=50)

    text_label = tk.Label(window, text="text")
    text_label.place(x=20, y=100)

    path_entry = tk.Entry(window)
    path_entry.place(x=100, y=50)

    text_entry = tk.Text(window, height=10, width=30)
    text_entry.place(x=100, y=100)

    modify_button = tk.Button(window, text="Modificar", width=10, height=2, command=lambda: main.analizador_lexico("modify -path->" + path_entry.get() + " -body->\"" + text_entry.get("1.0", "end-1c") + "\"", type_variable, encrypt_log_variable))
    modify_button.place(x=200, y=400)

    window.mainloop()

def copy_window():
    window = tk.Tk()

    window.title("Copy")
    window.geometry("500x500")

    from_path_label = tk.Label(window, text="from_path")
    from_path_label.place(x=20, y=50)

    to_path_label = tk.Label(window, text="to_path")
    to_path_label.place(x=20, y=100)

    from_path_entry = tk.Entry(window)
    from_path_entry.place(x=100, y=50)

    to_path_entry = tk.Entry(window)
    to_path_entry.place(x=100, y=100)

    copy_button = tk.Button(window, text="Copiar", width=10, height=2, command=lambda: main.analizador_lexico("copy -from->" + from_path_entry.get() + " -to->" + to_path_entry.get(), type_variable, encrypt_log_variable))
    copy_button.place(x=200, y=400)

    window.mainloop()

def add_window():
    window = tk.Tk()

    window.title("Add")
    window.geometry("500x500")

    path_label = tk.Label(window, text="path")
    path_label.place(x=20, y=50)

    text_label = tk.Label(window, text="text")
    text_label.place(x=20, y=150)

    path_entry = tk.Entry(window)
    path_entry.place(x=100, y=50)

    text_entry = tk.Text(window, height=10, width=30)
    text_entry.place(x=100, y=100)

    add_button = tk.Button(window, text="Agregar", width=10, height=2, command=lambda: main.analizador_lexico("add -path->" + path_entry.get() + " -body->\"" + text_entry.get("1.0", "end-1c") + "\"", type_variable, encrypt_log_variable))
    add_button.place(x=200, y=400)

    window.mainloop()

def transfer_window():
    window = tk.Tk()

    window.title("Transfer")
    window.geometry("500x500")

    from_path_label = tk.Label(window, text="from_path")
    from_path_label.place(x=20, y=50)

    to_path_label = tk.Label(window, text="to_path")
    to_path_label.place(x=20, y=100)

    mode_label = tk.Label(window, text="mode")
    mode_label.place(x=20, y=150)

    from_path_entry = tk.Entry(window)
    from_path_entry.place(x=100, y=50)

    to_path_entry = tk.Entry(window)
    to_path_entry.place(x=100, y=100)

    mode_entry = tk.Entry(window)
    mode_entry.place(x=100, y=150)

    transfer_button = tk.Button(window, text="Transferir", width=10, height=2, command=lambda: main.analizador_lexico("transfer -from->" + from_path_entry.get() + " -to->" + to_path_entry.get() + " -mode->" + mode_entry.get(), type_variable, encrypt_log_variable))
    transfer_button.place(x=200, y=400)

    window.mainloop()

def exec_window():
    window = tk.Tk()

    window.title("Exec")
    window.geometry("500x500")

    path_label = tk.Label(window, text="path")
    path_label.place(x=20, y=50)

    path_entry = tk.Entry(window)
    path_entry.place(x=100, y=50)

    exec_button = tk.Button(window, text="Ejecutar", width=10, height=2, command=lambda: exec_command(path_entry.get()))
    exec_button.place(x=200, y=400)

    window.mainloop()


def path_with_spaces(command, parameter):
    path_array = command.split(" -")
    for x in path_array:
        if x.find(parameter) != -1:
            p = x.replace("\"", "")
            p = p.replace(f"{parameter}->", "")
            p = p.strip()
            return p


def run_commands(_commands):
    global type_variable , encrypt_log_variable, encrypt_read_variable, llave_variable
    commands = _commands
    if commands[0].find("configure") != -1:
        run_configure(commands[0])
        commands.pop(0)
    elif commands[0].find("exec") != -1:
        path = path_with_spaces(commands[0], "path")
        exec_command(path)
        commands.pop(0)
    if encrypt_read_variable == "true":
        tmp = encrypt.Encrypt()
        commands = tmp.decrypt_ecb_aes(commands[0], llave_variable)
        commands = commands.split("\n")
        # print(commands)

    for command in commands:
        main.analizador_lexico(command, type_variable, encrypt_log_variable)

def exec_command(path):
    commands_file = open("../Archivos/" + path, "r")
    commands = commands_file.read()
    commands_file.close()
    commands = commands.split("\n")
    run_commands(commands)


def run_configure(_command):
    global type_variable, encrypt_log_variable, encrypt_read_variable, llave_variable
    command = _command.split(" -")
    for i in command:
        if i.lower().find("type") != -1:
            if i.lower().find("local") != -1:
                type_variable = True
            else:
                type_variable = False
        elif i.lower().find("encrypt_log") != -1:
            if i.lower().find("true") != -1:
                encrypt_log_variable = True
            else:
                encrypt_log_variable = False
        elif i.lower().find("encrypt_read") != -1:
            if i.lower().find("true") != -1:
                encrypt_read_variable = "true"
            else:
                encrypt_read_variable = "false"
            # print(encrypt_read_variable)
        elif i.lower().find("llave") != -1:
            llave_variable = i.split("->")[1]



# def exec_command():
#     commands_file = open("commands.txt", "r")
#     commands = commands_file.read()
#     commands_file.close()
#     commands = commands.split("\n")
#     for command in commands:
#         main.analizador_lexico(command, type_variable)





# main_window()