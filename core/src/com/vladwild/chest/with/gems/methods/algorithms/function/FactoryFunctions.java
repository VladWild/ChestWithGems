package com.vladwild.chest.with.gems.methods.algorithms.function;

import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.Function;

public enum  FactoryFunctions {
    EUCLIDEAN {
        @Override
        protected Algorithm getFunction(Function task) {
            return new Euclidean(task);
        }
    };

    protected abstract Algorithm getFunction(Function task);

    public static Algorithm getTypeFunction(FactoryFunctions typeFunction, Function task){
        return typeFunction.getFunction(task);
    }
}
