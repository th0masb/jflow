/**
 *
 */
package io.xyz.chains;


import static io.xyz.chains.utilities.CollectionUtil.len;
import static io.xyz.chains.utilities.CompositionUtil.compose;
import static io.xyz.chains.utilities.PredicateUtil.all;
import static io.xyz.chains.utilities.RangeUtil.range;

import java.util.Spliterator;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import com.google.common.primitives.ImmutableIntArray;

import io.xyz.chains.rangedfunction.RangedDoubleFunction;
import io.xyz.chains.rangedfunction.RangedFunction;
import io.xyz.chains.rangedfunction.RangedIntFunction;
import io.xyz.chains.rangedfunction.RangedLongFunction;

/**
 * A {@linkplain IntChain} is a finite length ordered series (chain) of primitive ints which can operate with the provided
 * static utility methods in this library.
 *
 * @author ThomasB
 * @since 29 Jan 2018
 */
public interface IntChain extends BaseChain
{

	/**
	 * Element retrieval method.
	 *
	 * @param index - The index of the element to be retrieved
	 * @return the element at the given index.
	 */
	int elementAt(final int index);

	/**
	 * The descriptor of a chain is the function which can produce the values 'stored' in
	 * this chain on demand, i.e. by supplying an index. It is recommended to use the elementAt
	 * method for this purpose though.
	 *
	 * @return the function descriptor
	 */
	default IntUnaryOperator getDescriptor()
	{
		return this::elementAt;
	}

	/**
	 * Converts this chain to an {@link Chain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain Chain}
	 */
	default <T> Chain<T> toObjChain(final IntFunction<T> f)
	{
		return RangedFunction.of(compose(f, getDescriptor()), length());
	}

	/**
	 * Converts this chain to a new {@link IntChain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain IntChain}
	 */
	default IntChain toIntChain(final IntUnaryOperator f)
	{
		return RangedIntFunction.of(f.compose(getDescriptor()), length());
	}

	/**
	 * Converts this chain to a {@link DoubleChain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain DoubleChain}
	 */
	default DoubleChain toDoubleChain(final IntToDoubleFunction f)
	{
		return RangedDoubleFunction.of(compose(f, getDescriptor()), length());
	}

	/**
	 * Converts this chain to an {@link LongChain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain LongChain}
	 */
	default LongChain toLongChain(final IntToLongFunction f)
	{
		return RangedLongFunction.of(compose(f, getDescriptor()), length());
	}

	/**
	 * @return a {@link Spliterator.OfInt} describing how to partition the elements
	 *         of this chain.
	 */
	Spliterator.OfInt spliterator();

	/**
	 * @return whether this chain is sorted in ascending order.
	 */
	default boolean isSorted()
	{
		return all(i -> elementAt(i) <= elementAt(i + 1), range(len(this) - 1));
	}

	/**
	 * Caches this chain to a primitive double array.
	 *
	 * @return an array of the elements in this chain.
	 */
	default int[] toArray()
	{
		final int[] result = new int[len(this)];
		for (int i = 0; i < len(this); i++) {
			result[i] = getDescriptor().applyAsInt(i);
		}
		return result;
	}

	/**
	 * Caches this chain to an {@link ImmutableIntArray} instance.
	 *
	 * @return an array of the elements in this chain.
	 */
	default ImmutableIntArray toImmutableArray()
	{
		final ImmutableIntArray.Builder builder = ImmutableIntArray.builder(len(this));
		for (int i = 0; i < len(this); i++) {
			builder.add(getDescriptor().applyAsInt(i));
		}
		return builder.build();
	}

	/**
	 * @return a sequential {@link IntStream} of the elements in this chain.
	 */
	default IntStream stream()
	{
		return StreamSupport.intStream(spliterator(), false);
	}
}
