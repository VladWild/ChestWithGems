package com.vladwild.chest.with.gems.resources;

import com.badlogic.gdx.files.FileHandle;

public class GamePlayManager extends ResourceManager {
    private static final String PROPERTY_NAME = "property\\gameplay.properties";

    private static final String FIRST_LEVEL = "first_level";
    private static final String SECOND_LEVEL = "second_level";
    private static final String THIRD_LEVEL = "third_level";

    private static final String HUMAN = "human_0";
    private static final String KEY = "key";
    private static final String CHEST = "chest";

    private static final String ARROW = "arrow_";
    private static final String RIGHT = "right";
    private static final String UP = "up";
    private static final String LEFT = "left";
    private static final String DOWN = "down";

    private static final String BUTTON_ATLAS = "button_atlas";

    private static final String BUTTON_HOME_UP = "home_up";
    private static final String BUTTON_HOME_DOWN = "home_down";

    private static final String BUTTON_BACK_UP = "back_up";
    private static final String BUTTON_BACK_DOWN = "back_down";

    public GamePlayManager() {
        super(PROPERTY_NAME);
    }

    public FileHandle getFileHandleOfCSVFile(int level){
        switch (level){
            case 1:
                return getFileHandle(FIRST_LEVEL);
            case 2:
                return getFileHandle(SECOND_LEVEL);
            case 3:
                return getFileHandle(THIRD_LEVEL);
            default:
                return getFileHandle(FIRST_LEVEL);
        }
    }

    public FileHandle getFileHandleOfElementField(int number){
        return getFileHandle(String.valueOf(number));
    }

    public FileHandle getHuman(int number){
        return getFileHandle(HUMAN + String.valueOf(number));
    }

    public String getKey(){
        return getStringKey(KEY);
    }

    public String getChest(){
        return getStringKey(CHEST);
    }

    public String getArrowRight(){
        return getStringKey(ARROW + RIGHT);
    }

    public String getArrowUp(){
        return getStringKey(ARROW + UP);
    }

    public String getArrowLeft(){
        return getStringKey(ARROW + LEFT);
    }

    public String getArrowDown(){
        return getStringKey(ARROW + DOWN);
    }

    public String getButtonAtlas(){
        return getStringKey(BUTTON_ATLAS);
    }

    public String getButtonHomeUp(){
        return BUTTON_HOME_UP;
    }

    public String getButtonHomeDown(){
        return BUTTON_HOME_DOWN;
    }

    public String getButtonBackUp(){
        return BUTTON_BACK_UP;
    }

    public String getButtonBackDown(){
        return BUTTON_BACK_DOWN;
    }
}

