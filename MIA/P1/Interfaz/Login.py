from tkinter import Tk, Label, Entry, Button, messagebox
import tkinter as tk
from Encrypt import Encrypt
import Interfaz as interfaz

def login(username_entry,password_entry,window):
    encrypt = password_entry.get()
    encriptar = Encrypt()
    if validate(username_entry.get(), encriptar.encrypt_ecb_aes(encrypt)):
        messagebox.showinfo("Login", "Inicio de sesión correcto!")
        window.destroy()
        interfaz.main_window()
        new_window = tk.Toplevel(window)
    else:
        messagebox.showerror("Login", "Usuario o contraseña incorrecta. Por favor intentalo de nuevo.")
def main_window():

    window = Tk()
    window.title("Login")
    window.geometry("300x200")



    username_label = Label(window, text="Username:")
    username_label.pack()
    username_entry = Entry(window)
    username_entry.pack()

    password_label = Label(window, text="Password:")
    password_label.pack()
    password_entry = Entry(window, show="*")
    password_entry.pack()

    
    login_button = Button(window, text="Login", command=lambda:login(username_entry, password_entry, window))
    login_button.pack()
    window.mainloop()




def validate(username, password):
    file = open("miausuarios.txt", "r")
    content = file.read()
    file.close()

    content = content.split("\n")
    dic = {}
    for i in range(len(content) - 1):
        if i % 2 == 0:
            dic[content[i]] = content[i + 1]
    print(dic)
    if username in dic:
        if dic[username].upper() == password.upper():
            return True

    return False
    




