package xawd.jchain.chains.rangedfunction;


import static xawd.jchain.chains.utilities.CollectionUtil.len;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

import xawd.jchain.chains.Chain;


/**
 * @author ThomasB
 * @since 29 Jan 2018
 */
public class ImmutableChainSpliterator<T> implements Spliterator<T> {

	private int upper, origin;
	private final IntFunction<? extends T> generatingFunction;

	public ImmutableChainSpliterator(final int origin, final int upper, final IntFunction<? extends T> generatingFunction)
	{
		this.upper = upper;
		this.origin = origin;
		this.generatingFunction = generatingFunction;
	}

	public ImmutableChainSpliterator(final Chain<T> generator)
	{
		this(0, len(generator), generator.getDescriptor());
	}

	@Override
	public int characteristics()
	{
		return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
	}

	@Override
	public long estimateSize()
	{
		return upper - origin;
	}

	@Override
	public boolean tryAdvance(final Consumer<? super T> action)
	{
		if (origin < upper) {
			action.accept(generatingFunction.apply(origin++));
			return true;
		}
		return false;
	}

	@Override
	public Spliterator<T> trySplit()
	{
		final int lo = origin; // divide range in half
		final int mid = ((lo + upper) >>> 1) & ~1; // force midpoint to be even
		if (lo < mid) { // split out left half
			origin = mid; // reset this Spliterator's origin
			return new ImmutableChainSpliterator<>(lo, mid, generatingFunction);
		}
		else       // too small to split
			return null;
	}
}
