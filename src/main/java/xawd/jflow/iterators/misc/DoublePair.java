/**
 *
 */
package xawd.jflow.iterators.misc;

/**
 * @author t
 *
 */
public final class DoublePair
{
	private final double first, second;

	public DoublePair(final double first, final double second) {
		this.first = first;
		this.second = second;
	}

	public static DoublePair of(final double first, final double second)
	{
		return new DoublePair(first, second);
	}

	public double first() {
		return first;
	}

	public double second() {
		return second;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(")
				.append(first)
				.append(", ")
				.append(second)
				.append(")")
				.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(first);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(second);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		final DoublePair other = (DoublePair) obj;
		if (Double.doubleToLongBits(first) != Double.doubleToLongBits(other.first))
			return false;
		if (Double.doubleToLongBits(second) != Double.doubleToLongBits(other.second))
			return false;
		return true;
	}
}
