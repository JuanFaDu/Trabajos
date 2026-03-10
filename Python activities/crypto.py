from Crypto.Cipher import DES
import os
import base64
mensajeOriginal = "Estamos en Dos Hermanas".encode("utf-8")
print ("Mensaje original:", mensajeOriginal.decode("utf-8"))

key = b"abc123.."

iv = os.urandom(8)

print (iv)

cipher = DES.new(key, DES.MODE_OFB, iv=iv)

bytesCifrados = cipher.encrypt(mensajeOriginal)

print ("Bytes cifrados:", bytesCifrados)

mensajeCifrado = base64.b64encode(bytesCifrados).decode("utf-8")

print("Mensaje Cifrado:", mensajeCifrado)

