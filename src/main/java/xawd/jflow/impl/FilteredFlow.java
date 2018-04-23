package xawd.jflow.impl;

import java.util.NoSuchElementException;
import java.util.function.Predicate;

import xawd.jflow.AbstractFlow;
import xawd.jflow.Flow;

/**
 * @author ThomasB
 * @since 23 Apr 2018
 */
public class FilteredFlow<T> extends AbstractFlow<T>
{
	private final Flow<T> src;
	private final Predicate<? super T> predicate;
	
	private T cached = null;
	
	public FilteredFlow(final Flow<T> src, final Predicate<? super T> predicate)
	{
		this.src = src;
		this.predicate = predicate;
	}

	@Override
	public boolean hasNext()
	{
		while (cached == null && src.hasNext()) {
			final T next = src.next();
			if (predicate.test(next)) {
				cached = next;
			}
		}
		return cached != null;
	}

	@Override
	public T next()
	{
		if (hasNext()) {
			final T tmp = cached;
			cached = null;
			return tmp;
		}
		else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void skip()
	{
		if (hasNext()) {
			cached = null;
		}
		else {
			throw new NoSuchElementException();
		}
	}
}
