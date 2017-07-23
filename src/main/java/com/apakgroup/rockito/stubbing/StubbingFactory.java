package com.apakgroup.rockito.stubbing;

import org.mockito.stubbing.OngoingStubbing;

import com.apakgroup.rockito.choice.Mock;

public class StubbingFactory {

    public static <T> SelectorStubbing<T> wrap(final OngoingStubbing<T> when) {
        return new Stub<>(when);
    }

    public static <T> Mock<T> wrap(final T mock) {
        return new Mock<>(mock);
    }

}

