/**
 * 
 */
package com.github.maumay.jflow.iterators.custom;

import com.github.maumay.jflow.impl.AbstractEnhancedIterator;

/**
 * @author thomasb
 *
 */
@FunctionalInterface
public interface IteratorAdapter<E, R>
{
	AbstractEnhancedIterator<R> adapt(AbstractEnhancedIterator<? extends E> source);
}
