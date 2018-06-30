package xawd.jflow.iterators.impl;

import java.util.NoSuchElementException;

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
public final class TakeFlow
{
	private TakeFlow() {}

	public static class OfObject<E> extends AbstractFlow<E>
	{
		private final Flow<E> src;
		private final int takeCount;

		private int count = 0;

		public OfObject(final Flow<E> src, final int takeCount)
		{
			super(Optionals.map(n -> Math.min(takeCount, n), src.size()));
			if (takeCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.takeCount = takeCount;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && src.hasNext();
		}

		@Override
		public E next()
		{
			if (count++ < takeCount) {
				return src.next();
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip()
		{
			if (count++ < takeCount) {
				src.skip();
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfLong extends AbstractLongFlow
	{
		private final LongFlow src;
		private final int takeCount;

		private int count = 0;

		public OfLong(final LongFlow src, final int takeCount)
		{
			super(Optionals.map(n -> Math.min(takeCount, n), src.size()));
			if (takeCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.takeCount = takeCount;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && src.hasNext();
		}

		@Override
		public long nextLong()
		{
			if (count++ < takeCount) {
				return src.nextLong();
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip()
		{
			if (count++ < takeCount) {
				src.skip();
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfInt extends AbstractIntFlow
	{
		private final IntFlow src;
		private final int takeCount;

		private int count = 0;

		public OfInt(final IntFlow src, final int takeCount)
		{
			super(Optionals.map(n -> Math.min(takeCount, n), src.size()));
			if (takeCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.takeCount = takeCount;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && src.hasNext();
		}

		@Override
		public int nextInt()
		{
			if (count++ < takeCount) {
				return src.nextInt();
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip()
		{
			if (count++ < takeCount) {
				src.nextInt();
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfDouble extends AbstractDoubleFlow
	{
		private final DoubleFlow src;
		private final int takeCount;

		private int count = 0;

		public OfDouble(final DoubleFlow src, final int takeCount)
		{
			super(Optionals.map(n -> Math.min(takeCount, n), src.size()));
			if (takeCount < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.takeCount = takeCount;
		}

		@Override
		public boolean hasNext()
		{
			return count < takeCount && src.hasNext();
		}

		@Override
		public double nextDouble()
		{
			if (count++ < takeCount) {
				return src.nextDouble();
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip()
		{
			if (count++ < takeCount) {
				src.nextDouble();
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}
}
