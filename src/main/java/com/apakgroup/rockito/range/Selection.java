package com.apakgroup.rockito.range;

public interface Selection<T> {

    T randomly();

    T get(int index);

    int size();
}

