package com.vladwild.chest.with.gems.methods.searchwidth;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.gameplay.Direction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Labyrinth implements Task{
    private boolean[][] matrixLogic;   //логическая матрица

    private GridPoint2 human;       //координаты человека
    private final GridPoint2 START_HUMAN;   //координаты человека при старте игры
    private GridPoint2 chest;       //координаты координаты сундука
    private Set<GridPoint2> keys;   //координаты ключей

    Direction currentDirection;

    private boolean flag;           //флаг остановки

    private List<List> right;

    public Labyrinth(boolean[][] matrixLogic, GridPoint2 human, GridPoint2 chest,
                     Set<GridPoint2> keys) {
        this.matrixLogic = getReverseMatrix(matrixLogic);

        this.human = new GridPoint2(matrixLogic[0].length - human.y - 1, human.x);
        this.chest = new GridPoint2(matrixLogic[0].length - chest.y - 1, chest.x);

        this.keys = new HashSet<GridPoint2>();
        int i = 0;
        for (GridPoint2 key : keys) {
            this.keys.add(new GridPoint2(matrixLogic[0].length - key.y - 1, key.x));
        }

        //----------------------------
        START_HUMAN =  new GridPoint2(this.human.x, this.human.y);
        flag = true;

        right = new ArrayList<>();
    }

    //получаем обратную матрицу для соответствия соответствия координат человека и поля
    private boolean[][] getReverseMatrix(boolean[][] matrixLogic){
        boolean[][] reverseMatrix = new boolean[matrixLogic.length][matrixLogic[0].length];

        for (int i = 0; i < matrixLogic.length; i++){
            reverseMatrix[(matrixLogic.length - 1) - i] = matrixLogic[i];
        }

        return reverseMatrix;
    }

    //проверка на нахождение human в узловой точке
    private boolean isNodePoint(){
        return (matrixLogic[human.x + 1][human.y] && matrixLogic[human.x][human.y - 1]) ||
                (matrixLogic[human.x][human.y + 1] && matrixLogic[human.x + 1][human.y]) ||
                (matrixLogic[human.x - 1][human.y] && matrixLogic[human.x][human.y + 1]) ||
                (matrixLogic[human.x][human.y - 1] && matrixLogic[human.x - 1][human.y]);
    }

    //получение очереди всех возможных направлений относительно текущей узловой точки
    private List<Direction> getDirections(){
        List<Direction> directions = new ArrayList<>();

        if(matrixLogic[human.x + 1][human.y]) directions.add(Direction.DOWN);
        if(matrixLogic[human.x][human.y + 1]) directions.add(Direction.RIGTH);
        if(matrixLogic[human.x - 1][human.y]) directions.add(Direction.UP);
        if(matrixLogic[human.x][human.y - 1]) directions.add(Direction.LEFT);

        return directions;
    }

    //движение человкка по текущему направлению
    private void move(){
        switch (currentDirection){
            case DOWN:
                human.x++;
                break;
            case RIGTH:
                human.y++;
                break;
            case UP:
                human.x--;
                break;
            case LEFT:
                human.y--;
                break;
        }
    }

    @Override
    public List<Direction> getElementsStart() { //предполагается что на старте герой находится в узловой точке и не на сундуке
        return getDirections();
    }

    @Override
    public List<?> getElements(List<?> list) {
        List<Direction> currentList = new ArrayList<Direction>((List<Direction>) list);

        human = new GridPoint2(START_HUMAN);        //присваиваем стартовые координаты человека

        for (Direction direction : currentList) {   //ходим на каждой итерации до тех пор, пока не попадем в узел
            currentDirection = direction;
            do{
                move();
            } while (!isNodePoint());
        }

        return getDirections();
    }



    @Override
    public boolean isEnd(List<List> lists) {
        //без ключей
        /*
        human = new GridPoint2(START_HUMAN);        //присваиваем стартовые координаты человека

        int size = 0;

        for (List list : lists) {
            List<Direction> currentList = new ArrayList<Direction>((List<Direction>) list);

            human = new GridPoint2(START_HUMAN);        //присваиваем стартовые координаты человека
            for (Direction direction : currentList) {   //ходим на каждой итерации до тех пор, пока не попадем в узел
                currentDirection = direction;

                do{
                    move();
                } while (!isNodePoint());

                if (human.equals(chest)) {
                    flag = false;   //предполагается, что человек может найти сундук только в узле
                    right.add(currentList); //добавляем правильный вариант
                }
            }
            size = currentList.size();
        }
        System.out.println(size + " " + lists.size());

        return flag;
        */

        //с ключами
        human = new GridPoint2(START_HUMAN);        //присваиваем стартовые координаты человека

        int size = 0;
        Set<GridPoint2> keyBuffer = new HashSet<>(keys);

        for (List list : lists) {
            List<Direction> currentList = new ArrayList<Direction>((List<Direction>) list);

            human = new GridPoint2(START_HUMAN);        //присваиваем стартовые координаты человека

            for (Direction direction : currentList) {   //ходим на каждой итерации до тех пор, пока не попадем в узел
                currentDirection = direction;

                do{
                    move();
                    for (GridPoint2 key : keys){
                        if (human.equals(key)) {
                            keyBuffer.remove(key);
                        }
                    }
                } while (!isNodePoint());

                if (human.equals(chest) && keyBuffer.isEmpty()) {
                    flag = false;   //предполагается, что человек может найти сундук только в узле
                    right.add(currentList); //добавляем правильный вариант
                }
            }
            size = currentList.size();
            keyBuffer = new HashSet<>(keys);
        }
        System.out.println(size + " " + lists.size() + " " + right.size());

        return flag;
    }

    @Override
    public List<List> getRightVariants() {
        return right;
    }
}

