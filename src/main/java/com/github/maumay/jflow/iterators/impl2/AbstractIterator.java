/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

import com.github.maumay.jflow.iterators.Skippable;
import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author thomasb
 *
 */
public abstract class AbstractIterator implements Skippable
{
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

	final boolean hasOwnership()
	{
		return hasOwnership;
	}

	final void relinquishOwnership()
	{
		// Can't relinquish ownership twice!
		Exceptions.require(hasOwnership);
		hasOwnership = false;
	}

	// Skippable API
	@Override
	public final void skip()
	{
		if (hasOwnership()) {
			getSize().decrement();
			skipImpl();
		} else {
			throw new RuntimeException();
		}
	}

	public abstract void skipImpl();
}
