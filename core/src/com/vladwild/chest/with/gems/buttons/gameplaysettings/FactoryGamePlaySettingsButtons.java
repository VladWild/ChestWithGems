package com.vladwild.chest.with.gems.buttons.gameplaysettings;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vladwild.chest.with.gems.buttons.Command;
import com.vladwild.chest.with.gems.buttons.gameplaysettings.settable.LeftLevel;
import com.vladwild.chest.with.gems.buttons.gameplaysettings.settable.LeftSpeed;
import com.vladwild.chest.with.gems.buttons.gameplaysettings.settable.RightLevel;
import com.vladwild.chest.with.gems.buttons.gameplaysettings.settable.RightSpeed;
import com.vladwild.chest.with.gems.gamestarter.ChestWithGems;
import com.vladwild.chest.with.gems.screens.GamePlaySettings;

import java.util.ArrayList;
import java.util.List;

public enum FactoryGamePlaySettingsButtons {
    START {
        @Override
        Command getCommand(ChestWithGems game, GamePlaySettings settings) {
            return new Start(game, settings);
        }
    }, BACK {
        @Override
        Command getCommand(ChestWithGems game, GamePlaySettings settings) {
            return new Back(game);
        }
    }, LEFT_LEVEL {
        @Override
        Command getCommand(ChestWithGems game, GamePlaySettings settings) {
            return new LeftLevel(settings);
        }
    }, RIGHT_LEVEL {
        @Override
        Command getCommand(ChestWithGems game, GamePlaySettings settings) {
            return new RightLevel(settings);
        }
    }, LEFT_SPEED {
        @Override
        Command getCommand(ChestWithGems game, GamePlaySettings settings) {
            return new LeftSpeed(settings);
        }
    }, RIGHT_SPEED {
        @Override
        Command getCommand(ChestWithGems game, GamePlaySettings settings) {
            return new RightSpeed(settings);
        }
    };

    abstract Command getCommand(ChestWithGems game, GamePlaySettings settings);

    public static List<Stage> getButtons(ChestWithGems game, GamePlaySettings settings){
        List<Stage> buttons = new ArrayList<>();

        for (FactoryGamePlaySettingsButtons command : values()) {
            buttons.add(command.getCommand(game, settings).getButton());
        }

        return buttons;
    }
}
