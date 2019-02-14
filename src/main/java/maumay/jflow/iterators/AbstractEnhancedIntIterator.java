package maumay.jflow.iterators;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import maumay.jflow.iterators.factories.Iter;
import maumay.jflow.iterators.factories.Numbers;
import maumay.jflow.iterators.impl.AccumulationIterator;
import maumay.jflow.iterators.impl.AppendIterator;
import maumay.jflow.iterators.impl.DoubleMapIterator;
import maumay.jflow.iterators.impl.DropIterator;
import maumay.jflow.iterators.impl.DropwhileIterator;
import maumay.jflow.iterators.impl.FilteredIterator;
import maumay.jflow.iterators.impl.InsertIterator;
import maumay.jflow.iterators.impl.IntCollectionConsumption;
import maumay.jflow.iterators.impl.IntMinMaxConsumption;
import maumay.jflow.iterators.impl.IntPredicateConsumption;
import maumay.jflow.iterators.impl.IntReductionConsumption;
import maumay.jflow.iterators.impl.LongMapIterator;
import maumay.jflow.iterators.impl.MapIterator;
import maumay.jflow.iterators.impl.ObjectMapIterator;
import maumay.jflow.iterators.impl.SlicedIterator;
import maumay.jflow.iterators.impl.TakeIterator;
import maumay.jflow.iterators.impl.TakewhileIterator;
import maumay.jflow.iterators.impl.ZipIterator;
import maumay.jflow.iterators.misc.IntPair;
import maumay.jflow.iterators.misc.IntWith;

/**
 * A skeletal implementation of a IntFlow, users writing custom IntFlows should
 * subclass this class.
 *
 * @author ThomasB
 * @since 23 Apr 2018
 */
public abstract class AbstractEnhancedIntIterator extends AbstractOptionallySized
		implements EnhancedIntIterator
{
	public AbstractEnhancedIntIterator(OptionalInt size)
	{
		super(size);
	}

	@Override
	public AbstractEnhancedIntIterator slice(IntUnaryOperator sliceMap)
	{
		return new SlicedIterator.OfInt(this, sliceMap);
	}

	@Override
	public AbstractEnhancedIntIterator map(IntUnaryOperator f)
	{
		return new MapIterator.OfInt(this, f);
	}

	@Override
	public <E> AbstractEnhancedIterator<E> mapToObject(IntFunction<? extends E> f)
	{
		return new ObjectMapIterator.FromInt<>(this, f);
	}

	@Override
	public AbstractEnhancedDoubleIterator mapToDouble(IntToDoubleFunction f)
	{
		return new DoubleMapIterator.FromInt(this, f);
	}

	@Override
	public AbstractEnhancedLongIterator mapToLong(IntToLongFunction f)
	{
		return new LongMapIterator.FromInt(this, f);
	}

	@Override
	public <E> AbstractEnhancedIterator<IntWith<E>> zipWith(Iterator<? extends E> other)
	{
		return new ZipIterator.OfObjectAndInt<>(other, this);
	}

	@Override
	public AbstractEnhancedIterator<IntPair> zipWith(OfInt other)
	{
		return new ZipIterator.OfIntPair(this, other);
	}

	@Override
	public AbstractEnhancedIterator<IntPair> enumerate()
	{
		return zipWith(Numbers.natural());
	}

	@Override
	public AbstractEnhancedIntIterator take(int n)
	{
		return new TakeIterator.OfInt(this, n);
	}

	@Override
	public AbstractEnhancedIntIterator takeWhile(IntPredicate predicate)
	{
		return new TakewhileIterator.OfInt(this, predicate);
	}

	@Override
	public AbstractEnhancedIntIterator drop(int n)
	{
		return new DropIterator.OfInt(this, n);
	}

	@Override
	public AbstractEnhancedIntIterator dropWhile(IntPredicate predicate)
	{
		return new DropwhileIterator.OfInt(this, predicate);
	}

	@Override
	public AbstractEnhancedIntIterator filter(IntPredicate predicate)
	{
		return new FilteredIterator.OfInt(this, predicate);
	}

	@Override
	public AbstractEnhancedIntIterator append(OfInt other)
	{
		return new AppendIterator.OfInt(this, other);
	}

	@Override
	public AbstractEnhancedIntIterator append(int... xs)
	{
		return append(Iter.ints(xs));
	}

	@Override
	public AbstractEnhancedIntIterator insert(OfInt other)
	{
		return new InsertIterator.OfInt(this, other);
	}

	@Override
	public AbstractEnhancedIntIterator insert(int... xs)
	{
		return insert(Iter.ints(xs));
	}

	@Override
	public AbstractEnhancedIntIterator scan(IntBinaryOperator accumulator)
	{
		return new AccumulationIterator.OfInt(this, accumulator);
	}

	@Override
	public AbstractEnhancedIntIterator scan(int id, IntBinaryOperator accumulator)
	{
		return new AccumulationIterator.OfInt(this, id, accumulator);
	}

	@Override
	public OptionalInt min()
	{
		return IntMinMaxConsumption.findMin(this);
	}

	@Override
	public int min(int defaultValue)
	{
		return IntMinMaxConsumption.findMin(this, defaultValue);
	}

	// @Override
	// public int minByKey(int defaultValue, IntToDoubleFunction key)
	// {
	// return IntMinMaxConsumption.findMin(this, defaultValue, key);
	// }
	//
	// @Override
	// public OptionalInt minByKey(IntToDoubleFunction key)
	// {
	// return IntMinMaxConsumption.findMin(this, key);
	// }

	@Override
	public <C extends Comparable<C>> OptionalInt minByKey(IntFunction<C> key)
	{
		return IntMinMaxConsumption.findMin(this, key);
	}

	@Override
	public OptionalInt max()
	{
		return IntMinMaxConsumption.findMax(this);
	}

	@Override
	public int max(int defaultValue)
	{
		return IntMinMaxConsumption.findMax(this, defaultValue);
	}

	// @Override
	// public int maxByKey(int defaultValue, IntToDoubleFunction key)
	// {
	// return IntMinMaxConsumption.findMax(this, defaultValue, key);
	// }
	//
	// @Override
	// public OptionalInt maxByKey(IntToDoubleFunction key)
	// {
	// return IntMinMaxConsumption.findMax(this, key);
	// }

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