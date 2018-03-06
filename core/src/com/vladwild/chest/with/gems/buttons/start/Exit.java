package com.vladwild.chest.with.gems.buttons.start;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Exit implements CommandStart {
    ReceiverStart receiverStart;

    public Exit(ReceiverStart receiverStart){
        this.receiverStart = receiverStart;
    }

    @Override
    public Stage getButton() {
        return receiverStart.getExit();
    }
}
