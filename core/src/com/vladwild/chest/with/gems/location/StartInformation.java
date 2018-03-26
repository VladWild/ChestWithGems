package com.vladwild.chest.with.gems.location;

import com.badlogic.gdx.math.Rectangle;

public class StartInformation extends Information {
    private final Rectangle NAME = new Rectangle(0.2f * WIDTH, 0.56f *  HIGHT, 0.6f * WIDTH, 0.4f *  HIGHT);
    private final Rectangle CHEAT = new Rectangle(0.72f * WIDTH, 0.02f * HIGHT, 0.25f * WIDTH, 0.16f * HIGHT);

    public Rectangle getNameRect(){
        return NAME;
    }

    public Rectangle getCheatRect(){
        return CHEAT;
    }
}

