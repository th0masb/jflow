package com.github.maumay.jflow.impl;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import com.github.maumay.jflow.iterators.IntIterator;
import com.github.maumay.jflow.utils.IntTup;
import com.github.maumay.jflow.utils.Option;

/**
 * Abstract supertype of all {@link IntIterator} implementations. Users should
 * only subclass this class directly if they are creating a custom source
 * iterator, i.e. one that is not built from another iterator (an adapter). For
 * implementing custom adapters see {@link AbstractIteratorAdapter},
 * {@link AbstractIteratorBiAdapters}.
 * 
 * @author ThomasB
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
			throw new IteratorOwnershipException(OWNERSHIP_ERR_MSG);
		}
	}

	@Override
	public OptionalInt nextIntOp()
	{
		if (hasOwnership()) {
			getSize().decrement();
			return Option.of(nextIntImpl());
		} else {
			throw new IteratorOwnershipException(OWNERSHIP_ERR_MSG);
		}
	}

	@Override
	public void forEach(IntConsumer action)
	{
		relinquishOwnership();
		while (hasNext()) {
			action.accept(nextIntImpl());
		}
	}

	/**
	 * Implementation logic for the {@link #nextInt()} method. This method does not
	 * check the ownership flag of this iterator when it is called. Implementors of
	 * custom adapters should call this method on the previous iterator. This method
	 * should throw a {@link NoSuchElementException} if there are no further
	 * elements to traverse.
	 * 
	 * @return The next element traversed by this iterator.
	 */
	public abstract int nextIntImpl();

	// IntIterator API
	@Override
	public AbstractIntIterator slice(IntUnaryOperator sliceMap)
	{
		return new SliceAdapter.OfInt(this, sliceMap);
	}

	@Override
	public AbstractIntIterator map(IntUnaryOperator f)
	{
		return new MapAdapter.OfInt(this, f);
	}

	@Override
	public <E> AbstractRichIterator<E> mapToObject(IntFunction<? extends E> f)
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
	public AbstractRichIterator<IntTup> zip(OfInt other)
	{
		return new ZipAdapter.OfInts(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractRichIterator<IntTup> enumerate()
	{
		return new ZipAdapter.OfInts(new FunctionSource.OfInt(n -> n), this);
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
	public AbstractIntIterator drop(int n)
	{
		return new DropAdapter.OfInt(this, n);
	}

	@Override
	public AbstractIntIterator dropWhile(IntPredicate predicate)
	{
		return new DropwhileAdapter.OfInt(this, predicate);
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
	public OptionalInt minOp()
	{
		return IntMinMaxConsumption.findMinOption(this);
	}

	@Override
	public int min()
	{
		return minOp().orElseThrow(IllegalStateException::new);
	}

	@Override
	public <C extends Comparable<C>> OptionalInt minByKey(IntFunction<C> key)
	{
		return IntMinMaxConsumption.findMinOption(this, key);
	}

	@Override
	public OptionalInt maxOp()
	{
		return IntMinMaxConsumption.findMaxOption(this);
	}

	@Override
	public int max()
	{
		return maxOp().orElseThrow(IllegalStateException::new);
	}

	@Override
	public <C extends Comparable<C>> OptionalInt maxByKey(IntFunction<C> key)
	{
		return IntMinMaxConsumption.findMaxOption(this, key);
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
	public OptionalInt foldOp(IntBinaryOperator reducer)
	{
		return IntReductionConsumption.foldOption(this, reducer);
	}

	@Override
	public int[] toArray()
	{
		return ArrayAccumulators.consume(this);
	}

	@Override
	public IntVecImpl toVec()
	{
		return new IntVecImpl(ArrayAccumulators.consume(this));
	}
}