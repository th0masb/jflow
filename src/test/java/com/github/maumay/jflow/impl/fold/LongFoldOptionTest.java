/**
 * 
 */
package com.github.maumay.jflow.impl.fold;

import java.util.List;
import java.util.OptionalLong;

import com.github.maumay.jflow.test.AbstractLongCollectionTest;

/**
 * @author thomasb
 *
 */
public final class LongFoldOptionTest extends AbstractLongCollectionTest<OptionalLong>
{
	@Override
	protected Collector<OptionalLong> getCollectorToTest()
	{
		return iter -> iter.foldOp((a, b) -> a + b);
	}

	@Override
	protected List<Case<OptionalLong>> getTestCases()
	{
		return list(new Case<>(list(1L, 3L, 4L), OptionalLong.of(8)),
				new Case<>(list(), OptionalLong.empty()));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
