package com.apakgroup.rockito.range.primitives;

import com.apakgroup.rockito.choice.Selector;
import com.apakgroup.rockito.range.Selection;

/**
 * a range from start (inclusive) to end (exclusive)
 * 
 * @author daniel.stephens
 *
 */
public class IntRange implements Selection<Integer> {

    private final int start;

    private final int end;

    private final int size;

    public IntRange(final int start, final int end) {
        this.start = Math.min(start, end);
        this.end = Math.max(start, end);

        size = this.end - this.start;
    }

    @Override
    public Integer randomly() {
        return get(Selector.RANDOM.nextInt(size()));
    }

    @Override
    public Integer get(final int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        return start + index;
    }

    @Override
    public int size() {
        return size;
    }

}

