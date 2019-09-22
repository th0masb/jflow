/**
 *
 */
package com.github.maumay.jflow.impl.fold;

import com.github.maumay.jflow.test.AbstractDoubleCollectionTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class DoubleCountTest extends AbstractDoubleCollectionTest<Long>
{
    @Override
    protected Collector<Long> getCollectorToTest()
    {
        return iter -> iter.count();
    }

    @Override
    protected List<Case<Long>> getTestCases()
    {
        return list(new Case<>(list(), 0L),
                new Case<>(list(1.0, 2.0, 3.0), 3L));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list();
    }
}
