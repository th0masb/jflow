/**
 *
 */
package com.github.maumay.jflow.impl.minmax;

import com.github.maumay.jflow.test.AbstractIntCollectionTest;
import com.github.maumay.jflow.utils.Option;

import java.util.List;
import java.util.OptionalInt;

/**
 * @author thomasb
 *
 */
public final class IntMaxByKeyTest extends
        AbstractIntCollectionTest<OptionalInt>
{
    @Override
    protected Collector<OptionalInt> getCollectorToTest()
    {
        List<Double> source = list(1.0, 2.0, 3.0);
        return iter -> iter.maxByKey(source::get);
    }

    @Override
    protected List<Case<OptionalInt>> getTestCases()
    {
        return list(new Case<>(list(), Option.emptyInt()),
                new Case<>(list(0, 2, 2, 1), Option.of(2)));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list(new FailCase(list(5), IndexOutOfBoundsException.class));
    }

}
