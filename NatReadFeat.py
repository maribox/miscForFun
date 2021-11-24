import pyautogui as pag
from pynput import keyboard
from pynput.keyboard import Controller
import time
import keyboard as k

notReact = False


def on_press(key):
    print( k.is_pressed("alt gr")) 
    if key == keyboard.Key.alt_gr:
        while k.is_pressed("alt gr"):
            print(k.is_pressed("alt gr"))
            time.sleep(0.5)
        print("Druck")
        pag.keyDown("ctrl")
        pag.keyDown("shift")
        pag.press("r")

        pag.keyUp("shift")
        pag.keyUp("ctrl")

def on_release(key):
    if key == keyboard.Key.esc: 
        # Stop listener
        listener.stop()
        return False

with keyboard.Listener(on_press=on_press, on_release=on_release) as listener:
    listener.join() 