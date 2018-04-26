/**
 *
 */
package xawd.jchain.chains.rangedfunction;


import static xawd.jchain.chains.utilities.CollectionUtil.len;

import java.util.List;
import java.util.Spliterator;
import java.util.function.IntFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.google.common.primitives.ImmutableDoubleArray;

import xawd.jchain.chains.Chain;

/**
 * A {@linkplain RangedFunction} is a function {@code f : [0..k] -> set(T)} where {@code k} is some natural number.
 *
 * @author ThomasB
 * @since 17 Jan 2018
 */
public final class RangedFunction<T> extends AbstractRangedFunction implements Chain<T>
{
	/** The descriptor function which characterises this mapping. */
	private final IntFunction<? extends T> descriptor;

	private RangedFunction(final IntFunction<? extends T> f, final int rangeBound)
	{
		super(rangeBound);
		descriptor = f;
	}

	/**
	 * Constructs a {@link RangedFunction} of given length and descriptor
	 *
	 * @param length - The required length of the function domain
	 * @param descriptor - The function defining the target set
	 * @return a {@linkplain RangedFunction} mapping between the sets described by the parameters
	 */
	public static <T> RangedFunction<T> of(@Nonnull final IntFunction<? extends T> f, @Nonnegative final int size)
	{
		return new RangedFunction<>(f, size);
	}

	/**
	 * Constructs a {@link RangedFunction} from the supplied {@link ImmutableDoubleArray}
	 *
	 * @param xs - The elements the function will produce, retaining original order
	 * @return the {@linkplain RangedFunction} representation of <b>xs</b>
	 */
	public static <T> RangedFunction<T> from(@Nonnull final List<? extends T> xs)
	{
		return new RangedFunction<>(i -> xs.get(i), len(xs));
	}

	/**
	 *
	 * Constructs a {@link RangedFunction} from the supplied elements.
	 * <br><br>
	 * <b>Note:</b> Mutations in the source Array will be reflected in the returned function.
	 *
	 * @param xs - The elements the function will produce, retaining original order
	 * @return the {@linkplain RangedFunction} representation of <b>xs</b>
	 */
	@SafeVarargs
	public static <T> RangedFunction<T> from(@Nonnull final T... xs)
	{
		return of(i -> xs[i], len(xs));
	}

	@Override
	public T elementAt(final int index)
	{
		assert indexIsInRange(index);
		return descriptor.apply(index);
	}

	@Override
	public Spliterator<T> spliterator()
	{
		return new ImmutableChainSpliterator<>(this);
	}

	@Override
	public String toString()
	{
		return toList().toString();
	}
}
