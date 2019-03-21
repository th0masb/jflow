/**
 * 
 */
package com.github.maumay.jflow.impl.minmax;

import java.util.List;

import com.github.maumay.jflow.testframework.AbstractLongCollectionTest;

/**
 * @author thomasb
 *
 */
public final class LongMaxTest extends AbstractLongCollectionTest<Long>
{
	@Override
	protected Collector<Long> getCollectorToTest()
	{
		return iter -> iter.max();
	}

	@Override
	protected List<Case<Long>> getTestCases()
	{
		return list(new Case<>(list(1L), 1L), new Case<>(list(9L, 1L, 3L, 2L), 9L));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list(new FailCase(list(), IllegalStateException.class));
	}
}
