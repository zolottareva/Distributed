import socket
from time import sleep
s = socket.socket()
host = socket.gethostname()
port = 12345
s.connect((host, port))


def send_query(operation, data):
    send_data = [operation] + data
    for d in send_data:
        s.send(bytes(str(d), 'utf-8'))
        sleep(0.001)
    return str(s.recv(1024))


def add_line(color):
    return send_query('add_line', [color])


def delete_line(line_id):
    return send_query('delete_line', [line_id])


def add_station(name, line_id, is_end):
    return send_query('add_station', [name, line_id, is_end])


def delete_station(station_id):
    return send_query('delete_station', [station_id])


def get_station_by_name(name):
    return send_query('get_station_by_name', [name])


def get_line_len(line_id):
    return send_query('get_line_len', [line_id])


def get_stations(line_id):
    return send_query('get_stations', [line_id])


def get_lines():
    return send_query('get_stations', [])


if __name__ == '__main__':
    add_line('purple')