/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

/**
 * @author thomasb
 *
 */
public abstract class AbstractIteratorBiAdapter<E1, E2, R> extends AbstractEnhancedIterator<R>
{
	private final AbstractEnhancedIterator<? extends E1> sourceOne;
	private final AbstractEnhancedIterator<? extends E2> sourceTwo;

	public AbstractIteratorBiAdapter(AbstractIteratorSize size,
			AbstractEnhancedIterator<? extends E1> sourceOne,
			AbstractEnhancedIterator<? extends E2> sourceTwo)
	{
		super(size);
		this.sourceOne = sourceOne;
		this.sourceTwo = sourceTwo;
		sourceOne.relinquishOwnership();
		sourceTwo.relinquishOwnership();
	}

	protected AbstractEnhancedIterator<? extends E1> getSourceOne()
	{
		return sourceOne;
	}

	protected AbstractEnhancedIterator<? extends E2> getSourceTwo()
	{
		return sourceTwo;
	}
}
