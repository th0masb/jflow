/**
 *
 */
package xawd.jflow.iterators.construction;

import java.util.Iterator;
import java.util.NoSuchElementException;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.iterables.IntFlowIterable;
import xawd.jflow.iterators.misc.IntPair;
import xawd.jflow.iterators.misc.Pair;

/**
 * @author t
 */
public class IterProduct
{
	/**
	 * Experimental and currently untested (which is why it is marked as deprecated).
	 *
	 * @param xs
	 * @param ys
	 * @return
	 */
	@Deprecated
	public static <X, Y> Flow<Pair<X, Y>> of(final Iterable<X> xs, final Iterable<Y> ys)
	{
		return new AbstractFlow<Pair<X,Y>>() {
			Iterator<X> xSource = xs.iterator();
			boolean initialised = false;
			X currentX;

			Iterator<Y> ySource = ys.iterator();

			void init() {
				assert !initialised;
				if (xSource.hasNext()) {
					currentX = xSource.next();
					initialised = true;
				}
			}
			@Override
			public boolean hasNext() {
				if (!initialised) {
					init();
				}
				if (currentX == null) {
					return false;
				}
				else{
					return ySource.hasNext();
				}

			}

			@Override
			public Pair<X, Y> next() {
				if (!initialised) {
					init();
				}

				if (currentX == null) {
					throw new NoSuchElementException();
				}
				else{
					final Pair<X, Y> next = Pair.of(currentX, ySource.next());
					if (!ySource.hasNext() && xSource.hasNext()) {
						currentX = xSource.next();
						ySource = ys.iterator();
					}
					return next;
				}
			}

			@Override
			public void skip() {
				next();
			}
		};
	}

	public static Flow<IntPair> of(final IntFlowIterable first, final IntFlowIterable second)
	{
		return new AbstractFlow<IntPair>() {

			IntFlow xSource = first.iter();
			boolean initialised = false;

			boolean xExhausted = false;
			int currentX;

			IntFlow ySource = second.iter();

			void init() {
				assert !initialised;
				initialised = true;
				if (xSource.hasNext()) {
					currentX = xSource.nextInt();
				}
				else {
					xExhausted = true;
				}
			}
			@Override
			public boolean hasNext() {
				if (!initialised) {
					init();
				}
				if (xExhausted) {
					return false;
				}
				else{
					return ySource.hasNext();
				}

			}

			@Override
			public IntPair next() {
				if (!initialised) {
					init();
				}

				if (xExhausted) {
					throw new NoSuchElementException();
				}
				else{
					final IntPair next = IntPair.of(currentX, ySource.nextInt());

					if (!ySource.hasNext()) {
						if (xSource.hasNext()) {
							currentX = xSource.nextInt();
							ySource = second.iter();
						}
						else {
							xExhausted = true;
						}
					}
					return next;
				}
			}

			@Override
			public void skip() {
				next();
			}
		};
	}
}
