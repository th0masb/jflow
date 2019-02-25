package com.github.maumay.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.OptionalInt;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.AbstractIntIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.AbstractLongIterator;
import com.github.maumay.jflow.iterators.DoubleIterator;
import com.github.maumay.jflow.iterators.IntIterator;
import com.github.maumay.jflow.iterators.LongIterator;

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

	public static final LongIterator OF_LONGS = new OfLongs();

	private static class OfLongs extends AbstractLongIterator
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

	public static final DoubleIterator OF_DOUBLES = new OfDoubles();

	private static class OfDoubles extends AbstractDoubleIterator
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

	public static final IntIterator OF_INTS = new OfInts();

	private static class OfInts extends AbstractIntIterator
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
