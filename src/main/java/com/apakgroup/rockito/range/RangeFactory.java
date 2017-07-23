package com.apakgroup.rockito.range;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apakgroup.rockito.range.primitives.CharRange;
import com.apakgroup.rockito.range.primitives.IntRange;
import com.google.common.collect.ImmutableMap;

public class RangeFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(RangeFactory.class);

    private static final Map<Class<?>, RangeBuilder<?>> factoryMap;

    //@formatter:off
    static {
        factoryMap = ImmutableMap.<Class<?>, RangeFactory.RangeBuilder<?>>of(
                Integer.class, new IntRangeBuilder(),
                Character.class, new CharRangeBuilder());
    }
    //@formatter:on

    private RangeFactory() {
    }

    public static <T extends Object> Selection<T> build(final Class<T> clazz, final T start, final T end) {
        RangeBuilder<T> builder = (RangeBuilder<T>) factoryMap.get(clazz);
        if (builder == null) {
            builder = slowerResolve(start, end);
        }

        if (builder == null) {
            LOGGER.error("No builder could be found for the class type '{}' required", clazz.getSimpleName());
            throw new UnsupportedOperationException(
                    "This factory does not support the class '" + clazz.getSimpleName() + "'");
        }

        return builder.build(start, end);
    }

    private static <T extends Object> RangeBuilder<T> slowerResolve(final T start, final T end) {
        List<Class<?>> startClasses = resolveSuperclasses(start);
        startClasses.retainAll(resolveSuperclasses(end));

        if (startClasses.isEmpty()) {
            return null;
        }

        return (RangeBuilder<T>) factoryMap.get(startClasses.get(0));
    }

    private static <T extends Object> List<Class<?>> resolveSuperclasses(final T val) {
        List<Class<?>> superclasses = new ArrayList<>();
        Class<?> clazz = val.getClass();

        while (clazz != Object.class) {
            superclasses.add(clazz);
            clazz = clazz.getSuperclass();
        }

        return superclasses;
    }

    private interface RangeBuilder<T extends Object> {

        Selection<T> build(T start, T end);
    }

    private static class IntRangeBuilder implements RangeBuilder<Integer> {

        @Override
        public Selection<Integer> build(final Integer start, final Integer end) {
            return new IntRange(start, end);
        }
    }

    private static class CharRangeBuilder implements RangeBuilder<Character> {

        @Override
        public Selection<Character> build(final Character start, final Character end) {
            return new CharRange(start, end);
        }
    }
}

