/**
 *
 */
package com.github.maumay.jflow.iterators.impl.scan;

import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractLongIteratorAccumulationTest extends IteratorExampleProviders implements IteratorTest
{
	// @Test
	// void testAccumulationWithoutId()
	// {
	// AbstractIterableLongs populated = getLongTestIteratorProvider();
	// AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
	//
	// assertLongIteratorAsExpected(new long[] { 0, 1, 3, 6, 10 },
	// createAccumulationWithoutIdIteratorProviderFrom(populated, (a, b) -> a + b));
	// assertLongIteratorAsExpected(new long[] {},
	// createAccumulationWithoutIdIteratorProviderFrom(empty, (a, b) -> a + b));
	// }
	//
	// private AbstractIterableLongs
	// createAccumulationWithoutIdIteratorProviderFrom(
	// AbstractIterableLongs source, LongBinaryOperator accumulator)
	// {
	// return new AbstractIterableLongs() {
	// @Override
	// public AbstractLongIterator iter()
	// {
	// return source.iter().scan(accumulator);
	// }
	// };
	// }
	//
	// @Test
	// void testAccumulationWithId()
	// {
	// AbstractIterableLongs populated = getLongTestIteratorProvider();
	// AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
	//
	// assertLongIteratorAsExpected(new long[] { 5, 6, 8, 11, 15 },
	// createAccumulationWithIdIteratorProviderFrom(populated, 5, (a, b) -> a + b));
	// assertLongIteratorAsExpected(new long[] {},
	// createAccumulationWithIdIteratorProviderFrom(empty, 5, (a, b) -> a + b));
	// }
	//
	// private AbstractIterableLongs createAccumulationWithIdIteratorProviderFrom(
	// AbstractIterableLongs source, long id, LongBinaryOperator accumulator)
	// {
	// return new AbstractIterableLongs() {
	// @Override
	// public AbstractLongIterator iter()
	// {
	// return source.iter().scan(id, accumulator);
	// }
	// };
	// }
}
