package com.github.maumay.jflow.impl;

import com.github.maumay.jflow.iterator.IntIterator;
import com.github.maumay.jflow.iterator.IteratorSlicer;
import com.github.maumay.jflow.utils.IntTup;
import com.github.maumay.jflow.utils.Option;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.*;

/**
 * Abstract supertype of all {@link IntIterator} implementations. Users should
 * only subclass this class directly if they are creating a custom source
 * iterator, i.e. one that is not built from another iterator (an adapter). For
 * implementing custom adapters see {@link AbstractIteratorAdapter}, {@link
 * AbstractIteratorBiAdapters}.
 *
 * @author ThomasB
 */
public abstract class AbstractIntIterator extends AbstractIterator implements
        IntIterator
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
            if (hasNext()) {
                getSize().decrement();
                return Option.of(nextIntImpl());
            } else {
                return Option.emptyInt();
            }
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

    @Override
    public final void remove()
    {
        throw new UnsupportedOperationException(
                "Removing is not supported for rich iterators.");
    }

    /**
     * Implementation logic for the {@link #nextInt()} method. This method does
     * not check the ownership flag of this iterator when it is called.
     * Implementors of custom adapters should call this method on the previous
     * iterator. This method should throw a {@link NoSuchElementException} if
     * there are no further elements to traverse.
     *
     * @return The next element traversed by this iterator.
     */
    public abstract int nextIntImpl();

    // IntIterator API
    @Override
    public AbstractIntIterator slice(IteratorSlicer sliceMap)
    {
        return new SliceAdapter.OfInt(this, sliceMap);
    }

    @Override
    public AbstractIntIterator map(IntUnaryOperator f)
    {
        return new MapAdapter.OfInt(this, f);
    }

    @Override
    public <E> AbstractRichIterator<E> mapToObj(IntFunction<? extends E> f)
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
    public AbstractIntIterator skip(int n)
    {
        return new SkipAdapter.OfInt(this, n);
    }

    @Override
    public AbstractIntIterator skipWhile(IntPredicate predicate)
    {
        return new SkipwhileAdapter.OfInt(this, predicate);
    }

    @Override
    public AbstractIntIterator filter(IntPredicate predicate)
    {
        return new FilterAdapter.OfInt(this, predicate);
    }

    @Override
    public AbstractIntIterator chain(OfInt other)
    {
        return new ConcatenationAdapter.OfInt(this,
                IteratorWrapper.wrap(other));
    }

    @Override
    public AbstractIntIterator append(int... xs)
    {
        return chain(new ArraySource.OfInt(xs));
    }

    @Override
    public AbstractIntIterator rchain(OfInt other)
    {
        return new ConcatenationAdapter.OfInt(IteratorWrapper.wrap(other),
                this);
    }

    @Override
    public AbstractIntIterator insert(int... xs)
    {
        return rchain(new ArraySource.OfInt(xs));
    }

    @Override
    public OptionalInt minOp()
    {
        return IntMinMaxConsumption.findMinOption(this);
    }

    @Override
    public int min()
    {
        return IntMinMaxConsumption.findMin(this);
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
        return IntMinMaxConsumption.findMax(this);
    }

    @Override
    public <C extends Comparable<C>> OptionalInt maxByKey(IntFunction<C> key)
    {
        return IntMinMaxConsumption.findMaxOption(this, key);
    }

    @Override
    public boolean all(IntPredicate predicate)
    {
        return IntPredicateConsumption.allMatch(this, predicate);
    }

    @Override
    public boolean any(IntPredicate predicate)
    {
        return IntPredicateConsumption.anyMatch(this, predicate);
    }

    @Override
    public boolean none(IntPredicate predicate)
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