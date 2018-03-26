package com.vladwild.chest.with.gems.methods;

import com.badlogic.gdx.math.GridPoint2;
import com.vladwild.chest.with.gems.gameplay.Direction;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SearchVariantTrees {
    private boolean[][] matrixLogic;   //логическая матрица

    private GridPoint2 human;       //координаты человека
    private GridPoint2 chest;       //координаты координаты сундука
    private Set<GridPoint2> keys;   //координаты ключей

    private int limit;

    //private int countFindKeys;

    private Stack<Deque> stack; //стек очередей всех возможных направлений в узловой точке

    private Deque<Deque> allVariants;              //очередь всех вариантов до лимита
    private Deque<Deque> allRigthVariants;         //очередь правильных вариантов

    private Deque<Direction> currentDirectionsAll; //все текущие направления в одной попытке

    Direction currentDirection;

    private Deque<GridPoint2> nodes;  //последние координаты человека в узле

    public SearchVariantTrees(boolean[][] matrixLogic, GridPoint2 human, GridPoint2 chest,
                              Set<GridPoint2> keys, int limit){
        this.matrixLogic = getReverseMatrix(matrixLogic);
        //this.matrixLogic = matrixLogic;

        this.human = new GridPoint2(matrixLogic[0].length - human.y - 1, human.x);
        System.out.println("Human: x=" + this.human.x + "; y=" + this.human.y);
        this.chest = new GridPoint2(matrixLogic[0].length - chest.y - 1, chest.x);
        System.out.println("Chest: x=" + this.chest.x + "; y=" + this.chest.y);
        this.keys = new HashSet<GridPoint2>();
        int i = 0;
        for (GridPoint2 key : keys) {
            this.keys.add(new GridPoint2(matrixLogic[0].length - key.y - 1, key.x));
            System.out.println("Key" + (++i) + ": x=" + (matrixLogic[0].length - key.y - 1) + "; y=" + key.x);
        }

        this.limit = limit;

        stack = new Stack<Deque>();

        allVariants = new ArrayDeque<Deque>();
        allRigthVariants = new ArrayDeque<Deque>();

        currentDirectionsAll = new ArrayDeque<Direction>();

        nodes = new ArrayDeque<GridPoint2>();
    }

    //получаем обратную матрицу для соответствия соответствия координат человека и поля
    private boolean[][] getReverseMatrix(boolean[][] matrixLogic){
        boolean[][] reverseMatrix = new boolean[matrixLogic.length][matrixLogic[0].length];

        for (int i = 0; i < matrixLogic.length; i++){
            reverseMatrix[(matrixLogic.length - 1) - i] = matrixLogic[i];
        }

        return reverseMatrix;
    }

    //проверка на нахождение human в узловой точке
    private boolean isNodePoint(){
        //System.out.println();
        //System.out.println(human.x + " " + human.y);
        //System.out.println(matrixLogic[human.x][human.y]);

        //System.out.println(matrixLogic[human.x + 1][human.y]);
        //System.out.println(matrixLogic[human.x][human.y + 1]);
        //System.out.println(matrixLogic[human.x - 1][human.y]);
        //System.out.println(matrixLogic[human.x][human.y - 1]);

        return (matrixLogic[human.x + 1][human.y] && matrixLogic[human.x][human.y - 1]) ||
               (matrixLogic[human.x][human.y + 1] && matrixLogic[human.x + 1][human.y]) ||
               (matrixLogic[human.x - 1][human.y] && matrixLogic[human.x][human.y + 1]) ||
               (matrixLogic[human.x][human.y - 1] && matrixLogic[human.x - 1][human.y]);
    }

    //получение очереди всех возможных направлений относительно текущей узловой точки
    private Deque<Direction> getQueueDirections(){
        Deque<Direction> directions = new ArrayDeque<Direction>();

        if(matrixLogic[human.x + 1][human.y]) directions.offer(Direction.DOWN);
        if(matrixLogic[human.x][human.y + 1]) directions.offer(Direction.RIGTH);
        if(matrixLogic[human.x - 1][human.y]) directions.offer(Direction.UP);
        if(matrixLogic[human.x][human.y - 1]) directions.offer(Direction.LEFT);

        return directions;
    }

    //движение человкка по текущему направлению
    private void move(){
        switch (currentDirection){
            case DOWN:
                human.x++;
                break;
            case RIGTH:
                human.y++;
                break;
            case UP:
                human.x--;
                break;
            case LEFT:
                human.y--;
                break;
        }
    }

    //метод, возвращающий количество последних нулевых элементов очередей в стеке
    private int countNullDequesInStack(){

        System.out.println("-----------------------");
        int count = 0;
        for (Deque<Direction> deque : stack) {
            //System.out.println(deque.size());
            if (deque.size() == 0) {
                count++;
            }
            else {
                count = 0;
            }
        }
        //System.out.println("-----------------------");

        return count;
    }

    public Deque<Deque> getAllRigthVariants(){
        return allRigthVariants;
    }

    public Deque<Deque> function(){
        output();
        //while (countFindKeys != keys.size() && !human.equals(chest))
        //проверка - если если координаты человека не равны координатам сундука
        //while (!human.equals(chest)) {
        do {
            //если человек находится в узловой точке
            if (isNodePoint()) {

                //если размер стека равен лимиту
                if (stack.size() == limit){
                    allVariants.offer(new ArrayDeque<Direction>
                            (currentDirectionsAll));            //пихаем в allVariants этот вариант направлений

                    if(human.equals(chest)) allRigthVariants.offer(new ArrayDeque<Direction>
                            (currentDirectionsAll)); //пихаем в allRightVariants этот вариант направлений, если он привильный

                    int count = countNullDequesInStack();
                    if (count == 0){
                        currentDirectionsAll.pollLast();            //удаляем в currentDirectionsAll последнее направление
                        currentDirectionsAll.offerLast(
                                (Direction) stack.peek().pollFirst()); //возвращаем следующее направление из очереди в currentDirectionsAll,
                                                                       //удаляя его, но не саму очередь - и пихаем его в конец очереди
                        human = new GridPoint2(nodes.peekLast().x,
                                nodes.peekLast().y);                   //задаем последние координаты человека в предыдущем узле
                        nodes.pollLast();                             //удалением последнюю узловую точку
                        currentDirection = currentDirectionsAll.peekLast();  //извлекаем следующее направление из конца очереди
                                                                             //currentDirectionsAll, не удаляя его
                        nodes.add(new GridPoint2(human.x, human.y));
                    } else {
                        //несколько раз удаляем
                        for (int i = 0; i < count; i++){
                            stack.pop();                        //удаляем верх стека
                            currentDirectionsAll.pollLast();    //удаляем в currentDirectionsAll последнее направление
                            nodes.pollLast();                   //удаляем последние элементы nodes
                        }
                        currentDirectionsAll.pollLast();    //удаляем последний элемент currentDirictionAll еще раз
                        human = new GridPoint2(nodes.peekLast().x,
                                nodes.peekLast().y);                   //задаем последние координаты человека в предыдущем узле

                        currentDirectionsAll.offer((Direction) stack.peek().peekFirst());    //добавляем в очередь текущей попытке направление по которому будем идти
                        currentDirection = (Direction) stack.peek().pollFirst(); //возвращаем последнее направление из очереди, удаляя его, но не удаляя саму очередь
                    }

                } else {
                    stack.add(getQueueDirections());  //добавляем в стек очередь всех возможных вариантов ходов в этой узловой точке
                    currentDirectionsAll.offer((Direction) stack.peek().peekFirst());    //добавляем в очередь текущей попытке направление по которому будем идти
                    currentDirection = (Direction) stack.peek().pollFirst(); //возвращаем последнее направление из очереди, удаляя его, но не удаляя саму очередь
                    nodes.add(new GridPoint2(human.x, human.y));
                }
            }
            move(); //двигаемся человеком в текущем направлении
            //System.out.println(allVariants.size());
        } while (countNullDequesInStack() != stack.size());

        //?
        allVariants.offer(new ArrayDeque<Direction>(currentDirectionsAll));   //пихаем в allVariants последний правильный вариант направлений

        return allVariants;
    }

    //временно
    void output(){
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                System.out.print(matrixLogic[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Human: x=" + human.x + "; y=" + human.y);
        System.out.println("Chest: x=" + chest.x + "; y=" + chest.y);
        int i = 1;
        for (GridPoint2 elem : keys) {
            System.out.println("Key" + i + ": x=" + elem.x + "; y=" + elem.y);
            i++;
        }

    }
}
