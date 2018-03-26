package com.vladwild.chest.with.gems.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.location.GamePlayInformation;
import com.vladwild.chest.with.gems.resources.GamePlayManager;
import com.vladwild.chest.with.gems.utilities.CreatingLevelTexture;
import com.vladwild.chest.with.gems.utilities.ReadMatrixFromCSVFile;

import java.util.HashSet;
import java.util.Set;

public class Field implements StaticObjectField{
    private int[][] matrix;
    private boolean[][] matrixLogic;
    private Texture texture;
    private GridPoint2 positionPixel;

    private Set<GridPoint2> keysPoints;
    private GridPoint2 humanPoint;
    private GridPoint2 chestPoint;

    public Field(GamePlayInformation gpi, GamePlayManager gpm, int level){
        matrix = ReadMatrixFromCSVFile.getMatrix(gpi, gpm, level);
        texture = CreatingLevelTexture.getTexture(gpi, gpm, matrix);

        positionPixel = new GridPoint2(gpi.getFieldRect().x, gpi.getFieldRect().y);

        pointsObject(gpi);
        createMatrixLogic(gpi);
    }

    private void pointsObject(GamePlayInformation gpi){
        keysPoints = new HashSet<GridPoint2>();

        for (int i = 0; i < gpi.getLogicSizeField().x; i++) {
            for (int j = 0; j < gpi.getLogicSizeField().y; j++) {
                if (matrix[j][i] == gpi.getNumberKey()) keysPoints.add(new GridPoint2(i, j));
                if (matrix[j][i] == gpi.getNumberHuman()) humanPoint = new GridPoint2(i, j);
                if (matrix[j][i] == gpi.getNumberChest()) chestPoint = new GridPoint2(i, j);
            }
        }
    }

    private void createMatrixLogic(GamePlayInformation gpi){
        matrixLogic = new boolean[gpi.getLogicSizeField().x][gpi.getLogicSizeField().y];

        for (int i = 0; i < gpi.getLogicSizeField().x; i++) {
            for (int j = 0; j < gpi.getLogicSizeField().y; j++) {
                matrixLogic[j][i] = ((matrix[j][i] == gpi.getNumberBG())
                        || (matrix[j][i] > (gpi.getCountElementInField() - 1))) ? true : false;
            }
        }
    }

    @Override
    public Texture getTexture(){
        return texture;
    }

    @Override
    public GridPoint2 getPositionPixel(){
        return positionPixel;
    }

    @Override
    public Set<GridPoint2> getKeysPoints() {
        return keysPoints;
    }

    @Override
    public GridPoint2 getHumanPoint() {
        return humanPoint;
    }

    @Override
    public GridPoint2 getChestPoint() {
        return chestPoint;
    }

    @Override
    public boolean[][] getMatrixLogic(){
        return matrixLogic;
    }
}


