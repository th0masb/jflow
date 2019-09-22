/**
 *
 */
package com.github.maumay.jflow.impl.predicates;

import com.github.maumay.jflow.test.AbstractIntCollectionTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public class IntAnyMatchTest extends AbstractIntCollectionTest<Boolean>
{
    @Override
    protected Collector<Boolean> getCollectorToTest()
    {
        return iter -> iter.any(x -> x > 1);
    }

    @Override
    protected List<Case<Boolean>> getTestCases()
    {
        return list(new Case<>(list(), false), new Case<>(list(1, 0), false),
                new Case<>(list(0, 1, 3), true));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list();
    }
}
