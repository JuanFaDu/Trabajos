import socket
from datetime import datetime, time

HOST = '127.0.0.1'
PORT = 2000

with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as s:
    dir= (HOST, PORT)
    s.bind(dir)
    while True:
        #recibir
        datos, addre = s.recvfrom(1024)
        print('Recibidos {} bytes de {}'.format(len(datos), addre))
        print(datos)

        #enviar

        if datos:
            v = ''
            if datos == b'dia':
                fecha = datetime.now()
                v = 'El dia es:' + str(fecha.day)
                print("dia actual: ", v)
            elif datos == b'mes':
                fecha = datetime.now()
                v = 'El mes es: ' + str(fecha.month)
                print("mes actual: ", v)
            elif datos == b'anho':
                fecha = datetime.now()
                v = 'El anho es:' + str(fecha.year)
                print("anho actual: ", v)
            elif datos == b'hora':
                hora = datetime.now()
                hora_str = hora.strftime("%H:%M:%S")
                v = ("La hora actual es: " + hora_str)
            elif datos  == b'salir':
                v = "Cerrando servidor..."
                break
            mensaje = b'Mensaje recibido'
            sent = s.sendto(v.encode(), addre)
            print('mensaje enviado')
    s.close()