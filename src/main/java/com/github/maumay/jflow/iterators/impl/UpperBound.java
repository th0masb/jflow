/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.OptionalInt;

import com.github.maumay.jflow.utils.Option;

/**
 * @author thomasb
 *
 */
public class UpperBound extends AbstractValueSize
{
	public UpperBound(int value)
	{
		super(SizeType.UPPER_BOUND, value);
	}

	@Override
	public OptionalInt getUpperBound()
	{
		return Option.of(getValue());
	}

	@Override
	public OptionalInt getLowerBound()
	{
		return Option.emptyInt();
	}

	@Override
	public OptionalInt getExactSize()
	{
		return OptionalInt.empty();
	}
}
