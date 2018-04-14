package com.vladwild.chest.with.gems.methods.tasks.labyrinth;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.gameplay.Direction;
import com.vladwild.chest.with.gems.gameplay.StaticObjectField;
import com.vladwild.chest.with.gems.methods.tasks.SearchFunction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Keys extends Labyrinth implements SearchFunction{
    private Set<GridPoint2> keys = new HashSet<>();         //координаты ключей
    private Set<GridPoint2> keysBuffer;                     //буфер координат ключей

    private boolean flagFunction = false;                   //флаг для оконцания поиска функции

    public Keys(StaticObjectField field) {
        super(field);

        field.getKeysPoints().forEach(key -> keys.add(new GridPoint2(getX(key), getY(key))));
        keysBuffer = new HashSet<>(keys);
    }

    @Override
    protected void walk(Direction direction) {
        do {
            move(direction);
            keys.forEach(key -> {
                if (human.equals(key)) keysBuffer.remove(key);
            });
        } while (!isNodePoint());
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
}

