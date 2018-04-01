package com.vladwild.chest.with.gems.methods.deepwidth.algorithms;

import com.vladwild.chest.with.gems.methods.deepwidth.tasks.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DeepStack implements Algorithm{
    private Task task;       //задача
    private int limit;       //глубина

    public DeepStack(Task task, int limit) {
        this.task = task;
        this.limit = limit;
    }

    @Override
    public void start() {
        Stack<List> stack = new Stack<>();           //список всех вариантов до лимита
        List branch = new ArrayList();               //текущая ветка дерева

        stack.push(task.getElements(null));

        do{
            if (!stack.peek().isEmpty()){
                if (stack.size() < limit) {
                    branch.add(stack.peek().remove(0));
                    stack.push(new LinkedList(task.getElements(branch)));
                } else {
                    branch.add(stack.peek().remove(0));
                    task.save(branch);
                    branch = new ArrayList(branch);
                    branch.remove(branch.size() - 1);
                }
            } else {
                stack.pop();
                if (!branch.isEmpty()) branch.remove(branch.size() - 1);
            }
        } while (!stack.empty());
    }

    @Override
    public List<List> getVariants() {
        return task.getRequiredElements();
    }
}
