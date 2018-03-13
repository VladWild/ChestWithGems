package com.vladwild.chest.with.gems.buttons.gameplaysettings;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class InvokerGamePlaySettings {
    CommandGamePlaySettings start;
    CommandGamePlaySettings leftLevel;
    CommandGamePlaySettings rightLevel;
    CommandGamePlaySettings leftSpeed;
    CommandGamePlaySettings rightSpeed;
    CommandGamePlaySettings back;

    public InvokerGamePlaySettings(CommandGamePlaySettings start,
                                   CommandGamePlaySettings leftLevel,
                                   CommandGamePlaySettings rightLevel,
                                   CommandGamePlaySettings leftSpeed,
                                   CommandGamePlaySettings rightSpeed,
                                   CommandGamePlaySettings back){
        this.start = start;
        this.leftLevel = leftLevel;
        this.rightLevel = rightLevel;
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        this.back = back;
    }

    public Stage getStart(){
        return start.getButton();
    }

    public Stage getLeftLevel(){
        return leftLevel.getButton();
    }

    public Stage getRightLevel(){
        return rightLevel.getButton();
    }

    public Stage getLeftSpeed(){
        return leftSpeed.getButton();
    }

    public Stage getRightSpeed(){
        return rightSpeed.getButton();
    }

    public Stage getBack(){
        return back.getButton();
    }
}
