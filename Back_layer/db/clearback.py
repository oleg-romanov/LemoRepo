#Исходный код
import os
from rembg import remove
from PIL import Image

def remove_bg(file_path):
    if file_path.lower().endswith(('.png', '.jpg', '.jpeg')):
        print(f'[+] Удаляю фон: "{file_path}"...')
        output = remove(Image.open(file_path))
        
        output_path = os.path.join(os.path.dirname(file_path), 'out.png')
        output.save(output_path)
        
        print(f'[+] Фон удален и сохранен в новом файле: "{output_path}"')
    else:
        print(f'[-] Неверный формат файла. Поддерживаются только PNG, JPG и JPEG.')

def get_target_file():
    while not os.path.isfile(user_input := input("[+] Введите путь к изображению: ")):
        print(f'Файл "{user_input}" не найден\n')
    print(f"\nРаботаем с файлом {user_input}\n")
    return user_input

def main():
    remove_bg(get_target_file())
    print('\n[+] Удаление завершено!')

if __name__ == "__main__":
    main()

############################################################################################
#Возможно работающий код с POST-запросом 
from flask import Flask, request, send_file
from rembg import remove
from PIL import Image
import os
import io

app = Flask(__name__)

def process_image(image):
    try:
        # Удаляем фон
        output = remove(image)

        # Изменяем размер изображения до 225x225
        resized_output = output.resize((225, 225))

        # Создаем байтовый объект для сохранения изображения
        output_image_bytes = io.BytesIO()
        resized_output.save(output_image_bytes, format='PNG')

        # Возвращаем изображение в виде байтов в ответе
        return output_image_bytes.getvalue()
    except Exception as e:
        return str(e), 500

@app.route('/remove_bg', methods=['POST'])
def remove_background():
    file = request.files.get('image')

    if file and file.filename.lower().endswith(('.png', '.jpg', '.jpeg')):
        image = Image.open(file)
        processed_image = process_image(image)
        return send_file(io.BytesIO(processed_image), mimetype='image/png', as_attachment=True, download_name='output_image.png')
    else:
        return 'Неверный формат файла. Поддерживаются только PNG, JPG и JPEG.', 400

if __name__ == "__main__":
    app.run(debug=True)

#############################################################################################