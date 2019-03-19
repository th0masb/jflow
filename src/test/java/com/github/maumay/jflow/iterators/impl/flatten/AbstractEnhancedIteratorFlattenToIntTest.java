/**
 *
 */
package com.github.maumay.jflow.iterators.impl.flatten;

import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorFlattenToIntTest extends IteratorExampleProviders
		implements IteratorTest
{
	// @Test
	// void test()
	// {
	// AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
	// AbstractEnhancedIterable<String> empty =
	// getEmptyObjectTestIteratorProvider();
	//
	// Function<String, AbstractIntIterator> flattenMapping1 = string -> repeat(
	// Integer.parseInt(string), 2 * (Integer.parseInt(string) % 2));
	// assertIntIteratorAsExpected(new int[] { 1, 1, 3, 3 },
	// createFlattenToIntsIteratorProviderFrom(populated, flattenMapping1));
	// assertIntIteratorAsExpected(new int[0],
	// createFlattenToIntsIteratorProviderFrom(empty, flattenMapping1));
	//
	// Function<String, AbstractIntIterator> flattenMapping2 = string -> repeat(
	// Integer.parseInt(string), 2 * ((Integer.parseInt(string) + 1) % 2));
	// assertIntIteratorAsExpected(new int[] { 0, 0, 2, 2, 4, 4 },
	// createFlattenToIntsIteratorProviderFrom(populated, flattenMapping2));
	// assertIntIteratorAsExpected(new int[0],
	// createFlattenToIntsIteratorProviderFrom(empty, flattenMapping2));
	// }
	//
	// private AbstractIntIterator repeat(int element, int nTimes)
	// {
	// return new AbstractIntIterator(OptionalInt.of(nTimes)) {
	// int count = 0;
	//
	// @Override
	// public boolean hasNext()
	// {
	// return count < nTimes;
	// }
	//
	// @Override
	// public int nextInt()
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
	// nextInt();
	// }
	// };
	// }
	//
	// private <E> AbstractIterableInts createFlattenToIntsIteratorProviderFrom(
	// AbstractEnhancedIterable<E> source,
	// Function<? super E, ? extends IntIterator> flattenMapping)
	// {
	// return new AbstractIterableInts() {
	// @Override
	// public AbstractIntIterator iter()
	// {
	// return source.iterator().flatMapToInt(flattenMapping);
	// }
	// };
	// }
}
