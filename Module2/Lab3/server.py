import socket
import sys 
sys.path.append('../')
from controller import Controller

oper_to_args_num = {
    'add_line': 1,
    'delete_line': 1,
    'add_station': 3,
    'delete_station': 1,
    'get_station_by_name': 1,
    'get_line_len': 1,
    'get_stations': 1,
    'get_lines': 0
}

def start():
    global s
    
    c, addr = s.accept()
    while True:
        oper = c.recv(1024).decode()
        n_args = oper_to_args_num[oper]
        args = []
        for arg in range(n_args):
            c.recv(1024)
            args.append(arg)
        result = getattr(Controller, oper)(*args)
        c.send(bytes(str(result), 'utf-8'))

with socket.socket() as s:
    host = socket.gethostname()
    port = 12345
    s.bind((host, port))
    s.listen(5)
    start()