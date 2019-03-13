/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

/**
 * @author thomasb
 *
 */
public abstract class AbstractIteratorAdapter<E, R> extends AbstractEnhancedIterator<R>
{
	private final AbstractEnhancedIterator<? extends E> source;

	public AbstractIteratorAdapter(AbstractIteratorSize size,
			AbstractEnhancedIterator<? extends E> source)
	{
		super(size);
		this.source = source;

		// The source is now considered locked and can only be consumed indirectly via
		// this iterator.
		source.relinquishOwnership();
	}

	protected final AbstractEnhancedIterator<? extends E> getSource()
	{
		return source;
	}
}
