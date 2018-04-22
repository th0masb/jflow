/**
 * 
 */
package xawd.jflow;

import java.util.Iterator;
import java.util.OptionalLong;
import java.util.function.LongBinaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;

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

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#mapToObject(java.util.function.LongFunction)
	 */
	@Override
	public <T> ObjectFlow<T> mapToObject(final LongFunction<T> f) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#mapToDouble(java.util.function.LongToDoubleFunction)
	 */
	@Override
	public DoubleFlow mapToDouble(final LongToDoubleFunction f) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#mapToInt(java.util.function.LongToIntFunction)
	 */
	@Override
	public LongFlow mapToInt(final LongToIntFunction f) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#zipWith(java.util.Iterator)
	 */
	@Override
	public <T> ObjectFlow<LongWith<T>> zipWith(final Iterator<T> other) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#zipWith(java.util.PrimitiveIterator.OfLong)
	 */
	@Override
	public ObjectFlow<LongPair> zipWith(final OfLong other) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#zipWith(java.util.PrimitiveIterator.OfDouble)
	 */
	@Override
	public ObjectFlow<DoubleWithLong> zipWith(final OfDouble other) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#zipWith(java.util.PrimitiveIterator.OfInt)
	 */
	@Override
	public ObjectFlow<IntWithLong> zipWith(final OfInt other) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#enumerate()
	 */
	@Override
	public ObjectFlow<IntWithLong> enumerate() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see xawd.jflow.LongFlow#take(int)
	 */
	@Override
	public LongFlow take(final int n) {
		// TODO Auto-generated method stub
		return null;
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
