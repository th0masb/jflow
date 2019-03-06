/**
 * 
 */
package com.github.maumay.jflow.iterators.termination;

import java.util.Iterator;

/**
 * @author t
 *
 */
@FunctionalInterface
public interface IteratorCollector<E, R>
{
	R collect(Iterator<E> source);
}
