package com.vladwild.chest.with.gems.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.InputProcessors.GamePlayInputProcessor;
import com.vladwild.chest.with.gems.location.GamePlayInformation;
import com.vladwild.chest.with.gems.resources.GamePlayManager;

public class Human implements DynamicObject{
    private GamePlayInformation gpi;
    private Animation walkAnimation[];
    private Direction direction;
    protected GridPoint2 positionLogic;
    protected GridPoint2 positionPixel;
    private GamePlayInputProcessor gpip;
    protected boolean matrixLogic[][];
    private float stateTime;

    public Human(GamePlayInformation gpi, GamePlayManager gpm, GridPoint2 positionLogic,
                 GamePlayInputProcessor gpip, boolean matrixLogic[][]) {
        this.gpi = gpi;
        walkAnimation = getWalkAnimation(gpi, gpm);
        direction = Direction.STOP;
        this.positionLogic = new GridPoint2(positionLogic.x, positionLogic.y);
        positionPixel = new GridPoint2(gpi.getSizeBlockPicture().x * positionLogic.x,
                gpi.getSizeBlockPicture().y * positionLogic.y);
        this.gpip = gpip;
        this.matrixLogic = matrixLogic;
        stateTime = gpi.getStateTime();
    }

    //get amimation of human
    private Animation[] getWalkAnimation(GamePlayInformation gpi, GamePlayManager gpm) {
        Texture textures[] = new Texture[gpi.getCountTextures()];
        for (int i = 0; i < gpi.getCountTextures(); i++) {
            textures[i] = new Texture(gpm.getHuman(i + 1));
        }

        Animation[] walkAnimation = new Animation[gpi.getCountDirection()];
        for (int i = 0; i < gpi.getCountDirection(); i++) {
            walkAnimation[i] = new Animation(gpi.getSpeedAnimation(),
                    textures[gpi.getCountTexturesOnSide() * i],
                    textures[gpi.getCountTexturesOnSide() * i + 1],
                    textures[gpi.getCountTexturesOnSide() * i + 2]);
        }

        return walkAnimation;
    }

    //change logic coordinates
    private void changeLogicCoordinates() {
        positionLogic.x = this.positionPixel.x / gpi.getSizeBlockPicture().x;
        positionLogic.y = this.positionPixel.y / gpi.getSizeBlockPicture().y;
    }

    //set position left
    private void setPositionLeft(int pixel) {
        positionPixel.set(positionPixel.x - pixel, positionPixel.y);
        direction = Direction.LEFT;
        changeLogicCoordinates();
    }

    //set position right
    private void setPositionRigth(int pixel) {
        positionPixel.set(this.positionPixel.x + pixel, positionPixel.y);
        direction = Direction.RIGTH;
        changeLogicCoordinates();
    }

    //set position up
    private void setPositionUp(int pixel) {
        positionPixel.set(positionPixel.x, positionPixel.y + pixel);
        direction = Direction.UP;
        changeLogicCoordinates();
    }

    //set position down
    private void setPositionDown(int pixel) {
        positionPixel.set(positionPixel.x, positionPixel.y - pixel);
        direction = Direction.DOWN;
        changeLogicCoordinates();
    }

    //set position stop
    private void setPositionStop() {
        positionPixel.set(positionPixel.x, this.positionPixel.y);
        direction = Direction.STOP;
        if (isCenterLogicalSquare()) changeLogicCoordinates();
    }

    //set inputDirection
    private void setDirection(Direction directionIn, int pixel) {
        switch (directionIn) {
            case LEFT:
                setPositionLeft(pixel);
                break;
            case RIGTH:
                setPositionRigth(pixel);
                break;
            case DOWN:
                setPositionDown(pixel);
                break;
            case UP:
                setPositionUp(pixel);
                break;
        }
    }

    //test on revers direction
    private void testOnReverseDirection(Direction directionIn) {
        if (directionIn == Direction.LEFT && direction == Direction.RIGTH) {
            direction = Direction.LEFT;
        }
        if (directionIn == Direction.RIGTH && direction == Direction.LEFT) {
            direction = Direction.RIGTH;
        }
        if (directionIn == Direction.UP && direction == Direction.DOWN) {
            direction = Direction.UP;
        }
        if (directionIn == Direction.DOWN && direction == Direction.UP) {
            direction = Direction.DOWN;
        }
    }

    //next logic square is free
    private boolean isNextLogicSquareFree(Direction direction) {
        switch (direction) {
            case LEFT:
                return matrixLogic[positionLogic.y][positionLogic.x - 1];
            case RIGTH:
                return matrixLogic[positionLogic.y][positionLogic.x + 1];
            case DOWN:
                return matrixLogic[positionLogic.y - 1][positionLogic.x];
            case UP:
                return matrixLogic[positionLogic.y + 1][positionLogic.x];
            default:
                return false;
        }
    }

    @Override
    public Texture getTexture() {
        stateTime += Gdx.graphics.getDeltaTime();

        switch (direction) {
            case DOWN:
                return  (Texture) walkAnimation[direction.ordinal()].getKeyFrame(stateTime, true);
            case LEFT:
                return (Texture) walkAnimation[direction.ordinal()].getKeyFrame(stateTime, true);
            case RIGTH:
                return (Texture) walkAnimation[direction.ordinal()].getKeyFrame(stateTime, true);
            case UP:
                return (Texture) walkAnimation[direction.ordinal()].getKeyFrame(stateTime, true);
            default:
                return (Texture) walkAnimation[gpip.getDirection().getNumberDirection()].getKeyFrame(stateTime, false);
        }
    }

    @Override
    public GridPoint2 getPositionPixel() {
        return positionPixel;
    }

    //human in center of logic square
    @Override
    public boolean isCenterLogicalSquare() {
        return ((positionPixel.x % gpi.getSizeBlockPicture().x) == 0)
                && ((positionPixel.y % gpi.getSizeBlockPicture().y) == 0) ? true : false;
    }

    //move of human
    @Override
    public void move(Direction directionIn, int pixel) {
        if (isCenterLogicalSquare()) {
            switch (directionIn) {
                case LEFT:
                    if (matrixLogic[positionLogic.y][positionLogic.x - 1]) {
                        setPositionLeft(pixel);
                    } else {
                        if (isNextLogicSquareFree(direction)) {
                            setDirection(direction, pixel);
                        } else {
                            setPositionStop();
                        }
                    }
                    break;
                case RIGTH:
                    if (matrixLogic[positionLogic.y][positionLogic.x + 1]) {
                        setPositionRigth(pixel);
                    } else {
                        if (isNextLogicSquareFree(direction)) {
                            setDirection(direction, pixel);
                        } else {
                            setPositionStop();
                        }
                    }
                    break;
                case DOWN:
                    if (matrixLogic[positionLogic.y - 1][positionLogic.x]) {
                        setPositionDown(pixel);
                    } else {
                        if (isNextLogicSquareFree(direction)) {
                            setDirection(direction, pixel);
                        } else {
                            setPositionStop();
                        }
                    }
                    break;
                case UP:
                    if (matrixLogic[positionLogic.y + 1][positionLogic.x]) {
                        setPositionUp(pixel);
                    } else {
                        if (isNextLogicSquareFree(direction)) {
                            setDirection(direction, pixel);
                        } else {
                            setPositionStop();
                        }
                    }
                    break;
            }
        } else {
            setDirection(direction, pixel);
        }
        testOnReverseDirection(directionIn);
    }

}




