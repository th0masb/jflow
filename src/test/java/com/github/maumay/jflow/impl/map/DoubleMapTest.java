/**
 * 
 */
package com.github.maumay.jflow.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testframework.AbstractDoubleAdapterTest;

/**
 * @author thomasb
 *
 */
public final class DoubleMapTest extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<DoubleCase<AbstractDoubleIterator>> getTestCases()
	{
		DoubleAdapter<AbstractDoubleIterator> adapter = iter -> iter.map(n -> 2 * n);
		return list(new DoubleCase<>(list(), adapter, list()),
				new DoubleCase<>(list(1.0, 2.0, 3.0), adapter, list(2.0, 4.0, 6.0)));
	}
}
