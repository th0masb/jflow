package maumay.jflow.iterators.impl;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

import maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import maumay.jflow.iterators.AbstractEnhancedIntIterator;
import maumay.jflow.iterators.AbstractEnhancedIterator;
import maumay.jflow.iterators.AbstractEnhancedLongIterator;
import maumay.jflow.iterators.EnhancedDoubleIterator;
import maumay.jflow.iterators.EnhancedIntIterator;
import maumay.jflow.iterators.EnhancedIterator;
import maumay.jflow.iterators.EnhancedLongIterator;

/**
 * @author ThomasB
 * @since 25 Apr 2018
 */
public final class AccumulationIterator
{
	private AccumulationIterator()
	{
	}

	public static class OfObjectWithMixedTypes<T, R> extends AbstractEnhancedIterator<R>
	{
		private final EnhancedIterator<T> src;
		private final BiFunction<R, T, R> accumulator;

		private R accumulationValue;

		public OfObjectWithMixedTypes(final EnhancedIterator<T> src, final R id,
				final BiFunction<R, T, R> accumulator)
		{
			super(src.size());
			this.src = src;
			this.accumulator = accumulator;
			this.accumulationValue = id;
		}

		@Override
		public boolean hasNext()
		{
			return src.hasNext();
		}

		@Override
		public R next()
		{
			accumulationValue = accumulator.apply(accumulationValue, src.next());
			return accumulationValue;
		}

		@Override
		public void skip()
		{
			next();
		}
	}

	public static class OfObject<T> extends AbstractEnhancedIterator<T>
	{
		private final EnhancedIterator<T> src;
		private final BinaryOperator<T> accumulator;

		private T accumulationValue;

		public OfObject(final EnhancedIterator<T> src,
				final BinaryOperator<T> accumulator)
		{
			super(src.size());
			this.src = src;
			this.accumulator = accumulator;
		}

		@Override
		public boolean hasNext()
		{
			return src.hasNext();
		}

		@Override
		public T next()
		{
			final T accVal = accumulationValue, next = src.next();
			accumulationValue = accVal == null ? next : accumulator.apply(accVal, next);
			return accumulationValue;
		}

		@Override
		public void skip()
		{
			next();
		}
	}

	public static class OfLong extends AbstractEnhancedLongIterator
	{
		private final EnhancedLongIterator src;
		private final LongBinaryOperator accumulator;

		private long accumulationValue;
		private boolean initialized;

		public OfLong(final EnhancedLongIterator src, final long id,
				final LongBinaryOperator accumulator)
		{
			super(src.size());
			this.src = src;
			this.accumulator = accumulator;
			this.accumulationValue = id;
			initialized = true;
		}

		public OfLong(final EnhancedLongIterator src,
				final LongBinaryOperator accumulator)
		{
			super(src.size());
			this.src = src;
			this.accumulator = accumulator;
			initialized = false;
		}

		@Override
		public boolean hasNext()
		{
			return src.hasNext();
		}

		@Override
		public long nextLong()
		{
			if (initialized) {
				accumulationValue = accumulator.applyAsLong(accumulationValue,
						src.nextLong());
			} else {
				initialized = true;
				accumulationValue = src.nextLong();
			}
			return accumulationValue;
		}

		@Override
		public void skip()
		{
			nextLong();
		}
	}

	public static class OfInt extends AbstractEnhancedIntIterator
	{
		private final EnhancedIntIterator src;
		private final IntBinaryOperator accumulator;

		private int accumulationValue;
		private boolean initialized;

		public OfInt(final EnhancedIntIterator src, final int id,
				final IntBinaryOperator accumulator)
		{
			super(src.size());
			this.src = src;
			this.accumulator = accumulator;
			this.accumulationValue = id;
			initialized = true;
		}

		public OfInt(final EnhancedIntIterator src, final IntBinaryOperator accumulator)
		{
			super(src.size());
			this.src = src;
			this.accumulator = accumulator;
			initialized = false;
		}

		@Override
		public boolean hasNext()
		{
			return src.hasNext();
		}

		@Override
		public int nextInt()
		{
			if (initialized) {
				accumulationValue = accumulator.applyAsInt(accumulationValue,
						src.nextInt());
			} else {
				initialized = true;
				accumulationValue = src.nextInt();
			}
			return accumulationValue;
		}

		@Override
		public void skip()
		{
			nextInt();
		}
	}

	public static class OfDouble extends AbstractEnhancedDoubleIterator
	{
		private final EnhancedDoubleIterator src;
		private final DoubleBinaryOperator accumulator;

		private double accumulationValue;
		private boolean initialized;

		public OfDouble(final EnhancedDoubleIterator src, final double id,
				final DoubleBinaryOperator accumulator)
		{
			super(src.size());
			this.src = src;
			this.accumulator = accumulator;
			this.accumulationValue = id;
			initialized = true;
		}

		public OfDouble(final EnhancedDoubleIterator src,
				final DoubleBinaryOperator accumulator)
		{
			super(src.size());
			this.src = src;
			this.accumulator = accumulator;
			initialized = false;
		}

		@Override
		public boolean hasNext()
		{
			return src.hasNext();
		}

		@Override
		public double nextDouble()
		{
			if (initialized) {
				accumulationValue = accumulator.applyAsDouble(accumulationValue,
						src.nextDouble());
			} else {
				initialized = true;
				accumulationValue = src.nextDouble();
			}
			return accumulationValue;
		}

		@Override
		public void skip()
		{
			nextDouble();
		}
	}
}
