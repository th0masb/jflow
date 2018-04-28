/**
 *
 */
package xawd.jflow.abstractflows.taketests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import xawd.jflow.AbstractIntFlow;
import xawd.jflow.abstractiterables.AbstractIterableInts;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
class AbstractIntFlowTakeTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final int[][] expectedOutcomesForDifferentIndexArguments = {
				{},
				{0},
				{0, 1},
				{0, 1, 2},
				{0, 1, 2, 3},
				{0, 1, 2, 3, 4}
		};

		final int nArgs = expectedOutcomesForDifferentIndexArguments.length;

		final AbstractIterableInts populated = getPopulatedIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		IntStream.range(0, nArgs).forEach(i ->
		{
			assertIntIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[i], createTakeIteratorProviderFrom(populated, i));
			assertIntIteratorAsExpected(new int[] {}, createTakeIteratorProviderFrom(empty, i));
		});

		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i ->
		{
			assertThrows(IllegalArgumentException.class, () -> populated.iter().take(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iter().take(i));
		});

		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i ->
		{
			assertIntIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[nArgs - 1], createTakeIteratorProviderFrom(populated, i));
			assertIntIteratorAsExpected(new int[] {}, createTakeIteratorProviderFrom(empty, i));
		});
	}

	private AbstractIterableInts createTakeIteratorProviderFrom(AbstractIterableInts src, int takeCount)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntFlow iter() {
				return src.iter().take(takeCount);
			}
		};
	}
}
