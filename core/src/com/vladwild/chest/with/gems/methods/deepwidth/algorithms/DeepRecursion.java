package com.vladwild.chest.with.gems.methods.deepwidth.algorithms;

import com.vladwild.chest.with.gems.methods.deepwidth.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class DeepRecursion implements Algorithm {
    private Task task;       //задача
    private int limit;       //глубина

    private List elements = new ArrayList<>();            //список текущих элементов

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
            task.save(elements);

            elements = new ArrayList(elements);
            elements.remove(elements.size() - 1);
        }
    }

    @Override
    public void start() {
        function(task.getElements(null));
    }

    @Override
    public List<List> getVariants() {
        return task.getRequiredElements();
    }
}
