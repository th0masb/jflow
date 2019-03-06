package com.gihub.maumay.jflow.iterators.misc;

import java.util.Objects;

/**
 * Compact composition of a primitive double value and an object reference.
 * 
 * @param <T> The type of the object reference in this tuple.
 * @author ThomasB
 * @since 20 Apr 2018
 */
public final class DoubleWith<T>
{
	/**
	 * The primitive double component of this pairing
	 */
	public final double _d;

	/**
	 * The object component of this pairing
	 */
	public final T _o;

	private DoubleWith(double doubleval, T element)
	{
		this._d = doubleval;
		this._o = Objects.requireNonNull(element);
	}

	/**
	 * Creates a new pair.
	 * 
	 * @param           <T> The type of the object reference in the new pair.
	 * @param doubleVal The primitive double element of the new pair.
	 * @param element   The object reference element of the new pair.
	 * @return The new pair.
	 */
	public static <T> DoubleWith<T> of(double doubleVal, T element)
	{
		return new DoubleWith<>(doubleVal, element);
	}

	/**
	 * Retrieve the primitive double value contained in this pairing.
	 * 
	 * @return The primitive double element of this pair.
	 */
	public double _d()
	{
		return _d;
	}

	/**
	 * Retrieve the object reference value contained in this pairing.
	 * 
	 * @return The object reference element of this pair.
	 */
	public T _o()
	{
		return _o;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(").append(_d).append(", ").append(_o.toString()).append(")")
				.toString();
	}

	@Override
	public int hashCode()
	{
		int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(_d);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((_o == null) ? 0 : _o.hashCode());
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
		DoubleWith<?> other = (DoubleWith<?>) obj;
		if (Double.doubleToLongBits(_d) != Double.doubleToLongBits(other._d))
			return false;
		if (this._o == null) {
			if (other._o != null)
				return false;
		} else if (!this._o.equals(other._o))
			return false;
		return true;
	}
}
