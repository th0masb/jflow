/**
 * 
 */
package com.github.maumay.jflow.iterator.collector;

import com.github.maumay.jflow.iterator.DoubleIterator;

/**
 * An object which can terminate an iterator data piping chain by aggregating
 * the elements to create a new instance of a given type.
 * 
 * @param <R> the result type of the aggregation operation.
 * @author t
 */
@FunctionalInterface
public interface DoubleIteratorCollector<R>
{
	/**
	 * Consumes the argument iterator by aggregating the elements to create a new
	 * instance of the specified type.
	 * 
	 * @param source The iterator to collect and consume.
	 * @return The result of the collection operation.
	 */
	R collect(DoubleIterator source);
}
