package com.github.maumay.jflow.iterators.impl.skip;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractRichIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.FiniteIteratorTest;
import com.github.maumay.jflow.utils.Tup;

/**
 * @author t
 */
class AbstractEnhancedIteratorSkipwhileTest extends IteratorExampleProviders implements FiniteIteratorTest
{
	@Test
	void test()
	{
		AbstractRichIterable<String> populated = getObjectIteratorProviders();
		AbstractRichIterable<String> empty = getEmptyObjectIteratorProvider();

		List<Tup<List<String>, Predicate<String>>> testData = asList(
				Tup.of(asList(), string -> !string.equals("5")),
				Tup.of(asList("3", "4"), string -> !string.equals("3")),
				Tup.of(asList("0", "1", "2", "3", "4"), string -> !string.equals("0")));

		testData.stream().forEach(testCase -> {
			assertIteratorAsExpected(testCase._1(),
					createSkipwhileIteratorProviderFrom(populated, testCase._2()));
			assertIteratorAsExpected(asList(),
					createSkipwhileIteratorProviderFrom(empty, testCase._2()));
		});
	}

	private <T> AbstractRichIterable<T> createSkipwhileIteratorProviderFrom(
			AbstractRichIterable<T> src, Predicate<T> predicate)
	{
		return new AbstractRichIterable<T>() {
			@Override
			public AbstractRichIterator<T> iter()
			{
				return src.iter().skipWhile(predicate);
			}
		};
	}
}
