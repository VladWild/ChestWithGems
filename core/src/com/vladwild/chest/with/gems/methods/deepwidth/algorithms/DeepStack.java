package com.vladwild.chest.with.gems.methods.deepwidth.algorithms;

import com.vladwild.chest.with.gems.methods.deepwidth.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class DeepStack implements Algorithm{
    private Task task;       //задача
    private int limit;       //глубина

    public DeepStack(Task task, int limit) {
        this.task = task;
        this.limit = limit;
    }

    @Override
    public void start() {
        List<List> variants = new ArrayList<>();     //все возможные варианты ветвей
        List<List> stack = new ArrayList<>();        //список всех вариантов до лимита
        List branch = new ArrayList();               //текущая ветка дерева

        stack.add(task.getElements(new ArrayList<>()));

        do{
            if (stack.size() == limit){
                variants.add(branch);

            } else {
                branch.add(stack.get(stack.size() - 1).get(0));
                stack.get(stack.size() - 1).remove(0);
                stack.add(task.getElements(branch));
            }

        } while (!stack.isEmpty());


    }

    @Override
    public List<List> getVariants() {
        return null;
    }
}
