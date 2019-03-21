/**
 * 
 */
package com.github.maumay.jflow.impl.misc;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.test.AbstractDoubleAdapterTest;

/**
 * @author thomasb
 *
 */
public final class DoubleScanTest extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<Case<AbstractDoubleIterator>> getTestCases()
	{
		Adapter<AbstractDoubleIterator> adapter = iter -> iter.scan(0.0,
				(a, b) -> a + ((int) b));
		return list(new Case<>(list(), adapter, list(0.0)),
				new Case<>(list(1.0, 2.0, 3.0), adapter, list(0.0, 1.0, 3.0, 6.0)));
	}
}
