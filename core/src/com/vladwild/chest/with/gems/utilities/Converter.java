package com.vladwild.chest.with.gems.utilities;

@FunctionalInterface
public interface Converter <T, E>{
    E convert(T value);
}
