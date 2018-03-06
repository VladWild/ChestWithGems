package com.vladwild.chest.with.gems.utilityclasses;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.vladwild.chest.with.gems.location.GamePlayInformation;
import com.vladwild.chest.with.gems.resources.GamePlayManager;

public class CreatingLevelTexture {
    private static Pixmap[] getArrayPixmap(GamePlayInformation gpi, GamePlayManager gpm){
        Pixmap[] elements = new Pixmap[gpi.getCountElementInField()];

        for (int i = 0; i < gpi.getCountElementInField(); i++){
            elements[i] = new Pixmap(gpm.getFileHandleOfElementField(gpi.getNumberBG()));
            elements[i].drawPixmap(new Pixmap(gpm.getFileHandleOfElementField(i)),
                    gpi.getPositionPixmap().x, gpi.getPositionPixmap().y);
        }

        return elements;
    }

    private static int getFieldElement(int element, GamePlayInformation gpi){
        if (element < gpi.getCountElementInField()) return element;
        else return 1;
    }

    public static Texture getTexture(GamePlayInformation gpi, GamePlayManager gpm, int[][] matrix){
        Pixmap[] elements = getArrayPixmap(gpi, gpm);

        Texture texture = new Texture(gpi.getSizeBlockPicture().x * gpi.getLogicSizeField().x,
                gpi.getSizeBlockPicture().y * gpi.getLogicSizeField().y, Pixmap.Format.RGBA8888);

        for (int n = 0; n < gpi.getLogicSizeField().x; n++) { //исправить и посмотреть
            for (int k = 0; k < gpi.getLogicSizeField().y; k++) {
                texture.draw(elements[getFieldElement(matrix[(gpi.getLogicSizeField().x - 1) - n][k], gpi)],
                        k * gpi.getSizeBlockPicture().x, n * gpi.getSizeBlockPicture().y);
            }
        }

        return texture;
    }
}
