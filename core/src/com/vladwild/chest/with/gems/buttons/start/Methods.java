package com.vladwild.chest.with.gems.buttons.start;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vladwild.chest.with.gems.buttons.Command;
import com.vladwild.chest.with.gems.gamestarter.ChestWithGems;

class Methods implements Command {
    private final static String PATH_ATLAS = "buttons\\screens\\start\\start.pack";
    private final static String UP = "button_methods_up";
    private final static String DOWN = "button_methods_down";

    private final GridPoint2 BUTTON_SIZE = new GridPoint2((int) (0.3f * WIDTH), (int) (0.05f * HIGHT));
    private final Rectangle TABLE_SIZE = new Rectangle(0, 0, WIDTH, 0.65f * HIGHT);

    private ChestWithGems game;

    public Methods(ChestWithGems game){
        this.game = game;
    }

    @Override
    public Stage getButton() {
        return buildStage(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new com.vladwild.chest.with.gems.screens.methods.Methods(game, 2, 16, 2, true));

                //game.setScreen(new com.vladwild.chest.with.gems.screens.methods.Methods(game, 2, 16, true));
                //game.setScreen(new com.vladwild.chest.with.gems.screens.methods.Methods(game, 2, 16, false));
            }
        }, PATH_ATLAS, UP, DOWN, BUTTON_SIZE, TABLE_SIZE);
    }
}
