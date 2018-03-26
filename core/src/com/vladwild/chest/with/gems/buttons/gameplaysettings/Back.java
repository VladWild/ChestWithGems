package com.vladwild.chest.with.gems.buttons.gameplaysettings;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vladwild.chest.with.gems.buttons.Command;
import com.vladwild.chest.with.gems.gamestarter.ChestWithGems;
import com.vladwild.chest.with.gems.screens.Start;

class Back implements Command {
    private final static String PATH_ATLAS = "buttons\\all\\back\\back.pack";
    private final static String UP = "back_up";
    private final static String DOWN = "back_down";

    private final GridPoint2 BUTTON_SIZE = new GridPoint2((int) ((3f/ 21f) * WIDTH), (int) ((3f/ 31f) * HIGHT));
    private final Rectangle TABLE_SIZE = new Rectangle((( 17f/ 21f) * WIDTH), ((1f/ 31f) * HIGHT), ((3f/ 21f) * WIDTH), ((3f/ 31f) * HIGHT));

    private ChestWithGems game;

    public Back(ChestWithGems game){
        this.game = game;
    }

    @Override
    public Stage getButton() {
        return buildStage(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Start(game));
            }
        }, PATH_ATLAS, UP, DOWN, BUTTON_SIZE, TABLE_SIZE);
    }
}
