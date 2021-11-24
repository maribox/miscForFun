import os
from PIL import Image

# Execute this file with the pictures to create a batch file that can be executed that rescales the images

subfolder = "Example" # Leave empty if executed in same folder as images
im = Image.open(subfolder + "/" + os.listdir(subfolder + "/")[0])
width, height = im.size
print(width, height)
imgcount = len(os.listdir(subfolder + "/"))
print(imgcount)


with open(subfolder + "/" + "step1.bat", "w+") as mybat:
    for file in os.listdir(subfolder + "/"):
        if file[-3:] == "png": # If using another Imagetype, write here.
            width, height = width * 0.998, height * 0.998
            mybat.write('"C:\Program Files\GIMP 2\\bin\gimp-console-2.10.exe" -i -f -b "(batch-gimp-lqr \\"%s\\" %s %s \\"Backgound\\" \\"\\" \\"disc. mask\\")"  -b "(gimp-quit 0)     \n' % (file, int(width), int(height)))
 
