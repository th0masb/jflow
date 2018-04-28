/**
 *
 */
package xawd.jflow.abstractflows.taketests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import xawd.jflow.AbstractLongFlow;
import xawd.jflow.abstractiterables.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author t
 *
 */
public class AbstractLongFlowTakeTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final long[][] expectedOutcomesForDifferentIndexArguments = {
				{},
				{0},
				{0, 1},
				{0, 1, 2},
				{0, 1, 2, 3},
				{0, 1, 2, 3, 4}
		};

		final int nArgs = expectedOutcomesForDifferentIndexArguments.length;

		final AbstractIterableLongs populated = getPopulatedLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		IntStream.range(0, nArgs).forEach(i ->
		{
			assertLongIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[i], createTakeIteratorProviderFrom(populated, i));
			assertLongIteratorAsExpected(new long[] {}, createTakeIteratorProviderFrom(empty, i));
		});

		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i ->
		{
			assertThrows(IllegalArgumentException.class, () -> populated.iter().take(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iter().take(i));
		});

		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i ->
		{
			assertLongIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[nArgs - 1], createTakeIteratorProviderFrom(populated, i));
			assertLongIteratorAsExpected(new long[] {}, createTakeIteratorProviderFrom(empty, i));
		});
	}

	private AbstractIterableLongs createTakeIteratorProviderFrom(AbstractIterableLongs src, int takeCount)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongFlow iter() {
				return src.iter().take(takeCount);
			}
		};
	}
}
