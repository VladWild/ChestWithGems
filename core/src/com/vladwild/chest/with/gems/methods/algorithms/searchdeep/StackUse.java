package com.vladwild.chest.with.gems.methods.algorithms.searchdeep;

import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.SearchDeep;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class StackUse implements Algorithm {
    private SearchDeep deep;       //задача
    private int limit;       //глубина

    public StackUse(SearchDeep deep, int limit) {
        this.deep = deep;
        this.limit = limit;
    }

    @Override
    public void start() {
        Stack<List> stack = new Stack<>();           //список всех вариантов до лимита
        List branch = new ArrayList();               //текущая ветка дерева

        stack.push(deep.getElements(null));

        do{
            if (!stack.peek().isEmpty()){
                if (stack.size() < limit) {
                    branch.add(stack.peek().remove(0));
                    stack.push(new LinkedList(deep.getElements(branch)));
                } else {
                    branch.add(stack.peek().remove(0));
                    deep.save(branch);
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
        return deep.getRequiredElements();
    }
}
