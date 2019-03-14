/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2.source;

import java.util.NoSuchElementException;

import com.github.maumay.jflow.iterators.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.impl.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.impl.AbstractIntIterator;
import com.github.maumay.jflow.iterators.impl.AbstractLongIterator;
import com.github.maumay.jflow.iterators.impl.KnownSize;

/**
 * @author thomasb
 *
 */
public final class ArraySource
{
	private ArraySource()
	{
	}

	public static class OfObject<E> extends AbstractEnhancedIterator<E>
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
				return (E) data[count++];
			} catch (IndexOutOfBoundsException ex) {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
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
		public void skipImpl()
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
		public void skipImpl()
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
		public void skipImpl()
		{
			if (count++ >= data.length) {
				throw new NoSuchElementException();
			}
		}
	}
}
