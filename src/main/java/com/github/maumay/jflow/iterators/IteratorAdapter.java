/**
 * 
 */
package com.github.maumay.jflow.iterators;

import com.github.maumay.jflow.impl.AbstractRichIterator;

/**
 * @author thomasb
 *
 */
@FunctionalInterface
public interface IteratorAdapter<E, R>
{
	AbstractRichIterator<R> adapt(AbstractRichIterator<? extends E> source);
}
