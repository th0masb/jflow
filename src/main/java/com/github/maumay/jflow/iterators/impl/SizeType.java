/**
 * 
 */
package com.github.maumay.jflow.iterators.impl;

/**
 * @author thomasb
 *
 */
public enum SizeType
{
	EXACT(KnownSize.class), BOUNDED(BoundedSize.class), LOWER_BOUND(LowerBound.class),
	UPPER_BOUND(UpperBound.class), UNKNOWN(UnknownSize.class);

	@SuppressWarnings("unused")
	private final Class<? extends AbstractIteratorSize> associatedClass;

	private SizeType(Class<? extends AbstractIteratorSize> associatedClass)
	{
		this.associatedClass = associatedClass;
	}

	public boolean isKnown()
	{
		return this == EXACT;
	}

	public boolean isUnknown()
	{
		return !isKnown();
	}

}
