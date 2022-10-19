clear = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]


def verschlüsseln(string, key):
    final_str = ""
    for a in string:
        if a == " ":
            final_str += " "
            continue
        final_str += clear[(clear.index(a) + key) % 26]
    print(final_str)
    return final_str



def entschüssen(string, key):
    final_str = ""
    for a in string:
        if a == " ":
            final_str += " "
            continue
        final_str += clear[(clear.index(a) - key) % 26]
    print(final_str)
    return final_str

# print(verschlüsseln("hallo", 2))
key = int(input("Welchen Schlüssel möchtest du anwenden"))
print(entschüssen(verschlüsseln("guten tag", key), key), "final")