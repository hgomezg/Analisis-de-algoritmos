try:
    # Si usan Python2
    from Tkinter import *
except ImportError:
    # Si usan Python3
    from tkinter import *

def main():
    # Creacion y configuracion de root
    root = Tk()
    root.wm_title("Hola chavos")
    Grid.rowconfigure(root, 0, weight=1)
    Grid.columnconfigure(root, 0, weight=1)

    # Creacion y configuracion de frame
    frame = Frame(root)
    frame.grid(row=0, column=0, sticky=N+S+E+W)

    # Diccionario para acceder en el futuro a botones
    m = 4
    n = 4
    mis_botones = [[0 for x in range(n)] for y in range(m)]

    # Creamos cuadricula de mxn botones dentro del frame
    for ind_fila in range(m):
        Grid.rowconfigure(frame, ind_fila, weight=1)
        for ind_columna in range(n):
            Grid.columnconfigure(frame, ind_columna, weight=1)
            btn = Button(frame, state=DISABLED, bg = 'black') # Creacion del boton dentro del frame
            mis_botones[ind_fila][ind_columna] = btn
            btn.grid(row=ind_fila, column=ind_columna, sticky=N+S+E+W) # Agregandolo a la matriz/cuadricula

    # Adoquin especial
    mis_botones[2][1].configure(bg = 'white')
    # Colocando un tetris color miku
    mis_botones[2][0].configure(bg = '#10ea84') # <- Color hexadecimal
    mis_botones[3][0].configure(bg = '#10ea84')
    mis_botones[3][1].configure(bg = '#10ea84')

    root.mainloop()

if __name__ == "__main__":
    main()
