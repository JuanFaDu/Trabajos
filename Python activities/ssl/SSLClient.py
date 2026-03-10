import socket
import ssl

HOST = 'localhost'
PORT = 2000

context = ssl.SSLContext(ssl.PROTOCOL_TLS_CLIENT)
context.load_verify_locations('new.pem')

with socket.socket(socket.AF_INET, socket.SOCK_STREAM, 0) as sock:
    with context.wrap_socket(sock, server_hostname=HOST) as ssock:
        ssock.connect((HOST, PORT))
        print(ssock.version())
        while True:
            print("Conexión exitosa")
            data = input("Envíe un mensaje al servidor: ")
            ssock.sendall(data.encode("utf-8"))
            if (data.lower() == "cerrar"):
                break

            data = ssock.recv(1024)
            print("Mensaje recibido:", data.decode("utf-8"))
            