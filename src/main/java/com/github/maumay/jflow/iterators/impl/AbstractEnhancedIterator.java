/**
 *
 */
package com.github.maumay.jflow.iterators.impl;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.impl2.source.IteratorWrapper;
import com.github.maumay.jflow.iterators.implOld.ObjectMinMaxConsumption;
import com.github.maumay.jflow.iterators.implOld.ObjectPredicateConsumption;
import com.github.maumay.jflow.iterators.implOld.ObjectReductionConsumption;
import com.github.maumay.jflow.utils.Tup;

/**
 * A skeletal implementation of a Flow, users writing custom Flows should
 * subclass this class.
 *
 * @param <E> The type of elements produced by this Flow.
 *
 * @author ThomasB
 */
public abstract class AbstractEnhancedIterator<E> extends AbstractIterator
		implements EnhancedIterator<E>
{
	public AbstractEnhancedIterator(AbstractIteratorSize size)
	{
		super(size);
	}

	@Override
	public final E next()
	{
		if (hasOwnership()) {
			getSize().decrement();
			return nextImpl();
		} else {
			throw new RuntimeException();
		}
	}

	public abstract E nextImpl();

	// EnhancedIterator API
	@Override
	public <R> AbstractEnhancedIterator<R> map(Function<? super E, ? extends R> f)
	{
		return new MapAdapter.OfObject<>(this, f);
	}

	@Override
	public AbstractIntIterator mapToInt(ToIntFunction<? super E> f)
	{
		return new MapToIntAdapter.FromObject<>(this, f);
	}

	@Override
	public AbstractDoubleIterator mapToDouble(ToDoubleFunction<? super E> f)
	{
		return new MapToDoubleAdapter.FromObject<>(this, f);
	}

	@Override
	public AbstractLongIterator mapToLong(ToLongFunction<? super E> f)
	{
		return new MapToLongAdapter.FromObject<>(this, f);
	}

	@Override
	public <R> AbstractEnhancedIterator<R> flatMap(
			Function<? super E, ? extends Iterator<? extends R>> mapping)
	{
		return new FlatmapAdapter<>(this, mapping);
	}

	@Override
	public <R> AbstractEnhancedIterator<Tup<E, R>> zipWith(Iterator<? extends R> other)
	{
		return new ZipAdapter.OfObjects<>(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractEnhancedIterator<Tup<Integer, E>> enumerate()
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractEnhancedIterator<E> slice(IntUnaryOperator f)
	{
		return new SliceAdapter.OfObject<>(this, f);
	}

	@Override
	public AbstractEnhancedIterator<E> take(int n)
	{
		return new TakeAdapter.OfObject<>(this, n);
	}

	@Override
	public AbstractEnhancedIterator<E> takeWhile(Predicate<? super E> predicate)
	{
		return new TakewhileAdapter.OfObject<>(this, predicate);
	}

	@Override
	public AbstractEnhancedIterator<E> skip(int n)
	{
		return new SkipAdapter.OfObject<>(this, n);
	}

	@Override
	public AbstractEnhancedIterator<E> skipWhile(Predicate<? super E> predicate)
	{
		return new SkipwhileAdapter.OfObject<>(this, predicate);
	}

	@Override
	public AbstractEnhancedIterator<E> filter(Predicate<? super E> predicate)
	{
		return new FilterAdapter.OfObject<>(this, predicate);
	}

	@Override
	public AbstractEnhancedIterator<E> append(Iterator<? extends E> other)
	{
		return new ConcatenationAdapter.OfObject<>(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractEnhancedIterator<E> insert(Iterator<? extends E> other)
	{
		return new ConcatenationAdapter.OfObject<>(IteratorWrapper.wrap(other), this);
	}

	@Override
	public AbstractEnhancedIterator<E> scan(BinaryOperator<E> accumulator)
	{
		throw new RuntimeException();
	}

	@Override
	public <R> AbstractEnhancedIterator<R> scan(R id, BiFunction<R, E, R> accumulator)
	{
		throw new RuntimeException();
	}

	@Override
	public Optional<E> minOption(Comparator<? super E> orderingFunction)
	{
		return ObjectMinMaxConsumption.findMin(this, orderingFunction);
	}

	@Override
	public Optional<E> maxOption(Comparator<? super E> orderingFunction)
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
