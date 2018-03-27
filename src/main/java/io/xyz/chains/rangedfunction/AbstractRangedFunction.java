/**
 *
 */
package io.xyz.chains.rangedfunction;

import io.xyz.chains.BaseChain;

/**
 * Skeletal base class of all ranged function classes. It simply provides a length field.
 *
 * @author ThomasB
 * @since 14 Feb 2018
 */
abstract class AbstractRangedFunction implements BaseChain
{
	private final int chainLength;

	AbstractRangedFunction(final int chainLength)
	{
		assert chainLength > -1;
		this.chainLength = chainLength;
	}

	@Override
	public int linkCount()
	{
		return chainLength;
	}
}
