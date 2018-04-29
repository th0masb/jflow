package xawd.jflow.impl;

import java.util.NoSuchElementException;
import java.util.function.Function;

import xawd.jflow.AbstractFlow;
import xawd.jflow.Flow;

/**
 * @author t
 */
public final class FlattenedFlow
{
	private FlattenedFlow() {}

	public static class OfObject<T, R> extends AbstractFlow<R>
	{
		private final Flow<T> src;
		private final Function<? super T, ? extends Flow<? extends R>> mapping;

		private Flow<? extends R> currentSubFlow;

		public OfObject(Flow<T> src, Function<? super T, ? extends Flow<? extends R>> mapping) {
			this.src = src;
			this.mapping = mapping;
		}

		private void init()
		{
			if (src.hasNext()) {
				currentSubFlow = mapping.apply(src.next());
			}
		}

		@Override
		public boolean hasNext() {
			if (currentSubFlow == null) {
				init();
			}
			return currentSubFlow != null && currentSubFlow.hasNext();
		}

		@Override
		public R next() {
			if (currentSubFlow == null) {
				init();
			}

			if (currentSubFlow == null) {
				throw new NoSuchElementException();
			}
			else {
				final R next = currentSubFlow.next();
				if (!currentSubFlow.hasNext() && src.hasNext()) {
					currentSubFlow = mapping.apply(src.next());
				}
				return next;
			}
		}

		@Override
		public void skip() {
			next();
		}

	}
}
