/**
 * 
 */
package com.github.maumay.jflow.impl;

import com.github.maumay.jflow.iterators.Skippable;

/**
 * @author thomasb
 *
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

	public final AbstractIteratorSize getSize()
	{
		return size;
	}

	public final boolean hasOwnership()
	{
		return hasOwnership;
	}

	final void relinquishOwnership()
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

	public abstract void skipImpl();

	public abstract boolean hasNext();
}
