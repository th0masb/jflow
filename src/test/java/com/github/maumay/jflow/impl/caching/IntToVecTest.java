/**
 * 
 */
package com.github.maumay.jflow.impl.caching;

import java.util.List;

import com.github.maumay.jflow.test.AbstractIntCollectionTest;
import com.github.maumay.jflow.vec.IntVec;

/**
 * @author thomasb
 *
 */
public final class IntToVecTest extends AbstractIntCollectionTest<IntVec>
{
	@Override
	protected Collector<IntVec> getCollectorToTest()
	{
		return iter -> iter.toVec();
	}

	@Override
	protected List<Case<IntVec>> getTestCases()
	{
		return list(new Case<>(list(), IntVec.empty()),
				new Case<>(list(0, 1, 2, 4, 2), IntVec.of(0, 1, 2, 4, 2)));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
