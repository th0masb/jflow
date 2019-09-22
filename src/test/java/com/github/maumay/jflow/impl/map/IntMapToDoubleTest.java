/**
 *
 */
package com.github.maumay.jflow.impl.map;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.test.AbstractIntAdapterTest;

import java.util.List;

/**
 * @author t
 *
 */
public class IntMapToDoubleTest extends
        AbstractIntAdapterTest<AbstractDoubleIterator>
{
    @Override
    protected List<Case<AbstractDoubleIterator>> getTestCases()
    {
        Adapter<AbstractDoubleIterator> adapter = iter -> iter
                .mapToDouble(x -> 2 * x);
        return list(new Case<>(list(), adapter, list()),
                new Case<>(list(1, 2, 3), adapter, list(2.0, 4.0, 6.0)));
    }
}
