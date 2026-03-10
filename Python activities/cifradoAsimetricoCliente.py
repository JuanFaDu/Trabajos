import socket
import pickle
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Signature import DSS
from Crypto.PublicKey import RSA  
from Crypto.Hash import SHA256
from Crypto.PublicKey import DSA
from Crypto.Cipher import PKCS1_OAEP
from Crypto.Random import get_random_bytes

HOST = '127.0.0.1'
PORT = 2000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as cliente:
    cliente.connect((HOST, PORT))
    print('Conectado con éxito al servidor')

    while True:
        mensaje = input("Introduzca un mensaje: ")
        mensajeCodificado = mensaje.encode('utf-8')

        keys_rsa = RSA.generate(2048)
        key_rsa_publica = keys_rsa.public_key()

        keys_dsa = DSA.generate(2048)
        key_dsa_publica = keys_dsa.public_key()

        cipher_encrypt = PKCS1_OAEP.new(key_rsa_publica)

        BytesEncriptados = cipher_encrypt.encrypt(mensajeCodificado)
        print("Mensaje encriptado: ", BytesEncriptados)

        hash_func = SHA256.new(BytesEncriptados)

        signer = DSS.new(keys_dsa, 'fips-186-3')
        firma = signer.sign(hash_func)
        print("Firma digital: ", firma) 

        keys_rsa_str = keys_rsa.export_key().decode('ascii')
        key_dsa_publica_str = key_dsa_publica.export_key().decode('ascii')

        hash_hex = hash_func.hexdigest()

        # Modificar el código del servidor para exportar las claves RSA como bytes
        keys_rsa_bytes = keys_rsa.export_key()
        key_dsa_publica_bytes = key_dsa_publica.export_key()

        # Enviar las claves RSA como bytes al cliente
        data2 = [keys_rsa_bytes, key_dsa_publica_bytes, firma, hash_func.digest(), BytesEncriptados]
        data2_serialized = pickle.dumps(data2)
        cliente.sendall(data2_serialized)

        if mensaje == "cerrar":
            break

        mensaje = cliente.recv(1024)

        
        print("Ha llegado un mensaje del servidor: ", mensaje.decode())
