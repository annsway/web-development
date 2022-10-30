import os
import sqlite3

# From: https://goo.gl/YzypOI
def singleton(cls):
    instances = {}

    def getinstance():
        if cls not in instances:
            instances[cls] = cls()
        return instances[cls]

    return getinstance


class DatabaseDriver(object):
    """
    Database driver for the Venmo app.
    Handles with reading and writing data with the database.
    """

    def __init__(self):
        """
        Secures a connection with the database and stores it into the
        instance variable `conn`
        """
        self.conn = sqlite3.connect("venmo.db", check_same_thread=False)
        self.drop_user_table()
        self.create_user_table()

        self.drop_tx_table()
        self.create_tx_table()

    def drop_user_table(self):
        """
        Using SQL, drops user table
        """
        try:
            self.conn.execute(
                """
                DROP TABLE IF EXISTS user; 
                """
            )
        except Exception as e:
            print(e)

    def create_user_table(self):
        """
        Using SQL, creates a user table
        """
        try:
            self.conn.execute(
                """
                CREATE TABLE user (
                    id INTEGER PRIMARY KEY AUTOINCREMENT, 
                    name TEXT NOT NULL,
                    username TEXT NOT NULL,
                    balance INTEGER
                )
                """
            )
        except Exception as e:
            print(e)

    def create_tx_table(self):
        """
        Using SQL, creates a transaction table
        """
        try:
            self.conn.execute(
                """
                CREATE TABLE tx (
                    id INTEGER PRIMARY KEY AUTOINCREMENT, 
                    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    sender_id INTEGER SECONDARY KEY NOT NULL,
                    receiver_id INTEGER SECONDARY KEY NOT NULL,
                    amount INTEGER NOT NULL,
                    accepted BOOLEAN DEFAULT NULL, 
                    FOREIGN KEY (sender_id) REFERENCES user(id),
                    FOREIGN KEY (receiver_id) REFERENCES user(id)
                )
                """
            )
        except Exception as e:
            print(e)

    def drop_tx_table(self):
        """
        Using SQL, drops tx table
        """
        try:
            self.conn.execute(
                """
                DROP TABLE IF EXISTS tx; 
                """
            )
        except Exception as e:
            print(e)

    def get_all_users(self):
        """
        Using SQL, gets all users in the user table
        """
        cursor = self.conn.execute("SELECT * FROM user;")
        res = []
        for row in cursor: 
            res.append({
                "id":row[0],
                "name":row[1],
                "username":row[2]
            })
        return res
    
    def insert_user_table(self, name, username, balance):
        """
        Using SQL, adds a new user to the user table
        """
        cursor = self.conn.execute(
            """
            INSERT INTO user (name, username, balance)
            VALUES (?, ?, ?)
            """,
            (name, username, balance)
        )
        self.conn.commit()
        return cursor.lastrowid

    def get_user_by_id(self, user_id):
        """
        Using SQL, get a user by id with transactions
        """
        cursor_user = self.conn.execute(
            """
            SELECT * 
            FROM user
            WHERE id = ?;
            """,
            (user_id,)
        )
        cursor_tx_send = self.conn.execute(
            """
            SELECT *
            FROM tx
            WHERE sender_id = ?;
            """,
            (user_id,)
        )
        cursor_tx_rec = self.conn.execute(
            """
            SELECT *
            FROM tx
            WHERE receiver_id = ?;
            """,
            (user_id,)
        )
        transactions = []
        for row in cursor_tx_send:
            transactions.append({
                "id" : row[0], 
                "timestamp" : row[1],
                "sender_id" : row[2],
                "receiver_id" : row[3],
                "amount" : row[4],
                "accepted" : row[5]
            })
        for row in cursor_tx_rec:
            transactions.append({
                "id" : row[0], 
                "timestamp" : row[1],
                "sender_id" : row[2],
                "receiver_id" : row[3],
                "amount" : row[4],
                "accepted" : row[5]
            })
        for row in cursor_user:
            return {
                "id":row[0], 
                "name":row[1], 
                "username":row[2], 
                "balance":row[3],
                "transactions":transactions
                }
        return None

    def delete_user_by_id(self, user_id):
        """
        Using SQL, delete a user by id, including transactions 
        """
        self.conn.execute(
            """
            DELETE FROM user
            WHERE id = ?
            """,
            (user_id, )
        )
        self.conn.execute(
            """
            DELETE FROM tx
            WHERE sender_id = ?
            """,
            (user_id, )
        )
        self.conn.execute(
            """
            DELETE FROM tx
            WHERE receiver_id = ?
            """,
            (user_id, )
        )
        self.conn.commit()

    def update_balance_by_id(self, user_id, new_amount):
        """
        Using SQL, update balance for user 
        """
        self.conn.execute(
            """
            UPDATE user
            SET balance = ?
            WHERE id = ?
            """,
            (new_amount, user_id)
        )
        self.conn.commit()

    ##### Transactions --------------------------
    def insert_tx_table(self, sender_id, rec_id, amount, accepted):
        """
        Using SQL, create a transaction by sending or requesting money
        """
        cursor = self.conn.execute(
            """
            INSERT INTO tx (
                    sender_id,
                    receiver_id,
                    amount,
                    accepted)
            VALUES (?, ?, ?, ?)
            """,
            (sender_id, rec_id, amount, accepted)
        )
        self.conn.commit()
        return cursor.lastrowid

    def get_tx_by_id(self, tx_id):
        """
        Using SQL, get a transation by id
        """
        cursor = self.conn.execute(
            """
            SELECT * FROM tx
            WHERE id = ?
            """,
            (tx_id,)
        )
        for row in cursor:
            return ({
                "id":row[0],
                "timestamp":row[1],
                "sender_id":row[2],
                "receiver_id":row[3],
                "amount":row[4],
                "accepted":row[5]
            })
        return None

    def update_tx_by_id(self, tx_id, accepted):
        """
        Using SQL, update tx status 
        """
        self.conn.execute(
            """
            UPDATE tx
            SET accepted = ?
            WHERE id = ?
            """,
            (accepted, tx_id)
            )
        self.conn.commit()
        
# Only <=1 instance of the database driver
# exists within the app at all times
DatabaseDriver = singleton(DatabaseDriver)