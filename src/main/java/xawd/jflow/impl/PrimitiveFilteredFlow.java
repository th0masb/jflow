/**
 * 
 */
package xawd.jflow.impl;

import java.util.NoSuchElementException;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;
import xawd.jflow.DoubleFlow;
import xawd.jflow.IntFlow;
import xawd.jflow.LongFlow;

/**
 * @author t
 *
 */
public final class PrimitiveFilteredFlow 
{
	private PrimitiveFilteredFlow() {}
	
	public static class OfLong extends AbstractLongFlow
	{
		private final LongFlow src;
		private final LongPredicate predicate;
		
		private boolean nextReady = false;
		private long cached = -1;

		public OfLong(final LongFlow src, final LongPredicate predicate) {
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext() {
			while (!nextReady && src.hasNext()) {
				final long next = src.nextLong();
				if (predicate.test(next)) {
					nextReady = true;
					cached = next;
				}
			}
			return nextReady;
		}
		
		@Override
		public long nextLong() {
			if (nextReady) {
				nextReady = false;
				return cached;
			}
			else {
				if (hasNext()) {
					nextReady = false;
					return cached;
				}
				else {
					throw new NoSuchElementException();
				}
			}
		}
		
		@Override
		public void skip() {
			if (nextReady) {
				nextReady = false;
			}
			else {
				if (hasNext()) {
					nextReady = false;
				}
				else {
					throw new NoSuchElementException();
				}
			}
		}
	}
	
	public static class OfInt extends AbstractIntFlow
	{
		private final IntFlow src;
		private final IntPredicate predicate;
		
		private boolean nextReady = false;
		private int cached = -1;

		public OfInt(final IntFlow src, final IntPredicate predicate) {
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext() {
			while (!nextReady && src.hasNext()) {
				final int next = src.nextInt();
				if (predicate.test(next)) {
					nextReady = true;
					cached = next;
				}
			}
			return nextReady;
		}
		
		@Override
		public int nextInt() {
			if (nextReady) {
				nextReady = false;
				return cached;
			}
			else {
				if (hasNext()) {
					nextReady = false;
					return cached;
				}
				else {
					throw new NoSuchElementException();
				}
			}
		}
		
		@Override
		public void skip() {
			if (nextReady) {
				nextReady = false;
			}
			else {
				if (hasNext()) {
					nextReady = false;
				}
				else {
					throw new NoSuchElementException();
				}
			}
		}
	}
	
	public static class OfDouble extends AbstractDoubleFlow
	{
		private final DoubleFlow src;
		private final DoublePredicate predicate;
		
		private boolean nextReady = false;
		private double cached = -1;

		public OfDouble(final DoubleFlow src, final DoublePredicate predicate) {
			this.src = src;
			this.predicate = predicate;
		}

		@Override
		public boolean hasNext() {
			while (!nextReady && src.hasNext()) {
				final double next = src.nextDouble();
				if (predicate.test(next)) {
					nextReady = true;
					cached = next;
				}
			}
			return nextReady;
		}
		
		@Override
		public double nextDouble() {
			if (nextReady) {
				nextReady = false;
				return cached;
			}
			else {
				if (hasNext()) {
					nextReady = false;
					return cached;
				}
				else {
					throw new NoSuchElementException();
				}
			}
		}
		
		@Override
		public void skip() {
			if (nextReady) {
				nextReady = false;
			}
			else {
				if (hasNext()) {
					nextReady = false;
				}
				else {
					throw new NoSuchElementException();
				}
			}
		}
	}
}
