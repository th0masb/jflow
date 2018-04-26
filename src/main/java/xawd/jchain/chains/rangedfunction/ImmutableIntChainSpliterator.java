package xawd.jchain.chains.rangedfunction;


import static xawd.jchain.chains.utilities.CollectionUtil.len;

import java.util.Spliterator;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;

import xawd.jchain.chains.IntChain;


/**
 * @author ThomasB
 * @since 29 Jan 2018
 */
public class ImmutableIntChainSpliterator implements Spliterator.OfInt {

	private int upper, origin;
	private final IntUnaryOperator generatingFunction;

	public ImmutableIntChainSpliterator(final int origin, final int upper, final IntUnaryOperator generatingFunction)
	{
		this.upper = upper;
		this.origin = origin;
		this.generatingFunction = generatingFunction;
	}

	public ImmutableIntChainSpliterator(final IntChain generator)
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
	public boolean tryAdvance(final IntConsumer action)
	{
		if (origin < upper) {
			action.accept(generatingFunction.applyAsInt(origin++));
			return true;
		}
		return false;
	}

	@Override
	public OfInt trySplit()
	{
		final int lo = origin; // divide range in half
		final int mid = ((lo + upper) >>> 1) & ~1; // force midpoint to be even
		if (lo < mid) { // split out left half
			origin = mid; // reset this Spliterator's origin
			return new ImmutableIntChainSpliterator(lo, mid, generatingFunction);
		}
		else       // too small to split
			return null;
	}
}
