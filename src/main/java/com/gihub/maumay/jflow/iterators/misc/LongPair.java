/**
 *
 */
package com.gihub.maumay.jflow.iterators.misc;

/**
 * @author t
 *
 */
public final class LongPair
{
	public final long _1, _2;

	public LongPair(long first, long second)
	{
		this._1 = first;
		this._2 = second;
	}

	public static LongPair of(long first, long second)
	{
		return new LongPair(first, second);
	}

	public long _1()
	{
		return _1;
	}

	public long _2()
	{
		return _2;
	}

	@Override
	public int hashCode()
	{
		int prime = 31;
		int result = 1;
		result = prime * result + (int) (_1 ^ (_1 >>> 32));
		result = prime * result + (int) (_2 ^ (_2 >>> 32));
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
		LongPair other = (LongPair) obj;
		if (_1 != other._1)
			return false;
		if (_2 != other._2)
			return false;
		return true;
	}
}
