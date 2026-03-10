import socket
HOST = ''
PORT = 2000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    print('Esperando establecer la conexion')
    server = s.recv(1024) #Linea bloqueante
    print('Servidor: ', server.encode("utf-8"))
    while True:
        mensaje: input("Ingrese un mensaje: ")
        s.sendall(mensaje)
        if mensaje == b"cerrar":
            break
        server = s.recv(1024)
        print("Mensaje: ", server.encode("utf-8"))
    print("Cerrando conexión")
