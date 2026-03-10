from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes

key = get_random_bytes(32) 
MensajeOriginal = 'PEpito de colores ' # This is your data
print("Mensaje original:", MensajeOriginal)

# Convertir un string a un objeto de bytes codificado en UTF-8
data = MensajeOriginal.encode('utf-8')

# Encriptación
cipher_encrypt = AES.new(key, AES.MODE_CFB)
BytesEncriptados = cipher_encrypt.encrypt(data)

# Nuestros datos y vector de inicialización
iv = cipher_encrypt.iv
print("Mensaje encriptado:", BytesEncriptados)
print("Vector de inicialización:", iv)

# Desencriptación
cipher_decrypt = AES.new(key, AES.MODE_CFB, iv=iv)
BytesDesncriptados = cipher_decrypt.decrypt(BytesEncriptados)

# Conversión de bytes a string
MensajeDesencriptado = BytesDesncriptados.decode('utf-8')
print("Mensaje desencriptado:", MensajeDesencriptado)

# Probamos coincidencia original-encriptado-desencriptado
assert MensajeOriginal == MensajeDesencriptado, f'El mensaje original ({MensajeOriginal}) no coincide con el desencriptado ({MensajeDesencriptado})'
