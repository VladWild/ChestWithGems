package com.vladwild.chest.with.gems.methods.algorithms.function;

import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.SearchFunction;

public enum FactoryFunction {
    EUCLIDEAN {
        @Override
        protected Algorithm getFunction(SearchFunction task) {
            return new Euclidean(task);
        }
    };

    protected abstract Algorithm getFunction(SearchFunction task);

    public static Algorithm getTypeFunction(FactoryFunction typeAlgorithm, SearchFunction task){
        return typeAlgorithm.getFunction(task);
    }
}
