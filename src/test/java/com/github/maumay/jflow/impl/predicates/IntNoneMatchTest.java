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
public class IntNoneMatchTest extends AbstractIntCollectionTest<Boolean>
{
    @Override
    protected Collector<Boolean> getCollectorToTest()
    {
        return iter -> iter.none(x -> x > 1);
    }

    @Override
    protected List<Case<Boolean>> getTestCases()
    {
        return list(new Case<>(list(), true), new Case<>(list(0, 2, 1), false),
                new Case<>(list(0, 1, 1), true));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list();
    }
}
