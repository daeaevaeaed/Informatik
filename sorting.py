

from random import randint


def sorting(list_to_sort):
    index = 0
    final = []
    print("List_to_sort", list_to_sort)
    while(len(list_to_sort) > 0):
        high_val = list_to_sort[0]
        index = 0
        while(index < len(list_to_sort)):
            if high_val > list_to_sort[index]:
                high_val = list_to_sort[index]
            # print(high_val, "highval", index, "Index")
            index += 1
        list_to_sort.remove(high_val)
        final.append(high_val)
    print("List is final", final)
list_to_sort = []
while(len(list_to_sort) < randint(10000, 100000)):
    list_to_sort.append(randint(0, 100))
print(list_to_sort, "this is the to be sorted list")
sorting(list_to_sort)