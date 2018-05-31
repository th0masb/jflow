package xawd.jflow.iterators;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.PrimitiveIterator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntUnaryOperator;

import xawd.jflow.iterators.iterables.DoubleFlowIterable;
import xawd.jflow.iterators.iterables.IntFlowIterable;
import xawd.jflow.iterators.iterables.LongFlowIterable;
import xawd.jflow.iterators.misc.DoublePair;
import xawd.jflow.iterators.misc.DoublePredicatePartition;
import xawd.jflow.iterators.misc.DoubleWith;
import xawd.jflow.iterators.misc.DoubleWithLong;
import xawd.jflow.iterators.misc.IntWithDouble;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public interface DoubleFlow extends SkippableDoubleIterator
{
	DoubleFlow map(final DoubleUnaryOperator f);

	<T> Flow<T> mapToObject(DoubleFunction<T> f);

	LongFlow mapToLong(DoubleToLongFunction f);

	IntFlow mapToInt(DoubleToIntFunction f);

	<E> Flow<DoubleWith<E>> zipWith(final Iterator<? extends E> other);

	Flow<DoublePair> zipWith(final PrimitiveIterator.OfDouble other);

	Flow<DoubleWithLong> zipWith(final PrimitiveIterator.OfLong other);

	Flow<IntWithDouble> zipWith(final PrimitiveIterator.OfInt other);

	DoubleFlow combineWith(final OfDouble other, final DoubleBinaryOperator combiner);

	Flow<IntWithDouble> enumerate();

	DoubleFlow take(final int n);

	DoubleFlow takeWhile(final DoublePredicate p);

	DoubleFlow skip(final int n);

	DoubleFlow skipWhile(final DoublePredicate p);

	DoubleFlow filter(final DoublePredicate p);

	DoubleFlow append(double... xs);

	DoubleFlow append(PrimitiveIterator.OfDouble other);

	DoubleFlow insert(PrimitiveIterator.OfDouble other);

	DoubleFlow insert(double... xs);

	DoubleFlow slice(IntUnaryOperator indexMapping);

	DoubleFlow accumulate(DoubleBinaryOperator accumulator);

	DoubleFlow accumulate(double id, DoubleBinaryOperator accumulator);



	OptionalDouble min();

	double min(double defaultValue);

	double minByKey(double defaultValue, final DoubleUnaryOperator key);

	OptionalDouble minByKey(final DoubleUnaryOperator key);

	OptionalDouble max();

	double max(double defaultValue);

	double maxByKey(double defaultValue, final DoubleUnaryOperator key);

	OptionalDouble maxByKey(final DoubleUnaryOperator key);


	boolean areAllEqual();

	boolean allMatch(final DoublePredicate predicate);

	boolean anyMatch(final DoublePredicate predicate);

	boolean noneMatch(final DoublePredicate predicate);

	DoublePredicatePartition partition(DoublePredicate predicate);


	int count();

	double reduce(double id, DoubleBinaryOperator reducer);

	OptionalDouble reduce(DoubleBinaryOperator reducer);


	double[] toArray();

	<K, V> Map<K, V> toMap(final DoubleFunction<K> keyMapper, final DoubleFunction<V> valueMapper);

	<K> Map<K, double[]> groupBy(final DoubleFunction<K> classifier);


	default Flow<Double> boxed()
	{
		return mapToObject(x -> x);
	}

	default <T> Flow<DoubleWith<T>> zipWith(final Iterable<T> other)
	{
		return zipWith(other.iterator());
	}

	default Flow<DoublePair> zipWith(final DoubleFlowIterable other)
	{
		return zipWith(other.iter());
	}

	default Flow<DoubleWithLong> zipWith(final LongFlowIterable other)
	{
		return zipWith(other.iter());
	}

	default Flow<IntWithDouble> zipWith(final IntFlowIterable other)
	{
		return zipWith(other.iter());
	}

	default DoubleFlow combineWith(final DoubleFlowIterable other, final DoubleBinaryOperator combiner)
	{
		return combineWith(other.iter(), combiner);
	}

	default DoubleFlow append(final DoubleFlowIterable other)
	{
		return append(other.iter());
	}

	default DoubleFlow insert(final DoubleFlowIterable other)
	{
		return insert(other.iter());
	}
}
