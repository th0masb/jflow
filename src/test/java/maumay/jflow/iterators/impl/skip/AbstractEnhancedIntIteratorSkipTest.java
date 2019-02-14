package maumay.jflow.iterators.impl.skip;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import maumay.jflow.iterators.AbstractEnhancedIntIterator;
import maumay.jflow.testutilities.AbstractIterableInts;
import maumay.jflow.testutilities.IteratorExampleProvider;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
class AbstractEnhancedIntIteratorSkipTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		final int[][] expectedOutcomesForDifferentIndexArguments = { { 0, 1, 2, 3, 4 },
				{ 1, 2, 3, 4 }, { 2, 3, 4 }, { 3, 4 }, { 4 }, {}, };

		final int nArgs = expectedOutcomesForDifferentIndexArguments.length;

		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		IntStream.range(0, nArgs).forEach(i -> {
			assertIntIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[i],
					createSkipIteratorProviderFrom(populated, i));
			assertIntIteratorAsExpected(new int[0],
					createSkipIteratorProviderFrom(empty, i));
		});

		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i -> {
			assertThrows(IllegalArgumentException.class,
					() -> populated.iter().drop(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iter().drop(i));
		});

		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i -> {
			assertIntIteratorAsExpected(new int[0],
					createSkipIteratorProviderFrom(populated, i));
			assertIntIteratorAsExpected(new int[0],
					createSkipIteratorProviderFrom(empty, i));
		});
	}

	private AbstractIterableInts createSkipIteratorProviderFrom(AbstractIterableInts src,
			int skipCount)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractEnhancedIntIterator iter()
			{
				return src.iter().drop(skipCount);
			}
		};
	}
}
