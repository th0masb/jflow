package com.github.maumay.jflow.iterators.implOld;

import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

import com.github.maumay.jflow.iterators.IntIterator;
import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.LongIterator;

/**
 * @author ThomasB
 */
public final class DoubleMapIterator
{
	private DoubleMapIterator()
	{
	}

	public static class FromObject<E> extends AbstractDoubleIterator
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

	public static class FromLong extends AbstractDoubleIterator
	{
		private final LongIterator sourceFlow;
		private final LongToDoubleFunction mappingFunction;

		public FromLong(final LongIterator src,
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

	public static class FromInt extends AbstractDoubleIterator
	{
		private final IntIterator sourceFlow;
		private final IntToDoubleFunction mappingFunction;

		public FromInt(final IntIterator src,
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
