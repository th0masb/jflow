/**
 *
 */
package xawd.jflow.iterators.impl.skip;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.testutilities.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author t
 *
 */
class AbstractLongFlowSkipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final long[][] expectedOutcomesForDifferentIndexArguments = {
				{0, 1, 2, 3, 4},
				{1, 2, 3, 4},
				{2, 3, 4},
				{3, 4},
				{4},
				{},
		};

		final int nArgs = expectedOutcomesForDifferentIndexArguments.length;

		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		IntStream.range(0, nArgs).forEach(i ->
		{
			assertLongIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[i], createSkipIteratorProviderFrom(populated, i));
			assertLongIteratorAsExpected(new long[0], createSkipIteratorProviderFrom(empty, i));
		});

		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i ->
		{
			assertThrows(IllegalArgumentException.class, () -> populated.iterator().drop(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iterator().drop(i));
		});

		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i ->
		{
			assertLongIteratorAsExpected(new long[0], createSkipIteratorProviderFrom(populated, i));
			assertLongIteratorAsExpected(new long[0], createSkipIteratorProviderFrom(empty, i));
		});
	}

	private AbstractIterableLongs createSkipIteratorProviderFrom(AbstractIterableLongs src, int skipCount)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongFlow iterator() {
				return src.iterator().drop(skipCount);
			}
		};
	}
}
