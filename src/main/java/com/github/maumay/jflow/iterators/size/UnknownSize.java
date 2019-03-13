/**
 * 
 */
package com.github.maumay.jflow.iterators.size;

/**
 * @author thomasb
 *
 */
public class UnknownSize extends AbstractIteratorSize
{
	private static final UnknownSize INSTANCE = new UnknownSize();

	private UnknownSize()
	{
		super(SizeType.UNKNOWN);
	}

	public static UnknownSize instance()
	{
		return INSTANCE;
	}

	@Override
	public void decrement()
	{
		// Nothing to do.
	}
}
