/**
 *
 */
package xawd.jflow.iterators.misc;

/**
 * @author t
 *
 */
public final class IntWithLong
{
	private final int intVal;
	private final long longVal;

	public IntWithLong(final int intVal, final long longVal) {
		this.intVal = intVal;
		this.longVal = longVal;
	}

	public static IntWithLong of(final int intVal, final long longVal) {
		return new IntWithLong(intVal, longVal);
	}

	public int getIntVal() {
		return intVal;
	}

	public long getLongVal() {
		return longVal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + intVal;
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
		final IntWithLong other = (IntWithLong) obj;
		if (intVal != other.intVal)
			return false;
		if (longVal != other.longVal)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(")
				.append(intVal)
				.append(", ")
				.append(longVal)
				.append(")")
				.toString();
	}
}
