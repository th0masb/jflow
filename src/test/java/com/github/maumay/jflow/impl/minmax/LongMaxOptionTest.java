/**
 * 
 */
package com.github.maumay.jflow.impl.minmax;

import java.util.List;
import java.util.OptionalLong;

import com.github.maumay.jflow.test.AbstractLongCollectionTest;
import com.github.maumay.jflow.utils.Option;

/**
 * @author thomasb
 *
 */
public final class LongMaxOptionTest extends AbstractLongCollectionTest<OptionalLong>
{
	@Override
	protected Collector<OptionalLong> getCollectorToTest()
	{
		return iter -> iter.maxOp();
	}

	@Override
	protected List<Case<OptionalLong>> getTestCases()
	{
		return list(new Case<>(list(), Option.emptyLong()), new Case<>(list(1L), Option.of(1L)),
				new Case<>(list(9L, 1L, 3L, 2L), Option.of(9L)),
				new Case<>(list(1L, 9L, 3L, 2L), Option.of(9L)));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
