/**
 * 
 */
package com.github.maumay.jflow.impl;

import com.github.maumay.jflow.iterators.Iter;
import com.github.maumay.jflow.iterators.Repeatedly;

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
		super(IteratorSizes.min(sourceOne.getSize(), sourceTwo.getSize()).times(2),
				sourceOne, sourceTwo);
		oneEngaged = true;
	}

	@Override
	public E nextImpl()
	{
		E next = oneEngaged ? getSourceOne().nextImpl() : getSourceTwo().nextImpl();
		oneEngaged = !oneEngaged;
		return next;
	}

	@Override
	public void skipImpl()
	{
		if (oneEngaged) {
			getSourceOne().skipImpl();
		} else {
			getSourceTwo().skipImpl();
		}
		oneEngaged = !oneEngaged;
	}

	@Override
	public boolean hasNext()
	{
		return oneEngaged ? getSourceOne().hasNext() : getSourceTwo().hasNext();
	}

	public static void main(String[] args)
	{
		System.out.println(
				Iter.over("1", "2", "3").interleave(Repeatedly.call(() -> "!")).toVec());
	}
}
