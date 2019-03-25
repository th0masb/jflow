/**
 * 
 */
package com.github.maumay.jflow.vec;

import java.util.Optional;

/**
 * @author ThomasB
 *
 */
public interface Indexable<E>
{
	int size();

	E get(int index);

	default boolean isEmpty()
	{
		return size() == 0;
	}

	default boolean isPopulated()
	{
		return !isEmpty();
	}

	/**
	 * Safely attempts to retrieve the element at a given index.
	 * 
	 * @param index the index to search at.
	 * @return an optional wrapping the element if the index is valid, nothing
	 *         otherwise.
	 */
	default Optional<E> getOp(int index)
	{
		return -1 < index && index < size() ? Optional.of(get(index)) : Optional.empty();
	}

	/**
	 * Gets the head (first element) of this vector in an unsafe manner. If this
	 * vector is empty then an exception will be thrown.
	 * 
	 * @return the head of this vector.
	 */
	default E head()
	{
		return get(0);
	}

	/**
	 * Gets the head (first element) of this vector in an safe manner.
	 * 
	 * @return an optional wrapping the head of this vector if is is non-empty,
	 *         nothing otherwise.
	 */
	default Optional<E> headOp()
	{
		return getOp(0);
	}

	/**
	 * Gets the last element of this vector in an unsafe manner. If this vector is
	 * empty then an exception will be thrown.
	 * 
	 * @return the last element of this vector.
	 */
	default E last()
	{
		return get(size() - 1);
	}

	/**
	 * Gets the last element of this vector in an safe manner.
	 * 
	 * @return the last element of this vector if it is non-empty, nothing
	 *         otherwise.
	 */
	default Optional<E> lastOp()
	{
		return getOp(size() - 1);
	}
}
