package com.apakgroup.rockito.stubbing;

import java.util.Collection;

import org.mockito.stubbing.OngoingStubbing;

import com.apakgroup.rockito.choice.Mock;
import com.apakgroup.rockito.collector.Looper;
import com.apakgroup.rockito.iterator.SelectionIteratorBuilder;

public interface SelectorStubbing<T> extends OngoingStubbing<T> {

    SelectorStubbing<T> thenIterate(T t, T... ts);

    SelectorStubbing<T> thenIterate(Mock<T> t);

    SelectionIteratorBuilder<T> choose(int i);

    SelectorStubbing<T> thenIterate(Looper<T> loop);

    SelectorStubbing<T> thenIterate(Collection<T> collection);

}

