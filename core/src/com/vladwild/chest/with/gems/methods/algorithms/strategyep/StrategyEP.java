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

    public StrategyEP(SearchStrategyEP sep){
        this.sep = sep;
    }

    /*
    private Set getSet(Node node){
        Set<Node> set = new HashSet<>();
        set.add(node);
        return set;
    }
    */

    private Set addNodeInSet(Node node){
        if (sep.getObject(node.getDown()) == null){
            Set<Node> set = new HashSet<>();
            set.add(node);
            return set;
        } else {
            Set<Node> set = objects.get(sep.getObject(node.getDown()));
            set.add(node);
            
        }
    }

    @Override
    public void start() {
        Map<List, Integer> map = new HashMap<>();         //Map путей и их дистанций

        List<Node> nodes = new ArrayList<>();             //список узлов

        objects.put(sep.getObject(), getSet(new Node())); //ложим в Map:
                                                          //ключ - объект (начальные координаты) человека;
                                                          //значение - множество узелов (дизтанция в нем будет = 0)

        for (Object element : sep.getElements(null)) {   //вводим первые элементы в список узлов
            Node node = new Node<>(null, element);
            node.setChilds(sep.getElements(node.getDown()));
            node.setDistance(sep.getDistance(node.getDown(), null));
            nodes.add(node);

            objects.put(sep.getObject(node.getDown()), getSet(node));   //кладем в Map
        }

        int minDistanceObject = Integer.MAX_VALUE;           //минимальная дистанция до необходимого объекта

        do{
            int minDistance = Integer.MAX_VALUE;

            Node currentNode = new Node();          //текущий узел из которого мы берем требуемый производный узел
            Node requiredChildNode = new Node();    //найденый производный узел

            //нахождение производного элемента с минимальным путем от начального положения до этого производного
            for (Node node : nodes){                                  //проходимся по всем узлам
                for(Object element : node.getChilds()){               //проходимся по производным элементам этих узлов
                    Node nodeChild = new Node(node, element);
                    nodeChild.setDistance(sep.getDistance(node.getDown(), element));
                    if (nodeChild.getDistance() < minDistance){
                        nodeChild.setChilds(sep.getElements(node.getDown(), element));
                        minDistance = nodeChild.getDistance();
                        currentNode = node;
                        requiredChildNode = nodeChild;
                    }
                }
            }

            //удаляем производный узел из списка списка возможных узлов текущего узла
            currentNode.deleteChild(requiredChildNode.getElement());

            //удаляем из списка возможных узлов узел с пустым списком всех возможных производных узлов
            if (currentNode.isEmpotyChild()) nodes.remove(currentNode);

            //если этот найденый производный элемент есть в objects
            if (objects.get(sep.getObject(requiredChildNode.getDown())) != null){
                //если в найденом производном элементе distance < чем в Map (с этими координатами)
                if (requiredChildNode.getDistance() < objects.get(sep.getObject(requiredChildNode.getDown())).getDistance()){
                    //удаляем этот узел в nodes, доставая его из Map'а
                    nodes.remove(objects.get(sep.getObject(requiredChildNode.getDown())));
                    //заменяем его в Map
                    objects.remove(objects.get(sep.getObject(requiredChildNode.getDown())));                    //удаляем
                    objects.put(objects.get(sep.getObject(requiredChildNode.getDown())), requiredChildNode);    //вставляем
                    //добавляем найденый производный узел в спизок всех возможных узлов
                    nodes.add(requiredChildNode);
                }
            } else {
                //кладем узел в Map
                objects.put(sep.getObject(requiredChildNode.getDown()), requiredChildNode);
                //добавляем найденый производный узел в спизок всех возможных узлов
                nodes.add(requiredChildNode);
            }

            for (Node node : nodes) {                                       //проверяем на окончание цикла
                if (sep.isEnd(node.getDown())) {
                    minDistanceObject = node.getDistance();                 //если нашли сундук, то записываем в minDistanceObject значение пути
                    map.put(node.getDown(), node.getDistance());
                    break;
                }
            }
        } while (minDistanceObject == Integer.MAX_VALUE);

        nodes.forEach(node -> System.out.println(node.getDown()));

        //пробегаемся по всем оставшимся узлам, находя другие пути, не превышающие minDistanceObject
        boolean end;

        do{
            end = false;

            int minDistance = Integer.MAX_VALUE;

            Node currentNode = null;          //текущий узел из которого мы берем требуемый производный узел
            Node requiredChildNode = null;    //найденый производный узел

            for (Node node : nodes){                                        //проходимся по всем узлам
                for(Object element : node.getChilds()){                     //проходимся по производным элементам этих узлов
                    Node nodeChild = new Node(node, element);
                    nodeChild.setDistance(sep.getDistance(node.getDown(), element));
                    if (nodeChild.getDistance() < minDistance && nodeChild.getDistance() <= minDistanceObject){
                        nodeChild.setChilds(sep.getElements(node.getDown(), element));
                        currentNode = node;
                        requiredChildNode = nodeChild;
                        end = true;
                    }
                }
            }

            //удаляем производный узел из списка списка возможных узлов текущего узла
            if (requiredChildNode != null) currentNode.deleteChild(requiredChildNode.getElement());

            //удаляем из списка возможных узлов узел с пустым списком всех возможных производных узлов
            if (currentNode != null) if (currentNode.isEmpotyChild()) nodes.remove(currentNode);

            if (requiredChildNode != null) {
                //если этот найденый производный элемент есть в objects
                if (objects.get(sep.getObject(requiredChildNode.getDown())) != null){
                    //если в найденом производном элементе distance <= чем в Map (с этими координатами)
                    if (requiredChildNode.getDistance() < objects.get(sep.getObject(requiredChildNode.getDown())).getDistance()){
                        //удаляем этот узел в nodes, доставая его из Map'а
                        nodes.remove(objects.get(sep.getObject(requiredChildNode.getDown())));
                        //заменяем его в Map
                        objects.remove(objects.get(sep.getObject(requiredChildNode.getDown())));                    //удаляем
                        objects.put(objects.get(sep.getObject(requiredChildNode.getDown())), requiredChildNode);    //вставляем
                        //добавляем найденый производный узел в спизок всех возможных узлов
                        nodes.add(requiredChildNode);
                    }
                } else {
                    //кладем узел в Map
                    objects.put(sep.getObject(requiredChildNode.getDown()), requiredChildNode);
                    //добавляем найденый производный узел в спизок всех возможных узлов
                    nodes.add(requiredChildNode);
                }
            }

            for (Node node : nodes) {                    //проверяем на на то, стоит ли из положить в map или нет
                if (sep.isEnd(node.getDown())) {         //?
                    map.put(node.getDown(), node.getDistance());
                }
            }

            for (Node node : nodes) {                    //вывод
                System.out.println(node.getDistance());
            }

            System.out.println("------------------------------");

        } while (end);

        //находим минимальную дистанцию
        int minDistance = Integer.MAX_VALUE;
        for (Integer distance : map.values()){
            if (distance < minDistance) minDistance = distance;
        }

        //сохраняем все пути с минимальной дистанцией
        for (Node node : nodes){
            if (node.getDistance() == minDistance) sep.save(node.getDown());
        }

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

        public Node(){

        }

        public Node(Node previous, E element) {
            this.previous = previous;
            this.element = element;
        }

        public List getDown() {
            List elements = new ArrayList();
            Node currentNode = this;

            while (true){
                elements.add(currentNode.getElement());
                if (currentNode.getPreviousNode() != null) currentNode = currentNode.getPreviousNode();
                else break;
            }

            Collections.reverse(elements);

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


