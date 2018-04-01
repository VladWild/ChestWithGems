package com.vladwild.chest.with.gems.methods.deepwidth.algorithms;

import com.vladwild.chest.with.gems.methods.deepwidth.tasks.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WidthLink implements Algorithm{
    private Task task;

    public WidthLink(Task task){
        this.task = task;
    }

    @Override
    public void start() {
        List<Node> nodes = new ArrayList<>();
        boolean end = false;

        for (Object element : task.getElements(null)) {   //?
            nodes.add(new Node<>(null, element));
        }

        List<Node> nodesBuffer = new ArrayList<>(nodes);

        do{
            nodes = new ArrayList<>();

            for (Node node : nodesBuffer){                                  //проходимся по всем узлам
                for(Object element : task.getElements(node.getDown())){     //формируем список узлов на следующем уровне
                    nodes.add(new Node(node, element));
                }
            }

            for (Node node : nodes) {                                       //проверяем на окончание цикла
                if (task.isEnd(node.getDown())) {
                    end = true;
                    break;
                }
            }

            nodesBuffer = new ArrayList<>(nodes);
        } while (!end);

        nodes.forEach(node -> task.save(node.getDown()));
    }

    @Override
    public List<List> getVariants() {
        return task.getRequiredElements();
    }

    private class Node<E>{
        private Node previous;
        private E element;

        public Node(Node previous, E elements){
            this.previous = previous;
            this.element = elements;
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

        private Node getPreviousNode(){
            return previous;
        }

        public E getElement(){
            return element;
        }
    }
}


