/**
 *
 */
package com.github.maumay.jflow.impl;

/**
 * @author thomasb
 *
 */
public abstract class AbstractValueSize extends AbstractIteratorSize
{
    private int value;

    protected AbstractValueSize(SizeType type, int value)
    {
        super(type);
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    @Override
    void decrement()
    {
        value = Math.max(0, value - 1);
    }

    @Override
    public String toString()
    {
        return new StringBuilder(getType().name()).append("(").append(value)
                .append(")")
                .toString();
    }
}
