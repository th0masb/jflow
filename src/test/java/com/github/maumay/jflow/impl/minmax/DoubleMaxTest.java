/**
 *
 */
package com.github.maumay.jflow.impl.minmax;

import com.github.maumay.jflow.test.AbstractDoubleCollectionTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class DoubleMaxTest extends AbstractDoubleCollectionTest<Double>
{
    @Override
    protected Collector<Double> getCollectorToTest()
    {
        return iter -> iter.max();
    }

    @Override
    protected List<Case<Double>> getTestCases()
    {
        return list(new Case<>(list(1.0), 1.0),
                new Case<>(list(9.0, 1.0, 3.0, 2.0), 9.0),
                new Case<>(list(1.0, 9.0, 10.0), 10.0),
                new Case<>(list(1.0, Double.NaN), 1.0));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list(new FailCase(list(), IllegalStateException.class),
                new FailCase(list(Double.NaN), IllegalStateException.class));
    }
}
