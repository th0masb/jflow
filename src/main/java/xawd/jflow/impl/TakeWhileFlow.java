package xawd.jflow.impl;

import java.util.NoSuchElementException;
import java.util.function.Predicate;

import xawd.jflow.AbstractFlow;
import xawd.jflow.Flow;

/**
 * @author ThomasB
 * @since 23 Apr 2018
 */
public final class TakeWhileFlow<T> extends AbstractFlow<T>
{
	private final Flow<T> src;
	private final Predicate<? super T> predicate;
	
	private T cached = null;
	private boolean iteratorExhausted = false;
	
	public TakeWhileFlow(final Flow<T> src, final Predicate<? super T> predicate) 
	{
		this.src = src;
		this.predicate = predicate;
	}
	
	@Override
	public boolean hasNext()
	{
		if (cached != null) {
			return true;
		}
		else if(src.hasNext()) {
			final T tmp  = src.next();
			if (predicate.test(tmp)) {
				cached = tmp;
				return true;
			}
			else {
				iteratorExhausted = true;
				return false;
			}
		}
		else {
			iteratorExhausted = true;
			return false;
		}
	}

	@Override
	public T next()
	{
		if (iteratorExhausted) {
			throw new NoSuchElementException();
		}
		else if (cached != null) {
			final T tmp = cached;
			cached = null;
			return tmp;
		}
		else { // hasnext wasn't called
			if (hasNext()) {
				final T tmp = cached;
				cached = null;
				return tmp;
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}

	@Override
	public void skip()
	{
		if (iteratorExhausted) {
			throw new NoSuchElementException();
		}
		else if (cached != null) {
			cached = null;
		}
		else { // hasnext wasn't called
			if (hasNext()) {
				cached = null;
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}
}
