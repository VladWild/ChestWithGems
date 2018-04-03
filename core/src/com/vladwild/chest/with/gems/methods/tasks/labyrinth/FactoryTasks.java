package com.vladwild.chest.with.gems.methods.tasks.labyrinth;

import com.vladwild.chest.with.gems.gameplay.StaticObjectField;

public enum FactoryTasks {
    ALL {
        @Override
        protected Labyrinth getTask(StaticObjectField field) {
            return new All(field);
        }
    }, CHEST {
        @Override
        protected Labyrinth getTask(StaticObjectField field) {
            return new Chest(field);
        }
    }, KEYS {
        @Override
        protected Labyrinth getTask(StaticObjectField field) {
            return new Keys(field);
        }
    };

    protected abstract Labyrinth getTask(StaticObjectField field);

    public static Labyrinth getTypeTask(FactoryTasks typeTask, StaticObjectField field){
        return typeTask.getTask(field);
    }
}
