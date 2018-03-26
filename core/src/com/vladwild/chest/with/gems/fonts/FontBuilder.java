package com.vladwild.chest.with.gems.fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public abstract class FontBuilder {
    private static final FileHandle FONT_FILE = Gdx.files.internal("fonts\\exo-large.fnt");

    protected final float WIDTH = (float) Gdx.graphics.getWidth();
    protected final float HIGHT = (float) Gdx.graphics.getHeight();

    protected Font font;

    public void createFont(){
        font = new Font(FONT_FILE);
    }

    public abstract void buildColor();
    public abstract void buildSize();
    public abstract void buildText();
    public abstract void buildPosition();

    public Font getFont(){
        return font;
    }
}
