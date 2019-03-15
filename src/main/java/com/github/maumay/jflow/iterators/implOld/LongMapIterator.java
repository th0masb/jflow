/**
 *
 */
package com.github.maumay.jflow.iterators.implOld;

import java.util.function.DoubleToLongFunction;
import java.util.function.IntToLongFunction;
import java.util.function.ToLongFunction;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.iterators.DoubleIterator;
import com.github.maumay.jflow.iterators.IntIterator;
import com.github.maumay.jflow.iterators.EnhancedIterator;

/**
 * @author ThomasB
 */
public final class LongMapIterator
{
	private LongMapIterator()
	{
	}

	public static class FromObject<E> extends AbstractLongIterator
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

	public static class FromInt extends AbstractLongIterator
	{
		private final IntIterator sourceFlow;
		private final IntToLongFunction mappingFunction;

		public FromInt(final IntIterator src,
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

	public static class FromDouble extends AbstractLongIterator
	{
		private final DoubleIterator sourceFlow;
		private final DoubleToLongFunction mappingFunction;

		public FromDouble(final DoubleIterator src,
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
