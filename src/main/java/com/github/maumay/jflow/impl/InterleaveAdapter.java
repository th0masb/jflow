/**
 * 
 */
package com.github.maumay.jflow.impl;

/**
 * @author thomasb
 *
 */
public final class InterleaveAdapter<E> extends
		AbstractIteratorBiAdapters.OfObject<AbstractRichIterator<E>, AbstractRichIterator<? extends E>, E>
{
	private boolean oneEngaged;

	public InterleaveAdapter(AbstractRichIterator<E> sourceOne,
			AbstractRichIterator<? extends E> sourceTwo)
	{
		super(IteratorSizes.min(sourceOne.getSize(), sourceTwo.getSize()), sourceOne, sourceTwo);
		oneEngaged = true;
	}

	@Override
	public E nextImpl()
	{
		oneEngaged = !oneEngaged;
		return oneEngaged ? getSourceTwo().nextImpl() : getSourceOne().nextImpl();
	}

	@Override
	public void skipImpl()
	{
		oneEngaged = !oneEngaged;
		if (oneEngaged) {
			getSourceTwo().skipImpl();
		} else {
			getSourceOne().skipImpl();
		}
	}

	@Override
	public boolean hasNext()
	{
		return oneEngaged ? getSourceOne().hasNext() : getSourceTwo().hasNext();
	}
}
