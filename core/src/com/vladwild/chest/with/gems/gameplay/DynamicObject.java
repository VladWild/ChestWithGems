package com.vladwild.chest.with.gems.gameplay;

public interface DynamicObject extends StaticObject{
    boolean isCenterLogicalSquare();
    void move(Direction directionIn, int pixel);
}

