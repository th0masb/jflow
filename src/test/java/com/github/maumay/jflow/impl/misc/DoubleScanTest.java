/**
 * 
 */
package com.github.maumay.jflow.impl.misc;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractDoubleAdapterTest;

/**
 * @author thomasb
 *
 */
public final class DoubleScanTest extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<DoubleCase<AbstractDoubleIterator>> getTestCases()
	{
		DoubleAdapter<AbstractDoubleIterator> adapter = iter -> iter.scan(0.0,
				(a, b) -> a + ((int) b));
		return list(new DoubleCase<>(list(), adapter, list(0.0)),
				new DoubleCase<>(list(1.0, 2.0, 3.0), adapter, list(0.0, 1.0, 3.0, 6.0)));
	}
}
