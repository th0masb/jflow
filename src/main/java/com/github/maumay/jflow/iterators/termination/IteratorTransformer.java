/**
 * 
 */
package com.github.maumay.jflow.iterators.termination;

/**
 * @author t
 *
 */
@FunctionalInterface
public interface IteratorTransformer<E, R>
{
	R transform(Iterable<E> source);
}
