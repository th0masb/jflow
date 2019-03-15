/**
 *
 */
package com.github.maumay.jflow.iterators.impl.scan;

import java.util.function.IntBinaryOperator;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractIntIteratorAccumulationTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testAccumulationWithoutId()
	{
		AbstractIterableInts populated = getIntTestIteratorProvider();
		AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		assertIntIteratorAsExpected(new int[] { 0, 1, 3, 6, 10 },
				createAccumulationWithoutIdIteratorProviderFrom(populated, (a, b) -> a + b));
		assertIntIteratorAsExpected(new int[] {},
				createAccumulationWithoutIdIteratorProviderFrom(empty, (a, b) -> a + b));
	}

	private AbstractIterableInts createAccumulationWithoutIdIteratorProviderFrom(
			AbstractIterableInts source, IntBinaryOperator accumulator)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return source.iter().scan(accumulator);
			}
		};
	}

	@Test
	void testAccumulationWithId()
	{
		AbstractIterableInts populated = getIntTestIteratorProvider();
		AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		assertIntIteratorAsExpected(new int[] { 5, 6, 8, 11, 15 },
				createAccumulationWithIdIteratorProviderFrom(populated, 5, (a, b) -> a + b));
		assertIntIteratorAsExpected(new int[] {},
				createAccumulationWithIdIteratorProviderFrom(empty, 5, (a, b) -> a + b));
	}

	private AbstractIterableInts createAccumulationWithIdIteratorProviderFrom(
			AbstractIterableInts source, int id, IntBinaryOperator accumulator)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return source.iter().scan(id, accumulator);
			}
		};
	}
}
