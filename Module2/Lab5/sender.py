import pymqi

gmo = pymqi.GMO()
gmo.Options = pymqi.CMQC.MQGMO_WAIT | pymqi.CMQC.MQGMO_FAIL_IF_QUIESCING
gmo.WaitInterval = 3000
qmgr = pymqi.QueueManager('QM1')
getq = pymqi.Queue(qmgr, 'SRV.Q')
putq = pymqi.Queue(qmgr, 'CL.Q')


def send_query(operation, data):
    send_data = [operation] + data + [None]
    for d in send_data:
        putq.put(d)


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


def printResult():
    try:
        data = []

        while True:
            msg = getq.get(gmo)
            if msg is None:
                break
            data.append(msg)
        print(data)

    except:
        return False


def main1():
    try:
        get_line_len(1)
        add_line('pink')
    except:
        pass


def main2():
    while (printResult()):
        pass


main1()
