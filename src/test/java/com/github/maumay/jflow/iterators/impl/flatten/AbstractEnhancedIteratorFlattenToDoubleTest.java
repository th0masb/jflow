/**
 *
 */
package com.github.maumay.jflow.iterators.impl.flatten;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.DoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorFlattenToDoubleTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		Function<String, AbstractDoubleIterator> flattenMapping1 = string -> repeat(
				Double.parseDouble(string), 2 * (Integer.parseInt(string) % 2));
		assertDoubleIteratorAsExpected(new double[] { 1, 1, 3, 3 },
				createFlattenToDoublesIteratorProviderFrom(populated, flattenMapping1));
		assertDoubleIteratorAsExpected(new double[0],
				createFlattenToDoublesIteratorProviderFrom(empty, flattenMapping1));

		Function<String, AbstractDoubleIterator> flattenMapping2 = string -> repeat(
				Double.parseDouble(string), 2 * ((Integer.parseInt(string) + 1) % 2));
		assertDoubleIteratorAsExpected(new double[] { 0, 0, 2, 2, 4, 4 },
				createFlattenToDoublesIteratorProviderFrom(populated, flattenMapping2));
		assertDoubleIteratorAsExpected(new double[0],
				createFlattenToDoublesIteratorProviderFrom(empty, flattenMapping2));
	}

	private AbstractDoubleIterator repeat(double element, int nTimes)
	{
		return new AbstractDoubleIterator(OptionalInt.of(nTimes)) {
			int count = 0;

			@Override
			public boolean hasNext()
			{
				return count < nTimes;
			}

			@Override
			public double nextDouble()
			{
				if (count++ >= nTimes) {
					throw new NoSuchElementException();
				}
				return element;
			}

			@Override
			public void skip()
			{
				nextDouble();
			}
		};
	}

	private <E> AbstractIterableDoubles createFlattenToDoublesIteratorProviderFrom(
			AbstractEnhancedIterable<E> source,
			Function<? super E, ? extends DoubleIterator> flattenMapping)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return source.iterator().flatMapToDouble(flattenMapping);
			}
		};
	}
}
