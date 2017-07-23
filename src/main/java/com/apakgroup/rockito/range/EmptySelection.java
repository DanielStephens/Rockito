package com.apakgroup.rockito.range;

public class EmptySelection<T> implements Selection<T> {

    @Override
    public T randomly() {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public T get(final int index) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return 0;
    }

}

