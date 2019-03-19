/**
 * 
 */
package com.github.maumay.jflow.impl.takewhile;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractDoubleAdapterTest;

/**
 * @author t
 *
 */
public final class DoubleTakewhileTest
		extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<DoubleCase<AbstractDoubleIterator>> getTestCases()
	{
		DoubleAdapter<AbstractDoubleIterator> adapter = i -> i.takeWhile(x -> x > 3);
		return list(new DoubleCase<>(list(), adapter, list()),
				new DoubleCase<>(list(3.0, 1.0, 5.0), adapter, list()),
				new DoubleCase<>(list(4.0, 4.0, 2.0, 4.0), adapter, list(4.0, 4.0)),
				new DoubleCase<>(list(4.0, 4.0, 1.0), adapter, list(4.0, 4.0)),
				new DoubleCase<>(list(4.0, 4.0, 4.0), adapter, list(4.0, 4.0, 4.0)));
	}
}
