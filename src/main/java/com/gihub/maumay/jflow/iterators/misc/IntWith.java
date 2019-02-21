package com.gihub.maumay.jflow.iterators.misc;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public final class IntWith<T>
{
	public final int _i;
	public final T _o;

	private IntWith(int integer, T element)
	{
		this._i = integer;
		this._o = element;
	}

	public static <T> IntWith<T> of(int index, T element)
	{
		return new IntWith<>(index, element);
	}

	public int _i()
	{
		return _i;
	}

	public T _o()
	{
		return _o;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(").append(_i).append(", ").append(_o.toString())
				.append(")").toString();
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
		@SuppressWarnings("rawtypes")
		final IntWith other = (IntWith) obj;
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
