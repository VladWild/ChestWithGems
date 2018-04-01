package com.vladwild.chest.with.gems.methods.deepwidth.tasks;

import java.util.List;

public interface Task {
    List<?> getElements(List<?> list);
    List<List> getRequiredElements();

    void save(List<?> list);
    boolean isEnd(List<?> list);
}


