/**
 *
 */
package com.github.maumay.jflow.impl.map;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.test.AbstractObjectAdapterTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class ObjectMapToDoubleTest extends
        AbstractObjectAdapterTest<String, AbstractDoubleIterator>
{
    @Override
    protected List<Case<String, AbstractDoubleIterator>> getTestCases()
    {
        Adapter<String, AbstractDoubleIterator> adapter = iter -> iter
                .mapToDouble(Double::parseDouble);
        return list(new Case<>(list(), adapter, list()),
                new Case<>(list("0", "1"), adapter, list(0.0, 1.0)));
    }
}
