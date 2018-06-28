package xawd.jflow.iterators.impl;

import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;

/**
 * @author ThomasB
 */
public final class MapToDoubleFlow
{
	private MapToDoubleFlow() {}

	public static class FromObject<E> extends AbstractDoubleFlow
	{
		private final Flow<E> sourceFlow;
		private final ToDoubleFunction<? super E> mappingFunction;

		public FromObject(final Flow<E> src, final ToDoubleFunction<? super E> mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public double nextDouble()
		{
			return mappingFunction.applyAsDouble(sourceFlow.next());
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

	public static class FromLong extends AbstractDoubleFlow
	{
		private final LongFlow sourceFlow;
		private final LongToDoubleFunction mappingFunction;

		public FromLong(final LongFlow src, final LongToDoubleFunction mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public double nextDouble()
		{
			return mappingFunction.applyAsDouble(sourceFlow.nextLong());
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

	public static class FromInt extends AbstractDoubleFlow
	{
		private final IntFlow sourceFlow;
		private final IntToDoubleFunction mappingFunction;

		public FromInt(final IntFlow src, final IntToDoubleFunction mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public double nextDouble()
		{
			return mappingFunction.applyAsDouble(sourceFlow.nextInt());
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
