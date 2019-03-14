/**
 * 
 */
package com.github.maumay.jflow.iterators.implOld;

import java.util.OptionalInt;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.function.UnaryOperator;

import com.github.maumay.jflow.iterators.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.impl.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.impl.AbstractIntIterator;
import com.github.maumay.jflow.iterators.impl.AbstractLongIterator;

/**
 * @author ThomasB
 */
public final class ApplicationIterator
{
	private ApplicationIterator()
	{
	}

	public static class OfObjects<E> extends AbstractEnhancedIterator<E>
	{
		private final UnaryOperator<E> applicationOperator;
		private E currentValue;

		public OfObjects(UnaryOperator<E> applicationOperator, E initialValue)
		{
			super(OptionalInt.empty());
			this.applicationOperator = applicationOperator;
			this.currentValue = initialValue;
		}

		@Override
		public boolean hasNext()
		{
			return true;
		}

		@Override
		public E next()
		{
			E prev = currentValue;
			currentValue = applicationOperator.apply(currentValue);
			return prev;
		}

		@Override
		public void skip()
		{
			currentValue = applicationOperator.apply(currentValue);
		}
	}

	public static class OfInts extends AbstractIntIterator
	{
		private final IntUnaryOperator applicationOperator;
		private int currentValue;

		public OfInts(IntUnaryOperator applicationOperator, int initialValue)
		{
			super(OptionalInt.empty());
			this.applicationOperator = applicationOperator;
			this.currentValue = initialValue;
		}

		@Override
		public boolean hasNext()
		{
			return true;
		}

		@Override
		public int nextInt()
		{
			int prev = currentValue;
			currentValue = applicationOperator.applyAsInt(currentValue);
			return prev;
		}

		@Override
		public void skip()
		{
			currentValue = applicationOperator.applyAsInt(currentValue);
		}
	}

	public static class OfLongs extends AbstractLongIterator
	{
		private final LongUnaryOperator applicationOperator;
		private long currentValue;

		public OfLongs(LongUnaryOperator applicationOperator, long initialValue)
		{
			super(OptionalInt.empty());
			this.applicationOperator = applicationOperator;
			this.currentValue = initialValue;
		}

		@Override
		public boolean hasNext()
		{
			return true;
		}

		@Override
		public long nextLong()
		{
			long prev = currentValue;
			currentValue = applicationOperator.applyAsLong(currentValue);
			return prev;
		}

		@Override
		public void skip()
		{
			currentValue = applicationOperator.applyAsLong(currentValue);
		}
	}

	public static class OfDoubles extends AbstractDoubleIterator
	{
		private final DoubleUnaryOperator applicationOperator;
		private double currentValue;

		public OfDoubles(DoubleUnaryOperator applicationOperator, double initialValue)
		{
			super(OptionalInt.empty());
			this.applicationOperator = applicationOperator;
			this.currentValue = initialValue;
		}

		@Override
		public boolean hasNext()
		{
			return true;
		}

		@Override
		public double nextDouble()
		{
			double prev = currentValue;
			currentValue = applicationOperator.applyAsDouble(currentValue);
			return prev;
		}

		@Override
		public void skip()
		{
			currentValue = applicationOperator.applyAsDouble(currentValue);
		}
	}
}
