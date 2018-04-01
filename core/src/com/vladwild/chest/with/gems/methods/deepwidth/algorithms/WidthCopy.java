package com.vladwild.chest.with.gems.methods.deepwidth.algorithms;

import com.vladwild.chest.with.gems.methods.deepwidth.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class WidthCopy implements Algorithm {
    private Task task;     //задача

    public WidthCopy(Task task) {
        this.task = task;
    }

    @Override
    public void start() {
        List<List> variants = new ArrayList<>();         //список всех вариантов до лимита
        boolean end = false;                             //флаг окончания работы цикла

        for (Object element : task.getElements(null)) {
            List elements = new ArrayList();
            elements.add(element);
            variants.add(elements);
        }

        List<List> variantsBuffer = new ArrayList<>(variants);  //буффер

        do {
            int i = 0;                                 //номер списка на текущем уровне
            for (List list : variants) {               //проходимся по всем спискам элементов
                boolean one = true;                    //флаг элемента
                for (Object element : task.getElements(list)) {       //берем все дочернии элементы последнего элемента нашего текущего списка
                    if (one) {                                        //если элемент один
                        variantsBuffer.get(i).add(element);           //добавляем елемент в наш текущий буферный список
                        one = false;                                  //устанавливаем флаг на несколько элементов
                        i++;                                          //меняем номер списка на текущем уровне
                    } else {                                          //иначе, если элементов больше одного
                        List newDirections = new ArrayList(list);     //создаем новый список, копируя туда текущий список элементов
                        newDirections.remove(list.size() - 1);     //удаляем из скопираного списка последний элемент
                        newDirections.add(element);                   //добавляем в список то направление, которое получили из списка направлений
                        variantsBuffer.add(i, newDirections);         //добавляем текущий список в следующую позицию буффера
                        i++;                                          //меняем номер списка на текущем уровне
                    }
                }
            }
            variants = new ArrayList<>(variantsBuffer);    //заносим все списки из буффера в variants
            for (List variant : variants) {
                if (task.isEnd(variant)) {
                    end = true;
                    break;
                }
            }
        } while (!end);

        variants.forEach(task::save);    //сохраняем список вариантов
    }

    @Override
    public List<List> getVariants() {
        return task.getRequiredElements();
    }
}
