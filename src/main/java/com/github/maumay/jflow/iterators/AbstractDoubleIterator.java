package com.github.maumay.jflow.iterators;

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

import com.github.maumay.jflow.iterators.factories.Iter;
import com.github.maumay.jflow.iterators.factories.Numbers;
import com.github.maumay.jflow.iterators.impl.AccumulationIterator;
import com.github.maumay.jflow.iterators.impl.AppendIterator;
import com.github.maumay.jflow.iterators.impl.DoubleCollectionConsumption;
import com.github.maumay.jflow.iterators.impl.DoubleMinMaxConsumption;
import com.github.maumay.jflow.iterators.impl.DoublePredicateConsumption;
import com.github.maumay.jflow.iterators.impl.DoubleReductionConsumption;
import com.github.maumay.jflow.iterators.impl.SkipIterator;
import com.github.maumay.jflow.iterators.impl.SkipwhileIterator;
import com.github.maumay.jflow.iterators.impl.FilteredIterator;
import com.github.maumay.jflow.iterators.impl.InsertIterator;
import com.github.maumay.jflow.iterators.impl.IntMapIterator;
import com.github.maumay.jflow.iterators.impl.LongMapIterator;
import com.github.maumay.jflow.iterators.impl.MapIterator;
import com.github.maumay.jflow.iterators.impl.ObjectMapIterator;
import com.github.maumay.jflow.iterators.impl.SlicedIterator;
import com.github.maumay.jflow.iterators.impl.TakeIterator;
import com.github.maumay.jflow.iterators.impl.TakewhileIterator;
import com.github.maumay.jflow.iterators.impl.ZipIterator;
import com.github.maumay.jflow.iterators.impl2.AbstractEnhancedIterator;
import com.github.maumay.jflow.utils.DoubleTup;
import com.github.maumay.jflow.utils.DoubleWith;

/**
 * A skeletal implementation of DoubleFlow, users writing custom DoubleFlows
 * should subclass this class.
 *
 * @author ThomasB
 * @since 23 Apr 2018
 */
public abstract class AbstractDoubleIterator extends AbstractOptionallySized
		implements DoubleIterator
{
	public AbstractDoubleIterator(OptionalInt size)
	{
		super(size);
	}

	@Override
	public AbstractDoubleIterator slice(IntUnaryOperator indexMapping)
	{
		return new SlicedIterator.OfDouble(this, indexMapping);
	}

	@Override
	public AbstractDoubleIterator map(DoubleUnaryOperator f)
	{
		return new MapIterator.OfDouble(this, f);
	}

	@Override
	public <E> AbstractEnhancedIterator<E> mapToObject(DoubleFunction<? extends E> f)
	{
		return new ObjectMapIterator.FromDouble<>(this, f);
	}

	@Override
	public AbstractLongIterator mapToLong(DoubleToLongFunction f)
	{
		return new LongMapIterator.FromDouble(this, f);
	}

	@Override
	public AbstractIntIterator mapToInt(DoubleToIntFunction f)
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
	public AbstractEnhancedIterator<DoubleTup> zipWith(OfDouble other)
	{
		return new ZipIterator.OfDoublePair(this, other);
	}

	@Override
	public AbstractEnhancedIterator<DoubleWith<Integer>> enumerate()
	{
		return zipWith(Numbers.natural());
	}

	@Override
	public AbstractDoubleIterator take(int n)
	{
		return new TakeIterator.OfDouble(this, n);
	}

	@Override
	public AbstractDoubleIterator takeWhile(DoublePredicate predicate)
	{
		return new TakewhileIterator.OfDouble(this, predicate);
	}

	@Override
	public AbstractDoubleIterator skip(int n)
	{
		return new SkipIterator.OfDouble(this, n);
	}

	@Override
	public AbstractDoubleIterator skipWhile(DoublePredicate predicate)
	{
		return new SkipwhileIterator.OfDouble(this, predicate);
	}

	@Override
	public AbstractDoubleIterator filter(DoublePredicate predicate)
	{
		return new FilteredIterator.OfDouble(this, predicate);
	}

	@Override
	public AbstractDoubleIterator append(OfDouble other)
	{
		return new AppendIterator.OfDouble(this, other);
	}

	@Override
	public AbstractDoubleIterator append(double... xs)
	{
		return append(Iter.doubles(xs));
	}

	@Override
	public AbstractDoubleIterator insert(OfDouble other)
	{
		return new InsertIterator.OfDouble(this, other);
	}

	@Override
	public AbstractDoubleIterator insert(double... xs)
	{
		return insert(Iter.doubles(xs));
	}

	@Override
	public AbstractDoubleIterator accumulate(DoubleBinaryOperator accumulator)
	{
		return new AccumulationIterator.OfDouble(this, accumulator);
	}

	@Override
	public AbstractDoubleIterator accumulate(double id,
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