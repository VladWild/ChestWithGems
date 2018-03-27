package com.vladwild.chest.with.gems.methods.searchwidth;

import java.util.List;

public interface Task {
    List<?> getElementsStart();
    List<?> getElements(List<?> list);

    List<List> getRightVariants();
    boolean isEnd(List<List> lists);
}

