package com.apakgroup.rockito.choice;

import java.util.Random;

import org.mockito.Mockito;

import com.apakgroup.rockito.stubbing.SelectorStubbing;
import com.apakgroup.rockito.stubbing.StubbingFactory;

public class Selector {

    public static final Random RANDOM = new Random(System.currentTimeMillis());

    public static <T> SelectorStubbing<T> when(final T methodCall) {
        return StubbingFactory.wrap(Mockito.when(methodCall));
    }

    public static <T> Mock<T> mock(final Class<T> clazz) {
        return StubbingFactory.wrap(Mockito.mock(clazz));
    }

}

