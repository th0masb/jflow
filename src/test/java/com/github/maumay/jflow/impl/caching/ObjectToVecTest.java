/**
 *
 */
package com.github.maumay.jflow.impl.caching;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;
import com.github.maumay.jflow.vec.Vec;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class ObjectToVecTest extends
        AbstractObjectCollectionTest<Double, Vec<Double>>
{
    @Override
    protected Collector<Double, Vec<Double>> getCollectorToTest()
    {
        return iter -> iter.toVec();
    }

    @Override
    protected List<Case<Double, Vec<Double>>> getTestCases()
    {
        return list(new Case<>(list(), Vec.empty()),
                new Case<>(list(0.0, 1.0, 2.0, 4.0, 2.0),
                        vec(0.0, 1.0, 2.0, 4.0, 2.0)));
    }

    @Override
    protected List<FailCase<Double>> getFailureCases()
    {
        return list();
    }
}
