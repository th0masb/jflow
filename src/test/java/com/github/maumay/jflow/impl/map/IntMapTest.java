/**
 *
 */
package com.github.maumay.jflow.impl.map;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.test.AbstractIntAdapterTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class IntMapTest extends
        AbstractIntAdapterTest<AbstractIntIterator>
{
    @Override
    protected List<Case<AbstractIntIterator>> getTestCases()
    {
        Adapter<AbstractIntIterator> adapter = iter -> iter.map(n -> 2 * n);
        return list(new Case<>(list(), adapter, list()),
                new Case<>(list(1, 2, 3), adapter, list(2, 4, 6)));
    }

}
