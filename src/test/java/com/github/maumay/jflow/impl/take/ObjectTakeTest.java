/**
 *
 */
package com.github.maumay.jflow.impl.take;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractObjectAdapterTest;

import java.util.List;

/**
 * @author t
 *
 */
public final class ObjectTakeTest
        extends AbstractObjectAdapterTest<String, AbstractRichIterator<String>>
{
    @Override
    protected List<Case<String, AbstractRichIterator<String>>> getTestCases()
    {
        List<String> src = list("", "", "", "", "", "");
        return list(new Case<>(list(), i -> i.take(0), list()),
                new Case<>(list(), i -> i.take(3), list()),
                new Case<>(src, i -> i.take(0), list()),
                new Case<>(src, i -> i.take(3), list("", "", "")),
                new Case<>(src, i -> i.take(6), src));
    }
}
