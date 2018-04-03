package com.vladwild.chest.with.gems.methods.tasks.labyrinth;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.gameplay.Direction;
import com.vladwild.chest.with.gems.gameplay.StaticObjectField;
import com.vladwild.chest.with.gems.methods.tasks.Function;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Chest extends Labyrinth implements Function{
    private Set<GridPoint2> chest = new HashSet<>();

    public Chest(StaticObjectField field) {
        super(field);
        //chest.add()
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

    @Override
    public List<?> getElements(GridPoint2 point) {
        if (point == null) return getDirections();

        human = new GridPoint2(point);

        return getDirections();
    }

    @Override
    public GridPoint2 getPositionObject() {
        return human;
    }

    @Override
    public Set<GridPoint2> getPositionObjects() {
        return chest;
    }

    @Override
    public GridPoint2 getPossiblePosition(Object element, GridPoint2 point) {
        Direction direction = (Direction) element;

        human = new GridPoint2(point);

        do {
            move(direction);
        } while (!isNodePoint());

        return human;
    }
}
