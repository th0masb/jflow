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
public final class IntMinByKeyTest extends AbstractIntCollectionTest<OptionalInt>
{
	@Override
	protected Collector<OptionalInt> getCollectorToTest()
	{
		List<Double> source = list(1.0, 2.0, 3.0);
		return iter -> iter.minByKey(source::get);
	}

	@Override
	protected List<Case<OptionalInt>> getTestCases()
	{
		return list(new Case<>(list(), Option.emptyInt()),
				new Case<>(list(2, 0, 2, 1), Option.of(0)));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list(new FailCase(list(5), IndexOutOfBoundsException.class));
	}
}
