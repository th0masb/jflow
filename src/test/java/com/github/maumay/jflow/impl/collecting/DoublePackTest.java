/**
 *
 */
package com.github.maumay.jflow.impl.collecting;

import com.github.maumay.jflow.iterator.collector.IterCollect;
import com.github.maumay.jflow.test.AbstractDoubleCollectionTest;
import com.github.maumay.jflow.vec.DoubleVec;
import com.github.maumay.jflow.vec.Vec;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class DoublePackTest extends
        AbstractDoubleCollectionTest<Vec<DoubleVec>>
{
    @Override
    protected Collector<Vec<DoubleVec>> getCollectorToTest()
    {
        return iter -> iter.collect(IterCollect.packDoubles(3));
    }

    @Override
    protected List<Case<Vec<DoubleVec>>> getTestCases()
    {
        return list(new Case<>(list(1.0, 2.0), vec()),
                new Case<>(list(1.0, 2.0, 3.0), vec(dvec(1, 2, 3))),
                new Case<>(list(1.0, 2.0, 3.0, 4.0, 5.0), vec(dvec(1, 2, 3))),
                new Case<>(list(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0),
                        vec(dvec(1, 2, 3), dvec(4, 5, 6))));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list();
    }
}
