/**
 * 
 */
package com.github.maumay.jflow.iterators;

import java.util.Iterator;

/**
 * @author t
 *
 */
@FunctionalInterface
public interface IteratorCollector<E, R>
{
	R collect(Iterator<E> iterator);
}
