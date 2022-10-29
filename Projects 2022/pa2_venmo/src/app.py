import json
from flask import Flask, request
import db

DB = db.DatabaseDriver()

app = Flask(__name__)

# generalized response formats
def success_responses(body, code=200):
    return json.dumps(body), code

def failure_responses(message, code=404):
    return json.dumps({"error":message}), code

@app.route("/")

# your routes here
@app.route("/users/")
def get_users():
    """Endpoint for getting all users"""
    return success_responses({"users":DB.get_all_users()})

@app.route("/users/", methods=["POST"])
def create_user():
    """Endpoint for creating a new user"""
    body = json.loads(request.data)
    name = body.get("name")
    username = body.get("username")
    balance = body.get("balance")

    user_id = DB.insert_user_table(name, username, balance)
    user = DB.get_user_by_id(user_id)

    if user is None:
        return failure_responses("Count not create user. ", 400)
    return success_responses(user, 201)

@app.route("/users/<int:user_id>/")
def get_user_by_id(user_id):
    """Endpoint for getting a specific user"""
    user = DB.get_user_by_id(user_id)
    if user is None:
        return failure_responses("Cannot find user! ", 404)
    return success_responses(user, 200)

@app.route("/users/<int:user_id>/", methods=["DELETE"])
def delete_user_by_id(user_id):
    """Endpoint for deleting a specific user"""
    user = DB.get_user_by_id(user_id)
    if user is None:
        return failure_responses("Cannot find user! ", 404)
    DB.delete_user_by_id(user_id)
    return success_responses(user, 200)

@app.route("/send/", methods=["POST"])
def send_money():
    """Endpoint for sending money from one user to another by id"""
    body = json.loads(request.data)
    sender_id = body.get("sender_id")
    receiver_id = body.get("receiver_id")
    amount = body.get("amount")
    # Check if input is valid
    sender = DB.get_user_by_id(sender_id)
    receiver = DB.get_user_by_id(receiver_id)
    if (sender is None or receiver is None or amount is None):
        return failure_responses("Invalid input!", 400)
    # Note: a sender cannot overdraw his balance. 
    if (sender["balance"] < amount):
        return failure_responses("Sorry, you don't have enough balance. ", 400)
    # Calculate new balances
    new_amount_sender = sender["balance"] - amount
    new_amount_receiver = receiver["balance"] + amount
    # Update both users 
    DB.update_balance_by_id(sender_id, new_amount_sender)
    DB.update_balance_by_id(receiver_id, new_amount_receiver)
    return success_responses(body)

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8000, debug=True)
