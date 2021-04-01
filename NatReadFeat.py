import pyautogui as pag
from pynput import keyboard
import time
notReact = False
playing = False


def on_press(key):
    global notReact
    global playing
    print(notReact)
    if not notReact:
        notReact = True
        if key == keyboard.Key.space:
            if pag.pixel(883, 59) == (227, 227, 227):
                pag.click(882, 60)
                playing = False
            else:
                pag.click(831, 61)
                playing = True
            pag.hotkey("shift", "space")
            notReact = False
    else:
        time.sleep(0.5)
        notReact = False
        

def on_release(key):
    if key == keyboard.Key.esc: 
        # Stop listener
        listener.stop()
        return False

with keyboard.Listener(on_press=on_press, on_release=on_release) as listener:
    listener.join() 