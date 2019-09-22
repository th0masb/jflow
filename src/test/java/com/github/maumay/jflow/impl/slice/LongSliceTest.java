/**
 *
 */
package com.github.maumay.jflow.impl.slice;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.test.AbstractLongAdapterTest;

import java.util.List;

import static java.lang.Math.max;

/**
 * @author thomasb
 *
 */
public final class LongSliceTest extends
        AbstractLongAdapterTest<AbstractLongIterator>
{
    @Override
    protected List<Case<AbstractLongIterator>> getTestCases()
    {
        List<Long> src = list(0L, 1L, 2L, 3L, 4L, 5L);
        return list(new Case<>(list(), i -> i.slice(n -> n), list()),
                new Case<>(src, i -> i.slice(n -> n), src),
                new Case<>(src, i -> i.slice(n -> n + 1),
                        list(1L, 2L, 3L, 4L, 5L)),
                new Case<>(src, i -> i.slice(n -> 2 * n), list(0L, 2L, 4L)),
                new Case<>(src, i -> i.slice(n -> max(0, 3 * n - 1)),
                        list(0L, 2L, 5L)));
    }
}
