/**
 *
 */
package com.github.maumay.jflow.impl.predicates;

import com.github.maumay.jflow.test.AbstractDoubleCollectionTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public class DoubleNoneMatchTest extends AbstractDoubleCollectionTest<Boolean>
{
    @Override
    protected Collector<Boolean> getCollectorToTest()
    {
        return iter -> iter.none(x -> x > 1);
    }

    @Override
    protected List<Case<Boolean>> getTestCases()
    {
        return list(new Case<>(list(), true),
                new Case<>(list(0.0, 2.0, 1.0), false),
                new Case<>(list(0.0, 1.0, 1.0), true));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list();
    }
}
