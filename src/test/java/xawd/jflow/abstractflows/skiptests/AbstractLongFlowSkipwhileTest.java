package xawd.jflow.abstractflows.skiptests;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.LongPredicate;

import org.junit.jupiter.api.Test;

import xawd.jflow.AbstractLongFlow;
import xawd.jflow.abstractiterables.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;
import xawd.jflow.zippedpairs.Pair;

/**
 * @author t
 */
class AbstractLongFlowSkipwhileTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableLongs populated = getPopulatedLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		final List<Pair<long[], LongPredicate>> testData = asList(
				Pair.of(new long[] {0, 1, 2, 3, 4}, x -> x < -0.1),
				Pair.of(new long[] {4}, x -> x < 3.1),
				Pair.of(new long[] {}, x -> x < 5)
				);

		testData.stream().forEach(testCase ->
		{
			assertLongIteratorAsExpected(testCase.first(), createSkipwhileIteratorProviderFrom(populated, testCase.second()));
			assertLongIteratorAsExpected(new long[] {}, createSkipwhileIteratorProviderFrom(empty, testCase.second()));
		});
	}

	private  AbstractIterableLongs createSkipwhileIteratorProviderFrom(AbstractIterableLongs src, LongPredicate predicate)
	{
		return new AbstractIterableLongs()
		{
			@Override
			public AbstractLongFlow iter()
			{
				return src.iter().skipWhile(predicate);
			}
		};
	}
}
