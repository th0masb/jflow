/**
 *
 */
package com.github.maumay.jflow.impl.misc;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractObjectAdapterTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class ObjectScanTest
        extends AbstractObjectAdapterTest<String, AbstractRichIterator<String>>
{
    @Override
    protected List<Case<String, AbstractRichIterator<String>>> getTestCases()
    {
        Adapter<String, AbstractRichIterator<String>> adapter = iter -> iter
                .scan("",
                        (a, b) -> a + b);
        return list(new Case<>(list(), adapter, list("")),
                new Case<>(list("a", "b", "c"), adapter,
                        list("", "a", "ab", "abc")));
    }
}
