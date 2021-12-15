import mysql.connector
db = mysql.connector.connect(
    host="localhost",
    user="root",
    password="1234",
)
cursor = db.cursor()

cursor.execute('CREATE DATABASE IF NOT EXISTS subway')

try:
    db = mysql.connector.connect(
        user="root", password="1234", host="localhost", database="subway")
    cursor = db.cursor()
except Exception as e:
    print(e)
    exit()

cursor.execute(
    'CREATE TABLE IF NOT EXISTS line(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, color VARCHAR(127) NOT NULL)'
)
cursor.execute(
    'CREATE TABLE IF NOT EXISTS station(id INT NOT NULL AUTO_INCREMENT, line_id INT NOT NULL, name CHAR(30) NOT NULL, is_end BOOLEAN NOT NULL, PRIMARY KEY(id), FOREIGN KEY (line_id) REFERENCES line(id))'
)


def add_line(color):
    sql = f"INSERT INTO line (color) VALUES ('{color}')"
    try:
        cursor.execute(sql)
        db.commit()
        print(f"Line {color} added ")
        return True
    except Exception as e:
        print(e)
        print(f"Error when adding line {color}")
        db.rollback()
        return False


def get_lines():
    sql = "SELECT * FROM line LEFT JOIN station ON line.id = station.line_id"
    res = []
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        for row in results:
            res.append(row)
            print(row)
    except Exception as e:
        print(e)
        print("Error when fetching data")
    return res


def get_station_by_name(name):
    sql = f"SELECT * FROM station WHERE station.name = {name}"
    res = []
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        for row in results:
            res.append(row)
            print(row)
    except Exception as e:
        print(e)
        print("Error when fetching data")
    return res


def get_line_len(id):
    sql = f"SELECT * FROM station WHERE station.line_id = {id}"
    res = 0
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        for row in results:
            res += 1
            print(row)
    except Exception as e:
        print(e)
        print("Error when fetching data")
    return res


def get_stations(line_id):
    sql = f"SELECT * FROM station WHERE station.line_id = {line_id}"
    res = 0
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        for row in results:
            res.append(row)
            print(row)
    except Exception as e:
        print(e)
        print("Error when fetching data")
    return res


def delete_line(id):
    sql = f"DELETE FROM line WHERE id = {id}"
    try:
        cursor.execute(sql)
        db.commit()
        print(f"Line with id {id} successfully deleted")
        return True
    except Exception as e:
        print(e)
        print(f"Error when deleting line with id {id}")
        db.rollback()
        return False


def add_station(name, line_id, is_end):
    sql = f"INSERT INTO station (name, line_id, is_end) VALUES ('{name}', {line_id}, {is_end})"
    try:
        cursor.execute(sql)
        db.commit()
        print(f"Station {name} added ")
        return True
    except Exception as e:
        print(e)
        print(f"Error when adding station {name}")
        db.rollback()
        return False


def delete_station(id):
    sql = f"DELETE FROM station WHERE id = {id}"
    try:
        cursor.execute(sql)
        db.commit()
        print(f"Station with id {id} successfully deleted")
        return True
    except Exception as e:
        print(e)
        print(f"Error when deleting station with id {id}")
        db.rollback()
        return False
