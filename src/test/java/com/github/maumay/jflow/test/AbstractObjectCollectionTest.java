/**
 *
 */
package com.github.maumay.jflow.test;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author thomasb
 */
public abstract class AbstractObjectCollectionTest<E, R> extends
        AbstractCollectionBuilder
{
    protected abstract Collector<E, ? extends R> getCollectorToTest();

    protected abstract List<Case<E, R>> getTestCases();

    protected abstract List<FailCase<E>> getFailureCases();

    @Test
    public final void testPassCases()
    {
        Collector<E, ? extends R> collector = getCollectorToTest();
        for (Case<E, R> testCase : getTestCases()) {
            List<TestIterable<AbstractRichIterator<E>>> iteratorProviders = IteratorProvider
                    .buildIterables(testCase.source);
            for (TestIterable<AbstractRichIterator<E>> provider : iteratorProviders) {
                AbstractRichIterator<E> iterator = provider.iter();

                // Make sure we have the expected result.
                assertEquals(testCase.expectedResult,
                        collector.apply(iterator));

                // Make sure ownership was taken away by the collector
                assertFalse(iterator.hasOwnership());
            }
        }
    }

    @Test
    public final void testFailureCases()
    {
        Collector<E, ? extends R> collector = getCollectorToTest();
        for (FailCase<E> testCase : getFailureCases()) {
            List<TestIterable<AbstractRichIterator<E>>> iteratorProviders = IteratorProvider
                    .buildIterables(testCase.source);
            for (TestIterable<AbstractRichIterator<E>> provider : iteratorProviders) {
                AbstractRichIterator<E> iterator = provider.iter();
                assertThrows(testCase.expectedFailType,
                        () -> collector.apply(iterator));
            }
        }
    }

    @FunctionalInterface
    public static interface Collector<E, R> extends
            Function<AbstractRichIterator<E>, R>
    {
    }

    public static class Case<E, R>
    {
        final List<E> source;
        final R expectedResult;

        public Case(List<E> source, R expectedResult)
        {
            this.source = source;
            this.expectedResult = expectedResult;
        }
    }

    public static class FailCase<E>
    {
        final List<E> source;
        final Class<? extends RuntimeException> expectedFailType;

        public FailCase(List<E> source,
                Class<? extends RuntimeException> expectedFailType)
        {
            this.source = source;
            this.expectedFailType = expectedFailType;
        }
    }
}
