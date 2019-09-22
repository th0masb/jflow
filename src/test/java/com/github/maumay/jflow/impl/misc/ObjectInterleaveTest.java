/**
 *
 */
package com.github.maumay.jflow.impl.misc;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractObjectBiAdapterTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class ObjectInterleaveTest
        extends
        AbstractObjectBiAdapterTest<Double, Double, AbstractRichIterator<Double>>
{
    @Override
    protected List<Case<Double, Double, AbstractRichIterator<Double>>> getTestCases()
    {
        Adapter<Double, Double, AbstractRichIterator<Double>> interleave = (i1, i2) -> i1
                .interleave(i2);
        return list(new Case<>(list(), list(), interleave, list()),
                new Case<>(list(1.0), list(), interleave, list(1.0)),
                new Case<>(list(), list(1.0), interleave, list()),
                new Case<>(list(5.0, 4.0, 6.0, 8.0, 9.0), list(1.0, 2.0, 3.0),
                        interleave,
                        list(5.0, 1.0, 4.0, 2.0, 6.0, 3.0, 8.0)));
    }
}
