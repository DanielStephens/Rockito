package com.apakgroup.rockito.iterator;

import java.util.Iterator;

public interface IteratorBuilder<T> {

    Iterator<T> build();

}

