/**
 *
 */
package maumay.jflow.iterators.impl.scan;

import static java.util.Arrays.asList;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import org.junit.jupiter.api.Test;

import maumay.jflow.iterators.AbstractEnhancedIterator;
import maumay.jflow.testutilities.AbstractEnhancedIterable;
import maumay.jflow.testutilities.IteratorExampleProvider;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractEnhancedIteratorAccumulationTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void testAccumulationWithoutId()
	{
		final AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("0", "01", "012", "0123", "01234"),
				createAccumlationWithoutIdIteratorProviderFrom(populated, String::concat));
		assertObjectIteratorAsExpected(asList(),
				createAccumlationWithoutIdIteratorProviderFrom(empty, String::concat));
	}

	private <E> AbstractEnhancedIterable<E> createAccumlationWithoutIdIteratorProviderFrom(
			final AbstractEnhancedIterable<E> source, final BinaryOperator<E> accumulator)
	{
		return new AbstractEnhancedIterable<E>() {
			@Override
			public AbstractEnhancedIterator<E> iter()
			{
				return source.iter().scan(accumulator);
			}
		};
	}

	@Test
	void testAccumulationWithId()
	{
		final AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("x0", "x01", "x012", "x0123", "x01234"),
				createAccumlationWithIdIteratorProviderFrom(populated, "x", String::concat));
		assertObjectIteratorAsExpected(asList(),
				createAccumlationWithIdIteratorProviderFrom(empty, "x", String::concat));
	}

	private <E, R> AbstractEnhancedIterable<R> createAccumlationWithIdIteratorProviderFrom(
			final AbstractEnhancedIterable<E> source, final R id,
			final BiFunction<R, E, R> accumulator)
	{
		return new AbstractEnhancedIterable<R>() {
			@Override
			public AbstractEnhancedIterator<R> iter()
			{
				return source.iter().scan(id, accumulator);
			}
		};
	}
}
