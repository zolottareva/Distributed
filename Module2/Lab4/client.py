import Pyro4

ns = Pyro4.locateNS()
uri = ns.lookup('controller')
o = Pyro4.Proxy(uri)

o.add_line('orange')
