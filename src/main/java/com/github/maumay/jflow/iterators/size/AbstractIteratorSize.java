/**
 * 
 */
package com.github.maumay.jflow.iterators.size;

import java.util.OptionalInt;

import com.github.maumay.jflow.utils.Option;

/**
 * @author thomasb
 *
 */
public abstract class AbstractIteratorSize
{
	private final SizeType type;

	public AbstractIteratorSize(SizeType type)
	{
		this.type = type;
	}

	public SizeType getType()
	{
		return type;
	}

	public final OptionalInt getExactSize()
	{
		return type.isKnown() ? Option.of(((KnownSize) this).getValue()) : Option.emptyInt();
	}

	public abstract void decrement();
}
