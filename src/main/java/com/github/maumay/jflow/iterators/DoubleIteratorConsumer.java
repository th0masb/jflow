/**
 * 
 */
package com.github.maumay.jflow.iterators;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;

/**
 * An object which terminates a double iterator data piping chain by performing
 * side effects and consuming the iterator in the process.
 * 
 * @author t
 */
@FunctionalInterface
public interface DoubleIteratorConsumer
{
	/**
	 * Consumes the argument by performing side effects using the elements.
	 * 
	 * @param source the iterator to consume.
	 */
	void consume(AbstractDoubleIterator source);
}
