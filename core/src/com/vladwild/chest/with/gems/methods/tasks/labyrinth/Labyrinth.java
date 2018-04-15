package com.vladwild.chest.with.gems.methods.tasks.labyrinth;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.gameplay.Direction;
import com.vladwild.chest.with.gems.gameplay.StaticObjectField;
import com.vladwild.chest.with.gems.methods.tasks.SearchWidth;

import java.util.ArrayList;
import java.util.List;

public abstract class Labyrinth implements SearchWidth {
    protected boolean[][] matrixLogic;                  //логическая матрица

    protected final GridPoint2 START_HUMAN;             //координаты человека при старте игры
    protected GridPoint2 human;                         //координаты человека
    protected GridPoint2 chest;                         //координаты сундука

    protected List<List> ways = new ArrayList<>();      //все требеумые варианты направлений

    protected Labyrinth(StaticObjectField field) {
        matrixLogic = field.getReverseMatrix();
        START_HUMAN = new GridPoint2(getX(field.getHumanPoint()), getY(field.getHumanPoint()));
        human = new GridPoint2(START_HUMAN);
        chest = new GridPoint2(getX(field.getChestPoint()), getY(field.getChestPoint()));
    }

    //новый x
    protected int getX(GridPoint2 point) {
        return point.x;
    }

    //новый y
    protected int getY(GridPoint2 point) {
        return matrixLogic[0].length - point.y - 1;
    }

    //проверка на нахождение human в узловой точке
    protected boolean isNodePoint() {
        return (matrixLogic[human.y + 1][human.x] && matrixLogic[human.y][human.x - 1]) ||
                (matrixLogic[human.y][human.x + 1] && matrixLogic[human.y + 1][human.x]) ||
                (matrixLogic[human.y - 1][human.x] && matrixLogic[human.y][human.x + 1]) ||
                (matrixLogic[human.y][human.x - 1] && matrixLogic[human.y - 1][human.x]);
    }

    //получение списка всех возможных направлений относительно текущего положения объекта
    protected List<Direction> getDirections() {
        List<Direction> directions = new ArrayList<>();

        if (isNodePoint()){                         //относительно текущей узловой точки
            if (matrixLogic[human.y + 1][human.x]) directions.add(Direction.DOWN);
            if (matrixLogic[human.y][human.x + 1]) directions.add(Direction.RIGTH);
            if (matrixLogic[human.y - 1][human.x]) directions.add(Direction.UP);
            if (matrixLogic[human.y][human.x - 1]) directions.add(Direction.LEFT);
        } else {                                    //не в узловой точке
            if (matrixLogic[human.y + 1][human.x] && matrixLogic[human.y - 1][human.x]) {
                directions.add(Direction.UP);
                directions.add(Direction.DOWN);
            }
            if (matrixLogic[human.y][human.x + 1] && matrixLogic[human.y][human.x - 1]){
                directions.add(Direction.RIGTH);
                directions.add(Direction.LEFT);
            }
        }

        return directions;
    }

    //движение человкка по направлению
    protected void move(Direction direction) {
        switch (direction) {
            case DOWN:
                human.y++;
                break;
            case RIGTH:
                human.x++;
                break;
            case UP:
                human.y--;
                break;
            case LEFT:
                human.x--;
                break;
        }
    }

    //гуляем от текущей точки человека до следующей узловой
    protected int walk(Direction direction) {
        int count = 0;

        do {
            move(direction);
            count++;
        } while (!isNodePoint());

        return count;
    }

    @Override
    public List<?> getElements(List<?> elements) {
        if (elements == null) return getDirections();     //если элементов нет, то возвращаем список направлений относительно текущего положения человека

        List<Direction> directions = new ArrayList<>((List<Direction>) elements);  //конвертируем элементы направлений, создавая список этих направлений
        human = new GridPoint2(START_HUMAN);                //присваиваем стартовые координаты человека
        directions.forEach(this::walk);                     //пробегаем весь путь на текущем списке направлений

        return getDirections();
    }

    @Override
    public boolean isEnd(List<?> list) {
        List<Direction> directions = new ArrayList<Direction>((List<Direction>) list);

        human = new GridPoint2(START_HUMAN);        //присваиваем стартовые координаты человека
        directions.forEach(this::walk);             //пробегаем этот вариант направлений

        return human.equals(chest);
    }

    @Override
    public List<List> getRequiredElements() {
        return ways;
    }
}


