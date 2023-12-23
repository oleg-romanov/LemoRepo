import numpy as np
from keras.preprocessing import image
from keras.models import load_model
import requests
from io import BytesIO

def predict_image_quality(model_url, image_path, target_size=(255, 255)):
    try:
        # Загрузка модели из файла .h5
        response = requests.get(model_url)
        response.raise_for_status()
        model = load_model(BytesIO(response.content))

        # Загрузка и предобработка изображения для предсказания
        img = image.load_img(image_path, target_size=target_size)
        img_array = image.img_to_array(img)
        img_array = np.expand_dims(img_array, axis=0)
        img_array /= 255.0  # Предобработка изображения, если требуется

        # Предсказание класса
        prediction = model.predict(img_array)[0]
        predicted_class = np.argmax(prediction)

        # Вывод результата
        labels = {0: 'bad_quality', 1: 'good_quality', 2: 'green'}
        predicted_quality = labels[predicted_class]

        print(f"The predicted quality for the input image is: {predicted_quality}")

    except Exception as e:
        print(f"Ошибка при выполнении предсказания: {e}")

# Пример использования функции с Google Drive
model_url = 'https://github.com/oleg-romanov/LemoRepo/blob/main/Back_layer/AI/lemon_quality_dataset.h5'
image_path = input("Введите путь к изображению: ")

predict_image_quality(model_url, image_path)
