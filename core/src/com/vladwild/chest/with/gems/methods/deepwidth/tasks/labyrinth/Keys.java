package com.vladwild.chest.with.gems.methods.deepwidth.tasks.labyrinth;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.gameplay.Direction;
import com.vladwild.chest.with.gems.gameplay.StaticObjectField;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Keys extends Labyrinth{

    public Keys(StaticObjectField field) {
        super(field);
    }

    @Override
    public boolean isEnd(List<List> lists) {
        rightWays = new ArrayList<>();

        Set<GridPoint2> keyBuffer = new HashSet<>(keys);

        for (List list : lists) {
            List<Direction> directions = new ArrayList<Direction>((List<Direction>) list);

            human = new GridPoint2(START_HUMAN);        //присваиваем стартовые координаты человека

            for (Direction direction : directions) {    //ходим на каждой итерации до тех пор, пока не попадем в узел
                do{
                    move(direction);
                    for (GridPoint2 key : keys){
                        if (human.equals(key)) {
                            keyBuffer.remove(key);
                        }
                    }
                } while (!isNodePoint());

                if (human.equals(chest) && keyBuffer.isEmpty()) {
                    rightWays.add(directions);      //добавляем правильный вариант
                }
            }
            keyBuffer = new HashSet<>(keys);
        }

        return rightWays.isEmpty();
    }

    @Override
    public List<List> getRequiredElements() {
        return rightWays;
    }
}
