package xawd.jflow.iterators.impl.skip;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.testutilities.AbstractIterableInts;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
class AbstractIntFlowSkipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final int[][] expectedOutcomesForDifferentIndexArguments = {
				{0, 1, 2, 3, 4},
				{1, 2, 3, 4},
				{2, 3, 4},
				{3, 4},
				{4},
				{},
		};

		final int nArgs = expectedOutcomesForDifferentIndexArguments.length;

		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		IntStream.range(0, nArgs).forEach(i ->
		{
			assertIntIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[i], createSkipIteratorProviderFrom(populated, i));
			assertIntIteratorAsExpected(new int[0], createSkipIteratorProviderFrom(empty, i));
		});

		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i ->
		{
			assertThrows(IllegalArgumentException.class, () -> populated.iterator().drop(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iterator().drop(i));
		});

		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i ->
		{
			assertIntIteratorAsExpected(new int[0], createSkipIteratorProviderFrom(populated, i));
			assertIntIteratorAsExpected(new int[0], createSkipIteratorProviderFrom(empty, i));
		});
	}

	private AbstractIterableInts createSkipIteratorProviderFrom(AbstractIterableInts src, int skipCount)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntFlow iterator() {
				return src.iterator().drop(skipCount);
			}
		};
	}
}
