/**
 * 
 */
package xawd.jflow.impl;

import java.util.NoSuchElementException;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;

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
		private final int elementCap;
		
		private int count = 0;
		
		public OfObject(final IntFunction<T> src, final int elementCap) {
			this.src = src;
			this.elementCap = elementCap;
		}
		
		public OfObject(final IntFunction<T> src) {
			this(src, -1);
		}

		@Override
		public boolean hasNext() {
			if (elementCap < 0) {
				return true;
			}
			else {
				return count < elementCap;
			}
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
			next();
		}
	}
	
	public static class OfLong extends AbstractLongFlow
	{
		private final IntToLongFunction src;
		private final int elementCap;
		
		private int count = 0;
		
		public OfLong(final IntToLongFunction src, final int elementCap) {
			this.src = src;
			this.elementCap = elementCap;
		}
		
		public OfLong(final IntToLongFunction src) {
			this(src, -1);
		}

		@Override
		public boolean hasNext() {
			if (elementCap < 0) {
				return true;
			}
			else {
				return count < elementCap;
			}
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
			next();
		}
	}
	
	public static class OfInt extends AbstractIntFlow
	{
		private final IntUnaryOperator src;
		private final int elementCap;
		
		private int count = 0;
		
		public OfInt(final IntUnaryOperator src, final int elementCap) {
			this.src = src;
			this.elementCap = elementCap;
		}
		
		public OfInt(final IntUnaryOperator src) {
			this(src, -1);
		}

		@Override
		public boolean hasNext() {
			if (elementCap < 0) {
				return true;
			}
			else {
				return count < elementCap;
			}
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
			next();
		}
	}
	
	public static class OfDouble extends AbstractDoubleFlow
	{
		private final IntToDoubleFunction src;
		private final int elementCap;
		
		private int count = 0;
		
		public OfDouble(final IntToDoubleFunction src, final int elementCap) {
			this.src = src;
			this.elementCap = elementCap;
		}
		
		public OfDouble(final IntToDoubleFunction src) {
			this(src, -1);
		}

		@Override
		public boolean hasNext() {
			if (elementCap < 0) {
				return true;
			}
			else {
				return count < elementCap;
			}
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
			next();
		}
	}
}
