package xawd.jflow.iterators.impl;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.iterators.LongFlow;
import xawd.jflow.utilities.Optionals;

/**
 * @author ThomasB
 * @since 25 Apr 2018
 */
public final class DropFlow
{
	private DropFlow() {}

	public static class OfObject<T> extends AbstractFlow<T>
	{
		private final Flow<T> src;
		private final int dropCount;

		private boolean dropped = false;

		public OfObject(final Flow<T> src, final int dropCount)
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

		private void dropElements() {
			for (int count = 0; count < dropCount && src.hasNext(); count++) {
				src.skip();
			}
			dropped = true;
		}
	}

	public static class OfLong extends AbstractLongFlow
	{
		private final LongFlow src;
		private final int dropCount;

		private boolean dropped = false;

		public OfLong(final LongFlow src, final int dropCount)
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

		private void dropElements() {
			for (int count = 0; count < dropCount && src.hasNext(); count++) {
				src.skip();
			}
			dropped = true;
		}
	}

	public static class OfInt extends AbstractIntFlow
	{
		private final IntFlow src;
		private final int dropCount;

		private boolean dropped = false;

		public OfInt(final IntFlow src, final int dropCount)
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

		private void dropElements() {
			for (int count = 0; count < dropCount && src.hasNext(); count++) {
				src.skip();
			}
			dropped = true;
		}
	}

	public static class OfDouble extends AbstractDoubleFlow
	{
		private final DoubleFlow src;
		private final int dropCount;

		private boolean dropped = false;

		public OfDouble(final DoubleFlow src, final int dropCount)
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

		private void dropElements() {
			for (int count = 0; count < dropCount && src.hasNext(); count++) {
				src.skip();
			}
			dropped = true;
		}
	}
}
