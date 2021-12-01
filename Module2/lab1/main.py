from xml.dom import minidom


class SubwayLine:
    def __init__(self, id, color):
        self.id = id
        self.color = color


class SubwayStation:
    def __init__(self, id, name, is_end, line):
        self.id = id
        self.name = name
        self.is_end = is_end
        self.line = line


class Subway:
    lines = []
    stations = []

    def __init__(self, file_name):
        self.parse_file(file_name)

    def parse_file(self, file_name):
        DOMTree = minidom.parse(file_name)
        collection = DOMTree.documentElement
        lines = collection.getElementsByTagName("line")
        for l in lines:
            id = l.getAttribute("id")
            color = l.getAttribute("color")
            self.lines.append(SubwayLine(id, color))
            stations = l.getElementsByTagName("station")
            for s in stations:
                s_id = s.getAttribute("id")
                name = s.getAttribute("name")
                is_end = s.getAttribute("is_end")
                self.stations.append(SubwayStation(s_id, name, is_end, id))

    def get_line(self, id):
        for line in self.lines:
            if line.id == id:
                return line
        raise ValueError("Invalid id")

    def get_station(self, id):
        for station in self.stations:
            if station.id == id:
                return station
        raise ValueError("Invalid id")

    def get_line_by_idx(self, idx):
        if idx > self.count_lines:
            raise ValueError("Invalid index")
        return self.lines[idx]

    def count_lines(self):
        return len(self.lines)

    def add_line(self, id, color):
        try:
            self.get_line(id)
        except:
            self.lines.append(SubwayLine(id, color))

    def add_station(self, id, name, is_end, line):
        try:
            self.get_line(line)
        except:
            raise ValueError("Invalid line id")

        try:
            self.get_station(id)
        except:
            self.stations.append(SubwayStation(id, name, is_end, line))
            return

        raise ValueError(f"Station with id {id} already exists")

    def delete_line(self, id):
        try:
            self.get_line(id)
        except:
            raise ValueError("Invalid id")
        self.lines = [line for line in self.lines if line.id != id]

    def save_to_file(self, file_name):
        root = minidom.Document()
        xml = root.createElement("map")
        root.appendChild(xml)

        for l in self.lines:
            line = root.createElement("line")
            line.setAttribute("id", l.id)
            line.setAttribute("color", l.color)
            for s in self.stations:
                if s.line == l.id:
                    station = root.createElement("station")
                    station.setAttribute("id", s.id)
                    station.setAttribute("name", s.name)
                    station.setAttribute("is_end", s.is_end)
                    line.appendChild(station)

            xml.appendChild(line)

        xml_str = root.toprettyxml(indent="\t")
        with open(file_name, "w") as f:
            f.write(xml_str)


def main():
    subway = Subway('subway.xml')
    subway.add_line('1', 'blue')
    subway.add_station('11', 'Demiivska', '0', '1')
    subway.add_station('12', 'Maidan Nezalejnosti', '0', '1')
    subway.add_station('13', 'Teremky', '1', '1')

    subway.add_line('2', 'green')
    subway.add_station('21', 'Klovska', '0', '2')
    subway.add_station('22', 'Zoloti Vorota', '0', '2')
    subway.add_station('23', 'Dryzhbi Narodiv', '0', '2')

    subway.delete_line('4')

    subway.save_to_file('res.xml')


if __name__ == '__main__':
    main()
