package com.apakgroup.rockito.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.apakgroup.rockito.iterator.IteratorBuilder;

public class LoopBuilder {

    private LoopBuilder() {
    }

    public static <T> Looper<T> build(final Collection<T> collection) {
        IteratorBuilder<T> itBuilder = new IteratorBuilder<T>() {

            @Override
            public Iterator<T> build() {
                return collection.iterator();
            }
        };

        return new Looper<>(itBuilder);
    }

    public static <T> Looper<T> build(final IteratorBuilder<T> itBuilder) {
        return new Looper<>(itBuilder);
    }

    public static <T> Looper<T> build(final T t, final T... ts) {
        List<T> list = new ArrayList<>();
        list.add(t);

        if (ts == null) {
            list.add(null);
        } else {
            list.addAll(Arrays.asList(ts));
        }

        return build(list);
    }

    public static <T> Looper<T> build(final T t) {
        List<T> list = new ArrayList<>();
        list.add(t);
        return build(list);
    }

    public static LoopableCollection build() {
        return new LoopableCollection();
    }

}

