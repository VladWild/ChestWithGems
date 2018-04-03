package com.vladwild.chest.with.gems.methods.algorithms.searchwidth;

import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.labyrinth.Labyrinth;

public enum FactoryWidth {
    COPY {
        @Override
        protected Algorithm getSearchWidth(Labyrinth task) {
            return new Copy(task);
        }
    }, LINK {
        @Override
        protected Algorithm getSearchWidth(Labyrinth task) {
            return new Link(task);
        }
    };

    protected abstract Algorithm getSearchWidth(Labyrinth task);

    public static Algorithm getTypeSearchWidth(FactoryWidth typeAlgorithm, Labyrinth task){
        return typeAlgorithm.getSearchWidth(task);
    }
}
