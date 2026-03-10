import socket
import threading
import pickle
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Signature import DSS
from Crypto.PublicKey import RSA  
from Crypto.Hash import SHA256
from Crypto.PublicKey import DSA
from Crypto.Random import get_random_bytes

HOST = ''
PORT = 2000
        
def manejar_cliente(conexion, addr):
    mensajeDecodificado = ""
    while True:
        try:
            data2_serialized = conexion.recv(4096)
            if not data2_serialized:
                break
            data2 = pickle.loads(data2_serialized)
            keys_rsa_bytes, key_dsa_publica_bytes, firmaCli, hash_hex, BytesEncriptados = data2

            # Importar las claves RSA como objetos de clave RSA
            keys_rsaCli = RSA.import_key(keys_rsa_bytes)
            key_dsaCli = DSA.import_key(key_dsa_publica_bytes)

            hash_func = SHA256.new()
            hash_func.update(BytesEncriptados)

            cipher_decrypt = PKCS1_OAEP.new(keys_rsaCli)
            verifier = DSS.new(key_dsaCli, 'fips-186-3')
            verifier.verify(hash_func, firmaCli)
            print("La firma digital es válida. El mensaje proviene del emisor autenticado.")
            mensajeDescifrado = cipher_decrypt.decrypt(BytesEncriptados)
            mensajeDecodificado = mensajeDescifrado.decode()
            print("Mensaje descifrado:", mensajeDecodificado)
        except ValueError:
            print("La firma digital no es válida. El mensaje podría haber sido alterado o no proviene del emisor autenticado.")

        if mensajeDecodificado.lower() == "cerrar":
            print(f"Cliente ({addr[0]}:{addr[1]}) ha solicitado cerrar la conexión.")
            break

        mensaje = input("Introduzca un mensaje de vuelta: ")
        conexion.sendall(mensaje.encode('utf-8'))

    conexion.close()

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as servidor:
    servidor.bind((HOST, PORT))
    servidor.listen()
    print(f"Servidor a la escucha en el puerto {PORT}")

    while True:
        conexion, addr = servidor.accept()
        print(f"Conexión exitosa con el cliente. IP ({addr[0]}) Puerto ({addr[1]})")

        cliente_thread = threading.Thread(target=manejar_cliente, args=(conexion, addr))
        cliente_thread.start()
