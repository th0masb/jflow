/**
 *
 */
package xawd.jflow.iterators.misc;

import java.util.Arrays;

/**
 * @author ThomasB
 *
 */
public final class LongPredicatePartition
{
	private final long[] acceptedElements, rejectedElements;

	public LongPredicatePartition(final long[] acceptedElements, final long[] rejectedElements)
	{
		this.acceptedElements = acceptedElements;
		this.rejectedElements = rejectedElements;
	}

	public long[] getAccepted()
	{
		return acceptedElements;
	}

	public long[] getRejected()
	{
		return rejectedElements;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(acceptedElements);
		result = prime * result + Arrays.hashCode(rejectedElements);
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
		final LongPredicatePartition other = (LongPredicatePartition) obj;
		if (!Arrays.equals(acceptedElements, other.acceptedElements))
			return false;
		if (!Arrays.equals(rejectedElements, other.rejectedElements))
			return false;
		return true;
	}
}
