package com.vladwild.chest.with.gems.buttons.gameplay;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vladwild.chest.with.gems.gamestarter.ChestWithGems;
import com.vladwild.chest.with.gems.location.GamePlayInformation;
import com.vladwild.chest.with.gems.resources.GamePlayManager;
import com.vladwild.chest.with.gems.screens.Start;

public class ReceiverGamePlay {
    private ChestWithGems game;
    private GamePlayInformation gpi;
    private GamePlayManager gpm;

    public ReceiverGamePlay(ChestWithGems game, GamePlayInformation gpi, GamePlayManager gpm) {
        this.gpi = gpi;
        this.gpm = gpm;
        this.game = game;
    }

    private Stage getStage(ClickListener clickListener, String atlasPath, String upStyle,
                           String downStyle, GridPoint2 sizeButton, Rectangle sizeTable) {
        TextureAtlas atlas = new TextureAtlas(atlasPath);

        Skin skin = new Skin(atlas);

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

    Stage getHome() {
        return getStage(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                game.setScreen(new Start(game));
                            }
                        }, gpm.getButtonAtlas(), gpm.getButtonHomeUp(),
                gpm.getButtonHomeDown(), gpi.getSizeButtons(), gpi.getTableSizeHome());
    }



    Stage getBack() {
        return getStage(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                game.setScreen(new Start(game));
                            }
                        }, gpm.getButtonAtlas(), gpm.getButtonBackUp(),
                gpm.getButtonBackDown(), gpi.getSizeButtons(), gpi.getTableSizeBack());
    }
}
