package com.apakgroup.rockito.collector;

public interface Loopable {

    boolean hasNext();

    void advance();

    void reset();

}

