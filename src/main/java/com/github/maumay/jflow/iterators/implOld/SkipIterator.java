package com.github.maumay.jflow.iterators.implOld;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.impl.AbstractEnhancedIterator;
import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.iterators.DoubleIterator;
import com.github.maumay.jflow.iterators.IntIterator;
import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.LongIterator;
import com.github.maumay.jflow.utils.Option;

/**
 * @author ThomasB
 * @since 25 Apr 2018
 */
public final class SkipIterator
{
	private SkipIterator()
	{
	}

	public static class OfObject<T> extends AbstractEnhancedIterator<T>
	{
		private final EnhancedIterator<T> src;
		private final int dropCount;

		private boolean dropped = false;

		public OfObject(final EnhancedIterator<T> src, final int dropCount)
		{
			super(Option.map(n -> Math.max(0, n - dropCount), src.size()));
			if (dropCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.dropCount = dropCount;
		}

		@Override
		public boolean hasNext()
		{
			if (!dropped) {
				dropElements();
			}
			return src.hasNext();
		}

		@Override
		public T next()
		{
			if (!dropped) {
				dropElements();
			}
			return src.next();
		}

		@Override
		public void skip()
		{
			if (!dropped) {
				dropElements();
			}
			src.skip();
		}

		private void dropElements()
		{
			for (int count = 0; count < dropCount && src.hasNext(); count++) {
				src.skip();
			}
			dropped = true;
		}
	}

	public static class OfLong extends AbstractLongIterator
	{
		private final LongIterator src;
		private final int dropCount;

		private boolean dropped = false;

		public OfLong(final LongIterator src, final int dropCount)
		{
			super(Option.map(n -> Math.max(0, n - dropCount), src.size()));
			if (dropCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.dropCount = dropCount;
		}

		@Override
		public boolean hasNext()
		{
			if (!dropped) {
				dropElements();
			}
			return src.hasNext();
		}

		@Override
		public long nextLong()
		{
			if (!dropped) {
				dropElements();
			}
			return src.nextLong();
		}

		@Override
		public void skip()
		{
			if (!dropped) {
				dropElements();
			}
			src.skip();
		}

		private void dropElements()
		{
			for (int count = 0; count < dropCount && src.hasNext(); count++) {
				src.skip();
			}
			dropped = true;
		}
	}

	public static class OfInt extends AbstractIntIterator
	{
		private final IntIterator src;
		private final int dropCount;

		private boolean dropped = false;

		public OfInt(final IntIterator src, final int dropCount)
		{
			super(Option.map(n -> Math.max(0, n - dropCount), src.size()));
			if (dropCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.dropCount = dropCount;
		}

		@Override
		public boolean hasNext()
		{
			if (!dropped) {
				dropElements();
			}
			return src.hasNext();
		}

		@Override
		public int nextInt()
		{
			if (!dropped) {
				dropElements();
			}
			return src.nextInt();
		}

		@Override
		public void skip()
		{
			if (!dropped) {
				dropElements();
			}
			src.skip();
		}

		private void dropElements()
		{
			for (int count = 0; count < dropCount && src.hasNext(); count++) {
				src.skip();
			}
			dropped = true;
		}
	}

	public static class OfDouble extends AbstractDoubleIterator
	{
		private final DoubleIterator src;
		private final int dropCount;

		private boolean dropped = false;

		public OfDouble(final DoubleIterator src, final int dropCount)
		{
			super(Option.map(n -> Math.max(0, n - dropCount), src.size()));
			if (dropCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.dropCount = dropCount;
		}

		@Override
		public boolean hasNext()
		{
			if (!dropped) {
				dropElements();
			}
			return src.hasNext();
		}

		@Override
		public double nextDouble()
		{
			if (!dropped) {
				dropElements();
			}
			return src.nextDouble();
		}

		@Override
		public void skip()
		{
			if (!dropped) {
				dropElements();
			}
			src.skip();
		}

		private void dropElements()
		{
			for (int count = 0; count < dropCount && src.hasNext(); count++) {
				src.skip();
			}
			dropped = true;
		}
	}
}
