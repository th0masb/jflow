/**
 * 
 */
package io.xyz.common.arrays;

import static io.xyz.common.funcutils.MapUtil.range;
import static io.xyz.common.funcutils.PredicateUtil.all;

import io.xyz.common.funcutils.CollectionUtil;

/**
 * @author t
 *
 */
public interface ImmutablePrimitiveArray {

	int indexBound();

	ImmutablePrimitiveArray sort();

	ImmutablePrimitiveArray sort(int lowerIndex, int upperIndex);

	boolean isSorted();

	default boolean indexInRange(final int... indices)
	{
		final int len = indexBound();
		return all(i -> 0 <= i && i < len, range(CollectionUtil.len(indices)));
	}
}
