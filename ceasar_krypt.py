clear = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]


def verschlüsseln(string, key):
    final_str = ""
    for a in string:
        # a = a.lower()
        if a == " ":
            final_str += " "
            continue
        if a.isupper():
            print("upper")
            final_str += clear[(clear.index(a.lower()) + key) % 26].upper()
        else:
            final_str += clear[(clear.index(a) + key) % 26]
    print(final_str)
    return final_str



def entschüsseln(string, key):
    final_str = ""
    for a in string:
        if a == " ":
            final_str += " "
            continue
        if a.isupper():
            print("upper")
            final_str += clear[(clear.index(a.lower()) - key) % 26].upper()
        else:
            final_str += clear[(clear.index(a) - key) % 26]
    print(final_str)
    return final_str

# print(verschlüsseln("hallo", 2))
# key = int(input("Welchen Schlüssel möchtest du anwenden"))
print(entschüsseln(verschlüsseln("Guten Tag", 1), 1), "final")