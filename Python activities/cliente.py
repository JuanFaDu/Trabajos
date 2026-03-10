import socket
from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes

HOST = '127.0.0.1'
PORT = 2000

with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as s:
    dir = (HOST, PORT)
    while True:
        mensaje:  input("Introduzca una de las siguientes opciones: dia, mes, anho, hora, salir \n")
        #enviar

        sent = s.sendto(mensaje.encode(),dir)
        print("Mensaje enviado")

        # recibir

        print("esperando.....")
        datos , server = s.recvfrom(1024)
        print("datos recibidos")
        print(datos)