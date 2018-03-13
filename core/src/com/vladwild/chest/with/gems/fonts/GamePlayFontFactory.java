package com.vladwild.chest.with.gems.fonts;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.vladwild.chest.with.gems.location.GamePlayInformation;
import com.vladwild.chest.with.gems.resources.GamePlayManager;

public enum GamePlayFontFactory {
    NAME_LEVEL {
        @Override
        protected BitmapFont getFont(){
            BitmapFont font = new BitmapFont(gpm.getFontLarge());
            font.setColor(gpi.getColor());
            font.getData().setScale(gpi.getSizeLevelName());
            return font;
        }
    }, NUMBER_LEVEL {
        @Override
        protected BitmapFont getFont(){
            BitmapFont font = new BitmapFont(gpm.getFontLarge());
            font.setColor(gpi.getColor());
            font.getData().setScale(gpi.getSizeLevelNumber());
            return font;
        }
    }, NAME_KEYS {
        @Override
        protected BitmapFont getFont(){
            BitmapFont font = new BitmapFont(gpm.getFontLarge());
            font.setColor(gpi.getColor());
            font.getData().setScale(gpi.getSizeKeysName());
            return font;
        }
    }, COUNT_KEYS {
        @Override
        protected BitmapFont getFont(){
            BitmapFont font = new BitmapFont(gpm.getFontLarge());
            font.setColor(gpi.getColor());
            font.getData().setScale(gpi.getSizeKeysCount());
            return font;
        }
    };

    private static GamePlayInformation gpi;
    private static GamePlayManager gpm;

    protected abstract BitmapFont getFont();

    public static BitmapFont getTypeFont(GamePlayFontFactory typeFont, GamePlayInformation gpiIn, GamePlayManager gpmIn){
        gpm = gpmIn;
        gpi = gpiIn;
        return typeFont.getFont();
    }
}
