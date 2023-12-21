from flask import Flask, jsonify, request

from Back_layer.db.DB_actions import Connection

app = Flask(__name__)
connection = Connection("127.0.0.1", "postgres", "postgres", "`123qwe")
connection.table_creation()

@app.route("/get/<login>", methods=['GET'])
def get_history(login):
    history = []
    for items in connection.get_history(login):
        history.append(
            {
                "Login" : items[0],
                "Date" : items[1],
                "Result" : items[2],
                "Shop" : items[3]
            }
        )

    return jsonify(history)

@app.route("/")
def greetings():
    return "Hello World"

@app.route("/add_user", methods=['POST'])
def add_user():
    login = request.json["Login"]
    password = request.json["Password"]
    name = request.json["Name"]
    surname = request.json["Surname"]

    connection.user_insert(login, password, name, surname)
    return  jsonify(status = 200)



if __name__ == "__main__":
    app.run(host='127.0.0.1', port=5050)