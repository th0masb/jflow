package maumay.jflow.iterators.impl.take;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.DoublePredicate;

import org.junit.jupiter.api.Test;

import maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import maumay.jflow.iterators.misc.Pair;
import maumay.jflow.testutilities.AbstractIterableDoubles;
import maumay.jflow.testutilities.IteratorExampleProvider;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
class AbstractEnhancedDoubleIteratorTakewhileTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		final List<Pair<double[], DoublePredicate>> testData = asList(
				Pair.of(new double[] {}, x -> x < -0.1),
				Pair.of(new double[] { 0, 1, 2, 3 }, x -> x < 3.1),
				Pair.of(new double[] { 0, 1, 2, 3, 4 }, x -> x < 5));

		testData.stream().forEach(testCase -> {
			assertDoubleIteratorAsExpected(testCase._1(),
					createTakewhileIteratorProviderFrom(populated, testCase._2()));
			assertDoubleIteratorAsExpected(new double[] {},
					createTakewhileIteratorProviderFrom(empty, testCase._2()));
		});
	}

	private AbstractIterableDoubles createTakewhileIteratorProviderFrom(
			AbstractIterableDoubles src, DoublePredicate predicate)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractEnhancedDoubleIterator iter()
			{
				return src.iter().takeWhile(predicate);
			}
		};
	}
}
