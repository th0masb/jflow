/**
 *
 */
package com.github.maumay.jflow.impl.zip;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractLongAdapterTest;
import com.github.maumay.jflow.utils.LongTup;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class LongZipTest
        extends AbstractLongAdapterTest<AbstractRichIterator<LongTup>>
{
    @Override
    protected List<Case<AbstractRichIterator<LongTup>>> getTestCases()
    {

        return list(new Case<>(list(), i -> i.zip(iter(list())), list()),
                new Case<>(list(), i -> i.zip(iter(list(1L))), list()),
                new Case<>(list(1L), i -> i.zip(iter(list())), list()),
                new Case<>(list(1L), i -> i.zip(iter(list(2L))),
                        list(tup(1, 2))),
                new Case<>(list(1L, 2L), i -> i.zip(iter(list(2L))),
                        list(tup(1, 2))),
                new Case<>(list(1L), i -> i.zip(iter(list(2L, 3L))),
                        list(tup(1, 2))));
    }

    private LongTup tup(long left, long right)
    {
        return LongTup.of(left, right);
    }
}
