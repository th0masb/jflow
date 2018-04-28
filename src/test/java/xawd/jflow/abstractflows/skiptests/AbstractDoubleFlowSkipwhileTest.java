package xawd.jflow.abstractflows.skiptests;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.DoublePredicate;

import org.junit.jupiter.api.Test;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.abstractiterables.AbstractIterableDoubles;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;
import xawd.jflow.zippedpairs.Pair;

/**
 * @author t
 */
class AbstractDoubleFlowSkipwhileTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableDoubles populated = getPopulatedDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		final List<Pair<double[], DoublePredicate>> testData = asList(
				Pair.of(new double[] {0, 1, 2, 3, 4}, x -> x < -0.1),
				Pair.of(new double[] {4}, x -> x < 3.1),
				Pair.of(new double[] {}, x -> x < 5)
				);

		testData.stream().forEach(testCase ->
		{
			assertDoubleIteratorAsExpected(testCase.first(), createSkipwhileIteratorProviderFrom(populated, testCase.second()));
			assertDoubleIteratorAsExpected(new double[] {}, createSkipwhileIteratorProviderFrom(empty, testCase.second()));
		});
	}

	private  AbstractIterableDoubles createSkipwhileIteratorProviderFrom(AbstractIterableDoubles src, DoublePredicate predicate)
	{
		return new AbstractIterableDoubles()
		{
			@Override
			public AbstractDoubleFlow iter()
			{
				return src.iter().skipWhile(predicate);
			}
		};
	}
}