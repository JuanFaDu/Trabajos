import socket
import ssl     

HOST ='localhost'  
PORT = 2000       

context = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)

context.load_cert_chain('new.pem', 'private.key')


with socket.socket(socket.AF_INET, socket.SOCK_STREAM, 0) as sock:
    sock.bind((HOST, PORT))
    sock.listen()
    print(f"Servidor arrancado en: {HOST},{PORT}")

    with context.wrap_socket(sock, server_side=True) as ssock:
        conn, addr = ssock.accept()
        print(f"Conexión exitosa con: {addr}")
        while True:
            data = conn.recv()
            print("Mensaje recivido: ", data.decode("utf-8")) 
            if (data.decode("utf-8").lower() == f"cerrar"):
                break

            data = input("Introduzca un mensaje para el cliente: ")
            conn.sendall(data.encode("utf-8"))