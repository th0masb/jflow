/**
 *
 */
package com.github.maumay.jflow.impl.source;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.impl.ApplicationSource;
import com.github.maumay.jflow.test.AbstractSourceTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class ApplicationSourceTest extends
        AbstractSourceTest<AbstractRichIterator<Integer>>
{
    @Override
    protected List<Case<AbstractRichIterator<Integer>>> getTestCases()
    {
        return list(new Case<>(
                () -> new ApplicationSource.OfObject<>(n -> 2 * n, 1).take(3),
                list(1, 2, 4)));
    }
}
