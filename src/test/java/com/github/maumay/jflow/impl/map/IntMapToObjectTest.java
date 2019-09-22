/**
 *
 */
package com.github.maumay.jflow.impl.map;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractIntAdapterTest;

import java.util.List;

/**
 * @author t
 *
 */
public final class IntMapToObjectTest
        extends AbstractIntAdapterTest<AbstractRichIterator<String>>
{
    @Override
    protected List<Case<AbstractRichIterator<String>>> getTestCases()
    {
        Adapter<AbstractRichIterator<String>> adapter = iter -> iter
                .mapToObj(x -> "" + x);
        return list(new Case<>(list(), adapter, list()),
                new Case<>(list(1, 2, 3, 4), adapter,
                        list("1", "2", "3", "4")));
    }
}
