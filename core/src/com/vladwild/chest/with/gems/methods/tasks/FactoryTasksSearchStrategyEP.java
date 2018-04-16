package com.vladwild.chest.with.gems.methods.tasks;

import com.vladwild.chest.with.gems.gameplay.StaticObjectField;
import com.vladwild.chest.with.gems.methods.tasks.labyrinth.Chest;
import com.vladwild.chest.with.gems.methods.tasks.labyrinth.Keys;

public enum FactoryTasksSearchStrategyEP {
    CHEST {
        @Override
        protected SearchStrategyEP getTask(StaticObjectField field) {
            return new Chest(field);
        }
    }, KEYS {
        @Override
        protected SearchStrategyEP getTask(StaticObjectField field) {
            return new Keys(field);
        }
    };

    protected abstract SearchStrategyEP getTask(StaticObjectField field);

    public static SearchStrategyEP getTypeTask(FactoryTasksSearchStrategyEP typeTask, StaticObjectField field){
        return typeTask.getTask(field);
    }
}

