/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.OptionalInt;

import com.github.maumay.jflow.utils.Option;

/**
 * @author thomasb
 *
 */
public final class KnownSize extends AbstractValueSize
{
	public KnownSize(int size)
	{
		super(SizeType.EXACT, IteratorImplUtils.requireNonNegative(size));
	}

	@Override
	public OptionalInt getUpperBound()
	{
		return Option.of(getValue());
	}

	@Override
	public OptionalInt getLowerBound()
	{
		return Option.of(getValue());
	}

	@Override
	public OptionalInt getExactSize()
	{
		return Option.of(getValue());
	}

	@Override
	public KnownSize copy()
	{
		return new KnownSize(getValue());
	}
}
