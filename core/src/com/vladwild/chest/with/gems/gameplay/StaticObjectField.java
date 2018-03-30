package com.vladwild.chest.with.gems.gameplay;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Set;

public interface StaticObjectField extends StaticObject{
    Set<GridPoint2> getKeysPoints();
    GridPoint2 getHumanPoint();
    GridPoint2 getChestPoint();

    boolean[][] getMatrixLogic();
    boolean[][] getReverseMatrix();
}

