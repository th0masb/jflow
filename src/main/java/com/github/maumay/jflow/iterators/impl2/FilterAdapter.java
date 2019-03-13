/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * @author thomasb
 *
 */
public final class FilterAdapter<E> extends AbstractIteratorAdapter<E, E>
{
	private final Predicate<? super E> predicate;

	private E cached;

	public FilterAdapter(AbstractEnhancedIterator<E> source, Predicate<? super E> predicate)
	{
		super(computeNewSize(source), source);
		this.predicate = predicate;
	}

	static AbstractIteratorSize computeNewSize(AbstractEnhancedIterator<?> source)
	{
		AbstractIteratorSize sourceSize = source.getSize();
		switch (sourceSize.getType()) {
		case EXACT:
			return new UpperBound(KnownSize.class.cast(sourceSize).getValue());
		case UPPER_BOUND:
			return sourceSize;
		default:
			return UnknownSize.instance();
		}
	}

	@Override
	public boolean hasNext()
	{
		while (cached == null && getSource().hasNext()) {
			E next = getSource().nextImpl();
			if (predicate.test(next)) {
				cached = next;
				break;
			}
		}
		return cached != null;
	}

	@Override
	public E nextImpl()
	{
		if (hasNext()) {
			E tmp = cached;
			cached = null;
			return tmp;
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void skipImpl()
	{
		if (hasNext()) {
			cached = null;
		} else {
			throw new NoSuchElementException();
		}
	}
}
