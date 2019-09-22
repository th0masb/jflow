/**
 *
 */
package com.github.maumay.jflow.impl.source;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.impl.CollectionSource;
import com.github.maumay.jflow.test.AbstractSourceTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class CollectionSourceTest extends
        AbstractSourceTest<AbstractRichIterator<Integer>>
{
    @Override
    protected List<Case<AbstractRichIterator<Integer>>> getTestCases()
    {
        return list(
                new Case<>(() -> new CollectionSource<Integer>(list()), list()),
                new Case<>(() -> new CollectionSource<>(list(1, 3, 2)),
                        list(1, 3, 2)));
    }
}
