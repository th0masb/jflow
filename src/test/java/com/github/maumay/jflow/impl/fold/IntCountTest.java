/**
 * 
 */
package com.github.maumay.jflow.impl.fold;

import java.util.List;

import com.github.maumay.jflow.test.AbstractIntCollectionTest;

/**
 * @author thomasb
 *
 */
public final class IntCountTest extends AbstractIntCollectionTest<Long>
{
	@Override
	protected Collector<Long> getCollectorToTest()
	{
		return iter -> iter.count();
	}

	@Override
	protected List<Case<Long>> getTestCases()
	{
		return list(new Case<>(list(), 0L), new Case<>(list(1, 2, 3), 3L));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
