import socket
HOST = ''
PORT = 2000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen()
    print("Escuchando conexiones")
    conexion, dir = s.accept()
    print("Conexion exitosa con", dir)
    conexion.sendall("Hola cliente".encode("utf-8"))
    while True:
        dato = conexion.recv(1024)

        if dato == "cerrar":
            break
        print("El mensaje recibido es: ", dato)
    conexion.close()