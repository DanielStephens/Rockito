package com.apakgroup.rockito.integration;

public class ParentTestObject {

    private int p;

    private TestObject to;

    public ParentTestObject(final int p, final TestObject to) {
        this.p = p;
        this.to = to;
    }

    public int getP() {
        return p;
    }

    public TestObject getTo() {
        return to;
    }

}

