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
public class FlatmapTest extends
        AbstractObjectAdapterTest<Integer, AbstractRichIterator<String>>
{
    @Override
    protected List<Case<Integer, AbstractRichIterator<String>>> getTestCases()
    {
        List<List<String>> src = list(list(), list(""));
        Adapter<Integer, AbstractRichIterator<String>> adapter = iter -> iter
                .flatMap(n -> src.get(n).iterator());
        return list(new Case<>(list(), adapter, list()),
                new Case<>(list(0, 0), adapter, list()),
                new Case<>(list(0, 1), adapter, list("")),
                new Case<>(list(1, 0), adapter, list("")),
                new Case<>(list(0, 1, 1, 0), adapter, list("", "")),
                new Case<>(list(0, 1, 0, 1, 0), adapter, list("", "")),
                new Case<>(list(1, 0, 0, 0, 1), adapter, list("", "")));
    }
}
