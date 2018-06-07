/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.function.BiFunction;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.Flow;

/**
 * @author ThomasB
 *
 */
public final class PairFoldFlow
{
	private PairFoldFlow() {}

	public static class OfObject<E, R> extends AbstractFlow<R>
	{
		private final Flow<E> source;
		private final BiFunction<? super E, ? super E, R> foldFunction;

		private E cached;

		public OfObject(final Flow<E> source, final BiFunction<? super E, ? super E, R> foldFunction)
		{
			this.source = source;
			this.foldFunction = foldFunction;
			this.cached = source.hasNext()? source.next() : null;
		}

		@Override
		public boolean hasNext()
		{
			return source.hasNext();
		}

		@Override
		public R next()
		{
			final E nextFromSource = source.next();
			final R result = foldFunction.apply(cached, nextFromSource);
			cached = nextFromSource;
			return result;
		}

		@Override
		public void skip()
		{
			next();
		}
	}
}
