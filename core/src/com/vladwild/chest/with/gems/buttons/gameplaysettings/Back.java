package com.vladwild.chest.with.gems.buttons.gameplaysettings;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Back implements CommandGamePlaySettings {
    private ReceiverGamePlaySettings receiverGamePlaySettings;

    public Back(ReceiverGamePlaySettings receiverGamePlaySettings){
        this.receiverGamePlaySettings = receiverGamePlaySettings;
    }

    @Override
    public Stage getButton() {
        return receiverGamePlaySettings.getBack();
    }
}
