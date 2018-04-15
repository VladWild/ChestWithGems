package com.vladwild.chest.with.gems.methods.tasks;

import com.vladwild.chest.with.gems.gameplay.StaticObjectField;
import com.vladwild.chest.with.gems.methods.tasks.labyrinth.Chest;
import com.vladwild.chest.with.gems.methods.tasks.labyrinth.Keys;

public enum FactorySearchStrategyBB {
    CHEST {
        @Override
        protected SearchStrategyBB getTask(StaticObjectField field) {
            return new Chest(field);
        }
    }, KEYS {
        @Override
        protected SearchStrategyBB getTask(StaticObjectField field) {
            return new Keys(field);
        }
    };

    protected abstract SearchStrategyBB getTask(StaticObjectField field);

    public static SearchStrategyBB getTypeTask(FactorySearchStrategyBB typeTask, StaticObjectField field){
        return typeTask.getTask(field);
    }
}


