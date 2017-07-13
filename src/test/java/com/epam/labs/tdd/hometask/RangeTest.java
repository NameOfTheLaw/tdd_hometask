package com.epam.labs.tdd.hometask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class RangeTest {

    private Range range;

    private long extendedUpperBound;
    private long extendedLowerBound;

    @BeforeEach
    void init() {

        extendedLowerBound = 10L;
        extendedUpperBound = 20L;
        range = new RangeImpl(extendedLowerBound, extendedUpperBound);

    }

    @Test
    void testThatLowerBoundIsCorrect() {

        assertThat(range.getLowerBound(), is(extendedLowerBound));

    }

    @Test
    void testThatUpperBoundIsCorrect() {

        assertThat(range.getUpperBound(), is(extendedUpperBound));

    }


    @Test
    void testThatIsAfterMethodCorrect() {

        RangeImpl rangeAfter = new RangeImpl(extendedUpperBound + 1, extendedUpperBound + 3);

        assertThat(rangeAfter.isAfter(range), is(true));
        assertThat(range.isAfter(rangeAfter), is(false));
    }

    @Test
    void testThatIsBeforeMethodCorrect() {

        RangeImpl rangeBefore = new RangeImpl(5, 9);

        assertThat(rangeBefore.isBefore(range), is(true));
        assertThat(range.isBefore(rangeBefore), is(false));
    }

    @Test
    void testThatContainsMethodCorrect() {

        for (long i = range.getLowerBound(); i < range.getUpperBound(); i++) {

            assertThat(range.contains(i), is(true));
        }
    }

    @Test
    void testThatIsConcurrentRangeMethodCorrect() {

        RangeImpl concurrentRange = new RangeImpl(5, 15);

        assertThat(range.isConcurrent(concurrentRange), is(true));
        assertThat(concurrentRange.isConcurrent(range), is(true));
        assertThat(range.isConcurrent(range), is(true));

    }

    @Test
    void testThatIteratorCorrect() {

        Iterator<Long> iterator = range.asIterator();

        for (long currentValue = range.getLowerBound();
             currentValue < range.getUpperBound();
             currentValue++) {

            assertThat(iterator.next(), is(currentValue));
        }
    }

    @Test
    void testThatIteratorThrowsOutOfRangeException() {

        Iterator<Long> iterator = range.asIterator();
        long last = 0;

        while (iterator.hasNext()) {
            iterator.next();
        }

        Assertions.assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testThatAsListMethodCorrect() {

        List<Long> longs = new ArrayList<>();

        for (long i = range.getLowerBound(); i < range.getUpperBound() + 1; i++) {
            longs.add(i);
        }

        assertThat(range.asList(), is(longs));
    }
}