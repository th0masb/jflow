package com.github.maumay.jflow.iterators.impl;

import java.util.Map;
import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import com.github.maumay.jflow.iterators.IntIterator;
import com.github.maumay.jflow.iterators.factories.Numbers;
import com.github.maumay.jflow.iterators.impl2.source.ArraySource;
import com.github.maumay.jflow.iterators.impl2.source.IteratorWrapper;
import com.github.maumay.jflow.iterators.implOld.IntCollectionConsumption;
import com.github.maumay.jflow.iterators.implOld.IntMinMaxConsumption;
import com.github.maumay.jflow.iterators.implOld.IntPredicateConsumption;
import com.github.maumay.jflow.iterators.implOld.IntReductionConsumption;
import com.github.maumay.jflow.utils.IntTup;

/**
 * A skeletal implementation of a IntFlow, users writing custom IntFlows should
 * subclass this class.
 *
 * @author ThomasB
 * @since 23 Apr 2018
 */
public abstract class AbstractIntIterator extends AbstractIterator implements IntIterator
{
	public AbstractIntIterator(AbstractIteratorSize size)
	{
		super(size);
	}

	@Override
	public final int nextInt()
	{
		if (hasOwnership()) {
			getSize().decrement();
			return nextIntImpl();
		} else {
			throw new RuntimeException();
		}
	}

	public abstract int nextIntImpl();

	// IntIterator API
	@Override
	public AbstractIntIterator slice(IntUnaryOperator sliceMap)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractIntIterator map(IntUnaryOperator f)
	{
		return new MapAdapter.OfInt(this, f);
	}

	@Override
	public <E> AbstractEnhancedIterator<E> mapToObject(IntFunction<? extends E> f)
	{
		return new MapToObjectAdapter.FromInt<>(this, f);
	}

	@Override
	public AbstractDoubleIterator mapToDouble(IntToDoubleFunction f)
	{
		return new MapToDoubleAdapter.FromInt(this, f);
	}

	@Override
	public AbstractLongIterator mapToLong(IntToLongFunction f)
	{
		return new MapToLongAdapter.FromInt(this, f);
	}

	@Override
	public AbstractEnhancedIterator<IntTup> zipWith(OfInt other)
	{
		return new ZipAdapter.OfInts(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractEnhancedIterator<IntTup> enumerate()
	{
		return zipWith(Numbers.natural());
	}

	@Override
	public AbstractIntIterator take(int n)
	{
		return new TakeAdapter.OfInt(this, n);
	}

	@Override
	public AbstractIntIterator takeWhile(IntPredicate predicate)
	{
		return new TakewhileAdapter.OfInt(this, predicate);
	}

	@Override
	public AbstractIntIterator skip(int n)
	{
		return new SkipAdapter.OfInt(this, n);
	}

	@Override
	public AbstractIntIterator skipWhile(IntPredicate predicate)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractIntIterator filter(IntPredicate predicate)
	{
		return new FilterAdapter.OfInt(this, predicate);
	}

	@Override
	public AbstractIntIterator append(OfInt other)
	{
		return new ConcatenationAdapter.OfInt(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractIntIterator append(int... xs)
	{
		return append(new ArraySource.OfInt(xs));
	}

	@Override
	public AbstractIntIterator insert(OfInt other)
	{
		return new ConcatenationAdapter.OfInt(IteratorWrapper.wrap(other), this);
	}

	@Override
	public AbstractIntIterator insert(int... xs)
	{
		return insert(new ArraySource.OfInt(xs));
	}

	@Override
	public AbstractIntIterator scan(IntBinaryOperator accumulator)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractIntIterator scan(int id, IntBinaryOperator accumulator)
	{
		throw new RuntimeException();
	}

	@Override
	public OptionalInt minOption()
	{
		return IntMinMaxConsumption.findMin(this);
	}

	@Override
	public int min(int defaultValue)
	{
		return IntMinMaxConsumption.findMin(this, defaultValue);
	}

	@Override
	public <C extends Comparable<C>> OptionalInt minByKey(IntFunction<C> key)
	{
		return IntMinMaxConsumption.findMin(this, key);
	}

	@Override
	public OptionalInt maxOption()
	{
		return IntMinMaxConsumption.findMax(this);
	}

	@Override
	public int max(int defaultValue)
	{
		return IntMinMaxConsumption.findMax(this, defaultValue);
	}

	@Override
	public <C extends Comparable<C>> OptionalInt maxByKey(IntFunction<C> key)
	{
		return IntMinMaxConsumption.findMax(this, key);
	}

	@Override
	public boolean areAllEqual()
	{
		return IntPredicateConsumption.allEqual(this);
	}

	@Override
	public boolean allMatch(IntPredicate predicate)
	{
		return IntPredicateConsumption.allMatch(this, predicate);
	}

	@Override
	public boolean anyMatch(IntPredicate predicate)
	{
		return IntPredicateConsumption.anyMatch(this, predicate);
	}

	@Override
	public boolean noneMatch(IntPredicate predicate)
	{
		return IntPredicateConsumption.noneMatch(this, predicate);
	}

	@Override
	public long count()
	{
		return IntReductionConsumption.count(this);
	}

	@Override
	public int fold(int id, IntBinaryOperator reducer)
	{
		return IntReductionConsumption.fold(this, id, reducer);
	}

	@Override
	public int fold(IntBinaryOperator reducer)
	{
		return IntReductionConsumption.fold(this, reducer);
	}

	@Override
	public OptionalInt foldOption(IntBinaryOperator reducer)
	{
		return IntReductionConsumption.foldOption(this, reducer);
	}

	@Override
	public int[] toArray()
	{
		return IntCollectionConsumption.toArray(this);
	}

	@Override
	public <K, V> Map<K, V> toMap(IntFunction<? extends K> keyMapper,
			IntFunction<? extends V> valueMapper)
	{
		return IntCollectionConsumption.toMap(this, keyMapper, valueMapper);
	}

	@Override
	public <K> Map<K, int[]> groupBy(IntFunction<? extends K> classifier)
	{
		return IntCollectionConsumption.groupBy(this, classifier);
	}
}