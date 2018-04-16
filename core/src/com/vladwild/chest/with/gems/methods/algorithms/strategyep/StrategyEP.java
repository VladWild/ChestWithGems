package com.vladwild.chest.with.gems.methods.algorithms.strategyep;

import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.SearchStrategyEP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StrategyEP implements Algorithm{
    SearchStrategyEP sep;

    Map<Object, Set<Node>> objects = new HashMap<>();    //Map объектов (координат) и узлов, находящихся в этих объектах
    int minDistance;                                     //минимальная дистанция текущего пути

    public StrategyEP(SearchStrategyEP sep){
        this.sep = sep;
    }

    private Set getSet(Node node){
        Set<Node> set = new HashSet<>();
        set.add(node);
        return set;
    }

    private Set addNodeInSet(Node node){
        if (objects.get(sep.getObject(node.getDown())) == null){
            Set<Node> set = new HashSet<>();
            set.add(node);
            return set;
        } else {
            Set<Node> set = objects.get(sep.getObject(node.getDown()));
            set.add(node);
            return set;
        }
    }

    private int getDistance(Set<Node> nodes){
        int distance = 0;

        for (Node node : nodes){
            distance = node.getDistance();
        }

        return distance;
    }

    @Override
    public void start() {
        Map<List, Integer> paths = new HashMap<>();       //Map путей и их дистанций

        List<Node> nodes = new ArrayList<>();             //список узлов

        objects.put(sep.getObject(), getSet(new Node())); //кладем в Map:
                                                          //ключ - объект (начальные координаты) человека;
                                                          //значение - множество одного начального узела (дизтанция в нем будет = 0)

        for (Object element : sep.getElements(null)) {   //вводим первые элементы в список узлов
            Node node = new Node<>(null, element);
            node.setChilds(sep.getElements(node.getDown()));
            node.setDistance(sep.getDistance(node.getDown(), null));

            nodes.add(node);

            objects.put(sep.getObject(node.getDown()), addNodeInSet(node));   //кладем в Map
        }

        int distanceRightPath = Integer.MAX_VALUE;           //дистанция правильного пути до необходимого объекта


        do{
            minDistance = Integer.MAX_VALUE;                 //обновляем дистанцию на максимально возможное значение

            if (nodes.isEmpty()) break;

            Node parentNode = nodes.get(0);                  //берем (первый) узел из списки nodes

            //для безопасности
            if (nodes.get(0).getChilds().isEmpty()) {
                nodes.remove(parentNode);
                continue;
            }

            Node childNode = new Node(parentNode,                             //берем производный элемент (первый) у этого узла
                    parentNode.getChilds().get(0));
            childNode.setChilds(sep.getElements(parentNode.getDown(),
                    parentNode.getChilds().get(0)));
            childNode.setDistance(sep.getDistance(parentNode.getDown(),
                    parentNode.getChilds().get(0)));

            parentNode.deleteChild(childNode.getElement());                  //удаляем найденый производный элемент из этого узла
            if (parentNode.isEmpotyChild()) nodes.remove(parentNode);        //удаляем из nodes этот узел
                                                                             //если список всех возможных производных узлов пуст

            if (childNode.getDistance() <= distanceRightPath) {
                //если этот найденый производный элемент есть в objects
                if (objects.get(sep.getObject(childNode.getDown())) != null){
                    //если в найденом производном элементе distance < чем в Map (с этими координатами) - заменяем Set
                    if (childNode.getDistance() < getDistance(objects.get(sep.getObject(childNode.getDown())))){
                        //заменяем его в Map
                        objects.remove(sep.getObject(childNode.getDown()));                                  //удаляем ключ - объект (координаты)
                        objects.put(sep.getObject(childNode.getDown()), addNodeInSet(childNode));    //вставляем
                        //добавляем найденый производный узел в спизок всех возможных узлов
                        nodes.add(childNode);
                    }
                    //если в найденом производном элементе distance = в Map (с этими координатами) - дополняем Set
                    if (childNode.getDistance() == getDistance(objects.get(sep.getObject(childNode.getDown())))){
                        //дополняем его в Map (по факту заменяем, но в методе addNodeInSet этот Set дополняется
                        objects.put(sep.getObject(childNode.getDown()), addNodeInSet(childNode));    //вставляем
                        //добавляем найденый производный узел в спизок всех возможных узлов
                        nodes.add(childNode);
                    }
                } else {
                    //кладем узел в Map
                    objects.put(sep.getObject(childNode.getDown()), addNodeInSet(childNode));
                    //добавляем найденый производный узел в спизок всех возможных узлов
                    nodes.add(childNode);
                }
            }

            for (Node node : nodes) {                                       //проверяем на окончание цикла
                if (sep.isEnd(node.getDown())) {
                    //только в случае distanceRightPath == Integer.MAX_VALUE
                    if (distanceRightPath == Integer.MAX_VALUE)             //если нашли сундук, то записываем в
                        distanceRightPath = node.getDistance();             //distanceRightPath значение пути
                    paths.put(node.getDown(), node.getDistance());
                    break;
                }
            }

            nodes.forEach(node -> {
                if (node.getDistance() < minDistance){
                    minDistance = node.getDistance();
                }
            });

            System.out.println("min = " + minDistance + "; right = " + distanceRightPath);

        } while (minDistance <= distanceRightPath);

        paths.keySet().forEach(System.out::println);

        paths.keySet().forEach(sep::save);
    }

    @Override
    public List<List> getVariants() {
        return sep.getRequiredElements();
    }

    private class Node<E>{
        private Node previous;
        private E element;
        private List<E> childs;
        private int distance;

        private List elements;

        public Node(){

        }

        public Node(Node previous, E element) {
            this.previous = previous;
            this.element = element;
        }

        public List getDown() {
            if (elements == null) {
                elements = new ArrayList();
                Node currentNode = this;

                while (true){
                    elements.add(currentNode.getElement());
                    if (currentNode.getPreviousNode() != null) currentNode = currentNode.getPreviousNode();
                    else break;
                }

                Collections.reverse(elements);
            }

            return elements;
        }

        public void deleteChild(E element){
            childs.remove(element);
        }

        public boolean isEmpotyChild(){
            return childs.isEmpty();
        }

        public void setChilds(List<E> childs) {
            this.childs = childs;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        private Node getPreviousNode(){
            return previous;
        }

        public E getElement(){
            return element;
        }

        public List<E> getChilds(){
            return childs;
        }

        public int getDistance(){
            return distance;
        }

        //сравнение узлов идет по коллекциям путей, которвые они возвращают методом getDown()

        @Override
        public boolean equals(Object o) {
            return getDown().equals(((Node) o).getDown());
        }

        @Override
        public int hashCode() {
            return getDown().hashCode();
        }
    }
}


