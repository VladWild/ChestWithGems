package com.vladwild.chest.with.gems.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.InputProcessors.GamePlayInputProcessor;
import com.vladwild.chest.with.gems.location.GamePlayInformation;
import com.vladwild.chest.with.gems.resources.GamePlayManager;

public class Arrow implements StaticObject{
    private Texture rigth;
    private Texture up;
    private Texture left;
    private Texture down;

    private GridPoint2 positionPixel;

    private GamePlayInputProcessor gpip;

    public Arrow(GamePlayInformation gpi, GamePlayManager gpm, GamePlayInputProcessor gpip){
        rigth = new Texture(gpm.getArrowRight());
        up = new Texture(gpm.getArrowUp());
        left = new Texture(gpm.getArrowLeft());
        down = new Texture(gpm.getArrowDown());

        positionPixel = gpi.getArrowPosition();

        this.gpip = gpip;
    }

    @Override
    public Texture getTexture(){
        switch (gpip.getDirection()){
            case RIGTH:
                return rigth;
            case UP:
                return up;
            case LEFT:
                return left;
            case DOWN:
                return down;
            default:
                return rigth;
        }
    }

    @Override
    public GridPoint2 getPositionPixel(){
        return positionPixel;
    }


}


