/**
 * 
 */
package com.github.maumay.jflow.impl.minmax;

import java.util.List;

import com.github.maumay.jflow.testframework.AbstractDoubleCollectionTest;

/**
 * @author thomasb
 *
 */
public final class DoubleMaxTest extends AbstractDoubleCollectionTest<Double>
{
	@Override
	protected Collector<Double> getCollectorToTest()
	{
		return iter -> iter.max();
	}

	@Override
	protected List<Case<Double>> getTestCases()
	{
		return list(new Case<>(list(1.0), 1.0), new Case<>(list(9.0, 1.0, 3.0, 2.0), 9.0));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list(new FailCase(list(), IllegalStateException.class));
	}
}
