package com.vladwild.chest.with.gems.location;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public abstract class Information {
    protected final float WIDTH = (float) Gdx.graphics.getWidth();
    protected final float HIGHT = (float) Gdx.graphics.getHeight();

    private final Rectangle BACKGROUND = new Rectangle(0, 0, WIDTH, HIGHT);

    public float getWidth(){
        return WIDTH;
    }

    public float getHight(){
        return HIGHT;
    }

    public Rectangle getBGRect(){
        return BACKGROUND;
    }
}
