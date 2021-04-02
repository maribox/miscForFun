from PIL import Image
import os

# Program to scale every image back to scale

for file in os.listdir():
    if file[-3:] == "png":
        image = Image.open(file)
        new_image = image.resize((1280, 528))
        new_image.save(file)
    else:
        print(file[-3:])
