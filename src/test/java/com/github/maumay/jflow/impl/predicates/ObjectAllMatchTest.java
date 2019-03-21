/**
 * 
 */
package com.github.maumay.jflow.impl.predicates;

import java.util.List;

import com.github.maumay.jflow.testframework.AbstractObjectCollectionTest;

/**
 * @author thomasb
 *
 */
public final class ObjectAllMatchTest extends AbstractObjectCollectionTest<Double, Boolean>
{
	@Override
	protected Collector<Double, Boolean> getCollectorToTest()
	{
		return iter -> iter.allMatch(x -> x > 1);
	}

	@Override
	protected List<Case<Double, Boolean>> getTestCases()
	{
		return list(new Case<>(list(), true), new Case<>(list(0.0, 1.0, 3.0), false),
				new Case<>(list(3.0, 2.0, 5.0), true), new Case<>(list(4.0, 2.0, 0.0), false));
	}

	@Override
	protected List<FailCase<Double>> getFailureCases()
	{
		return list();
	}
}
