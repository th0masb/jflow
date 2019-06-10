/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.NoSuchElementException;
import java.util.OptionalLong;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;

import com.github.maumay.jflow.iterators.IteratorSlicer;
import com.github.maumay.jflow.iterators.LongIterator;
import com.github.maumay.jflow.utils.LongTup;
import com.github.maumay.jflow.utils.Option;

/**
 * Abstract supertype of all {@link LongIterator} implementations. Users should
 * only subclass this class directly if they are creating a custom source
 * iterator, i.e. one that is not built from another iterator (an adapter). For
 * implementing custom adapters see {@link AbstractIteratorAdapter},
 * {@link AbstractIteratorBiAdapters}.
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
			throw new IteratorOwnershipException(OWNERSHIP_ERR_MSG);
		}
	}

	@Override
	public OptionalLong nextLongOp()
	{
		if (hasOwnership()) {
			if (hasNext()) {
				getSize().decrement();
				return Option.of(nextLongImpl());
			} else {
				return Option.emptyLong();
			}
		} else {
			throw new IteratorOwnershipException(OWNERSHIP_ERR_MSG);
		}
	}

	@Override
	public void forEach(LongConsumer action)
	{
		relinquishOwnership();
		while (hasNext()) {
			action.accept(nextLongImpl());
		}
	}

	/**
	 * Implementation logic for the {@link #nextLong()} method. This method does not
	 * check the ownership flag of this iterator when it is called. Implementors of
	 * custom adapters should call this method on the previous iterator. This method
	 * should throw a {@link NoSuchElementException} if there are no further
	 * elements to traverse.
	 * 
	 * @return The next element traversed by this iterator.
	 */
	public abstract long nextLongImpl();

	// LongIterator API
	@Override
	public AbstractLongIterator slice(IteratorSlicer indexMapping)
	{
		return new SliceAdapter.OfLong(this, indexMapping);
	}

	@Override
	public AbstractLongIterator map(LongUnaryOperator f)
	{
		return new MapAdapter.OfLong(this, f);
	}

	@Override
	public <E> AbstractRichIterator<E> mapToObject(LongFunction<? extends E> f)
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
	public AbstractRichIterator<LongTup> zipWith(OfLong other)
	{
		return new ZipAdapter.OfLongs(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractRichIterator<LongTup> enumerate()
	{
		return new ZipAdapter.OfLongs(new FunctionSource.OfLong(x -> x), this);
	}

	@Override
	public AbstractLongIterator take(int n)
	{
		return new TakeAdapter.OfLong(this, n);
	}

	@Override
	public AbstractLongIterator takeWhile(LongPredicate predicate)
	{
		return new TakewhileAdapter.OfLong(this, predicate);
	}

	@Override
	public AbstractLongIterator drop(int n)
	{
		return new DropAdapter.OfLong(this, n);
	}

	@Override
	public AbstractLongIterator dropWhile(LongPredicate predicate)
	{
		return new DropwhileAdapter.OfLong(this, predicate);
	}

	@Override
	public AbstractLongIterator filter(LongPredicate predicate)
	{
		return new FilterAdapter.OfLong(this, predicate);
	}

	@Override
	public AbstractLongIterator chain(OfLong other)
	{
		return new ConcatenationAdapter.OfLong(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractLongIterator chain(long... xs)
	{
		return chain(new ArraySource.OfLong(xs));
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
	public OptionalLong minOp()
	{
		return LongMinMaxConsumption.findMinOption(this);
	}

	@Override
	public long min()
	{
		return minOp().orElseThrow(IllegalStateException::new);
	}

	@Override
	public OptionalLong maxOp()
	{
		return LongMinMaxConsumption.findMaxOption(this);
	}

	@Override
	public long max()
	{
		return maxOp().orElseThrow(IllegalStateException::new);
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
	public OptionalLong foldOp(LongBinaryOperator reducer)
	{
		return LongReductionConsumption.foldOption(this, reducer);
	}

	@Override
	public long[] toArray()
	{
		return ArrayAccumulators.consume(this);
	}

	@Override
	public LongVecImpl toVec()
	{
		return new LongVecImpl(ArrayAccumulators.consume(this));
	}
}
