package com.vladwild.chest.with.gems.methods.tasks.labyrinth;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.gameplay.Direction;
import com.vladwild.chest.with.gems.gameplay.StaticObjectField;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Keys extends Labyrinth {
    private Set<GridPoint2> keys = new HashSet<>();         //координаты ключей
    private Set<GridPoint2> keysBuffer;                     //буфер координат ключей

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
}
