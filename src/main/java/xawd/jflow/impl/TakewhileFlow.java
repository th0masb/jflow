package xawd.jflow.impl;

import java.util.NoSuchElementException;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;
import xawd.jflow.DoubleFlow;
import xawd.jflow.Flow;
import xawd.jflow.IntFlow;
import xawd.jflow.LongFlow;

/**
 * @author ThomasB
 * @since 23 Apr 2018
 */
public final class TakewhileFlow
{
	private TakewhileFlow() {}
	
	public static class OfObject<T> extends AbstractFlow<T>
	{
		private final Flow<T> src;
		private final Predicate<? super T> predicate;
		
		private T cached = null;
		private boolean iteratorExhausted = false;
		
		public OfObject(final Flow<T> src, final Predicate<? super T> predicate) 
		{
			this.src = src;
			this.predicate = predicate;
		}
		
		@Override
		public boolean hasNext()
		{
			if (cached != null) {
				return true;
			}
			else if(src.hasNext()) {
				final T tmp  = src.next();
				if (predicate.test(tmp)) {
					cached = tmp;
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
		public T next()
		{
			if (iteratorExhausted) {
				throw new NoSuchElementException();
			}
			else if (cached != null) {
				final T tmp = cached;
				cached = null;
				return tmp;
			}
			else { // hasnext wasn't called
				if (hasNext()) {
					final T tmp = cached;
					cached = null;
					return tmp;
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
			else if (cached != null) {
				cached = null;
			}
			else { // hasnext wasn't called
				if (hasNext()) {
					cached = null;
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
