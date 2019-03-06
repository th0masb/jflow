package com.gihub.maumay.jflow.iterators.misc;

import java.util.Objects;

/**
 * Compact composition of a primitive int value and an object reference.
 * 
 * @author ThomasB
 * @since 20 Apr 2018
 */
public final class IntWith<T>
{
	/**
	 * The primitive int component of this pairing
	 */
	public final int _i;

	/**
	 * The object component of this pairing
	 */
	public final T _o;

	private IntWith(int intval, T element)
	{
		this._i = intval;
		this._o = Objects.requireNonNull(element);
	}

	/**
	 * Creates a new pair.
	 * 
	 * @param intVal  The primitive int element of the new pair.
	 * @param element The object reference element of the new pair.
	 * @return The new pair.
	 */
	public static <T> IntWith<T> of(int intVal, T element)
	{
		return new IntWith<>(intVal, element);
	}

	/**
	 * Retrieve the primitive int value contained in this pairing.
	 * 
	 * @return The primitive int element of this pair.
	 */
	public int _i()
	{
		return _i;
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
		return new StringBuilder("(").append(_i).append(", ").append(_o.toString()).append(")")
				.toString();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_o == null) ? 0 : _o.hashCode());
		result = prime * result + _i;
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
		IntWith<?> other = (IntWith<?>) obj;
		if (_o == null) {
			if (other._o != null)
				return false;
		} else if (!_o.equals(other._o))
			return false;
		if (_i != other._i)
			return false;
		return true;
	}
}
