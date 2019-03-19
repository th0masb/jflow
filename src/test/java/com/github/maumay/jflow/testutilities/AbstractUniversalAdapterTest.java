/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import org.junit.jupiter.api.Test;

/**
 * @author thomasb
 *
 */
public abstract class AbstractUniversalAdapterTest<T>
		implements AdapterTest<T>, IntAdapterTest, LongAdapterTest, DoubleAdapterTest, ListBuilder
{
	@Test
	public final void testImpl()
	{
		test();
		testInts();
		testLongs();
		testDoubles();
	}
}
