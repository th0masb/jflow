/**
 *
 */
package com.github.maumay.jflow.impl.collecting;

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
    protected Collector<Integer, ? extends Map<Integer, Integer>> getCollectorToTest()
    {
        return iter -> iter.toMap(n -> n, n -> n * n);
    }

    @Override
    protected List<Case<Integer, Map<Integer, Integer>>> getTestCases()
    {
        return list(new Case<>(list(1, 2), caseOneResult()));
    }

    private Map<Integer, Integer> caseOneResult()
    {
        Map<Integer, Integer> result = new HashMap<>();
        result.put(1, 1);
        result.put(2, 4);
        return result;
    }

    @Override
    protected List<FailCase<Integer>> getFailureCases()
    {
        return list(new FailCase<>(list(1, 1), IllegalStateException.class));
    }
}
