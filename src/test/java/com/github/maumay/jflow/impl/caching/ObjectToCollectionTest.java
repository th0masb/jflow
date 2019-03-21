/**
 * 
 */
package com.github.maumay.jflow.impl.caching;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;

/**
 * @author thomasb
 *
 */
public final class ObjectToCollectionTest
		extends AbstractObjectCollectionTest<Integer, Set<Integer>>
{
	@Override
	protected Collector<Integer, HashSet<Integer>> getCollectorToTest()
	{
		return iter -> iter.toCollection(HashSet::new);
	}

	@Override
	protected List<Case<Integer, Set<Integer>>> getTestCases()
	{
		return list(new Case<>(list(), set()), new Case<>(list(1, 2, 2, 3), set(1, 2, 3)));
	}

	private final Set<Integer> set(int... xs)
	{
		HashSet<Integer> result = new HashSet<>();
		for (int x : xs) {
			result.add(x);
		}
		return result;
	}

	@Override
	protected List<FailCase<Integer>> getFailureCases()
	{
		return list();
	}
}
