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
 * @author ThomasB
 * @since 23 Apr 2018
 */
public final class PrimitiveTakeWhileFlow
{
	private PrimitiveTakeWhileFlow() {}
	
	public static class OfInt extends AbstractIntFlow
	{
		private final IntFlow src;
		private final IntPredicate predicate;
		
		private int cached = -1;
		private boolean nextReady = false;
		private boolean iteratorExhausted = false;
		
		public OfInt(final IntFlow src, final IntPredicate predicate) 
		{
			this.src = src;
			this.predicate = predicate;
		}
		
		@Override
		public boolean hasNext()
		{
			if (nextReady) {
				return true;
			}
			else if (src.hasNext()) {
				final int tmp = src.nextInt();
				if (predicate.test(tmp)) {
					cached = tmp;
					nextReady = true;
					return true;
				}
				else {
					iteratorExhausted = true;
					return false;
				}
			}
			else {
				iteratorExhausted = true;
				return false;
			}
		}
		
		@Override
		public int nextInt()
		{
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			}
			else if (nextReady) {
				nextReady = false;
				return cached;
			}
			else { // hasnext wasn't called
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
		public void skip()
		{
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			}
			else if (nextReady) {
				nextReady = false;
			}
			else { // hasnext wasn't called
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
		
		private double cached = -1;
		private boolean nextReady = false;
		private boolean iteratorExhausted = false;
		
		public OfDouble(final DoubleFlow src, final DoublePredicate predicate) 
		{
			this.src = src;
			this.predicate = predicate;
		}
		
		@Override
		public boolean hasNext()
		{
			if (nextReady) {
				return true;
			}
			else if (src.hasNext()) {
				final double tmp = src.nextDouble();
				if (predicate.test(tmp)) {
					cached = tmp;
					nextReady = true;
					return true;
				}
				else {
					iteratorExhausted = true;
					return false;
				}
			}
			else {
				iteratorExhausted = true;
				return false;
			}
		}
		
		@Override
		public double nextDouble()
		{
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			}
			else if (nextReady) {
				nextReady = false;
				return cached;
			}
			else { // hasnext wasn't called
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
		public void skip()
		{
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			}
			else if (nextReady) {
				nextReady = false;
			}
			else { // hasnext wasn't called
				if (hasNext()) {
					nextReady = false;
				}
				else {
					throw new NoSuchElementException();
				}
			}
		}
	}

	public static class OfLong extends AbstractLongFlow
	{
		private final LongFlow src;
		private final LongPredicate predicate;
		
		private long cached = -1;
		private boolean nextReady = false;
		private boolean iteratorExhausted = false;
		
		public OfLong(final LongFlow src, final LongPredicate predicate) 
		{
			this.src = src;
			this.predicate = predicate;
		}
		
		@Override
		public boolean hasNext()
		{
			if (nextReady) {
				return true;
			}
			else if (src.hasNext()) {
				final long tmp = src.nextLong();
				if (predicate.test(tmp)) {
					cached = tmp;
					nextReady = true;
					return true;
				}
				else {
					iteratorExhausted = true;
					return false;
				}
			}
			else {
				iteratorExhausted = true;
				return false;
			}
		}
		
		@Override
		public long nextLong()
		{
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			}
			else if (nextReady) {
				nextReady = false;
				return cached;
			}
			else { // hasnext wasn't called
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
		public void skip()
		{
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			}
			else if (nextReady) {
				nextReady = false;
			}
			else { // hasnext wasn't called
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
