/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import com.github.maumay.jflow.iterable.RichIterable;
import com.github.maumay.jflow.iterator.IteratorSlicer;
import com.github.maumay.jflow.iterator.RichIterator;
import com.github.maumay.jflow.iterators.collector.IteratorCollector1;
import com.github.maumay.jflow.iterators.collector.IteratorCollector2;
import com.github.maumay.jflow.utils.Option;
import com.github.maumay.jflow.utils.Tup;
import com.github.maumay.jflow.vec.Vec;

/**
 * Abstract supertype of all {@link RichIterator} implementations. Users should
 * only subclass this class directly if they are creating a custom source
 * iterator, i.e. one that is not built from another iterator (an adapter). For
 * implementing custom adapters see {@link AbstractIteratorAdapter},
 * {@link AbstractIteratorBiAdapters}.
 * 
 * @param <E> The type of elements traversed by this iterator.
 *
 * @author ThomasB
 */
public abstract class AbstractRichIterator<E> extends AbstractIterator
		implements RichIterator<E>
{
	public AbstractRichIterator(AbstractIteratorSize size)
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
			throw new IteratorOwnershipException(OWNERSHIP_ERR_MSG);
		}
	}

	@Override
	public final Optional<E> nextOp()
	{
		if (hasOwnership()) {
			if (hasNext()) {
				getSize().decrement();
				return Option.of(nextImpl());
			} else {
				return Option.empty();
			}
		} else {
			throw new IteratorOwnershipException(OWNERSHIP_ERR_MSG);
		}
	}

	@Override
	public final void forEach(Consumer<? super E> action)
	{
		relinquishOwnership();
		while (hasNext()) {
			action.accept(nextImpl());
		}
	}

	@Override
	public final void remove()
	{
		throw new UnsupportedOperationException(
				"Removing is not supported for rich iterators.");
	}

	/**
	 * Implementation logic for the {@link #next()} method. This method does not
	 * check the ownership flag of this iterator when it is called. Implementors of
	 * custom adapters should call this method on the previous iterator. This method
	 * should throw a {@link NoSuchElementException} if there are no further
	 * elements to traverse.
	 * 
	 * @return The next element traversed by this iterator.
	 */
	public abstract E nextImpl();

	// EnhancedIterator API
	@Override
	public <R> AbstractRichIterator<R> map(Function<? super E, ? extends R> f)
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
	public <R> AbstractRichIterator<R> flatMap(
			Function<? super E, ? extends Iterator<? extends R>> mapping)
	{
		return new FlatmapAdapter<>(this, mapping);
	}

	@Override
	public <R> AbstractRichIterator<Tup<E, R>> zip(Iterator<? extends R> other)
	{
		return new ZipAdapter.OfObjects<>(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractRichIterator<E> interleave(Iterator<? extends E> other)
	{
		return new InterleaveAdapter<>(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractRichIterator<Tup<Integer, E>> enumerate()
	{
		return new ZipAdapter.OfObjects<>(new FunctionSource.OfObject<>(n -> n), this);
	}

	@Override
	public AbstractRichIterator<E> slice(IteratorSlicer f)
	{
		return new SliceAdapter.OfObject<>(this, f);
	}

	@Override
	public AbstractRichIterator<E> take(int n)
	{
		return new TakeAdapter.OfObject<>(this, n);
	}

	@Override
	public AbstractRichIterator<E> takeWhile(Predicate<? super E> predicate)
	{
		return new TakewhileAdapter.OfObject<>(this, predicate);
	}

	@Override
	public AbstractRichIterator<E> drop(int n)
	{
		return new DropAdapter.OfObject<>(this, n);
	}

	@Override
	public AbstractRichIterator<E> dropWhile(Predicate<? super E> predicate)
	{
		return new DropwhileAdapter.OfObject<>(this, predicate);
	}

	@Override
	public AbstractRichIterator<E> filter(Predicate<? super E> predicate)
	{
		return new FilterAdapter.OfObject<>(this, predicate);
	}

	@Override
	public AbstractRichIterator<E> chain(Iterator<? extends E> other)
	{
		return new ConcatenationAdapter.OfObject<>(this, IteratorWrapper.wrap(other));
	}

	@Override
	public AbstractRichIterator<E> insert(Iterator<? extends E> other)
	{
		return new ConcatenationAdapter.OfObject<>(IteratorWrapper.wrap(other), this);
	}

	@Override
	public RichIterator<E> append(E e)
	{
		return new ConcatenationAdapter.OfObject<>(this, new ArraySource.OfObject<>(e));
	}

	@Override
	public RichIterator<E> insert(E e)
	{
		return new ConcatenationAdapter.OfObject<>(new ArraySource.OfObject<>(e), this);
	}

	@Override
	public <R> AbstractRichIterator<R> scan(R id, BiFunction<R, E, R> accumulator)
	{
		return new ScanAdapter.OfObject<>(this, id, accumulator);
	}

	@Override
	public <R> AbstractRichIterator<R> adapt(RichIteratorAdapter<? super E, R> adapter)
	{
		return adapter.adapt(this);
	}

	@Override
	public <R> R collect(IteratorCollector1<? super E, ? extends R> collector)
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
	public <R> R collect(IteratorCollector2<E, ? extends R> collector)
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
	public Optional<E> minOp(Comparator<? super E> orderingFunction)
	{
		return ObjectMinMaxConsumption.findMin(this, orderingFunction);
	}

	@Override
	public Optional<E> maxOp(Comparator<? super E> orderingFunction)
	{
		return ObjectMinMaxConsumption.findMax(this, orderingFunction);
	}

	@Override
	public E min(Comparator<? super E> orderingFunction)
	{
		return minOp(orderingFunction).orElseThrow(IllegalStateException::new);
	}

	@Override
	public E max(Comparator<? super E> orderingFunction)
	{
		return maxOp(orderingFunction).orElseThrow(IllegalStateException::new);
	}

	@Override
	public boolean all(Predicate<? super E> predicate)
	{
		return ObjectPredicateConsumption.allMatch(this, predicate);
	}

	@Override
	public boolean any(Predicate<? super E> predicate)
	{
		return ObjectPredicateConsumption.anyMatch(this, predicate);
	}

	@Override
	public boolean none(Predicate<? super E> predicate)
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
	public Optional<E> foldOp(BinaryOperator<E> reducer)
	{
		return ObjectReductionConsumption.foldOp(this, reducer);
	}

	@Override
	public E fold(BinaryOperator<E> reducer)
	{
		return ObjectReductionConsumption.fold(this, reducer);
	}

	@Override
	public VecImpl<E> toVec()
	{
		return new VecImpl<>(ArrayAccumulators.consume(this));
	}

	@Override
	public <R> AbstractRichIterator<Tup<E, R>> zip(List<? extends R> other)
	{
		return zip(new CollectionSource<>(other));
	}

	@Override
	public <R> AbstractRichIterator<Tup<E, R>> zip(Vec<? extends R> other)
	{
		return zip(other.iter());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <R> AbstractRichIterator<R> cast()
	{
		return map(x -> (R) x);
	}

	@Override
	public <C extends Collection<E>> C to(Supplier<C> collectionFactory)
	{
		relinquishOwnership();
		C coll = collectionFactory.get();
		while (hasNext()) {
			coll.add(nextImpl());
		}
		return coll;
	}

	@Override
	public <K, V> Map<K, V> toMap(Function<? super E, ? extends K> keyMapper,
			Function<? super E, ? extends V> valueMapper)
	{
		relinquishOwnership();
		Map<K, V> collected = new HashMap<>();
		while (hasNext()) {
			E next = nextImpl();
			K key = keyMapper.apply(next);
			if (collected.containsKey(key)) {
				throw new IllegalStateException();
			} else {
				collected.put(key, valueMapper.apply(next));
			}
		}
		return collected;
	}

	@Override
	public <V> Map<E, V> associate(Function<? super E, ? extends V> valueMapper)
	{
		relinquishOwnership();
		Map<E, V> collected = new HashMap<>();
		while (hasNext()) {
			E key = nextImpl();
			if (collected.containsKey(key)) {
				throw new IllegalStateException();
			} else {
				collected.put(key, valueMapper.apply(key));
			}
		}
		return collected;
	}

	@Override
	public <K> Map<K, List<E>> groupBy(Function<? super E, ? extends K> classifier)
	{
		relinquishOwnership();
		Map<K, List<E>> collected = new HashMap<>();
		while (hasNext()) {
			E next = nextImpl();
			K key = classifier.apply(next);
			collected.putIfAbsent(key, new ArrayList<>());
			collected.get(key).add(next);
		}
		return collected;
	}

	@Override
	public RichIterable<E> lift()
	{
		return new SingleUseIterable<>(this);
	}

	@Override
	public E find(Predicate<? super E> predicate)
	{
		return filter(predicate).next();
	}

	@Override
	public Optional<E> findOp(Predicate<? super E> predicate)
	{
		return filter(predicate).nextOp();
	}
}