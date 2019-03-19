package com.github.maumay.jflow.iterators.impl.skip;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.IntPredicate;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.FiniteIteratorTest;
import com.github.maumay.jflow.utils.Tup;

/**
 * @author t
 */
class AbstractIntIteratorSkipwileTest extends IteratorExampleProviders implements FiniteIteratorTest
{
	@Test
	void test()
	{
		AbstractIterableInts populated = getIntTestIteratorProvider();
		AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		List<Tup<int[], IntPredicate>> testData = asList(
				Tup.of(new int[] { 0, 1, 2, 3, 4 }, x -> x < -0.1),
				Tup.of(new int[] { 4 }, x -> x < 3.1), Tup.of(new int[] {}, x -> x < 5));

		testData.stream().forEach(testCase -> {
			assertIntIteratorAsExpected(testCase._1(),
					createSkipwhileIteratorProviderFrom(populated, testCase._2()));
			assertIntIteratorAsExpected(new int[] {},
					createSkipwhileIteratorProviderFrom(empty, testCase._2()));
		});
	}

	private AbstractIterableInts createSkipwhileIteratorProviderFrom(AbstractIterableInts src,
			IntPredicate predicate)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return src.iter().skipWhile(predicate);
			}
		};
	}
}
