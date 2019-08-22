/**
 * 
 */
package com.github.maumay.jflow.impl.collecting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;

/**
 * @author thomasb
 *
 */
public final class ObjectGroupByTest
		extends AbstractObjectCollectionTest<Integer, Map<Boolean, List<Integer>>>
{
	@Override
	protected Collector<Integer, ? extends Map<Boolean, List<Integer>>> getCollectorToTest()
	{
		return iter -> iter.groupBy(n -> (n % 2) == 0);
	}

	@Override
	protected List<Case<Integer, Map<Boolean, List<Integer>>>> getTestCases()
	{
		Map<Boolean, List<Integer>> expected = new HashMap<>();
		expected.put(Boolean.TRUE, list(0, 2, 4));
		expected.put(Boolean.FALSE, list(1, 3, 5));
		return list(new Case<>(list(0, 1, 2, 3, 4, 5), expected));
	}

	@Override
	protected List<FailCase<Integer>> getFailureCases()
	{
		return list();
	}
}
