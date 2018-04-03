package com.vladwild.chest.with.gems.methods.tasks;

import java.util.List;

public interface SearchDeep {
    List<?> getElements(List<?> list);
    List<List> getRequiredElements();

    void save(List<?> list);
}
