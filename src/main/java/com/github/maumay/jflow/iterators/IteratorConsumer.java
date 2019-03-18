/**
 * 
 */
package com.github.maumay.jflow.iterators;

import com.github.maumay.jflow.impl.AbstractRichIterator;

/**
 * An object which terminates an appropriate iterator data piping chain by
 * performing side effects and consuming the iterator in the process.
 * 
 * @param <E> The element type of the iterator to be consumed.
 * @author t
 */
@FunctionalInterface
public interface IteratorConsumer<E>
{
	/**
	 * Consumes the argument iterator by performing side effects using the elements.
	 * 
	 * @param source the iterator to collect and consume.
	 */
	void consume(AbstractRichIterator<? extends E> source);
}