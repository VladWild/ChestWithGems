package com.vladwild.chest.with.gems.methods.searchwidth;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AlgorithmSearchWidth {
    private Task task;

    private List<List> allVariants;        //список всех вариантов до лимита
    private List<List> allRightVariants;   //список правильных вариантов

    private List<List> allVariantsBuffer;  //буффер

    public AlgorithmSearchWidth(Task task) {
        this.task = task;

        allVariants = new ArrayList<>();
        allRightVariants = new ArrayList<>();

        allVariantsBuffer = new ArrayList<>();
    }

    private Deque<Deque> listInDeque(List<List> lists) {
        Deque<Deque> deques = new ArrayDeque<>();

        for (List list : lists) {
            deques.offer(new ArrayDeque(list));
        }

        return deques;
    }


    public void function() {
        for (Object element : task.getElementsStart()) {
            List elements = new ArrayList();
            elements.add(element);
            allVariants.add(elements);
        }

        allVariantsBuffer = new ArrayList<>(allVariants);

        do {
            int i = 0;                                 //номер списка на текущем уровне
            for (List list : allVariants) {            //проходимся по всем спискам элементов
                boolean one = true;                    //флаг элемента
                for(Object element : task.getElements(list)) {     //берем все дочернии элементы последнего элемента нашего текущего списка
                    if (one) {                                     //если элемент один
                        allVariantsBuffer.get(i).add(element);     //добавляем елемент в наш текущий список в буффер
                        one = false;                               //флаг элемента
                        i++;                                       //меняем номер списка на текущем уровне
                    } else {                                       //иначе, если элементов больше одного
                        List newList = new ArrayList(list);        //создаем новый список, копируя туда текущий
                        newList.remove(list.size() - 1);        //удаляем из текущего последний элемент
                        newList.add(element);                      //добавляем в список тот элемент, который получили
                        allVariantsBuffer.add(i, newList);         //добавляем текущий список в следующую позицию
                        i++;                                       //меняем номер списка на текущем уровне
                    }
                }
            }
            allVariants = new ArrayList<>(allVariantsBuffer);     //заносим все списки из буффера в allVariants
        } while (task.isEnd(allVariants));                         //проверяем на окончание выхода

        allRightVariants = task.getRightVariants();
    }


    public Deque<Deque> getAllVariants() {
        return listInDeque(allVariants);
    }

    public Deque<Deque> getAllRigthVariants() {
        return listInDeque(allRightVariants);
    }

}
