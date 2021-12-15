import pymqi

gmo = pymqi.GMO()
gmo.Options = pymqi.CMQC.MQGMO_WAIT | pymqi.CMQC.MQGMO_FAIL_IF_QUIESCING
gmo.WaitInterval = 3000
qmgr = pymqi.QueueManager('QM1')
putq = pymqi.Queue(qmgr, 'SRV.Q')
getq = pymqi.Queue(qmgr, 'CL.Q')


def processQuery():
    try:
        received_data = []
        while True:
            message = getq.get(gmo)
            received_data.append(message)
            if message is None:
                break
        for data in received_data:
            putq.put(data)
        return True
    except:
        return False


i = 0
while (processQuery()):
    i += 1
putq.close()
getq.close()
