/**
 *
 */
package com.gihub.maumay.jflow.iterators.misc;

/**
 * @author t
 *
 */
public final class IntPair
{
	public final int _1, _2;

	public IntPair(final int first, final int second)
	{
		this._1 = first;
		this._2 = second;
	}

	public static IntPair of(final int first, final int second)
	{
		return new IntPair(first, second);
	}

	public int _1()
	{
		return _1;
	}

	public int _2()
	{
		return _2;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(").append(_1).append(", ").append(_2).append(")")
				.toString();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + _1;
		result = prime * result + _2;
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final IntPair other = (IntPair) obj;
		if (_1 != other._1)
			return false;
		if (_2 != other._2)
			return false;
		return true;
	}
}
