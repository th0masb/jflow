/**
 * 
 */
package com.github.maumay.jflow.test;

import com.github.maumay.jflow.impl.AbstractIterator;

/**
 * @author thomasb
 *
 */
@FunctionalInterface
public interface TestIterable<I extends AbstractIterator>
{
	I iter();
}
