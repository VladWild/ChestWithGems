package com.vladwild.chest.with.gems.buttons.start;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class InvokerStart {
    private CommandStart play;
    private CommandStart methods;
    private CommandStart author;
    private CommandStart exit;

    public InvokerStart(CommandStart play, CommandStart methods, CommandStart author, CommandStart exit){
        this.play = play;
        this.methods = methods;
        this.author = author;
        this.exit = exit;
    }

    public Stage getPlay(){
        return play.getButton();
    }

    public Stage getMethods(){
        return methods.getButton();
    }

    public Stage getAuthor(){
        return author.getButton();
    }

    public Stage getExit(){
        return exit.getButton();
    }

}
