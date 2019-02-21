package com.github.maumay.jflow.iterators.impl.take;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.IntPredicate;

import org.junit.jupiter.api.Test;

import com.gihub.maumay.jflow.iterators.misc.Pair;
import com.github.maumay.jflow.iterators.AbstractEnhancedIntIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

class AbstractEnhancedIntIteratorTakewhileTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();

		final List<Pair<int[], IntPredicate>> testData = asList(
				Pair.of(new int[] {}, x -> x < -0.1),
				Pair.of(new int[] { 0, 1, 2, 3 }, x -> x < 3.1),
				Pair.of(new int[] { 0, 1, 2, 3, 4 }, x -> x < 5));

		testData.stream().forEach(testCase -> {
			assertIntIteratorAsExpected(testCase._1(),
					createTakewhileIteratorProviderFrom(populated, testCase._2()));
			assertIntIteratorAsExpected(new int[] {},
					createTakewhileIteratorProviderFrom(empty, testCase._2()));
		});
	}

	private AbstractIterableInts createTakewhileIteratorProviderFrom(
			AbstractIterableInts src, IntPredicate predicate)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractEnhancedIntIterator iter()
			{
				return src.iter().takeWhile(predicate);
			}
		};
	}
}
