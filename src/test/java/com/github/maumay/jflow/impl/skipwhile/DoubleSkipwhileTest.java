/**
 *
 */
package com.github.maumay.jflow.impl.skipwhile;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.test.AbstractDoubleAdapterTest;

import java.util.List;

/**
 * @author t
 *
 */
public final class DoubleSkipwhileTest
        extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
    @Override
    protected List<Case<AbstractDoubleIterator>> getTestCases()
    {
        Adapter<AbstractDoubleIterator> adapter = i -> i.skipWhile(x -> x > 2);
        return list(new Case<>(list(), adapter, list()),
                new Case<>(list(1.0, 3.0, 0.0), adapter, list(1.0, 3.0, 0.0)),
                new Case<>(list(3.0, 1.0, 2.0), adapter, list(1.0, 2.0)),
                new Case<>(list(3.0, 3.0, 1.0), adapter, list(1.0)),
                new Case<>(list(3.0, 3.0, 3.0), adapter, list()));
    }
}
