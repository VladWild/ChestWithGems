package com.vladwild.chest.with.gems.methods.tasks;

import com.vladwild.chest.with.gems.gameplay.StaticObjectField;
import com.vladwild.chest.with.gems.methods.tasks.labyrinth.Chest;
import com.vladwild.chest.with.gems.methods.tasks.labyrinth.Keys;

public enum FactoryTasksSearchFunction {
    CHEST {
        @Override
        protected SearchFunction getTask(StaticObjectField field) {
            return new Chest(field);
        }
    }, KEYS {
        @Override
        protected SearchFunction getTask(StaticObjectField field) {
            return new Keys(field);
        }
    };

    protected abstract SearchFunction getTask(StaticObjectField field);

    public static SearchFunction getTypeTask(FactoryTasksSearchFunction typeTask, StaticObjectField field){
        return typeTask.getTask(field);
    }
}
