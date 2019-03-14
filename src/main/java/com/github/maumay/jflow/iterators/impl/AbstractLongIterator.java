/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.Map;
import java.util.OptionalLong;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;

import com.github.maumay.jflow.iterators.LongIterator;
import com.github.maumay.jflow.iterators.impl2.source.ArraySource;
import com.github.maumay.jflow.iterators.impl2.source.IteratorWrapper;
import com.github.maumay.jflow.iterators.implOld.LongCollectionConsumption;
import com.github.maumay.jflow.iterators.implOld.LongMinMaxConsumption;
import com.github.maumay.jflow.iterators.implOld.LongPredicateConsumption;
import com.github.maumay.jflow.iterators.implOld.LongReductionConsumption;
import com.github.maumay.jflow.utils.Exceptions;
import com.github.maumay.jflow.utils.LongTup;

/**
 * A skeletal implementation of a LongFlow, users writing custom LongFlows
 * should subclass this class.
 *
 * @author ThomasB
 */
public abstract class AbstractLongIterator extends AbstractIterator implements LongIterator
{
	public AbstractLongIterator(AbstractIteratorSize size)
	{
		super(size);
	}

	@Override
	public final long nextLong()
	{
		if (hasOwnership()) {
			getSize().decrement();
			return nextLongImpl();
		} else {
			throw new RuntimeException();
		}
	}

	public abstract long nextLongImpl();

	// LongIterator API
	@Override
	public AbstractLongIterator slice(IntUnaryOperator indexMapping)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractLongIterator map(LongUnaryOperator f)
	{
		return new MapAdapter.OfLong(this, f);
	}

	@Override
	public <E> AbstractEnhancedIterator<E> mapToObject(LongFunction<? extends E> f)
	{
		return new MapToObjectAdapter.FromLong<>(this, f);
	}

	@Override
	public AbstractDoubleIterator mapToDouble(LongToDoubleFunction f)
	{
		return new MapToDoubleAdapter.FromLong(this, f);
	}

	@Override
	public AbstractIntIterator mapToInt(LongToIntFunction f)
	{
		return new MapToIntAdapter.FromLong(this, f);
	}

	@Override
	public AbstractEnhancedIterator<LongTup> zipWith(OfLong other)
	{
		return new ZipAdapter.OfLongs(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractEnhancedIterator<LongTup> enumerate()
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractLongIterator take(int n)
	{
		Exceptions.require(n >= 0);
		return new TakeAdapter.OfLong(this, n);
	}

	@Override
	public AbstractLongIterator takeWhile(LongPredicate predicate)
	{
		return new TakewhileAdapter.OfLong(this, predicate);
	}

	@Override
	public AbstractLongIterator skip(int n)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractLongIterator skipWhile(LongPredicate predicate)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractLongIterator filter(LongPredicate predicate)
	{
		return new FilterAdapter.OfLong(this, predicate);
	}

	@Override
	public AbstractLongIterator append(OfLong other)
	{
		return new ConcatenationAdapter.OfLong(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractLongIterator append(long... xs)
	{
		return append(new ArraySource.OfLong(xs));
	}

	@Override
	public AbstractLongIterator insert(OfLong other)
	{
		return new ConcatenationAdapter.OfLong(IteratorWrapper.wrap(other), this);
	}

	@Override
	public AbstractLongIterator insert(long... xs)
	{
		return insert(new ArraySource.OfLong(xs));
	}

	@Override
	public AbstractLongIterator scan(LongBinaryOperator accumulator)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractLongIterator scan(long id, LongBinaryOperator accumulator)
	{
		throw new RuntimeException();
	}

	@Override
	public OptionalLong minOption()
	{
		throw new RuntimeException();
	}

	@Override
	public long min(long defaultValue)
	{
		return LongMinMaxConsumption.findMin(this, defaultValue);
	}

	@Override
	public OptionalLong maxOption()
	{
		return LongMinMaxConsumption.findMax(this);
	}

	@Override
	public long max(long defaultValue)
	{
		return LongMinMaxConsumption.findMax(this, defaultValue);
	}

	@Override
	public boolean areAllEqual()
	{
		return LongPredicateConsumption.allEqual(this);
	}

	@Override
	public boolean allMatch(LongPredicate predicate)
	{
		return LongPredicateConsumption.allMatch(this, predicate);
	}

	@Override
	public boolean anyMatch(LongPredicate predicate)
	{
		return LongPredicateConsumption.anyMatch(this, predicate);
	}

	@Override
	public boolean noneMatch(LongPredicate predicate)
	{
		return LongPredicateConsumption.noneMatch(this, predicate);
	}

	@Override
	public long count()
	{
		return LongReductionConsumption.count(this);
	}

	@Override
	public long fold(long id, LongBinaryOperator reducer)
	{
		return LongReductionConsumption.fold(this, id, reducer);
	}

	@Override
	public long fold(LongBinaryOperator reducer)
	{
		return LongReductionConsumption.fold(this, reducer);
	}

	@Override
	public OptionalLong foldOption(LongBinaryOperator reducer)
	{
		return LongReductionConsumption.foldOption(this, reducer);
	}

	@Override
	public long[] toArray()
	{
		return LongCollectionConsumption.toArray(this);
	}

	@Override
	public <K, V> Map<K, V> toMap(LongFunction<K> keyMapper, LongFunction<V> valueMapper)
	{
		return LongCollectionConsumption.toMap(this, keyMapper, valueMapper);
	}

	@Override
	public <K> Map<K, long[]> groupBy(LongFunction<K> classifier)
	{
		return LongCollectionConsumption.groupBy(this, classifier);
	}
}
