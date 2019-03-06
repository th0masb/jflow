/**
 *
 */
package com.github.maumay.jflow.iterators.impl.scan;

import static java.util.Arrays.asList;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

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
		AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("0", "01", "012", "0123", "01234"),
				createAccumlationWithoutIdIteratorProviderFrom(populated, String::concat));
		assertObjectIteratorAsExpected(asList(),
				createAccumlationWithoutIdIteratorProviderFrom(empty, String::concat));
	}

	private <E> AbstractEnhancedIterable<E> createAccumlationWithoutIdIteratorProviderFrom(
			AbstractEnhancedIterable<E> source, BinaryOperator<E> accumulator)
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
		AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("x0", "x01", "x012", "x0123", "x01234"),
				createAccumlationWithIdIteratorProviderFrom(populated, "x", String::concat));
		assertObjectIteratorAsExpected(asList(),
				createAccumlationWithIdIteratorProviderFrom(empty, "x", String::concat));
	}

	private <E, R> AbstractEnhancedIterable<R> createAccumlationWithIdIteratorProviderFrom(
			AbstractEnhancedIterable<E> source, R id, BiFunction<R, E, R> accumulator)
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
