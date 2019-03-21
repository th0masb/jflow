/**
 * 
 */
package com.github.maumay.jflow.impl.predicates;

import java.util.List;

import com.github.maumay.jflow.testframework.AbstractDoubleCollectionTest;

/**
 * @author thomasb
 *
 */
public class DoubleAnyMatchTest extends AbstractDoubleCollectionTest<Boolean>
{
	@Override
	protected Collector<Boolean> getCollectorToTest()
	{
		return iter -> iter.anyMatch(x -> x > 1);
	}

	@Override
	protected List<Case<Boolean>> getTestCases()
	{
		return list(new Case<>(list(), false), new Case<>(list(1.0, 0.0), false),
				new Case<>(list(0.0, 1.0, 3.0), true));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
