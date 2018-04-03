package com.vladwild.chest.with.gems.methods.tasks;

import com.badlogic.gdx.math.GridPoint2;

import java.util.List;
import java.util.Set;

public interface Function {
    List<?> getElements(GridPoint2 point);

    GridPoint2 getPositionObject();
    Set<GridPoint2> getPositionObjects();

    GridPoint2 getPossiblePosition(Object element, GridPoint2 point);
}
