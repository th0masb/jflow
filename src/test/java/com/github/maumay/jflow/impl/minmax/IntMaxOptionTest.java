/**
 * 
 */
package com.github.maumay.jflow.impl.minmax;

import java.util.List;
import java.util.OptionalInt;

import com.github.maumay.jflow.test.AbstractIntCollectionTest;
import com.github.maumay.jflow.utils.Option;

/**
 * @author thomasb
 *
 */
public final class IntMaxOptionTest extends AbstractIntCollectionTest<OptionalInt>
{
	@Override
	protected Collector<OptionalInt> getCollectorToTest()
	{
		return iter -> iter.maxOp();
	}

	@Override
	protected List<Case<OptionalInt>> getTestCases()
	{
		return list(new Case<>(list(), Option.emptyInt()), new Case<>(list(1), Option.of(1)),
				new Case<>(list(1, 9, 3, 2), Option.of(9)));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
