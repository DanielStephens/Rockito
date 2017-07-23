package com.apakgroup.rockito.stubbing;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.apakgroup.rockito.collector.Looper;

public class RangeReturns implements Answer<Object> {

    private final Looper<? extends Object> loop;

    public RangeReturns(final Looper<? extends Object> loop) {
        this.loop = loop;
    }

    @Override
    public Object answer(final InvocationOnMock invocation) throws Throwable {
        return loop.getCurrent();
    }

}

