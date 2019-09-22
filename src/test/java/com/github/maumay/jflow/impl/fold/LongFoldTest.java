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
public final class LongFoldTest extends AbstractLongCollectionTest<Long>
{
    @Override
    protected Collector<Long> getCollectorToTest()
    {
        return iter -> iter.fold((a, b) -> a + b);
    }

    @Override
    protected List<Case<Long>> getTestCases()
    {
        return list(new Case<>(list(1L, 3L, 4L), 8L));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list(new FailCase(list(), IllegalStateException.class));
    }

}
