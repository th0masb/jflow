package io.xyz.chains.utilities;

import static io.xyz.chains.utilities.RangeUtil.range;

import io.xyz.chains.rangedfunction.RangedIntFunction;

/**
 * @author ThomasB
 * @since 29 Jan 2018
 */
public final class Slicers
{
	public static RangedIntFunction rotate(final int shift, final int length)
	{
		return range(i -> Math.floorMod(i - shift, length), length);
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
	}
}

