package xawd.jchain.chains.rangedfunction;


import static xawd.jchain.chains.utilities.CollectionUtil.len;

import java.util.Arrays;
import java.util.Spliterator.OfLong;
import java.util.function.IntToLongFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.google.common.primitives.ImmutableDoubleArray;
import com.google.common.primitives.ImmutableLongArray;

import xawd.jchain.chains.LongChain;

/**
 * A {@linkplain RangedLongFunction} is a function {@code f : [0..k] -> set(longs)} where {@code k} is some natural number.
 *
 * @author ThomasB
 * @since 18 Jan 2018
 */
public class RangedLongFunction extends AbstractRangedFunction implements LongChain
{
	/** The degenerate (empty) case where our domain is the empty set. */
	private static final RangedLongFunction EMPTY = RangedLongFunction.of(i -> i, 0);

	/** The descriptor function which characterises this mapping. */
	private final IntToLongFunction descriptor;

	private RangedLongFunction(final IntToLongFunction f, final int rangeBound)
	{
		super(rangeBound);
		descriptor = f;
	}

	/**
	 * Constructs a {@link RangedLongFunction} of given length and descriptor
	 *
	 * @param length - The required length of the function domain
	 * @param descriptor - The function defining the target set
	 * @return a {@linkplain RangedLongFunction} mapping between the sets described by the parameters
	 */
	public static RangedLongFunction of(@Nonnull final IntToLongFunction descriptor, @Nonnegative final int length)
	{
		return new RangedLongFunction(descriptor, length);
	}

	/**
	 * Constructs a {@link RangedLongFunction} from the supplied {@link ImmutableDoubleArray}
	 *
	 * @param xs - The elements the function will produce, retaining original order
	 * @return the {@linkplain RangedLongFunction} representation of <b>xs</b>
	 */
	public static RangedLongFunction from(@Nonnull final ImmutableLongArray xs)
	{
		return new RangedLongFunction(xs::get, len(xs));
	}

	/**
	 * Constructs a {@link RangedLongFunction} from the supplied elements.
	 * <br><br>
	 * <b>Note:</b> Mutations in the source Array will be reflected in the returned function.
	 *
	 * @param xs - The elements the function will produce, retaining original order
	 * @return the {@linkplain RangedLongFunction} representation of <b>xs</b>
	 */
	public static RangedLongFunction from(@Nonnull final long... xs)
	{
		return new RangedLongFunction(i -> xs[i], len(xs));
	}

	/**
	 * @return the degenerate case (i.e. of length 0).
	 */
	public static RangedLongFunction empty()
	{
		return EMPTY;
	}

	@Override
	public long elementAt(final int index)
	{
		assert indexIsInRange(index);
		return descriptor.applyAsLong(index);
	}

	@Override
	public IntToLongFunction getDescriptor()
	{
		return descriptor;
	}

	@Override
	public OfLong spliterator()
	{
		return new ImmutableLongChainSpliterator(this);
	}

	@Override
	public String toString()
	{
		return Arrays.toString(toArray());
	}
}

