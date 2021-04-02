def stabprint(*args):
    arglen = []
    for arg in args:
        arglen.append(len(str(arg)))
    leng = max(arglen)
    
    for arg in args:
        print(arg, end = ((leng-len(str(arg))+1)*" " + "| "))
        print("\u25a0"*arg)
  
    print("M" + leng * " " + "| ", end ="")
    print("❐"*(int(args[0]/args[1])*args[1]), end ="")
    print("▩"*int(args[0]%args[1]))
    print("---------------------------------")

def euclid(a, b):

    if b == 0:
        return a
    else:
        stabprint(a,b)
        return euclid(b, a%b)

print(euclid(150, 139))