package com.github.maumay.jflow.iterators.impl.skip;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;
import com.github.maumay.jflow.utils.Tup;

/**
 * @author t
 */
class AbstractEnhancedIteratorSkipwhileTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		List<Tup<List<String>, Predicate<String>>> testData = asList(
				Tup.of(asList(), string -> !string.equals("5")),
				Tup.of(asList("3", "4"), string -> !string.equals("3")),
				Tup.of(asList("0", "1", "2", "3", "4"), string -> !string.equals("0")));

		testData.stream().forEach(testCase -> {
			assertObjectIteratorAsExpected(testCase._1(),
					createSkipwhileIteratorProviderFrom(populated, testCase._2()));
			assertObjectIteratorAsExpected(asList(),
					createSkipwhileIteratorProviderFrom(empty, testCase._2()));
		});
	}

	private <T> AbstractEnhancedIterable<T> createSkipwhileIteratorProviderFrom(
			AbstractEnhancedIterable<T> src, Predicate<T> predicate)
	{
		return new AbstractEnhancedIterable<T>() {
			@Override
			public AbstractEnhancedIterator<T> iter()
			{
				return src.iter().skipWhile(predicate);
			}
		};
	}
}
