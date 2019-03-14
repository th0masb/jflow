/**
 *
 */
package com.github.maumay.jflow.iterators.implOld;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

import com.github.maumay.jflow.iterators.DoubleIterator;
import com.github.maumay.jflow.iterators.IntIterator;
import com.github.maumay.jflow.iterators.LongIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractAdapterIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractIntIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractLongIterator;

/**
 * @author ThomasB
 *
 */
public class MapIterator
{
	private MapIterator()
	{
	}

	public static class OfObject<E, R> extends AbstractAdapterIterator<E, R>
	{
		private final Function<? super E, ? extends R> mappingFunction;

		public OfObject(AbstractEnhancedIterator<E> source,
				Function<? super E, ? extends R> mappingFunction)
		{
			super(source.getSize(), source);
			this.mappingFunction = mappingFunction;
		}

		@Override
		public boolean hasNext()
		{
			return getSource().hasNext();
		}

		@Override
		protected R nextImpl()
		{
			return mappingFunction.apply(getSource().ne)
		}

		@Override
		protected void skipImpl()
		{
			throw new RuntimeException("Not yet implemented");
		}
	}

	public static class OfLong extends AbstractLongIterator
	{
		private final LongIterator sourceFlow;
		private final LongUnaryOperator mappingFunction;

		public OfLong(LongIterator src, LongUnaryOperator mappingFunction)
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

	public static class OfDouble extends AbstractDoubleIterator
	{
		private final DoubleIterator sourceFlow;
		private final DoubleUnaryOperator mappingFunction;

		public OfDouble(DoubleIterator src, DoubleUnaryOperator mappingFunction)
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

	public static class OfInt extends AbstractIntIterator
	{
		private final IntIterator sourceFlow;
		private final IntUnaryOperator mappingFunction;

		public OfInt(IntIterator src, IntUnaryOperator mappingFunction)
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
