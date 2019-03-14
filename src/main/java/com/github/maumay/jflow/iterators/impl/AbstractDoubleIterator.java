package com.github.maumay.jflow.iterators.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntUnaryOperator;

import com.github.maumay.jflow.iterators.DoubleIterator;
import com.github.maumay.jflow.iterators.factories.Iter;
import com.github.maumay.jflow.iterators.factories.Numbers;
import com.github.maumay.jflow.iterators.implOld.AccumulationIterator;
import com.github.maumay.jflow.iterators.implOld.AppendIterator;
import com.github.maumay.jflow.iterators.implOld.DoubleCollectionConsumption;
import com.github.maumay.jflow.iterators.implOld.DoubleMinMaxConsumption;
import com.github.maumay.jflow.iterators.implOld.DoublePredicateConsumption;
import com.github.maumay.jflow.iterators.implOld.DoubleReductionConsumption;
import com.github.maumay.jflow.iterators.implOld.FilteredIterator;
import com.github.maumay.jflow.iterators.implOld.InsertIterator;
import com.github.maumay.jflow.iterators.implOld.IntMapIterator;
import com.github.maumay.jflow.iterators.implOld.LongMapIterator;
import com.github.maumay.jflow.iterators.implOld.ObjectMapIterator;
import com.github.maumay.jflow.iterators.implOld.SkipIterator;
import com.github.maumay.jflow.iterators.implOld.SkipwhileIterator;
import com.github.maumay.jflow.iterators.implOld.SlicedIterator;
import com.github.maumay.jflow.iterators.implOld.TakeIterator;
import com.github.maumay.jflow.iterators.implOld.TakewhileIterator;
import com.github.maumay.jflow.iterators.implOld.ZipIterator;
import com.github.maumay.jflow.utils.DoubleTup;
import com.github.maumay.jflow.utils.DoubleWith;

/**
 * A skeletal implementation of DoubleFlow, users writing custom DoubleFlows
 * should subclass this class.
 *
 * @author ThomasB
 * @since 23 Apr 2018
 */
public abstract class AbstractDoubleIterator extends AbstractIterator implements DoubleIterator
{
	public AbstractDoubleIterator(AbstractIteratorSize size)
	{
		super(size);
	}

	@Override
	public final double nextDouble()
	{
		if (hasOwnership()) {
			getSize().decrement();
			return nextDoubleImpl();
		} else {
			throw new RuntimeException();
		}
	}

	public abstract double nextDoubleImpl();

	// DoubleIterator API
	@Override
	public AbstractDoubleIterator slice(IntUnaryOperator indexMapping)
	{
		return new SlicedIterator.OfDouble(this, indexMapping);
	}

	@Override
	public AbstractDoubleIterator map(DoubleUnaryOperator f)
	{
		return new MapAdapter.OfDouble(this, f);
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
	public <E> AbstractEnhancedIterator<DoubleWith<E>> zipWith(Iterator<? extends E> other)
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
	public AbstractDoubleIterator accumulate(double id, DoubleBinaryOperator accumulator)
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
	public <K, V> Map<K, V> toMap(DoubleFunction<K> keyMapper, DoubleFunction<V> valueMapper)
	{
		return DoubleCollectionConsumption.toMap(this, keyMapper, valueMapper);
	}

	@Override
	public <K> Map<K, double[]> groupBy(DoubleFunction<K> classifier)
	{
		return DoubleCollectionConsumption.groupBy(this, classifier);
	}
}