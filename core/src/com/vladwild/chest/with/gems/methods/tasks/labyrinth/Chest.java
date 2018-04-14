package com.vladwild.chest.with.gems.methods.tasks.labyrinth;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.gameplay.Direction;
import com.vladwild.chest.with.gems.gameplay.StaticObjectField;

import java.util.ArrayList;
import java.util.List;

public class Chest extends Labyrinth {

    public Chest(StaticObjectField field) {
        super(field);
    }

    @Override
    public void save(List<?> list) {
        List<Direction> directions = new ArrayList<>((List<Direction>) list);

        human = new GridPoint2(START_HUMAN);        //присваиваем стартовые координаты человека

        directions.forEach(direction -> {
            walk(direction);
            if (human.equals(chest)) ways.add(directions);
        });
    }
}
