/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.function.DoubleToLongFunction;
import java.util.function.IntToLongFunction;
import java.util.function.ToLongFunction;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;

/**
 * @author ThomasB
 */
public final class MapToLongFlow
{
	private MapToLongFlow() {}

	public static class FromObject<E> extends AbstractLongFlow
	{
		private final Flow<E> sourceFlow;
		private final ToLongFunction<? super E> mappingFunction;

		public FromObject(final Flow<E> src, final ToLongFunction<? super E> mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public long nextLong()
		{
			return mappingFunction.applyAsLong(sourceFlow.next());
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

	public static class FromInt extends AbstractLongFlow
	{
		private final IntFlow sourceFlow;
		private final IntToLongFunction mappingFunction;

		public FromInt(final IntFlow src, final IntToLongFunction mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public long nextLong()
		{
			return mappingFunction.applyAsLong(sourceFlow.nextInt());
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

	public static class FromDouble extends AbstractLongFlow
	{
		private final DoubleFlow sourceFlow;
		private final DoubleToLongFunction mappingFunction;

		public FromDouble(final DoubleFlow src, final DoubleToLongFunction mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public long nextLong()
		{
			return mappingFunction.applyAsLong(sourceFlow.nextDouble());
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
