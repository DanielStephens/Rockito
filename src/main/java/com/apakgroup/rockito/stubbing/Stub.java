package com.apakgroup.rockito.stubbing;

import java.util.Collection;

import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

import com.apakgroup.rockito.choice.Mock;
import com.apakgroup.rockito.collector.LoopBuilder;
import com.apakgroup.rockito.collector.Looper;
import com.apakgroup.rockito.iterator.SelectionIteratorBuilder;

public class Stub<T> implements SelectorStubbing<T> {

    private final OngoingStubbing<T> when;

    public Stub(final OngoingStubbing<T> when) {
        this.when = when;
    }

    @Override
    public SelectionIteratorBuilder<T> choose(final int i) {
        SelectionIteratorBuilder<T> ib = new SelectionIteratorBuilder<>(i);
        thenIterate(LoopBuilder.build(ib));
        return ib;
    }

    @Override
    public SelectorStubbing<T> thenIterate(final Collection<T> collection) {
        Looper<T> loop = LoopBuilder.build(collection);
        thenIterate(loop);
        return this;
    }

    @Override
    public SelectorStubbing<T> thenIterate(final T t, final T... ts) {
        Looper<T> loop = LoopBuilder.build(t, ts);
        thenIterate(loop);
        return this;
    }

    @Override
    public SelectorStubbing<T> thenIterate(final Looper<T> loop) {
        Mock.getCurrentMock().addIteration(loop);
        when.thenAnswer(new RangeReturns(loop));
        return this;
    }

    @Override
    public SelectorStubbing<T> thenIterate(final Mock<T> m) {
        Mock.getCurrentMock().join(m);
        when.thenAnswer(m);
        return this;
    }

    @Override
    public SelectorStubbing<T> thenReturn(final T value) {
        when.thenReturn(value);
        return this;
    }

    @Override
    public SelectorStubbing<T> thenReturn(final T value, final T... values) {
        when.thenReturn(value, values);
        return this;
    }

    @Override
    public SelectorStubbing<T> thenThrow(final Throwable... throwables) {
        when.thenThrow(throwables);
        return this;
    }

    @Override
    public SelectorStubbing<T> thenThrow(final Class<? extends Throwable>... throwableClasses) {
        when.thenThrow(throwableClasses);
        return this;
    }

    @Override
    public SelectorStubbing<T> thenCallRealMethod() {
        when.thenCallRealMethod();
        return this;
    }

    @Override
    public SelectorStubbing<T> thenAnswer(final Answer<?> answer) {
        when.thenAnswer(answer);
        return this;
    }

    @Override
    public SelectorStubbing<T> then(final Answer<?> answer) {
        when.then(answer);
        return this;
    }

    @Override
    public <M> M getMock() {
        when.getMock();
        return when.getMock();
    }

}

