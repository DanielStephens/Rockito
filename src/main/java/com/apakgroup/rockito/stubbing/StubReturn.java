package com.apakgroup.rockito.stubbing;

import java.util.ArrayList;
import java.util.List;

public class StubReturn<T> {

    private final T method;

    private final List<T> returns;

    public StubReturn(final T method) {
        this.method = method;
        this.returns = new ArrayList<>();
    }

    public void addReturn(final T t) {
        returns.add(t);
    }

    public void setup() {

    }

}

