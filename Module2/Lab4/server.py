import Pyro4
import sys
sys.path.append('../')
from controller import Controller
Controller = Pyro4.expose(Controller)
daemon = Pyro4.Daemon()
uri = daemon.register(Controller)
ns = Pyro4.locateNS()
ns.register('controller', uri)
daemon.requestLoop()
