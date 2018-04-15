package com.vladwild.chest.with.gems.methods.algorithms.function;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.SearchFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class EuclideanCoefficient implements Algorithm {
    private SearchFunction function;            //задача
    private double coefficient;                 //коэффициент

    private List elements = new ArrayList();    //накопление элементов

    public EuclideanCoefficient(SearchFunction function, double coefficient){
        this.function = function;
        this.coefficient = coefficient;
    }

    //метод, считающий евклидово расстояние
    private double distance(GridPoint2 o1, GridPoint2 o2){
        return sqrt(pow(o1.x - o2.x, 2) + pow(o1.y - o2.y, 2));
    }

    //возвращает true если расстояние элемента
    //до нужного объекта минимально среди всех элементов
    private boolean isMin(double currentDistanceElement, Map map) {
        for (Object distance : map.values()){
            if (currentDistanceElement > ((Double) distance)){
                return false;
            }
        }

        return true;
    }

    @Override
    public void start() {
        List currentElements = new ArrayList();              //текущие возможные элементы

        Map<GridPoint2, Map> points = new HashMap<>();       //точки, в которых побывал движущийся объект до нахождения текущего объекта

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

                double minDistanceElement = Double.MAX_VALUE;       //минимальная дистанция от движущегося элемента к текущему объекту
                double currentDistanceElement;                      //текущая дистанция по текущему элементу

                Object requiredElement = new Object();              //нужный нам элемент

                Map<Object, Double> distances = new HashMap<>();    //новые вырианты элементов (направлений) и их расстояния в
                                                                    // текущую позицию движущегося объекта
                boolean flag = true;

                for(Object element : currentElements){
                    currentDistanceElement = distance(nearestObject, function.getMovingObject(element));

                    //если в текужей позиции движущегося объекта мы уже находили элементы (направления)
                    //по текужему положению движущегося объекта
                    if (points.get(function.getMovingObject()) != null && flag) {
                        //получаем ту найденую длинну и уменьшаем ее в coefficient раз
                        currentDistanceElement = (double) points.get(function.getMovingObject()).get(element) / coefficient;

                        //защита от очередного зацикливания
                        if (currentDistanceElement < 1d / coefficient)
                            //&& isMin(currentDistanceElement, points.get(function.getMovingObject()))
                            currentDistanceElement =
                                coefficient * distance(nearestObject, function.getMovingObject(element));

                        //кладем новое увеличенное значение этой увеличенной длинны
                        points.get(function.getMovingObject()).put(element, currentDistanceElement);

                        flag = false;
                    //иначе кладем новий вырианты элемента и их расстояния в текущую позицию движущегося объекта
                    } else {
                        distances.put(element, currentDistanceElement);
                    }

                    if (currentDistanceElement < minDistanceElement){
                        minDistanceElement = currentDistanceElement;
                        requiredElement = element;
                    }
                }

                if (flag) points.put(new GridPoint2(function.getMovingObject()), distances);  //сохраняем на найденой точке дистанцию
                                                                                              //от этой точки до нужного нам объекта
                function.move(requiredElement);          //двигаем объект по нужному нам направлению
                elements.add(requiredElement);           //сохраняем его

                System.out.println(function.getMovingObject());

                currentElements.clear();                        //очищаем коллекцию текущих элементов

                //условие выхода
                //if (elements.size() == 100000) break;

                //вывод количества
                System.out.println(elements.size());
            }

            points.clear();                                     //очищаем набор точек и значений дистанции

            function.delete(function.getMovingObject());        //удаляем элемент на позиции координат человека

            //условие выхода
            //if (elements.size() == 100000) break;
        }
    }

    @Override
    public List<List> getVariants() {
        List list = new ArrayList();

        list.add(elements);

        return list;
    }
}
