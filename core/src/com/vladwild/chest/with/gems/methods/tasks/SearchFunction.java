package com.vladwild.chest.with.gems.methods.tasks;

import com.badlogic.gdx.math.GridPoint2;

import java.util.List;
import java.util.Set;

public interface SearchFunction {
    Set<GridPoint2> getObjects();
    GridPoint2 getMovingObject();
    GridPoint2 getMovingObject(Object element);

    void move(Object element);
    void delete(Object element);

    boolean isEnd();

    List<?> getElements();
}

