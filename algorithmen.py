def GGT_euklied(val1, val2):
    while(val2 > 0 and val1 > 0):
        print("Val1", val1, "Val2", val2)
        if val2 == val1:
            return val1
        if val1 > val2:
            val1 -= val2
        if val2 > val1:
            val2 -= val1

def GGT_mod(a, b):
    if(b == 0):
        return a
    else:
        while(b > 0):
            h = a % b
            a = b
            b = h
            print(a, b, h)
    return a

print(GGT_euklied(34567890987654, 987678765456789987))
print(GGT_mod(34567890987654, 987678765456789987))



