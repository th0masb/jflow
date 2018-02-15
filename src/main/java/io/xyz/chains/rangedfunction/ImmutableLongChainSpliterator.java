package io.xyz.chains.rangedfunction;


import static io.xyz.chains.utilities.CollectionUtil.len;

import java.util.Spliterator;
import java.util.function.IntToLongFunction;
import java.util.function.LongConsumer;

import io.xyz.chains.LongChain;


/**
 * @author ThomasB
 * @since 29 Jan 2018
 */
public class ImmutableLongChainSpliterator implements Spliterator.OfLong {

	private int upper, origin;
	private final IntToLongFunction generatingFunction;

	public ImmutableLongChainSpliterator(final int origin, final int upper, final IntToLongFunction generatingFunction)
	{
		this.upper = upper;
		this.origin = origin;
		this.generatingFunction = generatingFunction;
	}

	public ImmutableLongChainSpliterator(final LongChain generator)
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
	public boolean tryAdvance(final LongConsumer action)
	{
		if (origin < upper) {
			action.accept(generatingFunction.applyAsLong(origin++));
			return true;
		}
		return false;
	}

	@Override
	public OfLong trySplit()
	{
		final int lo = origin; // divide range in half
		final int mid = ((lo + upper) >>> 1) & ~1; // force midpoint to be even
		if (lo < mid) { // split out left half
			origin = mid; // reset this Spliterator's origin
			return new ImmutableLongChainSpliterator(lo, mid, generatingFunction);
		}
		else       // too small to split
			return null;
	}
}
