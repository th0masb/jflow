/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.NoSuchElementException;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

/**
 * @author thomasb
 *
 */
public final class FunctionSource
{
	private FunctionSource()
	{
	}

	public static class OfObject<E> extends AbstractRichIterator<E>
	{
		private final IntFunction<? extends E> src;
		private final boolean infiniteSize;
		private final int size;

		private int count;

		public OfObject(IntFunction<? extends E> src, int size)
		{
			this(src, new KnownSize(size));
		}

		public OfObject(IntFunction<? extends E> src)
		{
			this(src, InfiniteSize.instance());
		}

		private OfObject(IntFunction<? extends E> src, AbstractIteratorSize size)
		{
			super(size);
			this.src = src;
			this.infiniteSize = size.getType() == SizeType.INFINITE;
			this.count = 0;
			this.size = infiniteSize ? -1 : ((KnownSize) size).getValue();
		}

		@Override
		public boolean hasNext()
		{
			return infiniteSize || count < size;
		}

		@Override
		public E nextImpl()
		{
			if (hasNext()) {
				return src.apply(count++);
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (hasNext()) {
				count++;
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfInt extends AbstractIntIterator
	{
		private final IntUnaryOperator src;
		private final boolean infiniteSize;
		private final int size;

		private int count;

		public OfInt(IntUnaryOperator src, int size)
		{
			this(src, new KnownSize(IteratorImplUtils.requireNonNegative(size)));
		}

		public OfInt(IntUnaryOperator src)
		{
			this(src, UnknownSize.instance());
		}

		private OfInt(IntUnaryOperator src, AbstractIteratorSize size)
		{
			super(size);
			this.src = src;
			this.infiniteSize = size.getType() == SizeType.UNKNOWN;
			this.count = 0;
			this.size = infiniteSize ? -1 : ((KnownSize) size).getValue();
		}

		@Override
		public boolean hasNext()
		{
			return infiniteSize || count < size;
		}

		@Override
		public int nextIntImpl()
		{
			if (hasNext()) {
				return src.applyAsInt(count++);
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (hasNext()) {
				count++;
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfLong extends AbstractLongIterator
	{
		private final IntToLongFunction src;
		private final boolean infiniteSize;
		private final int size;

		private int count;

		public OfLong(IntToLongFunction src, int size)
		{
			this(src, new KnownSize(IteratorImplUtils.requireNonNegative(size)));
		}

		public OfLong(IntToLongFunction src)
		{
			this(src, UnknownSize.instance());
		}

		private OfLong(IntToLongFunction src, AbstractIteratorSize size)
		{
			super(size);
			this.src = src;
			this.infiniteSize = size.getType() == SizeType.UNKNOWN;
			this.count = 0;
			this.size = infiniteSize ? -1 : ((KnownSize) size).getValue();
		}

		@Override
		public boolean hasNext()
		{
			return infiniteSize || count < size;
		}

		@Override
		public long nextLongImpl()
		{
			if (hasNext()) {
				return src.applyAsLong(count++);
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (hasNext()) {
				count++;
			} else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfDouble extends AbstractDoubleIterator
	{
		private final IntToDoubleFunction src;
		private final boolean infiniteSize;
		private final int size;

		private int count;

		public OfDouble(IntToDoubleFunction src, int size)
		{
			this(src, new KnownSize(IteratorImplUtils.requireNonNegative(size)));
		}

		public OfDouble(IntToDoubleFunction src)
		{
			this(src, UnknownSize.instance());
		}

		private OfDouble(IntToDoubleFunction src, AbstractIteratorSize size)
		{
			super(size);
			this.src = src;
			this.infiniteSize = size.getType() == SizeType.UNKNOWN;
			this.count = 0;
			this.size = infiniteSize ? -1 : ((KnownSize) size).getValue();
		}

		@Override
		public boolean hasNext()
		{
			return infiniteSize || count < size;
		}

		@Override
		public double nextDoubleImpl()
		{
			if (hasNext()) {
				return src.applyAsDouble(count++);
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skipImpl()
		{
			if (hasNext()) {
				count++;
			} else {
				throw new NoSuchElementException();
			}
		}
	}
}
