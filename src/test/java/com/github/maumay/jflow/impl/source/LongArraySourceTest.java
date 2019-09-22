/**
 *
 */
package com.github.maumay.jflow.impl.source;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.impl.ArraySource;
import com.github.maumay.jflow.test.AbstractSourceTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class LongArraySourceTest extends
        AbstractSourceTest<AbstractLongIterator>
{
    @Override
    protected List<Case<AbstractLongIterator>> getTestCases()
    {
        return list(new Case<>(() -> new ArraySource.OfLong(), list()),
                new Case<>(() -> new ArraySource.OfLong(0L, 1L, 2L),
                        list(0L, 1L, 2L)),
                new Case<>(() -> new ArraySource.OfLongReversed(), list()),
                new Case<>(() -> new ArraySource.OfLongReversed(0L, 1L, 2L),
                        list(2L, 1L, 0L)));
    }
}
