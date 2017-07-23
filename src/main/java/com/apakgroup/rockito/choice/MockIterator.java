package com.apakgroup.rockito.choice;

import java.util.Iterator;

import com.apakgroup.rockito.collector.Loopable;

public class MockIterator<T> implements Iterator<T> {

    private final Loopable itComp;

    private final T obj;

    private boolean unset = true;

    public MockIterator(final Loopable itComp, final T obj) {
        this.itComp = itComp;
        this.obj = obj;
    }

    @Override
    public boolean hasNext() {
        return itComp.hasNext();
    }

    @Override
    public T next() {
        if (unset) {
            unset = false;
            return obj;
        }

        itComp.advance();
        return obj;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}

