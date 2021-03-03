import os
import sounddevice as sd
import numpy as np



maxloudness = 0
maxdistance = 0
minloudness = 1
mindistance = 0

def valueBetween0and100(nparray):
    value = 0
    number = 0
    for line in nparray:
        for element in line:
            value += element
            number += 1
    loudness = value/number
    if loudness < 0:
        loudness *= -1
    return loudness * 100




loudnesslist = []

"""
def initialize(indata, outdata, frames, time, status):
    global loudnesslist
    loudness = valueBetween0and100(indata)
    loudnesslist.append(loudness)

print("Please clap a few times and make some noise...")
def min(list):
    mini = list[0]
    for element in list:
        if element < mini:
            mini = element
    return  mini


with sd.Stream(callback = initialize):
    sd.sleep(5000)
loudnesslist = loudnesslist[20:]
maxima = []
for loudness in loudnesslist:
    if len(maxima) == 0 or loudness > min(maxima) or len(maxima) < 10:
        maxima.append(loudness)
        if len(maxima) > 30:
            maxima.remove(min(maxima))

maxValue = sum(maxima)/len(maxima)
"""

try:
    size = os.get_terminal_size(1)
except:
    try:
        size = os.get_terminal_size(0)
    except Exception as e:
        print(e, "That didn't work. Choosing Default-Value of 145.")
        size = [145]
#  If none of both work, don't cry.
print(size[0])
size = size[0]
prelength = 0
def print_sound(indata, outdata, frames, time, status):
    #global maxValue
    global size
    #length =  int(float(loudness)/float(maxValue)*size)
    try:
        size = os.get_terminal_size(1)[0]
    except:
        try:
            size = os.get_terminal_size(0)[0]
        except Exception as e:
            print(e, "That didn't work. Choosing Default-Value of 145.")
            size = 145
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
    #print ("|" * int(volume_norm))


with sd.Stream(callback = print_sound):
    sd.sleep(100000)


