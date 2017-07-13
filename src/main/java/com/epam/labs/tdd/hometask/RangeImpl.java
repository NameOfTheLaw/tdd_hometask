package com.epam.labs.tdd.hometask;

import java.util.*;


public class RangeImpl implements Range {

    private long from;
    private long to;

    public RangeImpl(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isBefore(Range otherRange) {
        return otherRange.isAfter(this);
    }

    @Override
    public boolean isAfter(Range otherRange) {
        return this.getLowerBound() > otherRange.getUpperBound();
    }

    @Override
    public boolean isConcurrent(Range otherRange) {
        return this.contains(otherRange.getLowerBound()) ||
                this.contains(otherRange.getUpperBound());
    }

    @Override
    public long getLowerBound() {
        return from;
    }

    @Override
    public long getUpperBound() {
        return to;
    }

    @Override
    public boolean contains(long value) {
        return value <= getUpperBound() &&
                value >= getLowerBound();
    }

    @Override
    public List<Long> asList() {
        ArrayList<Long> longs = new ArrayList<>();
        asIterator().forEachRemaining(longs::add);
        return longs;
    }

    @Override
    public Iterator<Long> asIterator() {
        return new Iterator<Long>() {
            long currentValue = from - 1;

            @Override
            public boolean hasNext() {

                return currentValue < to;

            }

            @Override
            public Long next() {

                if (hasNext()) {
                    return ++currentValue;
                }

                throw new NoSuchElementException();
            }
        };
    }
}
