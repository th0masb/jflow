/**
 * 
 */
package com.github.maumay.jflow.iterators.size;

/**
 * @author thomasb
 *
 */
public enum SizeType
{
	EXACT, LOWER_BOUND, UPPER_BOUND, UNKNOWN;

	public boolean isKnown()
	{
		return this == EXACT;
	}

	public boolean isUnknown()
	{
		return !isKnown();
	}

}
