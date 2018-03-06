package com.vladwild.chest.with.gems.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.location.GamePlayInformation;
import com.vladwild.chest.with.gems.resources.GamePlayManager;

public class Key implements StaticObject{
    private static Texture texture;
    private GridPoint2 positionPixel;

    public Key(GamePlayInformation gpi, GamePlayManager gpm, GridPoint2 positionLogic){
        if (texture == null) texture = new Texture(gpm.getKey());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        return positionPixel.equals(key.positionPixel);

    }

    @Override
    public int hashCode() {
        return positionPixel.hashCode();
    }
}
