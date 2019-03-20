/**
 * 
 */
package com.github.maumay.jflow.impl.skip;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractDoubleAdapterTest;

/**
 * @author t
 *
 */
public final class DoubleSkipTest
		extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<DoubleCase<AbstractDoubleIterator>> getTestCases()
	{
		List<Double> src = list(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		return list(new DoubleCase<>(list(), i -> i.skip(2), list()),
				new DoubleCase<>(src, i -> i.skip(0), src),
				new DoubleCase<>(src, i -> i.skip(3), list(0.0, 0.0, 0.0)),
				new DoubleCase<>(src, i -> i.skip(6), list()),
				new DoubleCase<>(src, i -> i.skip(7), list()));
	}
}
