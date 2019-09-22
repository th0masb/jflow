/**
 *
 */
package com.github.maumay.jflow.test;

import com.github.maumay.jflow.impl.AbstractIterator;
import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.impl.IteratorOwnershipException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author thomasb
 *
 */
public abstract class AbstractObjectBiAdapterTest<T1, T2, I extends AbstractIterator>
        extends AbstractCollectionBuilder implements FiniteIteratorTest
{
    protected abstract List<Case<T1, T2, I>> getTestCases();

    @Test
    public final void testIterationBehaviour()
    {
        for (Case<T1, T2, I> testcase : getTestCases()) {
            List<TestIterable<I>> providers = IteratorProvider
                    .buildIterablesBi(testcase.sourceOne,
                            testcase.sourceTwo, testcase.adapter);

            assertIteratorAsExpected(testcase.result, providers);
        }
    }

    @Test
    public final void testOwnershipBehaviour()
    {
        for (Case<T1, T2, I> testcase : getTestCases()) {
            List<TestIterable<AbstractRichIterator<T1>>> providersOne = IteratorProvider
                    .buildIterables(testcase.sourceOne);

            List<TestIterable<AbstractRichIterator<T2>>> providersTwo = IteratorProvider
                    .buildIterables(testcase.sourceTwo);

            for (TestIterable<AbstractRichIterator<T1>> leftProvider : providersOne) {
                for (TestIterable<AbstractRichIterator<T2>> rightProvider : providersTwo) {
                    AbstractRichIterator<T1> left = leftProvider.iter();
                    AbstractRichIterator<T2> right = rightProvider.iter();

                    testcase.adapter.apply(left, right);
                    assertThrows(IteratorOwnershipException.class,
                            left::forward);
                    assertThrows(IteratorOwnershipException.class,
                            () -> next(left));

                    assertThrows(IteratorOwnershipException.class,
                            right::forward);
                    assertThrows(IteratorOwnershipException.class,
                            () -> next(right));
                }
            }
        }
    }

    @FunctionalInterface
    public static interface Adapter<T1, T2, I extends AbstractIterator>
            extends
            BiFunction<AbstractRichIterator<T1>, AbstractRichIterator<T2>, I>
    {
    }

    public static class Case<T1, T2, I extends AbstractIterator>
    {
        final List<T1> sourceOne;
        final List<T2> sourceTwo;
        final Adapter<T1, T2, I> adapter;
        final List<?> result;

        public Case(List<T1> sourceOne, List<T2> sourceTwo,
                Adapter<T1, T2, I> adapter,
                List<?> result)
        {
            this.sourceOne = sourceOne;
            this.sourceTwo = sourceTwo;
            this.result = result;
            this.adapter = adapter;
        }
    }
}
