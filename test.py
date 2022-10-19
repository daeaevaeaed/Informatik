from operator import truediv


def kilidiertMit(k:kreis):
    posX1 = kreis1.getX() + r1
    posY1 = kries1.getY() + r1
    posX2 = kreis2.getX() + r2
    posY2 = kries2.getY() + r2
    distance = (posX1-posX2)**2 + (posY1 - posY2)**2
    if distance >= r1 + r2:
        return True
    else:
        return False
        