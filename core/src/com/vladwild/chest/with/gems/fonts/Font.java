package com.vladwild.chest.with.gems.fonts;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.GridPoint2;

public class Font extends BitmapFont{
    private GridPoint2 position;
    private String text;

    public Font(FileHandle font){
        super(font);
    }

    public GridPoint2 getPosition() {
        return position;
    }

    public void setPosition(GridPoint2 position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getCalculatedX(String textIn){
        return position.x - textIn.length() * getSpaceWidth() / 2;
    }
}
