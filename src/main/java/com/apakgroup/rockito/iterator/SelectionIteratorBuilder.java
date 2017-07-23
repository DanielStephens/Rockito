package com.apakgroup.rockito.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.apakgroup.rockito.range.EmptySelection;
import com.apakgroup.rockito.range.FixedRange;
import com.apakgroup.rockito.range.RangeFactory;
import com.apakgroup.rockito.range.Selection;

public class SelectionIteratorBuilder<T> implements IteratorBuilder<T> {

    private final int choices;

    private Selection<T> selection = new EmptySelection<>();

    public SelectionIteratorBuilder(final int choices) {
        this.choices = choices;
    }

    public void setSelection(final Selection<T> selection) {
        this.selection = selection;
    }

    public void from(final Collection<T> ts) {
        selection = new FixedRange<>(ts);
    }

    public void from(final T t) {
        from(Arrays.asList(t));
    }

    public void from(final T t, final T... ts) {
        List<T> list = new ArrayList<>();
        list.add(t);

        if (ts == null) {
            list.add(null);
        } else {
            list.addAll(Arrays.asList(ts));
        }

        from(list);
    }

    public void between(final T start, final T end) {
        selection = RangeFactory.build((Class<T>) start.getClass(), start, end);
    }

    @Override
    public Iterator<T> build() {
        return new SelectionIterator<>(choices, selection);
    }

    private class SelectionIterator<S> implements Iterator<S> {

        private int count;

        private final Selection<S> selection;

        public SelectionIterator(final int count, final Selection<S> selection) {
            this.count = count;
            this.selection = selection;
        }

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public S next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            count--;
            return selection.randomly();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}

