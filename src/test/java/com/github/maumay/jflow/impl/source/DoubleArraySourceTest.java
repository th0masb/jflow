/**
 *
 */
package com.github.maumay.jflow.impl.source;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.impl.ArraySource;
import com.github.maumay.jflow.test.AbstractSourceTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class DoubleArraySourceTest extends
        AbstractSourceTest<AbstractDoubleIterator>
{
    @Override
    protected List<Case<AbstractDoubleIterator>> getTestCases()
    {
        return list(new Case<>(() -> new ArraySource.OfDouble(), list()),
                new Case<>(() -> new ArraySource.OfDouble(0.0, 1.0, 2.0),
                        list(0.0, 1.0, 2.0)),
                new Case<>(() -> new ArraySource.OfDoubleReversed(), list()),
                new Case<>(
                        () -> new ArraySource.OfDoubleReversed(0.0, 1.0, 2.0),
                        list(2.0, 1.0, 0.0)));
    }
}
