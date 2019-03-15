/**
 *
 */
package com.github.maumay.jflow.iterators.impl.scan;

import static java.util.Arrays.asList;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractEnhancedIterator;
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
	void testAccumulationWithId()
	{
		AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("x", "x0", "x01", "x012", "x0123", "x01234"),
				createAccumlationWithIdIteratorProviderFrom(populated, "x", String::concat));
		assertObjectIteratorAsExpected(asList("x"),
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
