package com.vladwild.chest.with.gems.methods.algorithms.searchdeep;

import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.labyrinth.Labyrinth;

public enum  FactoryDeep {
    RECURSION {
        @Override
        protected Algorithm getSearchDeep(Labyrinth task, int limit) {
            return new Recursion(task, limit);
        }
    }, STACK {
        @Override
        protected Algorithm getSearchDeep(Labyrinth task, int limit) {
            return new StackUse(task, limit);
        }
    };

    protected abstract Algorithm getSearchDeep(Labyrinth task, int limit);

    public static Algorithm getTypeSearchDeep(FactoryDeep typeAlgorithm, Labyrinth task, int limit){
        return typeAlgorithm.getSearchDeep(task, limit);
    }
}
