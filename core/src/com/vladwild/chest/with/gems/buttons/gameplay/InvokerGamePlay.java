package com.vladwild.chest.with.gems.buttons.gameplay;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class InvokerGamePlay {
    private CommandGamePlay home;
    private CommandGamePlay back;

    public InvokerGamePlay(CommandGamePlay home, CommandGamePlay back){
        this.home = home;
        this.back = back;
    }

    public Stage getHome(){
        return home.getButton();
    }

    public Stage getBack(){
        return back.getButton();
    }



}
