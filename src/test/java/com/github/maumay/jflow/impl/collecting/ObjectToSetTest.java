/**
 * 
 */
package com.github.maumay.jflow.impl.collecting;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;

/**
 * @author thomasb
 *
 */
public class ObjectToSetTest extends AbstractObjectCollectionTest<Integer, Set<Integer>>
{
	@Override
	protected Collector<Integer, ? extends Set<Integer>> getCollectorToTest()
	{
		return iter -> iter.to(HashSet::new);
	}

	@Override
	protected List<Case<Integer, Set<Integer>>> getTestCases()
	{
		return list(new Case<>(list(), new HashSet<Integer>()),
				new Case<>(list(1, 2, 3), new HashSet<Integer>(list(1, 2, 3))));
	}

	@Override
	protected List<FailCase<Integer>> getFailureCases()
	{
		return list();
	}

}
