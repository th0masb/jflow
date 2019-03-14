/**
 *
 */
package com.github.maumay.jflow.iterators.implOld;

import java.util.function.DoubleToIntFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ToIntFunction;

import com.github.maumay.jflow.iterators.DoubleIterator;
import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.LongIterator;
import com.github.maumay.jflow.iterators.impl.AbstractIntIterator;

/**
 * @author ThomasB
 *
 */
public final class IntMapIterator
{
	public IntMapIterator()
	{
	}

	public static class FromObject<E> extends AbstractIntIterator
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

	public static class FromLong extends AbstractIntIterator
	{
		private final LongIterator sourceFlow;
		private final LongToIntFunction mappingFunction;

		public FromLong(final LongIterator src,
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

	public static class FromDouble extends AbstractIntIterator
	{
		private final DoubleIterator sourceFlow;
		private final DoubleToIntFunction mappingFunction;

		public FromDouble(final DoubleIterator src,
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
