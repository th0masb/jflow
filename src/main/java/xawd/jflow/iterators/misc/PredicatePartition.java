package xawd.jflow.iterators.misc;

import java.util.Collections;
import java.util.List;

import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.factories.Iterate;

/**
 * @author ThomasB
 * @since 26 Apr 2018
 */
public final class PredicatePartition<E>
{
	private final List<E> acceptedElements, rejectedElements;

	public PredicatePartition(final List<E> acceptedElements, final List<E> rejectedElements)
	{
		this.acceptedElements = Collections.unmodifiableList(acceptedElements);
		this.rejectedElements = Collections.unmodifiableList(rejectedElements);
	}

	public List<E> getAcceptedElements()
	{
		return acceptedElements;
	}

	public Flow<E> iterateAccepted()
	{
		return Iterate.over(acceptedElements);
	}

	public List<E> getRejectedElements()
	{
		return rejectedElements;
	}

	public Flow<E> iterateRejected()
	{
		return Iterate.over(rejectedElements);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acceptedElements == null) ? 0 : acceptedElements.hashCode());
		result = prime * result + ((rejectedElements == null) ? 0 : rejectedElements.hashCode());
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
		final PredicatePartition<?> other = (PredicatePartition<?>) obj;
		if (acceptedElements == null) {
			if (other.acceptedElements != null)
				return false;
		} else if (!acceptedElements.equals(other.acceptedElements))
			return false;
		if (rejectedElements == null) {
			if (other.rejectedElements != null)
				return false;
		} else if (!rejectedElements.equals(other.rejectedElements))
			return false;
		return true;
	}
}
