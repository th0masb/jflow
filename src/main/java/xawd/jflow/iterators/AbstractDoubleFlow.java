package xawd.jflow.iterators;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntUnaryOperator;

import xawd.jflow.iterators.factories.Iterate;
import xawd.jflow.iterators.factories.Numbers;
import xawd.jflow.iterators.impl.AccumulationFlow;
import xawd.jflow.iterators.impl.AppendFlow;
import xawd.jflow.iterators.impl.CombinedFlow;
import xawd.jflow.iterators.impl.DoubleCollectionConsumption;
import xawd.jflow.iterators.impl.DoubleMinMaxConsumption;
import xawd.jflow.iterators.impl.DoublePredicateConsumption;
import xawd.jflow.iterators.impl.DoubleReductionConsumption;
import xawd.jflow.iterators.impl.DropFlow;
import xawd.jflow.iterators.impl.DropwhileFlow;
import xawd.jflow.iterators.impl.FilteredFlow;
import xawd.jflow.iterators.impl.InsertFlow;
import xawd.jflow.iterators.impl.MapFlow;
import xawd.jflow.iterators.impl.MapToIntFlow;
import xawd.jflow.iterators.impl.MapToLongFlow;
import xawd.jflow.iterators.impl.MapToObjectFlow;
import xawd.jflow.iterators.impl.SlicedFlow;
import xawd.jflow.iterators.impl.TakeFlow;
import xawd.jflow.iterators.impl.TakewhileFlow;
import xawd.jflow.iterators.impl.ZipFlow;
import xawd.jflow.iterators.misc.DoublePair;
import xawd.jflow.iterators.misc.DoublePredicatePartition;
import xawd.jflow.iterators.misc.DoubleWith;
import xawd.jflow.iterators.misc.DoubleWithLong;
import xawd.jflow.iterators.misc.IntWithDouble;

/**
 * A skeletal implementation of DoubleFlow, users writing custom DoubleFlows
 * should subclass this class.
 *
 * @author ThomasB
 * @since 23 Apr 2018
 */
public abstract class AbstractDoubleFlow extends AbstractOptionallySized implements DoubleFlow
{
	public AbstractDoubleFlow(OptionalInt size)
	{
		super(size);
	}

	@Override
	public AbstractDoubleFlow slice(final IntUnaryOperator indexMapping)
	{
		return new SlicedFlow.OfDouble(this, indexMapping);
	}

	@Override
	public AbstractDoubleFlow map(final DoubleUnaryOperator f)
	{
		return new MapFlow.OfDouble(this, f);
	}

	@Override
	public <E> AbstractFlow<E> mapToObject(final DoubleFunction<E> f)
	{
		return new MapToObjectFlow.FromDouble<>(this, f);
	}

	@Override
	public AbstractLongFlow mapToLong(final DoubleToLongFunction f)
	{
		return new MapToLongFlow.FromDouble(this, f);
	}

	@Override
	public AbstractIntFlow mapToInt(final DoubleToIntFunction f)
	{
		return new MapToIntFlow.FromDouble(this, f);
	}

	@Override
	public <E> AbstractFlow<DoubleWith<E>> zipWith(final Iterator<? extends E> other)
	{
		return new ZipFlow.OfObjectAndDouble<>(other, this);
	}

	@Override
	public AbstractFlow<DoublePair> zipWith(final OfDouble other)
	{
		return new ZipFlow.OfDoublePair(this, other);
	}

	@Override
	public AbstractFlow<DoubleWithLong> zipWith(final OfLong other)
	{
		return new ZipFlow.OfDoubleWithLong(this, other);
	}

	@Override
	public AbstractFlow<IntWithDouble> zipWith(final OfInt other)
	{
		return new ZipFlow.OfIntWithDouble(other, this);
	}

	@Override
	public AbstractDoubleFlow combineWith(final OfDouble other, final DoubleBinaryOperator combiner)
	{
		return new CombinedFlow.OfDoubles(this, other, combiner);
	}

	@Override
	public AbstractFlow<IntWithDouble> enumerate()
	{
		return zipWith(Numbers.natural());
	}

	@Override
	public AbstractDoubleFlow take(final int n)
	{
		return new TakeFlow.OfDouble(this, n);
	}

	@Override
	public AbstractDoubleFlow takeWhile(final DoublePredicate predicate)
	{
		return new TakewhileFlow.OfDouble(this, predicate);
	}

	@Override
	public AbstractDoubleFlow drop(final int n)
	{
		return new DropFlow.OfDouble(this, n);
	}

	@Override
	public AbstractDoubleFlow dropWhile(final DoublePredicate predicate)
	{
		return new DropwhileFlow.OfDouble(this, predicate);
	}

	@Override
	public AbstractDoubleFlow filter(final DoublePredicate predicate)
	{
		return new FilteredFlow.OfDouble(this, predicate);
	}

	@Override
	public AbstractDoubleFlow append(final OfDouble other)
	{
		return new AppendFlow.OfDouble(this, other);
	}

	@Override
	public AbstractDoubleFlow append(final double... xs)
	{
		return append(Iterate.overDoubles(xs));
	}

	@Override
	public AbstractDoubleFlow insert(final OfDouble other)
	{
		return new InsertFlow.OfDouble(this, other);
	}

	@Override
	public AbstractDoubleFlow insert(final double... xs)
	{
		return insert(Iterate.overDoubles(xs));
	}

	@Override
	public AbstractDoubleFlow accumulate(final DoubleBinaryOperator accumulator)
	{
		return new AccumulationFlow.OfDouble(this, accumulator);
	}

	@Override
	public AbstractDoubleFlow accumulate(final double id, final DoubleBinaryOperator accumulator)
	{
		return new AccumulationFlow.OfDouble(this, id, accumulator);
	}

	@Override
	public OptionalDouble min()
	{
		return DoubleMinMaxConsumption.findMin(this);
	}

	@Override
	public double min(final double defaultValue)
	{
		return DoubleMinMaxConsumption.findMin(this, defaultValue);
	}

	@Override
	public OptionalDouble max()
	{
		return DoubleMinMaxConsumption.findMax(this);
	}

	@Override
	public double max(final double defaultValue)
	{
		return DoubleMinMaxConsumption.findMax(this, defaultValue);
	}

	@Override
	public boolean areAllEqual()
	{
		return DoublePredicateConsumption.allEqual(this);
	}

	@Override
	public boolean allMatch(final DoublePredicate predicate)
	{
		return DoublePredicateConsumption.allMatch(this, predicate);
	}

	@Override
	public boolean anyMatch(final DoublePredicate predicate)
	{
		return DoublePredicateConsumption.anyMatch(this, predicate);
	}

	@Override
	public boolean noneMatch(final DoublePredicate predicate)
	{
		return DoublePredicateConsumption.noneMatch(this, predicate);
	}

	@Override
	public DoublePredicatePartition partition(final DoublePredicate predicate)
	{
		return DoublePredicateConsumption.partition(this, predicate);
	}

	@Override
	public long count()
	{
		return DoubleReductionConsumption.count(this);
	}

	@Override
	public double fold(final double id, final DoubleBinaryOperator reducer)
	{
		return DoubleReductionConsumption.reduce(this, id, reducer);
	}

	@Override
	public OptionalDouble reduce(final DoubleBinaryOperator reducer)
	{
		return DoubleReductionConsumption.reduce(this, reducer);
	}

	@Override
	public double[] toArray()
	{
		return DoubleCollectionConsumption.toArray(this);
	}

	@Override
	public <K, V> Map<K, V> toMap(final DoubleFunction<K> keyMapper, final DoubleFunction<V> valueMapper)
	{
		return DoubleCollectionConsumption.toMap(this, keyMapper, valueMapper);
	}

	@Override
	public <K> Map<K, double[]> groupBy(final DoubleFunction<K> classifier)
	{
		return DoubleCollectionConsumption.groupBy(this, classifier);
	}
}