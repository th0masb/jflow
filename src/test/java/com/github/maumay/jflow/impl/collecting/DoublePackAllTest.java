/**
 * 
 */
package com.github.maumay.jflow.impl.collecting;

import java.util.List;

import com.github.maumay.jflow.iterator.IterCollect;
import com.github.maumay.jflow.test.AbstractDoubleCollectionTest;
import com.github.maumay.jflow.vec.DoubleVec;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 *
 */
public final class DoublePackAllTest extends AbstractDoubleCollectionTest<Vec<DoubleVec>>
{
	@Override
	protected Collector<Vec<DoubleVec>> getCollectorToTest()
	{
		return iter -> iter.collect(IterCollect.packAllDoubles(3));
	}

	@Override
	protected List<Case<Vec<DoubleVec>>> getTestCases()
	{
		return list(new Case<>(list(1.0, 2.0), vec(dvec(1, 2))),
				new Case<>(list(1.0, 2.0, 3.0), vec(dvec(1, 2, 3))),
				new Case<>(list(1.0, 2.0, 3.0, 4.0, 5.0), vec(dvec(1, 2, 3), dvec(4, 5))),
				new Case<>(list(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0),
						vec(dvec(1, 2, 3), dvec(4, 5, 6), dvec(7))));
	}

	@Override
	protected List<FailCase> getFailureCases()
	{
		return list();
	}
}
