/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.function.DoubleFunction;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;

/**
 * @author ThomasB
 *
 */
public final class MapToObjectFlow
{
	private MapToObjectFlow() {}

	public static class FromLong<E> extends AbstractFlow<E>
	{
		private final LongFlow sourceFlow;
		private final LongFunction<E> mappingFunction;

		public FromLong(final LongFlow src, final LongFunction<E> mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public boolean hasNext()
		{
			return sourceFlow.hasNext();
		}

		@Override
		public E next()
		{
			return mappingFunction.apply(sourceFlow.nextLong());
		}

		@Override
		public void skip()
		{
			sourceFlow.skip();
		}
	}

	public static class FromInt<E> extends AbstractFlow<E>
	{
		private final IntFlow sourceFlow;
		private final IntFunction<E> mappingFunction;

		public FromInt(final IntFlow src, final IntFunction<E> mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public boolean hasNext()
		{
			return sourceFlow.hasNext();
		}

		@Override
		public E next()
		{
			return mappingFunction.apply(sourceFlow.nextInt());
		}

		@Override
		public void skip()
		{
			sourceFlow.skip();
		}
	}

	public static class FromDouble<E> extends AbstractFlow<E>
	{
		private final DoubleFlow sourceFlow;
		private final DoubleFunction<E> mappingFunction;

		public FromDouble(final DoubleFlow src, final DoubleFunction<E> mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public boolean hasNext()
		{
			return sourceFlow.hasNext();
		}

		@Override
		public E next()
		{
			return mappingFunction.apply(sourceFlow.nextDouble());
		}

		@Override
		public void skip()
		{
			sourceFlow.skip();
		}
	}
}
