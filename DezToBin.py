from random import randint


def calc(value):
    string = ""
    while(True):
        if value == 0:
            break
        string =  str(value % 2)[0] + string
        value = (value - (value % 2))/ 2
    return "0b" + string


for i in range(100000):
    val = randint(0, 10000)
    print(val)
    if calc(val) != bin(val):
        print("Error", val, "calc", calc(val), "bin", bin(val))