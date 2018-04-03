package com.vladwild.chest.with.gems.methods.algorithms.function;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Double.MAX_VALUE;

public class Euclidean implements Algorithm{
    private Function function;

    private List path = new ArrayList();

    public Euclidean(Function function){
        this.function = function;
    }

    @Override
    public void start() {
        List elements = new ArrayList();

        GridPoint2 ob;
        Set<GridPoint2> objects;

        elements.add(function.getElements(null));

        ob = new GridPoint2(function.getPositionObject());
        objects = function.getPositionObjects();

        double minDistance = MAX_VALUE;
        double distance;
        int index;

        GridPoint2 pp;

        //for (Object  objects)

        for (Object element : elements) {
            //pp = function.getPossiblePosition(element, o1);

            //distance = Math.sqrt(Math.pow(pp.x, 2) + Math.pow(o2.x, 2));

            //if (distance < minDistance) {
                //minDistance = distance;
                //index =
            //}
        }

    }

    @Override
    public List<List> getVariants() {
        List<List> list = new ArrayList<>();
        list.add(path);
        return list;
    }
}
