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
public final class DoubleAllMatchTest extends
        AbstractDoubleCollectionTest<Boolean>
{
    @Override
    protected Collector<Boolean> getCollectorToTest()
    {
        return iter -> iter.all(x -> x > 1);
    }

    @Override
    protected List<Case<Boolean>> getTestCases()
    {
        return list(new Case<>(list(), true),
                new Case<>(list(0.0, 1.0, 3.0), false),
                new Case<>(list(3.0, 2.0, 5.0), true),
                new Case<>(list(4.0, 2.0, 0.0), false));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list();
    }
}
