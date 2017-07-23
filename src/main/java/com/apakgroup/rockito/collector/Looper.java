package com.apakgroup.rockito.collector;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.apakgroup.rockito.iterator.IteratorBuilder;

public class Looper<T> implements Loopable {

    private final IteratorBuilder<T> itBuilder;

    private Iterator<T> it;

    private T current;

    private boolean started = false;

    protected Looper(final IteratorBuilder<T> itBuilder) {
        this.itBuilder = itBuilder;
    }

    public T getCurrent() {
        start();
        return current;
    }

    @Override
    public void advance() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        current = it.next();
    }

    @Override
    public boolean hasNext() {
        start();
        return it.hasNext();
    }

    @Override
    public void reset() {
        started = true;
        it = itBuilder.build();
        current = it.next();
    }

    private void start() {
        if (started) {
            return;
        }

        reset();
    }

}

