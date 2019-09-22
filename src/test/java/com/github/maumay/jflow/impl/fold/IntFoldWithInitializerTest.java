/**
 *
 */
package com.github.maumay.jflow.impl.fold;

import com.github.maumay.jflow.test.AbstractIntCollectionTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class IntFoldWithInitializerTest extends
        AbstractIntCollectionTest<Integer>
{
    @Override
    protected Collector<Integer> getCollectorToTest()
    {
        return iter -> iter.fold(1, (a, b) -> a + b);
    }

    @Override
    protected List<Case<Integer>> getTestCases()
    {
        return list(new Case<>(list(1, 3, 4), 9), new Case<>(list(), 1));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list();
    }

}
