/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.Iterator;
import java.util.PrimitiveIterator;
import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.skippable.Skippable;

/**
 * @author ThomasB
 *
 */
public final class CombinedFlow
{
	private CombinedFlow() {}

	public static class OfObjects<E1, E2, R> extends AbstractFlow<R>
	{
		private final Iterator<E1> firstSource;
		private final Iterator<E2> secondSource;
		private final BiFunction<? super E1, ? super E2, R> combiner;

		public OfObjects(final Iterator<E1> firstSource, final Iterator<E2> secondSource, final BiFunction<? super E1, ? super E2, R> combiner)
		{
			this.firstSource = firstSource;
			this.secondSource = secondSource;
			this.combiner = combiner;
		}

		@Override
		public boolean hasNext()
		{
			return firstSource.hasNext() && secondSource.hasNext();
		}

		@Override
		public R next()
		{
			return combiner.apply(firstSource.next(), secondSource.next());
		}

		@Override
		public void skip()
		{
			if (firstSource instanceof Skippable) {
				((Skippable) firstSource).skip();
			}
			else {
				firstSource.next();
			}

			if (secondSource instanceof Skippable) {
				((Skippable) secondSource).skip();
			}
			else {
				secondSource.next();
			}
		}
	}

	public static class OfDoubles extends AbstractDoubleFlow
	{
		private final PrimitiveIterator.OfDouble firstSource, secondSource;
		private final DoubleBinaryOperator combiner;

		public OfDoubles(
				final PrimitiveIterator.OfDouble firstSource,
				final PrimitiveIterator.OfDouble secondSource,
				final DoubleBinaryOperator combiner)
		{
			this.firstSource = firstSource;
			this.secondSource = secondSource;
			this.combiner = combiner;
		}

		@Override
		public boolean hasNext()
		{
			return firstSource.hasNext() && secondSource.hasNext();
		}

		@Override
		public double nextDouble()
		{
			return combiner.applyAsDouble(firstSource.nextDouble(), secondSource.nextDouble());
		}

		@Override
		public void skip()
		{
			if (firstSource instanceof Skippable) {
				((Skippable) firstSource).skip();
			}
			else {
				firstSource.nextDouble();
			}

			if (secondSource instanceof Skippable) {
				((Skippable) secondSource).skip();
			}
			else {
				secondSource.nextDouble();
			}
		}
	}

	public static class OfLongs extends AbstractLongFlow
	{
		private final PrimitiveIterator.OfLong firstSource, secondSource;
		private final LongBinaryOperator combiner;

		public OfLongs(
				final PrimitiveIterator.OfLong firstSource,
				final PrimitiveIterator.OfLong secondSource,
				final LongBinaryOperator combiner)
		{
			this.firstSource = firstSource;
			this.secondSource = secondSource;
			this.combiner = combiner;
		}

		@Override
		public boolean hasNext()
		{
			return firstSource.hasNext() && secondSource.hasNext();
		}

		@Override
		public long nextLong()
		{
			return combiner.applyAsLong(firstSource.nextLong(), secondSource.nextLong());
		}

		@Override
		public void skip()
		{
			if (firstSource instanceof Skippable) {
				((Skippable) firstSource).skip();
			}
			else {
				firstSource.nextLong();
			}

			if (secondSource instanceof Skippable) {
				((Skippable) secondSource).skip();
			}
			else {
				secondSource.nextLong();
			}
		}
	}

	public static class OfInts extends AbstractIntFlow
	{
		private final PrimitiveIterator.OfInt firstSource, secondSource;
		private final IntBinaryOperator combiner;

		public OfInts(
				final PrimitiveIterator.OfInt firstSource,
				final PrimitiveIterator.OfInt secondSource,
				final IntBinaryOperator combiner)
		{
			this.firstSource = firstSource;
			this.secondSource = secondSource;
			this.combiner = combiner;
		}

		@Override
		public boolean hasNext()
		{
			return firstSource.hasNext() && secondSource.hasNext();
		}

		@Override
		public int nextInt()
		{
			return combiner.applyAsInt(firstSource.nextInt(), secondSource.nextInt());
		}

		@Override
		public void skip()
		{
			if (firstSource instanceof Skippable) {
				((Skippable) firstSource).skip();
			}
			else {
				firstSource.nextInt();
			}

			if (secondSource instanceof Skippable) {
				((Skippable) secondSource).skip();
			}
			else {
				secondSource.nextInt();
			}
		}
	}
}
