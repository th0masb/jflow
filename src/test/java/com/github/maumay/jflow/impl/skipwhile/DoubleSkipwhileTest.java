/**
 * 
 */
package com.github.maumay.jflow.impl.skipwhile;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractDoubleAdapterTest;

/**
 * @author t
 *
 */
public final class DoubleSkipwhileTest
		extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<DoubleCase<AbstractDoubleIterator>> getTestCases()
	{
		DoubleAdapter<AbstractDoubleIterator> adapter = i -> i.skipWhile(x -> x > 2);
		return list(new DoubleCase<>(list(), adapter, list()),
				new DoubleCase<>(list(1.0, 3.0, 0.0), adapter, list(1.0, 3.0, 0.0)),
				new DoubleCase<>(list(3.0, 1.0, 2.0), adapter, list(1.0, 2.0)),
				new DoubleCase<>(list(3.0, 3.0, 1.0), adapter, list(1.0)),
				new DoubleCase<>(list(3.0, 3.0, 3.0), adapter, list()));
	}
}
