/**
 *
 */
package xawd.jflow.iterators.misc;

/**
 * @author t
 *
 */
public final class IntPair
{
	private final int first, second;

	public IntPair(final int first, final int second) {
		this.first = first;
		this.second = second;
	}

	public static IntPair of(final int first, final int second)
	{
		return new IntPair(first, second);
	}

	public int first() {
		return first;
	}

	public int second() {
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
		result = prime * result + first;
		result = prime * result + second;
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
		final IntPair other = (IntPair) obj;
		if (first != other.first)
			return false;
		if (second != other.second)
			return false;
		return true;
	}
}
