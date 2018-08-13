package xawd.jflow.iterators.impl.take;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.LongPredicate;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.misc.Pair;
import xawd.jflow.testutilities.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
class AbstractLongFlowTakewhileTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		final List<Pair<long[], LongPredicate>> testData = asList(
				Pair.of(new long[] {}, x -> x != 0),
				Pair.of(new long[] {0, 1, 2}, x -> x != 3),
				Pair.of(new long[] {0, 1, 2, 3, 4}, x -> x != 5)
				);

		testData.stream().forEach(testCase ->
		{
			assertLongIteratorAsExpected(testCase.first(), createTakewhileIteratorProviderFrom(populated, testCase.second()));
			assertLongIteratorAsExpected(new long[] {}, createTakewhileIteratorProviderFrom(empty, testCase.second()));
		});
	}

	private  AbstractIterableLongs createTakewhileIteratorProviderFrom(AbstractIterableLongs src, LongPredicate predicate)
	{
		return new AbstractIterableLongs()
		{
			@Override
			public AbstractLongFlow iterator()
			{
				return src.iterator().takeWhile(predicate);
			}
		};
	}
}
