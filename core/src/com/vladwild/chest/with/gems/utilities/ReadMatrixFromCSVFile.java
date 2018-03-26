package com.vladwild.chest.with.gems.utilities;

import com.badlogic.gdx.files.FileHandle;
import com.vladwild.chest.with.gems.location.GamePlayInformation;
import com.vladwild.chest.with.gems.resources.GamePlayManager;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadMatrixFromCSVFile {
    private static final String DELIMITER = ";";
    private static String line = "";

    public static int[][] getMatrix(GamePlayInformation gpl, GamePlayManager gpm, int level){
        int[][] matrix = new int[gpl.getLogicSizeField().x][gpl.getLogicSizeField().y];
        FileHandle file = gpm.getFileHandleOfCSVFile(level);
        BufferedReader fileReader = new BufferedReader(file.reader());

        int i = gpl.getLogicSizeField().y - 1;
        try {
            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(DELIMITER);
                int j = 0;
                for (String elem : tokens) {
                    matrix[i][j++] = Integer.valueOf(elem);
                }
                i--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //удалить
        /*
        for (i = gpl.getLogicSizeField().y - 1; i > -1; i--) {
            for (int j = 0; j < 21; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        */
        //---------------------------

        return matrix;
    }
}
