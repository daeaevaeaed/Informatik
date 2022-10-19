

from random import randint


def oscilation_upper_case(temp_string):
    final_str = ""
    for i in range(len(temp_string)):
        if randint(0, 2) == 2:
            letter = str(temp_string[i]).upper()
            print(letter)
            final_str += letter
        else:
            final_str += temp_string[i]
    return final_str



print(oscilation_upper_case("qwertzuiop"))
# print("hallo".upper())

