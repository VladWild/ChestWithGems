package com.vladwild.chest.with.gems.resources;

public class GamePlaySettingsManager extends ResourceManager{
    private static final String PROPERTY_NAME = "property\\gameplaysettings.properties";

    private static final String BUTTON_ATLAS_START = "button_atlas_start";
    private static final String BUTTON_ATLAS_SCROLLS = "button_atlas_scrolls";
    private static final String BUTTON_ATLAS_BACK = "button_atlas_back";

    private static final String BUTTON_START_UP = "button_start_up";
    private static final String BUTTON_START_DOWN = "button_start_down";

    private static final String BUTTON_SCROLLS_LEFT_UP = "left_up";
    private static final String BUTTON_SCROLLS_LEFT_DOWN = "left_down";
    private static final String BUTTON_SCROLLS_RIGTH_UP = "right_up";
    private static final String BUTTON_SCROLLS_RIGTH_DOWN = "right_down";

    private static final String BUTTON_BACK_UP = "back_up";
    private static final String BUTTON_BACK_DOWN = "back_down";

    public GamePlaySettingsManager() {
        super(PROPERTY_NAME);
    }

    public String getButtonAtlasStart(){
        return getStringKey(BUTTON_ATLAS_START);
    }

    public String getButtonAtlasScrolls(){
        return getStringKey(BUTTON_ATLAS_SCROLLS);
    }

    public String getButtonAtlasBack(){
        return getStringKey(BUTTON_ATLAS_BACK);
    }

    public String getButtonStartUp(){
        return BUTTON_START_UP;
    }

    public String getButtonStartDown(){
        return BUTTON_START_DOWN;
    }

    public String getButtonScrollsLeftUp(){
        return BUTTON_SCROLLS_LEFT_UP;
    }

    public String getButtonScrollsLeftDown(){
        return BUTTON_SCROLLS_LEFT_DOWN;
    }

    public String getButtonScrollsRigthUp(){
        return BUTTON_SCROLLS_RIGTH_UP;
    }

    public String getButtonScrollsRigthDown(){
        return BUTTON_SCROLLS_RIGTH_DOWN;
    }

    public String getButtonBackUp(){
        return BUTTON_BACK_UP;
    }

    public String getButtonBackDown(){
        return BUTTON_BACK_DOWN;
    }

}
