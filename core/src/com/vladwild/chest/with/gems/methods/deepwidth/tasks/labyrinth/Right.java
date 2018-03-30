package com.vladwild.chest.with.gems.methods.deepwidth.tasks.labyrinth;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.gameplay.Direction;
import com.vladwild.chest.with.gems.gameplay.StaticObjectField;

import java.util.ArrayList;
import java.util.List;

public class Right extends Labyrinth{

    public Right(StaticObjectField field) {
        super(field);
    }

    @Override
    public boolean isEnd(List<List> lists) {
        rightWays = new ArrayList<>();

        for (List list : lists) {
            List<Direction> directions = new ArrayList<Direction>((List<Direction>) list);

            human = new GridPoint2(START_HUMAN);        //присваиваем стартовые координаты человека
            for (Direction direction : directions) {    //ходим на каждой итерации до тех пор, пока не попадем в узел
                do{
                    move(direction);
                } while (!isNodePoint());

                if (human.equals(chest)) {
                    rightWays.add(directions);     //добавляем правильный вариант направлений
                }
            }
        }

        return rightWays.isEmpty();
    }

    @Override
    public List<List> getRequiredElements() {
        return rightWays;
    }
}
