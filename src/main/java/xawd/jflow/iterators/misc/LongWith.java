package xawd.jflow.iterators.misc;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public final class LongWith<T>
{
	private final long longVal;
	private final T element;

	public LongWith(final long longVal, final T element)
	{
		this.longVal = longVal;
		this.element = element;
	}
	
	public static <T> LongWith<T> of(final long longVal, final T element)
	{
		return new LongWith<>(longVal, element);
	}

	public long getLongVal()
	{
		return longVal;
	}


	public T getElement()
	{
		return element;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(")
				.append(longVal)
				.append(", ")
				.append(element.toString())
				.append(")")
				.toString();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		result = prime * result + (int) (longVal ^ (longVal >>> 32));
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
		final
		LongWith other = (LongWith) obj;
		if (element == null) {
			if (other.element != null)
				return false;
		}
		else if (!element.equals(other.element))
			return false;
		if (longVal != other.longVal)
			return false;
		return true;
	}
}
