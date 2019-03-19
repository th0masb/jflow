package com.github.maumay.jflow.iterators.impl.skip;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.DoublePredicate;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.FiniteIteratorTest;
import com.github.maumay.jflow.utils.Tup;

/**
 * @author t
 */
class AbstractDoubleIteratorSkipwhileTest extends IteratorExampleProviders implements FiniteIteratorTest
{
	@Test
	void test()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		List<Tup<double[], DoublePredicate>> testData = asList(
				Tup.of(new double[] { 0, 1, 2, 3, 4 }, x -> x < -0.1),
				Tup.of(new double[] { 4 }, x -> x < 3.1), Tup.of(new double[] {}, x -> x < 5));

		testData.stream().forEach(testCase -> {
			assertDoubleIteratorAsExpected(testCase._1(),
					createSkipwhileIteratorProviderFrom(populated, testCase._2()));
			assertDoubleIteratorAsExpected(new double[] {},
					createSkipwhileIteratorProviderFrom(empty, testCase._2()));
		});
	}

	private AbstractIterableDoubles createSkipwhileIteratorProviderFrom(AbstractIterableDoubles src,
			DoublePredicate predicate)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return src.iter().skipWhile(predicate);
			}
		};
	}
}
