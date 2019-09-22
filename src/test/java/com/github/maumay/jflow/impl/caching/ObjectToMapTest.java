/**
 *
 */
package com.github.maumay.jflow.impl.caching;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author thomasb
 *
 */
public final class ObjectToMapTest
        extends AbstractObjectCollectionTest<Integer, Map<Integer, Integer>>
{
    @Override
    protected Collector<Integer, Map<Integer, Integer>> getCollectorToTest()
    {
        return iter -> iter.toMap(n -> n, n -> 2 * n);
    }

    @Override
    protected List<Case<Integer, Map<Integer, Integer>>> getTestCases()
    {
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 2);
        expected.put(5, 10);
        expected.put(3, 6);
        return list(new Case<>(list(1, 3, 5), expected));
    }

    @Override
    protected List<FailCase<Integer>> getFailureCases()
    {
        return list(new FailCase<>(list(1, 2, 3, 5, 1, 3),
                IllegalStateException.class));
    }
}
