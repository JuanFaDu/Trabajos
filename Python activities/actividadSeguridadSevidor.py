import socket
import threading
import requests
import logging

HOST = ''
PORT = 2000
logging.basicConfig(filename='example.log', encoding='utf-8', level=logging.DEBUG)

def obtener_informacion_pais(nombre_pais):
    url = f"https://restcountries.com/v2/name/{nombre_pais}"
    try:
        response = requests.get(url)
        data = response.json()

        if response.status_code == 200 and data:
            # Extraer información relevante sobre el país
            pais = data[0]
            nombre = pais.get('name')
            capital = pais.get('capital')
            poblacion = pais.get('population')

            return f"Información sobre {nombre}:\nCapital: {capital}\nPoblación: {poblacion}"
        else:
            return "No se pudo obtener información para ese país."
    except requests.exceptions.RequestException as e:
        return f"No se pudo obtener información para ese país. Error: {e}"

def manejar_cliente(conexion, addr):
    while True:
        # Recibir la solicitud desde el cliente
        solicitud = conexion.recv(1024).decode('utf-8')

        # Verificar si el cliente desea cerrar la conexión
        if solicitud.lower() == "cerrar":
            print(f"Cliente ({addr[0]}:{addr[1]}) ha solicitado cerrar la conexión.")
            break
        
        if not solicitud.strip():
            info_pais = "Introduzca un nombre de país, por favor"
            logging.warning("El usuario no introdujo un país")
        # Obtener información sobre un país y enviar la respuesta al cliente
        else:
            info_pais = obtener_informacion_pais(solicitud.strip())
            logging.info(f"El usuario introdujo el país: {solicitud}")
            logging.info(f"Enviando la siguiente información al usuario: {info_pais}")
            
        conexion.sendall(info_pais.encode('utf-8'))

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