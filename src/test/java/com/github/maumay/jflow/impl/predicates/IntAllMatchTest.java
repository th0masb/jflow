/**
 * 
 */
package com.github.maumay.jflow.impl.predicates;

import java.util.List;

import com.github.maumay.jflow.testframework.AbstractIntCollectionTest;

/**
 * @author thomasb
 *
 */
public final class IntAllMatchTest extends AbstractIntCollectionTest<Boolean>
{
	@Override
	protected Collector<Boolean> getCollectorToTest()
	{
		return iter -> iter.allMatch(x -> x > 1);
	}

	@Override
	protected List<Case<Boolean>> getTestCases()
	{
		return list(new Case<>(list(), true), new Case<>(list(0, 1, 3), false),
				new Case<>(list(3, 2, 5), true), new Case<>(list(4, 2, 0), false));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
