/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;

/**
 * @author ThomasB
 *
 */
public class MapFlow
{
	private MapFlow() {}

	public static class OfObject<E, R> extends AbstractFlow<R>
	{
		private final Flow<E> sourceFlow;
		private final Function<? super E, R> mappingFunction;

		public OfObject(final Flow<E> sourceFlow, final Function<? super E, R> mappingFunction)
		{
			super(sourceFlow.size());
			this.sourceFlow = sourceFlow;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public boolean hasNext()
		{
			return sourceFlow.hasNext();
		}

		@Override
		public R next()
		{
			return mappingFunction.apply(sourceFlow.next());
		}

		@Override
		public void skip()
		{
			sourceFlow.skip();
		}
	}

	public static class OfLong extends AbstractLongFlow
	{
		private final LongFlow sourceFlow;
		private final LongUnaryOperator mappingFunction;

		public OfLong(final LongFlow src, final LongUnaryOperator mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public long nextLong()
		{
			return mappingFunction.applyAsLong(sourceFlow.nextLong());
		}

		@Override
		public boolean hasNext()
		{
			return sourceFlow.hasNext();
		}

		@Override
		public void skip()
		{
			sourceFlow.skip();
		}
	}

	public static class OfDouble extends AbstractDoubleFlow
	{
		private final DoubleFlow sourceFlow;
		private final DoubleUnaryOperator mappingFunction;

		public OfDouble(final DoubleFlow src, final DoubleUnaryOperator mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public double nextDouble()
		{
			return mappingFunction.applyAsDouble(sourceFlow.nextDouble());
		}

		@Override
		public boolean hasNext()
		{
			return sourceFlow.hasNext();
		}

		@Override
		public void skip()
		{
			sourceFlow.skip();
		}
	}

	public static class OfInt extends AbstractIntFlow
	{
		private final IntFlow sourceFlow;
		private final IntUnaryOperator mappingFunction;

		public OfInt(final IntFlow src, final IntUnaryOperator mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public int nextInt()
		{
			return mappingFunction.applyAsInt(sourceFlow.nextInt());
		}

		@Override
		public boolean hasNext()
		{
			return sourceFlow.hasNext();
		}

		@Override
		public void skip()
		{
			sourceFlow.skip();
		}
	}
}
