/**
 * 
 */
package com.github.maumay.jflow.impl.fold;

import java.util.List;
import java.util.OptionalInt;

import com.github.maumay.jflow.test.AbstractIntCollectionTest;

/**
 * @author thomasb
 *
 */
public final class IntFoldOptionTest extends AbstractIntCollectionTest<OptionalInt>
{
	@Override
	protected Collector<OptionalInt> getCollectorToTest()
	{
		return iter -> iter.foldOp((a, b) -> a + b);
	}

	@Override
	protected List<Case<OptionalInt>> getTestCases()
	{
		return list(new Case<>(list(1, 3, 4), OptionalInt.of(8)),
				new Case<>(list(), OptionalInt.empty()));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
