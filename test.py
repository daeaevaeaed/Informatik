from random import randint
from time import time
import timeit
from datetime import datetime


def gen_random_list():
    list_example = []
    while(len(list_example) < 6):
        list_example.append(randint(0, 9))
    return list_example


def sorting_selection(list_to_sort):
    # print(list_to_sort)
    index = 0
    final = []
    temp_list_later = []
    temp_list_later = list_to_sort.copy()
    # print("List_to_sort", list_to_sort)
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
    # print("List is final", final)
    # print(list_to_sort, "vorher")
    list_to_sort = temp_list_later
    # print(list_to_sort, "nachher")
    return final, list_to_sort


def searching_insertion(list_to_sort):
    list_temp = list_to_sort.copy()
    print(list_temp)
    index_sorted = 1
    for i in range(len(list_temp)):
        for o in range(i):
            if list_temp[i] < list_temp[o]:
                swap(list_temp, i, o)
    return list_temp, list_to_sort
        
def swap(list, index1, index2):
    if index1 > len(list) or index2 > len(list):
        raise Exception("index out of range")
    val1 = list[index1]
    list[index1] = list[index2]
    list[index2] = val1
    return list


def bubble_rise(list_to_sort):
    list_temp = list_to_sort
    # print(list_temp)
    for i in range(len(list_temp)):
        # print("i", i)
        for o in range((len(list_temp) - i)):
            # print("o", o)
            # print(swapped)
            if i > 0:
                    if list_not_in_order(list_temp):
                        if list_temp[o] > list_temp[o + 1]:
                            print("swap")
                            swap(list_temp, o, o + 1)
                            swapped = 1
                        print(list_temp)


    return list_temp

def list_not_in_order(list):
    counter = 0
    for i in range(len(list)-1):
        
        if list[i] < list[ i + 1]:
            counter +=1
    print(counter)
    if counter == len(list):
        return False
    else:
        return True

# [2,434,6574,43,6,5,78,53,45,3,745,567,5,45,2,7]
# print(bubble_rise([1,2,3,4,5,7,6,8,9,10,12,11, 13, 89, 13,25,64,23]))

print(list_not_in_order([1,2,3,4,6]))