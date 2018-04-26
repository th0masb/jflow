/**
 *
 */
package xawd.jchain.chains.rangedfunction;


import static xawd.jchain.chains.utilities.CollectionUtil.len;

import java.util.Arrays;
import java.util.Spliterator.OfDouble;
import java.util.function.IntToDoubleFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.google.common.primitives.ImmutableDoubleArray;

import xawd.jchain.chains.DoubleChain;

/**
 * A {@linkplain RangedDoubleFunction} is a function {@code f : [0..k] -> set(doubles)} where {@code k} is some natural number.
 *
 * @author ThomasB
 * @since 17 Jan 2018
 */
public final class RangedDoubleFunction extends AbstractRangedFunction implements DoubleChain
{
	/** The degenerate (empty) case where our domain is the empty set. */
	private static final RangedDoubleFunction EMPTY = RangedDoubleFunction.of(i -> i, 0);

	/** The descriptor function which characterises this mapping. */
	private final IntToDoubleFunction descriptor;

	private RangedDoubleFunction(final IntToDoubleFunction f, final int rangeBound)
	{
		super(rangeBound);
		descriptor = f;
	}

	/**
	 * Constructs a {@link RangedDoubleFunction} of given length and descriptor
	 *
	 * @param length - The required length of the function domain
	 * @param descriptor - The function defining the target set
	 * @return a {@linkplain RangedDoubleFunction} mapping between the sets described by the parameters
	 */
	public static RangedDoubleFunction of(@Nonnull final IntToDoubleFunction descriptor, @Nonnegative final int length)
	{
		return new RangedDoubleFunction(descriptor, length);
	}

	/**
	 * Constructs a {@link RangedDoubleFunction} from the supplied {@link ImmutableDoubleArray}
	 *
	 * @param xs - The elements the function will produce, retaining original order
	 * @return the {@linkplain RangedDoubleFunction} representation of <b>xs</b>
	 */
	public static RangedDoubleFunction from(@Nonnull final ImmutableDoubleArray xs)
	{
		return of(i -> xs.get(i), len(xs));
	}

	/**
	 * Constructs a {@link RangedDoubleFunction} from the supplied elements.
	 * <br><br>
	 * <b>Note:</b> Mutations in the source Array will be reflected in the returned function.
	 *
	 * @param xs - The elements the function will produce, retaining original order
	 * @return the {@linkplain RangedDoubleFunction} representation of <b>xs</b>
	 */
	public static RangedDoubleFunction from(@Nonnull final double... xs)
	{
		return of(i -> xs[i], len(xs));
	}

	/**
	 * @return the degenerate case (i.e. of length 0).
	 */
	public static RangedDoubleFunction empty()
	{
		return EMPTY;
	}

	@Override
	public double elementAt(final int index)
	{
		assert indexIsInRange(index);
		return descriptor.applyAsDouble(index);
	}

	@Override
	public IntToDoubleFunction getDescriptor()
	{
		return descriptor;
	}

	@Override
	public OfDouble spliterator()
	{
		return new ImmutableDoubleChainSpliterator(this);
	}

	@Override
	public String toString()
	{
		return Arrays.toString(toArray());
	}
}
