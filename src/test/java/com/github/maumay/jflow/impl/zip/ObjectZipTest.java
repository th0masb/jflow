/**
 *
 */
package com.github.maumay.jflow.impl.zip;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractObjectBiAdapterTest;
import com.github.maumay.jflow.utils.Tup;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class ObjectZipTest extends
        AbstractObjectBiAdapterTest<Double, Double, AbstractRichIterator<Tup<Double, Double>>>
{
    @Override
    protected List<Case<Double, Double, AbstractRichIterator<Tup<Double, Double>>>> getTestCases()
    {
        Adapter<Double, Double, AbstractRichIterator<Tup<Double, Double>>> adapter = (i1, i2) -> i1
                .zip(i2);
        return list(new Case<>(list(), list(), adapter, list()),
                new Case<>(list(), list(1.0), adapter, list()),
                new Case<>(list(1.0), list(), adapter, list()),
                new Case<>(list(1.0), list(2.0), adapter, list(tup(1.0, 2.0))),
                new Case<>(list(1.0, 2.0), list(2.0), adapter,
                        list(tup(1.0, 2.0))),
                new Case<>(list(1.0), list(2.0, 3.0), adapter,
                        list(tup(1.0, 2.0))));
    }

    private <L, R> Tup<L, R> tup(L left, R right)
    {
        return Tup.of(left, right);
    }
}
