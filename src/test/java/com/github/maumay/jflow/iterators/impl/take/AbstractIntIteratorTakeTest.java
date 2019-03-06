/**
 *
 */
package com.github.maumay.jflow.iterators.impl.take;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
class AbstractIntIteratorTakeTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		int[][] expectedOutcomesForDifferentIndexArguments = { {}, { 0 }, { 0, 1 }, { 0, 1, 2 },
				{ 0, 1, 2, 3 }, { 0, 1, 2, 3, 4 } };

		int nArgs = expectedOutcomesForDifferentIndexArguments.length;

		AbstractIterableInts populated = getIntTestIteratorProvider();
		AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		IntStream.range(0, nArgs).forEach(i -> {
			assertIntIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[i],
					createTakeIteratorProviderFrom(populated, i));
			assertIntIteratorAsExpected(new int[] {}, createTakeIteratorProviderFrom(empty, i));
		});

		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i -> {
			assertThrows(IllegalArgumentException.class, () -> populated.iter().take(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iter().take(i));
		});

		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i -> {
			assertIntIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[nArgs - 1],
					createTakeIteratorProviderFrom(populated, i));
			assertIntIteratorAsExpected(new int[] {}, createTakeIteratorProviderFrom(empty, i));
		});
	}

	private AbstractIterableInts createTakeIteratorProviderFrom(AbstractIterableInts src,
			int takeCount)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return src.iter().take(takeCount);
			}
		};
	}
}
