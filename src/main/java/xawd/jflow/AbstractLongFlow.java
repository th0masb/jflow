/**
 *
 */
package xawd.jflow;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.OptionalLong;
import java.util.function.LongBinaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;

import xawd.jflow.iterators.Skippable;
import xawd.jflow.utilities.Iter;
import xawd.jflow.zippedpairs.DoubleWithLong;
import xawd.jflow.zippedpairs.IntWithLong;
import xawd.jflow.zippedpairs.LongPair;
import xawd.jflow.zippedpairs.LongWith;

/**
 * @author t
 *
 */
public abstract class AbstractLongFlow implements LongFlow
{
	@Override
	public LongFlow map(final LongUnaryOperator f) {
		final AbstractLongFlow src = this;
		return new AbstractLongFlow() {
			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public long nextLong() {
				return f.applyAsLong(src.nextLong());
			}
			@Override
			public void skip() {
				src.skip();
			}
		};
	}

	@Override
	public <T> Flow<T> mapToObject(final LongFunction<T> f) {
		final AbstractLongFlow src = this;
		return new AbstractFlow<T>() {

			@Override
			public boolean hasNext() {
				return src.hasNext();
			}
			@Override
			public T next() {
				return f.apply(src.nextLong());
			}
			@Override
			public void skip() {
				src.nextLong();
			}
		};
	}

	@Override
	public DoubleFlow mapToDouble(final LongToDoubleFunction f)
	{
		final AbstractLongFlow src = this;

		return new AbstractDoubleFlow() {
			@Override
			public boolean hasNext()
			{
				return src.hasNext();
			}
			@Override
			public double nextDouble()
			{
				return f.applyAsDouble(src.nextLong());
			}
			@Override
			public void skip()
			{
				src.skip();
			}
		};
	}

	@Override
	public IntFlow mapToInt(final LongToIntFunction f)
	{
		final AbstractLongFlow src = this;

		return new AbstractIntFlow() {
			@Override
			public boolean hasNext()
			{
				return src.hasNext();
			}
			@Override
			public int nextInt()
			{
				return f.applyAsInt(src.nextLong());
			}
			@Override
			public void skip()
			{
				src.skip();
			}
		};
	}

	@Override
	public <T> Flow<LongWith<T>> zipWith(final Iterator<T> other) {
		final AbstractLongFlow src = this;

		return new AbstractFlow<LongWith<T>>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public LongWith<T> next() {
				return LongWith.of(src.nextLong(), other.next());
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

	@Override
	public Flow<LongPair> zipWith(final OfLong other)
	{
		final AbstractLongFlow src = this;

		return new AbstractFlow<LongPair>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public LongPair next() {
				return LongPair.of(src.nextLong(), other.nextLong());
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
	public Flow<DoubleWithLong> zipWith(final OfDouble other)
	{
		final AbstractLongFlow src = this;

		return new AbstractFlow<DoubleWithLong>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public DoubleWithLong next() {
				return DoubleWithLong.of(other.nextDouble(), src.nextLong());
			}
			@Override
			public void skip() {
				src.skip();
				if (other instanceof Skippable) {
					((Skippable) other).skip();
				}
				else {
					other.nextDouble();
				}
			}
		};
	}

	@Override
	public Flow<IntWithLong> zipWith(final OfInt other)
	{
		final AbstractLongFlow src = this;

		return new AbstractFlow<IntWithLong>() {
			@Override
			public boolean hasNext() {
				return src.hasNext() && other.hasNext();
			}
			@Override
			public IntWithLong next() {
				return IntWithLong.of(other.nextInt(), src.nextLong());
			}
			@Override
			public void skip() {
				src.skip();
				if (other instanceof Skippable) {
					((Skippable) other).skip();
				}
				else {
					other.nextInt();
				}
			}
		};
	}

	@Override
	public Flow<IntWithLong> enumerate() {
		return zipWith(Iter.naturalNumbers());
	}

	@Override
	public LongFlow take(final int n)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final AbstractLongFlow src = this;

		return new AbstractLongFlow() {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < n && src.hasNext();
			}
			@Override
			public long nextLong() {
				if (count++ >= n) {
					throw new NoSuchElementException();
				}
				else {
					return src.nextLong();
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

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#takeWhile(java.util.function.LongPredicate)
	 */
	@Override
	public LongFlow takeWhile(final LongPredicate p) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#drop(int)
	 */
	@Override
	public LongFlow drop(final int n) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#dropWhile(java.util.function.LongPredicate)
	 */
	@Override
	public LongFlow dropWhile(final LongPredicate p) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#filter(java.util.function.LongPredicate)
	 */
	@Override
	public LongFlow filter(final LongPredicate p) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#append(long)
	 */
	@Override
	public LongFlow append(final long x) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#append(java.util.PrimitiveIterator.OfLong)
	 */
	@Override
	public LongFlow append(final OfLong other) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#insert(java.util.PrimitiveIterator.OfLong)
	 */
	@Override
	public LongFlow insert(final OfLong other) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#insert(long)
	 */
	@Override
	public LongFlow insert(final long x) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#min()
	 */
	@Override
	public OptionalLong min() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#min(long)
	 */
	@Override
	public long min(final long defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#minByKey(long, java.util.function.LongToDoubleFunction)
	 */
	@Override
	public long minByKey(final long defaultValue, final LongToDoubleFunction key) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#minByKey(java.util.function.LongToDoubleFunction)
	 */
	@Override
	public OptionalLong minByKey(final LongToDoubleFunction key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#minByObjectKey(java.util.function.LongFunction)
	 */
	@Override
	public <C extends Comparable<C>> OptionalLong minByObjectKey(final LongFunction<C> key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#max()
	 */
	@Override
	public OptionalLong max() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#max(long)
	 */
	@Override
	public long max(final long defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#maxByKey(long, java.util.function.LongToDoubleFunction)
	 */
	@Override
	public long maxByKey(final long defaultValue, final LongToDoubleFunction key) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#maxByKey(java.util.function.LongToDoubleFunction)
	 */
	@Override
	public OptionalLong maxByKey(final LongToDoubleFunction key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#maxByObjectKey(java.util.function.LongFunction)
	 */
	@Override
	public <C extends Comparable<C>> OptionalLong maxByObjectKey(final LongFunction<C> key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#allMatch(java.util.function.LongPredicate)
	 */
	@Override
	public boolean allMatch(final LongPredicate predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#anyMatch(java.util.function.LongPredicate)
	 */
	@Override
	public boolean anyMatch(final LongPredicate predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#noneMatch(java.util.function.LongPredicate)
	 */
	@Override
	public boolean noneMatch(final LongPredicate predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#count()
	 */
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#reduce(long, java.util.function.LongBinaryOperator)
	 */
	@Override
	public long reduce(final long id, final LongBinaryOperator reducer) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#reduce(java.util.function.LongBinaryOperator)
	 */
	@Override
	public OptionalLong reduce(final LongBinaryOperator reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#accumulate(java.util.function.LongBinaryOperator)
	 */
	@Override
	public LongFlow accumulate(final LongBinaryOperator accumulator) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#accumulate(long, java.util.function.LongBinaryOperator)
	 */
	@Override
	public LongFlow accumulate(final long id, final LongBinaryOperator accumulator) {
		// TODO Auto-generated method stub
		return null;
	}

}
