package com.apakgroup.rockito.random;

import java.util.Comparator;
import java.util.Random;

public interface Randomiser<T> {

    static final Random RANDOM = new Random(System.currentTimeMillis());

    T nextRandom();

    T nextRandomBetween(T t1, T t2);

    T nextRandomBetween(T t1, T t2, Comparator<T> comparator);

}

