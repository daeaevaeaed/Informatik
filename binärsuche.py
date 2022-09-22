import math as m
from re import search
# Binäre Suche
# SucheNachKarte(Val, list)
# repeates = 0
# while(log2(Length(list)) or repeates < legth(list)){
#     repeates += 1
#     List_temp = list
#     Position = roundup(Length(List_temp) / 2)
#     card_val = DreheKarteUm(position)
#     if card_val > Val
#         deleteItemsOfList(position-length(List_temp))
#     else if card_val < Val
#         deleteItemsOfList(0-position)
#     if card_val = Val
#         return true
#         break
# }
# return False

samplelist = [10,20,30,40,60,70,80,90,100,110,120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260]

def SucheNachKarte(searching, Liste):
    print(searching)
    list_temp = samplelist
    repeates = 0
    # print((m.log2(len(samplelist))))
    print(m.ceil(m.log2(len(samplelist))))
    while(True):
        Positions = m.ceil(len(list_temp) / 2)
        # print("TempList", list_temp, "position", Positions)
        card_val = list_temp[Positions-1]
        # print("CardValue", card_val, "SearchVal", searchval)
        if card_val < searching:
            list_temp = list_temp[Positions:]
            # print("deleting bottom half")
        if card_val > searching:
            del list_temp[-Positions:]
            # print("deleting top half")
        if card_val == searching:
            return True
            break
        if len(list_temp) == 0:
            break
        print(list_temp)
    return False

searching = input("Nach was willst du suchen?")
print(SucheNachKarte(int(searching), searching))