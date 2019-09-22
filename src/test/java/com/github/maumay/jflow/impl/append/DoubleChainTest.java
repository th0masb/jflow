/**
 *
 */
package com.github.maumay.jflow.impl.append;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.test.AbstractDoubleAdapterTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class DoubleChainTest extends
        AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
    @Override
    protected List<Case<AbstractDoubleIterator>> getTestCases()
    {
        List<Double> empty = list(), populated = list(0.0);
        return list(new Case<>(empty, i -> i.chain(iter(empty)), empty),
                new Case<>(empty, i -> i.chain(iter(populated)), populated),
                new Case<>(populated, i -> i.chain(iter(empty)), populated),
                new Case<>(populated, i -> i.chain(iter(list(1.0))),
                        list(0.0, 1.0)));
    }
}
