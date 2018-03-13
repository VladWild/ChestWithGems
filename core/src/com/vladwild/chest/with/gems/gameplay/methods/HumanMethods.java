package com.vladwild.chest.with.gems.gameplay.methods;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.InputProcessors.GamePlayInputProcessor;
import com.vladwild.chest.with.gems.gameplay.Human;
import com.vladwild.chest.with.gems.location.GamePlayInformation;
import com.vladwild.chest.with.gems.resources.GamePlayManager;

public class HumanMethods extends Human implements DynamicObjectMethods {

    public HumanMethods(GamePlayInformation gpi, GamePlayManager gpm, GridPoint2 positionLogic,
                 GamePlayInputProcessor gpip, boolean matrixLogic[][]){
        super(gpi, gpm, positionLogic, gpip, matrixLogic);
    }

    @Override
    public GridPoint2 getPositionPixel(){
        return positionPixel;
    }

    @Override
    public GridPoint2 getPositionLogic() {
        return positionLogic;
    }

    @Override
    public void setPositionLogic(GridPoint2 positionLogic) {
        super.positionLogic = new GridPoint2(positionLogic.x, positionLogic.y);
    }

    @Override
    public void setPositionPixel(GridPoint2 positionPixel) {
        super.positionPixel = new GridPoint2(positionPixel.x, positionPixel.y);
    }

    @Override
    public boolean isNodePoint() {
        return (matrixLogic[positionLogic.y + 1][positionLogic.x] && matrixLogic[positionLogic.y][positionLogic.x - 1]) ||
                (matrixLogic[positionLogic.y][positionLogic.x + 1] && matrixLogic[positionLogic.y + 1][positionLogic.x]) ||
                (matrixLogic[positionLogic.y - 1][positionLogic.x] && matrixLogic[positionLogic.y][positionLogic.x + 1]) ||
                (matrixLogic[positionLogic.y][positionLogic.x - 1] && matrixLogic[positionLogic.y - 1][positionLogic.x]);
    }
}
