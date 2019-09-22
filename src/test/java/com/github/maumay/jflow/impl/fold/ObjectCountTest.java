/**
 *
 */
package com.github.maumay.jflow.impl.fold;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class ObjectCountTest extends
        AbstractObjectCollectionTest<Integer, Long>
{
    @Override
    protected Collector<Integer, ? extends Long> getCollectorToTest()
    {
        return iter -> iter.count();
    }

    @Override
    protected List<Case<Integer, Long>> getTestCases()
    {
        return list(new Case<>(list(), 0L), new Case<>(list(1, 2, 3), 3L));
    }

    @Override
    protected List<FailCase<Integer>> getFailureCases()
    {
        return list();
    }
}
