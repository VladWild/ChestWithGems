package com.vladwild.chest.with.gems.methods.deepwidth.algorithms;

import com.vladwild.chest.with.gems.methods.deepwidth.tasks.Task;
import com.vladwild.chest.with.gems.methods.deepwidth.tasks.labyrinth.All;

import java.util.ArrayList;
import java.util.List;

public class DeepRecursion implements Algorithm {
    private Task task;       //задача
    private int limit;       //глубина

    private List<List> variants = new ArrayList<>();         //список всех вариантов до лимита
    private List elements = new ArrayList<>();               //список текущих элементов

    public DeepRecursion(Task task, int limit) {
        this.task = task;
        this.limit = limit;
    }

    private boolean isLimit(){
        return elements.size() == limit;
    }

    private void function(List list) {
        if (!isLimit()) {
            for (Object element : list){
                elements.add(element);
                function(task.getElements(elements));
            }
            if (!elements.isEmpty()) elements.remove(elements.size() - 1);
        } else {
            variants.add(elements);

            elements = new ArrayList(elements);
            elements.remove(elements.size() - 1);
        }
    }

    private boolean taskIsAll(){
        return task instanceof All;
    }

    @Override
    public void start() {
        function(task.getElements(new ArrayList<>()));

        if (!taskIsAll()) {
            task.isEnd(variants);
            variants = task.getRequiredElements();
        }
    }

    @Override
    public List<List> getVariants() {
        return variants;
    }
}
