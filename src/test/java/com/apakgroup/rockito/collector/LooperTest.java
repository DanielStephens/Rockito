package com.apakgroup.rockito.collector;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class LooperTest {

    private final Looper<Integer> loop1 = LoopBuilder.build(1, 2);

    @Test
    public void testStart() {
        assertThat(loop1.getCurrent()).isEqualTo(1);

        loop1.advance();

        assertThat(loop1.getCurrent()).isEqualTo(2);

        loop1.reset();

        assertThat(loop1.getCurrent()).isEqualTo(1);
    }

    @Test
    public void testAdvance() {
        loop1.advance();

        assertThat(loop1.getCurrent()).isEqualTo(2);

        loop1.reset();

        assertThat(loop1.getCurrent()).isEqualTo(1);
    }

    @Test
    public void testAutoReset() {
        loop1.advance();

        assertThat(loop1.getCurrent()).isEqualTo(2);

        loop1.reset();

        assertThat(loop1.getCurrent()).isEqualTo(1);

        loop1.advance();

        assertThat(loop1.getCurrent()).isEqualTo(2);

        loop1.reset();

        assertThat(loop1.getCurrent()).isEqualTo(1);
    }

    @Test
    public void testReset() {
        loop1.advance();

        assertThat(loop1.getCurrent()).isEqualTo(2);

        loop1.reset();

        assertThat(loop1.getCurrent()).isEqualTo(1);

        loop1.advance();

        assertThat(loop1.getCurrent()).isEqualTo(2);
    }
}

