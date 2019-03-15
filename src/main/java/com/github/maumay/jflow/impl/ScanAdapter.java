/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;

/**
 * @author thomasb
 *
 */
public final class ScanAdapter
{
	private ScanAdapter()
	{
	}

	public static class OfObject<E, R>
			extends AbstractIteratorAdapter.OfObject<AbstractEnhancedIterator<E>, R>
	{
		private final BiFunction<R, E, R> accumulator;

		private R accumulationValue;
		private boolean initialValueConsumed;

		public OfObject(AbstractEnhancedIterator<E> src, R id, BiFunction<R, E, R> accumulator)
		{
			super(IteratorImplUtils.add(src.getSize(), 1), src);
			this.accumulator = Objects.requireNonNull(accumulator);
			this.accumulationValue = Objects.requireNonNull(id);
			this.initialValueConsumed = false;
		}

		@Override
		public boolean hasNext()
		{
			return !initialValueConsumed || getSource().hasNext();
		}

		@Override
		public R nextImpl()
		{
			if (!initialValueConsumed) {
				initialValueConsumed = true;
			} else {
				accumulationValue = accumulator.apply(accumulationValue, getSource().nextImpl());
			}
			return accumulationValue;
		}

		@Override
		public void skipImpl()
		{
			nextImpl();
		}
	}

	public static class OfDouble extends AbstractIteratorAdapter.OfDouble<AbstractDoubleIterator>
	{
		private final DoubleBinaryOperator accumulator;

		private double accumulationValue;
		private boolean initialValueConsumed;

		public OfDouble(AbstractDoubleIterator src, double id, DoubleBinaryOperator accumulator)
		{
			super(IteratorImplUtils.add(src.getSize(), 1), src);
			this.accumulator = Objects.requireNonNull(accumulator);
			this.accumulationValue = id;
			this.initialValueConsumed = false;
		}

		@Override
		public boolean hasNext()
		{
			return !initialValueConsumed || getSource().hasNext();
		}

		@Override
		public double nextDoubleImpl()
		{
			if (!initialValueConsumed) {
				initialValueConsumed = true;
			} else {
				accumulationValue = accumulator.applyAsDouble(accumulationValue,
						getSource().nextDoubleImpl());
			}
			return accumulationValue;
		}

		@Override
		public void skipImpl()
		{
			nextDoubleImpl();
		}
	}
}
