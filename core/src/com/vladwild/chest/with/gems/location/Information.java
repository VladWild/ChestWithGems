package com.vladwild.chest.with.gems.location;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

abstract class Information {
    private final Rectangle BACKGROUND;

    protected final float WIDTH;
    protected final float HIGHT;

    protected Information(){
        final float BACKGROUND_X0 = 0;
        final float BACKGROUND_Y0 = 0;

        WIDTH = (float) Gdx.graphics.getWidth();
        HIGHT = (float) Gdx.graphics.getHeight();

        BACKGROUND = new Rectangle(BACKGROUND_X0, BACKGROUND_Y0, WIDTH, HIGHT);
    }

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
