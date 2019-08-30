/**
 * 
 */
package com.github.maumay.jflow.impl.collecting;

import java.util.List;

import com.github.maumay.jflow.iterator.IterCollect;
import com.github.maumay.jflow.test.AbstractObjectCollectionTest;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 *
 */
public final class ObjectPackAllTest
		extends AbstractObjectCollectionTest<Integer, Vec<Vec<Integer>>>
{
	@Override
	protected Collector<Integer, ? extends Vec<Vec<Integer>>> getCollectorToTest()
	{
		return iter -> iter.collect(IterCollect.packAll(3));
	}

	@Override
	protected List<Case<Integer, Vec<Vec<Integer>>>> getTestCases()
	{
		return list(new Case<>(list(1, 2), vec(vec(1, 2))),
				new Case<>(list(1, 2, 3), vec(vec(1, 2, 3))),
				new Case<>(list(1, 2, 3, 4, 5), vec(vec(1, 2, 3), vec(4, 5))),
				new Case<>(list(1, 2, 3, 4, 5, 6, 7), vec(vec(1, 2, 3), vec(4, 5, 6), vec(7))));
	}

	@Override
	protected List<FailCase<Integer>> getFailureCases()
	{
		return list();
	}
}
