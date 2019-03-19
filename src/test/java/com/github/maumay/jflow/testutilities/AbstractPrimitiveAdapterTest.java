/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import org.junit.jupiter.api.Test;

/**
 * @author thomasb
 *
 */
public abstract class AbstractPrimitiveAdapterTest
		implements IntAdapterTest, LongAdapterTest, DoubleAdapterTest
{
	@Test
	public final void testImpl()
	{
		testInts();
		testLongs();
		testDoubles();
	}
}
