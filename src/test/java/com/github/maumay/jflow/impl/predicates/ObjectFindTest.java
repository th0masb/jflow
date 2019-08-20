/**
 * 
 */
package com.github.maumay.jflow.impl.predicates;

import java.util.List;
import java.util.NoSuchElementException;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;

/**
 * @author thomasb
 *
 */
public final class ObjectFindTest extends AbstractObjectCollectionTest<Integer, Integer>
{
	@Override
	protected Collector<Integer, ? extends Integer> getCollectorToTest()
	{
		return iter -> iter.find(n -> n > 2);
	}

	@Override
	protected List<Case<Integer, Integer>> getTestCases()
	{
		return list(new Case<>(list(1, 2, 3, 4), 3), new Case<>(list(3, 1), 3));
	}

	@Override
	protected List<FailCase<Integer>> getFailureCases()
	{
		return list(new FailCase<>(list(), NoSuchElementException.class),
				new FailCase<>(list(1, 1, 0), NoSuchElementException.class));
	}
}
