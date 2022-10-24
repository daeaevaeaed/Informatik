import math as m


clear = "abcdefghijklmnopqrstuvwxyz"


def verschlüsseln_c(string, key, enable):
    if enable:
        key1 = int(key[0])
        key2 = int(key[1])
        key3 = int(key[2])
    else:
        key1 = -int(key[0])
        key2 = -int(key[1])
        key3 = -int(key[2])
    # print(key1, key2, key3, "KEYS")
    counter = 0
    final_str = ""
    for a in string:
        if a == " ":
            final_str += " "
            continue
        mod = counter % 3
        # print(mod)
        if mod == 0:
            if a.isupper():
                final_str += clear[(clear.index(a.lower()) + key1) % 26].upper()
            else:
                final_str += clear[(clear.index(a) + key1) % 26]
        if mod == 1:
            if a.isupper():
                final_str += clear[(clear.index(a.lower()) + key2) % 26].upper()
            else:
                final_str += clear[(clear.index(a) + key2) % 26]
        if mod == 2:
            if a.isupper():
                final_str += clear[(clear.index(a.lower()) + key3) % 26].upper()
            else:
                final_str += clear[(clear.index(a) + key3) % 26]
        # print(final_str)
        counter += 1
    # print(final_str)
    return final_str

def entschüsseln_c(string, key, enable):
    return verschlüsseln_c(string, key, enable)

text = verschlüsseln_c("Ich bin ein Baum", "132", True)
print(text)
print(entschüsseln_c(text, "132", False))