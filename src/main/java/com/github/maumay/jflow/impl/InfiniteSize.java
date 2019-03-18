/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.OptionalInt;

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
	public OptionalInt getExactSize()
	{
		return OptionalInt.empty();
	}

	@Override
	public OptionalInt getUpperBound()
	{
		return OptionalInt.empty();
	}

	@Override
	public OptionalInt getLowerBound()
	{
		return OptionalInt.empty();
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
	public boolean isSingleton()
	{
		return true;
	}
}
