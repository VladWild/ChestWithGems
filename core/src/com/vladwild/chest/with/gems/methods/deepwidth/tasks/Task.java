package com.vladwild.chest.with.gems.methods.deepwidth.tasks;

import java.util.List;

public interface Task {
    List<?> getElements(List<?> list);
    List<List> getRequiredElements();

    boolean isEnd(List<List> lists);
}


