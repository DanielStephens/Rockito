package com.apakgroup.rockito.choice;

import java.util.Iterator;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.apakgroup.rockito.collector.LoopBuilder;
import com.apakgroup.rockito.collector.Loopable;
import com.apakgroup.rockito.collector.LoopableCollection;

public class Mock<T> implements Iterable<T>, Answer<T> {

    private static Mock currentMock;

    private final T mock;

    private final LoopableCollection group;

    private final Iterator<T> iterator;

    public Mock(final T mock) {
        this.mock = mock;
        group = LoopBuilder.build();
        iterator = new MockIterator<>(group, mock);
    }

    public T calls() {
        currentMock = this;
        return mock;
    }

    public static Mock getCurrentMock() {
        return currentMock;
    }

    @Override
    public Iterator<T> iterator() {
        group.reset();
        return new MockIterator<>(group, mock);
    }

    public void addIteration(final Loopable loop) {
        group.compose(loop);
    }

    public void join(final Mock m) {
        addIteration(m.group);
    }

    @Override
    public T answer(final InvocationOnMock invocation) throws Throwable {
        return mock;
    }

}

