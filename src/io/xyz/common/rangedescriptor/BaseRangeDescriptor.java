/**
 * 
 */
package io.xyz.common.rangedescriptor;

/**
 * @author t
 *
 *         Represents functionality which all range descriptors possess. A range
 *         descriptor is a functional representation of a finite, ordered
 *         collection of elements.
 */
public interface BaseRangeDescriptor {

	int rangeBound();

	default boolean indexIsInRange(final int index)
	{
		return 0 <= index && index <= rangeBound();
	}
}
