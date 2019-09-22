/**
 *
 */
package com.github.maumay.jflow.impl.collecting;

import com.github.maumay.jflow.iterator.collector.IterCollect;
import com.github.maumay.jflow.test.AbstractObjectCollectionTest;
import com.github.maumay.jflow.vec.Vec;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class ObjectPackTest extends
        AbstractObjectCollectionTest<Integer, Vec<Vec<Integer>>>
{
    @Override
    protected Collector<Integer, ? extends Vec<Vec<Integer>>> getCollectorToTest()
    {
        return iter -> iter.collect(IterCollect.pack(3));
    }

    @Override
    protected List<Case<Integer, Vec<Vec<Integer>>>> getTestCases()
    {
        return list(new Case<>(list(1, 2), vec()),
                new Case<>(list(1, 2, 3), vec(vec(1, 2, 3))),
                new Case<>(list(1, 2, 3, 4, 5), vec(vec(1, 2, 3))),
                new Case<>(list(1, 2, 3, 4, 5, 6, 7),
                        vec(vec(1, 2, 3), vec(4, 5, 6))));
    }

    @Override
    protected List<FailCase<Integer>> getFailureCases()
    {
        return list();
    }
}
