import os
import sounddevice as sd
import numpy as np

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

maxloundness = 0
minloudness = 1

def print_sound(indata, outdata, frames, time, status):
    # volume_norm = np.linalg.norm(indata)*10
    loudness = valueBetween0and100(indata)
    if loudness < minloudness:
        minloudness = loudness
    if loudness > maxloundness:
        maxloundness = loudness
    print()
    #print ("|" * int(volume_norm))

with sd.Stream(callback = print_sound):
    sd.sleep(10000)

