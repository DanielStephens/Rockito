package com.apakgroup.rockito.collector;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class LoopableCompositionTest {

    private Looper<Integer> loop1;

    private Looper<Integer> loop2;

    private Looper<Integer> loop3;

    private Loopable loopComp1;

    private Loopable loopComp2;

    @Before
    public void resetLoops() {
        loop1 = LoopBuilder.build(1, 2);
        loop2 = LoopBuilder.build(3, 4);
        loop3 = LoopBuilder.build(5, 6);
        loopComp1 = new LoopableCollection(loop1, loop2);
        loopComp2 = new LoopableCollection(loopComp1, loop3);
    }

    @Test
    public void testStart() {
        assertThat(loop1.getCurrent()).isEqualTo(1);
        assertThat(loop2.getCurrent()).isEqualTo(3);

        loopComp1.advance();

        assertThat(loop1.getCurrent()).isEqualTo(2);
        assertThat(loop2.getCurrent()).isEqualTo(3);

        loopComp1.advance();

        assertThat(loop1.getCurrent()).isEqualTo(1);
        assertThat(loop2.getCurrent()).isEqualTo(4);

        loopComp1.advance();

        assertThat(loop1.getCurrent()).isEqualTo(2);
        assertThat(loop2.getCurrent()).isEqualTo(4);
    }

    @Test
    public void testAdvance() {
        loopComp1.advance();

        assertThat(loop1.getCurrent()).isEqualTo(2);
        assertThat(loop2.getCurrent()).isEqualTo(3);

        loopComp1.advance();

        assertThat(loop1.getCurrent()).isEqualTo(1);
        assertThat(loop2.getCurrent()).isEqualTo(4);
    }

    @Test
    public void testAdvanceComp() {
        assertThat(loop1.getCurrent()).isEqualTo(1);
        assertThat(loop2.getCurrent()).isEqualTo(3);
        assertThat(loop3.getCurrent()).isEqualTo(5);

        loopComp2.advance();

        assertThat(loop1.getCurrent()).isEqualTo(2);
        assertThat(loop2.getCurrent()).isEqualTo(3);
        assertThat(loop3.getCurrent()).isEqualTo(5);

        loopComp2.advance();

        assertThat(loop1.getCurrent()).isEqualTo(1);
        assertThat(loop2.getCurrent()).isEqualTo(4);
        assertThat(loop3.getCurrent()).isEqualTo(5);

        loopComp2.advance();

        assertThat(loop1.getCurrent()).isEqualTo(2);
        assertThat(loop2.getCurrent()).isEqualTo(4);
        assertThat(loop3.getCurrent()).isEqualTo(5);

        loopComp2.advance();

        assertThat(loop1.getCurrent()).isEqualTo(1);
        assertThat(loop2.getCurrent()).isEqualTo(3);
        assertThat(loop3.getCurrent()).isEqualTo(6);

    }
}

