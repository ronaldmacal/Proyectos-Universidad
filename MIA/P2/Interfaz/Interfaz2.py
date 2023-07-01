import tkinter as tk
import Login as login
import sys
import Encrypt as encrypt
sys.path.append("..")
import main
from tkinter import filedialog
'''***************************************************************************************************************
################################################   Crear Ventana  ################################################
***************************************************************************************************************'''
type_variable = True
# encrypt_log_variable = "false"
# encrypt_read_variable = "false"
# llave_variable = "miaproyecto12345"

def main_window():
    

    window = tk.Tk()

    window.title("Archivos")
    window.geometry("1000x500")


    label = tk.Label(window, text="Consola")
    label.place(x=20, y=50)

    window.configure(bg='yellow')
    label.configure(bg='yellow')

    text_area = tk.Text(window, height=9.5, width=90)
    text_area.place(x=30, y=80)

    text_area2 = tk.Text(window, height=9.5, width=90, state='disabled')
    text_area2.place(x=30, y=280)

    def on_key_pressed(event):
        commands = text_area.get("1.0", "end-1c")
        commands = commands.split("\n")
        # print(commands)
        run_commands(commands)
        append_new_line()
        
    def append_new_line():
        text = text_area.get("1.0", "end-1c")
        # new_line = ""
        updated_text = text + "\n"
        text_area2.config(state=tk.NORMAL)
        text_area2.delete("1.0", tk.END)
        text_area2.insert(tk.END, updated_text)
        text_area2.config(state=tk.DISABLED)





    text_area.bind("<KP_Enter>", on_key_pressed)
    


    # def on_key_pressed(event):
    #     if event.keysym == 'Return' or event.keysym == 'KP_Enter':  # Check for Enter/Return key
    #         input_text = text_area.get("1.0", "end-1c")  # Retrieve the text from the text box
    #         print(input_text)



    '''***************************************************************************************************************
    ###########################################   Metodos para cada botón  ###########################################
    ***************************************************************************************************************'''

    def execute(text_field):
        choose_file()
    def metodo_transfer():
        pass
    def metodo_create():    
        pass
    def metodo_rename():
        pass
    def metodo_delete():
        pass
    def metodo_modify():
        pass
    def metodo_copy():
        pass
    def metodo_add():
        pass
    def metodo_backup():
        print("backup")
    def metodo_exec():
        pass
    def metodo_cerrar_sesion():
        window.destroy()
        login.main_window()
        

    '''***************************************************************************************************************
    ###########################################        Crear Botones       ###########################################
    ***************************************************************************************************************'''
    path = tk.StringVar()
    # fild text
    text_field = tk.Entry(window, width=90)
    text_field.place(x=30, y=30)
    text_field.configure(bg='white', textvariable=path)

    button_configure = tk.Button(window, text="Ejecutar", width=10, height=2, command=lambda: execute(text_field))
    # button_transfer = tk.Button(window, text="Transfer", width=10, height=2, command=metodo_transfer)
    # button_create = tk.Button(window, text="Create", width=10, height=2, command=metodo_create)
    # button_rename = tk.Button(window, text="Rename", width=10, height=2, command=metodo_rename)
    # button_delete = tk.Button(window, text="Delete", width=10, height=2, command=metodo_delete)
    # button_modify = tk.Button(window, text="Modify", width=10, height=2, command=metodo_modify)
    # button_copy = tk.Button(window, text="Copy", width=10, height=2, command=metodo_copy)
    # button_add = tk.Button(window, text="Add", width=10, height=2, command=metodo_add)
    # button_backup = tk.Button(window, text="Backup", width=10, height=2, command=metodo_backup)
    # button_exec = tk.Button(window, text="Exec", width=10, height=2, command=metodo_exec)
    # button_cerrar_sesion = tk.Button(window, text="Cerrar Sesion", width=10, height=2, command=metodo_cerrar_sesion)

    button_configure.place(x=710, y=24)
    # button_transfer.place(x=850, y=30)
    # button_create.place(x=710, y=100)
    # button_rename.place(x=850, y=100)
    # button_delete.place(x=710, y=170)
    # button_modify.place(x=850, y=170)
    # button_copy.place(x=710, y=240)
    # button_add.place(x=850, y=240)
    # button_backup.place(x=710, y=310)
    # button_exec.place(x=850, y=310)
    # button_cerrar_sesion.place(x=710, y=380)

    
    window.mainloop()

def choose_file():
    
    file_path = filedialog.askopenfilename()
    print("Selected file:", file_path)
    exec_command(file_path)


def exec_command(path):
    commands_file = open(path, "r")
    commands = commands_file.read()
    commands_file.close()
    commands = commands.split("\n")
    run_commands(commands)

def run_commands(_commands):
    global type_variable
    commands = _commands

    for command in commands:
        # main.analizador_lexico(command)
        print(command)


main_window()