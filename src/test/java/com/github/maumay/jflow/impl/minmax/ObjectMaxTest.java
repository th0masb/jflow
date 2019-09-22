/**
 *
 */
package com.github.maumay.jflow.impl.minmax;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;

import java.util.Comparator;
import java.util.List;

/**
 * @author thomasb
 *
 */
public final class ObjectMaxTest extends
        AbstractObjectCollectionTest<Double, Double>
{
    @Override
    protected Collector<Double, Double> getCollectorToTest()
    {
        return iter -> iter.max(Comparator.naturalOrder());
    }

    @Override
    protected List<Case<Double, Double>> getTestCases()
    {
        return list(new Case<>(list(1.0), 1.0),
                new Case<>(list(9.0, 1.0, 3.0, 2.0), 9.0));
    }

    @Override
    protected List<FailCase<Double>> getFailureCases()
    {
        return list(new FailCase<>(list(), IllegalStateException.class));
    }
}
