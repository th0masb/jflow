/**
 *
 */
package xawd.jflow.iterators.abstractflows.accumulationtests;

import static java.util.Arrays.asList;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.abstractiterables.AbstractFlowIterable;
import xawd.jflow.iterators.testutilities.IteratorExampleProvider;
import xawd.jflow.iterators.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractFlowAccumulationTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testAccumulationWithoutId()
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("0", "01", "012", "0123", "01234"), createAccumlationWithoutIdIteratorProviderFrom(populated, String::concat));
		assertObjectIteratorAsExpected(asList(), createAccumlationWithoutIdIteratorProviderFrom(empty, String::concat));
	}

	private <E> AbstractFlowIterable<E> createAccumlationWithoutIdIteratorProviderFrom(final AbstractFlowIterable<E> source, final BinaryOperator<E> accumulator)
	{
		return new AbstractFlowIterable<E>() {
			@Override
			public AbstractFlow<E> iter() {
				return source.iter().accumulate(accumulator);
			}
		};
	}

	@Test
	void testAccumulationWithId()
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("x0", "x01", "x012", "x0123", "x01234"), createAccumlationWithIdIteratorProviderFrom(populated, "x", String::concat));
		assertObjectIteratorAsExpected(asList(), createAccumlationWithIdIteratorProviderFrom(empty, "x", String::concat));
	}

	private <E, R> AbstractFlowIterable<R> createAccumlationWithIdIteratorProviderFrom(final AbstractFlowIterable<E> source, final R id, final BiFunction<R, E, R> accumulator)
	{
		return new AbstractFlowIterable<R>() {
			@Override
			public AbstractFlow<R> iter() {
				return source.iter().accumulate(id, accumulator);
			}
		};
	}
}
