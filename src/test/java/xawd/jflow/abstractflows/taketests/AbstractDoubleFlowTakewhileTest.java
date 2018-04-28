package xawd.jflow.abstractflows.taketests;

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
class AbstractDoubleFlowTakewhileTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableDoubles populated = getPopulatedDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		final List<Pair<double[], DoublePredicate>> testData = asList(
				Pair.of(new double[] {}, x -> x < -0.1),
				Pair.of(new double[] {0, 1, 2, 3}, x -> x < 3.1),
				Pair.of(new double[] {0, 1, 2, 3, 4}, x -> x < 5)
				);

		testData.stream().forEach(testCase ->
		{
			assertDoubleIteratorAsExpected(testCase.first(), createTakewhileIteratorProviderFrom(populated, testCase.second()));
			assertDoubleIteratorAsExpected(new double[] {}, createTakewhileIteratorProviderFrom(empty, testCase.second()));
		});
	}

	private  AbstractIterableDoubles createTakewhileIteratorProviderFrom(AbstractIterableDoubles src, DoublePredicate predicate)
	{
		return new AbstractIterableDoubles()
		{
			@Override
			public AbstractDoubleFlow iterator()
			{
				return src.iterator().takeWhile(predicate);
			}
		};
	}
}
