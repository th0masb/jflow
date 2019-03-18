/**
 * 
 */
package com.github.maumay.jflow.iterators.custom;

import com.github.maumay.jflow.impl.AbstractRichIterator;

/**
 * An object which can terminate an iterator data piping chain by aggregating
 * the elements (and hence consuming the iterator) to create a new instance of a
 * given type.
 * 
 * @param <E> The element type of the iterator to be collected.
 * @param <R> the result type of the aggregation operation.
 * @author t
 */
@FunctionalInterface
public interface IteratorCollector<E, R>
{
	/**
	 * Consumes the argument iterator by aggregating the elements to create a new
	 * instance of the specified type.
	 * 
	 * @param source The iterator to collect and consume.
	 * @return The result of the collection operation.
	 */
	R collect(AbstractRichIterator<? extends E> source);
}
