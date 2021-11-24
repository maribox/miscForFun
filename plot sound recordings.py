import sounddevice as sd
import numpy as np
import matplotlib.pyplot as plt
sd.default.samplerate = 1000 # minimal 1000 
sd.default.channels = 2 # stereo
duration = 3 # in seconds
rec = sd.rec(int(duration * sd.default.samplerate)) # returns nparray
sd.wait()
# np.set_printoptions(precision=3)
# print(rec[5])
leftChannel = []
rightChannel = []

for n in rec:
    leftChannel.append(n[0])
    rightChannel.append(n[1])

plt.plot(range(0,len(leftChannel)), leftChannel)
plt.plot(range(0,len(rightChannel)), rightChannel)

plt.show()

# sd.play(rec)
# sd.wait()