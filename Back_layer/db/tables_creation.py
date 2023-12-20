import psycopg2

class Creator:
    def __init__(self, host_ip, db_name, username, password):
        self.host_ip = host_ip
        self.password = password
        self.db_name = db_name
        self.username = username
        self.password = password
        self.conn = psycopg2.connect(dbname=self.db_name, user=self.username, password=self.password, host=self.host_ip)

    def table_creation(self):
            cursor = self.conn.cursor()
            self.conn.autocommit = True
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