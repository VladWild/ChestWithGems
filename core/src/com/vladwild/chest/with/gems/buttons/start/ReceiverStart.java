package com.vladwild.chest.with.gems.buttons.start;

import com.badlogic.gdx.Gdx;
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
import com.vladwild.chest.with.gems.location.StartInformation;
import com.vladwild.chest.with.gems.resources.StartManager;
import com.vladwild.chest.with.gems.screens.GamePlay;
import com.vladwild.chest.with.gems.screens.methods.SearchVariantTreesScreen;

public class ReceiverStart {
    ChestWithGems game;
    StartInformation si;
    StartManager sm;

    public ReceiverStart(ChestWithGems game, StartInformation si, StartManager sm) {
        this.si = si;
        this.sm = sm;
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

    Stage getPlay() {
        return getStage(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                game.setScreen(new GamePlay(game, 1, 8));
                            }
                        }, sm.getButtonAtlas(), sm.getButtonStartUp(), sm.getButtonStartDown(),
                si.getSizeButtons(), si.getTableSizePlay());
    }

    Stage getMethods() {
        return getStage(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                game.setScreen(new SearchVariantTreesScreen(game, 3, 16, 2, true));
                            }
                        }, sm.getButtonAtlas(), sm.getButtonMethodsUp(), sm.getButtonMethodsDown(),
                si.getSizeButtons(), si.getTableSizeMethods());
    }

    Stage getAuthor() {
        return getStage(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                System.out.println("Click Author");
                            }
                        }, sm.getButtonAtlas(), sm.getButtonAuthorUp(),
                sm.getButtonAuthorDown(), si.getSizeButtons(), si.getTableSizeAutors());
    }

    Stage getExit(){
        return getStage(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                Gdx.app.exit();
                            }
                        }, sm.getButtonAtlas(), sm.getButtonExitUp(),
                sm.getButtonExitDown(), si.getSizeButtons(), si.getTableSizeExit());
    }
}

