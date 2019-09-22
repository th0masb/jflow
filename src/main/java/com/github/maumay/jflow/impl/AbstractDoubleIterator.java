package com.github.maumay.jflow.impl;

import com.github.maumay.jflow.iterator.DoubleIterator;
import com.github.maumay.jflow.iterator.IteratorSlicer;
import com.github.maumay.jflow.iterator.collector.DoubleIteratorCollector;
import com.github.maumay.jflow.utils.DoubleTup;
import com.github.maumay.jflow.utils.Option;

import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.function.*;

/**
 * Abstract supertype of all {@link DoubleIterator} implementations. Users
 * should only subclass this class directly if they are creating a custom source
 * iterator, i.e. one that is not built from another iterator (an adapter). For
 * implementing custom adapters see {@link AbstractIteratorAdapter}, {@link
 * AbstractIteratorBiAdapters}.
 *
 * @author ThomasB
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
            throw new IteratorOwnershipException(OWNERSHIP_ERR_MSG);
        }
    }

    @Override
    public OptionalDouble nextDoubleOp()
    {
        if (hasOwnership()) {
            if (hasNext()) {
                getSize().decrement();
                return Option.of(nextDoubleImpl());
            } else {
                return Option.emptyDouble();
            }
        } else {
            throw new IteratorOwnershipException(OWNERSHIP_ERR_MSG);
        }
    }

    @Override
    public void forEach(DoubleConsumer action)
    {
        relinquishOwnership();
        while (hasNext()) {
            action.accept(nextDoubleImpl());
        }
    }

    @Override
    public final void remove()
    {
        throw new UnsupportedOperationException(
                "Removing is not supported for rich iterators.");
    }

    /**
     * Implementation logic for the {@link #nextDouble()} method. This method
     * does not check the ownership flag of this iterator when it is called.
     * Implementors of custom adapters should call this method on the previous
     * iterator. This method should throw a {@link NoSuchElementException} if
     * there are no further elements to traverse.
     *
     * @return The next element traversed by this iterator.
     */
    public abstract double nextDoubleImpl();

    // DoubleIterator API
    @Override
    public AbstractDoubleIterator slice(IteratorSlicer indexMapping)
    {
        return new SliceAdapter.OfDouble(this, indexMapping);
    }

    @Override
    public AbstractDoubleIterator map(DoubleUnaryOperator f)
    {
        return new MapAdapter.OfDouble(this, f);
    }

    @Override
    public <E> AbstractRichIterator<E> mapToObj(DoubleFunction<? extends E> f)
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
    public AbstractRichIterator<DoubleTup> zip(OfDouble other)
    {
        return new ZipAdapter.OfDoubles(this, IteratorWrapper.wrap(other));
    }

    @Override
    public AbstractRichIterator<DoubleTup> enumerate()
    {
        return new ZipAdapter.OfDoubles(new FunctionSource.OfDouble(x -> x),
                this);
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
    public AbstractDoubleIterator chain(OfDouble other)
    {
        return new ConcatenationAdapter.OfDouble(this,
                IteratorWrapper.wrap(other));
    }

    @Override
    public AbstractDoubleIterator append(double... xs)
    {
        return chain(new ArraySource.OfDouble(xs));
    }

    @Override
    public AbstractDoubleIterator rchain(OfDouble other)
    {
        return new ConcatenationAdapter.OfDouble(IteratorWrapper.wrap(other),
                this);
    }

    @Override
    public AbstractDoubleIterator insert(double... xs)
    {
        return rchain(new ArraySource.OfDouble(xs));
    }

    @Override
    public AbstractDoubleIterator scan(double id,
            DoubleBinaryOperator accumulator)
    {
        return new ScanAdapter.OfDouble(this, id, accumulator);
    }

    @Override
    public OptionalDouble minOp()
    {
        return DoubleMinMaxConsumption.findMinOption(this);
    }

    @Override
    public double min()
    {
        return DoubleMinMaxConsumption.findMin(this);
    }

    @Override
    public OptionalDouble maxOp()
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
    public boolean all(DoublePredicate predicate)
    {
        return DoublePredicateConsumption.allMatch(this, predicate);
    }

    @Override
    public boolean any(DoublePredicate predicate)
    {
        return DoublePredicateConsumption.anyMatch(this, predicate);
    }

    @Override
    public boolean none(DoublePredicate predicate)
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
    public OptionalDouble foldOp(DoubleBinaryOperator reducer)
    {
        return DoubleReductionConsumption.foldOption(this, reducer);
    }

    @Override
    public double[] toArray()
    {
        return ArrayAccumulators.consume(this);
    }

    @Override
    public DoubleIterator adapt(DoubleIteratorAdapter adapter)
    {
        return adapter.adapt(this);
    }

    @Override
    public <R> R collect(DoubleIteratorCollector<R> collector)
    {
        if (hasOwnership()) {
            R result = collector.collect(this);
            relinquishOwnership();
            return result;
        } else {
            throw new IteratorOwnershipException(OWNERSHIP_ERR_MSG);
        }
    }

    @Override
    public DoubleVecImpl toVec()
    {
        return new DoubleVecImpl(ArrayAccumulators.consume(this));
    }
}