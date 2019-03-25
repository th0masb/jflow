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
public final class IntMinOptionTest extends AbstractIntCollectionTest<OptionalInt>
{
	@Override
	protected Collector<OptionalInt> getCollectorToTest()
	{
		return iter -> iter.minOp();
	}

	@Override
	protected List<Case<OptionalInt>> getTestCases()
	{
		return list(new Case<>(list(), Option.emptyInt()), new Case<>(list(1), Option.of(1)),
				new Case<>(list(9, 1, 3, 2), Option.of(1)));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
