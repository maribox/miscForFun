import os
import sounddevice as sd
import numpy as np



try:
    size = os.get_terminal_size(1)
    term_num = 1
except:
    try:
        size = os.get_terminal_size(0)
        term_num = 0
    except Exception as e:
        print(e, "That didn't work. Choosing Default-Value of 145.")
        size = [145]
#  If none of both work, don't cry.
print(size[0])
size = size[0]
prelength = 0
def sound(indata, outdata, frames, frozenintime, status):
    global size
    global term_num
    size = os.get_terminal_size(term_num)[0]


    #  If none of both work, don't cry.
    # print(size)
        
    halfsize = int(size / 2)
    length = np.linalg.norm(indata)*size/10
    length = int(length)
    #print(length)
    if length > size:
        length = size
    global prelength

    
    while prelength > length:
        print(" " * (halfsize - prelength) + "\\" + (prelength*2-3) * " " + "/" + " " * (halfsize - prelength), end = "\n")
        prelength -=1
    while prelength < length:
        print(" " * (halfsize - prelength) + "/" + (prelength*2-3) * " " + "\\" + " " * (halfsize - prelength), end = "\n")
        prelength +=1
    print(" " * (halfsize - length) + "|" + (length*2-3) * " " + "|" + " " * (halfsize - length), end = "\n")

with sd.Stream(callback = sound):
    sd.sleep(10000000)

