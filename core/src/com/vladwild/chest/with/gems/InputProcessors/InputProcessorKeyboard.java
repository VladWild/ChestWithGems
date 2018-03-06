package com.vladwild.chest.with.gems.InputProcessors;

import com.badlogic.gdx.InputProcessor;
import com.vladwild.chest.with.gems.gameplay.Direction;

public class InputProcessorKeyboard extends GamePlayInputProcessor implements InputProcessor {

    public InputProcessorKeyboard(Direction directionStart){
        super(directionStart);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case 19:
                direction = Direction.UP;
                break;
            case 20:
                direction = Direction.DOWN;
                break;
            case 21:
                direction = Direction.LEFT;
                break;
            case 22:
                direction = Direction.RIGTH;
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
