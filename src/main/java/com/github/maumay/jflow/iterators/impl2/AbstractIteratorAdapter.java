/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

import com.github.maumay.jflow.iterators.size.AbstractIteratorSize;

/**
 * @author thomasb
 *
 */
public abstract class AbstractIteratorAdapter<E, R> extends AbstractEnhancedIterator<R>
{
	private final AbstractEnhancedIterator<E> source;

	public AbstractIteratorAdapter(AbstractIteratorSize size, AbstractEnhancedIterator<E> source)
	{
		super(size);
		this.source = source;
		// The source is now considered locked and can only be consumed indirectly via
		// this iterator.
		source.removeOwnership();
	}

	protected final AbstractEnhancedIterator<E> getSource()
	{
		return source;
	}
}
