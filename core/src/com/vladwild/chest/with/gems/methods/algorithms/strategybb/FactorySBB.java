package com.vladwild.chest.with.gems.methods.algorithms.strategybb;

import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.SearchStrategyBB;

public enum  FactorySBB {
    STRATEGY_BB {
        @Override
        protected Algorithm getSBB(SearchStrategyBB task) {
            return new StrategyBB(task);
        }
    };

    protected abstract Algorithm getSBB(SearchStrategyBB task);

    public static Algorithm getTypeSBB(FactorySBB typeAlgorithm, SearchStrategyBB task){
        return typeAlgorithm.getSBB(task);
    }
}




