package com.vladwild.chest.with.gems.location;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

public class StartInformation extends Information {
    private final Rectangle NAME;
    private final Rectangle CHEAT;

    private final GridPoint2 SIZE_BUTTONS;
    private final Rectangle TABLE_SIZE_PLAY;
    private final Rectangle TABLE_SIZE_METHODS;
    private final Rectangle TABLE_SIZE_AUTHOR;
    private final Rectangle TABLE_SIZE_EXIT;

    public StartInformation(){
        final float NAME_X0 = 0.2f * WIDTH;
        final float NAME_Y0 = 0.56f *  HIGHT;
        final float NAME_X1 = WIDTH - 2f * NAME_X0;
        final float NAME_Y1 = HIGHT - NAME_Y0 - 0.05f * HIGHT;
        NAME = new Rectangle(NAME_X0, NAME_Y0, NAME_X1, NAME_Y1);

        final float CHEAT_X0 = 0.7f * WIDTH;
        final float CHEAT_Y0 = 0.02f * HIGHT;
        final float CHEAT_X1 =  WIDTH - CHEAT_X0 - 0.05f * WIDTH;
        final float CHEAT_Y1 = 0.18f * HIGHT - CHEAT_Y0;
        CHEAT = new Rectangle(CHEAT_X0, CHEAT_Y0, CHEAT_X1, CHEAT_Y1);

        SIZE_BUTTONS = new GridPoint2((int) (0.3f * WIDTH), (int) (0.05f * HIGHT));

        final float DISTANCE = 0.15f * HIGHT;
        TABLE_SIZE_PLAY = new Rectangle(0, 0, WIDTH, 0.8f * HIGHT);
        TABLE_SIZE_METHODS = new Rectangle(0, 0, WIDTH, TABLE_SIZE_PLAY.height - DISTANCE);
        TABLE_SIZE_AUTHOR = new Rectangle(0, 0, WIDTH, TABLE_SIZE_PLAY.height - 2 * DISTANCE);
        TABLE_SIZE_EXIT = new Rectangle(0, 0, WIDTH, TABLE_SIZE_PLAY.height - 3 * DISTANCE);
    }

    public Rectangle getNameRect(){
        return NAME;
    }

    public Rectangle getCheatRect(){
        return CHEAT;
    }

    public GridPoint2 getSizeButtons(){
        return SIZE_BUTTONS;
    }

    public Rectangle getTableSizePlay(){
        return TABLE_SIZE_PLAY;
    }

    public Rectangle getTableSizeMethods(){
        return TABLE_SIZE_METHODS;
    }

    public Rectangle getTableSizeAutors(){
        return TABLE_SIZE_AUTHOR;
    }

    public Rectangle getTableSizeExit(){
        return TABLE_SIZE_EXIT;
    }

}

