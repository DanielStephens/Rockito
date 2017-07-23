package com.apakgroup.rockito.range;

import java.util.Collection;
import java.util.Iterator;

import com.apakgroup.rockito.choice.Selector;

public class FixedRange<T> implements Selection<T> {

    private final Collection<T> collection;

    public FixedRange(final Collection<T> collection) {
        this.collection = collection;
    }

    @Override
    public T randomly() {
        return get(Selector.RANDOM.nextInt(size()));
    }

    @Override
    public T get(final int index) {
        Iterator<T> it = collection.iterator();

        int i = 0;
        while (i++ < index) {
            it.next();
        }

        return it.next();
    }

    @Override
    public int size() {
        return collection.size();
    }

}

