import time
import sys
x = None
if len(sys.argv) == 1:
    x = 10
elif len(sys.argv) == 2 and sys.argv[1].isnumeric():
    x = int(sys.argv[1])
else:
    while x is None:
        y = input("Type in the number of which you want to have the square root: ")
        if y.isnumeric():
            x = int(y)
            break

sizefactor = (input("Type in the sizefactor of your forms. (1 => 1 line for every unit.):"))
if not sizefactor.isnumeric():
    raise Exception("A factor is a number.")
sizefactor = int(sizefactor)

a = x
b = 1
print("Square root of", x, "is:")


def formprint(a, b, sizefactor):
    print((" " * int(b * sizefactor)) + str(round(b, 2)))
    for x in range(0, int(a* sizefactor)):
        #print(" | ", end = "")        
        print(" ", "XX" * int(b * sizefactor), end = "")
        if(x == int((a* sizefactor)/2)):
            print(" ", round(a, 2), end = "")
        #print(" | ")
        print("")
    print("\n")

    #print("---" + "-" * int(b * sizefactor) + "---")



while round(a, 5) != round(b, 5):
    a = (a+b)/2
    b = x/a
    #print(a, end = ("    nach " + str(n) + " Rechnungen\r"))
    formprint(a, b, sizefactor)
    time.sleep(0.5)



