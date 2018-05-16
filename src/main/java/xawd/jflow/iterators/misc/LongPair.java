/**
 *
 */
package xawd.jflow.iterators.misc;

/**
 * @author t
 *
 */
public final class LongPair
{
	private final long first, second;

	public LongPair(final long first, final long second) {
		this.first = first;
		this.second = second;
	}

	public static LongPair of(final long first, final long second) {
		return new LongPair(first, second);
	}

	public long getFirst() {
		return first;
	}

	public long getSecond() {
		return second;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (first ^ (first >>> 32));
		result = prime * result + (int) (second ^ (second >>> 32));
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
		final LongPair other = (LongPair) obj;
		if (first != other.first)
			return false;
		if (second != other.second)
			return false;
		return true;
	}
}
