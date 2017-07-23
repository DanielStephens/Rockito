package com.apakgroup.rockito.range;

import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;

public class FixedRangeTest {

    private final Collection<Integer> ints = ImmutableSet.of(0, 1, 2, 3, 4, 5);

    private final FixedRange<Integer> selection = new FixedRange<>(ints);

    @Test
    public void testGet() {
        Assertions.assertThat(selection.get(0)).isEqualTo(0);
        Assertions.assertThat(selection.get(2)).isEqualTo(2);
        Assertions.assertThat(selection.get(5)).isEqualTo(5);
    }
}

