import tempfile
import base64
from flask import Flask, jsonify, request
from Back_layer.db.DB_actions import Connection
from Back_layer.db.clearback import remove_bg
from Back_layer.db.AI_process import predict_image_quality

app = Flask(__name__)
connection = Connection("127.0.0.1", "postgres", "postgres", "`123qwe")
connection.table_creation()
model_url = 'E:\\LemoRepo\\lemon_quality_with_green_new_dataset12121.h5'

@app.route("/get_history", methods=['POST'])
def get_history():
    login = request.json["Login"]
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

@app.route("/auth", methods=['POST'])
def authentication():
    login = request.json["Login"]
    password = request.json["Password"]

    data = connection.check_auth(login)
    if not data:
        return "No such client", 404
    else:
        if data[1] != password:
            return "Bad password", 501
        else:
            return jsonify({"Login" : data[0], "Name" : data[2], "Surname" : data[3]}), 200


@app.route('/result', methods=['POST'])
def AI_scanning():
    login = request.json["Login"]
    date = request.json["Date"]
    image = request.json["Image"]
    shop = request.json["Shop"]
    data = base64.b64decode(image)

    with tempfile.TemporaryDirectory() as tmpdirname:
        print('created temporary directory', tmpdirname)
        with open(f"{tmpdirname}\\in.jpg", 'wb') as file:
            file.write(data)
        result = predict_image_quality(model_url, remove_bg(f"{tmpdirname}\\in.jpg"))
        connection.history_insert(login, date, result, shop)

        return result

if __name__ == "__main__":
    app.run(host='127.0.0.1', port=5050)