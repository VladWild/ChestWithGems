package com.vladwild.chest.with.gems.buttons.start;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vladwild.chest.with.gems.buttons.Command;
import com.vladwild.chest.with.gems.gamestarter.ChestWithGems;

import java.util.ArrayList;
import java.util.List;

public enum FactoryStartButtons {
    START {
        @Override
        Command getCommand(ChestWithGems game) {
            return new Play(game);
        }
    }, METHODS {
        @Override
        Command getCommand(ChestWithGems game) {
            return new Methods(game);
        }
    }, AUTHOR {
        @Override
        Command getCommand(ChestWithGems game) {
            return new Author(game);
        }
    }, EXIT {
        @Override
        Command getCommand(ChestWithGems game) {
            return new Exit();
        }
    };

    abstract Command getCommand(ChestWithGems game);

    public static List<Stage> getButtons(ChestWithGems game){
        List<Stage> buttons = new ArrayList<>();

        for (FactoryStartButtons command : values()) {
            buttons.add(command.getCommand(game).getButton());
        }

        return buttons;
    }
}

