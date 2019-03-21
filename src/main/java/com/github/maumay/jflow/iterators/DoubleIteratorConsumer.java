/**
 * 
 */
package com.github.maumay.jflow.iterators;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;

/**
 * @author thomasb
 *
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
