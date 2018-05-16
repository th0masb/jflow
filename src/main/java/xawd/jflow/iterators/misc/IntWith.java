package xawd.jflow.iterators.misc;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public final class IntWith<T>
{
	private final int intVal;
	private final T element;

	private IntWith(final int integer, final T element)
	{
		this.intVal = integer;
		this.element = element;
	}

	public static <T> IntWith<T> of(final int index, final T element)
	{
		return new IntWith<>(index, element);
	}

	public int getInt()
	{
		return intVal;
	}

	public T getElement()
	{
		return element;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(")
				.append(intVal)
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
		result = prime * result + intVal;
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
		if (element == null) {
			if (other.element != null)
				return false;
		}
		else if (!element.equals(other.element))
			return false;
		if (intVal != other.intVal)
			return false;
		return true;
	}
}
