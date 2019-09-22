/**
 *
 */
package com.github.maumay.jflow.impl.minmax;

import com.github.maumay.jflow.test.AbstractIntCollectionTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class IntMaxTest extends AbstractIntCollectionTest<Integer>
{
    @Override
    protected Collector<Integer> getCollectorToTest()
    {
        return iter -> iter.max();
    }

    @Override
    protected List<Case<Integer>> getTestCases()
    {
        return list(new Case<>(list(1), 1), new Case<>(list(1, 9, 3, 2), 9));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list(new FailCase(list(), IllegalStateException.class));
    }
}
