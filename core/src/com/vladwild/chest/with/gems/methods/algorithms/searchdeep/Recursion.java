package com.vladwild.chest.with.gems.methods.algorithms.searchdeep;

import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.SearchDeep;

import java.util.ArrayList;
import java.util.List;

public class Recursion implements Algorithm {
    private SearchDeep deep;       //задача
    private int limit;       //глубина

    private List elements = new ArrayList<>();            //список текущих элементов

    public Recursion(SearchDeep deep, int limit) {
        this.deep = deep;
        this.limit = limit;
    }

    private boolean isLimit(){
        return elements.size() == limit;
    }

    private void function(List list) {
        if (!isLimit()) {
            for (Object element : list){
                elements.add(element);
                function(deep.getElements(elements));
            }
            if (!elements.isEmpty()) elements.remove(elements.size() - 1);
        } else {
            deep.save(elements);

            elements = new ArrayList(elements);
            elements.remove(elements.size() - 1);
        }
    }

    @Override
    public void start() {
        function(deep.getElements(null));
    }

    @Override
    public List<List> getVariants() {
        return deep.getRequiredElements();
    }
}
