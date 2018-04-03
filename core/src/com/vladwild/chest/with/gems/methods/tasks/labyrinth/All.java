package com.vladwild.chest.with.gems.methods.tasks.labyrinth;

import com.vladwild.chest.with.gems.gameplay.Direction;
import com.vladwild.chest.with.gems.gameplay.StaticObjectField;

import java.util.ArrayList;
import java.util.List;

public class All extends Labyrinth {

    public All(StaticObjectField field) {
        super(field);
    }

    @Override
    public void save(List<?> list) {
        ways.add(new ArrayList<>((List<Direction>) list));
    }
}



