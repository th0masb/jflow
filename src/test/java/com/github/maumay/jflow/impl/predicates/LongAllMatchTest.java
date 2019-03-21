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
public final class LongAllMatchTest extends AbstractLongCollectionTest<Boolean>
{
	@Override
	protected Collector<Boolean> getCollectorToTest()
	{
		return iter -> iter.allMatch(x -> x > 1);
	}

	@Override
	protected List<Case<Boolean>> getTestCases()
	{
		return list(new Case<>(list(), true), new Case<>(list(0L, 1L, 3L), false),
				new Case<>(list(3L, 2L, 5L), true), new Case<>(list(4L, 2L, 0L), false));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
