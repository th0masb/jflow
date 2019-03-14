package com.github.maumay.jflow.iterators.impl;

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
import com.github.maumay.jflow.iterators.impl2.source.ArraySource;
import com.github.maumay.jflow.iterators.impl2.source.IteratorWrapper;
import com.github.maumay.jflow.iterators.implOld.DoubleCollectionConsumption;
import com.github.maumay.jflow.iterators.implOld.DoubleMinMaxConsumption;
import com.github.maumay.jflow.iterators.implOld.DoublePredicateConsumption;
import com.github.maumay.jflow.iterators.implOld.DoubleReductionConsumption;
import com.github.maumay.jflow.utils.DoubleTup;

/**
 * A skeletal implementation of DoubleFlow, users writing custom DoubleFlows
 * should subclass this class.
 *
 * @author ThomasB
 * @since 23 Apr 2018
 */
public abstract class AbstractDoubleIterator extends AbstractIterator
		implements DoubleIterator
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
		return new SliceAdapter.OfDouble(this, indexMapping);
	}

	@Override
	public AbstractDoubleIterator map(DoubleUnaryOperator f)
	{
		return new MapAdapter.OfDouble(this, f);
	}

	@Override
	public <E> AbstractEnhancedIterator<E> mapToObject(DoubleFunction<? extends E> f)
	{
		return new MapToObjectAdapter.FromDouble<>(this, f);
	}

	@Override
	public AbstractLongIterator mapToLong(DoubleToLongFunction f)
	{
		return new MapToLongAdapter.FromDouble(this, f);
	}

	@Override
	public AbstractIntIterator mapToInt(DoubleToIntFunction f)
	{
		return new MapToIntAdapter.FromDouble(this, f);
	}

	@Override
	public AbstractEnhancedIterator<DoubleTup> zipWith(OfDouble other)
	{
		return new ZipAdapter.OfDoubles(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractEnhancedIterator<DoubleTup> enumerate()
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractDoubleIterator take(int n)
	{
		return new TakeAdapter.OfDouble(this, n);
	}

	@Override
	public AbstractDoubleIterator takeWhile(DoublePredicate predicate)
	{
		return new TakewhileAdapter.OfDouble(this, predicate);
	}

	@Override
	public AbstractDoubleIterator skip(int n)
	{
		return new SkipAdapter.OfDouble(this, n);
	}

	@Override
	public AbstractDoubleIterator skipWhile(DoublePredicate predicate)
	{
		return new SkipwhileAdapter.OfDouble(this, predicate);
	}

	@Override
	public AbstractDoubleIterator filter(DoublePredicate predicate)
	{
		return new FilterAdapter.OfDouble(this, predicate);
	}

	@Override
	public AbstractDoubleIterator append(OfDouble other)
	{
		return new ConcatenationAdapter.OfDouble(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractDoubleIterator append(double... xs)
	{
		return append(new ArraySource.OfDouble(xs));
	}

	@Override
	public AbstractDoubleIterator insert(OfDouble other)
	{
		return new ConcatenationAdapter.OfDouble(IteratorWrapper.wrap(other), this);
	}

	@Override
	public AbstractDoubleIterator insert(double... xs)
	{
		return insert(new ArraySource.OfDouble(xs));
	}

	@Override
	public AbstractDoubleIterator accumulate(DoubleBinaryOperator accumulator)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractDoubleIterator accumulate(double id, DoubleBinaryOperator accumulator)
	{
		throw new RuntimeException();
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