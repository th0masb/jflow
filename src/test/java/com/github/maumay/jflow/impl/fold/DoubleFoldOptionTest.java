/**
 * 
 */
package com.github.maumay.jflow.impl.fold;

import java.util.List;
import java.util.OptionalDouble;

import com.github.maumay.jflow.test.AbstractDoubleCollectionTest;

/**
 * @author thomasb
 *
 */
public final class DoubleFoldOptionTest extends AbstractDoubleCollectionTest<OptionalDouble>
{
	@Override
	protected Collector<OptionalDouble> getCollectorToTest()
	{
		return iter -> iter.foldOp((a, b) -> a + b);
	}

	@Override
	protected List<Case<OptionalDouble>> getTestCases()
	{
		return list(new Case<>(list(1.0, 3.0, 4.0), OptionalDouble.of(8)),
				new Case<>(list(), OptionalDouble.empty()));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
