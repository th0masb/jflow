package com.github.maumay.jflow.utils;

import java.util.Objects;

/**
 * Compact composition of a primitive long value and an object reference.
 * 
 * @param <T> The type of the object reference in this tuple.
 * @author ThomasB
 * @since 20 Apr 2018
 */
public final class LongWith<T>
{
	/**
	 * The primitive long component of this pairing
	 */
	public final long _l;

	/**
	 * The object component of this pairing
	 */
	public final T _o;

	private LongWith(long longval, T element)
	{
		this._l = longval;
		this._o = Objects.requireNonNull(element);
	}

	/**
	 * Creates a new pair.
	 * 
	 * @param         <T> The type of the object reference in the new pair.
	 * @param longVal The primitive long element of the new pair.
	 * @param element The object reference element of the new pair.
	 * @return The new pair.
	 */
	public static <T> LongWith<T> of(long longVal, T element)
	{
		return new LongWith<>(longVal, element);
	}

	/**
	 * Retrieve the primitive long value contained in this pairing.
	 * 
	 * @return The primitive long element of this pair.
	 */
	public long _i()
	{
		return _l;
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
		return new StringBuilder("(").append(_l).append(", ").append(_o.toString()).append(")")
				.toString();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_o == null) ? 0 : _o.hashCode());
		result = prime * result + (int) (_l ^ (_l >>> 32));
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
		@SuppressWarnings("rawtypes")
		final LongWith other = (LongWith) obj;
		if (_o == null) {
			if (other._o != null)
				return false;
		} else if (!_o.equals(other._o))
			return false;
		if (_l != other._l)
			return false;
		return true;
	}
}
