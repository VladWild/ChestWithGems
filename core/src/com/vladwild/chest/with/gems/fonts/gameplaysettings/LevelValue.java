package com.vladwild.chest.with.gems.fonts.gameplaysettings;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.fonts.FontBuilder;

public class LevelValue extends FontBuilder{
    @Override
    public void buildColor() {
        font.setColor(Color.WHITE);
    }

    @Override
    public void buildSize() {
        font.getData().setScale(0.5f);
    }

    @Override
    public void buildText() {
        font.setText("");
    }

    @Override
    public void buildPosition() {
        font.setPosition(new GridPoint2((int) (0.5f * WIDTH), (int) (0.545f * HIGHT)));
    }
}
