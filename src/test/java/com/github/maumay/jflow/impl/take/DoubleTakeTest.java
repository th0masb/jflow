/**
 * 
 */
package com.github.maumay.jflow.impl.take;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testframework.AbstractDoubleAdapterTest;

/**
 * @author t
 *
 */
public final class DoubleTakeTest
		extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<Case<AbstractDoubleIterator>> getTestCases()
	{
		List<Double> src = list(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		return list(new Case<>(list(), i -> i.take(0), list()),
				new Case<>(list(), i -> i.take(3), list()),
				new Case<>(src, i -> i.take(0), list()),
				new Case<>(src, i -> i.take(3), list(0.0, 0.0, 0.0)),
				new Case<>(src, i -> i.take(6), src));
	}
}
