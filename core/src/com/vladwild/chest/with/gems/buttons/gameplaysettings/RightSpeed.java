package com.vladwild.chest.with.gems.buttons.gameplaysettings;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class RightSpeed implements CommandGamePlaySettings{
    private ReceiverGamePlaySettings receiverGamePlaySettings;

    public RightSpeed(ReceiverGamePlaySettings receiverGamePlaySettings){
        this.receiverGamePlaySettings = receiverGamePlaySettings;
    }

    @Override
    public Stage getButton() {
        return receiverGamePlaySettings.getRightSpeed();
    }
}
