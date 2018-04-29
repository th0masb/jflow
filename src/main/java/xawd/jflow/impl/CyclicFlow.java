/**
 *
 */
package xawd.jflow.impl;

import java.util.Iterator;
import java.util.PrimitiveIterator;
import java.util.function.Supplier;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;
import xawd.jflow.iterators.Skippable;

/**
 * @author t
 *
 */
public final class CyclicFlow
{
	private CyclicFlow() {}

	public static class OfObject<T> extends AbstractFlow<T>
	{
		private final Supplier<? extends Iterator<? extends T>> iteratorSupply;
		private Iterator<? extends T> iter;

		public OfObject(Supplier<? extends Iterator<? extends T>> iteratorSupply) {
			this.iteratorSupply = iteratorSupply;
			iter = iteratorSupply.get();
			if (!iter.hasNext()) {
				throw new IllegalArgumentException("can't cycle empty");
			}
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public T next() {
			if (!iter.hasNext()) {
				iter = iteratorSupply.get();
			}
			return iter.next();
		}

		@Override
		public void skip() {
			if (!iter.hasNext()) {
				iter = iteratorSupply.get();
			}
			if (iter instanceof Skippable) {
				((Skippable) iter).skip();
			}
			else {
				iter.next();
			}
		}
	}

	public static class OfLong extends AbstractLongFlow
	{
		private final Supplier<? extends PrimitiveIterator.OfLong> iteratorSupplier;
		private PrimitiveIterator.OfLong iter;

		public OfLong(Supplier<? extends PrimitiveIterator.OfLong> iteratorSupplier) {
			this.iteratorSupplier = iteratorSupplier;
			iter = iteratorSupplier.get();
			if (!iter.hasNext()) {
				throw new IllegalArgumentException("can't cycle empty");
			}
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public long nextLong() {
			if (!iter.hasNext()) {
				iter = iteratorSupplier.get();
			}
			return iter.nextLong();
		}

		@Override
		public void skip() {
			if (!iter.hasNext()) {
				iter = iteratorSupplier.get();
			}
			if (iter instanceof Skippable) {
				((Skippable) iter).skip();
			}
			else {
				iter.nextLong();
			}
		}
	}

	public static class OfDouble extends AbstractDoubleFlow
	{
		private final Supplier<? extends PrimitiveIterator.OfDouble> iteratorSupplier;
		private PrimitiveIterator.OfDouble iter;

		public OfDouble(Supplier<? extends PrimitiveIterator.OfDouble> iteratorSupplier) {
			this.iteratorSupplier = iteratorSupplier;
			iter = iteratorSupplier.get();
			if (!iter.hasNext()) {
				throw new IllegalArgumentException("can't cycle empty");
			}
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public double nextDouble() {
			if (!iter.hasNext()) {
				iter = iteratorSupplier.get();
			}
			return iter.nextDouble();
		}

		@Override
		public void skip() {
			if (!iter.hasNext()) {
				iter = iteratorSupplier.get();
			}
			if (iter instanceof Skippable) {
				((Skippable) iter).skip();
			}
			else {
				iter.nextDouble();
			}
		}
	}

	public static class OfInt extends AbstractIntFlow
	{
		private final Supplier<? extends PrimitiveIterator.OfInt> iteratorSupplier;
		private PrimitiveIterator.OfInt iter;

		public OfInt(Supplier<? extends PrimitiveIterator.OfInt> iteratorSupplier) {
			this.iteratorSupplier = iteratorSupplier;
			iter = iteratorSupplier.get();
			if (!iter.hasNext()) {
				throw new IllegalArgumentException("can't cycle empty");
			}
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public int nextInt() {
			if (!iter.hasNext()) {
				iter = iteratorSupplier.get();
			}
			return iter.nextInt();
		}

		@Override
		public void skip() {
			if (!iter.hasNext()) {
				iter = iteratorSupplier.get();
			}
			if (iter instanceof Skippable) {
				((Skippable) iter).skip();
			}
			else {
				iter.nextInt();
			}
		}
	}
}
