/**
 *
 */
package com.github.maumay.jflow.impl.fold;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public class ObjectFoldWithInitializerTest extends
        AbstractObjectCollectionTest<String, String>
{
    @Override
    protected Collector<String, ? extends String> getCollectorToTest()
    {
        return iter -> iter.fold("c", (a, b) -> a + b);
    }

    @Override
    protected List<Case<String, String>> getTestCases()
    {
        return list(new Case<>(list("a", "b"), "cab"),
                new Case<>(list("a"), "ca"),
                new Case<>(list(), "c"));
    }

    @Override
    protected List<FailCase<String>> getFailureCases()
    {
        return list();
    }
}
