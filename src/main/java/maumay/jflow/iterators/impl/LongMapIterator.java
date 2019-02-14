/**
 *
 */
package maumay.jflow.iterators.impl;

import java.util.function.DoubleToLongFunction;
import java.util.function.IntToLongFunction;
import java.util.function.ToLongFunction;

import maumay.jflow.iterators.AbstractEnhancedLongIterator;
import maumay.jflow.iterators.EnhancedDoubleIterator;
import maumay.jflow.iterators.EnhancedIntIterator;
import maumay.jflow.iterators.EnhancedIterator;

/**
 * @author ThomasB
 */
public final class LongMapIterator
{
	private LongMapIterator()
	{
	}

	public static class FromObject<E> extends AbstractEnhancedLongIterator
	{
		private final EnhancedIterator<E> sourceFlow;
		private final ToLongFunction<? super E> mappingFunction;

		public FromObject(final EnhancedIterator<E> src,
				final ToLongFunction<? super E> mappingFunction)
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

	public static class FromInt extends AbstractEnhancedLongIterator
	{
		private final EnhancedIntIterator sourceFlow;
		private final IntToLongFunction mappingFunction;

		public FromInt(final EnhancedIntIterator src,
				final IntToLongFunction mappingFunction)
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

	public static class FromDouble extends AbstractEnhancedLongIterator
	{
		private final EnhancedDoubleIterator sourceFlow;
		private final DoubleToLongFunction mappingFunction;

		public FromDouble(final EnhancedDoubleIterator src,
				final DoubleToLongFunction mappingFunction)
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
