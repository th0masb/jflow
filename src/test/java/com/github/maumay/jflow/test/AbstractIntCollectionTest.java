/**
 *
 */
package com.github.maumay.jflow.test;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author thomasb
 *
 */
public abstract class AbstractIntCollectionTest<R> extends
        AbstractCollectionBuilder
{
    protected abstract Collector<R> getCollectorToTest();

    protected abstract List<Case<R>> getTestCases();

    protected abstract List<FailCase> getFailureCases();

    @Test
    public final void test()
    {
        Collector<R> collector = getCollectorToTest();
        for (Case<R> testCase : getTestCases()) {
            List<TestIterable<AbstractIntIterator>> iteratorProviders = IteratorProvider
                    .buildIntIterables(testCase.source);
            for (TestIterable<AbstractIntIterator> provider : iteratorProviders) {
                AbstractIntIterator iterator = provider.iter();

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
        Collector<R> collector = getCollectorToTest();
        for (FailCase testCase : getFailureCases()) {
            List<TestIterable<AbstractIntIterator>> iteratorProviders = IteratorProvider
                    .buildIntIterables(testCase.source);
            for (TestIterable<AbstractIntIterator> provider : iteratorProviders) {
                AbstractIntIterator iterator = provider.iter();
                assertThrows(testCase.expectedFailType,
                        () -> collector.apply(iterator));
            }
        }
    }

    @FunctionalInterface
    public static interface Collector<R> extends
            Function<AbstractIntIterator, R>
    {
    }

    public static class Case<R>
    {
        final List<Integer> source;
        final R expectedResult;

        public Case(List<Integer> source, R expectedResult)
        {
            this.source = source;
            this.expectedResult = expectedResult;
        }
    }

    public static class FailCase
    {
        final List<Integer> source;
        final Class<? extends RuntimeException> expectedFailType;

        public FailCase(List<Integer> source,
                Class<? extends RuntimeException> expectedFailType)
        {
            this.source = source;
            this.expectedFailType = expectedFailType;
        }
    }
}
