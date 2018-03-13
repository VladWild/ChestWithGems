package com.vladwild.chest.with.gems.buttons.gameplaysettings;

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
import com.vladwild.chest.with.gems.location.GamePlaySettingsInformation;
import com.vladwild.chest.with.gems.resources.GamePlaySettingsManager;
import com.vladwild.chest.with.gems.screens.*;
import com.vladwild.chest.with.gems.screens.Start;

public class ReceiverGamePlaySettings {
    private ChestWithGems game;
    private GamePlaySettings gamePlaySettings;
    private GamePlaySettingsInformation gpsi;
    private GamePlaySettingsManager gpsm;

    public ReceiverGamePlaySettings(ChestWithGems game, GamePlaySettings gamePlaySettings,
                                    GamePlaySettingsInformation gpsi, GamePlaySettingsManager gpsm) {
        this.game = game;
        this.gamePlaySettings = gamePlaySettings;
        this.gpsi = gpsi;
        this.gpsm = gpsm;
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

    Stage getStart() {
        return getStage(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                game.setScreen(new GamePlay(game, gamePlaySettings.getCurrentLevel(),
                                        gamePlaySettings.getCurrentSpeed()));
                            }
                        }, gpsm.getButtonAtlasStart(), gpsm.getButtonStartUp(),
                gpsm.getButtonStartDown(), gpsi.getSizeStart(), gpsi.getTableSizeStart());
    }

    Stage getLeftLevel(){
        return getStage(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                int level = gamePlaySettings.getCurrentLevel() - 1;
                                if (level > 0 && level <= gamePlaySettings.getCountLevel())
                                    gamePlaySettings.setCurrentLevel(level);
                            }
                        }, gpsm.getButtonAtlasScrolls(), gpsm.getButtonScrollsLeftUp(),
                gpsm.getButtonScrollsLeftDown(), gpsi.getSizeScrolls(), gpsi.getTableSizeLevelLeft());
    }

    Stage getRigthLevel(){
        return getStage(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                int level = gamePlaySettings.getCurrentLevel() + 1;
                                if (level > 0 && level <= gamePlaySettings.getCountLevel())
                                    gamePlaySettings.setCurrentLevel(level);
                            }
                        }, gpsm.getButtonAtlasScrolls(), gpsm.getButtonScrollsRigthUp(),
                gpsm.getButtonScrollsRigthDown(), gpsi.getSizeScrolls(), gpsi.getTableSizeLevelRight());
    }

    Stage getLeftSpeed(){
        return getStage(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                int speed = gamePlaySettings.getCurrentSpeed() / 2;
                                if (speed > 0 && speed <= gamePlaySettings.getMaxSpeed())
                                    gamePlaySettings.setCurrentSpeed(speed);
                            }
                        }, gpsm.getButtonAtlasScrolls(), gpsm.getButtonScrollsLeftUp(),
                gpsm.getButtonScrollsLeftDown(), gpsi.getSizeScrolls(), gpsi.getTableSizeSpeedLeft());
    }

    Stage getRightSpeed(){
        return getStage(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                int speed = gamePlaySettings.getCurrentSpeed() * 2;
                                if (speed > 0 && speed <= gamePlaySettings.getMaxSpeed())
                                    gamePlaySettings.setCurrentSpeed(speed);
                            }
                        }, gpsm.getButtonAtlasScrolls(), gpsm.getButtonScrollsRigthUp(),
                gpsm.getButtonScrollsRigthDown(), gpsi.getSizeScrolls(), gpsi.getTableSizeSpeedRight());
    }

    Stage getBack(){
        return getStage(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                game.setScreen(new Start(game));
                            }
                        }, gpsm.getButtonAtlasBack(), gpsm.getButtonBackUp(),
                gpsm.getButtonBackDown(), gpsi.getSizeBack(), gpsi.getTableSizeBack());
    }

}
