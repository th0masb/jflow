/**
 *
 */
package com.github.maumay.jflow.impl.source;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.impl.ArraySource;
import com.github.maumay.jflow.test.AbstractSourceTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class IntArraySourceTest extends
        AbstractSourceTest<AbstractIntIterator>
{
    @Override
    protected List<Case<AbstractIntIterator>> getTestCases()
    {
        return list(new Case<>(() -> new ArraySource.OfInt(), list()),
                new Case<>(() -> new ArraySource.OfIntReversed(), list()),
                new Case<>(() -> new ArraySource.OfInt(0, 1, 2), list(0, 1, 2)),
                new Case<>(() -> new ArraySource.OfIntReversed(0, 1, 2),
                        list(2, 1, 0)));
    }
}
