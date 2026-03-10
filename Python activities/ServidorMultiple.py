import socket
import threading
import time

HOST = '127.0.0.1'
PORT = 2000

def manejar_cliente (conexion, dir):
    print("conexion establecida con el cliente con IP ", dir[0], " y puerto ", dir[1])
    conexion.sendall("hola".encode("utf-8"))
    data = conexion.recv(1024)
    while True:
        if not data:
            break
        print("mensaje recibido: ", data)
        if data == b'cerrar':
            break
        conexion.sendall("La conexion se cerrara en 30 segundos".encode("utf-8"))
        for i in range (30, 0, -1):
            time.sleep(1)
            conexion.sendall(f"Tiempo: {i}".encode('utf-8'))
        if i == 1:
            conexion.sendall("cerrar")
            break
    print("Cerrando conexion")
    conexion.close()

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    dir= ((HOST, PORT))
    s.bind(dir)
    s.listen()
    print("Escuchando por el puerto ", PORT)
    while True:
        conexion, dir = s.accept()
        cliente = threading.Thread(targer=manejar_cliente, args=(conexion, dir))