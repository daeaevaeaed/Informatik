from random import randint


def calc(value):
    string = ""
    if value == 0:
        string = "0"

    while(value > 0):
        string =  str(value % 2)[0] + string
        value = (value - (value % 2))/ 2
    return "0b" + string


for i in range(100000):
    val = i
    print(val)
    if calc(val) != bin(val):
        print("Error", val, "calc", calc(val), "bin", bin(val))
print("Done")