/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

/**
 * @author thomasb
 *
 */
public final class ConcatenationAdapter<E> extends AbstractIteratorBiAdapter<E, E, E>
{
	public ConcatenationAdapter(AbstractEnhancedIterator<? extends E> sourceOne,
			AbstractEnhancedIterator<? extends E> sourceTwo)
	{
		super(AbstractIteratorSize.combine(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
				sourceTwo);
	}

	@Override
	public boolean hasNext()
	{
		return getSourceOne().hasNext() || getSourceTwo().hasNext();
	}

	@Override
	public E nextImpl()
	{
		return getSourceOne().hasNext() ? getSourceOne().nextImpl() : getSourceTwo().nextImpl();
	}

	@Override
	public void skipImpl()
	{
		if (getSourceOne().hasNext()) {
			getSourceOne().skipImpl();
		} else {
			getSourceTwo().skipImpl();
		}
	}
}
