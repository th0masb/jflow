/**
 *
 */
package xawd.jflow.iterators;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;

import xawd.jflow.iterators.construction.Iterate;
import xawd.jflow.iterators.construction.Numbers;
import xawd.jflow.iterators.impl.AccumulationFlow;
import xawd.jflow.iterators.impl.AppendFlow;
import xawd.jflow.iterators.impl.CombinedFlow;
import xawd.jflow.iterators.impl.FilteredFlow;
import xawd.jflow.iterators.impl.InsertFlow;
import xawd.jflow.iterators.impl.MapFlow;
import xawd.jflow.iterators.impl.MapToDoubleFlow;
import xawd.jflow.iterators.impl.MapToIntFlow;
import xawd.jflow.iterators.impl.MapToObjectFlow;
import xawd.jflow.iterators.impl.SkipFlow;
import xawd.jflow.iterators.impl.SkipwhileFlow;
import xawd.jflow.iterators.impl.SlicedFlow;
import xawd.jflow.iterators.impl.TakeFlow;
import xawd.jflow.iterators.impl.TakewhileFlow;
import xawd.jflow.iterators.impl.ZipFlow;
import xawd.jflow.iterators.impl.consumption.LongCollectionConsumption;
import xawd.jflow.iterators.impl.consumption.LongMinMaxConsumption;
import xawd.jflow.iterators.impl.consumption.LongPredicateConsumption;
import xawd.jflow.iterators.impl.consumption.LongReductionConsumption;
import xawd.jflow.iterators.misc.DoubleWithLong;
import xawd.jflow.iterators.misc.IntWithLong;
import xawd.jflow.iterators.misc.LongPair;
import xawd.jflow.iterators.misc.LongPredicatePartition;
import xawd.jflow.iterators.misc.LongWith;

/**
 * @author t
 */
public abstract class AbstractLongFlow implements LongFlow
{
	@Override
	public AbstractLongFlow slice(final IntUnaryOperator indexMapping)
	{
		return new SlicedFlow.OfLong(this, indexMapping);
	}

	@Override
	public AbstractLongFlow map(final LongUnaryOperator f)
	{
		return new MapFlow.OfLong(this, f);
	}

	@Override
	public <E> AbstractFlow<E> mapToObject(final LongFunction<E> f)
	{
		return new MapToObjectFlow.FromLong<>(this, f);
	}

	@Override
	public AbstractDoubleFlow mapToDouble(final LongToDoubleFunction f)
	{
		return new MapToDoubleFlow.FromLong(this, f);
	}

	@Override
	public AbstractIntFlow mapToInt(final LongToIntFunction f)
	{
		return new MapToIntFlow.FromLong(this, f);
	}

	@Override
	public <E> AbstractFlow<LongWith<E>> zipWith(final Iterator<? extends E> other)
	{
		return new ZipFlow.OfObjectAndLong<>(other, this);
	}

	@Override
	public AbstractFlow<LongPair> zipWith(final OfLong other)
	{
		return new ZipFlow.OfLongPair(this, other);
	}

	@Override
	public AbstractFlow<DoubleWithLong> zipWith(final OfDouble other)
	{
		return new ZipFlow.OfDoubleWithLong(other, this);
	}

	@Override
	public AbstractFlow<IntWithLong> zipWith(final OfInt other)
	{
		return new ZipFlow.OfIntWithLong(other, this);
	}

	@Override
	public AbstractLongFlow combineWith(final OfLong other, final LongBinaryOperator combiner)
	{
		return new CombinedFlow.OfLongs(this, other, combiner);
	}

	@Override
	public AbstractFlow<IntWithLong> enumerate()
	{
		return zipWith(Numbers.natural());
	}

	@Override
	public AbstractLongFlow take(final int n)
	{
		return new TakeFlow.OfLong(this, n);
	}

	@Override
	public AbstractLongFlow takeWhile(final LongPredicate predicate)
	{
		return new TakewhileFlow.OfLong(this, predicate);
	}

	@Override
	public AbstractLongFlow skip(final int n)
	{
		return new SkipFlow.OfLong(this, n);
	}

	@Override
	public AbstractLongFlow skipWhile(final LongPredicate predicate)
	{
		return new SkipwhileFlow.OfLong(this, predicate);
	}

	@Override
	public AbstractLongFlow filter(final LongPredicate predicate)
	{
		return new FilteredFlow.OfLong(this, predicate);
	}

	@Override
	public AbstractLongFlow append(final OfLong other)
	{
		return new AppendFlow.OfLong(this, other);
	}

	@Override
	public AbstractLongFlow append(final long... xs)
	{
		return append(Iterate.over(xs));
	}

	@Override
	public AbstractLongFlow insert(final OfLong other)
	{
		return new InsertFlow.OfLong(this, other);
	}

	@Override
	public AbstractLongFlow insert(final long... xs)
	{
		return insert(Iterate.over(xs));
	}

	@Override
	public AbstractLongFlow accumulate(final LongBinaryOperator accumulator)
	{
		return new AccumulationFlow.OfLong(this, accumulator);
	}

	@Override
	public AbstractLongFlow accumulate(final long id, final LongBinaryOperator accumulator)
	{
		return new AccumulationFlow.OfLong(this, id, accumulator);
	}

	@Override
	public OptionalLong min()
	{
		return LongMinMaxConsumption.findMin(this);
	}

	@Override
	public long min(final long defaultValue)
	{
		return LongMinMaxConsumption.findMin(this, defaultValue);
	}

	@Override
	public long minByKey(final long defaultValue, final LongToDoubleFunction key)
	{
		return LongMinMaxConsumption.findMin(this, defaultValue, key);
	}

	@Override
	public OptionalLong minByKey(final LongToDoubleFunction key)
	{
		return LongMinMaxConsumption.findMin(this, key);
	}

	@Override
	public OptionalLong max()
	{
		return LongMinMaxConsumption.findMax(this);
	}

	@Override
	public long max(final long defaultValue)
	{
		return LongMinMaxConsumption.findMax(this, defaultValue);
	}

	@Override
	public long maxByKey(final long defaultValue, final LongToDoubleFunction key)
	{
		return LongMinMaxConsumption.findMax(this, defaultValue, key);
	}

	@Override
	public OptionalLong maxByKey(final LongToDoubleFunction key)
	{
		return LongMinMaxConsumption.findMax(this, key);
	}

	@Override
	public boolean areAllEqual()
	{
		return LongPredicateConsumption.allEqual(this);
	}

	@Override
	public boolean allMatch(final LongPredicate predicate)
	{
		return LongPredicateConsumption.allMatch(this, predicate);
	}

	@Override
	public boolean anyMatch(final LongPredicate predicate)
	{
		return LongPredicateConsumption.anyMatch(this, predicate);
	}

	@Override
	public boolean noneMatch(final LongPredicate predicate)
	{
		return LongPredicateConsumption.noneMatch(this, predicate);
	}

	@Override
	public LongPredicatePartition partition(final LongPredicate predicate)
	{
		return LongPredicateConsumption.partition(this, predicate);
	}

	@Override
	public int count()
	{
		return LongReductionConsumption.count(this);
	}

	@Override
	public long reduce(final long id, final LongBinaryOperator reducer)
	{
		return LongReductionConsumption.reduce(this, id, reducer);
	}

	@Override
	public OptionalLong reduce(final LongBinaryOperator reducer)
	{
		return LongReductionConsumption.reduce(this, reducer);
	}

	@Override
	public long[] toArray()
	{
		return LongCollectionConsumption.toArray(this);
	}

	@Override
	public <K, V> Map<K, V> toMap(final LongFunction<K> keyMapper, final LongFunction<V> valueMapper)
	{
		return LongCollectionConsumption.toMap(this, keyMapper, valueMapper);
	}

	@Override
	public <K> Map<K, long[]> groupBy(final LongFunction<K> classifier)
	{
		return LongCollectionConsumption.groupBy(this, classifier);
	}
}
