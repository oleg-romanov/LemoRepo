import psycopg2

class Connection:
    def __init__(self, host_ip, db_name, username, password):
        self.host_ip = host_ip
        self.password = password
        self.db_name = db_name
        self.username = username
        self.password = password
        self.conn = psycopg2.connect(dbname=self.db_name, user=self.username, password=self.password, host=self.host_ip)
        self.conn.autocommit = True

    def table_creation(self):
        cursor = self.conn.cursor()

        cursor.execute(f'CREATE TABLE IF NOT EXISTS Users('
                       f'Login VARCHAR(20) NOT NULL,'
                       f'Password VARCHAR(20) NOT NULL,'
                       f'Name VARCHAR(20) NOT NULL,'
                       f'Surname VARCHAR(20) NOT NULL,'
                       f'CONSTRAINT Users_pkey PRIMARY KEY(Login)'
                       f')')
        print("База данных Users была создана")

        cursor.execute(f'CREATE TABLE IF NOT EXISTS History('
                       f'Login VARCHAR(20) NOT NULL,'
                       f'Time DATE NOT NULL,'
                       f'Result VARCHAR(20) NOT NULL,'
                       f'Shop VARCHAR(20) NOT NULL,'
                       f'CONSTRAINT Users_fkey FOREIGN KEY(Login)'
                       f'   REFERENCES Users(Login) MATCH SIMPLE'
                       f'   ON UPDATE CASCADE'
                       f'   ON DELETE CASCADE'
                       f')')
        print("База данных History была создана")
        cursor.close()

    def user_insert(self, login, password, name, surname):
        cursor = self.conn.cursor()

        cursor.execute(f'INSERT INTO Users VALUES('
                       f"'{login}', "
                       f"'{password}', "
                       f"'{name}', "
                       f"'{surname}')")
        print("User was added")
        cursor.close()

    def history_insert(self, login, time, result, shop):
        cursor = self.conn.cursor()

        cursor.execute(f'INSERT INTO History VALUES('
                       f"'{login}', "
                       f"'{time}', "
                       f"'{result}', "
                       f"'{shop}')")
        print("History of scanning was added")
        cursor.close()

    def get_history(self, login):
        cursor = self.conn.cursor()

        cursor.execute(f"SELECT "
                       f"Login,"
                       f"DATE(Time),"
                       f"Result,"
                       f"Shop"
                       f"   FROM History"
                       f"   WHERE login='{login}'")
        return [item for item in cursor]
