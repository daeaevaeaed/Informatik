import timeit


mysetup = ""
mycode = """

sleep(5000)
"""

print(timeit.timeit(setup = mysetup, stmt= mycode, number = 100))