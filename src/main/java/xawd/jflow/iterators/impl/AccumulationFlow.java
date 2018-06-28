package xawd.jflow.iterators.impl;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;

/**
 * @author ThomasB
 * @since 25 Apr 2018
 */
public final class AccumulationFlow
{
	private AccumulationFlow() {}

	public static class OfObjectWithMixedTypes<T, R> extends AbstractFlow<R>
	{
		private final Flow<T> src;
		private final BiFunction<R, T, R> accumulator;

		private R accumulationValue;

		public OfObjectWithMixedTypes(final Flow<T> src, final R id, final BiFunction<R, T, R> accumulator)
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

	public static class OfObject<T> extends AbstractFlow<T>
	{
		private final Flow<T> src;
		private final BinaryOperator<T> accumulator;

		private T accumulationValue;

		public OfObject(final Flow<T> src, final BinaryOperator<T> accumulator)
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

	public static class OfLong extends AbstractLongFlow
	{
		private final LongFlow src;
		private final LongBinaryOperator accumulator;

		private long accumulationValue;
		private boolean initialized;

		public OfLong(final LongFlow src, final long id, final LongBinaryOperator accumulator)
		{
			super(src.size());
			this.src = src;
			this.accumulator = accumulator;
			this.accumulationValue = id;
			initialized = true;
		}

		public OfLong(final LongFlow src, final LongBinaryOperator accumulator)
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
				accumulationValue = accumulator.applyAsLong(accumulationValue, src.nextLong());
			}
			else {
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

	public static class OfInt extends AbstractIntFlow
	{
		private final IntFlow src;
		private final IntBinaryOperator accumulator;

		private int accumulationValue;
		private boolean initialized;

		public OfInt(final IntFlow src, final int id, final IntBinaryOperator accumulator)
		{
			super(src.size());
			this.src = src;
			this.accumulator = accumulator;
			this.accumulationValue = id;
			initialized = true;
		}

		public OfInt(final IntFlow src, final IntBinaryOperator accumulator)
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
				accumulationValue = accumulator.applyAsInt(accumulationValue, src.nextInt());
			}
			else {
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

	public static class OfDouble extends AbstractDoubleFlow
	{
		private final DoubleFlow src;
		private final DoubleBinaryOperator accumulator;

		private double accumulationValue;
		private boolean initialized;

		public OfDouble(final DoubleFlow src, final double id, final DoubleBinaryOperator accumulator)
		{
			super(src.size());
			this.src = src;
			this.accumulator = accumulator;
			this.accumulationValue = id;
			initialized = true;
		}

		public OfDouble(final DoubleFlow src, final DoubleBinaryOperator accumulator)
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
				accumulationValue = accumulator.applyAsDouble(accumulationValue, src.nextDouble());
			}
			else {
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
