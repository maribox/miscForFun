import pyautogui as pag
from pynput import keyboard
import time
notReact = False

def on_press(key):
    global notReact
    print(notReact)

    if not notReact:
        notReact = True
        if key == keyboard.Key.space:
            pag.click(882, 60)
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