/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

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
		super(SizeType.EXACT, requireNonNegative(size));
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
}
