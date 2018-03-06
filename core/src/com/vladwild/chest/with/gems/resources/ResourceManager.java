package com.vladwild.chest.with.gems.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

abstract class ResourceManager {
    protected final String PROPERTY_NAME;

    private static final String BACKGROUND = "background";

    protected ResourceManager(final String PROPERTY_NAME) {
        this.PROPERTY_NAME = PROPERTY_NAME;
    }

    protected String getStringKey(String key) {
        FileHandle propertiesFileHandle = Gdx.files.internal(PROPERTY_NAME);
        Properties property = new Properties();
        try {
            property.load(new BufferedInputStream(propertiesFileHandle.read()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property.getProperty(key);
    }

    protected FileHandle getFileHandle(String key){
        FileHandle propertiesFileHandle = Gdx.files.internal(PROPERTY_NAME);
        Properties property = new Properties();
        try {
            property.load(new BufferedInputStream(propertiesFileHandle.read()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Gdx.files.internal(property.getProperty(key));
    }

    public String getBackground(){
        return getStringKey(BACKGROUND);
    }
}
