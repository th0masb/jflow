/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.Iterator;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.Supplier;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.AbstractIntIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.AbstractLongIterator;

/**
 * @author t
 *
 */
public final class CyclicIterator
{
	private CyclicIterator()
	{
	}

	public static class OfObject<T> extends AbstractEnhancedIterator<T>
	{
		private final Supplier<? extends Iterator<? extends T>> iteratorSupply;
		private Iterator<? extends T> iter;

		public OfObject(Supplier<? extends Iterator<? extends T>> iteratorSupply)
		{
			super(OptionalInt.empty());
			this.iteratorSupply = iteratorSupply;
			iter = iteratorSupply.get();
			if (!iter.hasNext()) {
				throw new IllegalArgumentException("can't cycle empty");
			}
		}

		@Override
		public boolean hasNext()
		{
			return true;
		}

		@Override
		public T next()
		{
			if (!iter.hasNext()) {
				iter = iteratorSupply.get();
			}
			return iter.next();
		}

		@Override
		public void skip()
		{
			if (!iter.hasNext()) {
				iter = iteratorSupply.get();
			}
			ImplUtils.skip(iter);
		}
	}

	public static class OfLong extends AbstractLongIterator
	{
		private final Supplier<? extends PrimitiveIterator.OfLong> iteratorSupplier;
		private PrimitiveIterator.OfLong iter;

		public OfLong(Supplier<? extends PrimitiveIterator.OfLong> iteratorSupplier)
		{
			super(OptionalInt.empty());
			this.iteratorSupplier = iteratorSupplier;
			iter = iteratorSupplier.get();
			if (!iter.hasNext()) {
				throw new IllegalArgumentException("can't cycle empty");
			}
		}

		@Override
		public boolean hasNext()
		{
			return true;
		}

		@Override
		public long nextLong()
		{
			if (!iter.hasNext()) {
				iter = iteratorSupplier.get();
			}
			return iter.nextLong();
		}

		@Override
		public void skip()
		{
			if (!iter.hasNext()) {
				iter = iteratorSupplier.get();
			}
			ImplUtils.skip(iter);
		}
	}

	public static class OfDouble extends AbstractDoubleIterator
	{
		private final Supplier<? extends PrimitiveIterator.OfDouble> iteratorSupplier;
		private PrimitiveIterator.OfDouble iter;

		public OfDouble(Supplier<? extends PrimitiveIterator.OfDouble> iteratorSupplier)
		{
			super(OptionalInt.empty());
			this.iteratorSupplier = iteratorSupplier;
			iter = iteratorSupplier.get();
			if (!iter.hasNext()) {
				throw new IllegalArgumentException("can't cycle empty");
			}
		}

		@Override
		public boolean hasNext()
		{
			return true;
		}

		@Override
		public double nextDouble()
		{
			if (!iter.hasNext()) {
				iter = iteratorSupplier.get();
			}
			return iter.nextDouble();
		}

		@Override
		public void skip()
		{
			if (!iter.hasNext()) {
				iter = iteratorSupplier.get();
			}
			ImplUtils.skip(iter);
		}
	}

	public static class OfInt extends AbstractIntIterator
	{
		private final Supplier<? extends PrimitiveIterator.OfInt> iteratorSupplier;
		private PrimitiveIterator.OfInt iter;

		public OfInt(Supplier<? extends PrimitiveIterator.OfInt> iteratorSupplier)
		{
			super(OptionalInt.empty());
			this.iteratorSupplier = iteratorSupplier;
			iter = iteratorSupplier.get();
			if (!iter.hasNext()) {
				throw new IllegalArgumentException("can't cycle empty");
			}
		}

		@Override
		public boolean hasNext()
		{
			return true;
		}

		@Override
		public int nextInt()
		{
			if (!iter.hasNext()) {
				iter = iteratorSupplier.get();
			}
			return iter.nextInt();
		}

		@Override
		public void skip()
		{
			if (!iter.hasNext()) {
				iter = iteratorSupplier.get();
			}
			ImplUtils.skip(iter);
		}
	}
}
