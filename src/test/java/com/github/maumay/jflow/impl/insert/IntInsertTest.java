/**
 *
 */
package com.github.maumay.jflow.impl.insert;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.test.AbstractIntAdapterTest;

import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * @author thomasb
 *
 */
public final class IntInsertTest extends
        AbstractIntAdapterTest<AbstractIntIterator>
{
    @Override
    protected List<Case<AbstractIntIterator>> getTestCases()
    {
        List<Integer> empty = list(), populated = list(0);
        return list(new Case<>(empty, i -> i.rchain(unbox(empty.iterator())),
                        empty),
                new Case<>(empty, i -> i.rchain(unbox(populated.iterator())),
                        populated),
                new Case<>(populated, i -> i.rchain(unbox(empty.iterator())),
                        populated),
                new Case<>(populated, i -> i.rchain(unbox(list(1).iterator())),
                        list(1, 0)));
    }

    private PrimitiveIterator.OfInt unbox(Iterator<Integer> source)
    {
        return new PrimitiveIterator.OfInt()
        {
            @Override
            public boolean hasNext()
            {
                return source.hasNext();
            }

            @Override
            public int nextInt()
            {
                return source.next();
            }
        };
    }
}
