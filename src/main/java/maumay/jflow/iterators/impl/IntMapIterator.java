/**
 *
 */
package maumay.jflow.iterators.impl;

import java.util.function.DoubleToIntFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ToIntFunction;

import maumay.jflow.iterators.AbstractEnhancedIntIterator;
import maumay.jflow.iterators.EnhancedDoubleIterator;
import maumay.jflow.iterators.EnhancedIterator;
import maumay.jflow.iterators.EnhancedLongIterator;

/**
 * @author ThomasB
 *
 */
public final class IntMapIterator
{
	public IntMapIterator()
	{
	}

	public static class FromObject<E> extends AbstractEnhancedIntIterator
	{
		private final EnhancedIterator<E> sourceFlow;
		private final ToIntFunction<? super E> mappingFunction;

		public FromObject(final EnhancedIterator<E> src,
				final ToIntFunction<? super E> mappingFunction)
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

	public static class FromLong extends AbstractEnhancedIntIterator
	{
		private final EnhancedLongIterator sourceFlow;
		private final LongToIntFunction mappingFunction;

		public FromLong(final EnhancedLongIterator src,
				final LongToIntFunction mappingFunction)
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

	public static class FromDouble extends AbstractEnhancedIntIterator
	{
		private final EnhancedDoubleIterator sourceFlow;
		private final DoubleToIntFunction mappingFunction;

		public FromDouble(final EnhancedDoubleIterator src,
				final DoubleToIntFunction mappingFunction)
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
