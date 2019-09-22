/**
 *
 */
package com.github.maumay.jflow.impl.map;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.test.AbstractObjectAdapterTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class ObjectMapToLongTest extends
        AbstractObjectAdapterTest<String, AbstractLongIterator>
{
    @Override
    protected List<Case<String, AbstractLongIterator>> getTestCases()
    {
        Adapter<String, AbstractLongIterator> adapter = iter -> iter
                .mapToLong(Long::parseLong);
        return list(new Case<>(list(), adapter, list()),
                new Case<>(list("0", "1"), adapter, list(0L, 1L)));
    }
}
