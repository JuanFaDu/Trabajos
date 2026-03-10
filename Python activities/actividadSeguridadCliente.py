import socket
import logging

HOST = '127.0.0.1'
PORT = 2000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as cliente:
    try:
        cliente.connect((HOST, PORT))
        print('Conectado con éxito al servidor')

        while True:
            # Solicitar al cliente la ciudad de la que desea conocer el tiempo
            pais = input("¿Que país quiere consultar? (escribe 'cerrar' para salir): ")
            cliente.sendall(pais.encode('utf-8'))
            logging.info(pais)
            # Verificar si el cliente desea cerrar la conexión
            if pais == "cerrar":
                break

            # Recibir y mostrar la respuesta del servidor
            respuesta = cliente.recv(1024).decode('utf-8')
            if not respuesta.strip():
                print("No se ha recibido nada")
                logging.warning("El servidor no ha devuelvo datos")
            else:
                print(respuesta)
                logging.info(respuesta)
    except ConnectionRefusedError:
        print("Error: No se pudo conectar al servidor. Asegúrate de que el servidor esté en ejecución.")
    except Exception as e:
        print(f"Error inesperado: {e}")
