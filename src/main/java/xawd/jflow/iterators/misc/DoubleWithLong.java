/**
 *
 */
package xawd.jflow.iterators.misc;

/**
 * @author t
 *
 */
public final class DoubleWithLong
{
	private final double doubleVal;
	private final long longVal;

	public DoubleWithLong(final double doubleVal, final long longVal) {
		this.doubleVal = doubleVal;
		this.longVal = longVal;
	}

	public static DoubleWithLong of(final double doubleVal, final long longVal) {
		return new DoubleWithLong(doubleVal, longVal);
	}

	public double getDoubleVal() {
		return doubleVal;
	}

	public long getLongVal() {
		return longVal;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(")
				.append(doubleVal)
				.append(", ")
				.append(longVal)
				.append(")")
				.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(doubleVal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (longVal ^ (longVal >>> 32));
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
		final DoubleWithLong other = (DoubleWithLong) obj;
		if (Double.doubleToLongBits(doubleVal) != Double.doubleToLongBits(other.doubleVal))
			return false;
		if (longVal != other.longVal)
			return false;
		return true;
	}
}
