package com.vladwild.chest.with.gems.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public interface Command {
    int WIDTH = Gdx.graphics.getWidth();
    int HIGHT = Gdx.graphics.getHeight();

    Stage getButton();

    default Stage buildStage(ClickListener clickListener, final String PATH_ATLAS,
                             final String upStyle, final String downStyle,
                             final GridPoint2 sizeButton, final Rectangle sizeTable){
        Skin skin = new Skin(new TextureAtlas(PATH_ATLAS));

        TextButton.TextButtonStyle styleButton = new TextButton.TextButtonStyle();
        styleButton.up = skin.getDrawable(upStyle);
        styleButton.down = skin.getDrawable(downStyle);

        Button button = new Button(styleButton);
        button.addListener(clickListener);

        Table table = new Table(skin);
        table.add(button).size(sizeButton.x, sizeButton.y);
        table.setSize(sizeTable.width, sizeTable.height);
        table.setPosition(sizeTable.x, sizeTable.y);

        Stage stage = new Stage();
        stage.addActor(table);

        return stage;
    }
}

