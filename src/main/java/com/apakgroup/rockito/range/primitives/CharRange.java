package com.apakgroup.rockito.range.primitives;

import com.apakgroup.rockito.range.Selection;

public class CharRange implements Selection<Character> {

    private final Selection<Integer> wrappedSelection;

    public CharRange(final char start, final char end) {
        wrappedSelection = new IntRange(start, end);
    }

    @Override
    public Character randomly() {
        return asCharacter(wrappedSelection.randomly());
    }

    @Override
    public Character get(final int index) {
        return asCharacter(wrappedSelection.get(index));
    }

    @Override
    public int size() {
        return wrappedSelection.size();
    }

    private Character asCharacter(final Integer i) {
        return (char) (int) i;
    }

}
