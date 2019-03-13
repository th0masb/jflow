/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

import com.github.maumay.jflow.iterators.size.AbstractIteratorSize;

/**
 * @author thomasb
 *
 */
public abstract class AbstractSourceIterator<E> extends AbstractEnhancedIterator<E>
{
	public AbstractSourceIterator(AbstractIteratorSize size)
	{
		super(size);
	}
}
