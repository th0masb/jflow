/**
 *
 */
package xawd.jchain.chains;

import static xawd.jchain.chains.utilities.CollectionUtil.len;
import static xawd.jchain.chains.utilities.CompositionUtil.compose;
import static xawd.jchain.chains.utilities.PredicateUtil.all;
import static xawd.jchain.chains.utilities.RangeUtil.range;

import java.util.Spliterator;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.stream.DoubleStream;
import java.util.stream.StreamSupport;

import com.google.common.primitives.ImmutableDoubleArray;

import xawd.jchain.chains.rangedfunction.RangedDoubleFunction;
import xawd.jchain.chains.rangedfunction.RangedFunction;
import xawd.jchain.chains.rangedfunction.RangedIntFunction;
import xawd.jchain.chains.rangedfunction.RangedLongFunction;

/**
 * A {@linkplain DoubleChain} is a finite length ordered series (chain) of primitive doubles which can operate with the provided
 * static utility methods in this library.
 *
 * @author ThomasB
 * @since 29 Jan 2018
 */
public interface DoubleChain extends BaseChain
{
	/**
	 * Element retrieval method.
	 *
	 * @param index - The index of the element to be retrieved
	 * @return the element at the given index.
	 */
	double elementAt(final int index);

	/**
	 * @return a {@link Spliterator.OfDouble} describing how to partition the elements
	 *         of this chain.
	 */
	Spliterator.OfDouble spliterator();

	/**
	 * The descriptor of a chain is the function which can produce the values 'stored' in
	 * this chain on demand, i.e. by supplying an index. It is recommended to use the elementAt
	 * method for this purpose though.
	 *
	 * @return the function descriptor
	 */
	default IntToDoubleFunction getDescriptor()
	{
		return this::elementAt;
	}

	/**
	 * Converts this chain to an {@link Chain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain Chain}
	 */
	default <T> Chain<T> mapToObj(final DoubleFunction<T> f)
	{
		return RangedFunction.of(compose(f, getDescriptor()), linkCount());
	}

	/**
	 * Converts this chain to an {@link IntChain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain IntChain}
	 */
	default IntChain mapToInt(final DoubleToIntFunction f)
	{
		return RangedIntFunction.of(compose(f, getDescriptor()), linkCount());
	}

	/**
	 * Converts this chain to a new {@link DoubleChain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain DoubleChain}
	 */
	default DoubleChain map(final DoubleUnaryOperator f)
	{
		return RangedDoubleFunction.of(compose(f, getDescriptor()), linkCount());
	}

	/**
	 * Converts this chain to an {@link LongChain} via function composition.
	 *
	 * @param f - The function to apply after this instance's descriptor.
	 * @return the new {@linkplain LongChain}
	 */
	default LongChain mapToLong(final DoubleToLongFunction f)
	{
		return RangedLongFunction.of(compose(f, getDescriptor()), linkCount());
	}

	/**
	 * @return whether this chain is sorted in ascending order.
	 */
	default boolean isSorted()
	{
		return all(i -> elementAt(i) <= elementAt(i + 1), range(len(this) - 1));
	}

	/**
	 * Converts this chain to a primitive double array.
	 *
	 * @return an array of the elements in this chain.
	 */
	default double[] toArray()
	{
		final double[] result = new double[len(this)];
		for (int i = 0; i < len(this); i++) {
			result[i] = getDescriptor().applyAsDouble(i);
		}
		return result;
	}

	/**
	 * Converts this chain to an {@link ImmutableDoubleArray} instance.
	 *
	 * @return an array of the elements in this chain.
	 */
	default ImmutableDoubleArray toImmutableArray()
	{
		final ImmutableDoubleArray.Builder builder = ImmutableDoubleArray.builder(len(this));
		for (int i = 0; i < len(this); i++) {
			builder.add(getDescriptor().applyAsDouble(i));
		}
		return builder.build();
	}

	/**
	 * @return a sequential {@link DoubleStream} of the elements in this chain.
	 */
	default DoubleStream stream()
	{
		return StreamSupport.doubleStream(spliterator(), false);
	}
}
