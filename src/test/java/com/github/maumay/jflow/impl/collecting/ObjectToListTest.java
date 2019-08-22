/**
 * 
 */
package com.github.maumay.jflow.impl.collecting;

import java.util.List;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;

/**
 * @author thomasb
 *
 */
public class ObjectToListTest extends AbstractObjectCollectionTest<Integer, List<Integer>>
{
	@Override
	protected Collector<Integer, ? extends List<Integer>> getCollectorToTest()
	{
		return iter -> iter.toList();
	}

	@Override
	protected List<Case<Integer, List<Integer>>> getTestCases()
	{
		return list(new Case<>(list(), list()), new Case<>(list(1, 2, 3), list(1, 2, 3)));
	}

	@Override
	protected List<FailCase<Integer>> getFailureCases()
	{
		return list();
	}

}
