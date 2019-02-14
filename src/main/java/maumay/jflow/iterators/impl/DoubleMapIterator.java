package maumay.jflow.iterators.impl;

import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

import maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import maumay.jflow.iterators.EnhancedIntIterator;
import maumay.jflow.iterators.EnhancedIterator;
import maumay.jflow.iterators.EnhancedLongIterator;

/**
 * @author ThomasB
 */
public final class DoubleMapIterator
{
	private DoubleMapIterator()
	{
	}

	public static class FromObject<E> extends AbstractEnhancedDoubleIterator
	{
		private final EnhancedIterator<E> sourceFlow;
		private final ToDoubleFunction<? super E> mappingFunction;

		public FromObject(final EnhancedIterator<E> src,
				final ToDoubleFunction<? super E> mappingFunction)
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

	public static class FromLong extends AbstractEnhancedDoubleIterator
	{
		private final EnhancedLongIterator sourceFlow;
		private final LongToDoubleFunction mappingFunction;

		public FromLong(final EnhancedLongIterator src,
				final LongToDoubleFunction mappingFunction)
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

	public static class FromInt extends AbstractEnhancedDoubleIterator
	{
		private final EnhancedIntIterator sourceFlow;
		private final IntToDoubleFunction mappingFunction;

		public FromInt(final EnhancedIntIterator src,
				final IntToDoubleFunction mappingFunction)
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
