/**
 *
 */
package com.github.maumay.jflow.iterators.impl.scan;

import java.util.function.DoubleBinaryOperator;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleIteratorAccumulationTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testAccumulationWithoutId()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		assertDoubleIteratorAsExpected(new double[] { 0, 1, 3, 6, 10 },
				createAccumulationWithoutIdIteratorProviderFrom(populated, (a, b) -> a + b));
		assertDoubleIteratorAsExpected(new double[] {},
				createAccumulationWithoutIdIteratorProviderFrom(empty, (a, b) -> a + b));
	}

	private AbstractIterableDoubles createAccumulationWithoutIdIteratorProviderFrom(
			AbstractIterableDoubles source, DoubleBinaryOperator accumulator)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return source.iter().accumulate(accumulator);
			}
		};
	}

	@Test
	void testAccumulationWithId()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		assertDoubleIteratorAsExpected(new double[] { 5, 6, 8, 11, 15 },
				createAccumulationWithIdIteratorProviderFrom(populated, 5, (a, b) -> a + b));
		assertDoubleIteratorAsExpected(new double[] {},
				createAccumulationWithIdIteratorProviderFrom(empty, 5, (a, b) -> a + b));
	}

	private AbstractIterableDoubles createAccumulationWithIdIteratorProviderFrom(
			AbstractIterableDoubles source, double id, DoubleBinaryOperator accumulator)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return source.iter().accumulate(id, accumulator);
			}
		};
	}
}
