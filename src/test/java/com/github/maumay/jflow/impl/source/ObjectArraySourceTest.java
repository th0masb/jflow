/**
 *
 */
package com.github.maumay.jflow.impl.source;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.impl.ArraySource;
import com.github.maumay.jflow.test.AbstractSourceTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class ObjectArraySourceTest extends
        AbstractSourceTest<AbstractRichIterator<Double>>
{
    @Override
    protected List<Case<AbstractRichIterator<Double>>> getTestCases()
    {
        return list(new Case<>(() -> new ArraySource.OfObject<>(), list()),
                new Case<>(() -> new ArraySource.OfObject<>(0.0, 1.0, 2.0),
                        list(0.0, 1.0, 2.0)),
                new Case<>(() -> new ArraySource.OfObjectReversed<>(), list()),
                new Case<>(
                        () -> new ArraySource.OfObjectReversed<>(0.0, 1.0, 2.0),
                        list(2.0, 1.0, 0.0)));
    }
}
