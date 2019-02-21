/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

import com.github.maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedIntIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedLongIterator;
import com.github.maumay.jflow.iterators.EnhancedDoubleIterator;
import com.github.maumay.jflow.iterators.EnhancedIntIterator;
import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.EnhancedLongIterator;

/**
 * @author ThomasB
 *
 */
public class MapIterator
{
	private MapIterator()
	{
	}

	public static class OfObject<E, R> extends AbstractEnhancedIterator<R>
	{
		private final EnhancedIterator<E> sourceFlow;
		private final Function<? super E, ? extends R> mappingFunction;

		public OfObject(EnhancedIterator<E> sourceFlow,
				Function<? super E, ? extends R> mappingFunction)
		{
			super(sourceFlow.size());
			this.sourceFlow = sourceFlow;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public boolean hasNext()
		{
			return sourceFlow.hasNext();
		}

		@Override
		public R next()
		{
			return mappingFunction.apply(sourceFlow.next());
		}

		@Override
		public void skip()
		{
			sourceFlow.skip();
		}
	}

	public static class OfLong extends AbstractEnhancedLongIterator
	{
		private final EnhancedLongIterator sourceFlow;
		private final LongUnaryOperator mappingFunction;

		public OfLong(EnhancedLongIterator src, LongUnaryOperator mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public long nextLong()
		{
			return mappingFunction.applyAsLong(sourceFlow.nextLong());
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

	public static class OfDouble extends AbstractEnhancedDoubleIterator
	{
		private final EnhancedDoubleIterator sourceFlow;
		private final DoubleUnaryOperator mappingFunction;

		public OfDouble(EnhancedDoubleIterator src, DoubleUnaryOperator mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public double nextDouble()
		{
			return mappingFunction.applyAsDouble(sourceFlow.nextDouble());
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

	public static class OfInt extends AbstractEnhancedIntIterator
	{
		private final EnhancedIntIterator sourceFlow;
		private final IntUnaryOperator mappingFunction;

		public OfInt(EnhancedIntIterator src, IntUnaryOperator mappingFunction)
		{
			super(src.size());
			this.sourceFlow = src;
			this.mappingFunction = mappingFunction;
		}

		@Override
		public int nextInt()
		{
			return mappingFunction.applyAsInt(sourceFlow.nextInt());
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
