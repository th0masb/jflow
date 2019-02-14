package maumay.jflow.iterators.impl;

import maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import maumay.jflow.iterators.AbstractEnhancedIntIterator;
import maumay.jflow.iterators.AbstractEnhancedIterator;
import maumay.jflow.iterators.AbstractEnhancedLongIterator;
import maumay.jflow.iterators.EnhancedDoubleIterator;
import maumay.jflow.iterators.EnhancedIntIterator;
import maumay.jflow.iterators.EnhancedIterator;
import maumay.jflow.iterators.EnhancedLongIterator;
import maumay.jflow.iterators.misc.Optionals;

/**
 * @author ThomasB
 * @since 25 Apr 2018
 */
public final class DropIterator
{
	private DropIterator()
	{
	}

	public static class OfObject<T> extends AbstractEnhancedIterator<T>
	{
		private final EnhancedIterator<T> src;
		private final int dropCount;

		private boolean dropped = false;

		public OfObject(final EnhancedIterator<T> src, final int dropCount)
		{
			super(Optionals.map(n -> Math.max(0, n - dropCount), src.size()));
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

	public static class OfLong extends AbstractEnhancedLongIterator
	{
		private final EnhancedLongIterator src;
		private final int dropCount;

		private boolean dropped = false;

		public OfLong(final EnhancedLongIterator src, final int dropCount)
		{
			super(Optionals.map(n -> Math.max(0, n - dropCount), src.size()));
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

	public static class OfInt extends AbstractEnhancedIntIterator
	{
		private final EnhancedIntIterator src;
		private final int dropCount;

		private boolean dropped = false;

		public OfInt(final EnhancedIntIterator src, final int dropCount)
		{
			super(Optionals.map(n -> Math.max(0, n - dropCount), src.size()));
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

	public static class OfDouble extends AbstractEnhancedDoubleIterator
	{
		private final EnhancedDoubleIterator src;
		private final int dropCount;

		private boolean dropped = false;

		public OfDouble(final EnhancedDoubleIterator src, final int dropCount)
		{
			super(Optionals.map(n -> Math.max(0, n - dropCount), src.size()));
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
