package com.github.maumay.jflow.iterators.impl.skip;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.impl2.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
class AbstractIntIteratorSkipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		int[][] expectedOutcomesForDifferentIndexArguments = { { 0, 1, 2, 3, 4 }, { 1, 2, 3, 4 },
				{ 2, 3, 4 }, { 3, 4 }, { 4 }, {}, };

		int nArgs = expectedOutcomesForDifferentIndexArguments.length;

		AbstractIterableInts populated = getIntTestIteratorProvider();
		AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		IntStream.range(0, nArgs).forEach(i -> {
			assertIntIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[i],
					createSkipIteratorProviderFrom(populated, i));
			assertIntIteratorAsExpected(new int[0], createSkipIteratorProviderFrom(empty, i));
		});

		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i -> {
			assertThrows(IllegalArgumentException.class, () -> populated.iter().skip(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iter().skip(i));
		});

		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i -> {
			assertIntIteratorAsExpected(new int[0], createSkipIteratorProviderFrom(populated, i));
			assertIntIteratorAsExpected(new int[0], createSkipIteratorProviderFrom(empty, i));
		});
	}

	private AbstractIterableInts createSkipIteratorProviderFrom(AbstractIterableInts src,
			int skipCount)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return src.iter().skip(skipCount);
			}
		};
	}
}
