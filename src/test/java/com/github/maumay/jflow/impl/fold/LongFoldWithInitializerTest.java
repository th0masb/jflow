/**
 * 
 */
package com.github.maumay.jflow.impl.fold;

import java.util.List;

import com.github.maumay.jflow.test.AbstractLongCollectionTest;

/**
 * @author thomasb
 *
 */
public final class LongFoldWithInitializerTest extends AbstractLongCollectionTest<Long>
{
	@Override
	protected Collector<Long> getCollectorToTest()
	{
		return iter -> iter.fold(1, (a, b) -> a + b);
	}

	@Override
	protected List<Case<Long>> getTestCases()
	{
		return list(new Case<>(list(1L, 3L, 4L), 9L), new Case<>(list(), 1L));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
