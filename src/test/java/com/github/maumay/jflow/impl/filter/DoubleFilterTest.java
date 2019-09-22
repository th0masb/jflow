/**
 *
 */
package com.github.maumay.jflow.impl.filter;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.test.AbstractDoubleAdapterTest;

import java.util.List;

/**
 * @author t
 *
 */
public final class DoubleFilterTest
        extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
    @Override
    protected List<Case<AbstractDoubleIterator>> getTestCases()
    {
        Adapter<AbstractDoubleIterator> adapter = iter -> iter
                .filter(x -> x > 1);
        return list(new Case<>(list(), adapter, list()),
                new Case<>(list(0.0, 2.0), adapter, list(2.0)),
                new Case<>(list(0.0, 1.0), adapter, list()),
                new Case<>(list(2.0, 3.0), adapter, list(2.0, 3.0)));
    }
}
