package io.xyz.chains.rangedfunction;


import static io.xyz.chains.utilities.CollectionUtil.len;

import java.util.Spliterator;
import java.util.function.DoubleConsumer;
import java.util.function.IntToDoubleFunction;

import io.xyz.chains.DoubleChain;


/**
 * @author ThomasB
 * @since 29 Jan 2018
 */
public class ImmutableDoubleChainSpliterator implements Spliterator.OfDouble {

	private int upper, origin;
	private final IntToDoubleFunction generatingFunction;

	public ImmutableDoubleChainSpliterator(final int origin, final int upper, final IntToDoubleFunction generatingFunction)
	{
		this.upper = upper;
		this.origin = origin;
		this.generatingFunction = generatingFunction;
	}

	public ImmutableDoubleChainSpliterator(final DoubleChain generator)
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
	public boolean tryAdvance(final DoubleConsumer action)
	{
		if (origin < upper) {
			action.accept(generatingFunction.applyAsDouble(origin++));
			return true;
		}
		return false;
	}

	@Override
	public OfDouble trySplit()
	{
		final int lo = origin; // divide range in half
		final int mid = ((lo + upper) >>> 1) & ~1; // force midpoint to be even
		if (lo < mid) { // split out left half
			origin = mid; // reset this Spliterator's origin
			return new ImmutableDoubleChainSpliterator(lo, mid, generatingFunction);
		}
		else       // too small to split
			return null;
	}
}
