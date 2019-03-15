/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * @author thomasb
 *
 */
public final class FlatmapAdapter<E, R>
		extends AbstractIteratorAdapter.OfObject<AbstractEnhancedIterator<? extends E>, R>
{
	private final Function<? super E, ? extends Iterator<? extends R>> map;

	private Iterator<? extends R> currentIterator;

	public FlatmapAdapter(AbstractEnhancedIterator<? extends E> source,
			Function<? super E, ? extends Iterator<? extends R>> map)
	{
		super(UnknownSize.instance(), source);
		this.map = map;
	}

	private void init()
	{
		while ((currentIterator == null || !currentIterator.hasNext())
				&& getSource().hasNext()) {
			currentIterator = map.apply(getSource().nextImpl());
		}
	}

	@Override
	public boolean hasNext()
	{
		if (currentIterator == null) {
			init();
		}
		return currentIterator != null && currentIterator.hasNext();
	}

	@Override
	public R nextImpl()
	{
		if (hasNext()) {
			R next = currentIterator.next();
			while (!currentIterator.hasNext() && getSource().hasNext()) {
				currentIterator = map.apply(getSource().nextImpl());
			}
			return next;
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void skipImpl()
	{
		nextImpl();
	}
}
