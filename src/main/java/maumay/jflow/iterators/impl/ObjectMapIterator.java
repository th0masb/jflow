/**
 *
 */
package maumay.jflow.iterators.impl;

import java.util.function.DoubleFunction;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

import maumay.jflow.iterators.AbstractEnhancedIterator;
import maumay.jflow.iterators.EnhancedDoubleIterator;
import maumay.jflow.iterators.EnhancedIntIterator;
import maumay.jflow.iterators.EnhancedLongIterator;

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
		private final EnhancedLongIterator sourceFlow;
		private final LongFunction<? extends E> mappingFunction;

		public FromLong(EnhancedLongIterator src,
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
		private final EnhancedIntIterator sourceFlow;
		private final IntFunction<? extends E> mappingFunction;

		public FromInt(final EnhancedIntIterator src,
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
		private final EnhancedDoubleIterator sourceFlow;
		private final DoubleFunction<? extends E> mappingFunction;

		public FromDouble(EnhancedDoubleIterator src,
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
