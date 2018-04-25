/**
 * Copyright © 2018 Lhasa Limited
 * File created: 25 Apr 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package xawd.jflow.impl;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;
import xawd.jflow.DoubleFlow;
import xawd.jflow.Flow;
import xawd.jflow.IntFlow;
import xawd.jflow.LongFlow;

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
			this.src = src;
			this.accumulator = accumulator;
			this.accumulationValue = id;
			initialized = true;
		}

		public OfLong(final LongFlow src, final LongBinaryOperator accumulator)
		{
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
			this.src = src;
			this.accumulator = accumulator;
			this.accumulationValue = id;
			initialized = true;
		}

		public OfInt(final IntFlow src, final IntBinaryOperator accumulator)
		{
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
			this.src = src;
			this.accumulator = accumulator;
			this.accumulationValue = id;
			initialized = true;
		}

		public OfDouble(final DoubleFlow src, final DoubleBinaryOperator accumulator)
		{
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
