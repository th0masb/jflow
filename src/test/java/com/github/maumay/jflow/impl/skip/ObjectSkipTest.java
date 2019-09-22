/**
 *
 */
package com.github.maumay.jflow.impl.skip;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractObjectAdapterTest;

import java.util.List;

/**
 * @author t
 *
 */
public final class ObjectSkipTest
        extends AbstractObjectAdapterTest<String, AbstractRichIterator<String>>
{
    @Override
    protected List<Case<String, AbstractRichIterator<String>>> getTestCases()
    {
        List<String> src = list("", "", "", "", "", "");
        return list(new Case<>(list(), i -> i.skip(2), list()),
                new Case<>(src, i -> i.skip(0), src),
                new Case<>(src, i -> i.skip(3), list("", "", "")),
                new Case<>(src, i -> i.skip(6), list()),
                new Case<>(src, i -> i.skip(7), list()));
    }
}
