package com.vladwild.chest.with.gems.buttons.gameplaysettings;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vladwild.chest.with.gems.buttons.Command;
import com.vladwild.chest.with.gems.gamestarter.ChestWithGems;
import com.vladwild.chest.with.gems.screens.GamePlay;
import com.vladwild.chest.with.gems.screens.GamePlaySettings;

class Start implements Command {
    private final static String PATH_ATLAS = "buttons\\screens\\gameplaysettings\\start.pack";
    private final static String UP = "start_up";
    private final static String DOWN = "start_down";

    private final GridPoint2 BUTTON_SIZE = new GridPoint2((int) (0.3f * WIDTH), (int) (0.05f * HIGHT));
    private final Rectangle TABLE_SIZE = new Rectangle(0, 0.4f * HIGHT, WIDTH, 0.6f * HIGHT);

    private ChestWithGems game;
    private GamePlaySettings settings;

    public Start(ChestWithGems game, GamePlaySettings settings){
        this.game = game;
        this.settings = settings;
    }

    @Override
    public Stage getButton() {
        return buildStage(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GamePlay(game, settings.getCurrentLevel(), settings.getCurrentSpeed()));
            }
        }, PATH_ATLAS, UP, DOWN, BUTTON_SIZE, TABLE_SIZE);
    }
}
