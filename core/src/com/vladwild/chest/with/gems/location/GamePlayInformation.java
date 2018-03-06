package com.vladwild.chest.with.gems.location;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.vladwild.chest.with.gems.gameplay.Direction;

public class GamePlayInformation extends Information {
    private final GridPoint2 SIZE_SCREEN;           //Screen
    private final Rectangle SIZE_BACKGROUND;        //Background

    private final GridPoint2 LOGIC_SIZE_FIELD;      //Field
    private final GridPoint2 SIZE_PICTURE;
    private final int COUNT_ELEMENTS_IN_FIELD;
    private final int NUMBER_BG;
    private final GridPoint2 POSITION_PIXMAP;
    private final int BLANK;
    private final GridPoint2 FIELD_RECT;

    private final int NUMBER_KEY;                   //Numbers objects on field
    private final int NUMBER_HUMAN;
    private final int NUMBER_CHEST;

    private final int COUNT_TEXTURES;               //Human
    private final int COUNT_TEXTURES_ON_SIDE;
    private final int COUNT_DIRECTION;
    private final float SPEED_ANIMATION;
    private final float STATE_TIME;

    private final int OFFSET;                       //Input Processor
    private final Direction DEFAULT_DIRECTION;

    private final GridPoint2 ARROW_POSITION;        //Arrow

    private final GridPoint2 SIZE_BUTTONS;          //Buttons
    private final Rectangle TABLE_SIZE_HOME;
    private final Rectangle TABLE_SIZE_BACK;

    private final GridPoint2 POSITION_LEVEL_NAME;   //Font
    private final GridPoint2 POSITION_LEVEL_NUMBER;
    private final GridPoint2 POSITION_KEYS_NAME;
    private final GridPoint2 POSITION_KEYS_COUNT;

    private final float SIZE_LEVEL_NAME;
    private final float SIZE_LEVEL_NUMBER;
    private final float SIZE_KEYS_NAME;
    private final float SIZE_KEYS_COUNT;

    private final String LEVEL;
    private final String KEYS;
    private final String DELIMITER;
    private final String DEFAULT_COUNT_KEYS;

    private final Color COLOR;

    public GamePlayInformation(){
        LOGIC_SIZE_FIELD = new GridPoint2(21, 21); //Field
        SIZE_PICTURE = new GridPoint2(128, 128);
        COUNT_ELEMENTS_IN_FIELD = 17;
        NUMBER_BG = 1;
        POSITION_PIXMAP = new GridPoint2(0, 0);
        BLANK = 5 * SIZE_PICTURE.y;
        FIELD_RECT = new GridPoint2(0, BLANK);

        SIZE_SCREEN = new GridPoint2(LOGIC_SIZE_FIELD.x * SIZE_PICTURE.x,
                LOGIC_SIZE_FIELD.y * SIZE_PICTURE.y + 2 * BLANK);    //Screen
        SIZE_BACKGROUND = new Rectangle(0, 0, SIZE_SCREEN.x, SIZE_SCREEN.y); //Background

        NUMBER_KEY = 17;        //Numbers objects on field
        NUMBER_HUMAN = 18;
        NUMBER_CHEST = 19;

        COUNT_TEXTURES = 12;        //Human
        COUNT_TEXTURES_ON_SIDE = 3;
        COUNT_DIRECTION = 4;
        SPEED_ANIMATION = 0.1f;
        STATE_TIME = 0f;

        OFFSET = 40;                            //Input Processor
        DEFAULT_DIRECTION = Direction.STOP;

        ARROW_POSITION = new GridPoint2(4 * SIZE_PICTURE.x, SIZE_PICTURE.y);    //Arrow

        SIZE_BUTTONS = new GridPoint2((int) ((3f/ 21f) * WIDTH), (int) ((3f/ 31f) * HIGHT));       //Buttons
        TABLE_SIZE_HOME = new Rectangle(((1f/ 21f) * WIDTH), ((1f/ 31f) * HIGHT),
                ((3f/ 21f) * WIDTH), ((3f/ 31f) * HIGHT));
        TABLE_SIZE_BACK = new Rectangle((( 17f/ 21f) * WIDTH), ((1f/ 31f) * HIGHT),
                ((3f/ 21f) * WIDTH), ((3f/ 31f) * HIGHT));

        POSITION_LEVEL_NAME = new GridPoint2((int) (( 1f/ 21f) * SIZE_SCREEN.x),           //Font
                (int) (( 30f/ 31f) * SIZE_SCREEN.y));
        POSITION_LEVEL_NUMBER = new GridPoint2((int) ((1.8f/ 21f) * SIZE_SCREEN.x),
                (int) ((28.5f/ 31f) * SIZE_SCREEN.y));
        POSITION_KEYS_NAME = new GridPoint2((int) (( 17f/ 21f) * SIZE_SCREEN.x),
                (int) (( 30f/ 31f) * SIZE_SCREEN.y));
        POSITION_KEYS_COUNT = new GridPoint2((int) ((16.7f/ 21f) * SIZE_SCREEN.x),
                (int) ((28.5f/ 31f) * SIZE_SCREEN.y));

        SIZE_LEVEL_NAME = 1.78f;
        SIZE_LEVEL_NUMBER = 3.52f;
        SIZE_KEYS_NAME = 1.78f;
        SIZE_KEYS_COUNT = 3.52f;

        LEVEL = "Level:";
        KEYS = "Keys:";
        DELIMITER = "/";
        DEFAULT_COUNT_KEYS = "0";

        COLOR = Color.BLACK;

    }

    @Override
    public Rectangle getBGRect() {
        return SIZE_BACKGROUND;
    }

    public float getWidthGamePlay(){
        return SIZE_SCREEN.x;
    }

    public float getHeightGamePlay(){
        return SIZE_SCREEN.y;
    }

    public GridPoint2 getLogicSizeField(){
        return LOGIC_SIZE_FIELD;
    }

    public GridPoint2 getSizeBlockPicture(){
        return SIZE_PICTURE;
    }

    public int getCountElementInField(){
        return COUNT_ELEMENTS_IN_FIELD;
    }

    public int getNumberBG(){
        return NUMBER_BG;
    }

    public GridPoint2 getPositionPixmap(){
        return POSITION_PIXMAP;
    }

    public int getBlank(){
        return BLANK;
    }

    public GridPoint2 getFieldRect(){
        return FIELD_RECT;
    }

    public int getNumberKey(){
        return NUMBER_KEY;
    }

    public int getNumberHuman(){
        return NUMBER_HUMAN;
    }

    public int getNumberChest(){
        return NUMBER_CHEST;
    }

    public int getCountTextures(){
        return COUNT_TEXTURES;
    }

    public int getCountTexturesOnSide(){
        return COUNT_TEXTURES_ON_SIDE;
    }

    public int getCountDirection(){
        return COUNT_DIRECTION;
    }

    public float getSpeedAnimation(){
        return SPEED_ANIMATION;
    }

    public float getStateTime(){
        return STATE_TIME;
    }

    public int getOffset(){
        return OFFSET;
    }

    public Direction getDefaultDirection(){
        return DEFAULT_DIRECTION;
    }

    public GridPoint2 getArrowPosition(){
        return ARROW_POSITION;
    }

    public GridPoint2 getSizeButtons(){
        return SIZE_BUTTONS;
    }

    public Rectangle getTableSizeHome(){
        return TABLE_SIZE_HOME;
    }

    public Rectangle getTableSizeBack(){
        return TABLE_SIZE_BACK;
    }

    public GridPoint2 getPositionNameLevel(){
        return POSITION_LEVEL_NAME;
    }

    public GridPoint2 getPositionNumberLevel(){
        return POSITION_LEVEL_NUMBER;
    }

    public GridPoint2 getPositionNameKeys(){
        return POSITION_KEYS_NAME;
    }

    public GridPoint2 getPositionCountKeys(){
        return POSITION_KEYS_COUNT;
    }

    public float getSizeLevelName(){
        return SIZE_LEVEL_NAME;
    }

    public float getSizeLevelNumber(){
        return SIZE_LEVEL_NUMBER;
    }

    public float getSizeKeysName(){
        return SIZE_KEYS_NAME;
    }

    public float getSizeKeysCount(){
        return SIZE_KEYS_COUNT;
    }

    public String getLevel(){
        return LEVEL;
    }

    public String getKeys(){
        return KEYS;
    }

    public String getDelimiter(){
        return DELIMITER;
    }

    public String getDefaultCountKeys(){
        return DEFAULT_COUNT_KEYS;
    }

    public Color getColor(){
        return COLOR;
    }

}
