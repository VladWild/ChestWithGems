package com.vladwild.chest.with.gems.methods.tasks;

import java.util.List;

public interface SearchStrategyBB extends SearchWidth{
    int getDistance(List<?> list, Object element);
    List<?> getElements(List<?> list, Object element);
}




