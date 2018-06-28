/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.function.DoubleToIntFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ToIntFunction;

import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.LongFlow;

/**
 * @author ThomasB
 *
 */
public final class MapToIntFlow
{
	public MapToIntFlow() {}

	public static class FromObject<E> extends AbstractIntFlow
	{
		private final Flow<E> sourceFlow;
		private final ToIntFunction<? super E> mappingFunction;

		public FromObject(final Flow<E> src, final ToIntFunction<? super E> mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public int nextInt()
		{
			return mappingFunction.applyAsInt(sourceFlow.next());
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

	public static class FromLong extends AbstractIntFlow
	{
		private final LongFlow sourceFlow;
		private final LongToIntFunction mappingFunction;

		public FromLong(final LongFlow src, final LongToIntFunction mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public int nextInt()
		{
			return mappingFunction.applyAsInt(sourceFlow.nextLong());
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

	public static class FromDouble extends AbstractIntFlow
	{
		private final DoubleFlow sourceFlow;
		private final DoubleToIntFunction mappingFunction;

		public FromDouble(final DoubleFlow src, final DoubleToIntFunction mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public int nextInt()
		{
			return mappingFunction.applyAsInt(sourceFlow.nextDouble());
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
