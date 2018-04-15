package com.vladwild.chest.with.gems.methods.algorithms.function;

import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.SearchFunction;

public enum FactoryFunction {
    EUCLIDEAN {
        @Override
        protected Algorithm getFunction(SearchFunction task, double coefficient) {
            return new Euclidean(task);
        }
    },
    EUCLIDEAN_COEFFICIENT {
        @Override
        protected Algorithm getFunction(SearchFunction task, double coefficient) {
            return new EuclideanCoefficient(task, coefficient);
        }
    };

    protected abstract Algorithm getFunction(SearchFunction task, double coefficient);

    public static Algorithm getTypeFunction(FactoryFunction typeAlgorithm, SearchFunction task, double coefficient){
        return typeAlgorithm.getFunction(task, coefficient);
    }
}
