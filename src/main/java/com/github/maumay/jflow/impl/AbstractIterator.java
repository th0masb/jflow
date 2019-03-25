/**
 * 
 */
package com.github.maumay.jflow.impl;

import com.github.maumay.jflow.iterators.Skippable;

/**
 * Abstract supertype of all rich iterator implementations which adds the notion
 * of size, ownership and skipping.
 * 
 * @author thomasb
 */
public abstract class AbstractIterator implements Skippable
{
	protected static final String OWNERSHIP_ERR_MSG = "Ownership has been reliquished!";

	/**
	 * The sizing information for this iterator
	 */
	private final AbstractIteratorSize size;

	/**
	 * Flag indicating whether this iterator has ownership over its {@link #next()}
	 * and {@link #skip()} methods. If it does not possess ownership (in the case it
	 * has been adapted) then calling these methods will throw an exception.
	 */
	private boolean hasOwnership;

	public AbstractIterator(AbstractIteratorSize size)
	{
		this.size = size;
		this.hasOwnership = true;
	}

	/**
	 * Retrieve the size of this iterator.
	 * 
	 * @return The size of this iterator.
	 */
	public final AbstractIteratorSize getSize()
	{
		return size;
	}

	/**
	 * Retrieve flag indicating whether this iterator still possesses ownership of
	 * its skip and next methods.
	 * 
	 * @return The ownership flag of this iterator.
	 */
	public final boolean hasOwnership()
	{
		return hasOwnership;
	}

	/**
	 * Instructs this iterator to relinquish ownership of itself. This can only be
	 * called once and should only be used when implementing custom iterators.
	 */
	public final void relinquishOwnership()
	{
		// Can't relinquish ownership twice!
		if (hasOwnership) {
			hasOwnership = false;
		} else {
			throw new IteratorOwnershipException(OWNERSHIP_ERR_MSG);
		}
	}

	// Skippable API
	@Override
	public final void skip()
	{
		if (hasOwnership()) {
			getSize().decrement();
			skipImpl();
		} else {
			throw new IteratorOwnershipException(OWNERSHIP_ERR_MSG);
		}
	}

	/**
	 * Implementation for the {@link #skip()} method, it does not check for
	 * ownership and therefore should only be used in custom iterator
	 * implementation.
	 */
	public abstract void skipImpl();

	/**
	 * Checks if this iterator still has more elements to traverse.
	 * 
	 * @return Whether there are more elements to traverse.
	 */
	public abstract boolean hasNext();
}
