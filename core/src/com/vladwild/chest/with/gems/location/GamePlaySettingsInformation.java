package com.vladwild.chest.with.gems.location;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

public class GamePlaySettingsInformation extends Information{
    private final GridPoint2 SIZE_START;
    private final GridPoint2 SIZE_SCROLLS;
    private final GridPoint2 SIZE_BACK;

    private final Rectangle TABLE_SIZE_START;
    private final Rectangle TABLE_SIZE_LEVEL_LEFT;
    private final Rectangle TABLE_SIZE_LEVEL_RIGHT;
    private final Rectangle TABLE_SIZE_SPEED_LEFT;
    private final Rectangle TABLE_SIZE_SPEED_RIGHT;
    private final Rectangle TABLE_SIZE_BACK;

    private final GridPoint2 POSITION_LEVEL;
    private final GridPoint2 POSITION_NUMBER_LEVEL;
    private final GridPoint2 POSITION_SPEED;
    private final GridPoint2 POSITION_VALUE_CURRENT_SPEED;

    private final float SIZE_LEVEL;
    private final float SIZE_NUMBER_LEVEL;
    private final float SIZE_SPEED;
    private final float SIZE_VALUE_CURRENT_SPEED;

    private final String LEVEL;
    private final String SPEED;

    private final Color COLOR;

    public GamePlaySettingsInformation(){
        SIZE_START = new GridPoint2((int) (0.3f * WIDTH), (int) (0.05f * HIGHT));
        SIZE_SCROLLS = new GridPoint2((int) (0.08f * WIDTH), (int) (0.06f * HIGHT));

        TABLE_SIZE_START = new Rectangle(0, 0.4f * HIGHT, WIDTH, 0.6f * HIGHT);

        final float Y_H = 0.1f * HIGHT;

        TABLE_SIZE_LEVEL_LEFT = new Rectangle(0.2f * WIDTH, 0.3f * HIGHT, 0.3f * WIDTH, 0.45f * HIGHT);
        TABLE_SIZE_LEVEL_RIGHT = new Rectangle(0.5f * WIDTH, 0.3f * HIGHT, 0.3f * WIDTH, 0.45f * HIGHT);
        TABLE_SIZE_SPEED_LEFT = new Rectangle(0.2f * WIDTH, 0.3f * HIGHT - Y_H, 0.3f * WIDTH, 0.45f * HIGHT - Y_H);
        TABLE_SIZE_SPEED_RIGHT = new Rectangle(0.5f * WIDTH, 0.3f * HIGHT - Y_H, 0.3f * WIDTH, 0.45f * HIGHT - Y_H);

        SIZE_BACK = new GridPoint2((int) ((3f/ 21f) * WIDTH), (int) ((3f/ 31f) * HIGHT));
        TABLE_SIZE_BACK = new Rectangle((( 17f/ 21f) * WIDTH), ((1f/ 31f) * HIGHT), ((3f/ 21f) * WIDTH), ((3f/ 31f) * HIGHT));

        POSITION_LEVEL = new GridPoint2((int) (0.5f * WIDTH), (int) (0.62f * HIGHT));
        POSITION_NUMBER_LEVEL = new GridPoint2((int) (0.5f * WIDTH), (int) (0.545f * HIGHT));
        POSITION_SPEED = new GridPoint2((int) (0.5f * WIDTH), (int) (0.47f * HIGHT));
        POSITION_VALUE_CURRENT_SPEED = new GridPoint2((int) (0.5f * WIDTH), (int) (0.395f * HIGHT));

        SIZE_LEVEL = 0.5f;
        SIZE_NUMBER_LEVEL = 0.5f;
        SIZE_SPEED = 0.5f;
        SIZE_VALUE_CURRENT_SPEED = 0.5f;

        LEVEL = "Level";
        SPEED = "Speed";

        COLOR = Color.WHITE;
    }

    public GridPoint2 getSizeStart(){
        return SIZE_START;
    }

    public GridPoint2 getSizeScrolls(){
        return SIZE_SCROLLS;
    }

    public GridPoint2 getSizeBack(){
        return SIZE_BACK;
    }

    public Rectangle getTableSizeStart(){
        return TABLE_SIZE_START;
    }

    public Rectangle getTableSizeLevelLeft(){
        return TABLE_SIZE_LEVEL_LEFT;
    }

    public Rectangle getTableSizeLevelRight(){
        return TABLE_SIZE_LEVEL_RIGHT;
    }

    public Rectangle getTableSizeSpeedLeft(){
        return TABLE_SIZE_SPEED_LEFT;
    }

    public Rectangle getTableSizeSpeedRight(){
        return TABLE_SIZE_SPEED_RIGHT;
    }

    public Rectangle getTableSizeBack(){
        return TABLE_SIZE_BACK;
    }

    public GridPoint2 getPositionLevel() {
        return POSITION_LEVEL;
    }

    public GridPoint2 getPositionNumberLevel() {
        return POSITION_NUMBER_LEVEL;
    }

    public GridPoint2 getPositionSpeed() {
        return POSITION_SPEED;
    }

    public GridPoint2 getPositionValueCurrentSpeed() {
        return POSITION_VALUE_CURRENT_SPEED;
    }

    public float getSizeLevel() {
        return SIZE_LEVEL;
    }

    public float getSizeNumberLevel() {
        return SIZE_NUMBER_LEVEL;
    }

    public float getSizeSpeed() {
        return SIZE_SPEED;
    }

    public float getSizeValueCurrentSpeed() {
        return SIZE_VALUE_CURRENT_SPEED;
    }

    public String getLevel() {
        return LEVEL;
    }

    public String getSpeed() {
        return SPEED;
    }

    public Color getColor() {
        return COLOR;
    }
}
