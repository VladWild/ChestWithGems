package com.vladwild.chest.with.gems.methods.tasks.labyrinth;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.gameplay.Direction;
import com.vladwild.chest.with.gems.gameplay.StaticObjectField;
import com.vladwild.chest.with.gems.methods.tasks.SearchFunction;
import com.vladwild.chest.with.gems.methods.tasks.SearchStrategyBB;
import com.vladwild.chest.with.gems.methods.tasks.SearchStrategyEP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Keys extends Labyrinth implements SearchFunction, SearchStrategyBB, SearchStrategyEP {
    private Set<GridPoint2> keys = new HashSet<>();         //координаты ключей
    private Set<GridPoint2> keysBuffer;                     //буфер координат ключей

    private boolean flagFunction = false;                   //флаг для оконцания поиска функции

    public Keys(StaticObjectField field) {
        super(field);

        field.getKeysPoints().forEach(key -> keys.add(new GridPoint2(getX(key), getY(key))));
        keysBuffer = new HashSet<>(keys);
    }

    @Override
    protected int walk(Direction direction) {
        int count = 0;

        do {
            move(direction);
            count++;
            keys.forEach(key -> {
                if (human.equals(key)) keysBuffer.remove(key);
            });
        } while (!isNodePoint());

        return count;
    }

    @Override
    public boolean isEnd(List<?> list) {
        List<Direction> directions = new ArrayList<Direction>((List<Direction>) list);

        keysBuffer = new HashSet<>(keys);

        human = new GridPoint2(START_HUMAN);        //присваиваем стартовые координаты человека
        directions.forEach(this::walk);             //пробегаем этот вариант направлений

        return human.equals(chest) && keysBuffer.isEmpty();
    }

    @Override
    public void save(List<?> list) {
        List<Direction> directions = new ArrayList<>((List<Direction>) list);

        human = new GridPoint2(START_HUMAN);        //присваиваем стартовые координаты человека
        keysBuffer = new HashSet<>(keys);

        directions.forEach(direction -> {
            walk(direction);
            if (human.equals(chest) && keysBuffer.isEmpty()) ways.add(directions);      //добавляем правильный вариант
        });
    }

    //функция
    //----------------------------------------------------------------------

    @Override
    public Set<GridPoint2> getObjects() {
        if (!keys.isEmpty()) return keys;

        Set<GridPoint2> chest = new HashSet<>();
        chest.add(super.chest);

        return chest;
    }

    @Override
    public GridPoint2 getMovingObject() {
        return new GridPoint2(human);
    }

    @Override
    public GridPoint2 getMovingObject(Object element) {
        GridPoint2 buffer = new GridPoint2(human);          //заносим в буфер текущие координаты человека
        move((Direction) element);                          //двигаемся человеком по принятому направлению
        GridPoint2 point = new GridPoint2(human);           //сохраняем получившиеся координаты
        human = new GridPoint2(buffer);                     //возвращаем человеку координаты из буфера
        return point;
    }

    @Override
    public void move(Object element) {
        move((Direction) element);
    }

    @Override
    public void delete(Object element) {
        if (!keys.isEmpty()) keys.remove(element);
        else flagFunction = true;
    }

    @Override
    public boolean isEnd() {
        return flagFunction;
    }

    @Override
    public List<?> getElements() {
        return getElements(null);
    }

    //стратегия ветвей и границ
    //-------------------------------------------------------------------------------

    @Override
    public int getDistance(List<?> elements, Object element) {
        List<Direction> directions = new ArrayList<>((List<Direction>) elements);  //конвертируем элементы направлений, создавая список этих направлений
        human = new GridPoint2(START_HUMAN);                //присваиваем стартовые координаты человека

        int path = 0;

        //пробегаем весь путь на текущем списке направлений и считаем сумму пути
        for (Direction direction : directions) {
            path += walk(direction);
        }

        if (element != null) path += walk((Direction) element);

        return path;
    }

    @Override
    public List<?> getElements(List<?> elements, Object element) {
        if (elements == null) return getDirections();     //если элементов нет, то возвращаем список направлений относительно текущего положения человека

        List<Direction> directions = new ArrayList<>((List<Direction>) elements);  //конвертируем элементы направлений, создавая список этих направлений
        human = new GridPoint2(START_HUMAN);                //присваиваем стартовые координаты человека

        directions.forEach(this::walk);                     //пробегаем весь путь на текущем списке направлений
        walk((Direction) element);

        return getDirections();
    }

    //стратегия равных цен
    //---------------------------------------------------------------------------------------

    @Override
    public Object getObject() {
        return new GridPoint2(START_HUMAN);
    }

    @Override
    public Object getObject(List<?> list) {
        List<Direction> directions = new ArrayList<>((List<Direction>) list);

        human = new GridPoint2(START_HUMAN);        //присваиваем стартовые координаты человека

        directions.forEach(super::walk);

        GridPoint2 point = new GridPoint2(human);

        human = new GridPoint2(START_HUMAN);

        return point;
    }
}


