/**
 * 
 */
package com.github.maumay.jflow.impl.minmax;

import java.util.List;

import com.github.maumay.jflow.test.AbstractDoubleCollectionTest;

/**
 * @author thomasb
 *
 */
public final class DoubleMinTest extends AbstractDoubleCollectionTest<Double>
{
	@Override
	protected Collector<Double> getCollectorToTest()
	{
		return iter -> iter.min();
	}

	@Override
	protected List<Case<Double>> getTestCases()
	{
		return list(new Case<>(list(1.0), 1.0), new Case<>(list(9.0, 1.0, 3.0, 2.0), 1.0));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list(new FailCase(list(), IllegalStateException.class));
	}
}
