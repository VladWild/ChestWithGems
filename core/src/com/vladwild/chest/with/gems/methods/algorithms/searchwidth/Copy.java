package com.vladwild.chest.with.gems.methods.algorithms.searchwidth;

import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.labyrinth.Labyrinth;

import java.util.ArrayList;
import java.util.List;

public class Copy implements Algorithm {
    private Labyrinth width;     //задача

    public Copy(Labyrinth width) {
        this.width = width;
    }

    @Override
    public void start() {
        List<List> variants = new ArrayList<>();         //список всех вариантов до лимита
        boolean end = false;                             //флаг окончания работы цикла

        for (Object element : width.getElements(null)) {
            List elements = new ArrayList();
            elements.add(element);
            variants.add(elements);
        }

        List<List> variantsBuffer = new ArrayList<>(variants);  //буффер

        do {
            int i = 0;                                 //номер списка на текущем уровне
            for (List list : variants) {               //проходимся по всем спискам элементов
                boolean one = true;                    //флаг элемента
                for (Object element : width.getElements(list)) {       //берем все дочернии элементы последнего элемента нашего текущего списка
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
                if (width.isEnd(variant)) {
                    end = true;
                    break;
                }
            }
        } while (!end);

        variants.forEach(width::save);    //сохраняем список вариантов
    }

    @Override
    public List<List> getVariants() {
        return width.getRequiredElements();
    }
}
