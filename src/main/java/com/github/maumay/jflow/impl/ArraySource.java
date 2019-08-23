/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author thomasb
 *
 */
public final class ArraySource
{
	private ArraySource()
	{
	}

	public static class OfObject<E> extends AbstractRichIterator<E>
	{
		private final Object[] data;
		private int count = 0;

		@SafeVarargs
		public OfObject(E... elements)
		{
			super(new KnownSize(elements.length));
			this.data = elements;
		}

		@Override
		public boolean hasNext()
		{
			return count < data.length;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E nextImpl()
		{
			try {
				return (E) Objects.requireNonNull(data[count++]);
			} catch (IndexOutOfBoundsException ex) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void forwardImpl()
		{
			if (count++ >= data.length) {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfObjectReversed<E> extends AbstractRichIterator<E>
	{
		private final Object[] data;
		private int count, size;

		@SafeVarargs
		public OfObjectReversed(E... elements)
		{
			super(new KnownSize(elements.length));
			this.data = elements;
			this.count = 0;
			this.size = elements.length;
		}

		@Override
		public boolean hasNext()
		{
			return count < data.length;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E nextImpl()
		{
			try {
				return (E) Objects.requireNonNull(data[size - 1 - count++]);
			} catch (IndexOutOfBoundsException ex) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void forwardImpl()
		{
			if (count++ >= data.length) {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfLong extends AbstractLongIterator
	{
		private final long[] data;
		private int count = 0;

		public OfLong(long... elements)
		{
			super(new KnownSize(elements.length));
			this.data = elements;
		}

		@Override
		public boolean hasNext()
		{
			return count < data.length;
		}

		@Override
		public long nextLongImpl()
		{
			try {
				return data[count++];
			} catch (IndexOutOfBoundsException ex) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void forwardImpl()
		{
			if (count++ >= data.length) {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfInt extends AbstractIntIterator
	{
		private final int[] data;
		private int count = 0;

		public OfInt(int... elements)
		{
			super(new KnownSize(elements.length));
			this.data = elements;
		}

		@Override
		public boolean hasNext()
		{
			return count < data.length;
		}

		@Override
		public int nextIntImpl()
		{
			try {
				return data[count++];
			} catch (IndexOutOfBoundsException ex) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void forwardImpl()
		{
			if (count++ >= data.length) {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfDouble extends AbstractDoubleIterator
	{
		private final double[] data;
		private int count = 0;

		public OfDouble(double... elements)
		{
			super(new KnownSize(elements.length));
			this.data = elements;
		}

		@Override
		public boolean hasNext()
		{
			return count < data.length;
		}

		@Override
		public double nextDoubleImpl()
		{
			try {
				return data[count++];
			} catch (IndexOutOfBoundsException ex) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void forwardImpl()
		{
			if (count++ >= data.length) {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfIntReversed extends AbstractIntIterator
	{
		private final int[] data;
		private int count, size;

		public OfIntReversed(int... elements)
		{
			super(new KnownSize(elements.length));
			this.data = elements;
			this.count = 0;
			this.size = data.length;
		}

		@Override
		public boolean hasNext()
		{
			return count < size;
		}

		@Override
		public int nextIntImpl()
		{
			try {
				return data[size - 1 - count++];
			} catch (IndexOutOfBoundsException ex) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void forwardImpl()
		{
			if (count++ >= size) {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfLongReversed extends AbstractLongIterator
	{
		private final long[] data;
		private int count, size;

		public OfLongReversed(long... elements)
		{
			super(new KnownSize(elements.length));
			this.data = elements;
			this.count = 0;
			this.size = data.length;
		}

		@Override
		public boolean hasNext()
		{
			return count < size;
		}

		@Override
		public long nextLongImpl()
		{
			try {
				return data[size - 1 - count++];
			} catch (IndexOutOfBoundsException ex) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void forwardImpl()
		{
			if (count++ >= size) {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfDoubleReversed extends AbstractDoubleIterator
	{
		private final double[] data;
		private int count, size;

		public OfDoubleReversed(double... elements)
		{
			super(new KnownSize(elements.length));
			this.data = elements;
			this.count = 0;
			this.size = data.length;
		}

		@Override
		public boolean hasNext()
		{
			return count < size;
		}

		@Override
		public double nextDoubleImpl()
		{
			try {
				return data[size - 1 - count++];
			} catch (IndexOutOfBoundsException ex) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void forwardImpl()
		{
			if (count++ >= size) {
				throw new NoSuchElementException();
			}
		}
	}
}
