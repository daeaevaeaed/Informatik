import math as m
from operator import truediv
from random import sample
from re import search
from turtle import pos, position
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

samplelist = []

def SucheNachKarte(SearchVal, Liste):
    list_temp = samplelist
    repeates = 0
    # print((m.log2(len(samplelist))))
    print(m.ceil(m.log2(len(samplelist))))
    while(repeates < 3 + m.ceil(m.log2(len(samplelist)))):
        Positions = m.ceil(len(list_temp) / 2)
        print("TempList", list_temp)
        card_val = list_temp[Positions]
        print("CardValue", card_val)
        if card_val > SearchVal:
            del list_temp[Positions:]
            print("deleting top half")
        elif card_val < SearchVal:
            del list_temp[-Positions:]
            print("deleting top half")
        if card_val == SearchVal:
            return True
            break
        repeates += 1
        print("repeats", repeates)
    return False


print(SucheNachKarte(20, 1))