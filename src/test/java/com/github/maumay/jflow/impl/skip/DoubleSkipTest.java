/**
 *
 */
package com.github.maumay.jflow.impl.skip;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.test.AbstractDoubleAdapterTest;

import java.util.List;

/**
 * @author t
 *
 */
public final class DoubleSkipTest
        extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
    @Override
    protected List<Case<AbstractDoubleIterator>> getTestCases()
    {
        List<Double> src = list(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        return list(new Case<>(list(), i -> i.skip(2), list()),
                new Case<>(src, i -> i.skip(0), src),
                new Case<>(src, i -> i.skip(3), list(0.0, 0.0, 0.0)),
                new Case<>(src, i -> i.skip(6), list()),
                new Case<>(src, i -> i.skip(7), list()));
    }
}
