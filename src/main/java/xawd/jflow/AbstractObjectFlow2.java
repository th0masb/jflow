/**
 * 
 */
package xawd.jflow;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.PrimitiveIterator.OfDouble;
import java.util.PrimitiveIterator.OfInt;
import java.util.PrimitiveIterator.OfLong;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import xawd.jflow.iterators.Skippable;
import xawd.jflow.zippedpairs.DoubleWith;
import xawd.jflow.zippedpairs.IntWith;
import xawd.jflow.zippedpairs.LongWith;
import xawd.jflow.zippedpairs.Pair;

/**
 * @author t
 *
 */
public abstract class AbstractObjectFlow2<T> implements ObjectFlow<T> {

	@Override
	public <R> ObjectFlow<R> map(final Function<? super T, R> f) {
		final AbstractObjectFlow2<T> src = this;
		
		return new AbstractObjectFlow2<R>() {
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public R next() {
				return f.apply(src.next());
			}
			@Override
			public void skip() {
				src.skip();
			}
		};
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#mapToInt(java.util.function.ToIntFunction)
	 */
	@Override
	public IntFlow mapToInt(final ToIntFunction<? super T> f) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#mapToDouble(java.util.function.ToDoubleFunction)
	 */
	@Override
	public DoubleFlow mapToDouble(final ToDoubleFunction<? super T> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LongFlow mapToLong(final ToLongFunction<? super T> f) {
		final AbstractObjectFlow2<T> src = this;
		
		return new AbstractLongFlow() {
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public long nextLong() {
				return f.applyAsLong(src.next());
			}
			@Override
			public void skip() {
				src.skip();
			}
		};
	}

	@Override
	public <R> ObjectFlow<Pair<T, R>> zipWith(final Iterator<R> other) {
		final AbstractObjectFlow2<T> src = this;
		
		return new AbstractObjectFlow2<Pair<T,R>>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public Pair<T, R> next() {
				return Pair.of(src.next(), other.next());
			}
			@Override
			public void skip() {
				src.skip();
				if (other instanceof Skippable) {
					((Skippable) other).skip();
				}
				else {
					other.next();
				}
			}
		};
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#zipWith(java.util.PrimitiveIterator.OfInt)
	 */
	@Override
	public ObjectFlow<IntWith<T>> zipWith(final OfInt other) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#zipWith(java.util.PrimitiveIterator.OfDouble)
	 */
	@Override
	public ObjectFlow<DoubleWith<T>> zipWith(final OfDouble other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectFlow<LongWith<T>> zipWith(final OfLong other) {
		final AbstractObjectFlow2<T> src = this;
		
		return new AbstractObjectFlow2<LongWith<T>>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public LongWith<T> next() {
				return LongWith.of(other.nextLong(), src.next());
			}
			@Override
			public void skip() {
				src.skip();
				if (other instanceof Skippable) {
					((Skippable) other).skip();
				}
				else {
					other.nextLong();
				}
			}
		};
	}

	@Override
	public <U, R> ObjectFlow<R> combineWith(final Iterator<U> other, final BiFunction<T, U, R> f) {
		final AbstractObjectFlow2<T> src = this;
		
		return new AbstractObjectFlow2<R>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public R next() {
				return f.apply(src.next(), other.next());
			}
			@Override
			public void skip() {
				src.skip();
				if (other instanceof Skippable) {
					((Skippable) other).skip();
				}
				else {
					other.next();
				}
			}
		};
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#enumerate()
	 */
	@Override
	public ObjectFlow<IntWith<T>> enumerate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectFlow<T> take(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final AbstractObjectFlow2<T> src = this;
		
		return new AbstractObjectFlow2<T>() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < n && src.hasNext();
			}
			@Override
			public T next() {
				if (count++ >= n) {
					throw new NoSuchElementException();
				}
				else {
					return src.next();
				}
			}
			@Override
			public void skip() {
				if (count++ >= n) {
					throw new NoSuchElementException();
				}
				else {
					src.skip();
				}
			}
		};
	}

	@Override
	public ObjectFlow<T> takeWhile(final Predicate<? super T> p) {
		final AbstractObjectFlow2<T> src = this;
		
		return new AbstractObjectFlow2<T>() {
			T cached = null;
			boolean nextReady = false;
			boolean iteratorExhausted = false;
			@Override
			public boolean hasNext() {
				if (nextReady) {
					return true;
				}
				if (src.hasNext()) {
					cached = src.next();
					if (p.test(cached)) {
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
			public T next() {
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
			public void skip() {
				if (iteratorExhausted) {
					throw new NoSuchElementException();
				}
				else if (nextReady) {
					nextReady = false;
					src.skip();
				}
				else { // hasnext wasn't called
					if (hasNext()) {
						nextReady = false;
						src.skip();
					}
					else {
						throw new NoSuchElementException();
					}
				}
			}
		};
	}

	@Override
	public ObjectFlow<T> drop(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final AbstractObjectFlow2<T> src = this;
		
		return new AbstractObjectFlow2<T>() {
			{
				for (int i = 0; i < n && src.hasNext(); i++) {
					src.skip();
				}
			}
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public T next() {
				return src.next();
			}
			@Override
			public void skip() {
				src.skip();
			}
		};
	}

	@Override
	public ObjectFlow<T> dropWhile(final Predicate<? super T> p) {
		final AbstractObjectFlow2<T> src = this;
		
		return new AbstractObjectFlow2<T>() {
			T firstFailure = null;
			{
				while (src.hasNext() && firstFailure == null)
				{
					final T next = src.next();
					if (!p.test(next)) {
						firstFailure = next;
					}
				}
			}
			@Override
			public boolean hasNext() {
				return src.hasNext() || firstFailure != null;
			}

			@Override
			public T next() {
				if (firstFailure != null) {
					final T tmp = firstFailure;
					firstFailure = null;
					return tmp;
				}
				else {
					return src.next();
				}
			}
			@Override
			public void skip() {
				if (firstFailure != null) {
					firstFailure = null;
				}
				else {
					src.skip();
				}
			}
		};
	}

	@Override
	public ObjectFlow<T> filter(final Predicate<? super T> p) {
		final AbstractObjectFlow2<T> src = this;
		
		return new AbstractObjectFlow2<T>() {

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void skip() {
				// TODO Auto-generated method stub
				
			}
		};
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#append(java.util.Iterator)
	 */
	@Override
	public ObjectFlow<T> append(final Iterator<? extends T> other) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#append(java.lang.Object)
	 */
	@Override
	public ObjectFlow<T> append(final T t) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#insert(java.util.Iterator)
	 */
	@Override
	public ObjectFlow<T> insert(final Iterator<? extends T> other) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#insert(java.lang.Object)
	 */
	@Override
	public ObjectFlow<T> insert(final T t) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#minByKey(java.util.function.ToDoubleFunction)
	 */
	@Override
	public Optional<T> minByKey(final ToDoubleFunction<? super T> key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#minByObjectKey(java.util.function.Function)
	 */
	@Override
	public <C extends Comparable<C>> Optional<T> minByObjectKey(final Function<? super T, C> key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#maxByKey(java.util.function.ToDoubleFunction)
	 */
	@Override
	public Optional<T> maxByKey(final ToDoubleFunction<T> key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#maxByObjectKey(java.util.function.Function)
	 */
	@Override
	public <C extends Comparable<C>> Optional<T> maxByObjectKey(final Function<? super T, C> key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#allMatch(java.util.function.Predicate)
	 */
	@Override
	public boolean allMatch(final Predicate<? super T> predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#anyMatch(java.util.function.Predicate)
	 */
	@Override
	public boolean anyMatch(final Predicate<? super T> predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#noneMatch(java.util.function.Predicate)
	 */
	@Override
	public boolean noneMatch(final Predicate<? super T> predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#count()
	 */
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#reduce(java.lang.Object, java.util.function.BinaryOperator)
	 */
	@Override
	public T reduce(final T id, final BinaryOperator<? super T> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#reduce(java.util.function.BinaryOperator)
	 */
	@Override
	public Optional<T> reduce(final BinaryOperator<? super T> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#accumulate(java.util.function.BinaryOperator)
	 */
	@Override
	public ObjectFlow<T> accumulate(final BinaryOperator<? super T> accumulator) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.ObjectFlow#accumulate(java.lang.Object, java.util.function.BiFunction)
	 */
	@Override
	public <R> ObjectFlow<R> accumulate(final R id, final BiFunction<R, T, R> accumulator) {
		// TODO Auto-generated method stub
		return null;
	}
}
