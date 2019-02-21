package com.gihub.maumay.jflow.iterators.misc;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public final class LongWith<T>
{
	public final long _L;
	public final T _o;

	public LongWith(long longVal, T element)
	{
		this._L = longVal;
		this._o = element;
	}

	public static <T> LongWith<T> of(long longVal, T element)
	{
		return new LongWith<>(longVal, element);
	}

	public long _L()
	{
		return _L;
	}

	public T _o()
	{
		return _o;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(").append(_L).append(", ").append(_o.toString())
				.append(")").toString();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_o == null) ? 0 : _o.hashCode());
		result = prime * result + (int) (_L ^ (_L >>> 32));
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
		if (_L != other._L)
			return false;
		return true;
	}
}
