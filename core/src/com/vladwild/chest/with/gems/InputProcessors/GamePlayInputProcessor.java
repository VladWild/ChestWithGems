package com.vladwild.chest.with.gems.InputProcessors;

import com.vladwild.chest.with.gems.gameplay.Direction;

public abstract class GamePlayInputProcessor {
    protected static Direction direction;

    public GamePlayInputProcessor(Direction directionStart){
        direction = directionStart;
    }

    public Direction getDirection(){
        return direction;
    }
}
