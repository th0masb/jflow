/**
 * 
 */
package com.github.maumay.jflow.impl.filter;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testframework.AbstractDoubleAdapterTest;

/**
 * @author t
 *
 */
public final class DoubleFilterTest
		extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<DoubleCase<AbstractDoubleIterator>> getTestCases()
	{
		DoubleAdapter<AbstractDoubleIterator> adapter = iter -> iter.filter(x -> x > 1);
		return list(new DoubleCase<>(list(), adapter, list()),
				new DoubleCase<>(list(0.0, 2.0), adapter, list(2.0)),
				new DoubleCase<>(list(0.0, 1.0), adapter, list()),
				new DoubleCase<>(list(2.0, 3.0), adapter, list(2.0, 3.0)));
	}
}
