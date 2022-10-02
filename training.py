import timeit

mysetup=""""""

mycode = """
a = 10000
val = 0
for i in range(a):
    if i == 0:
        val = 1
        continue
    val = val * i
# print(val)
"""
print(timeit.timeit(setup = mysetup, stmt = mycode, number = 1000))




