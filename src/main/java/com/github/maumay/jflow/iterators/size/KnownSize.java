/**
 * 
 */
package com.github.maumay.jflow.iterators.size;

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
}
