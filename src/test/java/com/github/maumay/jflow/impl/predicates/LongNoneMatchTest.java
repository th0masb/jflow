/**
 * 
 */
package com.github.maumay.jflow.impl.predicates;

import java.util.List;

import com.github.maumay.jflow.test.AbstractLongCollectionTest;

/**
 * @author thomasb
 *
 */
public class LongNoneMatchTest extends AbstractLongCollectionTest<Boolean>
{
	@Override
	protected Collector<Boolean> getCollectorToTest()
	{
		return iter -> iter.noneMatch(x -> x > 1);
	}

	@Override
	protected List<Case<Boolean>> getTestCases()
	{
		return list(new Case<>(list(), true), new Case<>(list(0L, 2L, 1L), false),
				new Case<>(list(0L, 1L, 1L), true));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
