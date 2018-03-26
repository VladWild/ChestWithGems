package com.vladwild.chest.with.gems.resources;

public class StartManager extends ResourceManager {
    private static final String PROPERTY_NAME = "properties\\start.properties";

    private static final String NAME = "name";
    private static final String CHEAT = "cheat";

    public StartManager() {
        super(PROPERTY_NAME);
    }

    public String getName(){
        return getStringKey(NAME);
    }

    public String getCheat(){
        return getStringKey(CHEAT);
    }
}
