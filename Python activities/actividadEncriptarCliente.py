import socket
import pickle
from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes

HOST = '127.0.0.1'
PORT = 2000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as cliente:
    cliente.connect((HOST, PORT))
    print('Conectado con éxito al servidor')

    while True:
        # Solicitar al cliente la ciudad de la que desea conocer el tiempo
        pais = input("Introduzca un mensaje: ")
        cliente.sendall(pais.encode('utf-8'))

        # Verificar si el cliente desea cerrar la conexión
        if pais == "cerrar":
            break

        # Recibir y mostrar la respuesta del servidor
        data2_serialized = cliente.recv(1024)

        data2 = pickle.loads(data2_serialized)
        key, iv, mensajeRecibido = data2
        cipher_decrypt = AES.new(key, AES.MODE_CFB, iv=iv)
        mensajeDesencriptado = cipher_decrypt.decrypt(mensajeRecibido)
        mensajeTraducido = mensajeDesencriptado.decode('utf-8')
        print("Clave: ", key)
        print("Vector de inicializacion: ", iv)
        print("Mensaje encriptado: ", mensajeRecibido)
        print("Mensaje desencriptado: ", mensajeTraducido)