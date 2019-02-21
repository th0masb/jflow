/**
 *
 */
package com.gihub.maumay.jflow.iterators.misc;

/**
 * @author t
 */
public final class DoublePair
{
	public final double _1, _2;

	public DoublePair(double first, double second)
	{
		this._1 = first;
		this._2 = second;
	}

	public static DoublePair of(double first, double second)
	{
		return new DoublePair(first, second);
	}

	public double _1()
	{
		return _1;
	}

	public double _2()
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
		int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(_1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(_2);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		DoublePair other = (DoublePair) obj;
		if (Double.doubleToLongBits(_1) != Double.doubleToLongBits(other._1))
			return false;
		if (Double.doubleToLongBits(_2) != Double.doubleToLongBits(other._2))
			return false;
		return true;
	}
}
