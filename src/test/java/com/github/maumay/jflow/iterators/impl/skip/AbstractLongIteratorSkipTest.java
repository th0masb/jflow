/**
 *
 */
package com.github.maumay.jflow.iterators.impl.skip;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author t
 *
 */
class AbstractLongIteratorSkipTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		final long[][] expectedOutcomesForDifferentIndexArguments = { { 0, 1, 2, 3, 4 },
				{ 1, 2, 3, 4 }, { 2, 3, 4 }, { 3, 4 }, { 4 }, {}, };

		final int nArgs = expectedOutcomesForDifferentIndexArguments.length;

		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		IntStream.range(0, nArgs).forEach(i -> {
			assertLongIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[i],
					createSkipIteratorProviderFrom(populated, i));
			assertLongIteratorAsExpected(new long[0],
					createSkipIteratorProviderFrom(empty, i));
		});

		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i -> {
			assertThrows(IllegalArgumentException.class,
					() -> populated.iter().skip(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iter().skip(i));
		});

		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i -> {
			assertLongIteratorAsExpected(new long[0],
					createSkipIteratorProviderFrom(populated, i));
			assertLongIteratorAsExpected(new long[0],
					createSkipIteratorProviderFrom(empty, i));
		});
	}

	private AbstractIterableLongs createSkipIteratorProviderFrom(
			AbstractIterableLongs src, int skipCount)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return src.iter().skip(skipCount);
			}
		};
	}
}
