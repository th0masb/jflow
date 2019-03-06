/**
 * 
 */
package com.github.maumay.jflow.iterators.termination;

/**
 * @author t
 *
 */
@FunctionalInterface
public interface IteratorConsumer<E>
{
	void consume(Iterable<E> source);
}