package com.apakgroup.rockito.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

public class LoopableCollection implements Loopable {

    private final Collection<Loopable> loops;

    public LoopableCollection() {
        loops = new ArrayList<>();
    }

    public LoopableCollection(final Loopable... loops) {
        this.loops = Arrays.asList(loops);
    }

    @Override
    public boolean hasNext() {
        for (Loopable loop : loops) {
            if (loop.hasNext()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void advance() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        for (Loopable loop : loops) {
            if (loop.hasNext()) {
                loop.advance();
                break;
            }
            loop.reset();
        }
    }

    @Override
    public void reset() {
        for (Loopable loop : loops) {
            loop.reset();
        }
    }

    public void compose(final Loopable l) {
        loops.add(l);
    }

}

