/**
 * 
 */
package com.github.maumay.jflow.impl.fold;

import java.util.List;

import com.github.maumay.jflow.test.AbstractDoubleCollectionTest;

/**
 * @author thomasb
 *
 */
public final class DoubleFoldWithInitializerTest extends AbstractDoubleCollectionTest<Double>
{
	@Override
	protected Collector<Double> getCollectorToTest()
	{
		return iter -> iter.fold(1, (a, b) -> a + b);
	}

	@Override
	protected List<Case<Double>> getTestCases()
	{
		return list(new Case<>(list(1.0, 3.0, 4.0), 9.0), new Case<>(list(), 1.0));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
