/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.NoSuchElementException;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.AbstractLongFlow;

/**
 * @author t
 *
 */
public final class FlowFromFunction
{
	private FlowFromFunction() {}

	public static class OfObject<T> extends AbstractFlow<T>
	{
		private final IntFunction<T> src;
		private int count = 0;

		public OfObject(final IntFunction<T> src, final int elementCap) {
			super(elementCap);
			this.src = src;
		}

		public OfObject(final IntFunction<T> src) {
			this(src, -1);
		}

		@Override
		public boolean hasNext() {
			return size < 0 || count < size;
		}

		@Override
		public T next() {
			if (hasNext()) {
				return src.apply(count++);
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip() {
			if (hasNext()) {
				count++;
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfLong extends AbstractLongFlow
	{
		private final IntToLongFunction src;
		private final int elementCap;

		private int count = 0;

		public OfLong(final IntToLongFunction src, final int elementCap) {
			if (elementCap < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.elementCap = elementCap;
		}

		@Override
		public boolean hasNext() {
			return count < elementCap;
		}

		@Override
		public long nextLong() {
			if (hasNext()) {
				return src.applyAsLong(count++);
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip() {
			if (hasNext()) {
				count++;
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfInt extends AbstractIntFlow
	{
		private final IntUnaryOperator src;
		private final int elementCap;

		private int count = 0;

		public OfInt(final IntUnaryOperator src, final int elementCap) {
			if (elementCap < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.elementCap = elementCap;
		}

		@Override
		public boolean hasNext() {
			return count < elementCap;
		}

		@Override
		public int nextInt() {
			if (hasNext()) {
				return src.applyAsInt(count++);
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip() {
			if (hasNext()) {
				count++;
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}

	public static class OfDouble extends AbstractDoubleFlow
	{
		private final IntToDoubleFunction src;
		private final int elementCap;

		private int count = 0;

		public OfDouble(final IntToDoubleFunction src, final int elementCap) {
			if (elementCap < 0) {
				throw new IllegalArgumentException();
			}
			this.src = src;
			this.elementCap = elementCap;
		}

		@Override
		public boolean hasNext() {
			return count < elementCap;
		}

		@Override
		public double nextDouble() {
			if (hasNext()) {
				return src.applyAsDouble(count++);
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void skip() {
			if (hasNext()) {
				count++;
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}
}
