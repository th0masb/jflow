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
public final class LowerBound extends AbstractValueSize
{
	public LowerBound(int size)
	{
		super(SizeType.LOWER_BOUND, size);
	}

	@Override
	public OptionalInt getMinimalUpperBound()
	{
		return Option.emptyInt();
	}

	@Override
	public OptionalInt getMaximalLowerBound()
	{
		return Option.of(getValue());
	}

	@Override
	public OptionalInt getExactSize()
	{
		return OptionalInt.empty();
	}

	@Override
	public LowerBound copy()
	{
		return new LowerBound(getValue());
	}
}
