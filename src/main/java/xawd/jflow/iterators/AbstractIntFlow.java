package xawd.jflow.iterators;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import xawd.jflow.iterators.factories.Iterate;
import xawd.jflow.iterators.factories.Numbers;
import xawd.jflow.iterators.impl.AccumulationFlow;
import xawd.jflow.iterators.impl.AppendFlow;
import xawd.jflow.iterators.impl.CombinedFlow;
import xawd.jflow.iterators.impl.DropFlow;
import xawd.jflow.iterators.impl.DropwhileFlow;
import xawd.jflow.iterators.impl.FilteredFlow;
import xawd.jflow.iterators.impl.InsertFlow;
import xawd.jflow.iterators.impl.IntCollectionConsumption;
import xawd.jflow.iterators.impl.IntMinMaxConsumption;
import xawd.jflow.iterators.impl.IntPredicateConsumption;
import xawd.jflow.iterators.impl.IntReductionConsumption;
import xawd.jflow.iterators.impl.MapFlow;
import xawd.jflow.iterators.impl.MapToDoubleFlow;
import xawd.jflow.iterators.impl.MapToLongFlow;
import xawd.jflow.iterators.impl.MapToObjectFlow;
import xawd.jflow.iterators.impl.SlicedFlow;
import xawd.jflow.iterators.impl.TakeFlow;
import xawd.jflow.iterators.impl.TakewhileFlow;
import xawd.jflow.iterators.impl.ZipFlow;
import xawd.jflow.iterators.misc.IntPair;
import xawd.jflow.iterators.misc.IntPredicatePartition;
import xawd.jflow.iterators.misc.IntWith;
import xawd.jflow.iterators.misc.IntWithDouble;
import xawd.jflow.iterators.misc.IntWithLong;

/**
 * A skeletal implementation of a IntFlow, users writing custom IntFlows should
 * subclass this class.
 *
 * @author ThomasB
 * @since 23 Apr 2018
 */
public abstract class AbstractIntFlow extends AbstractOptionallySized implements IntFlow
{
	public AbstractIntFlow(OptionalInt size)
	{
		super(size);
	}

	@Override
	public AbstractIntFlow slice(final IntUnaryOperator sliceMap)
	{
		return new SlicedFlow.OfInt(this, sliceMap);
	}

	@Override
	public AbstractIntFlow map(final IntUnaryOperator f)
	{
		return new MapFlow.OfInt(this, f);
	}

	@Override
	public <E> AbstractFlow<E> mapToObject(final IntFunction<E> f)
	{
		return new MapToObjectFlow.FromInt<>(this, f);
	}

	@Override
	public AbstractDoubleFlow mapToDouble(final IntToDoubleFunction f)
	{
		return new MapToDoubleFlow.FromInt(this, f);
	}

	@Override
	public AbstractLongFlow mapToLong(final IntToLongFunction f)
	{
		return new MapToLongFlow.FromInt(this, f);
	}

	@Override
	public <E> AbstractFlow<IntWith<E>> zipWith(final Iterator<? extends E> other)
	{
		return new ZipFlow.OfObjectAndInt<>(other, this);
	}

	@Override
	public AbstractFlow<IntPair> zipWith(final OfInt other)
	{
		return new ZipFlow.OfIntPair(this, other);
	}

	@Override
	public AbstractFlow<IntWithDouble> zipWith(final OfDouble other)
	{
		return new ZipFlow.OfIntWithDouble(this, other);
	}

	@Override
	public AbstractFlow<IntWithLong> zipWith(final OfLong other)
	{
		return new ZipFlow.OfIntWithLong(this, other);
	}

	@Override
	public AbstractIntFlow combineWith(final PrimitiveIterator.OfInt other, final IntBinaryOperator combiner)
	{
		return new CombinedFlow.OfInts(this, other, combiner);
	}

	@Override
	public AbstractFlow<IntPair> enumerate()
	{
		return zipWith(Numbers.natural());
	}

	@Override
	public AbstractIntFlow take(final int n)
	{
		return new TakeFlow.OfInt(this, n);
	}

	@Override
	public AbstractIntFlow takeWhile(final IntPredicate predicate)
	{
		return new TakewhileFlow.OfInt(this, predicate);
	}

	@Override
	public AbstractIntFlow drop(final int n)
	{
		return new DropFlow.OfInt(this, n);
	}

	@Override
	public AbstractIntFlow dropWhile(final IntPredicate predicate)
	{
		return new DropwhileFlow.OfInt(this, predicate);
	}

	@Override
	public AbstractIntFlow filter(final IntPredicate predicate)
	{
		return new FilteredFlow.OfInt(this, predicate);
	}

	@Override
	public AbstractIntFlow append(final OfInt other)
	{
		return new AppendFlow.OfInt(this, other);
	}

	@Override
	public AbstractIntFlow append(final int... xs)
	{
		return append(Iterate.overInts(xs));
	}

	@Override
	public AbstractIntFlow insert(final OfInt other)
	{
		return new InsertFlow.OfInt(this, other);
	}

	@Override
	public AbstractIntFlow insert(final int... xs)
	{
		return insert(Iterate.overInts(xs));
	}

	@Override
	public AbstractIntFlow accumulate(final IntBinaryOperator accumulator)
	{
		return new AccumulationFlow.OfInt(this, accumulator);
	}

	@Override
	public AbstractIntFlow accumulate(final int id, final IntBinaryOperator accumulator)
	{
		return new AccumulationFlow.OfInt(this, id, accumulator);
	}

	@Override
	public OptionalInt min()
	{
		return IntMinMaxConsumption.findMin(this);
	}

	@Override
	public int min(final int defaultValue)
	{
		return IntMinMaxConsumption.findMin(this, defaultValue);
	}

	// @Override
	// public int minByKey(final int defaultValue, final IntToDoubleFunction key)
	// {
	// return IntMinMaxConsumption.findMin(this, defaultValue, key);
	// }
	//
	// @Override
	// public OptionalInt minByKey(final IntToDoubleFunction key)
	// {
	// return IntMinMaxConsumption.findMin(this, key);
	// }

	@Override
	public <C extends Comparable<C>> OptionalInt minByKey(final IntFunction<C> key)
	{
		return IntMinMaxConsumption.findMin(this, key);
	}

	@Override
	public OptionalInt max()
	{
		return IntMinMaxConsumption.findMax(this);
	}

	@Override
	public int max(final int defaultValue)
	{
		return IntMinMaxConsumption.findMax(this, defaultValue);
	}

	// @Override
	// public int maxByKey(final int defaultValue, final IntToDoubleFunction key)
	// {
	// return IntMinMaxConsumption.findMax(this, defaultValue, key);
	// }
	//
	// @Override
	// public OptionalInt maxByKey(final IntToDoubleFunction key)
	// {
	// return IntMinMaxConsumption.findMax(this, key);
	// }

	@Override
	public <C extends Comparable<C>> OptionalInt maxByKey(final IntFunction<C> key)
	{
		return IntMinMaxConsumption.findMax(this, key);
	}

	@Override
	public boolean areAllEqual()
	{
		return IntPredicateConsumption.allEqual(this);
	}

	@Override
	public boolean allMatch(final IntPredicate predicate)
	{
		return IntPredicateConsumption.allMatch(this, predicate);
	}

	@Override
	public boolean anyMatch(final IntPredicate predicate)
	{
		return IntPredicateConsumption.anyMatch(this, predicate);
	}

	@Override
	public boolean noneMatch(final IntPredicate predicate)
	{
		return IntPredicateConsumption.noneMatch(this, predicate);
	}

	@Override
	public IntPredicatePartition partition(final IntPredicate predicate)
	{
		return IntPredicateConsumption.partition(this, predicate);
	}

	@Override
	public long count()
	{
		return IntReductionConsumption.count(this);
	}

	@Override
	public int fold(final int id, final IntBinaryOperator reducer)
	{
		return IntReductionConsumption.reduce(this, id, reducer);
	}

	@Override
	public OptionalInt fold(final IntBinaryOperator reducer)
	{
		return IntReductionConsumption.reduce(this, reducer);
	}

	@Override
	public int[] toArray()
	{
		return IntCollectionConsumption.toArray(this);
	}

	@Override
	public <K, V> Map<K, V> toMap(final IntFunction<K> keyMapper, final IntFunction<V> valueMapper)
	{
		return IntCollectionConsumption.toMap(this, keyMapper, valueMapper);
	}

	@Override
	public <K> Map<K, int[]> groupBy(final IntFunction<K> classifier)
	{
		return IntCollectionConsumption.groupBy(this, classifier);
	}
}