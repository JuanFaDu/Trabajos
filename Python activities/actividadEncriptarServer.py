import socket
import threading
import pickle
from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes

HOST = ''
PORT = 2000
        

def manejar_cliente(conexion, addr):
    while True:
        # Recibir la solicitud desde el cliente
        data = conexion.recv(1024)
        dataTrad = data.decode('utf-8')

        # Verificar si el cliente desea cerrar la conexión
        if dataTrad.lower() == "cerrar":
            print(f"Cliente ({addr[0]}:{addr[1]}) ha solicitado cerrar la conexión.")
            break
        else:
            print("Mensaje: ", dataTrad)
            key = get_random_bytes(32)
            cipher_encrypt = AES.new(key, AES.MODE_CFB)
            iv = cipher_encrypt.iv
            BytesEncriptados = cipher_encrypt.encrypt(("Recibido tu mensaje: " + dataTrad).encode('utf-8'))
            print("Mensaje encriptado: ", BytesEncriptados)
            
            # Obtener información sobre un país y enviar la respuesta al cliente
            data2 = [key, iv, BytesEncriptados]
            data2_serialized = pickle.dumps(data2)

            conexion.sendall(data2_serialized)

    conexion.close()

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as servidor:
    servidor.bind((HOST, PORT))
    servidor.listen()
    print(f"Servidor a la escucha en el puerto {PORT}")

    while True:
        conexion, addr = servidor.accept()
        print(f"Conexión exitosa con el cliente. IP ({addr[0]}) Puerto ({addr[1]})")

        # Crear un hilo para manejar la conexión con el cliente actual
        cliente_thread = threading.Thread(target=manejar_cliente, args=(conexion, addr))
        cliente_thread.start()