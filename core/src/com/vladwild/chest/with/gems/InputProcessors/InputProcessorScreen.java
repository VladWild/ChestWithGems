package com.vladwild.chest.with.gems.InputProcessors;

import com.badlogic.gdx.InputProcessor;
import com.vladwild.chest.with.gems.gameplay.Direction;

public class InputProcessorScreen extends GamePlayInputProcessor implements InputProcessor {
    private int screenX0;
    private int screenY0;
    private int screenX;
    private int screenY;
    private int offset;
    private int pointer;

    public InputProcessorScreen(int offset, Direction directionStart){
        super(directionStart);
        this.offset = offset;
    }

    @Override
    public boolean keyDown(int keycode) {
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
        screenX0 = -1;
        screenY0 = -1;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX0 = -1;
        screenY0 = -1;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if ((screenX0 == -1) && (screenY0 == -1)) {
            this.screenX0 = screenX;
            this.screenY0 = screenY;
        }
        this.screenX = screenX;
        this.screenY = screenY;
        this.pointer = pointer;
        if (this.screenX - this.screenX0 > offset) {
            this.screenX0 = screenX;
            this.screenY0 = screenY;
            direction = Direction.RIGTH;
        }
        if (this.screenX - this.screenX0 < -offset) {
            this.screenX0 = screenX;
            this.screenY0 = screenY;
            direction = Direction.LEFT;
        }
        if (this.screenY - this.screenY0 > offset) {
            this.screenX0 = screenX;
            this.screenY0 = screenY;
            direction = Direction.DOWN;
        }
        if (this.screenY - this.screenY0 < -offset) {
            this.screenX0 = screenX;
            this.screenY0 = screenY;
            direction = Direction.UP;
        }
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
