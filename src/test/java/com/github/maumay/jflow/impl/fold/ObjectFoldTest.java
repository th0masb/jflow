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
public class ObjectFoldTest extends AbstractObjectCollectionTest<String, String>
{
    @Override
    protected Collector<String, ? extends String> getCollectorToTest()
    {
        return iter -> iter.fold((a, b) -> a + b);
    }

    @Override
    protected List<Case<String, String>> getTestCases()
    {
        return list(new Case<>(list("a", "b"), "ab"),
                new Case<>(list("a"), "a"));
    }

    @Override
    protected List<FailCase<String>> getFailureCases()
    {
        return list(new FailCase<>(list(), IllegalStateException.class));
    }
}
