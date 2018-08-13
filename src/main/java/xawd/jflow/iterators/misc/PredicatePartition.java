package xawd.jflow.iterators.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import xawd.jflow.collections.FList;
import xawd.jflow.collections.Lists;
import xawd.jflow.iterators.Flow;

/**
 * Represents result of partitioning some sequence of elements based on whether
 * they pass or fail a given predicate.
 *
 * @author ThomasB
 * @since 26 Apr 2018
 */
public final class PredicatePartition<E>
{
	private final FList<E> acceptedElements, rejectedElements;

	public PredicatePartition(Flow<E> source, Predicate<? super E> predicate)
	{
		final List<E> accepted = new ArrayList<>(), rejected = new ArrayList<>();
		while (source.hasNext()) {
			final E next = source.next();
			if (predicate.test(next)) {
				accepted.add(next);
			}
			else {
				rejected.add(next);
			}
		}
		this.acceptedElements = Lists.copy(accepted);
		this.rejectedElements = Lists.copy(rejected);
	}

	public PredicatePartition(List<? extends E> accepted, List<? extends E> rejected)
	{
		this.acceptedElements = Lists.copy(accepted);
		this.rejectedElements = Lists.copy(rejected);
	}

	public FList<E> getAccepted()
	{
		return acceptedElements;
	}

	public FList<E> getRejected()
	{
		return rejectedElements;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acceptedElements == null) ? 0 : acceptedElements.hashCode());
		result = prime * result + ((rejectedElements == null) ? 0 : rejectedElements.hashCode());
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
