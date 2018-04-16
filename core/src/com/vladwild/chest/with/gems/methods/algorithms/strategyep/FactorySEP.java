package com.vladwild.chest.with.gems.methods.algorithms.strategyep;

import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.tasks.SearchStrategyEP;

public enum  FactorySEP {
    STRATEGY_EP {
        @Override
        protected Algorithm getSBB(SearchStrategyEP task) {
            return new StrategyEP(task);
        }
    };

    protected abstract Algorithm getSBB(SearchStrategyEP task);

    public static Algorithm getTypeSBB(FactorySEP typeAlgorithm, SearchStrategyEP task){
        return typeAlgorithm.getSBB(task);
    }
}


