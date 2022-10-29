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
        Using SQL, get a user by id
        """
        cursor = self.conn.execute(
            """
            SELECT * FROM user
            WHERE id = ?;
            """,
            (user_id,)
        )
        for row in cursor:
            return {"id":row[0], "name":row[1], "username":row[2], "balance":row[3]}
        return None

    def delete_user_by_id(self, user_id):
        """
        Using SQL, delete a user by id
        """
        self.conn.execute(
            """
            DELETE FROM user
            WHERE id = ?
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

# Only <=1 instance of the database driver
# exists within the app at all times
DatabaseDriver = singleton(DatabaseDriver)