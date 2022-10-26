import timeit


mysetup = ""
mycode = """def calc(value):
    string = ""
    while(value > 0):
        string =  str(value % 2)[0] + string
        value = (value - (value % 2))/ 2
    return string

    print(calc(900))
"""

print(timeit.timeit(setup = mysetup, stmt= mycode, number = 100000000))