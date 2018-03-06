package com.vladwild.chest.with.gems.resources;

public class StartManager extends ResourceManager {
    private static final String PROPERTY_NAME = "property\\start.properties";

    private static final String NAME = "name";
    private static final String CHEAT = "cheat";

    private static final String BUTTON_ATLAS = "buttons_atlas";

    private static final String BUTTON_PLAY_UP = "button_play_up";
    private static final String BUTTON_PLAY_DOWN = "button_play_down";

    private static final String BUTTON_METHODS_UP = "button_methods_up";
    private static final String BUTTON_METHODS_DOWN = "button_methods_down";

    private static final String BUTTON_AUTHOR_UP = "button_author_up";
    private static final String BUTTON_AUTHOR_DOWN = "button_author_down";

    private static final String BUTTON_EXIT_UP = "button_exit_up";
    private static final String BUTTON_EXIT_DOWN = "button_exit_down";

    public StartManager() {
        super(PROPERTY_NAME);
    }

    public String getName(){
        return getStringKey(NAME);
    }

    public String getCheat(){
        return getStringKey(CHEAT);
    }

    public String getButtonAtlas(){
        return getStringKey(BUTTON_ATLAS);
    }

    public String getButtonStartUp(){
        return BUTTON_PLAY_UP;
    }

    public String getButtonStartDown(){
        return BUTTON_PLAY_DOWN;
    }

    public String getButtonMethodsUp(){
        return BUTTON_METHODS_UP;
    }

    public String getButtonMethodsDown(){
        return BUTTON_METHODS_DOWN;
    }

    public String getButtonAuthorUp(){
        return BUTTON_AUTHOR_UP;
    }

    public String getButtonAuthorDown(){
        return BUTTON_AUTHOR_DOWN;
    }

    public String getButtonExitUp(){
        return BUTTON_EXIT_UP;
    }

    public String getButtonExitDown(){
        return BUTTON_EXIT_DOWN;
    }
}
