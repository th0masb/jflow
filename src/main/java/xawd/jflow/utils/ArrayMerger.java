/**
 * 
 */
package xawd.jflow.utils;

import static io.xyz.chains.utilities.CollectionUtil.tail;

import java.util.List;

/**
 * @author t
 *
 */
public final class ArrayMerger 
{
	public static void mergeEquallySizedArrays(final int srcArraySize, final List<int[]> srcArrays, final int[] dest)
	{
		if (dest.length < srcArraySize*(srcArrays.size() - 1)) {
			throw new IllegalArgumentException();
		}
		
		final int cacheSizeMinusOne = srcArrays.size() - 1;
		for (int i = 0; i < cacheSizeMinusOne; i++) {
			System.arraycopy(srcArrays.get(i), 0, dest, i*srcArraySize, srcArraySize);
		}
		final int lastCacheSize = dest.length - cacheSizeMinusOne*srcArraySize ;
		System.arraycopy(tail(srcArrays), 0, dest, cacheSizeMinusOne*srcArraySize, lastCacheSize);
	}
}
