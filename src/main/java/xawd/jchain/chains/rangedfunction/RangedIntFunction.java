/**
 *
 */
package xawd.jchain.chains.rangedfunction;


import static xawd.jchain.chains.utilities.CollectionUtil.len;

import java.util.Arrays;
import java.util.Spliterator.OfInt;
import java.util.function.IntUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.google.common.primitives.ImmutableDoubleArray;
import com.google.common.primitives.ImmutableIntArray;

import xawd.jchain.chains.IntChain;

/**
 * A {@linkplain RangedIntFunction} is a function {@code f : [0..k] -> set(ints)} where {@code k} is some natural number.
 *
 * @author ThomasB
 * @since 17 Jan 2018
 */
public final class RangedIntFunction extends AbstractRangedFunction implements IntChain
{
	/** The degenerate (empty) case where our domain is the empty set. */
	private static final RangedIntFunction EMPTY = RangedIntFunction.of(i -> i, 0);

	/** The descriptor function which characterises this mapping. */
	private final IntUnaryOperator descriptor;

	private RangedIntFunction(final IntUnaryOperator f, final int rangeBound)
	{
		super(rangeBound);
		this.descriptor = f;
	}

	/**
	 * Constructs a {@link RangedIntFunction} of given length and descriptor
	 *
	 * @param length - The required length of the function domain
	 * @param descriptor - The function defining the target set
	 * @return a {@linkplain RangedIntFunction} mapping between the sets described by the parameters
	 */
	public static RangedIntFunction of(@Nonnull final IntUnaryOperator descriptor, @Nonnegative final int length)
	{
		return new RangedIntFunction(descriptor, length);
	}

	/**
	 * Constructs a {@link RangedIntFunction} from the supplied {@link ImmutableDoubleArray}
	 *
	 * @param xs - The elements the function will produce, retaining original order
	 * @return the {@linkplain RangedIntFunction} representation of <b>xs</b>
	 */
	public static RangedIntFunction from(@Nonnull final ImmutableIntArray xs)
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
	public static RangedIntFunction from(@Nonnull final int... xs)
	{
		return of(i -> xs[i], len(xs));
	}

	/**
	 * @return the degenerate case (i.e. of length 0).
	 */
	public static RangedIntFunction empty()
	{
		return EMPTY;
	}

	@Override
	public int elementAt(final int index)
	{
		assert indexIsInRange(index);
		return descriptor.applyAsInt(index);
	}

	@Override
	public IntUnaryOperator getDescriptor()
	{
		return descriptor;
	}

	@Override
	public OfInt spliterator()
	{
		return new ImmutableIntChainSpliterator(this);
	}

	@Override
	public String toString()
	{
		return Arrays.toString(toArray());
	}
}
