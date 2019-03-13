/**
 *
 */
package com.github.maumay.jflow.iterators.impl2;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
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

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.AbstractIntIterator;
import com.github.maumay.jflow.iterators.AbstractLongIterator;
import com.github.maumay.jflow.iterators.DoubleIterator;
import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.IntIterator;
import com.github.maumay.jflow.iterators.LongIterator;
import com.github.maumay.jflow.iterators.impl.ObjectMinMaxConsumption;
import com.github.maumay.jflow.iterators.impl.ObjectPredicateConsumption;
import com.github.maumay.jflow.iterators.impl.ObjectReductionConsumption;
import com.github.maumay.jflow.iterators.impl2.source.IteratorWrapper;
import com.github.maumay.jflow.utils.DoubleWith;
import com.github.maumay.jflow.utils.Exceptions;
import com.github.maumay.jflow.utils.IntWith;
import com.github.maumay.jflow.utils.LongWith;
import com.github.maumay.jflow.utils.Tup;

/**
 * A skeletal implementation of a Flow, users writing custom Flows should
 * subclass this class.
 *
 * @param <E> The type of elements produced by this Flow.
 *
 * @author ThomasB
 */
public abstract class AbstractEnhancedIterator<E> implements EnhancedIterator<E>
{
	/**
	 * The sizing information for this iterator
	 */
	private final AbstractIteratorSize size;

	/**
	 * Flag indicating whether this iterator has ownership over its {@link #next()}
	 * and {@link #skip()} methods. If it does not possess ownership (in the case it
	 * has been adapted) then calling these methods will throw an exception.
	 */
	private boolean hasOwnership;

	public AbstractEnhancedIterator(AbstractIteratorSize size)
	{
		this.size = size;
		this.hasOwnership = true;
	}

	public final AbstractIteratorSize getSize()
	{
		return size;
	}

	final void relinquishOwnership()
	{
		// Can't relinquish ownership twice!
		Exceptions.require(hasOwnership);
		hasOwnership = false;
	}

	@Override
	public final E next()
	{
		if (hasOwnership) {
			size.decrement();
			return nextImpl();
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public final void skip()
	{
		if (hasOwnership) {
			size.decrement();
			skipImpl();
		} else {
			throw new RuntimeException();
		}
	}

	public abstract E nextImpl();

	public abstract void skipImpl();

	// EnhancedIterator API
	@Override
	public <R> AbstractEnhancedIterator<R> map(Function<? super E, ? extends R> f)
	{
		return new MapAdapter<>(this, f);
	}

	@Override
	public AbstractIntIterator mapToInt(ToIntFunction<? super E> f)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractDoubleIterator mapToDouble(ToDoubleFunction<? super E> f)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractLongIterator mapToLong(ToLongFunction<? super E> f)
	{
		throw new RuntimeException();
	}

	@Override
	public <R> AbstractEnhancedIterator<R> flatMap(
			Function<? super E, ? extends Iterator<? extends R>> mapping)
	{
		return new FlatmapAdapter<>(this, mapping);
	}

	@Override
	public AbstractIntIterator flatMapToInt(Function<? super E, ? extends IntIterator> mapping)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractLongIterator flatMapToLong(Function<? super E, ? extends LongIterator> mapping)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractDoubleIterator flatMapToDouble(
			Function<? super E, ? extends DoubleIterator> mapping)
	{
		throw new RuntimeException();
	}

	@Override
	public <R> AbstractEnhancedIterator<Tup<E, R>> zipWith(Iterator<? extends R> other)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractEnhancedIterator<IntWith<E>> zipWith(OfInt other)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractEnhancedIterator<DoubleWith<E>> zipWith(OfDouble other)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractEnhancedIterator<LongWith<E>> zipWith(OfLong other)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractEnhancedIterator<IntWith<E>> enumerate()
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractEnhancedIterator<E> slice(IntUnaryOperator f)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractEnhancedIterator<E> take(int n)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractEnhancedIterator<E> takeWhile(Predicate<? super E> predicate)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractEnhancedIterator<E> skip(int n)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractEnhancedIterator<E> skipWhile(Predicate<? super E> predicate)
	{
		throw new RuntimeException();
	}

	@Override
	public AbstractEnhancedIterator<E> filter(Predicate<? super E> predicate)
	{
		return new FilterAdapter<>(this, predicate);
	}

	@Override
	public AbstractEnhancedIterator<E> append(Iterator<? extends E> other)
	{
		if (other instanceof AbstractEnhancedIterator<?>) {
			return new ConcatenationAdapter<>(this, (AbstractEnhancedIterator<? extends E>) other);
		} else {
			return new ConcatenationAdapter<>(this, new IteratorWrapper<>(other));
		}
	}

	@Override
	public AbstractEnhancedIterator<E> insert(Iterator<? extends E> other)
	{
		if (other instanceof AbstractEnhancedIterator<?>) {
			return new ConcatenationAdapter<>((AbstractEnhancedIterator<? extends E>) other, this);
		} else {
			return new ConcatenationAdapter<>(new IteratorWrapper<>(other), this);
		}
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
