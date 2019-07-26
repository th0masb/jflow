/**
 * 
 */
package com.github.maumay.jflow.impl.predicates;

import java.util.List;

import com.github.maumay.jflow.test.AbstractDoubleCollectionTest;

/**
 * @author thomasb
 *
 */
public class DoubleAllEqualTest extends AbstractDoubleCollectionTest<Boolean>
{

	@Override
	protected Collector<Boolean> getCollectorToTest()
	{
		return iter -> iter.areAllEqual();
	}

	@Override
	protected List<Case<Boolean>> getTestCases()
	{
		return list(new Case<>(list(1.0, 2.0), false), new Case<>(list(1.0, 1.0), true),
				new Case<>(list(1.0), true), new Case<>(list(), true),
				new Case<>(list(Double.NaN, Double.NaN, 2.0), false),
				new Case<>(list(Double.NaN, Double.NaN), true),
				new Case<>(list(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY), true));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}

}
