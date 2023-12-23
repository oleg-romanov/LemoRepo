from rembg import remove
from PIL import Image
import os
import io

def remove_bg(file_path):
    if file_path.lower().endswith(('.png', '.jpg', '.jpeg')):
        print(f'[+] Удаляю фон: "{file_path}"...')
        output = remove(Image.open(file_path))
        resized_output = output.resize((225, 225))
        output_path = os.path.join(os.path.dirname(file_path), 'out.png')
        resized_output.save(output_path)

        print(f'[+] Фон удален и сохранен в новом файле: "{output_path}"')
        return output_path
