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
		super(SizeType.EXACT, size);
	}

	@Override
	public OptionalInt getMinimalUpperBound()
	{
		return Option.of(getValue());
	}

	@Override
	public OptionalInt getMaximalLowerBound()
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
