package xawd.jchain.chains;

import static xawd.jchain.chains.utilities.CollectionUtil.len;
import static xawd.jchain.chains.utilities.CompositionUtil.compose;
import static xawd.jchain.chains.utilities.PredicateUtil.all;
import static xawd.jchain.chains.utilities.RangeUtil.range;

import java.util.Spliterator;
import java.util.function.IntToLongFunction;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

import com.google.common.primitives.ImmutableLongArray;

import xawd.jchain.chains.rangedfunction.RangedDoubleFunction;
import xawd.jchain.chains.rangedfunction.RangedFunction;
import xawd.jchain.chains.rangedfunction.RangedIntFunction;
import xawd.jchain.chains.rangedfunction.RangedLongFunction;

/**
 * A {@linkplain Chain} is a finite length ordered series (chain) of primitive longs which can operate with the provided
 * static utility methods in this library.
 *
 * @author ThomasB
 * @since 29 Jan 2018
 */
public interface LongChain extends BaseChain
{
	/**
	 * Element retrieval method.
	 *
	 * @param index - The index of the element to be retrieved
	 * @return the element at the given index.
	 */
	long elementAt(final int index);

	/**
	 * @return a {@link Spliterator.OfLong} describing how to partition the elements
	 *         of this chain.
	 */
	Spliterator.OfLong spliterator();

	/**
	 * The descriptor of a chain is the function which can produce the values 'stored' in
	 * this chain on demand, i.e. by supplying an index. It is recommended to use the elementAt
	 * method for this purpose though.
	 *
	 * @return the function descriptor
	 */
	default IntToLongFunction getDescriptor()
	{
		return this::elementAt;
	}

	/**
	 * Converts this chain to a {@link Chain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain Chain}
	 */
	default <T> Chain<T> mapToObj(final LongFunction<T> f)
	{
		return RangedFunction.of(compose(f, getDescriptor()), linkCount());
	}

	/**
	 * Converts this chain to an {@link IntChain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain IntChain}
	 */
	default IntChain mapToInt(final LongToIntFunction f)
	{
		return RangedIntFunction.of(compose(f, getDescriptor()), linkCount());
	}

	/**
	 * Converts this chain to a {@link DoubleChain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain DoubleChain}
	 */
	default DoubleChain mapToDouble(final LongToDoubleFunction f)
	{
		return RangedDoubleFunction.of(compose(f, getDescriptor()), linkCount());
	}

	/**
	 * Converts this chain to a new {@link LongChain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain LongChain}
	 */
	default LongChain map(final LongUnaryOperator f)
	{
		return RangedLongFunction.of(compose(f, getDescriptor()), linkCount());
	}

	/**
	 * @return whether this chain is sorted in ascending order.
	 */
	default boolean isSorted()
	{
		return all(i -> elementAt(i) < elementAt(i + 1), range(linkCount() - 1));
	}

	/**
	 * Caches this chain to a primitive double array.
	 *
	 * @return an array of the elements in this chain.
	 */
	default long[] toArray()
	{
		final long[] result = new long[len(this)];
		for (int i = 0; i < len(this); i++) {
			result[i] = getDescriptor().applyAsLong(i);
		}
		return result;
	}

	/**
	 * Caches this chain to an {@link ImmutableLongArray} instance.
	 *
	 * @return an array of the elements in this chain.
	 */
	default ImmutableLongArray toImmutableArray()
	{
		final ImmutableLongArray.Builder builder = ImmutableLongArray.builder(len(this));
		for (int i = 0; i < len(this); i++) {
			builder.add(getDescriptor().applyAsLong(i));
		}
		return builder.build();
	}

	/**
	 * @return a sequential {@link IntStream} of the elements in this chain.
	 */
	default LongStream stream()
	{
		return StreamSupport.longStream(spliterator(), false);
	}
}