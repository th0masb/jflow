/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.Objects;

/**
 * Represents the four different possible states of the size of an iterator.
 * 
 * @author thomasb
 */
public enum SizeType
{
	EXACT(KnownSize.class), BOUNDED(BoundedSize.class), LOWER_BOUND(LowerBound.class),
	INFINITE(InfiniteSize.class);

	// Just to emphasize that we want to treat this enum as an
	// algebraic data type like in rust or haskell.
	@SuppressWarnings("unused")
	private final Class<? extends AbstractIteratorSize> associatedClass;

	private SizeType(Class<? extends AbstractIteratorSize> associatedClass)
	{
		this.associatedClass = Objects.requireNonNull(associatedClass);
	}
}
