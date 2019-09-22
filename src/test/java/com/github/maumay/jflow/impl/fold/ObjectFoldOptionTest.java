/**
 *
 */
package com.github.maumay.jflow.impl.fold;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;

import java.util.List;
import java.util.Optional;

/**
 * @author thomasb
 *
 */
public class ObjectFoldOptionTest extends
        AbstractObjectCollectionTest<String, Optional<String>>
{
    @Override
    protected Collector<String, ? extends Optional<String>> getCollectorToTest()
    {
        return iter -> iter.foldOp((a, b) -> a + b);
    }

    @Override
    protected List<Case<String, Optional<String>>> getTestCases()
    {
        return list(new Case<>(list("a", "b"), Optional.of("ab")),
                new Case<>(list("a"), Optional.of("a")),
                new Case<>(list(), Optional.empty()));
    }

    @Override
    protected List<FailCase<String>> getFailureCases()
    {
        return list();
    }
}
