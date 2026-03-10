import socket
import threading
import requests
HOST = '127.0.0.1'
PORT = 2000

def obtener_informacion_pais(nombre_pais):
    url = f"https://api.openweathermap.org/data/2.5/weather?q={nombre_pais}&appid=0e8778fc0715b403da2279566870722d"
    response = requests.get(url)
    data = response.json()
    if response.status_code == 200 and data:
        return data
    else:
        return "error"

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen()
    print("Escuchando conexiones")
    conexion, dir = s.accept()
    print("Conexion exitosa con", dir)
    conexion.sendall("Hola cliente".encode("utf-8"))
    while True:
        dato = conexion.recv(1024)
        dato1 = dato.decode("utf-8")
        if dato1.lower() == "cerrar":
            break
        
        
        data = obtener_informacion_pais(dato1)
        description = data["weather"][0]["description"]
        tempMin = int(data["main"]["temp_min"]) - 273
        tempMax = int(data["main"]["temp_max"]) - 273
        humedad = data["main"]["humidity"]
        wind = data["wind"]["speed"]
        
        infoTotal = str("La descripcion del clima es " + str(description) + " ; las temperaturas maxima y minima son " + str(tempMax) + " y " + str(tempMin) + "; la humedad y el viento son " + str(humedad) + " y " + str(wind) + "")
        conexion.sendall(infoTotal.encode("utf-8"))
    conexion.close()