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
public final class ObjectGroupByTest
        extends
        AbstractObjectCollectionTest<Integer, Map<Integer, List<Integer>>>
{
    @Override
    protected Collector<Integer, Map<Integer, List<Integer>>> getCollectorToTest()
    {
        return iter -> iter.groupBy(n -> n % 2);
    }

    @Override
    protected List<Case<Integer, Map<Integer, List<Integer>>>> getTestCases()
    {
        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(0, list(2, 4, 6));
        expected.put(1, list(1, 9));
        return list(new Case<>(list(2, 1, 4, 6, 9), expected));
    }

    @Override
    protected List<FailCase<Integer>> getFailureCases()
    {
        return list();
    }
}
