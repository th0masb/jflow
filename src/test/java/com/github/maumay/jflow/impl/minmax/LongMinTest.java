/**
 *
 */
package com.github.maumay.jflow.impl.minmax;

import com.github.maumay.jflow.test.AbstractLongCollectionTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class LongMinTest extends AbstractLongCollectionTest<Long>
{
    @Override
    protected Collector<Long> getCollectorToTest()
    {
        return iter -> iter.min();
    }

    @Override
    protected List<Case<Long>> getTestCases()
    {
        return list(new Case<>(list(1L), 1L),
                new Case<>(list(9L, 1L, 3L, 2L), 1L));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list(new FailCase(list(), IllegalStateException.class));
    }
}
