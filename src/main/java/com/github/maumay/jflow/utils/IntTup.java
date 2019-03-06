/**
 *
 */
package com.github.maumay.jflow.utils;

/**
 * Compact two element tuple (pair) of primitive doubles inspired by Scala.
 * 
 * @author t
 */
public final class IntTup
{
	/**
	 * First element of the pair. Note this is the Scala naming convention for
	 * tuples
	 */
	public final int _1;

	/**
	 * Second element of the pair. Note this is the Scala naming convention for
	 * tuples
	 */
	public final int _2;

	public IntTup(int first, int second)
	{
		this._1 = first;
		this._2 = second;
	}

	/**
	 * Creates a new tuple.
	 * 
	 * @param first  The first (left) element of the new tuple.
	 * @param second The second (right) element of the new tuple.
	 * @return The new tuple.
	 */
	public static IntTup of(int first, int second)
	{
		return new IntTup(first, second);
	}

	/**
	 * Retrieve the first (left) element of this tuple.
	 * 
	 * @return The first element of this tuple.
	 */
	public int _1()
	{
		return _1;
	}

	/**
	 * Retrieve the second (right) element of this tuple.
	 * 
	 * @return The second element of this tuple.
	 */
	public int _2()
	{
		return _2;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(").append(_1).append(", ").append(_2).append(")").toString();
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
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final IntTup other = (IntTup) obj;
		if (_1 != other._1)
			return false;
		if (_2 != other._2)
			return false;
		return true;
	}
}
