package com.vladwild.chest.with.gems.gameplay.methods;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.gameplay.DynamicObject;

public interface DynamicObjectMethods extends DynamicObject {
    GridPoint2 getPositionLogic();
    GridPoint2 getPositionPixel();

    void setPositionLogic(GridPoint2 positionLogic);
    void setPositionPixel(GridPoint2 positionPixel);

    boolean isNodePoint();
}
