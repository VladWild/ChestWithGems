package com.vladwild.chest.with.gems.buttons.gameplaysettings.settable;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.vladwild.chest.with.gems.buttons.Command;
import com.vladwild.chest.with.gems.screens.GamePlaySettings;

public class RightSpeed implements Command {
    private final static String PATH_ATLAS = "buttons\\all\\right\\right.pack";
    private final static String UP = "right_up";
    private final static String DOWN = "right_down";

    private final GridPoint2 BUTTON_SIZE = new GridPoint2((int) (0.08f * WIDTH), (int) (0.06f * HIGHT));
    private final Rectangle TABLE_SIZE = new Rectangle(0.5f * WIDTH, 0.2f * HIGHT, 0.3f * WIDTH, 0.35f * HIGHT);

    private GamePlaySettings settings;

    public RightSpeed(GamePlaySettings settings){
        this.settings = settings;
    }

    @Override
    public Stage getButton() {
        return buildStage(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int speed = settings.getCurrentSpeed() * 2;
                if (speed > 0 && speed <= settings.getMaxSpeed())
                    settings.setCurrentSpeed(speed);
            }
        }, PATH_ATLAS, UP, DOWN, BUTTON_SIZE, TABLE_SIZE);
    }
}
