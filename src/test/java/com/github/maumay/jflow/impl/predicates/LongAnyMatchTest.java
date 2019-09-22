/**
 *
 */
package com.github.maumay.jflow.impl.predicates;

import com.github.maumay.jflow.test.AbstractLongCollectionTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public class LongAnyMatchTest extends AbstractLongCollectionTest<Boolean>
{
    @Override
    protected Collector<Boolean> getCollectorToTest()
    {
        return iter -> iter.any(x -> x > 1);
    }

    @Override
    protected List<Case<Boolean>> getTestCases()
    {
        return list(new Case<>(list(), false), new Case<>(list(1L, 0L), false),
                new Case<>(list(0L, 1L, 3L), true));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list();
    }
}
