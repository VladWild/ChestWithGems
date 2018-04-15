package com.vladwild.chest.with.gems.methods.algorithms.strategybb;

import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.SearchStrategyBB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrategyBB implements Algorithm{
    SearchStrategyBB sbb;

    public StrategyBB(SearchStrategyBB sbb){
        this.sbb = sbb;
    }


    @Override
    public void start() {
        List<Node> nodes = new ArrayList<>();       //список узлов

        Map<List, Integer> map = new HashMap<>();   //Map путей и их дистанций

        for (Object element : sbb.getElements(null)) {   //вводим первые элементы в список узлов
            Node node = new Node<>(null, element);
            node.setChilds(sbb.getElements(node.getDown()));
            node.setDistance(sbb.getDistance(node.getDown(), null));
            nodes.add(node);
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
                    nodeChild.setDistance(sbb.getDistance(node.getDown(), element));
                    if (nodeChild.getDistance() < minDistance){
                        nodeChild.setChilds(sbb.getElements(node.getDown(), element));
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

            //добавляем найденый производный узел в спизок всех возможных узлов
            nodes.add(requiredChildNode);

            for (Node node : nodes) {                                       //проверяем на окончание цикла
                if (sbb.isEnd(node.getDown())) {
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
                    nodeChild.setDistance(sbb.getDistance(node.getDown(), element));
                    if (nodeChild.getDistance() < minDistance && nodeChild.getDistance() <= minDistanceObject){
                        nodeChild.setChilds(sbb.getElements(node.getDown(), element));
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

            //добавляем найденый производный узел в спизок всех возможных узлов
            if (requiredChildNode != null) nodes.add(requiredChildNode);

            for (Node node : nodes) {                    //проверяем на на то, стоит ли из положить в map или нет
                if (sbb.isEnd(node.getDown())) {
                    map.put(node.getDown(), node.getDistance());
                }
            }

            for (Node node : nodes) {                    //проверяем на на то, стоит ли нам выходить из цикла
                System.out.println(node.getDistance());
                //if (node.getDistance() < minDistanceObject) end = true;
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
            if (node.getDistance() == minDistance) sbb.save(node.getDown());
        }

    }

    @Override
    public List<List> getVariants() {
        return sbb.getRequiredElements();
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
    }
}




