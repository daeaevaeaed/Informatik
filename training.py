from ast import If
import math
from re import A
from signal import strsignal
from socket import if_nameindex
import string
from tempfile import tempdir
from threading import main_thread
from typing import final

from numpy import half
from pip import main


def swap_last_first(liste):
    save = liste[0]
    liste[0] = liste[-1]
    liste[-1] = save
    return liste


def swap_index_list(liste, index1, index2):
    if index1 > len(liste) or index2 > len(liste):
        raise Exception("Index out of range")
    save = liste[index1]
    liste[index1] = liste[index2]
    liste[index2] = save
    return liste

def check_str_palindrome(temp_str):
    for i in range(int(len(temp_str)/2)):
        if temp_str[i] != temp_str[-i-1]:
            return False
    return True

def check_str_sym(temp_str):
    half_index = math.floor(len(temp_str)/2)
    temp_str1 = temp_str[0:half_index]
    temp_str2 = temp_str[-half_index: ]
    if temp_str2 == temp_str1:
        return True
    else:
        return False

def word_count_sentence(str_sample):    return len(str_sample.split(" "))


def uppercase_half_str(str_sample):
    half_index = math.floor(len(str_sample)/2)
    if len(str_sample) % 2:
        temp_str1 = str_sample[0:half_index]
        temp_str2 = str_sample[-half_index-1: ]
    else:
        temp_str1 = str_sample[0:half_index]
        temp_str2 = str_sample[-half_index: ]
    
    final_str = temp_str1.upper() + temp_str2
    return final_str

def str_length_no_space(str):
    return len(str.strip(" "))


def every_last_first_letter_cap(str):
    words = str.split(" ")
    final_str = ""
    for i in words:
        if len(i) > 1:
            a = i[0].upper()
            b = i[-1].upper()
            in_between = i[1:-1]
            final_str += a + in_between + b + " "
        else:
            final_str +=i.upper() + " "
    return final_str


def check_int_str(temp_string):
    string_presence = False
    integer_presence = False
    for i in temp_string:
        if i.isalpha():
            string_presence = True
        if i.isdigit():
            integer_presence = True
        if integer_presence and string_presence:
            return True
        # print(integer_presence, string_presence)
    return False

def check_int_str_2(temp_string):
    if int in temp_string:
        return True
    return False


def character_counter(temp_string):
    temp_dict = {}
    for i in temp_string:
        try:
            temp_dict[i] += 1
        except:
            temp_dict[i] = 1
    return temp_dict

