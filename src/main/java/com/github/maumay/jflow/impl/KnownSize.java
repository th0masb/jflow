/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.Objects;

/**
 * @author thomasb
 *
 */
public final class KnownSize extends AbstractValueSize
{
    KnownSize(int size)
    {
        super(SizeType.EXACT, size);
    }

    public static KnownSize of(int size)
    {
        return new KnownSize(IteratorSizes.requireNonNegative(size));
    }

    @Override
    public KnownSize copy()
    {
        return new KnownSize(getValue());
    }

    @Override
    AbstractIteratorSize addImpl(int value)
    {
        return new KnownSize(getValue() + value);
    }

    @Override
    AbstractIteratorSize subtractImpl(int value)
    {
        return new KnownSize(Math.max(0, getValue() - value));
    }

    @Override
    AbstractIteratorSize minImpl(int value)
    {
        return new KnownSize(Math.min(getValue(), value));
    }

    @Override
    public AbstractIteratorSize dropLowerBound()
    {
        return new BoundedSize(0, getValue());
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof KnownSize) {
            KnownSize other = (KnownSize) obj;
            return getValue() == other.getValue();
        } else if (obj instanceof BoundedSize) {
            BoundedSize other = (BoundedSize) obj;
            return getValue() == other.lower() && getValue() == other.upper();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getValue(), getValue());
    }

    @Override
    AbstractIteratorSize timesImpl(int value)
    {
        return new KnownSize(getValue() * value);
    }
}
