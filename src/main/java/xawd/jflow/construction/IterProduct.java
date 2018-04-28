/**
 *
 */
package xawd.jflow.construction;

import java.util.Iterator;
import java.util.NoSuchElementException;

import xawd.jflow.AbstractFlow;
import xawd.jflow.Flow;
import xawd.jflow.zippedpairs.Pair;

/**
 * @author t
 *
 */
public class IterProduct
{
	public static <X, Y> Flow<Pair<X, Y>> of(final Iterable<X> xs, final Iterable<Y> ys)
	{
		return new AbstractFlow<Pair<X,Y>>() {
			Iterator<X> iterX = xs.iterator();
			X current = null;
			{
				if (iterX.hasNext()) {
					current = iterX.next();
				}
			}

			Iterator<Y> iterY = ys.iterator();

			@Override
			public boolean hasNext() {
				return iterX.hasNext() && iterY.hasNext();
			}

			@Override
			public Pair<X, Y> next() {
				if (current == null) {
					throw new NoSuchElementException();
				}
				final Pair<X, Y> retPair = Pair.of(current, iterY.next());
				if (!iterY.hasNext()) {
					iterY = ys.iterator();
					current = iterX.hasNext()? iterX.next() : null;
				}
				return retPair;
			}

			@Override
			public void skip() {
				if (current == null) {
					throw new NoSuchElementException();
				}
				iterY.next();
				if (!iterY.hasNext()) {
					iterY = ys.iterator();
					current = iterX.hasNext()? iterX.next() : null;
				}
			}
		};
	}
}
