/**
 *
 */
package com.github.maumay.jflow.impl.fold;

import com.github.maumay.jflow.test.AbstractLongCollectionTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class LongCountTest extends AbstractLongCollectionTest<Long>
{
    @Override
    protected Collector<Long> getCollectorToTest()
    {
        return iter -> iter.count();
    }

    @Override
    protected List<Case<Long>> getTestCases()
    {
        return list(new Case<>(list(), 0L), new Case<>(list(1L, 2L, 3L), 3L));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list();
    }
}
