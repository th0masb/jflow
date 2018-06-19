/**
 *
 */
package xawd.jflow.collections;

import java.util.Iterator;
import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.iterables.FlowIterable;

/**
 * @author ThomasB
 */
public interface FlowList<E> extends List<E>, FlowIterable<E>
{
	default Flow<E> slice(final IntUnaryOperator indexMap)
	{
		return flow().slice(indexMap);
	}

	default Flow<E> take(final int n)
	{
		return flow().take(n);
	}

	default Flow<E> takeWhile(final Predicate<? super E> predicate)
	{
		return flow().takeWhile(predicate);
	}

	default Flow<E> drop(final int n)
	{
		return flow().drop(n);
	}

	default Flow<E> dropWhile(final Predicate<? super E> predicate)
	{
		return flow().dropWhile(predicate);
	}

	default Flow<E> insert(Iterator<? extends E> other)
	{
		return flow().insert(other);
	}

	default Flow<E> insert(Iterable<? extends E> other)
	{
		return flow().insert(other.iterator());
	}

	default Flow<E> insert(E other)
	{
		return flow().insert(other);
	}
}
