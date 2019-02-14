package maumay.jflow.iterators;

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

import maumay.jflow.iterators.factories.Iter;
import maumay.jflow.iterators.factories.Numbers;
import maumay.jflow.iterators.impl.AccumulationIterator;
import maumay.jflow.iterators.impl.AppendIterator;
import maumay.jflow.iterators.impl.DoubleCollectionConsumption;
import maumay.jflow.iterators.impl.DoubleMinMaxConsumption;
import maumay.jflow.iterators.impl.DoublePredicateConsumption;
import maumay.jflow.iterators.impl.DoubleReductionConsumption;
import maumay.jflow.iterators.impl.DropIterator;
import maumay.jflow.iterators.impl.DropwhileIterator;
import maumay.jflow.iterators.impl.FilteredIterator;
import maumay.jflow.iterators.impl.InsertIterator;
import maumay.jflow.iterators.impl.IntMapIterator;
import maumay.jflow.iterators.impl.LongMapIterator;
import maumay.jflow.iterators.impl.MapIterator;
import maumay.jflow.iterators.impl.ObjectMapIterator;
import maumay.jflow.iterators.impl.SlicedIterator;
import maumay.jflow.iterators.impl.TakeIterator;
import maumay.jflow.iterators.impl.TakewhileIterator;
import maumay.jflow.iterators.impl.ZipIterator;
import maumay.jflow.iterators.misc.DoublePair;
import maumay.jflow.iterators.misc.DoubleWith;

/**
 * A skeletal implementation of DoubleFlow, users writing custom DoubleFlows
 * should subclass this class.
 *
 * @author ThomasB
 * @since 23 Apr 2018
 */
public abstract class AbstractEnhancedDoubleIterator extends AbstractOptionallySized
		implements EnhancedDoubleIterator
{
	public AbstractEnhancedDoubleIterator(OptionalInt size)
	{
		super(size);
	}

	@Override
	public AbstractEnhancedDoubleIterator slice(IntUnaryOperator indexMapping)
	{
		return new SlicedIterator.OfDouble(this, indexMapping);
	}

	@Override
	public AbstractEnhancedDoubleIterator map(DoubleUnaryOperator f)
	{
		return new MapIterator.OfDouble(this, f);
	}

	@Override
	public <E> AbstractEnhancedIterator<E> mapToObject(DoubleFunction<? extends E> f)
	{
		return new ObjectMapIterator.FromDouble<>(this, f);
	}

	@Override
	public AbstractEnhancedLongIterator mapToLong(DoubleToLongFunction f)
	{
		return new LongMapIterator.FromDouble(this, f);
	}

	@Override
	public AbstractEnhancedIntIterator mapToInt(DoubleToIntFunction f)
	{
		return new IntMapIterator.FromDouble(this, f);
	}

	@Override
	public <E> AbstractEnhancedIterator<DoubleWith<E>> zipWith(
			Iterator<? extends E> other)
	{
		return new ZipIterator.OfObjectAndDouble<>(other, this);
	}

	@Override
	public AbstractEnhancedIterator<DoublePair> zipWith(OfDouble other)
	{
		return new ZipIterator.OfDoublePair(this, other);
	}

	@Override
	public AbstractEnhancedIterator<DoubleWith<Integer>> enumerate()
	{
		return zipWith(Numbers.natural());
	}

	@Override
	public AbstractEnhancedDoubleIterator take(int n)
	{
		return new TakeIterator.OfDouble(this, n);
	}

	@Override
	public AbstractEnhancedDoubleIterator takeWhile(DoublePredicate predicate)
	{
		return new TakewhileIterator.OfDouble(this, predicate);
	}

	@Override
	public AbstractEnhancedDoubleIterator drop(int n)
	{
		return new DropIterator.OfDouble(this, n);
	}

	@Override
	public AbstractEnhancedDoubleIterator dropWhile(DoublePredicate predicate)
	{
		return new DropwhileIterator.OfDouble(this, predicate);
	}

	@Override
	public AbstractEnhancedDoubleIterator filter(DoublePredicate predicate)
	{
		return new FilteredIterator.OfDouble(this, predicate);
	}

	@Override
	public AbstractEnhancedDoubleIterator append(OfDouble other)
	{
		return new AppendIterator.OfDouble(this, other);
	}

	@Override
	public AbstractEnhancedDoubleIterator append(double... xs)
	{
		return append(Iter.doubles(xs));
	}

	@Override
	public AbstractEnhancedDoubleIterator insert(OfDouble other)
	{
		return new InsertIterator.OfDouble(this, other);
	}

	@Override
	public AbstractEnhancedDoubleIterator insert(double... xs)
	{
		return insert(Iter.doubles(xs));
	}

	@Override
	public AbstractEnhancedDoubleIterator accumulate(DoubleBinaryOperator accumulator)
	{
		return new AccumulationIterator.OfDouble(this, accumulator);
	}

	@Override
	public AbstractEnhancedDoubleIterator accumulate(double id,
			DoubleBinaryOperator accumulator)
	{
		return new AccumulationIterator.OfDouble(this, id, accumulator);
	}

	@Override
	public OptionalDouble minOption()
	{
		return DoubleMinMaxConsumption.findMinOption(this);
	}

	@Override
	public double min()
	{
		return DoubleMinMaxConsumption.findMin(this);
	}

	@Override
	public OptionalDouble maxOption()
	{
		return DoubleMinMaxConsumption.findMaxOption(this);
	}

	@Override
	public double max()
	{
		return DoubleMinMaxConsumption.findMax(this);
	}

	@Override
	public boolean areAllEqual()
	{
		return DoublePredicateConsumption.allEqual(this);
	}

	@Override
	public boolean allMatch(DoublePredicate predicate)
	{
		return DoublePredicateConsumption.allMatch(this, predicate);
	}

	@Override
	public boolean anyMatch(DoublePredicate predicate)
	{
		return DoublePredicateConsumption.anyMatch(this, predicate);
	}

	@Override
	public boolean noneMatch(DoublePredicate predicate)
	{
		return DoublePredicateConsumption.noneMatch(this, predicate);
	}

	@Override
	public long count()
	{
		return DoubleReductionConsumption.count(this);
	}

	@Override
	public double fold(double id, DoubleBinaryOperator reducer)
	{
		return DoubleReductionConsumption.fold(this, id, reducer);
	}

	@Override
	public double fold(DoubleBinaryOperator reducer)
	{
		return DoubleReductionConsumption.fold(this, reducer);
	}

	@Override
	public OptionalDouble foldOption(DoubleBinaryOperator reducer)
	{
		return DoubleReductionConsumption.foldOption(this, reducer);
	}

	@Override
	public double[] toArray()
	{
		return DoubleCollectionConsumption.toArray(this);
	}

	@Override
	public <K, V> Map<K, V> toMap(DoubleFunction<K> keyMapper,
			DoubleFunction<V> valueMapper)
	{
		return DoubleCollectionConsumption.toMap(this, keyMapper, valueMapper);
	}

	@Override
	public <K> Map<K, double[]> groupBy(DoubleFunction<K> classifier)
	{
		return DoubleCollectionConsumption.groupBy(this, classifier);
	}
}