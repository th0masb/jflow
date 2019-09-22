/**
 *
 */
package com.github.maumay.jflow.impl.map;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.test.AbstractDoubleAdapterTest;

import java.util.List;

/**
 * @author t
 *
 */
public class DoubleMapToLongTest extends
        AbstractDoubleAdapterTest<AbstractLongIterator>
{
    @Override
    protected List<Case<AbstractLongIterator>> getTestCases()
    {
        Adapter<AbstractLongIterator> adapter = iter -> iter
                .mapToLong(x -> (int) (2 * x));
        return list(new Case<>(list(), adapter, list()),
                new Case<>(list(1.0, 2.0), adapter, list(2L, 4L)));
    }
}
