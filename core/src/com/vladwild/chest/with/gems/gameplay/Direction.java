package com.vladwild.chest.with.gems.gameplay;

public enum Direction {
    DOWN, LEFT, RIGTH, UP, STOP;

    public int getNumberDirection(){
        return this != STOP ? ordinal() : RIGTH.ordinal();
    }
}

