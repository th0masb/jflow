/**
 * 
 */
package com.github.maumay.jflow.impl.slice;

import static java.lang.Math.max;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractDoubleAdapterTest;

/**
 * @author thomasb
 *
 */
public final class DoubleSliceTest extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<DoubleCase<AbstractDoubleIterator>> getTestCases()
	{
		List<Double> src = list(0.0, 1.0, 2.0, 3.0, 4.0, 5.0);
		return list(new DoubleCase<>(list(), i -> i.slice(n -> n), list()),
				new DoubleCase<>(src, i -> i.slice(n -> n), src),
				new DoubleCase<>(src, i -> i.slice(n -> n + 1), list(1.0, 2.0, 3.0, 4.0, 5.0)),
				new DoubleCase<>(src, i -> i.slice(n -> 2 * n), list(0.0, 2.0, 4.0)),
				new DoubleCase<>(src, i -> i.slice(n -> max(0, 3 * n - 1)), list(0.0, 2.0, 5.0)));
	}
}
