/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.function.DoubleFunction;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.DoubleIterator;
import com.github.maumay.jflow.iterators.IntIterator;
import com.github.maumay.jflow.iterators.LongIterator;

/**
 * @author ThomasB
 *
 */
public final class ObjectMapIterator
{
	private ObjectMapIterator()
	{
	}

	public static class FromLong<E> extends AbstractEnhancedIterator<E>
	{
		private final LongIterator sourceFlow;
		private final LongFunction<? extends E> mappingFunction;

		public FromLong(LongIterator src,
				LongFunction<? extends E> mappingFunction)
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

	public static class FromInt<E> extends AbstractEnhancedIterator<E>
	{
		private final IntIterator sourceFlow;
		private final IntFunction<? extends E> mappingFunction;

		public FromInt(final IntIterator src,
				final IntFunction<? extends E> mappingFunction)
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

	public static class FromDouble<E> extends AbstractEnhancedIterator<E>
	{
		private final DoubleIterator sourceFlow;
		private final DoubleFunction<? extends E> mappingFunction;

		public FromDouble(DoubleIterator src,
				DoubleFunction<? extends E> mappingFunction)
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
