package com.vladwild.chest.with.gems.buttons.gameplay;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Home implements CommandGamePlay {
    private ReceiverGamePlay receiverGamePlay;

    public Home(ReceiverGamePlay receiverGamePlay){
        this.receiverGamePlay = receiverGamePlay;
    }

    @Override
    public Stage getButton() {
        return receiverGamePlay.getHome();
    }
}
