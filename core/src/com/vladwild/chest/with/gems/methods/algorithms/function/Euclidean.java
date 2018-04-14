package com.vladwild.chest.with.gems.methods.algorithms.function;


import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.SearchFunction;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class Euclidean implements Algorithm{
    private SearchFunction function;            //задача

    private List elements = new ArrayList();    //накопление элементов

    public Euclidean(SearchFunction function){
        this.function = function;
    }

    //метод, считающий евклидово расстояние
    private double distance(GridPoint2 o1, GridPoint2 o2){
        return sqrt(pow(o1.x - o2.x, 2) + pow(o1.y - o2.y, 2));
    }

    @Override
    public void start() {
        List currentElements = new ArrayList();            //текущие возможные элементы

        GridPoint2 nearestObject = new GridPoint2();       //координаты ближайшего объекта

        //цикл
        while (!function.isEnd()){
            double minDistance = Double.MAX_VALUE;      //минимальная дистанция до самого ближайшего объекта
            double currentDistance;                     //дистанция до текущего объекта

            for (GridPoint2 object : function.getObjects()) {                //определение ближайшего объекта по текущему расстоянию от человека
                currentDistance = distance(function.getMovingObject(), object);
                if (currentDistance < minDistance){
                    nearestObject = new GridPoint2(object);
                    minDistance = currentDistance;
                }
            }

            while (!function.getMovingObject().equals(nearestObject)){
                currentElements.addAll(function.getElements());
                System.out.println(currentElements);

                double minDistanceElement = Double.MAX_VALUE;   //минимальная дистанция от движущегося элемента к текущему объекту
                double currentDistanceElement;                  //текущая дистанция по текущему элементу

                Object requiredElement = new Object();          //нужный нам элемент

                for(Object element : currentElements){
                    currentDistanceElement = distance(nearestObject, function.getMovingObject(element));
                    if (currentDistanceElement < minDistanceElement){
                        minDistanceElement =  currentDistanceElement;
                        requiredElement = element;
                    }
                }

                function.move(requiredElement);                 //двигаем объект по нужному нам направлению
                elements.add(requiredElement);                  //сохраняем его

                System.out.println(function.getMovingObject());

                currentElements.clear();                        //очищаем коллекцию текущих элементов
            }

            function.delete(function.getMovingObject());        //удаляем элемент на позиции координат человека
        }
    }

    @Override
    public List<List> getVariants() {
        List list = new ArrayList();

        list.add(elements);

        return list;
    }
}


