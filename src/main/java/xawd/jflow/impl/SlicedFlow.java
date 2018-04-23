/**
 *
 */
package xawd.jflow.impl;

import java.util.NoSuchElementException;
import java.util.function.IntUnaryOperator;

import xawd.jflow.AbstractFlow;
import xawd.jflow.Flow;

/**
 * @author t
 *
 */
public final class SlicedFlow<T> extends AbstractFlow<T> {

	private final Flow<T> src;
	private final IntUnaryOperator indexMapping;

	private int indexCount = 0;
	private int checkpoint = -1;
	private int iteratorCount = 0;
	private T cached = null;
	boolean srcExhausted = false;

	public SlicedFlow(final Flow<T> src, final IntUnaryOperator indexMapping)
	{
		this.src = src;
		this.indexMapping = indexMapping;

		// Wrong!, this is not lazy
		updateCheckpoint(indexMapping.applyAsInt(indexCount));
		cacheNextElement();
	}

	private void updateCheckpoint(final int newCheckpoint)
	{
		if (newCheckpoint < checkpoint) {
			throw new IllegalStateException();
		}
		checkpoint = newCheckpoint;
	}

	@Override
	public boolean hasNext() {
		if (srcExhausted) {
			return false;
		}
		else if (cached == null) {
			return cacheNextElement();
		}
		else {
			return true;
		}
	}

	private boolean cacheNextElement()
	{
		assert !srcExhausted && cached == null;
		while (iteratorCount < checkpoint) {
			if (src.hasNext()) {
				src.skip();
				iteratorCount++;
			}
			else {
				srcExhausted = true;
				return false;
			}
		}

		if (src.hasNext()) {
			cached = src.next();
			iteratorCount++;
			updateCheckpoint(indexMapping.applyAsInt(++indexCount));
			return true;
		}
		else {
			srcExhausted = true;
			return false;
		}
	}

	@Override
	public T next() {
		if (srcExhausted) {
			throw new NoSuchElementException();
		}
		else if (cached == null) {
			if (cacheNextElement()) {
				return returnCacheAndErase();
			}
			else {
				throw new NoSuchElementException();
			}
		}
		else {
			return returnCacheAndErase();
		}
	}

	@Override
	public void skip() {
		if (srcExhausted) {
			throw new NoSuchElementException();
		}
		else if (cached == null) {
			if (cacheNextElement()) {
				returnCacheAndErase();
			}
			else {
				throw new NoSuchElementException();
			}
		}
		else {
			returnCacheAndErase();
		}
	}

	private T returnCacheAndErase()
	{
		assert cached != null;
		final T tmp = cached;
		cached = null;
		return tmp;
	}
}
