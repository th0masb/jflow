/**
 *
 */
package com.github.maumay.jflow.impl;

/**
 * @author thomasb
 *
 */
public final class InfiniteSize extends AbstractIteratorSize
{
    private static final InfiniteSize INSTANCE = new InfiniteSize();

    private InfiniteSize()
    {
        super(SizeType.INFINITE);
    }

    public static InfiniteSize instance()
    {
        return INSTANCE;
    }

    @Override
    public InfiniteSize copy()
    {
        return this;
    }

    @Override
    void decrement()
    {
        // Do nothing.
    }

    @Override
    AbstractIteratorSize addImpl(int value)
    {
        return this;
    }

    @Override
    AbstractIteratorSize subtractImpl(int value)
    {
        return this;
    }

    @Override
    AbstractIteratorSize minImpl(int value)
    {
        return new KnownSize(value);
    }

    @Override
    public AbstractIteratorSize dropLowerBound()
    {
        return new LowerBound(0);
    }

    @Override
    AbstractIteratorSize timesImpl(int value)
    {
        return this;
    }
}
