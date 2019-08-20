/**
 * 
 */
package com.github.maumay.jflow.impl.predicates;

import java.util.List;
import java.util.Optional;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;

/**
 * @author thomasb
 *
 */
public final class ObjectFindOpTest
		extends AbstractObjectCollectionTest<Integer, Optional<Integer>>
{
	@Override
	protected Collector<Integer, ? extends Optional<Integer>> getCollectorToTest()
	{
		return iter -> iter.findOp(n -> n > 2);
	}

	@Override
	protected List<Case<Integer, Optional<Integer>>> getTestCases()
	{
		return list(new Case<>(list(1, 2, 3, 4), Optional.of(3)),
				new Case<>(list(3, 1), Optional.of(3)),
				new Case<>(list(), Optional.empty()),
				new Case<>(list(1, 1, 0), Optional.empty()));
	}

	@Override
	protected List<FailCase<Integer>> getFailureCases()
	{
		return list();
	}
}
