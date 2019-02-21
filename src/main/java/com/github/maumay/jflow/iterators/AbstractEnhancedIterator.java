/**
 *
 */
package com.github.maumay.jflow.iterators;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.PrimitiveIterator.OfDouble;
import java.util.PrimitiveIterator.OfInt;
import java.util.PrimitiveIterator.OfLong;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import com.gihub.maumay.jflow.iterators.misc.DoubleWith;
import com.gihub.maumay.jflow.iterators.misc.IntWith;
import com.gihub.maumay.jflow.iterators.misc.LongWith;
import com.gihub.maumay.jflow.iterators.misc.Pair;
import com.github.maumay.jflow.iterators.factories.Numbers;
import com.github.maumay.jflow.iterators.impl.AccumulationIterator;
import com.github.maumay.jflow.iterators.impl.AppendIterator;
import com.github.maumay.jflow.iterators.impl.DoubleMapIterator;
import com.github.maumay.jflow.iterators.impl.DropIterator;
import com.github.maumay.jflow.iterators.impl.DropwhileIterator;
import com.github.maumay.jflow.iterators.impl.FilteredIterator;
import com.github.maumay.jflow.iterators.impl.FlattenedIterator;
import com.github.maumay.jflow.iterators.impl.InsertIterator;
import com.github.maumay.jflow.iterators.impl.IntMapIterator;
import com.github.maumay.jflow.iterators.impl.LongMapIterator;
import com.github.maumay.jflow.iterators.impl.MapIterator;
import com.github.maumay.jflow.iterators.impl.ObjectMinMaxConsumption;
import com.github.maumay.jflow.iterators.impl.ObjectPredicateConsumption;
import com.github.maumay.jflow.iterators.impl.ObjectReductionConsumption;
import com.github.maumay.jflow.iterators.impl.SlicedIterator;
import com.github.maumay.jflow.iterators.impl.TakeIterator;
import com.github.maumay.jflow.iterators.impl.TakewhileIterator;
import com.github.maumay.jflow.iterators.impl.ZipIterator;

/**
 * A skeletal implementation of a Flow, users writing custom Flows should
 * subclass this class.
 *
 * @param <E> The type of elements produced by this Flow.
 *
 * @author ThomasB
 */
public abstract class AbstractEnhancedIterator<E> extends AbstractOptionallySized
		implements EnhancedIterator<E>
{
	public AbstractEnhancedIterator(OptionalInt size)
	{
		super(size);
	}

	@Override
	public <R> AbstractEnhancedIterator<R> map(Function<? super E, ? extends R> f)
	{
		return new MapIterator.OfObject<>(this, f);
	}

	@Override
	public AbstractEnhancedIntIterator mapToInt(ToIntFunction<? super E> f)
	{
		return new IntMapIterator.FromObject<>(this, f);
	}

	@Override
	public AbstractEnhancedDoubleIterator mapToDouble(ToDoubleFunction<? super E> f)
	{
		return new DoubleMapIterator.FromObject<>(this, f);
	}

	@Override
	public AbstractEnhancedLongIterator mapToLong(ToLongFunction<? super E> f)
	{
		return new LongMapIterator.FromObject<>(this, f);
	}

	@Override
	public <R> AbstractEnhancedIterator<R> flatMap(
			Function<? super E, ? extends Iterator<? extends R>> mapping)
	{
		return new FlattenedIterator.OfObject<>(this, mapping);
	}

	@Override
	public AbstractEnhancedIntIterator flatMapToInt(
			Function<? super E, ? extends EnhancedIntIterator> mapping)
	{
		return new FlattenedIterator.OfInt<>(this, mapping);
	}

	@Override
	public AbstractEnhancedLongIterator flatMapToLong(
			Function<? super E, ? extends EnhancedLongIterator> mapping)
	{
		return new FlattenedIterator.OfLong<>(this, mapping);
	}

	@Override
	public AbstractEnhancedDoubleIterator flatMapToDouble(
			Function<? super E, ? extends EnhancedDoubleIterator> mapping)
	{
		return new FlattenedIterator.OfDouble<>(this, mapping);
	}

	@Override
	public <R> AbstractEnhancedIterator<Pair<E, R>> zipWith(Iterator<? extends R> other)
	{
		return new ZipIterator.OfObjects<>(this, other);
	}

	@Override
	public AbstractEnhancedIterator<IntWith<E>> zipWith(OfInt other)
	{
		return new ZipIterator.OfObjectAndInt<>(this, other);
	}

	@Override
	public AbstractEnhancedIterator<DoubleWith<E>> zipWith(OfDouble other)
	{
		return new ZipIterator.OfObjectAndDouble<>(this, other);
	}

	@Override
	public AbstractEnhancedIterator<LongWith<E>> zipWith(OfLong other)
	{
		return new ZipIterator.OfObjectAndLong<>(this, other);
	}

	@Override
	public AbstractEnhancedIterator<IntWith<E>> enumerate()
	{
		return zipWith(Numbers.natural());
	}

	@Override
	public AbstractEnhancedIterator<E> slice(IntUnaryOperator f)
	{
		return new SlicedIterator.OfObject<>(this, f);
	}

	@Override
	public AbstractEnhancedIterator<E> take(int n)
	{
		return new TakeIterator.OfObject<>(this, n);
	}

	@Override
	public AbstractEnhancedIterator<E> takeWhile(Predicate<? super E> predicate)
	{
		return new TakewhileIterator.OfObject<>(this, predicate);
	}

	@Override
	public AbstractEnhancedIterator<E> drop(int n)
	{
		return new DropIterator.OfObject<>(this, n);
	}

	@Override
	public AbstractEnhancedIterator<E> dropWhile(Predicate<? super E> predicate)
	{
		return new DropwhileIterator.OfObject<>(this, predicate);
	}

	@Override
	public AbstractEnhancedIterator<E> filter(Predicate<? super E> predicate)
	{
		return new FilteredIterator.OfObject<>(this, predicate);
	}

	@Override
	public AbstractEnhancedIterator<E> append(Iterator<? extends E> other)
	{
		return new AppendIterator.OfObject<>(this, other);
	}

	@Override
	public AbstractEnhancedIterator<E> insert(Iterator<? extends E> other)
	{
		return new InsertIterator.OfObject<>(this, other);
	}

	@Override
	public AbstractEnhancedIterator<E> scan(BinaryOperator<E> accumulator)
	{
		return new AccumulationIterator.OfObject<>(this, accumulator);
	}

	@Override
	public <R> AbstractEnhancedIterator<R> scan(R id, BiFunction<R, E, R> accumulator)
	{
		return new AccumulationIterator.OfObjectWithMixedTypes<>(this, id, accumulator);
	}

	@Override
	public Optional<E> min(Comparator<? super E> orderingFunction)
	{
		return ObjectMinMaxConsumption.findMin(this, orderingFunction);
	}

	@Override
	public Optional<E> max(Comparator<? super E> orderingFunction)
	{
		return ObjectMinMaxConsumption.findMax(this, orderingFunction);
	}

	@Override
	public boolean areAllEqual()
	{
		return ObjectPredicateConsumption.allEqual(this);
	}

	@Override
	public boolean allMatch(Predicate<? super E> predicate)
	{
		return ObjectPredicateConsumption.allMatch(this, predicate);
	}

	@Override
	public boolean anyMatch(Predicate<? super E> predicate)
	{
		return ObjectPredicateConsumption.anyMatch(this, predicate);
	}

	@Override
	public boolean noneMatch(Predicate<? super E> predicate)
	{
		return ObjectPredicateConsumption.noneMatch(this, predicate);
	}

	@Override
	public long count()
	{
		return ObjectReductionConsumption.count(this);
	}

	@Override
	public <R> R fold(R id, BiFunction<R, E, R> reducer)
	{
		return ObjectReductionConsumption.fold(this, id, reducer);
	}

	@Override
	public Optional<E> foldOption(BinaryOperator<E> reducer)
	{
		return ObjectReductionConsumption.reduceOption(this, reducer);
	}

	@Override
	public E fold(BinaryOperator<E> reducer)
	{
		return ObjectReductionConsumption.reduce(this, reducer);
	}
}
