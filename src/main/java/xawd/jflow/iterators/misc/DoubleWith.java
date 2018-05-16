package xawd.jflow.iterators.misc;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public final class DoubleWith<T>
{
	private final double doubleVal;
	private final T element;

	private DoubleWith(final double doubleval, final T element)
	{
		this.doubleVal = doubleval;
		this.element = element;
	}

	public static <T> DoubleWith<T> of(final double doubleVal, final T element)
	{
		return new DoubleWith<>(doubleVal, element);
	}

	public double getDouble()
	{
		return doubleVal;
	}

	public T getElement()
	{
		return element;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(")
				.append(doubleVal)
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
		long temp;
		temp = Double.doubleToLongBits(doubleVal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((element == null) ? 0 : element.hashCode());
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
		final DoubleWith other = (DoubleWith) obj;
		if (Double.doubleToLongBits(doubleVal) != Double.doubleToLongBits(other.doubleVal))
			return false;
		if (element == null) {
			if (other.element != null)
				return false;
		}
		else if (!element.equals(other.element))
			return false;
		return true;
	}
}
