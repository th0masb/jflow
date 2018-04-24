/**
 *
 */
package xawd.jflow.impl;

import java.util.NoSuchElementException;
import java.util.function.Predicate;

import xawd.jflow.AbstractFlow;
import xawd.jflow.Flow;

/**
 * @author t
 *
 */
public class DropWhileFlow<T> extends AbstractFlow<T> {

	private final Flow<T> src;
	private final Predicate<? super T> predicate;

	private boolean firstFailureConsumed = false;
	private T firstFailure = null;

	public DropWhileFlow(final Flow<T> src, final Predicate<? super T> predicate) {
		this.src = src;
		this.predicate = predicate;
	}

	@Override
	public boolean hasNext()
	{
		if (firstFailure == null) {
			while (firstFailure == null && src.hasNext()) {
				final T next = src.next();
				if (!predicate.test(next)) {
					firstFailure = next;
					return true;
				}
			}
			return false;
		}
		else if (!firstFailureConsumed) {
			return true;
		}
		else {
			return src.hasNext();
		}
	}

	@Override
	public T next() {
		if (firstFailure == null) {
			if (hasNext()) {
				firstFailureConsumed = true;
				return firstFailure;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		else if (firstFailureConsumed) {
			return src.next();
		}
		else {
			firstFailureConsumed = true;
			return firstFailure;
		}
	}

	@Override
	public void skip() {
		if (firstFailure == null) {
			if (hasNext()) {
				firstFailureConsumed = true;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		else if (firstFailureConsumed) {
			src.skip();
		}
		else {
			firstFailureConsumed = true;
		}
	}
}
