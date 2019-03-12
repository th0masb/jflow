/**
 * 
 */
package com.github.maumay.jflow.vec;

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
}
