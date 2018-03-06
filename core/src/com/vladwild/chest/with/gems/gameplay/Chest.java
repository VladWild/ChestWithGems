package com.vladwild.chest.with.gems.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.location.GamePlayInformation;
import com.vladwild.chest.with.gems.resources.GamePlayManager;

public class Chest implements StaticObject{
    private Texture texture;
    private GridPoint2 positionPixel;

    public Chest(GamePlayInformation gpi, GamePlayManager gpm, GridPoint2 positionLogic){
        texture = new Texture(gpm.getChest());
        positionPixel = new GridPoint2(gpi.getSizeBlockPicture().x * positionLogic.x,
                gpi.getSizeBlockPicture().y * positionLogic.y);
    }

    @Override
    public Texture getTexture(){
        return texture;
    }

    @Override
    public GridPoint2 getPositionPixel(){
        return positionPixel;
    }
}
