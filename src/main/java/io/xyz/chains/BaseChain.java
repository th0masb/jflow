/**
 *
 */
package io.xyz.chains;

/**
 * A {@linkplain BaseChain} is a finite length ordered series (chain) of
 * values which canoperate with the provided static utility methods in this
 * library. All chains therefore have a length property and the ability to
 * check whether a given index is in inside the corresponding legal index range.
 *
 * @author ThomasB
 * @since 14 Feb 2018
 */
public interface BaseChain
{

	/**
	 * @return the length (number of value links) in this chain.
	 */
	int length();

	/**
	 * @param index - a query index
	 * @return whether the query index is inside the range [0,..., length())
	 */
	default boolean indexIsInRange(final int index)
	{
		return 0 <= index && index <= length();
	}
}
