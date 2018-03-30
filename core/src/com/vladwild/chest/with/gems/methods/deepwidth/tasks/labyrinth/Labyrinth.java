package com.vladwild.chest.with.gems.methods.deepwidth.tasks.labyrinth;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.gameplay.Direction;
import com.vladwild.chest.with.gems.gameplay.StaticObjectField;
import com.vladwild.chest.with.gems.methods.deepwidth.tasks.Task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Labyrinth implements Task{
    protected boolean[][] matrixLogic;        //логическая матрица

    protected final GridPoint2 START_HUMAN;   //координаты человека при старте игры
    protected GridPoint2 human;               //координаты человека
    protected GridPoint2 chest;               //координаты сундука
    protected Set<GridPoint2> keys;           //координаты ключей

    protected List<List> allWays;             //все варианты направлений
    protected List<List> rightWays;           //все требуемые варианты направлений

    protected Labyrinth(StaticObjectField field){
        matrixLogic = field.getReverseMatrix();

        START_HUMAN = new GridPoint2(matrixLogic[0].length - field.getHumanPoint().y - 1,
                field.getHumanPoint().x);

        human = new GridPoint2(START_HUMAN);
        chest = new GridPoint2(matrixLogic[0].length - field.getChestPoint().y - 1,
                field.getChestPoint().x);
        keys = new HashSet<>();
        for (GridPoint2 key : field.getKeysPoints()) {
            keys.add(new GridPoint2(matrixLogic[0].length - key.y - 1, key.x));
        }
    }

    //проверка на нахождение human в узловой точке
    protected boolean isNodePoint(){
        return (matrixLogic[human.x + 1][human.y] && matrixLogic[human.x][human.y - 1]) ||
                (matrixLogic[human.x][human.y + 1] && matrixLogic[human.x + 1][human.y]) ||
                (matrixLogic[human.x - 1][human.y] && matrixLogic[human.x][human.y + 1]) ||
                (matrixLogic[human.x][human.y - 1] && matrixLogic[human.x - 1][human.y]);
    }

    //получение списка всех возможных направлений относительно текущей узловой точки
    protected List<Direction> getDirections(){
        List<Direction> directions = new ArrayList<>();

        if(matrixLogic[human.x + 1][human.y]) directions.add(Direction.DOWN);
        if(matrixLogic[human.x][human.y + 1]) directions.add(Direction.RIGTH);
        if(matrixLogic[human.x - 1][human.y]) directions.add(Direction.UP);
        if(matrixLogic[human.x][human.y - 1]) directions.add(Direction.LEFT);

        return directions;
    }

    //движение человкка по направлению
    protected void move(Direction direction){
        switch (direction){
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
    public List<?> getElements(List<?> list) {
        if(list.isEmpty()) return getDirections();

        List<Direction> directions = new ArrayList<Direction>((List<Direction>) list);

        human = new GridPoint2(START_HUMAN);        //присваиваем стартовые координаты человека

        for (Direction direction : directions) {   //ходим на каждой итерации до тех пор, пока не попадем в узел
            do{
                move(direction);
            } while (!isNodePoint());
        }

        return getDirections();
    }
}
