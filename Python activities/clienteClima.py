import socket
HOST = '127.0.0.1'
PORT = 2000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    print('Esperando establecer la conexion')
    server = s.recv(1024) #Linea bloqueante
    while True:
        mensaje = input("Ingrese un mensaje: ")
        s.sendall(mensaje.encode('utf-8'))
        if mensaje == "cerrar":
            break
        server = s.recv(1024)
        print("Mensaje: ", server.decode("utf-8"))
    print("Cerrando conexión")
