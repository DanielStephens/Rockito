package com.apakgroup.rockito.iterator;

import java.util.Iterator;

public interface ReusableIterator<T> extends Iterator<T> {

    void reset();
}

