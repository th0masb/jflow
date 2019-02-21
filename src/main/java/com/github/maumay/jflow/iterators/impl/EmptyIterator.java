package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.OptionalInt;

import com.github.maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedIntIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedLongIterator;
import com.github.maumay.jflow.iterators.EnhancedDoubleIterator;
import com.github.maumay.jflow.iterators.EnhancedIntIterator;
import com.github.maumay.jflow.iterators.EnhancedLongIterator;

public final class EmptyIterator
{
	private EmptyIterator()
	{
	}

	public static class OfObjects<T> extends AbstractEnhancedIterator<T>
	{
		public OfObjects()
		{
			super(OptionalInt.of(0));
		}

		@Override
		public boolean hasNext()
		{
			return false;
		}

		@Override
		public T next()
		{
			throw new NoSuchElementException();
		}

		@Override
		public void skip()
		{
			throw new NoSuchElementException();
		}
	}

	public static final EnhancedLongIterator OF_LONGS = new OfLongs();

	private static class OfLongs extends AbstractEnhancedLongIterator
	{
		public OfLongs()
		{
			super(OptionalInt.of(0));
		}

		@Override
		public boolean hasNext()
		{
			return false;
		}

		@Override
		public long nextLong()
		{
			throw new NoSuchElementException();
		}

		@Override
		public void skip()
		{
			throw new NoSuchElementException();
		}
	}

	public static final EnhancedDoubleIterator OF_DOUBLES = new OfDoubles();

	private static class OfDoubles extends AbstractEnhancedDoubleIterator
	{
		private OfDoubles()
		{
			super(OptionalInt.of(0));
		}

		@Override
		public boolean hasNext()
		{
			return false;
		}

		@Override
		public double nextDouble()
		{
			throw new NoSuchElementException();
		}

		@Override
		public void skip()
		{
			throw new NoSuchElementException();
		}
	}

	public static final EnhancedIntIterator OF_INTS = new OfInts();

	private static class OfInts extends AbstractEnhancedIntIterator
	{
		private OfInts()
		{
			super(OptionalInt.of(0));
		}

		@Override
		public boolean hasNext()
		{
			return false;
		}

		@Override
		public int nextInt()
		{
			throw new NoSuchElementException();
		}

		@Override
		public void skip()
		{
			throw new NoSuchElementException();
		}
	}
}
