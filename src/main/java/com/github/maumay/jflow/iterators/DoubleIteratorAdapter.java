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
public interface DoubleIteratorAdapter
{
	DoubleIterator adapt(AbstractDoubleIterator source);
}
