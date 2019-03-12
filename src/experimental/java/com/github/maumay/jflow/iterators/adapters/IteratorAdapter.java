/**
 * 
 */
package com.github.maumay.jflow.iterators.adapters;

import com.github.maumay.jflow.iterators.EnhancedIterator;

/**
 * @author thomasb
 *
 */
@FunctionalInterface
public interface IteratorAdapter<E, R>
{
	EnhancedIterator<R> adapt(EnhancedIterator<? extends E> input);
}
