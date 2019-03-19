/**
 *
 */
package com.github.maumay.jflow.iterators.impl.scan;

import java.util.function.DoubleBinaryOperator;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.FiniteIteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleIteratorAccumulationTest extends IteratorExampleProviders implements FiniteIteratorTest
{
	// @Test
	// void testAccumulationWithoutId()
	// {
	// AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
	// AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
	//
	// assertDoubleIteratorAsExpected(new double[] { 0, 1, 3, 6, 10 },
	// createAccumulationWithoutIdIteratorProviderFrom(populated, (a, b) -> a + b));
	// assertDoubleIteratorAsExpected(new double[] {},
	// createAccumulationWithoutIdIteratorProviderFrom(empty, (a, b) -> a + b));
	// }
	//
	// private AbstractIterableDoubles
	// createAccumulationWithoutIdIteratorProviderFrom(
	// AbstractIterableDoubles source, DoubleBinaryOperator accumulator)
	// {
	// return new AbstractIterableDoubles() {
	// @Override
	// public AbstractDoubleIterator iter()
	// {
	// return source.iter().accumulate(accumulator);
	// }
	// };
	// }
	//
	@Test
	void testAccumulationWithId()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		assertDoubleIteratorAsExpected(new double[] { 5, 5, 6, 8, 11, 15 },
				createScanIteratorProviderFrom(populated, 5, (a, b) -> a + b));
		assertDoubleIteratorAsExpected(new double[] { 5 },
				createScanIteratorProviderFrom(empty, 5, (a, b) -> a + b));
	}

	private AbstractIterableDoubles createScanIteratorProviderFrom(AbstractIterableDoubles source,
			double id, DoubleBinaryOperator accumulator)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return source.iter().scan(id, accumulator);
			}
		};
	}
}
