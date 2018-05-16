/**
 *
 */
package xawd.jflow.iterators.misc;

/**
 * @author t
 *
 */
public final class IntWithDouble
{
	private final int intVal;
	private final double doubleVal;

	public IntWithDouble(final int intVal, final double doubleVal)
	{
		this.intVal = intVal;
		this.doubleVal = doubleVal;
	}

	public static IntWithDouble of(final int intVal, final double doubleVal)
	{
		return new IntWithDouble(intVal, doubleVal);
	}

	public int getIntVal()
	{
		return intVal;
	}
	public double getDoubleVal()
	{
		return doubleVal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(doubleVal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + intVal;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final IntWithDouble other = (IntWithDouble) obj;
		if (Double.doubleToLongBits(doubleVal) != Double.doubleToLongBits(other.doubleVal))
			return false;
		if (intVal != other.intVal)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(")
				.append(intVal)
				.append(", ")
				.append(doubleVal)
				.append(")")
				.toString();
	}
}
