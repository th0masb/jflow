/**
 *
 */
package com.github.maumay.jflow.iterators.impl.flatten;

import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.FiniteIteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorFlattenToDoubleTest extends IteratorExampleProviders
		implements FiniteIteratorTest
{
	// @Test
	// void test()
	// {
	// AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
	// AbstractEnhancedIterable<String> empty =
	// getEmptyObjectTestIteratorProvider();
	//
	// Function<String, AbstractDoubleIterator> flattenMapping1 = string -> repeat(
	// Double.parseDouble(string), 2 * (Integer.parseInt(string) % 2));
	// assertDoubleIteratorAsExpected(new double[] { 1, 1, 3, 3 },
	// createFlattenToDoublesIteratorProviderFrom(populated, flattenMapping1));
	// assertDoubleIteratorAsExpected(new double[0],
	// createFlattenToDoublesIteratorProviderFrom(empty, flattenMapping1));
	//
	// Function<String, AbstractDoubleIterator> flattenMapping2 = string -> repeat(
	// Double.parseDouble(string), 2 * ((Integer.parseInt(string) + 1) % 2));
	// assertDoubleIteratorAsExpected(new double[] { 0, 0, 2, 2, 4, 4 },
	// createFlattenToDoublesIteratorProviderFrom(populated, flattenMapping2));
	// assertDoubleIteratorAsExpected(new double[0],
	// createFlattenToDoublesIteratorProviderFrom(empty, flattenMapping2));
	// }
	//
	// private AbstractDoubleIterator repeat(double element, int nTimes)
	// {
	// return new AbstractDoubleIterator(OptionalInt.of(nTimes)) {
	// int count = 0;
	//
	// @Override
	// public boolean hasNext()
	// {
	// return count < nTimes;
	// }
	//
	// @Override
	// public double nextDouble()
	// {
	// if (count++ >= nTimes) {
	// throw new NoSuchElementException();
	// }
	// return element;
	// }
	//
	// @Override
	// public void skip()
	// {
	// nextDouble();
	// }
	// };
	// }
	//
	// private <E> AbstractIterableDoubles
	// createFlattenToDoublesIteratorProviderFrom(
	// AbstractEnhancedIterable<E> source,
	// Function<? super E, ? extends DoubleIterator> flattenMapping)
	// {
	// return new AbstractIterableDoubles() {
	// @Override
	// public AbstractDoubleIterator iter()
	// {
	// return source.iterator().flatMapToDouble(flattenMapping);
	// }
	// };
	// }
}
