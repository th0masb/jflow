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
public final class PrimitiveDropWhileFlow {

	private PrimitiveDropWhileFlow() {}
	
	public static class OfLong extends AbstractLongFlow 
	{
		private final LongFlow src;
		private final LongPredicate predicate;
		
		private boolean firstFailureInitialized, firstFailureConsumed;
		private long firstFailure;
		
		public OfLong(final LongFlow src, final LongPredicate predicate) {
			this.src = src;
			this.predicate = predicate;
		}
		
		@Override
		public boolean hasNext() 
		{
			if (!firstFailureInitialized) {
				while (!firstFailureInitialized && src.hasNext()) {
					final long next = src.nextLong();
					if (!predicate.test(next)) {
						firstFailure = next;
						firstFailureInitialized = true;
						return true;
					}
				}
				return false;
			}
			else if (!firstFailureConsumed) {
				return true;
			}
			else {
				return src.hasNext();
			}
		}
		
		@Override
		public long nextLong() {
			if (!firstFailureInitialized) {
				if (hasNext()) {
					firstFailureConsumed = true;
					return firstFailure;
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else if (firstFailureConsumed) {
				return src.nextLong();
			}
			else {
				firstFailureConsumed = true;
				return firstFailure;
			}
		}

		@Override
		public void skip() {
			if (!firstFailureInitialized) {
				if (hasNext()) {
					firstFailureConsumed = true;
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else if (firstFailureConsumed) {
				src.skip();
			}
			else {
				firstFailureConsumed = true;
			}
		}
	}

	public static class OfDouble extends AbstractDoubleFlow 
	{
		private final DoubleFlow src;
		private final DoublePredicate predicate;
		
		private boolean firstFailureInitialized, firstFailureConsumed;
		private double firstFailure;
		
		public OfDouble(final DoubleFlow src, final DoublePredicate predicate) {
			this.src = src;
			this.predicate = predicate;
		}
		
		@Override
		public boolean hasNext() 
		{
			if (!firstFailureInitialized) {
				while (!firstFailureInitialized && src.hasNext()) {
					final double next = src.nextDouble();
					if (!predicate.test(next)) {
						firstFailure = next;
						firstFailureInitialized = true;
						return true;
					}
				}
				return false;
			}
			else if (!firstFailureConsumed) {
				return true;
			}
			else {
				return src.hasNext();
			}
		}
		
		@Override
		public double nextDouble() {
			if (!firstFailureInitialized) {
				if (hasNext()) {
					firstFailureConsumed = true;
					return firstFailure;
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else if (firstFailureConsumed) {
				return src.nextDouble();
			}
			else {
				firstFailureConsumed = true;
				return firstFailure;
			}
		}

		@Override
		public void skip() {
			if (!firstFailureInitialized) {
				if (hasNext()) {
					firstFailureConsumed = true;
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else if (firstFailureConsumed) {
				src.skip();
			}
			else {
				firstFailureConsumed = true;
			}
		}
	}
	
	public static class OfInt extends AbstractIntFlow 
	{
		private final IntFlow src;
		private final IntPredicate predicate;
		
		private boolean firstFailureInitialized, firstFailureConsumed;
		private int firstFailure;
		
		public OfInt(final IntFlow src, final IntPredicate predicate) {
			this.src = src;
			this.predicate = predicate;
		}
		
		@Override
		public boolean hasNext() 
		{
			if (!firstFailureInitialized) {
				while (!firstFailureInitialized && src.hasNext()) {
					final int next = src.nextInt();
					if (!predicate.test(next)) {
						firstFailure = next;
						firstFailureInitialized = true;
						return true;
					}
				}
				return false;
			}
			else if (!firstFailureConsumed) {
				return true;
			}
			else {
				return src.hasNext();
			}
		}
		
		@Override
		public int nextInt() {
			if (!firstFailureInitialized) {
				if (hasNext()) {
					firstFailureConsumed = true;
					return firstFailure;
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else if (firstFailureConsumed) {
				return src.nextInt();
			}
			else {
				firstFailureConsumed = true;
				return firstFailure;
			}
		}

		@Override
		public void skip() {
			if (!firstFailureInitialized) {
				if (hasNext()) {
					firstFailureConsumed = true;
				}
				else {
					throw new NoSuchElementException();
				}
			}
			else if (firstFailureConsumed) {
				src.skip();
			}
			else {
				firstFailureConsumed = true;
			}
		}
	}
}
