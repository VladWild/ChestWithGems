package com.vladwild.chest.with.gems.fonts;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.vladwild.chest.with.gems.location.GamePlaySettingsInformation;
import com.vladwild.chest.with.gems.resources.GamePlaySettingsManager;

public enum GamePlaySettingsFontFactory {
    LEVEL {
        @Override
        protected BitmapFont getFont() {
            BitmapFont font = new BitmapFont(gpsm.getFontLarge());
            font.setColor(gpsi.getColor());
            font.getData().setScale(gpsi.getSizeLevel());
            return font;
        }
    }, NUMBER_LEVEL{
        @Override
        protected BitmapFont getFont(){
            BitmapFont font = new BitmapFont(gpsm.getFontLarge());
            font.setColor(gpsi.getColor());
            font.getData().setScale(gpsi.getSizeNumberLevel());
            return font;
        }
    }, SPEED{
        @Override
        protected BitmapFont getFont(){
            BitmapFont font = new BitmapFont(gpsm.getFontLarge());
            font.setColor(gpsi.getColor());
            font.getData().setScale(gpsi.getSizeSpeed());
            return font;
        }
    }, VALUE_CURRENT_SPEED{
        @Override
        protected BitmapFont getFont(){
            BitmapFont font = new BitmapFont(gpsm.getFontLarge());
            font.setColor(gpsi.getColor());
            font.getData().setScale(gpsi.getSizeValueCurrentSpeed());
            return font;
        }
    };

    private static GamePlaySettingsInformation gpsi;
    private static GamePlaySettingsManager gpsm;

    protected abstract BitmapFont getFont();

    public static BitmapFont getTypeFont(GamePlaySettingsFontFactory typeFont,
                                         GamePlaySettingsInformation gpsiIn,
                                         GamePlaySettingsManager gpsmIn){
        gpsi = gpsiIn;
        gpsm = gpsmIn;
        return typeFont.getFont();
    }
}
