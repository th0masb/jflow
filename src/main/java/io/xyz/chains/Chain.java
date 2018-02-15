/**
 *
 */
package io.xyz.chains;


import static io.xyz.chains.utilities.CollectionUtil.len;
import static io.xyz.chains.utilities.CompositionUtil.compose;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import io.xyz.chains.rangedfunction.RangedDoubleFunction;
import io.xyz.chains.rangedfunction.RangedFunction;
import io.xyz.chains.rangedfunction.RangedIntFunction;
import io.xyz.chains.rangedfunction.RangedLongFunction;

/**
 * A {@linkplain Chain} is a finite length ordered series (chain) of objects which can operate with the provided
 * static utility methods in this library.
 *
 * @author ThomasB
 * @since 29 Jan 2018
 */
public interface Chain<T> extends BaseChain, Iterable<T>
{

	/**
	 * Element retrieval method.
	 *
	 * @param index - The index of the element to be retrieved
	 * @return the element at the given index.
	 */
	T elementAt(final int index);

	/**
	 * The descriptor of a chain is the function which can produce the values 'stored' in
	 * this chain on demand, i.e. by supplying an index. It is recommended to use the elementAt
	 * method for this purpose though.
	 *
	 * @return the function descriptor
	 */
	default IntFunction<T> getDescriptor()
	{
		return this::elementAt;
	}

	/**
	 * Converts this chain to a new {@link Chain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain Chain}
	 */
	default <R> Chain<R> toObjChain(final Function<? super T, R> f)
	{
		return RangedFunction.of(compose(f, getDescriptor()), length());
	}

	/**
	 * Converts this chain to an {@link IntChain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain IntChain}
	 */
	default IntChain toIntChain(final ToIntFunction<? super T> f)
	{
		return RangedIntFunction.of(compose(f, getDescriptor()), length());
	}

	/**
	 * Converts this chain to a {@link DoubleChain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain DoubleChain}
	 */
	default DoubleChain toDoubleChain(final ToDoubleFunction<? super T> f)
	{
		return RangedDoubleFunction.of(compose(f, getDescriptor()), length());
	}

	/**
	 * Converts this chain to an {@link LongChain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain LongChain}
	 */
	default LongChain toLongChain(final ToLongFunction<? super T> f)
	{
		return RangedLongFunction.of(compose(f, getDescriptor()), length());
	}

	/**
	 * Converts this chain to a {@link ImmutableList}
	 *
	 * @return an {@linkplain ImmutableList} containing all elements in this chain
	 */
	default ImmutableList<T> toList()
	{
		return ImmutableList.copyOf(this);
	}

	/**
	 * Converts this chain to an {@link ArrayList}.
	 *
	 * @return an {@linkplain ArrayList} containing all elements in this chain.
	 */
	default List<T> toMutableList()
	{
		final List<T> mutable = new ArrayList<>(len(this));
		for (final T x : this) {
			mutable.add(x);
		}
		return mutable;
	}

	/**
	 * Converts this chain to a {@link ImmutableSet}
	 *
	 * @return an {@linkplain ImmutableSet} containing all unique elements in this chain
	 */
	default ImmutableSet<T> toSet()
	{
		return ImmutableSet.copyOf(this);
	}

	@Override
	default Iterator<T> iterator()
	{
		return new ChainIterator<>(this);
	}

	/**
	 * @return a sequential {@link Stream} of the elements in this chain
	 */
	default Stream<T> stream()
	{
		return StreamSupport.stream(spliterator(), false);
	}

	/**
	 * Default implementation of an {@link Iterator} for a {@link Chain}
	 *
	 * @author ThomasB
	 * @since 29 Jan 2018
	 */
	static class ChainIterator<T> implements Iterator<T>
	{

		private int upper, origin;
		private final IntFunction<? extends T> generatingFunction;

		public ChainIterator(final Chain<T> chain)
		{
			this.upper = len(chain);
			this.origin = 0;
			this.generatingFunction = chain.getDescriptor();
		}

		@Override
		public boolean hasNext()
		{
			return origin < upper;
		}

		@Override
		public T next()
		{
			return generatingFunction.apply(origin++);
		}
	}
}
