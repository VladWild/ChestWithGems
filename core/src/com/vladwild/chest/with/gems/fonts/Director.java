package com.vladwild.chest.with.gems.fonts;

public class Director {
    public static Font getFont(FontBuilder fontBuilder){
        fontBuilder.createFont();

        fontBuilder.buildColor();
        fontBuilder.buildSize();
        fontBuilder.buildText();
        fontBuilder.buildPosition();

        return fontBuilder.getFont();
    }
}
