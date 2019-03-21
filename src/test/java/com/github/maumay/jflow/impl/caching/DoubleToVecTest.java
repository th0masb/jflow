/**
 * 
 */
package com.github.maumay.jflow.impl.caching;

import java.util.List;

import com.github.maumay.jflow.test.AbstractDoubleCollectionTest;
import com.github.maumay.jflow.vec.DoubleVec;

/**
 * @author thomasb
 *
 */
public final class DoubleToVecTest extends AbstractDoubleCollectionTest<DoubleVec>
{
	@Override
	protected Collector<DoubleVec> getCollectorToTest()
	{
		return iter -> iter.toVec();
	}

	@Override
	protected List<Case<DoubleVec>> getTestCases()
	{
		return list(new Case<>(list(), DoubleVec.empty()),
				new Case<>(list(0.0, 1.0, 2.0, 4.0, 2.0), DoubleVec.of(0.0, 1.0, 2.0, 4.0, 2.0)));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
